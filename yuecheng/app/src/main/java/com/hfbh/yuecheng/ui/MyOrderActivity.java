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
import com.hfbh.yuecheng.fragment.OrderFragment;
import com.hfbh.yuecheng.fragment.RefundOrderFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Libin on 2018/7/26 09:28
 * Email：1993911441@qq.com
 * Describe：我的订单
 */
public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tab_my_activity)
    SlidingTabLayout tabOrder;
    @BindView(R.id.vp_my_activity)
    ViewPager vpOrder;

    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("我的订单");
        initTitle();
    }

    /**
     * tab分类
     */
    private void initTitle() {

        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList.add("全部");
        titleList.add("待付款");
        titleList.add("待提货");
        titleList.add("退款单");
        fragmentList.add(OrderFragment.newInstance(""));
        fragmentList.add(OrderFragment.newInstance("UNPAID"));
        fragmentList.add(OrderFragment.newInstance("PAID"));
        fragmentList.add(RefundOrderFragment.newInstance());
        initView();
    }


    /**
     * 绑定viewpager
     */
    private void initView() {

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),
                fragmentList, titleList);
        vpOrder.setOffscreenPageLimit(titleList.size());
        vpOrder.setAdapter(adapter);
        tabOrder.setViewPager(vpOrder);
        tabOrder.setCurrentTab(0);
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * @param msg 支付回调
     */
    @Subscribe
    public void payOrder(String msg) {
        if ("pay_order".equals(msg)) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
