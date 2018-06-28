package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Build;
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
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
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
    @BindView(R.id.tv_activity_end)
    TextView tvActivityEnd;
    //活动id
    private int activityId;
    private ActivityDetailBean activityBean;
    //是否活动结束
    private boolean isActivityEnd;
    //是否已报名
    private boolean isEnroll;
    //是否报名开始
    private boolean isEnrollStart;
    //是否报名结束
    private boolean isEnrollEnd;
//    //是否满额
//    private boolean isLimit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
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
        ws.setCacheMode(LOAD_NO_CACHE);
        ws.setUseWideViewPort(true);
        ws.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        String url = Constant.ACTIVITY_DETAIL + "?appType=Android&id=" + activityId + "&appVersion="
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

    private void initData() {
        OkHttpUtils.get()
                .url(Constant.ACTIVITY_INFO)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("id", String.valueOf(activityId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        activityBean = GsonUtils.jsonToBean(response, ActivityDetailBean.class);
                        if (activityBean.isFlag()) {
                            initView();
                        }
                    }
                });
    }


    private void initView() {
        isActivityEnd = System.currentTimeMillis() > DateUtils.getTime(
                "yyyy-MM-dd HH:mm:ss", activityBean.getData().getSignupDo().getActivityEndtime());
        isEnroll = activityBean.getData().getSignupDo().getIsSignup();

        isEnrollStart = System.currentTimeMillis() >= DateUtils.getTime(
                "yyyy-MM-dd HH:mm:ss", activityBean.getData().getSignupDo().getStartTime());
        isEnrollEnd = System.currentTimeMillis() > DateUtils.getTime(
                "yyyy-MM-dd HH:mm:ss", activityBean.getData().getSignupDo().getEndTime());

//        isLimit = activityBean.getData().getSignupDo().getSignupLimitNumber() > 0 &&
//                activityBean.getData().getSignupDo().getSignupNumber()
//                        == activityBean.getData().getSignupDo().getSignupLimitNumber();
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


        if (!isActivityEnd) {
            if (isEnrollStart) {
                if (isEnroll) {
                    tvExchange.setBackgroundResource(R.drawable.bound_gradient_red);
                    tvExchange.setText("已报名，查看报名信息");
                    tvExchange.setEnabled(true);
                } else {
                    if (isEnrollEnd) {
                        tvExchange.setBackgroundResource(R.drawable.bound_gray_99_33dp);
                        tvExchange.setText("报名已结束");
                        tvExchange.setEnabled(false);
                    } else {
                        tvExchange.setBackgroundResource(R.drawable.bound_gradient_red);
                        tvExchange.setText("去报名");
                        tvExchange.setEnabled(true);
//                        if (!isLimit) {
//                            if (SharedPreUtils.getBoolean(this, "is_login", false)
//                                    && activityBean.getData().getSignupDo().getAcivityType().equals("SCORE")
//                                    && activityBean.getData().getSignupDo().getEnrollScore() >
//                                    activityBean.getData().getMember().getBalanceScore()) {
//
//                                tvExchange.setBackgroundResource(R.drawable.bound_gray_99_33dp);
//                                tvExchange.setText("积分不足");
//                                tvExchange.setEnabled(false);
//                            } else {
//                                tvExchange.setBackgroundResource(R.drawable.bound_gradient_red);
//                                tvExchange.setText("立即报名");
//                                tvExchange.setEnabled(true);
//                            }
//
//                        } else {
//                            tvExchange.setBackgroundResource(R.drawable.bound_gray_99_33dp);
//                            tvExchange.setText("名额已满");
//                            tvExchange.setEnabled(false);
//                        }
                    }
                }
            } else {
                tvExchange.setText("待报名");
                tvExchange.setBackgroundResource(R.drawable.bound_gray_99_33dp);
                tvExchange.setEnabled(false);
            }

        } else {
            tvExchangeType.setVisibility(View.GONE);
            tvExchangeScore.setVisibility(View.GONE);
            tvExchange.setVisibility(View.GONE);
            tvActivityEnd.setVisibility(View.VISIBLE);
        }
    }


    private void getData() {
        activityId = getIntent().getIntExtra("activity_id", 0);
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
        if (!isActivityEnd && isEnrollStart) {
            Intent intent;
            if (SharedPreUtils.getBoolean(this, "is_login", false)) {
                if (isEnroll) {
                    intent = new Intent(this, CloseActionActivity.class);
                    intent.putExtra("activity_id", activityId);
                    startActivity(intent);
                } else {
                    intent = new Intent(this, EnrollActionActivity.class);
                    intent.putExtra("activity_id", activityId);
                    startActivity(intent);
                }

            } else {
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }

        }
    }


    @Subscribe
    public void isLogin(String msg) {
        if ("login_success".equals(msg)) {
            webView.reload();
            initData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
