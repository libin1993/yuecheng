package com.hfbh.yuecheng.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ConfirmPayBean;
import com.hfbh.yuecheng.bean.PayOrderBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.bean.UserBalanceBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.BigDecimalUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.jungly.gridpasswordview.GridPasswordView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/8/8 17:06
 * Email：1993911441@qq.com
 * Describe：去支付
 */
public class PayOrderActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.iv_order_shop_logo)
    SimpleDraweeView ivShopLogo;
    @BindView(R.id.tv_order_shop_name)
    TextView tvShopName;
    @BindView(R.id.iv_order_goods_logo)
    SimpleDraweeView ivGoodsLogo;
    @BindView(R.id.tv_order_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_order_goods_new_price)
    TextView tvNewPrice;
    @BindView(R.id.tv_order_goods_old_price)
    TextView tvOldPrice;
    @BindView(R.id.tv_order_buy_num)
    TextView tvBuyNum;
    @BindView(R.id.tv_order_balance)
    TextView tvBalance;
    @BindView(R.id.et_use_balance)
    EditText etUseBalance;
    @BindView(R.id.ll_order_balance)
    LinearLayout llOrderBalance;
    @BindView(R.id.tv_use_balance)
    TextView tvUseBalance;
    @BindView(R.id.tv_enroll_money)
    TextView tvNeedMoney;
    @BindView(R.id.tv_confirm_enroll)
    TextView tvConfirmOrder;
    @BindView(R.id.tv_goods_time)
    TextView tvGoodsTime;
    @BindView(R.id.tv_goods_type)
    TextView tvGoodsType;

    //订单id
    private int orderId;
    private ConfirmPayBean orderBean;

    private UserBalanceBean balanceBean;
    //用户余额
    private double balance;
    //商品总价格
    private double totalPrice;
    //使用余额
    private double useBalance;

    private PopupWindow mPopupWindow;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        tvHeaderTitle.setText("确认订单");
        tvConfirmOrder.setText("立即支付");

        etUseBalance.setFocusable(false);
        etUseBalance.setFocusableInTouchMode(false);
        getData();
        initData();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(Constant.PAY_ORDER)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("memberOrderShopId", String.valueOf(orderId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        orderBean = GsonUtils.jsonToBean(response, ConfirmPayBean.class);
                        if (orderBean.isFlag() && orderBean.getData() != null) {
                            initView();
                            if (orderBean.getData().isIsFrozen()) {
                                llOrderBalance.setVisibility(View.GONE);
                                useBalance = orderBean.getData().getUseAccountBalance();
                            } else {
                                llOrderBalance.setVisibility(View.VISIBLE);
                                initBalance();
                            }
                            initPrice();
                        }
                    }
                });
    }

    private void initView() {
        totalPrice = orderBean.getData().getPrice();

        ivShopLogo.setImageURI(orderBean.getData().getShopLogo());
        tvShopName.setText(orderBean.getData().getRelationName());
        ivGoodsLogo.setImageURI(orderBean.getData().getOrderDtlList().get(0).getDetailPicturepath());
        tvGoodsName.setText(orderBean.getData().getOrderDtlList().get(0).getDetailName());
        tvNewPrice.setText("¥" + DisplayUtils.isInteger(orderBean.getData().getOrderDtlList().get(0).getDetailPrice()));
        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        tvOldPrice.setText("¥" + DisplayUtils.isInteger(orderBean.getData().getOrderDtlList().get(0).getOriginalPrice()));
        tvBuyNum.setVisibility(View.VISIBLE);
        tvBuyNum.setText("x" + orderBean.getData().getOrderDtlList().get(0).getDetailAccount());

        tvGoodsTime.setText(orderBean.getData().getOrderDtlList().get(0).getGetTimeLimit() + "天提货有效");
        if ("GROUPON".equals(orderBean.getData().getOrderType())) {
            tvGoodsType.setText("失效未提货审核退款");
        } else {
            tvGoodsType.setText("失效未提货自动退款");
        }
    }


    /**
     * 余额
     */
    private void initBalance() {
        OkHttpUtils.get()
                .url(Constant.USER_BALANCE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        balanceBean = GsonUtils.jsonToBean(response, UserBalanceBean.class);
                        if (balanceBean.isFlag() && balanceBean.getData() != null) {
                            balance = balanceBean.getData().getAccountBalance();
                            tvBalance.setText("¥" + DisplayUtils.decimalFormat(balance));

                            if (balance > 0) {
                                etUseBalance.setFocusable(true);
                                etUseBalance.setFocusableInTouchMode(true);
                                etUseBalance.setEnabled(true);
                                etUseBalance.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {
                                        if (!TextUtils.isEmpty(s.toString())) {
                                            if (".".equals(s.toString())) {
                                                useBalance = Math.min(totalPrice, balance);
                                            } else {
                                                useBalance = Double.parseDouble(s.toString());
                                            }

                                            if (useBalance > Math.min(totalPrice, balance)) {
                                                etUseBalance.removeTextChangedListener(this);
                                                useBalance = Math.min(totalPrice, balance);
                                                etUseBalance.setText(DisplayUtils.isInteger(useBalance));
                                                etUseBalance.setSelection(etUseBalance.getText().toString().length());
                                                etUseBalance.addTextChangedListener(this);
                                            } else if (useBalance != (int) useBalance && String.valueOf(useBalance).length()
                                                    - String.valueOf(useBalance).indexOf(".") >= 3) {
                                                etUseBalance.removeTextChangedListener(this);
                                                useBalance = Double.parseDouble(DisplayUtils.decimalFormat(useBalance));
                                                etUseBalance.setText(DisplayUtils.isInteger(useBalance));
                                                etUseBalance.setSelection(etUseBalance.getText().toString().length());
                                                etUseBalance.addTextChangedListener(this);
                                            }

                                        } else {
                                            useBalance = 0;
                                        }

                                        initPrice();
                                    }
                                });
                            }
                        }
                    }
                });
    }

    /**
     * 付款金额
     */
    private void initPrice() {
        tvUseBalance.setText("¥" + DisplayUtils.decimalFormat(useBalance));
        tvNeedMoney.setText(DisplayUtils.decimalFormat(BigDecimalUtils.sub(totalPrice, useBalance)));
    }


    private void getData() {
        orderId = getIntent().getIntExtra("order_id", -1);
    }

    /**
     * 是否设置支付密码
     */
    private void isSetPayPwd() {
        OkHttpUtils.post()
                .url(Constant.IS_SET_PAY_PWD)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                        if (responseBean.isFlag()) {
                            inputPwd();
                        } else {
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(PayOrderActivity.this, "is_login");
                            } else {
                                setPayPwd();
                            }
                        }
                    }
                });
    }

    /**
     * 设置支付密码
     */
    private void setPayPwd() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("提示");
        dialog.setMessage("您尚未设置支付密码，是否前去设置？");
        //为“确定”按钮注册监听事件
        dialog.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(PayOrderActivity.this, ValidateActivity.class);
                intent.putExtra("type", "bind");
                startActivity(intent);
            }
        });

        //为“取消”按钮注册监听事件
        dialog.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();
    }


    /**
     * 输入密码弹窗
     */
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
                Intent intent = new Intent(PayOrderActivity.this, ValidateActivity.class);
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
                DisplayUtils.setBackgroundAlpha(PayOrderActivity.this, false);
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
                            frozenMoney(orderBean.getData().getOrderShopNumber());

                        } else {
                            ToastUtils.showToast(PayOrderActivity.this, "密码错误");
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(PayOrderActivity.this, "is_login");
                            }
                        }
                    }
                });
    }


    /**
     * 付现金
     */
    private void payMoney() {
        List<PayOrderBean.OrderInfo> orderInfoList = new ArrayList<>();
        orderInfoList.add(new PayOrderBean.OrderInfo("订单号", orderBean.getData().getOrderShopNumber()));
        orderInfoList.add(new PayOrderBean.OrderInfo("商品名称", orderBean.getData().getOrderDtlList().get(0).getDetailName()));

        List<PayOrderBean.DiscountBean> discountBeans = new ArrayList<>();
        discountBeans.add(new PayOrderBean.DiscountBean("订单金额", "¥" + DisplayUtils.decimalFormat(totalPrice)));
        discountBeans.add(new PayOrderBean.DiscountBean("余额抵扣", "-¥" + DisplayUtils.decimalFormat(useBalance)));

        MyApp.orderBean = new PayOrderBean(orderBean.getData().getOrderShopNumber(),
                "COMMODITY", "", false,totalPrice,
                BigDecimalUtils.sub(totalPrice, useBalance), orderBean.getData().getOrderType(),
                orderBean.getData().getOrderDtlList().get(0).getGetTimeLimit(), discountBeans, orderInfoList);

        startActivity(new Intent(PayOrderActivity.this, ConfirmPayActivity.class));

    }


    /**
     * 冻结余额
     */
    private void frozenMoney(final String orderNo) {
        OkHttpUtils.post()
                .url(Constant.FROZEN_ORDER_BALANCE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("orderPayNo", orderNo)
                .addParams("transMoney", String.valueOf(useBalance))
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
                                if (useBalance == totalPrice) {
                                    balancePay(orderNo);
                                } else {
                                    payMoney();
                                }
                            } else {
                                String msg = jsonObject.getString("msg");
                                ToastUtils.showToast(PayOrderActivity.this, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 预付卡全额支付
     */
    private void balancePay(String orderNo) {
        OkHttpUtils.post()
                .url(Constant.PAY_ORDER_BALANCE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("orderPayNo", orderNo)
                .addParams("transMoney", String.valueOf(useBalance))
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

                                List<PayOrderBean.OrderInfo> orderInfoList = new ArrayList<>();
                                orderInfoList.add(new PayOrderBean.OrderInfo("订单号", orderBean.getData().getOrderShopNumber()));
                                orderInfoList.add(new PayOrderBean.OrderInfo("商品名称",
                                        orderBean.getData().getOrderDtlList().get(0).getDetailName()));
                                orderInfoList.add(new PayOrderBean.OrderInfo("支付方式", "余额支付"));

                                List<PayOrderBean.DiscountBean> discountBeans = new ArrayList<>();
                                discountBeans.add(new PayOrderBean.DiscountBean("订单金额",
                                        "¥" + DisplayUtils.decimalFormat(totalPrice)));
                                discountBeans.add(new PayOrderBean.DiscountBean("余额抵扣",
                                        "-¥" + DisplayUtils.decimalFormat(useBalance)));


                                MyApp.orderBean = new PayOrderBean(orderBean.getData().getOrderShopNumber(),
                                        "COMMODITY", "", false,totalPrice,
                                        BigDecimalUtils.sub(totalPrice, useBalance), discountBeans, orderInfoList);


                                MyApp.orderBean.setPayResult(true);

                                startActivity(new Intent(PayOrderActivity.this, PayResultActivity.class));


                                EventBus.getDefault().post("pay_order");
                            } else {
                                String msg = jsonObject.getString("msg");
                                ToastUtils.showToast(PayOrderActivity.this, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * @param msg 支付回调
     */
    @Subscribe
    public void payOrder(String msg) {
        if ("pay_order".equals(msg)) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.iv_header_back, R.id.tv_confirm_enroll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_confirm_enroll:
                if (orderBean != null) {
                    if (orderBean.getData().isIsFrozen()) {
                        payMoney();
                    } else {
                        if (useBalance > 0) {
                            isSetPayPwd();
                        } else {
                            payMoney();
                        }
                    }

                }
                break;
        }
    }
}
