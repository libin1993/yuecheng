package com.hfbh.yuecheng.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.bean.PayOrderBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.EnrollOrderActivity;
import com.hfbh.yuecheng.ui.PayResultActivity;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.smarttop.library.utils.LogUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;


/**
 * 微信支付回调
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.color.gray_ed);
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                MyApp.orderBean.setPayResult(true);
            } else {
                MyApp.orderBean.setPayResult(false);
            }
            startActivity(new Intent(this, PayResultActivity.class));
            finish();
        }
    }

}