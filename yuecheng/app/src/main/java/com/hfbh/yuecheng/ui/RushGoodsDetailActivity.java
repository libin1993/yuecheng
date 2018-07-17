package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Libin on 2018/7/17 16:49
 * Email：1993911441@qq.com
 * Describe：秒杀商品详情
 */
public class RushGoodsDetailActivity extends BaseActivity {
    @BindView(R.id.webview_goods_detail)
    WebView webView;
    @BindView(R.id.iv_goods_back)
    ImageView ivGoodsBack;
    @BindView(R.id.iv_goods_share)
    ImageView ivGoodsShare;
    @BindView(R.id.tv_goods_money)
    TextView tvGoodsMoney;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.tv_buy_goods)
    TextView tvBuyGoods;
    @BindView(R.id.tv_goods_status)
    TextView tvGoodsStatus;
    @BindView(R.id.rl_pop_goods_buy)
    RelativeLayout rlPopGoodsBuy;
    private int goodsId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        getData();
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
