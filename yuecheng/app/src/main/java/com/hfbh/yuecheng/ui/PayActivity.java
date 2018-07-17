package com.hfbh.yuecheng.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.PayDataBean;
import com.hfbh.yuecheng.bean.PayResultBean;
import com.hfbh.yuecheng.bean.WechatPayBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.smarttop.library.utils.LogUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import okhttp3.Call;

/**
 * Author：Libin on 2018/7/11 16:26
 * Email：1993911441@qq.com
 * Describe：
 */
public class PayActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initData();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.PAY_DATA)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("totalFee", "0.01")
                .addParams("channelId", "WX_APP")
                .addParams("payType", "COMMODITY")
                .addParams("orderNo", "102917996223091318788")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        LogUtils.log(response);
//                        final PayDataBean payDataBean = GsonUtils.jsonToBean(response,PayDataBean.class);
//                        Runnable payRunnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                PayTask aliPay = new PayTask(PayActivity.this);
//                                Map<String, String> result = aliPay.payV2(payDataBean.getData().getOrderString(), true);
//                                Message msg = new Message();
//                                msg.what = 1;
//                                msg.obj = result;
//                                mHandler.sendMessage(msg);
//                            }
//                        };
//                        Thread payThread = new Thread(payRunnable);
//                        payThread.start();
                        WechatPayBean wechatPayBean = GsonUtils.jsonToBean(response, WechatPayBean.class);
                        IWXAPI api = WXAPIFactory.createWXAPI(PayActivity.this, Constant.APP_ID);
                        if (!api.isWXAppInstalled()) {
                            ToastUtils.showToast(PayActivity.this, "没有安装微信");
                        }
                        if (!api.isWXAppSupportAPI()) {
                            ToastUtils.showToast(PayActivity.this, "当前版本不支持支付功能");
                        }

                        PayReq req = new PayReq();
                        req.appId = Constant.APP_ID;
                        req.partnerId = wechatPayBean.getData().getMch_id();
                        req.prepayId = wechatPayBean.getData().getPrepay_id();
                        req.nonceStr = wechatPayBean.getData().getNonce_str();
                        req.timeStamp = wechatPayBean.getData().getTimestamp();
                        req.packageValue = "Sign=WXPay";
                        req.sign = wechatPayBean.getData().getSign();
                        api.sendReq(req);

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
                    String resultInfo = payResultBean.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResultBean.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showToast(PayActivity.this, "支付成功：" + resultInfo);


                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showToast(PayActivity.this, "支付失败：" + resultInfo);

                    }
                    break;
                }
            }
            return false;
        }
    });


}
