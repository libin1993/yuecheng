package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
    @BindView(R.id.rl_goods_status)
    RelativeLayout rlGoodsStatus;
    //商品id
    private int goodsId;
    private String url;
    private GroupGoodsDetailBean goodsBean;
    //余额支付回调
    private boolean paySuccess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        rlPopGoodsBuy.setVisibility(View.VISIBLE);
        getData();
        initData();
        initWebView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (paySuccess) {
            balancePayResult();
        }
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

        url = Constant.RUSH_GOODS_DETAIL + "?appType=Android&id=" + goodsId + "&appVersion="
                + MyApp.appVersion + "&organizeId=" + MyApp.organizeId
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
                    ShareUtils.showShare(this, goodsBean.getData().getPicturePath()
                            , goodsBean.getData().getCommodityName(),
                            "", url + "&share=true");
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

    /**
     * 支付结果
     */
    private void balancePayResult() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.ppw_exchange_success, null);
        int widthPixels = DisplayUtils.getMetrics(this).widthPixels;
        final PopupWindow mPopupWindow = new PopupWindow(contentView, (int) (widthPixels
                - DisplayUtils.dp2px(this, 66)), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        DisplayUtils.setBackgroundAlpha(this, true);

        ImageView ivResult = (ImageView) contentView.findViewById(R.id.iv_exchange_result);
        ImageView ivCancel = (ImageView) contentView.findViewById(R.id.iv_exchange_cancel);
        TextView tvResult = (TextView) contentView.findViewById(R.id.tv_exchange_result);
        TextView tvMsg = (TextView) contentView.findViewById(R.id.tv_exchange_reason);
        final TextView tvSuccess = (TextView) contentView.findViewById(R.id.tv_exchange_success);

        ivResult.setImageResource(R.mipmap.img_success);
        tvResult.setText("购买成功");
        tvSuccess.setText("去查看");
        tvSuccess.setBackgroundResource(R.drawable.bound_gradient_green);

        tvMsg.setText("购买的商品已放置于“我的-订单”，记得到店提货哦！");


        tvSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RushGoodsDetailActivity.this, MyOrderActivity.class));
                mPopupWindow.dismiss();
            }
        });

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                DisplayUtils.setBackgroundAlpha(RushGoodsDetailActivity.this, false);
            }
        });
    }


    @Subscribe
    public void isLogin(String msg) {
        if ("login_success".equals(msg)) {
            initData();
        }

        if ("balance_success".equals(msg)) {
            paySuccess = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
