package com.hfbh.yuecheng.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.GroupGoodsDetailBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.ShareUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static android.webkit.WebSettings.LOAD_NO_CACHE;

/**
 * Author：Libin on 2018/6/11 12:31
 * Email：1993911441@qq.com
 * Describe：新品详情
 */
public class NewGoodsDetailActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.iv_header_share)
    ImageView ivHeaderShare;
    @BindView(R.id.webview_new_detail)
    WebView webView;
    @BindView(R.id.view_header_line)
    View viewHeaderLine;

    private int goodsId;
    private GroupGoodsDetailBean goodsBean;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goods_detail);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("新品详情");
        ivHeaderShare.setVisibility(View.VISIBLE);
        viewHeaderLine.setVisibility(View.GONE);
        getData();
        initData();
        initView();
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
                    }
                });
    }


    private void getData() {
        goodsId = getIntent().getIntExtra("goods_id", 0);
    }

    private void initView() {
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

        url = Constant.NEW_GOODS_DETAIL + "?appType=Android&id=" + goodsId
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


    @OnClick({R.id.iv_header_back, R.id.iv_header_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.iv_header_share:
                if (goodsBean != null) {
                    ShareUtils.showShare(this, goodsBean.getData().getPicturePath()
                            , goodsBean.getData().getCommodityName(),
                            "", url + "&share=true");
                }

                break;
        }
    }
}
