package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.MD5Utils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.smarttop.library.utils.LogUtil;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.webkit.WebSettings.LOAD_NO_CACHE;

/**
 * Author：Libin on 2018/6/11 17:13
 * Email：1993911441@qq.com
 * Describe：游戏
 */
public class GameActivity extends BaseActivity {
    @BindView(R.id.webview_game)
    WebView webView;
    @BindView(R.id.iv_game_back)
    ImageView ivGameBack;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;

    //游戏url
    private String gameUrl;
    //app id
    private String appId;
    //token
    private String privateToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        getData();
        initView();
    }

    private void initView() {

        viewLoading.smoothToShow();
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
        //https加载http资源
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        String uid = SharedPreUtils.getStr(this, "hash");

        String[] split = gameUrl.split("/");
        String gameId = "";
        if (split.length > 0) {
            gameId = split[split.length - 1];
        }

        String raw_str = appId + gameId + uid + "," + MyApp.organizeId + privateToken;
        String token = MD5Utils.md5(raw_str);

        String url = gameUrl + "?appid=" + appId + "&uid=" + uid + "," + MyApp.organizeId + "&token=" + token;

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                viewLoading.smoothToHide();
            }
        });


    }

    private void getData() {
        Intent intent = getIntent();
        gameUrl = intent.getStringExtra("game_url");
        appId = intent.getStringExtra("app_id");
        privateToken = intent.getStringExtra("private_token");
    }

    @OnClick(R.id.iv_game_back)
    public void onViewClicked() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }
}
