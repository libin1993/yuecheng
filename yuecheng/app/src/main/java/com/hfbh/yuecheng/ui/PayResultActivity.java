package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.utils.DisplayUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Libin on 2018/7/17 14:51
 * Email：1993911441@qq.com
 * Describe：支付结果
 */
public class PayResultActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.iv_pay_result)
    ImageView ivPayResult;
    @BindView(R.id.tv_pay_result)
    TextView tvPayResult;
    @BindView(R.id.tv_order_total)
    TextView tvOrderTotal;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.tv_order_pay_type)
    TextView tvOrderPayType;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_back_homepage)
    TextView tvBackHomepage;
    @BindView(R.id.tv_join_activity)
    TextView tvJoinActivity;
    @BindView(R.id.tv_order_tips)
    TextView tvOrderTips;
    @BindView(R.id.ll_order_type)
    LinearLayout llOrderType;
    @BindView(R.id.rl_order_name)
    RelativeLayout rlOrderName;
    @BindView(R.id.rl_order_no)
    RelativeLayout rlOrderNo;
    @BindView(R.id.rl_order_back)
    RelativeLayout rlOrderBack;
    @BindView(R.id.rl_order_detail)
    RelativeLayout rlOrderDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        tvHeaderTitle.setText("支付结果");
        if (MyApp.orderBean != null) {
            tvOrderPayType.setText(MyApp.orderBean.getPayType());
            tvOrderTotal.setText("¥" + DisplayUtils.decimalFormat(MyApp.orderBean.getMoney()));
            if (MyApp.orderBean.isPayResult()) {
                ivPayResult.setImageResource(R.mipmap.img_success);
                tvPayResult.setText("支付成功");
                tvOrderTips.setVisibility(View.VISIBLE);
                rlOrderDetail.setVisibility(View.VISIBLE);
            } else {
                ivPayResult.setImageResource(R.mipmap.img_failure);
                tvPayResult.setText("支付失败");
                tvOrderTips.setVisibility(View.GONE);
                tvJoinActivity.setVisibility(View.GONE);
                rlOrderDetail.setVisibility(View.GONE);
            }
            switch (MyApp.orderBean.getOrderType()) {
                case "ACTIVITY":
                    tvJoinActivity.setText("查看活动");
                    tvOrderTips.setText("记得去现场参加活动哦！");
                    break;
                case "COMMODITY":
                    rlOrderNo.setVisibility(View.VISIBLE);
                    rlOrderName.setVisibility(View.VISIBLE);
                    llOrderType.setVisibility(View.VISIBLE);
                    tvOrderNo.setText(MyApp.orderBean.getOrderNo());
                    tvOrderName.setText(MyApp.orderBean.getOrderName());
                    tvJoinActivity.setText("查看订单");
                    tvOrderTips.setText("记得到店核销提货哦！");
                    break;
            }
        }
    }

    @OnClick({R.id.iv_header_back, R.id.tv_back_homepage, R.id.tv_join_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_back_homepage:
                startActivity(new Intent(PayResultActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.tv_join_activity:
                switch (MyApp.orderBean.getOrderType()) {
                    case "ACTIVITY":
                        startActivity(new Intent(PayResultActivity.this, MyActionActivity.class));
                        finish();
                        break;
                    case "COMMODITY":

                        break;
                }
                break;
        }
    }
}
