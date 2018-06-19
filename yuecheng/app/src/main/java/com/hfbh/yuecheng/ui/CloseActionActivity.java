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
import com.hfbh.yuecheng.bean.ActivityDetailBean;
import com.hfbh.yuecheng.bean.ActivityListBean;
import com.hfbh.yuecheng.bean.CloseActivityBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DataManagerUtils;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.QRCodeUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/29 14:59
 * Email：1993911441@qq.com
 * Describe：核销
 */
public class CloseActionActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.iv_activity_avatar)
    SimpleDraweeView ivActivityAvatar;
    @BindView(R.id.tv_activity_phone)
    TextView tvActivityPhone;
    @BindView(R.id.tv_activity_name)
    TextView tvActivityName;
    @BindView(R.id.iv_activity_qrcode)
    ImageView ivActivityQrcode;
    @BindView(R.id.tv_activity_code)
    TextView tvActivityCode;
    @BindView(R.id.tv_activity_time)
    TextView tvActivityTime;
    @BindView(R.id.tv_activity_address)
    TextView tvActivityAddress;
    @BindView(R.id.tv_activity_detail)
    TextView tvActivityDetail;
    @BindView(R.id.tv_activity_exchange_time)
    TextView tvExchangeTime;
    @BindView(R.id.tv_activity_tip)
    TextView tvActivityTip;
    @BindView(R.id.tv_activity_exchange_type)
    TextView tvExchangeType;

    private Bitmap qrBmp;

    private CloseActivityBean activityBean;
    private int activityId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_activity);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("活动报名核销");
        tvActivityDetail.setText("查看活动详情");
        tvActivityDetail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvActivityDetail.getPaint().setAntiAlias(true);
        getData();
        initData();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(Constant.CLOSE_ACTIVITY)
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
                        activityBean = GsonUtils.jsonToBean(response, CloseActivityBean.class);
                        if (activityBean.isFlag()) {
                            initView();
                        }
                    }
                });
    }


    private void getData() {
        activityId = getIntent().getIntExtra("activity_id", 0);
    }

    private void initView() {

        String avatar = SharedPreUtils.getStr(this, "avatar");

        if (!TextUtils.isEmpty(avatar)) {
            ivActivityAvatar.setImageURI(avatar);
        }

        tvActivityPhone.setText(activityBean.getData().getStatistic().getPhone());

        tvActivityName.setText(activityBean.getData().getActivity().getActivityTitle());

        if (!TextUtils.isEmpty(activityBean.getData().getActivity().getVerifyCode())) {
            String qrCode = activityBean.getData().getActivity().getVerifyCode() + 2;
            qrBmp = QRCodeUtils.createQRCode(qrCode,
                    (int) DisplayUtils.dp2px(this, 200));
            ivActivityQrcode.setImageBitmap(qrBmp);
            tvActivityCode.setText(qrCode);
        }


        tvActivityTime.setText("有效时间：" + DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                "yyyy.MM.dd", activityBean.getData().getActivity().getActivityStarttime()) + " - " +
                DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                        "yyyy.MM.dd", activityBean.getData().getActivity().getActivityEndtime()));
        tvActivityAddress.setText("活动地点：" + activityBean.getData().getActivity().getAcivityAddress());

        tvExchangeTime.setText("报名时间：" + activityBean.getData().getStatistic().getSignupTime());

        if (!TextUtils.isEmpty(activityBean.getData().getActivity().getAcivityType())) {
            switch (activityBean.getData().getActivity().getAcivityType()) {
                case "NONEED":
                    tvExchangeType.setVisibility(View.GONE);
                case "FREE":
                    tvExchangeType.setVisibility(View.VISIBLE);
                    tvExchangeType.setText("报名费用： 免费");
                    break;
                case "SCORE":
                    tvExchangeType.setVisibility(View.VISIBLE);
                    tvExchangeType.setText("报名费用： " + DisplayUtils.isInteger(activityBean.getData().getActivity().getEnrollScore()) + "积分");
                    break;
                case "CASH":
                    tvExchangeType.setVisibility(View.VISIBLE);
                    tvExchangeType.setText("报名费用： ¥" + DisplayUtils.isInteger(activityBean.getData().getActivity().getEnrollFee()));
                    break;
            }
        }

        tvActivityTip.setVisibility(View.VISIBLE);


    }

    @OnClick({R.id.iv_header_back, R.id.tv_activity_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_activity_detail:
                Intent intent = new Intent(this, ActionDetailActivity.class);
                intent.putExtra("activity_id", activityId);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataManagerUtils.recycleBmp(qrBmp);
    }
}
