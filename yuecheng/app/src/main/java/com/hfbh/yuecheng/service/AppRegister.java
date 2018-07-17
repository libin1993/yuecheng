package com.hfbh.yuecheng.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hfbh.yuecheng.constant.Constant;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Author：Libin on 2016/11/21 16:23
 * Email：1993911441@qq.com
 * Describe：微信支付
 */
public class AppRegister extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);

        // 将该app注册到微信
        msgApi.registerApp(Constant.APP_ID);
    }
}