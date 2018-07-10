package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.utils.MD5Utils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.view.X5WebView;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Libin on 2018/7/9 12:14
 * Email：1993911441@qq.com
 * Describe：
 */
public class NewGameActivity extends BaseActivity {
    @BindView(R.id.web_game)
    X5WebView webView;
    @BindView(R.id.iv_back_game)
    ImageView ivBackGame;
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
        setContentView(R.layout.activity_game_new);
        ButterKnife.bind(this);
        getData();
        initView();

    }

    private void initView() {
        viewLoading.smoothToShow();
        try {
            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
                getWindow()
                        .setFlags(
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
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


        webView.addJavascriptInterface(new JsInterface(), "jsInterface");
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
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

    public class JsInterface {
        @JavascriptInterface
        public void getMsg(String msg) {

        }
    }

    private void getData() {
        Intent intent = getIntent();
        gameUrl = intent.getStringExtra("game_url");
        appId = intent.getStringExtra("app_id");
        privateToken = intent.getStringExtra("private_token");
    }


    @OnClick(R.id.iv_back_game)
    public void onViewClicked() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        //释放资源
        if (webView != null)
            webView.destroy();
        super.onDestroy();
    }

}
