package com.hfbh.yuecheng.ui;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.PhoneNumberUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.hfbh.yuecheng.view.PermissionDialog;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import me.iwf.photopicker.utils.AndroidLifecycleUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Author：Libin on 2018/5/30 13:59
 * Email：1993911441@qq.com
 * Describe：意见反馈
 */
public class FeedBackActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.et_feedback_content)
    EditText etContent;
    @BindView(R.id.img_add_feed_back)
    ImageView imgAddFeedBack;
    @BindView(R.id.rv_feed_back)
    RecyclerView rvFeedBack;
    @BindView(R.id.et_feedback_phone)
    EditText etFeedbackPhone;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;
    @BindView(R.id.tv_confirm_feedback)
    TextView tvConfirmFeedback;

    private CommonAdapter adapter;
    private ArrayList<String> photoList = new ArrayList<>();

    static final String[] permissionStr = {Manifest.permission.CAMERA};
    //反馈图片url
    private String imgUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeaderTitle.setText("意见反馈");
        rvFeedBack.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        adapter = new CommonAdapter<String>(this, R.layout.rv_photopicker_item, photoList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

                Uri uri = Uri.fromFile(new File(s));

                boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(FeedBackActivity.this);

                if (canLoadImage) {
                    Glide.with(FeedBackActivity.this)
                            .load(uri)
                            .centerCrop()
                            .placeholder(me.iwf.photopicker.R.drawable.__picker_ic_photo_black_48dp)
                            .error(me.iwf.photopicker.R.drawable.__picker_ic_broken_image_black_48dp)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .into((ImageView) holder.getView(R.id.iv_picker_photo));
                }
            }
        };

        rvFeedBack.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                PhotoPreview.builder()
                        .setPhotos(photoList)
                        .setCurrentItem(position)
                        .start(FeedBackActivity.this);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @OnClick({R.id.iv_header_back, R.id.img_add_feed_back, R.id.tv_confirm_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.img_add_feed_back:
                if (!EasyPermissions.hasPermissions(this, permissionStr)) {
                    EasyPermissions.requestPermissions(this, "", 123, permissionStr);
                } else {
                    PhotoPicker.builder()
                            .setPhotoCount(3)
                            .setShowCamera(true)
                            .setSelected(photoList)
                            .start(this);
                }

                break;
            case R.id.tv_confirm_feedback:
                if (!TextUtils.isEmpty(etContent.getText().toString().trim())) {
                    if (!TextUtils.isEmpty(etFeedbackPhone.getText().toString().trim())) {
                        if (PhoneNumberUtils.judgePhoneNumber(etFeedbackPhone.getText().toString().trim())) {
                            loadingView.smoothToShow();
                            if (photoList.size() > 0) {
                                uploadImg();
                            } else {
                                submitData();
                            }
                        } else {
                            ToastUtils.showToast(this, "手机号格式不正确，请重新输入");
                        }
                    } else {
                        ToastUtils.showToast(this, "请输入手机号");
                    }

                } else {
                    ToastUtils.showToast(this, "请留下您的宝贵意见");
                }

                break;
        }
    }

    /**
     * 图片上传
     */
    private void uploadImg() {

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (int i = 0; i < photoList.size(); i++) {
            File file = new File(photoList.get(i));
            builder.addFormDataPart("img" + i, file.getName(),
                    RequestBody.create(MediaType.parse("image/png"), file));
        }

        MultipartBody requestBody = builder.build();
        //构建请求
        final Request request = new Request.Builder()
                .url(Constant.UPLOAD_FILE)
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) {

                try {
                    ResponseBean responseBean = GsonUtils.jsonToBean(response.body().string(),
                            ResponseBean.class);
                    if (responseBean.isFlag() && !TextUtils.isEmpty(responseBean.getData())) {
                        imgUrl = responseBean.getData();
                        submitData();
                    } else {
                        submitData();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 提交反馈
     */
    private void submitData() {
        Map<String, String> map = new HashMap<>();
        map.put("appType", MyApp.appType);
        map.put("appVersion", MyApp.appVersion);
        map.put("organizeId", MyApp.organizeId);
        map.put("hash", SharedPreUtils.getStr(this, "hash"));
        map.put("token", SharedPreUtils.getStr(this, "token"));
        map.put("content", etContent.getText().toString().trim());
        if (imgUrl != null) {
            map.put("imgPath", imgUrl);
        }
        map.put("phone", etFeedbackPhone.getText().toString().trim());

        OkHttpUtils.post()
                .url(Constant.FEED_BACK)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        loadingView.smoothToHide();
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                        if (responseBean.isFlag()) {
                            ToastUtils.showToast(FeedBackActivity.this, "反馈成功");
                            finish();
                        } else {
                            ToastUtils.showToast(FeedBackActivity.this, responseBean.getMsg());
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE
                || requestCode == PhotoPreview.REQUEST_CODE)) {

            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            photoList.clear();

            if (photos != null) {
                photoList.addAll(photos);
                adapter.notifyDataSetChanged();
            }

        }
    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return false;
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        PermissionDialog.showPermissionDialog(this, "相机");
    }
}
