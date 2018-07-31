package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.OrderDetailBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.QRCodeUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

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
    TextView tvCancel;
    @BindView(R.id.tv_order_detail_confirm)
    TextView tvConfirm;
    @BindView(R.id.ll_order_detail)
    LinearLayout llOrderDetail;
    @BindView(R.id.rl_order_goods)
    RelativeLayout rlOrderGoods;

    private int orderId;
    private OrderDetailBean orderBean;
    private CountDownTimer countDownTimer;
    private int type;
    private Bitmap qrBmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("订单详情");
        getData();

    }

    @Override
    protected void onResume() {
        super.onResume();
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

        switch (orderBean.getData().getState()) {
            case "UNPAID":
                long currentTime = System.currentTimeMillis();
                long orderTime = DateUtils.getTime("yyyy-MM-dd HH:mm:ss", orderBean.getData().getSumbitTime());

                if (currentTime - orderTime < 15 * 60 * 1000) {
                    countDownTimer = new CountDownTimer(15 * 60 * 1000 + orderTime - currentTime, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            long minute = millisUntilFinished / (1000 * 60);
                            long second = millisUntilFinished % (1000 * 60) / 1000;
                            tvOrderStatus.setText("待支付");
                            tvStatusInfo.setText("订单已提交,请在" + minute + ":" + second + "内完成支付，超时自动取消");


                            llOrderDetail.setVisibility(View.VISIBLE);


                            tvCancel.setVisibility(View.VISIBLE);
                            tvCancel.setText("取消订单");
                            tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                            tvConfirm.setVisibility(View.VISIBLE);
                            tvConfirm.setText("去支付");
                            tvConfirm.setBackgroundResource(R.drawable.bound_red_16dp);

                            type = 1;
                        }

                        @Override
                        public void onFinish() {
                            tvOrderStatus.setText("已取消");
                            tvStatusInfo.setText("订单已取消～");

                            llOrderDetail.setVisibility(View.GONE);
                        }
                    }.start();
                }

                isPaid(false);
                break;
            case "PAID":
                tvOrderStatus.setText("待提货");

                if (("GROUPON").equals(orderBean.getData().getOrderType())) {
                    tvStatusInfo.setText("拼团中，活动结束拼团不成功自动退款");
                    llOrderDetail.setVisibility(View.GONE);
                } else {
                    tvStatusInfo.setText("请在" + orderBean.getData().getOrderDtlList().get(0)
                            .getGetTimeLimit() + "天内到店提货哟！失效未提货自动退款");

                    llOrderDetail.setVisibility(View.VISIBLE);

                    tvCancel.setVisibility(View.VISIBLE);
                    tvCancel.setText("去退款");
                    tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                    tvConfirm.setVisibility(View.VISIBLE);
                    tvConfirm.setText("去提货");
                    tvConfirm.setBackgroundResource(R.drawable.bound_red_16dp);

                    type = 2;
                }

                isPaid(true);

                break;
            case "SINGIN":
                tvOrderStatus.setText("已完成");
                switch (orderBean.getData().getOrderType()) {
                    case "GROUPON":
                        tvStatusInfo.setText("团购成功，您已成功提货");
                        break;
                    case "SECKILL":
                        tvStatusInfo.setText("秒杀成功，您已成功提货");
                        break;
                    case "SPECIAL":
                        tvStatusInfo.setText("购买成功，您已成功提货");
                        break;
                }

                if ("Y".equals(orderBean.getData().getOrderDtlList().get(0).getIsRefund())) {
                    llOrderDetail.setVisibility(View.VISIBLE);

                    tvCancel.setVisibility(View.VISIBLE);
                    tvCancel.setText("退货退款");
                    tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                    tvConfirm.setVisibility(View.GONE);

                    type = 3;
                } else {
                    llOrderDetail.setVisibility(View.GONE);
                }

                isPaid(true);
                break;
            case "GROUP_SUCCESS":
                tvOrderStatus.setText("待提货");

                if (("GROUPON").equals(orderBean.getData().getOrderType())) {
                    tvStatusInfo.setText("拼团成功，请在活动结束后" + orderBean.getData()
                            .getOrderDtlList().get(0).getGetTimeLimit() + "天内到店提货哟！");
                }

                isPaid(true);
                llOrderDetail.setVisibility(View.VISIBLE);

                tvCancel.setVisibility(View.VISIBLE);
                tvCancel.setText("去退款");
                tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                tvConfirm.setVisibility(View.VISIBLE);
                tvConfirm.setText("去提货");
                tvConfirm.setBackgroundResource(R.drawable.bound_red_16dp);

                type = 4;
                break;
            case "CLOSE":
                switch (orderBean.getData().getCloseType()) {
                    case "AUTO":
                    case "HAND":
                        tvOrderStatus.setText("已取消");
                        tvStatusInfo.setText("订单已取消～");
                        isPaid(false);
                        llOrderDetail.setVisibility(View.GONE);
                        break;
                    case "AUTO_REFUNDED":
                        tvOrderStatus.setText("已关闭");
                        if (("GROUPON").equals(orderBean.getData().getOrderType())
                                && "GROUP_FAIL".equals(orderBean.getData().getGroupState())) {
                            tvStatusInfo.setText("拼团失败，自动退款成功");
                        } else {
                            tvStatusInfo.setText("未提货且已失效，自动退款成功");
                        }

                        isPaid(true);
                        llOrderDetail.setVisibility(View.GONE);
                        break;
                    case "HAND_REFUNDED":
                        tvOrderStatus.setText("已关闭");
                        if (("GROUPON").equals(orderBean.getData().getOrderType())
                                && "GROUP_CLOSE".equals(orderBean.getData().getGroupState())) {
                            if ("SIGNIN".equals(orderBean.getData().getOrderDtlList().get(0).getVerifyState())) {
                                tvStatusInfo.setText("拼团成功已完成提货，申请退货退款成功");
                            } else {
                                tvStatusInfo.setText("拼团成功未提货且已失效，申请退款成功");
//                                if () {
//                                    tvStatusInfo.setText("拼团成功未提货且已失效，申请退款成功");
//                                } else {
//                                    tvStatusInfo.setText("拼团成功未提货，申请退款成功");
//                                }

                            }

                        } else {
                            if ("SIGNIN".equals(orderBean.getData().getOrderDtlList().get(0).getVerifyState())) {
                                tvStatusInfo.setText("已完成提货，申请退货退款成功");
                            } else {
                                tvStatusInfo.setText("未提货，申请退款成功");
                            }
                        }

                        isPaid(true);
                        llOrderDetail.setVisibility(View.GONE);
                        break;
                    case "UNSIGNIN":
                        tvOrderStatus.setText("已失效");
                        if (("GROUPON").equals(orderBean.getData().getOrderType())) {
                            tvStatusInfo.setText("团购成功，您未在有效时间内提货，可申请退款~");
                        }

                        isPaid(true);
                        llOrderDetail.setVisibility(View.VISIBLE);
                        tvCancel.setVisibility(View.VISIBLE);
                        tvCancel.setText("去退款");
                        tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                        tvConfirm.setVisibility(View.GONE);
                        type = 3;
                        break;
                }

                break;
            case "EXPIRED":
                tvOrderStatus.setText("已失效");
                if (("GROUPON").equals(orderBean.getData().getOrderType())) {
                    tvStatusInfo.setText("团购成功，您未在有效时间内提货，可申请退款~");
                }
                isPaid(true);
                llOrderDetail.setVisibility(View.VISIBLE);
                tvCancel.setVisibility(View.VISIBLE);
                tvCancel.setText("去退款");
                tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                tvConfirm.setVisibility(View.GONE);
                break;
        }

        tvOrderShop.setText(orderBean.getData().getRelationName());
        ivOrder.setImageURI(orderBean.getData().getOrderDtlList().get(0).getDetailPicturepath());
        tvOrderName.setText(orderBean.getData().getOrderDtlList().get(0).getDetailName());


        tvNowPrice.setText("¥" + DisplayUtils.isInteger(orderBean.getData().getOrderDtlList().get(0).getDetailPrice()));

        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        tvOldPrice.setText("¥" + DisplayUtils.isInteger(orderBean.getData().getOrderDtlList().get(0).getOriginalPrice()));
        tvOrderNum.setText("x" + orderBean.getData().getOrderDtlList().get(0).getDetailAccount());
        tvTotalPrice.setText("¥" + DisplayUtils.isInteger(orderBean.getData().getPrice()));

        if (("GROUPON").equals(orderBean.getData().getOrderType())) {
            tvReceiveTime.setText("活动结束后" + orderBean.getData().getOrderDtlList().get(0).getGetTimeLimit() + "天提货有效");
            tvExpiredIntro.setText("失效未提货申请退款");
        } else {
            tvReceiveTime.setText("支付成功后" + orderBean.getData().getOrderDtlList().get(0).getGetTimeLimit() + "天提货有效");
            tvExpiredIntro.setText("失效未提货自动退款");
        }

        if ("Y".equals(orderBean.getData().getOrderDtlList().get(0).getIsRefund())) {
            tvRefundIntro.setText("提货后" + orderBean.getData().getOrderDtlList().get(0).getRefundTimeLimit() + "天后可退货退款");
        } else {
            tvRefundIntro.setText("不支持退款");
        }

        tvOrderNo.setText(orderBean.getData().getOrderShopNumber());
        switch (orderBean.getData().getOrderType()) {
            case "GROUPON":
                tvOrderType.setText("团购");
                break;
            case "SPECIAL":
                tvOrderType.setText("单品");
                break;
            case "SECKILL":
                tvOrderType.setText("秒杀");
                break;
        }
        tvSubmitTime.setText(orderBean.getData().getSumbitTime());

    }

    /**
     * 是否已支付
     */
    private void isPaid(boolean flag) {
        if (flag) {
            llOrderPay.setVisibility(View.VISIBLE);
            tvBalancePay.setText("¥" + DisplayUtils.decimalFormat(orderBean.getData().getUseAccountBalance()));
            tvCashPay.setText("¥" + DisplayUtils.decimalFormat(orderBean.getData().getWechatPay()));
            tvTotalPay.setText("¥" + DisplayUtils.decimalFormat(orderBean.getData().getPrice()));

            rlPayTime.setVisibility(View.VISIBLE);
            tvPayTime.setText(orderBean.getData().getPayTime());
        } else {
            llOrderPay.setVisibility(View.GONE);
            rlPayTime.setVisibility(View.GONE);
        }

    }

    private void getData() {
        orderId = getIntent().getIntExtra("order_id", 0);
    }

    @OnClick({R.id.iv_header_back, R.id.tv_order_detail_cancel, R.id.tv_order_detail_confirm, R.id.rl_order_goods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_order_detail_cancel:
                switch (type) {
                    case 1:
                        cancelOrder();
                        break;
                    case 2:
                    case 3:
                    case 4:
                        refundOrder(orderBean.getData().getOrderDtlList().get(0).getMemberOrderDetailId(),
                                "SIGNIN".equals(orderBean.getData().getOrderDtlList().get(0).getVerifyState())
                                        ? "RETURN" : "REFUND", orderBean.getData().getOrderDtlList().get(0).getDetailPrice());
                        break;
                }
                break;
            case R.id.tv_order_detail_confirm:
                switch (type) {
                    case 1:
                        payMoney();
                        break;
                    case 2:
                    case 4:
                        receiveGoods();
                        break;
                }
                break;
            case R.id.rl_order_goods:
                Intent intent = null;
                switch (orderBean.getData().getOrderType()) {

                    case "GROUPON":
                        intent = new Intent(OrderDetailActivity.this, GroupGoodsActivity.class);
                        break;
                    case "SPECIAL":
                        intent = new Intent(OrderDetailActivity.this, PopGoodsDetailActivity.class);
                        break;
                    case "SECKILL":
                        intent = new Intent(OrderDetailActivity.this, RushGoodsDetailActivity.class);
                        break;
                }
                intent.putExtra("goods_id", orderBean.getData().getOrderDtlList().get(0).getDetailId());
                startActivity(intent);
                break;
        }
    }

    /**
     * 提货
     */
    private void receiveGoods() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.ppw_receive_goods, null);
        int width = DisplayUtils.getMetrics(this).widthPixels;
        final PopupWindow mPopupWindow = new PopupWindow(contentView, (int) (width - DisplayUtils.dp2px(
                this, 64)), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        DisplayUtils.setBackgroundAlpha(this, true);

        ImageView ivCancel = (ImageView) contentView.findViewById(R.id.iv_cancel_receive);

        TextView tvConfirm = (TextView) contentView.findViewById(R.id.tv_goods_confirm);

        TextView tvCode = (TextView) contentView.findViewById(R.id.tv_goods_code);
        ImageView ivCode = (ImageView) contentView.findViewById(R.id.iv_goods_code);


        tvCode.setText("提货核销码：" + orderBean.getData().getPickupCode());

        if (!TextUtils.isEmpty(orderBean.getData().getPickupCode())) {
            qrBmp = QRCodeUtils.createQRCode(orderBean.getData().getPickupCode(),
                    (int) DisplayUtils.dp2px(this, 200));
            ivCode.setImageBitmap(qrBmp);
        }

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (qrBmp != null) {
                    qrBmp.recycle();
                    qrBmp = null;
                }
                DisplayUtils.setBackgroundAlpha(OrderDetailActivity.this, false);
                initData();
            }
        });
    }

    /**
     * 付款
     */
    private void payMoney() {
        Intent intent = new Intent(this, ConfirmOrderActivity.class);
        intent.putExtra("goods_id", orderBean.getData().getOrderDtlList().get(0).getDetailId());
        startActivity(intent);
    }

    /**
     * 退款
     */
    private void refundOrder(int refundId, String refundType, double price) {
        Intent intent = new Intent(this, ApplyRefundActivity.class);
        intent.putExtra("refund_id", refundId);
        intent.putExtra("refund_type", refundType);
        intent.putExtra("money", price);
        startActivity(intent);
    }

    /**
     * 取消订单
     */
    private void cancelOrder() {
        OkHttpUtils.post()
                .url(Constant.CANCEL_ORDER)
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
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean flag = jsonObject.getBoolean("flag");
                            if (flag) {
                                ToastUtils.showToast(OrderDetailActivity.this, "已取消订单");
                                initData();
                            } else {
                                String msg = jsonObject.getString("msg");
                                ToastUtils.showToast(OrderDetailActivity.this, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

}
