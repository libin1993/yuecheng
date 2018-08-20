package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.PayOrderBean;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

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
    @BindView(R.id.tv_back_homepage)
    TextView tvBackHomepage;
    @BindView(R.id.tv_join_activity)
    TextView tvJoinActivity;
    @BindView(R.id.tv_order_tips)
    TextView tvOrderTips;
    @BindView(R.id.ll_order_type)
    LinearLayout llOrderType;
    @BindView(R.id.rl_order_back)
    RelativeLayout rlOrderBack;
    @BindView(R.id.rl_order_detail)
    RelativeLayout rlOrderDetail;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.rv_result_discount)
    RecyclerView rvDiscount;
    @BindView(R.id.view_order_status)
    View viewStatus;
    @BindView(R.id.rv_result_order)
    RecyclerView rvOrder;
    @BindView(R.id.ll_order_status)
    LinearLayout llOrderStatus;
    @BindView(R.id.tv_order_goods_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_order_goods_type)
    TextView tvOrderType;

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
            tvOrderTotal.setText("¥" + DisplayUtils.decimalFormat(MyApp.orderBean.getOrderPrice()));
            if (MyApp.orderBean.isPayResult()) {
                ivPayResult.setImageResource(R.mipmap.img_success);
                tvPayResult.setText("支付成功");
            } else {
                ivPayResult.setImageResource(R.mipmap.img_failure);
                tvPayResult.setText("支付失败");
            }

            switch (MyApp.orderBean.getOrderType()) {
                case "ACTIVITY":
                    initOrder();
                    llOrderStatus.setVisibility(View.VISIBLE);
                    if (MyApp.orderBean.isPayResult()) {
                        rlOrderDetail.setVisibility(View.VISIBLE);
                        tvOrderTips.setVisibility(View.VISIBLE);
                    }
                    tvJoinActivity.setText("查看活动");
                    tvOrderTips.setText("记得去现场参加活动哦！");
                    break;
                case "COMMODITY":
                    initOrder();
                    llOrderStatus.setVisibility(View.VISIBLE);
                    llOrderType.setVisibility(View.VISIBLE);

                    tvOrderTime.setText(MyApp.orderBean.getLimitTime() + "天提货有效");
                    if ("GROUPON".equals(MyApp.orderBean.getGoodsType())) {
                        tvOrderType.setText("失效未提货审核退款");
                    } else {
                        tvOrderType.setText("失效未提货自动退款");
                    }


                    if (MyApp.orderBean.isPayResult()) {
                        rlOrderDetail.setVisibility(View.VISIBLE);
                        tvOrderTips.setVisibility(View.VISIBLE);
                    }

                    tvJoinActivity.setText("查看订单");
                    tvOrderTips.setText("记得到店核销提货哦！");
                    break;
                case "SCANCODE":
                    if (MyApp.orderBean.isPayResult()) {
                        rvDiscount.setVisibility(View.VISIBLE);
                        viewStatus.setVisibility(View.VISIBLE);
                        rlOrderDetail.setVisibility(View.VISIBLE);
                        initDiscount();
                    } else {
                        tvOrderStatus.setVisibility(View.VISIBLE);
                    }
                    initOrder();
                    break;
                case "PARKING ":

                    break;
            }
        }
    }

    private void initOrder() {
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<PayOrderBean.OrderInfo> adapter = new CommonAdapter<PayOrderBean.OrderInfo>(
                this, R.layout.rv_pay_result_item, MyApp.orderBean.getOrderList()) {
            @Override
            protected void convert(ViewHolder holder, PayOrderBean.OrderInfo orderInfo, int position) {
                holder.setText(R.id.tv_order_result_title, orderInfo.getTitle());
                holder.setText(R.id.tv_order_result_content, orderInfo.getContent());
            }
        };
        rvOrder.setAdapter(adapter);
    }


    private void initDiscount() {
        rvDiscount.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<PayOrderBean.DiscountBean> adapter = new CommonAdapter<PayOrderBean.DiscountBean>(
                this, R.layout.rv_pay_result_item, MyApp.orderBean.getDiscountList()) {
            @Override
            protected void convert(ViewHolder holder, PayOrderBean.DiscountBean discountBean, int position) {
                holder.setText(R.id.tv_order_result_title, discountBean.getTitle());
                holder.setText(R.id.tv_order_result_content, discountBean.getContent());
            }

        };
        rvDiscount.setAdapter(adapter);
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
                        startActivity(new Intent(PayResultActivity.this, MyOrderActivity.class));
                        finish();
                        break;
                }
                break;
        }
    }
}
