package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityListBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Author：Libin on 2018/7/16 13:50
 * Email：1993911441@qq.com
 * Describe：主扫订单
 */
public class ScanOrderActivity extends BaseActivity {
    private String ip;
    private String orderNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_order);
        getData();
        initData();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.SCAN_ORDER)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("serverIp", ip)
                .addParams("ordernum", orderNo)
                .addParams("cardno", SharedPreUtils.getStr(this, "card_number"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ToastUtils.showToast(ScanOrderActivity.this,response);
                    }
                });
    }

    private void getData() {
        Intent intent = getIntent();
        ip = intent.getStringExtra("ip");
        orderNo = intent.getStringExtra("order_no");
    }
}
