package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.CityListBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Author：Libin on 2018/5/23 09:03
 * Email：1993911441@qq.com
 * Describe：店铺详情
 */
public class ShopDetailActivity extends BaseActivity {
    private int shopId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        getData();
        initData();
    }


    private void getData() {
        shopId = getIntent().getIntExtra("shop_id", 0);
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.SHOP_DETAIL)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("shopId", String.valueOf(shopId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        LogUtils.log(s);

                    }
                });
    }
}
