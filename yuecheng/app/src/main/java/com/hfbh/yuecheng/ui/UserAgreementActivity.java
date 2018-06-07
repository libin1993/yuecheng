package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.SharedPreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Libin on 2018/6/4 16:36
 * Email：1993911441@qq.com
 * Describe：用户协议
 */
public class UserAgreementActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.webview_user_agreement)
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeaderTitle.setText("用户使用协议");

        WebSettings ws = webview.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setBuiltInZoomControls(false);
        ws.setSupportZoom(false);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        webview.loadUrl(Constant.USER_AGREEMENT);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
