package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.GroupGoodsDetailBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.ShareUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.TitleBarUtils;
import com.hfbh.yuecheng.view.ScrollWebView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static android.webkit.WebSettings.LOAD_NO_CACHE;

/**
 * Author：Libin on 2018/7/17 16:49
 * Email：1993911441@qq.com
 * Describe：秒杀商品详情
 */
public class RushGoodsDetailActivity extends BaseActivity {
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
    @BindView(R.id.rl_goods_status)
    RelativeLayout rlGoodsStatus;
    @BindView(R.id.tv_goods_title)
    TextView tvGoodsTitle;
    @BindView(R.id.rl_pop_goods_buy)
    RelativeLayout rlPopGoodsBuy;
    @BindView(R.id.webview_goods_detail)
    WebView webView;

    //商品id
    private int goodsId;
    private String url;
    private GroupGoodsDetailBean goodsBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TitleBarUtils.setNoTitleBar(this);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        rlPopGoodsBuy.setVisibility(View.VISIBLE);
        tvGoodsTitle.setText("秒杀详情");
        getData();
        initData();
        initWebView();
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
                        goodsBean = GsonUtils.jsonToBean(response, GroupGoodsDetailBean.class);
                        if (goodsBean.isFlag() && goodsBean.getData() != null) {
                            initView();
                        }
                    }
                });
    }

    private void initView() {
        boolean isFinish = !TextUtils.isEmpty(goodsBean.getData().getEndTime()) &&
                DateUtils.getTime("yyyy-MM-dd HH:mm:ss", goodsBean.getData().getNowDate()) >
                        DateUtils.getTime("yyyy-MM-dd HH:mm:ss", goodsBean.getData().getEndTime());
        boolean isStart = !TextUtils.isEmpty(goodsBean.getData().getStartTime()) &&
                DateUtils.getTime("yyyy-MM-dd HH:mm:ss", goodsBean.getData().getNowDate()) >
                        DateUtils.getTime("yyyy-MM-dd HH:mm:ss", goodsBean.getData().getStartTime());
        boolean isBuy = "Y".equals(goodsBean.getData().getIsJoin());
        boolean isNull = goodsBean.getData().getCommodityNum() == 0;

        if (isFinish) {
            tvGoodsStatus.setText("抢购已结束");
            tvGoodsStatus.setVisibility(View.VISIBLE);
            rlGoodsStatus.setVisibility(View.GONE);
        } else if (!isStart) {
            tvGoodsStatus.setText("抢购未开始");
            tvGoodsStatus.setVisibility(View.VISIBLE);
            rlGoodsStatus.setVisibility(View.GONE);
        } else if (isBuy) {
            tvGoodsStatus.setText("已抢购");
            tvGoodsStatus.setVisibility(View.VISIBLE);
            rlGoodsStatus.setVisibility(View.GONE);
        } else if (isNull) {
            tvGoodsStatus.setText("已抢光");
            tvGoodsStatus.setVisibility(View.VISIBLE);
            rlGoodsStatus.setVisibility(View.GONE);
        } else {
            tvGoodsPrice.setText(DisplayUtils.isInteger(goodsBean.getData().getNowPrice()));
            tvBuyGoods.setText("立即抢购");
            tvGoodsStatus.setVisibility(View.GONE);
            rlGoodsStatus.setVisibility(View.VISIBLE);
        }

    }

    private void initWebView() {
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setBuiltInZoomControls(false);
        ws.setSupportZoom(false);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        ws.setCacheMode(LOAD_NO_CACHE);
        ws.setUseWideViewPort(true);
        ws.setLoadWithOverviewMode(true);
        //支持H5 DOM Storage
        ws.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        url = Constant.RUSH_GOODS_DETAIL + "?appType=Android&id=" + goodsId
                + "&appVersion=" + MyApp.appVersion + "&organizeId=" + MyApp.organizeId
                + "&token=" + SharedPreUtils.getStr(this, "token")
                + "&hash=" + SharedPreUtils.getStr(this, "hash");
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void getData() {
        goodsId = getIntent().getIntExtra("goods_id", 0);
    }

    @OnClick({R.id.iv_goods_back, R.id.iv_goods_share, R.id.tv_buy_goods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_goods_back:
                finish();
                break;
            case R.id.iv_goods_share:
                if (goodsBean != null && goodsBean.getData() != null) {
                    ShareUtils.showShare(this, goodsBean.getData().getPicturePath(),
                            goodsBean.getData().getCommodityName(), "", url + "&share=true");
                }
                break;
            case R.id.tv_buy_goods:
                if (SharedPreUtils.getBoolean(this, "is_login", false)) {
                    Intent intent = new Intent(this, ConfirmOrderActivity.class);
                    intent.putExtra("goods", goodsBean);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
    }


    @Subscribe
    public void isLogin(String msg) {
        if ("login_success".equals(msg)) {
            initData();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
