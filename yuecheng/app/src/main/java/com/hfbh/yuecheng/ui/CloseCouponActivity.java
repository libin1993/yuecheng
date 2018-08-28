package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
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
import com.hfbh.yuecheng.bean.CloseCouponBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DataManagerUtils;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.QRCodeUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/14 10:18
 * Email：1993911441@qq.com
 * Describe：优惠券核销
 */
public class CloseCouponActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.iv_activity_avatar)
    SimpleDraweeView ivAvatar;
    @BindView(R.id.tv_activity_phone)
    TextView tvPhone;
    @BindView(R.id.tv_activity_name)
    TextView tvCouponName;
    @BindView(R.id.iv_activity_qrcode)
    ImageView ivCouponQrcode;
    @BindView(R.id.tv_activity_code)
    TextView tvCouponCode;
    @BindView(R.id.tv_activity_time)
    TextView tvCouponTime;
    @BindView(R.id.tv_activity_address)
    TextView tvCouponAddress;
    @BindView(R.id.tv_activity_detail)
    TextView tvCouponDetail;
    @BindView(R.id.tv_activity_exchange_time)
    TextView tvExchangeTime;
    @BindView(R.id.tv_activity_exchange_type)
    TextView tvExchangeType;
    @BindView(R.id.view_header_line)
    View viewHeaderLine;
    @BindView(R.id.tv_activity_tip)
    TextView tvCouponTip;

    //优惠券id
    private int couponId;
    //优惠券type
    private int couponType;
    private CloseCouponBean couponBean;

    private Bitmap qrBmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_activity);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("满减券核销");
        tvCouponDetail.setText("使用说明");
        tvCouponDetail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvCouponDetail.getPaint().setAntiAlias(true);
        tvCouponTip.setText("注：请到店使用");
        tvCouponTip.setVisibility(View.VISIBLE);
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
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("couponId", String.valueOf(couponType))
                .addParams("memberCouponId", String.valueOf(couponId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        couponBean = GsonUtils.jsonToBean(response, CloseCouponBean.class);
                        if (couponBean.isFlag()) {
                            initView();
                        }
                    }
                });
    }

    private void initView() {

        if (couponBean.getData().getListCouponShop().size() > 0) {
            CloseCouponBean.DataBean.ListCouponShopBean listCouponShopBean = couponBean.getData()
                    .getListCouponShop().get(couponBean.getData().getListCouponShop().size() - 1);
            ivAvatar.setImageURI(listCouponShopBean.getShopLogo());
            tvPhone.setText(listCouponShopBean.getShopName());
        }


        tvCouponName.setText(couponBean.getData().getCouponName());


        if (!TextUtils.isEmpty(couponBean.getData().getVerifyCode())) {
            String qrCode = couponBean.getData().getVerifyCode() + 1;
            qrBmp = QRCodeUtils.createQRCode(qrCode, (int) DisplayUtils.dp2px(this, 200));
            ivCouponQrcode.setImageBitmap(qrBmp);
            tvCouponCode.setText(qrCode);
        }

        tvCouponTime.setText("有效时间：" + DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                "yyyy.MM.dd", couponBean.getData().getStartTime()) + " - " +
                DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                        "yyyy.MM.dd", couponBean.getData().getEndTime()));
        tvCouponAddress.setText("适用范围：" + couponBean.getData().getUseRange());

        tvExchangeTime.setText("领取时间：" + couponBean.getData().getExchangeTime());

        double needScore = couponBean.getData().getAccessValue();

        if (!TextUtils.isEmpty(couponBean.getData().getAccessType())) {
            switch (couponBean.getData().getAccessType()) {
                case "FREE":
                    tvExchangeType.setText("领取费用：免费");
                    break;
                case "POINT":
                    tvExchangeType.setText("领取积分：" + DisplayUtils.isInteger(needScore) + "积分");
                    break;
                case "BUY":
                    tvExchangeType.setText("领取费用：" + DisplayUtils.isInteger(needScore) + "元");
                    break;
            }
        }

    }

    private void getData() {
        Intent intent = getIntent();
        couponId = intent.getIntExtra("coupon_id", 0);
        couponType = intent.getIntExtra("coupon_type", 0);
    }

    @OnClick({R.id.iv_header_back, R.id.tv_activity_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_activity_detail:
                if (couponBean != null && couponBean.getData() != null) {
                    Intent intent = new Intent(CloseCouponActivity.this, CouponIntroActivity.class);
                    intent.putExtra("coupon_range", couponBean.getData().getUseRange());
                    intent.putExtra("coupon_intro", couponBean.getData().getCouponDesc());
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataManagerUtils.recycleBmp(qrBmp);
    }
}
