package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.OrderDetailBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/7/26 15:59
 * Email：1993911441@qq.com
 * Describe：活动详情
 */
public class OrderDetailActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_order_detail_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_order_status_info)
    TextView tvStatusInfo;
    @BindView(R.id.tv_order_shop)
    TextView tvOrderShop;
    @BindView(R.id.iv_order)
    SimpleDraweeView ivOrder;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.tv_order_now_price)
    TextView tvNowPrice;
    @BindView(R.id.tv_order_old_price)
    TextView tvOldPrice;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_order_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_order_balance_pay)
    TextView tvBalancePay;
    @BindView(R.id.tv_order_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_order_cash_pay)
    TextView tvCashPay;
    @BindView(R.id.tv_order_total_pay)
    TextView tvTotalPay;
    @BindView(R.id.ll_order_pay)
    LinearLayout llOrderPay;
    @BindView(R.id.tv_order_receive_time)
    TextView tvReceiveTime;
    @BindView(R.id.tv_order_expired_intro)
    TextView tvExpiredIntro;
    @BindView(R.id.tv_order_refund_intro)
    TextView tvRefundIntro;
    @BindView(R.id.tv_order_detail_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_order_detail_type)
    TextView tvOrderType;
    @BindView(R.id.tv_order_detail_submit_time)
    TextView tvSubmitTime;
    @BindView(R.id.tv_order_detail_pay_time)
    TextView tvPayTime;
    @BindView(R.id.rl_order_pay_time)
    RelativeLayout rlPayTime;
    @BindView(R.id.tv_order_detail_cancel)
    TextView tvOrderCancel;
    @BindView(R.id.tv_order_detail_confirm)
    TextView tvOrderConfirm;
    @BindView(R.id.ll_order_detail)
    LinearLayout llOrderDetail;

    private int orderId;
    private OrderDetailBean orderBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("订单详情");
        getData();
        initData();

    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.ORDER_DETAIL)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("memberOrderShopId", String.valueOf(orderId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        orderBean = GsonUtils.jsonToBean(response, OrderDetailBean.class);
                        if (orderBean.isFlag()) {
                            initView();
                        } else {
                            if (orderBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(OrderDetailActivity.this, "is_login");
                            }
                        }
                    }
                });
    }

    private void initView() {
        
    }

    private void getData() {
        orderId = getIntent().getIntExtra("order_id", 0);
    }

    @OnClick({R.id.iv_header_back, R.id.tv_order_detail_cancel, R.id.tv_order_detail_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_order_detail_cancel:
                break;
            case R.id.tv_order_detail_confirm:
                break;
        }
    }
}
