package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.CouponDetailBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/25 14:04
 * Email：1993911441@qq.com
 * Describe：优惠券详情
 */
public class CouponDetailActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.view_header_line)
    View viewHeaderLine;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.iv_coupon_detail)
    SimpleDraweeView ivCoupon;
    @BindView(R.id.tv_coupon_detail_name)
    TextView tvCouponName;
    @BindView(R.id.tv_coupon_detail_remain)
    TextView tvCouponRemain;
    @BindView(R.id.tv_coupon_detail_time)
    TextView tvCouponTime;
    @BindView(R.id.tv_coupon_detail_range)
    TextView tvCouponRange;
    @BindView(R.id.tv_coupon_detail_intro)
    TextView tvCouponIntro;
    @BindView(R.id.tv_exchange_coupon_score)
    TextView tvCouponScore;
    @BindView(R.id.tv_exchange_coupon_now)
    TextView tvExchangeCoupon;
    @BindView(R.id.tv_exchange_coupon_type)
    TextView tvCouponType;

    private int couponId;
    private CouponDetailBean couponBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_detail);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("优惠券详情");
        viewHeaderLine.setVisibility(View.GONE);
        getData();
        initData();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.COUPON_DETAIL)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("couponId", String.valueOf(couponId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        couponBean = GsonUtils.jsonToBean(response, CouponDetailBean.class);
                        if (couponBean.isFlag()) {
                            initView();
                        }
                    }
                });
    }

    private void initView() {
        ivCoupon.setImageURI(couponBean.getData().getCouponImage());
        tvCouponName.setText(couponBean.getData().getCouponName());
        tvCouponRemain.setText("剩余： " + couponBean.getData().getBalanceNum() + "张");

        tvCouponTime.setText(couponBean.getData().getStartTime() + " - " + couponBean.getData().getEndTime());
        String type = couponBean.getData().getAccessType();
        if (!TextUtils.isEmpty(type)) {
            switch (type) {
                case "FREE":
                    tvCouponScore.setText("免费");
                    break;
                case "POINT":
                    tvCouponScore.setText(String.valueOf(couponBean.getData().getAccessValue()));
                    tvCouponType.setVisibility(View.VISIBLE);
                    break;
                case "BUY":
                    tvCouponScore.setText("¥" + couponBean.getData().getAccessValue());
                    break;
            }

        }
        tvCouponRange.setText(couponBean.getData().getUseRange());

    }

    private void getData() {
        couponId = getIntent().getIntExtra("coupon_id", 0);
    }

    @OnClick({R.id.iv_header_back, R.id.tv_exchange_coupon_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_exchange_coupon_now:
                if (SharedPreUtils.getBoolean(this, "is_login", false)) {
                    exchangeCoupon();
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
    }

    /**
     * 领取优惠券
     */
    private void exchangeCoupon() {
        OkHttpUtils.post()
                .url(Constant.EXCHANGE_COUPON)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("couponId", String.valueOf(couponId))
                .addParams("exchangeValue", String.valueOf(couponBean.getData().getAccessValue()))
                .addParams("exchangeNum", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            boolean flag = jsonObject.getBoolean("flag");
                            String msg = jsonObject.getString("msg");
                            ToastUtils.showToast(CouponDetailActivity.this, msg);
                            if (flag) {
                                int data = jsonObject.getInt("data");
                                tvCouponRemain.setText("剩余： " + data + "张");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }
}
