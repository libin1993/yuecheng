package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Libin on 2018/6/4 14:13
 * Email：1993911441@qq.com
 * Describe：活动详情
 */
public class ActionDetailActivity extends BaseActivity {


    @BindView(R.id.webview_activity_detail)
    WebView webView;
    @BindView(R.id.tv_title_header)
    TextView tvTitleHeader;
    @BindView(R.id.iv_back_header)
    ImageView ivBackHeader;
    @BindView(R.id.tv_exchange_activity_score)
    TextView tvExchangeScore;
    @BindView(R.id.tv_exchange_activity_type)
    TextView tvExchangeType;
    @BindView(R.id.tv_exchange_activity)
    TextView tvExchange;
    private int activityId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_detail);
        ButterKnife.bind(this);
        tvTitleHeader.setText("活动详情");
        getData();
        initView();

    }


    private void initView() {
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setBuiltInZoomControls(false);
        ws.setSupportZoom(false);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        String url = Constant.ACTIVITY_DETAIL + "?id=" + activityId;
        if (SharedPreUtils.getBoolean(this, "is_login", false)) {
            url += "&hash=" + SharedPreUtils.getStr(this, "hash");
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

    private void getData() {
        activityId = getIntent().getIntExtra("activity_id", 0);
        LogUtils.log("acti" + activityId);
    }

    @OnClick({R.id.iv_back_header, R.id.tv_exchange_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_header:
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.tv_exchange_activity:
                break;
        }
    }

}
