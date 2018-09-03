package com.hfbh.yuecheng.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.webkit.WebSettings.LOAD_NO_CACHE;

/**
 * Author：Libin on 2018/6/14 15:40
 * Email：1993911441@qq.com
 * Describe：banner h5
 */
public class BannerInfoActivity extends BaseActivity {
    @BindView(R.id.webview_banner)
    WebView webView;
    @BindView(R.id.iv_banner_back)
    ImageView ivBannerBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_info);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        String url = getIntent().getStringExtra("url");
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

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    @OnClick(R.id.iv_banner_back)
    public void onViewClicked() {
        finish();
    }
}
