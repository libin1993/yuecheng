package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityDetailBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
    @BindView(R.id.rl_action_join)
    RelativeLayout rlActionJoin;
    //活动id
    private int activityId;
    private ActivityDetailBean activityBean;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_detail);
        ButterKnife.bind(this);
        tvTitleHeader.setText("活动详情");
        getData();
        initData();
        initWebView();
    }

    private void initWebView() {
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setBuiltInZoomControls(false);
        ws.setSupportZoom(false);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

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

    private void initData() {
        OkHttpUtils.get()
                .url(Constant.ACTIVITY_INFO)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("id", String.valueOf(activityId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.log(response);
                        activityBean = GsonUtils.jsonToBean(response, ActivityDetailBean.class);
                        if (activityBean.isFlag()) {
                            initView();
                        }
                    }
                });
    }


    private void initView() {

        if (!TextUtils.isEmpty(activityBean.getData().getSignupDo().getAcivityType())) {
            switch (activityBean.getData().getSignupDo().getAcivityType()) {
                case "NONEED":
                    rlActionJoin.setVisibility(View.GONE);
                    break;
                case "FREE":
                    rlActionJoin.setVisibility(View.VISIBLE);
                    tvExchangeScore.setText("免费");
                    break;
                case "SCORE":
                    rlActionJoin.setVisibility(View.VISIBLE);
                    tvExchangeScore.setText(DisplayUtils.isInteger(activityBean.getData()
                            .getSignupDo().getEnrollScore()));
                    tvExchangeType.setVisibility(View.VISIBLE);
                    break;
                case "CASH":
                    rlActionJoin.setVisibility(View.VISIBLE);
                    tvExchangeScore.setText("¥" + DisplayUtils.isInteger(activityBean.getData()
                            .getSignupDo().getEnrollFee()));
                    break;
            }
        }

    }


    private void getData() {
        Intent intent = getIntent();
        activityId = intent.getIntExtra("activity_id", 0);
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
                enrollActivity();
                break;
        }
    }

    /**
     * 活动报名
     */
    private void enrollActivity() {
        Intent intent;
        if (SharedPreUtils.getBoolean(this, "is_login", false)) {
            intent = new Intent(this, EnrollActionActivity.class);
            intent.putExtra("activity_id", activityId);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
    }

}
