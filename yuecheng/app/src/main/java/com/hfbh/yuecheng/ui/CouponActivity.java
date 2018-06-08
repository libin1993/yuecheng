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
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.fragment.CouponFragment;
import com.hfbh.yuecheng.fragment.MyActivityFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Libin on 2018/6/7 17:24
 * Email：1993911441@qq.com
 * Describe：优惠券
 */
public class CouponActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tab_my_activity)
    SlidingTabLayout tabCoupon;
    @BindView(R.id.vp_my_activity)
    ViewPager vpCoupon;


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
        tvHeaderTitle.setText("我的票券");
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList.add("未使用");
        titleList.add("已使用");
        titleList.add("已过期");
        fragmentList.add(CouponFragment.newInstance(0));
        fragmentList.add(CouponFragment.newInstance(1));
        fragmentList.add(CouponFragment.newInstance(0));

        initView();
    }


    /**
     * 绑定viewpager
     */
    private void initView() {

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),
                fragmentList, titleList);
        vpCoupon.setOffscreenPageLimit(titleList.size());
        vpCoupon.setAdapter(adapter);
        tabCoupon.setViewPager(vpCoupon);
        tabCoupon.setCurrentTab(0);
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
