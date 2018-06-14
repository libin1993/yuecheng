package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.CouponDetailBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
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
    private int couponType;
    private CouponDetailBean couponBean;
    //已领取数量
    private int hasGetNum;

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
        hasGetNum = couponBean.getData().getMemberBroughtNum();

        ivCoupon.setImageURI(couponBean.getData().getCouponImage());
        tvCouponName.setText(couponBean.getData().getCouponName());
        tvCouponRemain.setText("剩余： " + couponBean.getData().getBalanceNum() + "张");

        tvCouponTime.setText(DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                "yyyy.MM.dd", couponBean.getData().getStartTime()) + " - " +
                DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                        "yyyy.MM.dd", couponBean.getData().getEndTime()));

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

        if (couponBean.getData().getBalanceNum() > 0) {
            if (hasGetNum < couponBean.getData().getLimitNum()) {
                tvExchangeCoupon.setText("立即兑换");
                tvExchangeCoupon.setBackgroundResource(R.drawable.bound_gradient_red);
                tvExchangeCoupon.setEnabled(true);
            } else {
                tvExchangeCoupon.setText("已领取");
                tvExchangeCoupon.setBackgroundResource(R.drawable.bound_gray_99_33dp);
                tvExchangeCoupon.setEnabled(false);
            }

        } else {
            tvExchangeCoupon.setText("已抢光");
            tvExchangeCoupon.setBackgroundResource(R.drawable.bound_gray_99_33dp);
            tvExchangeCoupon.setEnabled(true);
        }

        tvCouponRange.setText(couponBean.getData().getUseRange());
        tvCouponIntro.setText(couponBean.getData().getCouponDesc());

    }

    private void getData() {
        Intent intent = getIntent();
        couponId = intent.getIntExtra("coupon_id", 0);
        couponType = intent.getIntExtra("coupon_type", 0);
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
                .addParams("cyCouponId", String.valueOf(couponType))
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
                            if (flag) {
                                hasGetNum++;
                                int data = jsonObject.getInt("data");
                                tvCouponRemain.setText("剩余： " + data + "张");
                                if (data == 0) {
                                    tvExchangeCoupon.setText("已抢光");
                                    tvExchangeCoupon.setEnabled(false);
                                    tvExchangeCoupon.setBackgroundResource(R.drawable.bound_gray_99_33dp);
                                } else {
                                    if (hasGetNum < couponBean.getData().getLimitNum()) {
                                        tvExchangeCoupon.setText("立即兑换");
                                        tvExchangeCoupon.setEnabled(true);
                                        tvExchangeCoupon.setBackgroundResource(R.drawable.bound_gray_99_33dp);
                                    } else {
                                        tvExchangeCoupon.setText("已领取");
                                        tvExchangeCoupon.setEnabled(false);
                                        tvExchangeCoupon.setBackgroundResource(R.drawable.bound_gray_99_33dp);
                                    }
                                }
                                exChangeResult(true, "您兑换的优惠券已放置于“我的-票券”，记得去查看哦！");
                            } else {
                                exChangeResult(false, msg);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }


    /**
     * 兑换结果
     */
    private void exChangeResult(final boolean flag, String msg) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.ppw_exchange_success, null);
        int widthPixels = DisplayUtils.getMetrics(this).widthPixels;
        final PopupWindow mPopupWindow = new PopupWindow(contentView, (int) (widthPixels
                - DisplayUtils.dp2px(this, 66)), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        DisplayUtils.setBackgroundAlpha(this, true);

        ImageView ivResult = (ImageView) contentView.findViewById(R.id.iv_exchange_result);
        ImageView ivCancel = (ImageView) contentView.findViewById(R.id.iv_exchange_cancel);
        TextView tvResult = (TextView) contentView.findViewById(R.id.tv_exchange_result);
        TextView tvMsg = (TextView) contentView.findViewById(R.id.tv_exchange_reason);
        final TextView tvSuccess = (TextView) contentView.findViewById(R.id.tv_exchange_success);
        if (flag) {
            ivResult.setImageResource(R.mipmap.img_success);
            tvResult.setText("兑换成功");
            tvSuccess.setText("去查看");
            tvSuccess.setBackgroundResource(R.drawable.bound_gradient_green);
        } else {
            ivResult.setImageResource(R.mipmap.img_failure);
            tvResult.setText("兑换失败");
            tvSuccess.setText("返回");
            tvSuccess.setBackgroundResource(R.drawable.bound_gradient_yellow);
        }
        tvMsg.setText(msg);


        tvSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    startActivity(new Intent(CouponDetailActivity.this, CouponActivity.class));
                }
                mPopupWindow.dismiss();
            }
        });

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                DisplayUtils.setBackgroundAlpha(CouponDetailActivity.this, false);
            }
        });
    }
}
