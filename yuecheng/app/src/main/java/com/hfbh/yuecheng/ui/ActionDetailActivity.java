package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
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
 * Author：Libin on 2018/6/4 14:13
 * Email：1993911441@qq.com
 * Describe：活动详情
 */
public class ActionDetailActivity extends BaseActivity {

    @BindView(R.id.webview_activity_detail)
    WebView webView;
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
    @BindView(R.id.rl_activity_status)
    RelativeLayout rlActivityStatus;
    @BindView(R.id.iv_activity_back)
    ImageView ivActivityBack;
    @BindView(R.id.iv_activity_share)
    ImageView ivActivityShare;
    //活动id
    private int activityId;
    private ActivityDetailBean activityBean;
    //是否活动结束
    private boolean isActivityEnd;
    //是否已报名
    private boolean isEnroll;
    //是否报名开始
    private boolean isEnrollStart;
    private String url;
//    //是否满额
//    private boolean isLimit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        getData();
        initWebView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
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

        url = Constant.ACTIVITY_DETAIL + "?appType=Android&id=" + activityId + "&appVersion="
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
        isActivityEnd = System.currentTimeMillis() > DateUtils.getTime("yyyy-MM-dd HH:mm:ss",
                activityBean.getData().getSignupDo().getActivityEndtime());
        isEnroll = activityBean.getData().getSignupDo().isIsSignup();

        isEnrollStart = System.currentTimeMillis() >= DateUtils.getTime(
                "yyyy-MM-dd HH:mm:ss", activityBean.getData().getSignupDo().getStartTime());
        //报名结束
        boolean isEnrollEnd = System.currentTimeMillis() > DateUtils.getTime(
                "yyyy-MM-dd HH:mm:ss", activityBean.getData().getSignupDo().getEndTime());

//        isLimit = activityBean.getData().getSignupDo().getSignupLimitNumber() > 0 &&
//                activityBean.getData().getSignupDo().getSignupNumber()
//                        == activityBean.getData().getSignupDo().getSignupLimitNumber();
        if (!TextUtils.isEmpty(activityBean.getData().getSignupDo().getAcivityType())) {
            switch (activityBean.getData().getSignupDo().getAcivityType()) {
                case "FREE":
                    tvExchangeScore.setText("免费");
                    tvExchangeType.setVisibility(View.GONE);
                    break;
                case "SCORE":
                    tvExchangeScore.setText(DisplayUtils.isInteger(activityBean.getData()
                            .getSignupDo().getEnrollScore()));
                    tvExchangeType.setVisibility(View.VISIBLE);
                    break;
                case "CASH":
                    tvExchangeScore.setText("¥" + DisplayUtils.isInteger(activityBean.getData()
                            .getSignupDo().getEnrollFee()));
                    tvExchangeType.setVisibility(View.GONE);
                    break;
            }
        }


        if (!isActivityEnd) {
            if ("NONEED".equals(activityBean.getData().getSignupDo().getAcivityType())) {
                rlActionJoin.setVisibility(View.GONE);
            } else {
                rlActionJoin.setVisibility(View.VISIBLE);
                if (isEnrollStart) {
                    if (isEnroll) {
                        rlActivityStatus.setVisibility(View.VISIBLE);
                        tvActivityEnd.setVisibility(View.GONE);
                        tvExchange.setText("已报名，查看报名信息");
                    } else {
                        if (isEnrollEnd) {
                            rlActivityStatus.setVisibility(View.GONE);
                            tvActivityEnd.setVisibility(View.VISIBLE);
                            tvActivityEnd.setText("报名已结束");
                        } else {
                            rlActivityStatus.setVisibility(View.VISIBLE);
                            tvActivityEnd.setVisibility(View.GONE);
                            tvExchange.setText("去报名");
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
                    rlActivityStatus.setVisibility(View.GONE);
                    tvActivityEnd.setVisibility(View.VISIBLE);
                    tvActivityEnd.setText("待报名");
                }
            }
        } else {
            rlActionJoin.setVisibility(View.VISIBLE);
            rlActivityStatus.setVisibility(View.GONE);
            tvActivityEnd.setVisibility(View.VISIBLE);
            tvActivityEnd.setText("活动已结束");
        }
    }


    private void getData() {
        activityId = getIntent().getIntExtra("activity_id", 0);
    }

    @OnClick({R.id.iv_activity_back, R.id.tv_exchange_activity, R.id.iv_activity_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_activity_back:
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.tv_exchange_activity:
                enrollActivity();
                break;
            case R.id.iv_activity_share:
                if (activityBean != null) {
                    ShareUtils.showShare(this, activityBean.getData().getSignupDo()
                                    .getActivityPicture(), activityBean.getData().getSignupDo().getActivityTitle(),
                            "", url + "&share=true");
                }

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
                //下单完成
                if (isEnroll) {
                    if (activityBean.getData().getStatistic() != null) {
                        intent = new Intent(this, EnrollInfoActivity.class);
                        intent.putExtra("enroll_id", activityBean.getData().getStatistic().getMarketingActivitySignupStatisticsId());
                        startActivity(intent);
                    }

                } else {
                    //是否已报名
                    if (activityBean.getData().getStatistic() != null) {
                        intent = new Intent(this, ConfirmEnrollActivity.class);
                        intent.putExtra("enroll_id", activityBean.getData().getStatistic().getMarketingActivitySignupStatisticsId());
                        intent.putExtra("activity_id", activityId);
                        startActivity(intent);
                    } else {
                        intent = new Intent(this, EnrollActionActivity.class);
                        intent.putExtra("activity_id", activityId);
                        startActivity(intent);
                    }

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
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
