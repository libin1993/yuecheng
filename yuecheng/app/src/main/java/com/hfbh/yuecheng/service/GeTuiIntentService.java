package com.hfbh.yuecheng.service;

import android.content.Context;


import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;


/**
 * Author：Libin on 2016/12/20 13:47
 * Email：1993911441@qq.com
 * Describe：个推
 */
public class GeTuiIntentService extends GTIntentService {

    @Override
    public void onReceiveServicePid(Context context, int i) {
    }

    @Override
    public void onReceiveClientId(Context context, String s) {
        if (s != null) {
            SharedPreUtils.saveStr(this, "client_id", s);
        }
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage gtTransmitMessage) {

        String msg = new String(gtTransmitMessage.getPayload());

    }

    @Override
    public void onReceiveOnlineState(Context context, boolean b) {

    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage gtCmdMessage) {

    }
}
