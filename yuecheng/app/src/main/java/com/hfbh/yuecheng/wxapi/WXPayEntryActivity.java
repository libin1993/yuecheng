package com.hfbh.yuecheng.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.constant.Constant;
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
    private String errCode;

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
        LogUtils.log(GsonUtils.jsonToString(resp));
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            LogUtils.log("a"+resp.errCode+resp.errStr);
            ToastUtils.showToast(this,resp.errStr);
            switch (resp.errCode) {
                case 0:
                    errCode = "支付成功";
                    break;
                case -1:
                    errCode = "支付失败";
                    break;
                case -2:
                    errCode = "支付取消";
                    break;
            }
            ToastUtils.showToast(this, errCode);

//            Intent intent = new Intent(this, AppointmentActivity.class);
//            intent.putExtra("order_id", MyApplication.orderId.split(",")[0]);
//            if (resp.errCode == 0) {
//                MobclickAgent.onEvent(this, "pay");
//                intent.putExtra("type", "pay_success");
//            } else {
//                intent.putExtra("type", "pay_fail");
//            }
//            startActivity(intent);
//
//            finish();
        }
    }

}