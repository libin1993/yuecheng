package com.hfbh.yuecheng.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.PhoneNumberUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.hfbh.yuecheng.view.PermissionDialog;
import com.jungly.gridpasswordview.GridPasswordView;
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
 * Author：Libin on 2018/7/27 16:27
 * Email：1993911441@qq.com
 * Describe：申请退款
 */
public class ApplyRefundActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_refund_type)
    TextView tvRefundType;
    @BindView(R.id.tv_refund_reason)
    TextView tvRefundReason;
    @BindView(R.id.rl_refund_reason)
    RelativeLayout rlRefundReason;
    @BindView(R.id.tv_refund_total_money)
    TextView tvRefundMoney;
    @BindView(R.id.et_refund_intro)
    EditText etRefund;
    @BindView(R.id.iv_refund_pic)
    ImageView ivRefund;
    @BindView(R.id.rv_refund)
    RecyclerView rvRefund;
    @BindView(R.id.tv_confirm_refund)
    TextView tvConfirmRefund;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    //订单id
    private int orderId;
    //退款类型
    private String refundType;
    //退款金额
    private double money;
    //退款理由
    private String refundReason;
    //是否点击确定按钮
    private boolean isConfirm;
    //当前退款理由
    private int reasonPos = -1;

    private ArrayList<String> photoList = new ArrayList<>();
    private CommonAdapter<String> adapter;
    private String[] permissionStr = {Manifest.permission.CAMERA};
    //反馈图片url
    private String imgUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_refund);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("申请退款");
        getData();
        initView();
    }

    private void initView() {
        if ("REFUND".equals(refundType)) {
            tvRefundType.setText("仅退款");
        } else {
            tvRefundType.setText("退货退款");
        }

        tvRefundMoney.setText("¥" + DisplayUtils.decimalFormat(money));

        rvRefund.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        adapter = new CommonAdapter<String>(this, R.layout.rv_photopicker_item, photoList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

                Uri uri = Uri.fromFile(new File(s));

                boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(ApplyRefundActivity.this);

                if (canLoadImage) {
                    Glide.with(ApplyRefundActivity.this)
                            .load(uri)
                            .centerCrop()
                            .placeholder(me.iwf.photopicker.R.drawable.__picker_ic_photo_black_48dp)
                            .error(me.iwf.photopicker.R.drawable.__picker_ic_broken_image_black_48dp)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .into((ImageView) holder.getView(R.id.iv_picker_photo));
                }
            }
        };

        rvRefund.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                PhotoPreview.builder()
                        .setPhotos(photoList)
                        .setCurrentItem(position)
                        .start(ApplyRefundActivity.this);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    private void getData() {
        Intent intent = getIntent();
        orderId = intent.getIntExtra("order_id", -1);
        refundType = intent.getStringExtra("refund_type");
        money = intent.getDoubleExtra("money", 0);
    }

    @OnClick({R.id.iv_header_back, R.id.rl_refund_reason, R.id.iv_refund_pic, R.id.tv_confirm_refund})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.rl_refund_reason:
                selectReason();
                break;
            case R.id.iv_refund_pic:
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
            case R.id.tv_confirm_refund:
                viewLoading.smoothToShow();
                if (photoList.size() > 0) {
                    uploadImg();
                } else {
                    submitData();
                }


                break;
        }
    }

    /**
     * 选择退款理由
     */
    private void selectReason() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.ppw_refund_reason, null);
        int width = DisplayUtils.getMetrics(this).widthPixels;
        final PopupWindow mPopupWindow = new PopupWindow(contentView, width -
                (int) DisplayUtils.dp2px(this, 30), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0,
                (int) DisplayUtils.dp2px(this, 15));
        DisplayUtils.setBackgroundAlpha(this, true);

        ImageView ivCancel = (ImageView) contentView.findViewById(R.id.iv_cancel_refund);

        final TextView tvConfirm = (TextView) contentView.findViewById(R.id.tv_select_refund);


        RecyclerView rvRefund = (RecyclerView) contentView.findViewById(R.id.rv_refund_content);
        final List<String> reasonList = new ArrayList<>();
        reasonList.add("协商一致退货");
        reasonList.add("缺货");
        reasonList.add("拍错/多拍/不想要了");
        reasonList.add("其他");
        isConfirm = false;

        rvRefund.setLayoutManager(new LinearLayoutManager(this));

        final CommonAdapter<String> adapter = new CommonAdapter<String>(this, R.layout.rv_refund_reason_item, reasonList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_reason_refund, s);
                holder.setVisible(R.id.iv_select_refund, position == reasonPos);
            }
        };

        rvRefund.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                reasonPos = holder.getLayoutPosition();
                refundReason = reasonList.get(reasonPos);
                adapter.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isConfirm = true;
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (isConfirm && reasonPos >= 0) {
                    tvRefundReason.setText(refundReason);
                    tvConfirmRefund.setEnabled(true);
                }
                DisplayUtils.setBackgroundAlpha(ApplyRefundActivity.this, false);
            }
        });
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
        map.put("refundApplyType", refundType);
        map.put("refundCause", refundReason);
        map.put("refundAmount", String.valueOf(money));
        if (!TextUtils.isEmpty(etRefund.getText().toString().trim())) {
            map.put("refundApplyDesc", etRefund.getText().toString().trim());
        }

        if (imgUrl != null) {
            map.put("refundApplyPic", imgUrl);
        }

        OkHttpUtils.post()
                .url(Constant.APPLY_REFUND)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        viewLoading.smoothToHide();
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                        if (responseBean.isFlag()) {
//                            Intent intent = new Intent(ApplyRefundActivity.this,RefundDetailActivity.class);
//                            startActivity(intent);
//                            finish();
                            finish();
                        } else {
                            ToastUtils.showToast(ApplyRefundActivity.this, responseBean.getMsg());
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
