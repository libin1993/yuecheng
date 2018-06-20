package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.MyFragmentAdapter;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.MyCouponBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.fragment.CouponFragment;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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
    @BindView(R.id.view_header_line)
    View viewHeaderLine;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;


    private List<String> titleList;
    private List<Fragment> fragmentList;
    private MyCouponBean couponBean;
    private List<MyCouponBean.DataBean> usableList = new ArrayList<>();
    private List<MyCouponBean.DataBean> disableList = new ArrayList<>();
    private List<MyCouponBean.DataBean> overdueList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("我的票券");
        initData();
    }

    private void initData() {
        viewLoading.smoothToShow();
        OkHttpUtils.post()
                .url(Constant.MY_COUPON)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token",SharedPreUtils.getStr(this, "token"))
                .addParams("cardNumber", SharedPreUtils.getStr(this, "card_number"))
                .addParams("couponTypeKind", "VOUCHER")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        viewLoading.smoothToHide();
                        couponBean = GsonUtils.jsonToBean(response, MyCouponBean.class);
                        if (couponBean.isFlag() && couponBean.getData() != null
                                && couponBean.getData().size() > 0) {
                            initTitle();
                        }
                    }
                });
    }

    /**
     * tab分类
     */
    private void initTitle() {

        for (int i = 0; i < couponBean.getData().size(); i++) {
            if (System.currentTimeMillis() > DateUtils.getTime("yyyy-MM-dd", couponBean.getData().get(i).getValidDate())) {
                overdueList.add(couponBean.getData().get(i));
            } else {
                if (couponBean.getData().get(i).getBalance() > 0) {
                    usableList.add(couponBean.getData().get(i));
                } else {
                    disableList.add(couponBean.getData().get(i));
                }
            }
        }

        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList.add("未使用");
        titleList.add("已使用");
        titleList.add("已过期");
        fragmentList.add(CouponFragment.newInstance(1, usableList));
        fragmentList.add(CouponFragment.newInstance(2, disableList));
        fragmentList.add(CouponFragment.newInstance(3, overdueList));

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
