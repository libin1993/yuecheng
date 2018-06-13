package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.MyFragmentAdapter;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.UserInfoBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.fragment.CouponFragment;
import com.hfbh.yuecheng.fragment.ExchangeFragment;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.smarttop.library.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/8 18:24
 * Email：1993911441@qq.com
 * Describe：我的兑换
 */
public class MyExchangeActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tab_my_activity)
    SlidingTabLayout tabGift;
    @BindView(R.id.vp_my_activity)
    ViewPager vpGift;


    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);
        ButterKnife.bind(this);
        initTitle();
    }


    /**
     * tab分类
     */
    private void initTitle() {
        tvHeaderTitle.setText("我的兑换");
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList.add("待核销");
        titleList.add("全部兑换");
        fragmentList.add(ExchangeFragment.newInstance("UNUSE"));
        fragmentList.add(ExchangeFragment.newInstance(""));

        initView();
    }


    /**
     * 绑定viewpager
     */
    private void initView() {

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),
                fragmentList, titleList);
        vpGift.setOffscreenPageLimit(titleList.size());
        vpGift.setAdapter(adapter);
        tabGift.setViewPager(vpGift);
        tabGift.setCurrentTab(0);
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
