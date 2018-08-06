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
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.GoodsOrderBean;
import com.hfbh.yuecheng.bean.GroupGoodsDetailBean;
import com.hfbh.yuecheng.bean.PayOrderBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.bean.UserBalanceBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.MoneyInputFilter;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/7/18 15:23
 * Email：1993911441@qq.com
 * Describe：确认订单
 */
public class ConfirmOrderActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
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
    @BindView(R.id.tv_order_goods_reduce)
    TextView tvGoodsReduce;
    @BindView(R.id.tv_order_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_order_goods_add)
    TextView tvGoodsAdd;
    @BindView(R.id.tv_order_balance)
    TextView tvBalance;
    @BindView(R.id.et_use_balance)
    EditText etUseBalance;
    @BindView(R.id.tv_use_balance)
    TextView tvUseBalance;
    @BindView(R.id.tv_enroll_money)
    TextView tvNeedMoney;
    @BindView(R.id.tv_confirm_enroll)
    TextView tvConfirmOrder;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;


    private GroupGoodsDetailBean goodsBean;
    private UserBalanceBean balanceBean;
    //用户余额
    private double balance;
    //商品总价格
    private double totalPrice;
    //使用余额
    private double useBalance;
    private int num = 1;
    private PopupWindow mPopupWindow;
    private GoodsOrderBean orderBean;
    private int goodsId;
    //商品信息数据加载
    private boolean isGoods;
    //余额数据加载
    private boolean isBalance;

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
        initBalance();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(Constant.GOODS_DETAIL)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("commodityId", String.valueOf(goodsId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        goodsBean = GsonUtils.jsonToBean(response, GroupGoodsDetailBean.class);
                        if (goodsBean.isFlag() && goodsBean.getData() != null) {
                            isGoods = true;
                            initView();
                            if (isBalance) {
                                isGoods = false;
                                init();
                            }
                        }
                    }
                });
    }

    private void initView() {
        ivShopLogo.setImageURI(goodsBean.getData().getShopLogo());
        tvShopName.setText(goodsBean.getData().getShopName());
        ivGoodsLogo.setImageURI(goodsBean.getData().getPicturePath());
        tvGoodsName.setText(goodsBean.getData().getCommodityName());
        tvNewPrice.setText("¥" + DisplayUtils.isInteger(goodsBean.getData().getNowPrice()));
        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        tvOldPrice.setText("¥" + DisplayUtils.isInteger(goodsBean.getData().getOldPrice()));
    }

    /**
     * 付款金额
     */
    private void initPrice() {
        if (num == 1) {
            tvGoodsReduce.setTextColor(getResources().getColor(R.color.gray_ee));
            tvGoodsAdd.setTextColor(getResources().getColor(R.color.gray_99));
        } else if (num < goodsBean.getData().getBuyLimitNum() - goodsBean.getData().getBuyNum()) {
            tvGoodsReduce.setTextColor(getResources().getColor(R.color.gray_99));
            tvGoodsAdd.setTextColor(getResources().getColor(R.color.gray_99));
        } else {
            tvGoodsReduce.setTextColor(getResources().getColor(R.color.gray_99));
            tvGoodsAdd.setTextColor(getResources().getColor(R.color.gray_ee));
        }

        totalPrice = goodsBean.getData().getNowPrice() * num;

        tvGoodsNum.setText(String.valueOf(num));

        tvUseBalance.setText("¥" + DisplayUtils.decimalFormat(useBalance));
        tvNeedMoney.setText(DisplayUtils.decimalFormat(totalPrice - useBalance));

        etUseBalance.setFilters(new InputFilter[]{new MoneyInputFilter(Math.min(balance, totalPrice))});

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
                            isBalance = true;
                            balance = balanceBean.getData().getAccountBalance();
                            tvBalance.setText("¥" + DisplayUtils.decimalFormat(balance));
                            if (isGoods) {
                                isBalance = false;
                                init();
                            }
                        }
                    }
                });
    }

    private void init() {
        initPrice();
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
                        useBalance = Double.parseDouble(s.toString());
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

    private void getData() {
        goodsId = getIntent().getIntExtra("goods_id", 0);
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
                                SharedPreUtils.deleteStr(ConfirmOrderActivity.this, "is_login");
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
                Intent intent = new Intent(ConfirmOrderActivity.this, ValidateActivity.class);
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

        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                (int) DisplayUtils.dp2px(this, 191));
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
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
                Intent intent = new Intent(ConfirmOrderActivity.this, ValidateActivity.class);
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
                DisplayUtils.setBackgroundAlpha(ConfirmOrderActivity.this, false);
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
                            frozenMoney(orderBean.getData().getOrderNumber());

                        } else {
                            ToastUtils.showToast(ConfirmOrderActivity.this, "密码错误");
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(ConfirmOrderActivity.this, "is_login");
                            }
                        }
                    }
                });


    }

    /**
     * 提交订单
     */
    private void submitOrder() {
        Map<String, String> map = new HashMap<>();

        map.put("appType", MyApp.appType);
        map.put("appVersion", MyApp.appVersion);
        map.put("organizeId", MyApp.organizeId);
        map.put("hash", SharedPreUtils.getStr(this, "hash"));
        map.put("token", SharedPreUtils.getStr(this, "token"));
        map.put("commodityId", String.valueOf(goodsBean.getData().getCommodityId()));
        map.put("count", String.valueOf(num));
        map.put("commodityType", goodsBean.getData().getCommodityType());
        if (useBalance > 0) {
            map.put("useAmount", String.valueOf(useBalance));
        }
        OkHttpUtils.post()
                .url(Constant.SUBMIT_ORDER)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        orderBean = GsonUtils.jsonToBean(response, GoodsOrderBean.class);
                        if (orderBean.isFlag()) {
                            if (useBalance > 0) {
                                isSetPayPwd();
                            } else {
                                payMoney();
                            }
                        } else {
                            ToastUtils.showToast(ConfirmOrderActivity.this, orderBean.getMsg());
                        }

                    }
                });

    }

    /**
     * 付现金
     */
    private void payMoney() {
        List<PayOrderBean.OrderInfo> orderInfoList = new ArrayList<>();
        orderInfoList.add(new PayOrderBean.OrderInfo("订单号", orderBean.getData().getOrderNumber()));
        orderInfoList.add(new PayOrderBean.OrderInfo("商品名称", goodsBean.getData().getCommodityName()));

        List<PayOrderBean.DiscountBean> discountBeans = new ArrayList<>();
        discountBeans.add(new PayOrderBean.DiscountBean("订单金额", "¥" + DisplayUtils.decimalFormat(totalPrice)));
        discountBeans.add(new PayOrderBean.DiscountBean("余额抵扣", "-¥" + DisplayUtils.decimalFormat(useBalance)));

        MyApp.orderBean = new PayOrderBean(orderBean.getData().getOrderNumber(),
                "COMMODITY", "", false,
                totalPrice - useBalance, discountBeans, orderInfoList);

        startActivity(new Intent(ConfirmOrderActivity.this, ConfirmPayActivity.class));

    }

    @OnClick({R.id.iv_header_back, R.id.tv_order_goods_reduce, R.id.tv_order_goods_add, R.id.tv_confirm_enroll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_order_goods_reduce:
                if (goodsBean.getData() != null) {
                    if (num > 1) {
                        num--;
                        totalPrice = goodsBean.getData().getNowPrice() * num;
                        if (useBalance > Math.min(balance, totalPrice)) {
                            etUseBalance.setText(DisplayUtils.isInteger(Math.min(balance, totalPrice)));
                        } else {
                            initPrice();
                        }
                    }
                }
                break;
            case R.id.tv_order_goods_add:
                if (goodsBean.getData() != null) {
                    if (num < goodsBean.getData().getBuyLimitNum() - goodsBean.getData().getBuyNum()) {
                        num++;
                        initPrice();
                    }
                }

                break;
            case R.id.tv_confirm_enroll:
                submitOrder();
                break;
        }
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
                                ToastUtils.showToast(ConfirmOrderActivity.this, msg);
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
//                                EventBus.getDefault().post("balance_success");

                                List<PayOrderBean.OrderInfo> orderInfoList = new ArrayList<>();
                                orderInfoList.add(new PayOrderBean.OrderInfo("订单号", orderBean.getData().getOrderNumber()));
                                orderInfoList.add(new PayOrderBean.OrderInfo("商品名称", goodsBean.getData().getCommodityName()));
                                orderInfoList.add(new PayOrderBean.OrderInfo("支付方式", "余额支付"));

                                List<PayOrderBean.DiscountBean> discountBeans = new ArrayList<>();
                                discountBeans.add(new PayOrderBean.DiscountBean("订单金额",
                                        "¥" + DisplayUtils.decimalFormat(totalPrice)));
                                discountBeans.add(new PayOrderBean.DiscountBean("余额抵扣",
                                        "-¥" + DisplayUtils.decimalFormat(useBalance)));


                                MyApp.orderBean = new PayOrderBean(orderBean.getData().getOrderNumber(),
                                        "COMMODITY", "", false,
                                        totalPrice - useBalance, discountBeans, orderInfoList);


                                MyApp.orderBean.setPayResult(true);

                                startActivity(new Intent(ConfirmOrderActivity.this, PayResultActivity.class));


                                EventBus.getDefault().post("pay_order");
                            } else {
                                String msg = jsonObject.getString("msg");
                                ToastUtils.showToast(ConfirmOrderActivity.this, msg);
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

}
