package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Libin on 2018/6/14 12:22
 * Email：1993911441@qq.com
 * Describe：优惠券使用说明
 */
public class CouponIntroActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_coupon_range)
    TextView tvCouponRange;
    @BindView(R.id.tv_coupon_intro)
    TextView tvCouponIntro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_introduce);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String useRange = intent.getStringExtra("coupon_range");
        String intro = intent.getStringExtra("coupon_intro");

        tvHeaderTitle.setText("使用说明");
        tvCouponRange.setText(useRange);
        tvCouponIntro.setText(intro);
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
