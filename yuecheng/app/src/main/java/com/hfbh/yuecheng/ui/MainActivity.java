package com.hfbh.yuecheng.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
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
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.FragmentTabUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;


/**
 * Author：Libin on 2017/05/14 10:35
 * Email：1993911441@qq.com
 * Describe：首页
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.rgs_main_tab)
    RadioGroup rgsMainTab;

    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentTabUtils fragmentUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.LOCATION)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        LocationBean locationBean = GsonUtils.jsonToBean(s, LocationBean.class);
                        if (locationBean.isFlag()) {
                            MyApp.organizeId = String.valueOf(locationBean.getData().getOrganizeId());
                            String hash = locationBean.getHash();
                            SharedPreUtils.saveStr(MainActivity.this, "hash", hash);
                        }
                    }
                });

    }

    /**
     * 加载视图
     */
    private void initView() {

        fragmentList.add(HomepageFragment.newInstance());
        fragmentList.add(ActivityFragment.newInstance());
        fragmentList.add(DiscoveryFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());
        fragmentUtils = new FragmentTabUtils(this, getSupportFragmentManager(), fragmentList,
                R.id.fl_main_container, rgsMainTab);
    }
}
