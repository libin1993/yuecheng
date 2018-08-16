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

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ScannedDetailBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.jungly.gridpasswordview.GridPasswordView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/12 15:24
 * Email：1993911441@qq.com
 * Describe：被扫订单
 */
public class ScanedOrderActivity extends BaseActivity {

    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_confirm_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_confirm_order_shop)
    TextView tvOrderShop;
    @BindView(R.id.tv_confirm_order_money)
    TextView tvOrderMoney;
    @BindView(R.id.tv_confirm_order_discount)
    TextView tvOrderDiscount;
    @BindView(R.id.tv_confirm_order_points)
    TextView tvOrderPoints;
    @BindView(R.id.tv_confirm_order_balance)
    TextView tvOrderBalance;
    @BindView(R.id.tv_confirm_order_need)
    TextView tvOrderNeed;
    @BindView(R.id.tv_confirm_order_coupon)
    TextView tvOrderCoupon;
    @BindView(R.id.tv_confirm_order)
    TextView tvConfirmOrder;

    private ScannedDetailBean orderBean;

    double totalDiscount = 0.00;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaned_order);
        ButterKnife.bind(this);
        getData();
        initView();
    }

    private void initView() {
        tvHeaderTitle.setText("确认下单");
        tvOrderNumber.setText(orderBean.getData().getOrderNo());
        tvOrderShop.setText(orderBean.getData().getFDMC());
        tvOrderMoney.setText("¥" + orderBean.getData().getMoney());
        if (!TextUtils.isEmpty(orderBean.getData().getTotalzk())) {
            tvOrderDiscount.setText("¥" + orderBean.getData().getTotalzk());
        } else {
            tvOrderDiscount.setText("¥0.00");
        }


        if (!TextUtils.isEmpty(orderBean.getData().getMember_zk())) {
            tvOrderPoints.setText("¥" + orderBean.getData().getMember_zk());
        } else {
            tvOrderPoints.setText("¥0.00");
        }

        double totalCoupon = 0.00;
        for (int i = 0; i < orderBean.getData().getYhqlist().size(); i++) {
            totalCoupon += Double.parseDouble(orderBean.getData().getYhqlist().get(i).getMoney());
        }

        tvOrderCoupon.setText("¥" + totalCoupon);

        for (int i = 0; i < orderBean.getData().getCzklist().size(); i++) {
            totalDiscount += Double.parseDouble(orderBean.getData().getCzklist().get(i).getMoney());
        }

        tvOrderBalance.setText("¥" + totalDiscount);

        double needPay = Double.parseDouble(orderBean.getData().getMoney());
        if (!TextUtils.isEmpty(orderBean.getData().getTotalzk())) {
            needPay -= Double.parseDouble(orderBean.getData().getTotalzk());
        }
        if (!TextUtils.isEmpty(orderBean.getData().getMember_zk())) {
            needPay -= Double.parseDouble(orderBean.getData().getMember_zk());
        }

        needPay -= (totalCoupon + totalDiscount);

        tvOrderNeed.setText("¥" + needPay);

    }

    private void getData() {
        orderBean = (ScannedDetailBean) getIntent().getSerializableExtra("order");
    }


    @OnClick({R.id.iv_header_back, R.id.tv_confirm_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_confirm_order:
                isUseBalance();
                break;
        }
    }

    /**
     * 确认购买
     */
    private void isUseBalance() {
        if (totalDiscount > 0) {
            inputPwd();
        } else {
            confirmBuy();
        }

    }

    private void inputPwd() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.ppw_validate_pwd, null);

        int height = DisplayUtils.getMetrics(this).heightPixels;
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (height - DisplayUtils.dp2px(this, 180)));
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        DisplayUtils.setBackgroundAlpha(this, true);

        ImageView ivCancel = (ImageView) contentView.findViewById(R.id.iv_cancel_pay);

        TextView tvForget = (TextView) contentView.findViewById(R.id.tv_forget_pay_pwd);


        GridPasswordView pwdView = (GridPasswordView) contentView.findViewById(R.id.et_validate_pay_pwd);


        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanedOrderActivity.this, ValidateActivity.class);
                intent.putExtra("type", "reset");
                startActivity(intent);
            }
        });

        pwdView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String s) {

            }

            @Override
            public void onInputFinish(String s) {
                validatePwd(s);
            }
        });


        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                DisplayUtils.setBackgroundAlpha(ScanedOrderActivity.this, false);
            }
        });
    }

    /**
     * 密码验证
     */
    private void validatePwd(String validatePwd) {
        OkHttpUtils.post()
                .url(Constant.VALIDATE_PWD)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("payPassword", validatePwd)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                        if (responseBean.isFlag()) {
                            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                                mPopupWindow.dismiss();
                            }
                            confirmBuy();
                        } else {
                            ToastUtils.showToast(ScanedOrderActivity.this, "密码错误");
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(ScanedOrderActivity.this, "is_login");
                            }
                        }
                    }
                });


    }

    /**
     * 确认购买
     */
    private void confirmBuy() {
        OkHttpUtils.post()
                .url(Constant.CONFIRM_ORDER)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("orderNo", orderBean.getData().getOrderNo())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean flag = jsonObject.getBoolean("flag");
                            if (flag) {
                                ToastUtils.showToast(ScanedOrderActivity.this, "购买成功");
                                finish();
                            } else {
                                String msg = jsonObject.getString("msg");
                                ToastUtils.showToast(ScanedOrderActivity.this, msg);
                                if (jsonObject.getInt("code") == 4002) {
                                    SharedPreUtils.deleteStr(ScanedOrderActivity.this, "is_login");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
