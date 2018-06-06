package com.hfbh.yuecheng.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.LocationBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.fragment.ActivityFragment;
import com.hfbh.yuecheng.fragment.DiscoveryFragment;
import com.hfbh.yuecheng.fragment.HomepageFragment;
import com.hfbh.yuecheng.fragment.MineFragment;
import com.hfbh.yuecheng.utils.AppManagerUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.FragmentTabUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LocationUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.hfbh.yuecheng.view.PermissionDialog;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * Author：Libin on 2017/05/14 10:35
 * Email：1993911441@qq.com
 * Describe：首页
 */
public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.rgs_main_tab)
    RadioGroup rgsMainTab;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;


    //退出应用
    private long exitTime = 0;
    //读写权限，相机权限
    String[] permissionStr = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    //经度
    private String longitude = "";
    //纬度
    private String latitude = "";
    private FragmentTabUtils fragmentTabUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        getData();
    }

    /**
     * 首次进入或者切换商城
     */
    private void getData() {
        Intent intent = getIntent();
        boolean isChangeCity = intent.getBooleanExtra("change_market", false);
        if (isChangeCity) {
            initView();
        } else {
            isLogin();
        }
    }

    /**
     * 请求权限
     */
    private void requestPermission() {
        if (!EasyPermissions.hasPermissions(this, permissionStr)) {
            EasyPermissions.requestPermissions(this, "", 1234, permissionStr);
        } else {
            getLocation();
        }
    }

    /**
     * 获取经纬度
     */
    private void getLocation() {
        Location location = LocationUtils.getInstance(MainActivity.this).showLocation();
        if (location != null) {
            longitude = String.valueOf(location.getLongitude());
            latitude = String.valueOf(location.getLatitude());
            initLocation();
        }
    }

    /**
     * 检测是否登录
     */
    private void isLogin() {
        loadingView.smoothToShow();
        if (SharedPreUtils.getBoolean(this, "is_login", false)) {
            OkHttpUtils.post()
                    .url(Constant.IS_LOGIN)
                    .addParams("appType", MyApp.appType)
                    .addParams("appVersion", MyApp.appVersion)
                    .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int i) {

                        }

                        @Override
                        public void onResponse(String s, int i) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                boolean isLogin = jsonObject.getBoolean("data");
                                if (!isLogin) {
                                    SharedPreUtils.deleteStr(MainActivity.this, "is_login");
                                }
                                requestPermission();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } else {
            requestPermission();
        }

    }

    /**
     * 获取商场id ,hash值
     */
    private void initLocation() {

        OkHttpUtils.post()
                .url(Constant.LOCATION)
                .addParams("lng", longitude)
                .addParams("lat", latitude)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        loadingView.smoothToHide();
                        LocationBean locationBean = GsonUtils.jsonToBean(s, LocationBean.class);
                        if (locationBean.isFlag()) {
//                            MyApp.organizeId = String.valueOf(locationBean.getData().getOrganizeId());
                            MyApp.organizeName = locationBean.getData().getOrganizeName();
                            boolean isLogin = SharedPreUtils.getBoolean(MainActivity.this, "is_login", false);
                            //是否已经登录
                            if (!isLogin) {
                                SharedPreUtils.saveStr(MainActivity.this, "hash", locationBean.getHash());
                            }

                            initView();
                        }
                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {
        initIcon();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HomepageFragment.newInstance());
        fragmentList.add(ActivityFragment.newInstance());
        fragmentList.add(DiscoveryFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());
        fragmentTabUtils = new FragmentTabUtils(this, getSupportFragmentManager(), fragmentList,
                R.id.fl_main_container, rgsMainTab);
    }

    /**
     * 初始化icon
     */
    private void initIcon() {
        int width = (int) DisplayUtils.dp2px(this, 22);
        int height = (int) DisplayUtils.dp2px(this, 20);
        Drawable drawableHome = getResources().getDrawable(R.drawable.selector_homepage_tab);
        drawableHome.setBounds(0, 0, width, height);
        ((RadioButton) rgsMainTab.getChildAt(0)).setCompoundDrawables(null, drawableHome, null, null);

        Drawable drawableActivity = getResources().getDrawable(R.drawable.selector_activity_tab);
        drawableActivity.setBounds(0, 0, width, height);
        ((RadioButton) rgsMainTab.getChildAt(1)).setCompoundDrawables(null, drawableActivity, null, null);

        Drawable drawableDiscovery = getResources().getDrawable(R.drawable.selector_discovery_tab);
        drawableDiscovery.setBounds(0, 0, width, height);
        ((RadioButton) rgsMainTab.getChildAt(2)).setCompoundDrawables(null, drawableDiscovery, null, null);

        Drawable drawableMine = getResources().getDrawable(R.drawable.selector_mine_tab);
        drawableMine.setBounds(0, 0, width, height);
        ((RadioButton) rgsMainTab.getChildAt(3)).setCompoundDrawables(null, drawableMine, null, null);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        getLocation();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == 1234) {
            PermissionDialog.showPermissionDialog(this, "读写内存和定位");
            initLocation();
        }

    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showToast(getApplicationContext(), "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                AppManagerUtils.getInstance().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * @param msg 点击跳转活动页面
     */
    @Subscribe
    public void toActivity(String msg) {
        if ("activity".equals(msg)) {
            fragmentTabUtils.setCurrentFragment(1);
        }
    }
}
