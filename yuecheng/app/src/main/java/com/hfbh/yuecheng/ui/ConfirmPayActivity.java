package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.PayDataBean;
import com.hfbh.yuecheng.bean.PayOrderBean;
import com.hfbh.yuecheng.bean.PayResultBean;
import com.hfbh.yuecheng.bean.WechatPayBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.smarttop.library.utils.LogUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/7/17 13:54
 * Email：1993911441@qq.com
 * Describe：确认支付
 */
public class ConfirmPayActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_order_need_pay)
    TextView tvNeedPay;
    @BindView(R.id.iv_wechat_pay)
    ImageView ivWechatPay;
    @BindView(R.id.rl_wechat_pay)
    RelativeLayout rlWechatPay;
    @BindView(R.id.iv_ali_pay)
    ImageView ivAliPay;
    @BindView(R.id.rl_ali_pay)
    RelativeLayout rlAliPay;
    @BindView(R.id.tv_pay_order)
    TextView tvPayOrder;
    @BindView(R.id.rv_confirm_pay)
    RecyclerView rvOrder;

    //支付方式
    private String payType = "WX_APP";
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pay);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("确认支付");
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
        initView();
    }

    private void initView() {
        tvNeedPay.setText("¥" + DisplayUtils.decimalFormat(MyApp.orderBean.getMoney()));
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<PayOrderBean.DiscountBean> adapter = new CommonAdapter<PayOrderBean.DiscountBean>(
                this, R.layout.rv_confirm_order_item, MyApp.orderBean.getDiscountList()) {
            @Override
            protected void convert(ViewHolder holder, PayOrderBean.DiscountBean discountBean, int position) {
                holder.setText(R.id.tv_order_title, discountBean.getTitle());
                holder.setText(R.id.tv_order_content, discountBean.getContent());
            }
        };
        rvOrder.setAdapter(adapter);
    }


    @OnClick({R.id.iv_header_back, R.id.rl_wechat_pay, R.id.rl_ali_pay, R.id.tv_pay_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.rl_wechat_pay:
                payType = "WX_APP";
                ivWechatPay.setVisibility(View.VISIBLE);
                ivAliPay.setVisibility(View.GONE);
                break;
            case R.id.rl_ali_pay:
                payType = "ALIPAY_APP";
                ivWechatPay.setVisibility(View.GONE);
                ivAliPay.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_pay_order:
                payMoney();
                break;
        }
    }


    /**
     * 支付
     */
    private void payMoney() {
        OkHttpUtils.post()
                .url(Constant.PAY_DATA)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("totalFee", String.valueOf(MyApp.orderBean.getMoney()))
                .addParams("channelId", payType)
                .addParams("payType", MyApp.orderBean.getOrderType())
                .addParams("orderNo", MyApp.orderBean.getOrderNo())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("flag")) {
                                EventBus.getDefault().post("pay_order");
                                if ("ALIPAY_APP".equals(payType)) {
                                    final PayDataBean payDataBean = GsonUtils.jsonToBean(response, PayDataBean.class);
                                    if (payDataBean.isFlag() && payDataBean.getData() != null) {
                                        Runnable payRunnable = new Runnable() {
                                            @Override
                                            public void run() {
                                                PayTask aliPay = new PayTask(ConfirmPayActivity.this);
                                                Map<String, String> result = aliPay.payV2(payDataBean.getData().getOrderString(), true);
                                                Message msg = new Message();
                                                msg.what = 1;
                                                msg.obj = result;
                                                mHandler.sendMessage(msg);
                                            }
                                        };
                                        Thread payThread = new Thread(payRunnable);
                                        payThread.start();
                                    }
                                } else {
                                    WechatPayBean wechatPayBean = GsonUtils.jsonToBean(response, WechatPayBean.class);
                                    if (!api.isWXAppInstalled()) {
                                        ToastUtils.showToast(ConfirmPayActivity.this, "没有安装微信");
                                    }
                                    if (!api.isWXAppSupportAPI()) {
                                        ToastUtils.showToast(ConfirmPayActivity.this, "当前版本不支持支付功能");
                                    }

                                    PayReq req = new PayReq();
                                    req.appId = Constant.APP_ID;
                                    req.partnerId = wechatPayBean.getData().getSub_mch_id();
                                    req.prepayId = wechatPayBean.getData().getPrepay_id();
                                    req.nonceStr = wechatPayBean.getData().getNonce_str();
                                    req.timeStamp = wechatPayBean.getData().getTimestamp();
                                    req.packageValue = "Sign=WXPay";
                                    req.sign = wechatPayBean.getData().getSign();
                                    api.sendReq(req);

                                    MyApp.orderBean.getOrderList().add(new PayOrderBean.OrderInfo(
                                            "支付方式", MyApp.orderBean.getPayType() + "微信支付"));
                                    MyApp.orderBean.getDiscountList().add(new PayOrderBean.DiscountBean(
                                            "微信支付", DisplayUtils.decimalFormat(MyApp.orderBean.getMoney())));

                                    finish();
                                }
                            } else {
                                ToastUtils.showToast(ConfirmPayActivity.this, jsonObject.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }


    /**
     * 支付宝支付回调
     */
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    PayResultBean payResultBean = new PayResultBean((Map<String, String>) msg.obj);
                    String resultStatus = payResultBean.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    boolean payResult;
                    if (TextUtils.equals(resultStatus, "9000")) {
                        payResult = true;
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        payResult = false;
                    }
                    MyApp.orderBean.setPayResult(payResult);

                    MyApp.orderBean.getOrderList().add(new PayOrderBean.OrderInfo(
                            "支付方式", MyApp.orderBean.getPayType() + "支付宝支付"));
                    MyApp.orderBean.getDiscountList().add(new PayOrderBean.DiscountBean(
                            "支付宝支付", DisplayUtils.decimalFormat(MyApp.orderBean.getMoney())));
                    startActivity(new Intent(ConfirmPayActivity.this, PayResultActivity.class));
                    finish();
                    break;
                }
            }
            return false;
        }
    });

}
