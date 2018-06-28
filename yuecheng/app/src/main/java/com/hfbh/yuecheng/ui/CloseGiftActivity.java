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
import com.hfbh.yuecheng.bean.CloseGiftBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DataManagerUtils;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.QRCodeUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/13 10:18
 * Email：1993911441@qq.com
 * Describe：
 */
public class CloseGiftActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.iv_activity_avatar)
    SimpleDraweeView ivUserAvatar;

    @BindView(R.id.tv_activity_name)
    TextView tvGiftName;
    @BindView(R.id.iv_activity_qrcode)
    ImageView ivGiftQrcode;
    @BindView(R.id.tv_activity_code)
    TextView tvGiftCode;
    @BindView(R.id.tv_activity_time)
    TextView tvGiftTime;
    @BindView(R.id.tv_activity_address)
    TextView tvGiftAddress;
    @BindView(R.id.tv_activity_detail)
    TextView tvGiftDetail;
    @BindView(R.id.tv_activity_exchange_time)
    TextView tvGiftExchangeTime;
    @BindView(R.id.tv_activity_exchange_type)
    TextView tvGiftExchangeType;
    @BindView(R.id.tv_activity_phone)
    TextView tvGiftPhone;
    private int gainId;
    private CloseGiftBean giftBean;

    private Bitmap qrBmp;
    private boolean isOnline = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_activity);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("礼品兑换码");
        getData();
        initData();
    }

    private void getData() {
        gainId = getIntent().getIntExtra("gain_id", 0);
        tvGiftDetail.setText("礼品详情");
        tvGiftDetail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvGiftDetail.getPaint().setAntiAlias(true);
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.CLOSE_GIFT)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("gainId", String.valueOf(gainId))
                .addParams("queryType", "GIFT")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        giftBean = GsonUtils.jsonToBean(s, CloseGiftBean.class);
                        if (giftBean != null && giftBean.getData() != null) {
                            initView();
                        }
                    }
                });

    }

    private void initView() {
        ivUserAvatar.setImageURI(giftBean.getMember().getMemberHead());
        tvGiftPhone.setText(giftBean.getMember().getMemberPhone());

        tvGiftName.setText(giftBean.getData().getGainName() + "  X " + giftBean.getData().getDownloadNum());


        if (!TextUtils.isEmpty(giftBean.getData().getVerifyCode())) {
            String qrCode = giftBean.getData().getVerifyCode();
            qrBmp = QRCodeUtils.createQRCode(qrCode,
                    (int) DisplayUtils.dp2px(this, 200));
            ivGiftQrcode.setImageBitmap(qrBmp);
            tvGiftCode.setText(qrCode);
        }

        String startTime = "";
        if (!TextUtils.isEmpty(giftBean.getData().getStartTime())) {
            startTime = DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                    "yyyy.MM.dd", giftBean.getData().getStartTime());
        }

        String endTime = "";
        if (!TextUtils.isEmpty(giftBean.getData().getEndTime())) {
            endTime = DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                    "yyyy.MM.dd", giftBean.getData().getEndTime());

            if (System.currentTimeMillis() > DateUtils.getTime("yyyy-MM-dd HH:mm:ss",
                    giftBean.getData().getEndTime())) {
                isOnline = false;
            }
        }

        tvGiftTime.setText("有效时间：" + startTime + " - " + endTime);
        tvGiftAddress.setText("兑换地点：请到指定地点（参考详情）");


        tvGiftExchangeTime.setText("兑换时间：" + giftBean.getData().getDownloadTime());
        tvGiftExchangeType.setText("兑换积分：" + giftBean.getData().getPoints() + "积分");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataManagerUtils.recycleBmp(qrBmp);
    }

    @OnClick({R.id.iv_header_back, R.id.tv_activity_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_activity_detail:
                if (giftBean != null && giftBean.getData() != null) {
                    if (isOnline) {
                        Intent intent = new Intent(this, GiftDetailActivity.class);
                        intent.putExtra("id", giftBean.getData().getPointsRewardId());
                        startActivity(intent);
                    } else {
                        ToastUtils.showToast(CloseGiftActivity.this, "sorry，礼品已下架~");
                    }
                }
                break;
        }
    }
}
