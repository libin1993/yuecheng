package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.hfbh.yuecheng.bean.GiftDetailBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.smarttop.library.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/24 17:59
 * Email：1993911441@qq.com
 * Describe：积分兑礼详情
 */
public class GiftDetailActivity extends BaseActivity {
    @BindView(R.id.iv_exchange_gift_bg)
    SimpleDraweeView ivGift;
    @BindView(R.id.tv_exchange_gift_need)
    TextView tvGiftScore;
    @BindView(R.id.tv_gift_count)
    TextView tvGiftCount;
    @BindView(R.id.tv_exchange_gift_name)
    TextView tvGiftName;
    @BindView(R.id.tv_gift_reduce)
    TextView tvGiftReduce;
    @BindView(R.id.tv_gift_add)
    TextView tvGiftAdd;
    @BindView(R.id.tv_exchange_gift_intro)
    TextView tvGiftInfo;
    @BindView(R.id.tv_exchange_gift_introduce)
    TextView tvGiftIntroduce;
    @BindView(R.id.iv_exchange_back)
    ImageView ivGiftBack;
    @BindView(R.id.iv_exchange_share)
    ImageView ivGiftShare;
    @BindView(R.id.tv_exchange_need_score)
    TextView tvTotalScore;
    @BindView(R.id.tv_exchange_now)
    TextView tvExchange;
    @BindView(R.id.tv_exchange_gift_count)
    TextView tvExchangeGiftCount;
    @BindView(R.id.tv_exchange_gift_info)
    TextView tvExchangeGiftInfo;


    private int giftId;
    private GiftDetailBean giftBean;
    //数量
    private int num = 1;
    //单件积分
    private int score;
    //限制兑换件数
    private int limitNum;

    //总数量
    private int totalNum;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_detail);
        ButterKnife.bind(this);
        getData();
        initData();
    }


    private void getData() {
        giftId = getIntent().getIntExtra("id", 0);
    }

    private void initData() {
        OkHttpUtils.get()
                .url(Constant.GIFT_DETAIL)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("pointsRewardId", String.valueOf(giftId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.log(giftId + response);
                        giftBean = GsonUtils.jsonToBean(response, GiftDetailBean.class);
                        if (giftBean.isFlag()) {
                            score = giftBean.getData().getNeedScore();
                            totalNum = giftBean.getData().getBalanceNum();
                            limitNum = giftBean.getData().getLimitGetNum();

                            initView();
                        }
                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {

        ivGift.setImageURI(giftBean.getData().getPicUrl());
        tvGiftScore.setText(String.valueOf(score));
        tvGiftName.setText(giftBean.getData().getRelateName());
        tvGiftInfo.setText(giftBean.getData().getExchangeIntro());
        tvGiftIntroduce.setText(giftBean.getData().getGiftIntro());


        initCount();
    }

    /**
     * 数量
     */
    private void initCount() {
        if (num == 1) {
            tvGiftReduce.setTextColor(getResources().getColor(R.color.gray_ee));
            tvGiftAdd.setTextColor(getResources().getColor(R.color.gray_99));
        } else if (num < limitNum) {
            tvGiftReduce.setTextColor(getResources().getColor(R.color.gray_99));
            tvGiftAdd.setTextColor(getResources().getColor(R.color.gray_99));
        } else {
            tvGiftReduce.setTextColor(getResources().getColor(R.color.gray_99));
            tvGiftAdd.setTextColor(getResources().getColor(R.color.gray_ee));
        }

        tvGiftCount.setText("还剩" + totalNum + "件");
        tvExchangeGiftCount.setText(String.valueOf(num));
        tvTotalScore.setText(String.valueOf(score * num));
        if (totalNum > 0) {
            tvExchange.setText("立即兑换");
            tvExchange.setBackgroundResource(R.drawable.bound_gradient_red);
        } else {
            tvExchange.setText("已抢光");
            tvExchange.setBackgroundResource(R.drawable.bound_gray_99_33dp);
        }

    }

    @OnClick({R.id.tv_gift_reduce, R.id.tv_gift_add, R.id.iv_exchange_back, R.id.iv_exchange_share,
            R.id.tv_exchange_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_gift_reduce:
                if (giftBean.getData() != null) {
                    if (num > 1) {
                        num--;
                    }
                    initCount();
                }

                break;
            case R.id.tv_gift_add:
                if (giftBean.getData() != null) {
                    if (num < limitNum) {
                        num++;
                    } else {
                        ToastUtils.showToast(this, "每人限制兑换" + limitNum + "个哦~");
                    }
                    initCount();
                }
                break;
            case R.id.iv_exchange_back:
                finish();
                break;
            case R.id.iv_exchange_share:
                break;
            case R.id.tv_exchange_now:
                if (SharedPreUtils.getBoolean(this, "is_login", false)) {
                    exChangeGift();
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }

                break;
        }
    }

    /**
     * 兑换礼品
     */
    private void exChangeGift() {
        if (giftBean.getData() != null) {
            OkHttpUtils.post()
                    .url(Constant.EXCHANGE_GIFT)
                    .addParams("appType", MyApp.appType)
                    .addParams("appVersion", MyApp.appVersion)
                    .addParams("organizeId", MyApp.organizeId)
                    .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                    .addParams("pointsRewardId", String.valueOf(giftId))
                    .addParams("exchangeNum", String.valueOf(num))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtils.log(response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean flag = jsonObject.getBoolean("flag");
                                String msg = jsonObject.getString("msg");
                                if (flag) {
                                    JSONObject data = jsonObject.getJSONObject("data");
                                    totalNum = data.getInt("balanceGetNum");
                                    limitNum = data.getInt("limitGetNum");
                                    initCount();
                                    exChangeResult(true, "您兑换的礼品已放置于“我的-兑换”，记得到店核销兑换哦！");
                                } else {
                                    exChangeResult(false, msg);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

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
                    startActivity(new Intent(GiftDetailActivity.this, MyExchangeActivity.class));
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
                DisplayUtils.setBackgroundAlpha(GiftDetailActivity.this, false);
            }
        });
    }
}
