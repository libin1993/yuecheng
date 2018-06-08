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
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityListBean;
import com.hfbh.yuecheng.utils.DataManagerUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.QRCodeUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private ActivityListBean.DataBean dataBean;

    private Bitmap qrBmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_activity);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("活动报名核销");
        getData();
        initView();
    }


    private void getData() {
        dataBean = (ActivityListBean.DataBean) getIntent().getExtras().getSerializable("activity_info");
    }

    private void initView() {
        if (dataBean != null) {
            String avatar = SharedPreUtils.getStr(this, "avatar");
            String phone = SharedPreUtils.getStr(this, "phone");
            if (!TextUtils.isEmpty(avatar)) {
                ivActivityAvatar.setImageURI(avatar);
            }
            if (!TextUtils.isEmpty(phone)) {
                tvActivityPhone.setText(phone);
            }

            tvActivityName.setText(dataBean.getActivityTitle());

            qrBmp = QRCodeUtils.createQRCode(String.valueOf(dataBean.getVerifyCode()),
                    (int) DisplayUtils.dp2px(this, 200));
            ivActivityQrcode.setImageBitmap(qrBmp);

            tvActivityCode.setText(dataBean.getVerifyCode());
            tvActivityTime.setText("有效时间：" + dataBean.getActivityStarttime() + " - " + dataBean.getActivityEndtime());
            tvActivityAddress.setText("活动地点：" + dataBean.getAcivityAddress());
            tvActivityDetail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            tvExchangeTime.setText("报名时间：" + dataBean.getStartTimeStr() + " - " + dataBean.getEndTimeStr());

            if (!TextUtils.isEmpty(dataBean.getAcivityType())) {
                switch (dataBean.getAcivityType()) {
                    case "NONEED":
                        tvExchangeType.setVisibility(View.GONE);
                    case "FREE":
                        tvExchangeType.setVisibility(View.VISIBLE);
                        tvExchangeType.setText("报名费用： 免费");
                        break;
                    case "SCORE":
                        tvExchangeType.setVisibility(View.VISIBLE);
                        tvExchangeType.setText("报名费用： " + DisplayUtils.isInteger(dataBean.getEnrollScore()) + "积分");
                        break;
                    case "CASH":
                        tvExchangeType.setVisibility(View.VISIBLE);
                        tvExchangeType.setText("报名费用： ¥" + DisplayUtils.isInteger(dataBean.getEnrollFee()));
                        break;
                }
            }
        }

    }

    @OnClick({R.id.iv_header_back, R.id.tv_activity_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_activity_detail:
                Intent intent = new Intent(this, ActionDetailActivity.class);
                intent.putExtra("activity_id", dataBean.getMarketingActivitySignupId());
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
