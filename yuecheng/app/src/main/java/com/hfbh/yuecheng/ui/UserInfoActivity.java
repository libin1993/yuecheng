package com.hfbh.yuecheng.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.bean.SearchShopBean;
import com.hfbh.yuecheng.bean.UserInfoBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.AddressPickTask;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.ImageDisposeUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.hfbh.yuecheng.view.PermissionDialog;
import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.utils.LogUtil;
import com.smarttop.library.widget.AddressSelector;
import com.smarttop.library.widget.BottomDialog;
import com.smarttop.library.widget.OnAddressSelectedListener;
import com.soundcloud.android.crop.Crop;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import okhttp3.Call;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Author：Libin on 2018/5/30 09:22
 * Email：1993911441@qq.com
 * Describe：个人信息
 */
public class UserInfoActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.iv_user_avatar)
    SimpleDraweeView ivUserAvatar;
    @BindView(R.id.ll_user_avatar)
    LinearLayout llUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.ll_user_name)
    LinearLayout llUserName;
    @BindView(R.id.tv_user_sex)
    TextView tvUserSex;
    @BindView(R.id.ll_user_sex)
    LinearLayout llUserSex;
    @BindView(R.id.tv_user_birthday)
    TextView tvUserBirthday;
    @BindView(R.id.ll_user_birthday)
    LinearLayout llUserBirthday;
    @BindView(R.id.tv_user_address)
    TextView tvUserAddress;
    @BindView(R.id.ll_user_address)
    LinearLayout llUserAddress;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.ll_user_phone)
    LinearLayout llUserPhone;
    @BindView(R.id.tv_user_wechat)
    TextView tvUserWechat;
    @BindView(R.id.ll_user_wechat)
    LinearLayout llUserWechat;

    private UserInfoBean userInfoBean;

    //生日
    private String userBirthday;
    //省
    private String provinceAddress;
    //市
    private String cityAddress;
    //区
    private String countAddress;
    //性别
    private String sex;
    //头像
    private String avatar;

    private ArrayList<String> selectedPhotos = new ArrayList<>();
    //相机权限
    static final String[] permissionStr = {Manifest.permission.CAMERA};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        tvHeaderTitle.setText("个人信息");
        initData();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.USER_INFO)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        userInfoBean = GsonUtils.jsonToBean(response, UserInfoBean.class);
                        if (userInfoBean.isFlag()) {
                            initView();
                        }
                    }
                });

    }

    /**
     * 用户信息
     */
    private void initView() {
        tvUserName.setText(userInfoBean.getData().getMemberNickname());
        if (!TextUtils.isEmpty(userInfoBean.getData().getMemberHead())) {
            ivUserAvatar.setImageURI(userInfoBean.getData().getMemberHead());
        }
        provinceAddress = userInfoBean.getData().getMemberProvince();
        cityAddress = userInfoBean.getData().getMemberCity();
        countAddress = userInfoBean.getData().getMemberCountry();
        if (!TextUtils.isEmpty(provinceAddress)){
            tvUserAddress.setText(provinceAddress + " " + cityAddress + " " + countAddress);
        }


        String memberSex = userInfoBean.getData().getMemberSex();
        if (!TextUtils.isEmpty(memberSex)) {
            switch (memberSex) {
                case "MAN":
                    tvUserSex.setText("男");
                    break;
                case "WOMAN":
                    tvUserSex.setText("女");
                    break;
                case "UNKNOW":
                    tvUserSex.setText("保密");
                    break;
            }
        }

        userBirthday = userInfoBean.getData().getMemberBirthday();
        tvUserBirthday.setText(userBirthday);
        tvUserPhone.setText(userInfoBean.getData().getMemberPhone());

    }


    @OnClick({R.id.iv_header_back, R.id.ll_user_avatar, R.id.ll_user_name, R.id.ll_user_sex,
            R.id.ll_user_birthday, R.id.ll_user_address, R.id.ll_user_phone, R.id.ll_user_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.ll_user_avatar:
                updateAvatar();
                break;
            case R.id.ll_user_name:
                Intent intent = new Intent(this, UpdateNameActivity.class);
                intent.putExtra("username",userInfoBean.getData().getMemberNickname());
                startActivity(intent);
                break;
            case R.id.ll_user_sex:
                updateSex();
                break;
            case R.id.ll_user_birthday:
                updateBirthday();
                break;
            case R.id.ll_user_address:
                updateAddress();
                break;
            case R.id.ll_user_phone:
                break;
            case R.id.ll_user_wechat:
                break;
        }
    }

    /**
     * 选择地址
     */
    private void updateAddress() {
//        AddressPickTask task = new AddressPickTask(this);
//        task.setCallback(new AddressPickTask.Callback() {
//            @Override
//            public void onAddressInitFailed() {
//
//            }
//
//            @Override
//            public void onAddressPicked(Province province, City city, County county) {
//                provinceAddress = province.getAreaName();
//                cityAddress = city.getAreaName();
//                countAddress = county.getAreaName();
//
//                Map<String, String> map = new HashMap<>();
//
//                map.put("memberProvince", provinceAddress);
//                map.put("memberCity", cityAddress);
//                map.put("memberCountry", countAddress);
//                updateUserInfo("address", map);
//
//            }
//        });
//
//
//        if (!TextUtils.isEmpty(provinceAddress)) {
//            task.execute(provinceAddress, cityAddress, countAddress);
//        } else {
//            task.execute("北京市", "北京市", "朝阳区");
//        }

        final BottomDialog dialog = new BottomDialog(this);
        dialog.setDialogDismisListener(new AddressSelector.OnDialogCloseListener() {
            @Override
            public void dialogclose() {
                dialog.dismiss();
            }
        });
        dialog.setOnAddressSelectedListener(new OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(Province province, City city, County county) {
                provinceAddress = province.name;
                cityAddress = city.name;
                countAddress = county.name;

                Map<String, String> map = new HashMap<>();

                map.put("memberProvince", provinceAddress);
                map.put("memberCity", cityAddress);
                map.put("memberCountry", countAddress);
                updateUserInfo("address", map);
            }
        });
        dialog.show();

    }

    /**
     * 修改性别
     */
    private void updateSex() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.ppw_selelct_sex, null);
        final PopupWindow mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        DisplayUtils.setBackgroundAlpha(this, true);

        TextView tvMan = (TextView) contentView.findViewById(R.id.tv_user_man);
        TextView tvWoman = (TextView) contentView.findViewById(R.id.tv_user_woman);
        TextView tvSecrecy = (TextView) contentView.findViewById(R.id.tv_user_secrecy);


        tvMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = "男";

                Map<String, String> map = new HashMap<>();
                map.put("memberSex", "MAN");
                updateUserInfo("sex", map);

                mPopupWindow.dismiss();
            }
        });

        tvWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = "女";

                Map<String, String> map = new HashMap<>();
                map.put("memberSex", "WOMAN");
                updateUserInfo("sex", map);

                mPopupWindow.dismiss();
            }
        });

        tvSecrecy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex = "保密";

                Map<String, String> map = new HashMap<>();
                map.put("memberSex", "UNKNOW");
                updateUserInfo("sex", map);

                mPopupWindow.dismiss();
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                DisplayUtils.setBackgroundAlpha(UserInfoActivity.this, false);
            }
        });
    }

    /**
     * 修改头像
     */
    private void updateAvatar() {
        if (!EasyPermissions.hasPermissions(this, permissionStr)) {
            EasyPermissions.requestPermissions(this, "", 123, permissionStr);
        } else {
            PhotoPicker.builder()
                    .setPhotoCount(1)
                    .setShowCamera(true)
                    .start(this);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE ||
                requestCode == PhotoPreview.REQUEST_CODE)) {
            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();
            if (photos != null) {

                selectedPhotos.addAll(photos);
                if (selectedPhotos.size() != 0) {
                    Uri destination = Uri.fromFile(new File(Environment.getExternalStorageDirectory()
                            .getAbsolutePath() + File.separator + "yuecheng", "avatar.png"));
                    Crop.of(Uri.fromFile(new File(selectedPhotos.get(0))), destination).asSquare().start(this);
                }
            }
        } else if (requestCode == Crop.REQUEST_CROP) {
            try {
                if (data != null) {
                    ImageDisposeUtils.rotatingImageView(Crop.getOutput(data).getPath());
                    ivUserAvatar.setImageURI(Crop.getOutput(data));
                    File file = new File(Crop.getOutput(data).getPath());

                    uploadImg(file);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 图片上传oss
     */
    private void uploadImg(File file) {
        OkHttpUtils.post()
                .url(Constant.UPLOAD_FILE)
                .addFile("contentType", file.getName(), file)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                        if (responseBean.isFlag() && !TextUtils.isEmpty(responseBean.getData())) {
                            Map<String, String> map = new HashMap<>();

                            map.put("memberHead", responseBean.getData());
                            avatar = responseBean.getData();
                            updateUserInfo("avatar", map);
                        } else {
                            ToastUtils.showToast(UserInfoActivity.this, "修改失败");
                        }

                    }
                });
    }

    /**
     * 修改生日
     */
    private void updateBirthday() {
        DatePicker picker = new DatePicker(this);
        picker.setSubmitTextColor(getResources().getColor(R.color.red_99));
        picker.setOffset(3);
        picker.setTopLineColor(getResources().getColor(R.color.gray_ed));
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        picker.setRangeStart(1900, 1, 1);
        picker.setRangeEnd(year, month, day);
        if (!TextUtils.isEmpty(userBirthday)) {
            String[] split = userBirthday.split("-");
            picker.setSelectedItem(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
                    Integer.parseInt(split[2]));
        } else {
            picker.setSelectedItem(1980, 1, 1);
        }

        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                userBirthday = year + "-" + month + "-" + day;
                Map<String, String> map = new HashMap<>();
                map.put("memberBirthday", userBirthday);
                updateUserInfo("birthday", map);
            }
        });
        picker.show();
    }


    /**
     * 修改用户信息
     */
    private void updateUserInfo(final String key, Map<String, String> map) {
        map.put("appType", MyApp.appType);
        map.put("appVersion", MyApp.appVersion);
        map.put("organizeId", MyApp.organizeId);
        map.put("hash", SharedPreUtils.getStr(this, "hash"));
        map.put("memberId", SharedPreUtils.getStr(this, "member_id"));
        OkHttpUtils.post()
                .url(Constant.UPDATE_USER_INFO)
                .params(map)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                if (responseBean.isFlag()) {
                    switch (key) {
                        case "avatar":
                            ivUserAvatar.setImageURI(avatar);
                            break;
                        case "sex":
                            tvUserSex.setText(sex);
                            break;
                        case "birthday":
                            tvUserBirthday.setText(userBirthday);
                            break;
                        case "address":
                            tvUserAddress.setText(provinceAddress + " " + cityAddress + " " + countAddress);
                            break;
                    }
                    ToastUtils.showToast(UserInfoActivity.this, "修改成功");
                } else {
                    ToastUtils.showToast(UserInfoActivity.this, "修改失败");
                }

            }
        });
    }


    /**
     * @param msg 修改昵称回调
     */
    @Subscribe
    public void updateName(String msg) {
        tvUserName.setText(msg);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
