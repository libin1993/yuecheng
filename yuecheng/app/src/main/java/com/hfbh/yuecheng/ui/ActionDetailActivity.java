package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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
    //活动id
    private int activityId;
    //活动类型
    private String type;
    //报名费用
    private double enrollFee;
    //报名积分
    private double enrollScore;


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

        if (!TextUtils.isEmpty(type)) {
            switch (type) {
                case "NONEED":
                    tvExchangeScore.setText("无需报名");
                    break;
                case "FREE":
                    tvExchangeScore.setText("免费");
                    break;
                case "SCORE":
                    tvExchangeScore.setText(DisplayUtils.isInteger(enrollScore));
                    tvExchangeType.setVisibility(View.VISIBLE);
                    break;
                case "CASH":
                    tvExchangeScore.setText("¥" + DisplayUtils.isInteger(enrollFee));
                    break;
            }
        }

        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setBuiltInZoomControls(false);
        ws.setSupportZoom(false);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.addJavascriptInterface(new JsInterface(), "android");

        String url = Constant.ACTIVITY_DETAIL + "?&appType=Android&id=" + activityId;
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

    public class JsInterface {
        /**
         * @param phone 手机号
         */
        @JavascriptInterface
        public void getPhone(String phone) {

        }

        /**
         * @param name 姓名
         */
        @JavascriptInterface
        public void getName(String name) {

        }

        /**
         * @param data
         */
        @JavascriptInterface
        public void getData(String data) {

        }
    }


    private void getData() {
        Intent intent = getIntent();
        activityId = intent.getIntExtra("activity_id", 0);
        type = intent.getStringExtra("type");
        enrollFee = intent.getDoubleExtra("money", 0);
        enrollScore = intent.getDoubleExtra("score", 0);
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
                if (SharedPreUtils.getBoolean(this, "is_login", false)) {
                    enrollActivity();
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
    }

    /**
     * 活动报名
     */
    private void enrollActivity() {


    }

}
