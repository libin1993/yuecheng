package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityDetailBean;
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
 * Author：Libin on 2018/7/17 16:20
 * Email：1993911441@qq.com
 * Describe：团购商品详情
 */
public class GroupGoodsDetailActivity extends BaseActivity {
    @BindView(R.id.webview_goods_detail)
    WebView webView;
    @BindView(R.id.iv_goods_back)
    ImageView ivGoodsBack;
    @BindView(R.id.iv_goods_share)
    ImageView ivGoodsShare;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.tv_buy_goods)
    TextView tvBuyGoods;
    @BindView(R.id.tv_goods_status)
    TextView tvGoodsStatus;
    @BindView(R.id.rl_pop_goods_buy)
    RelativeLayout rlPopGoodsBuy;
    //商品id
    private int goodsId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        getData();
        initData();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(Constant.GOODS_DETAIL)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("commodityId", String.valueOf(goodsId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }

    private void getData() {
        goodsId = getIntent().getIntExtra("goods_id",0);
    }

    @OnClick({R.id.iv_goods_back, R.id.iv_goods_share, R.id.tv_buy_goods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_goods_back:
                finish();
                break;
            case R.id.iv_goods_share:
                break;
            case R.id.tv_buy_goods:
                break;
        }
    }
}
