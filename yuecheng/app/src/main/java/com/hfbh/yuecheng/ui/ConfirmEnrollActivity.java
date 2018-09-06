package com.hfbh.yuecheng.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityRecordBean;
import com.hfbh.yuecheng.bean.EnrollOrderBean;
import com.hfbh.yuecheng.bean.PayOrderBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.bean.UserBalanceBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.BigDecimalUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SerializableMap;
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
 * Author：Libin on 2018/7/12 17:06
 * Email：1993911441@qq.com
 * Describe：确认报名
 */
public class ConfirmEnrollActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_enroll_activity_name)
    TextView tvActivityName;
    @BindView(R.id.tv_enroll_activity_time)
    TextView tvActivityTime;
    @BindView(R.id.tv_enroll_activity_fee)
    TextView tvActivityFee;
    @BindView(R.id.tv_user_money)
    TextView tvUserMoney;
    @BindView(R.id.et_input_money)
    EditText etInputMoney;
    @BindView(R.id.tv_input_money)
    TextView tvInputMoney;
    @BindView(R.id.tv_enroll_money)
    TextView tvEnrollMoney;
    @BindView(R.id.tv_confirm_enroll)
    TextView tvConfirmEnroll;
    @BindView(R.id.ll_enroll_balance)
    LinearLayout llEnrollBalance;

    //报名记录id
    private int enrollId;
    //活动id
    private int activityId;
    private ActivityRecordBean activityBean;
    private UserBalanceBean balanceBean;
    //用户余额
    private double balance;
    //活动报名金额
    private double enrollFee;
    //使用余额
    private double useBalance;
    private PopupWindow mPopupWindow;
    //报名姓名
    private String enrollName;
    //报名手机号
    private String enrollPhone;
    //报名数据
    private String enrollData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_enroll);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        tvHeaderTitle.setText("确认报名");
        tvConfirmEnroll.setText("去支付");
        etInputMoney.setFocusable(false);
        etInputMoney.setFocusableInTouchMode(false);

        getData();
        initData();

    }

    /**
     * 报名信息
     */
    private void initData() {
        OkHttpUtils.get()
                .url(Constant.CASH_ENROLL_INFO)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("id", String.valueOf(enrollId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        activityBean = GsonUtils.jsonToBean(response, ActivityRecordBean.class);
                        if (activityBean.isFlag() && activityBean.getData() != null) {
                            enrollFee = activityBean.getData().getActivity().getEnrollFee();
                            enrollName = activityBean.getData().getRecord().getName();
                            enrollPhone = activityBean.getData().getRecord().getPhone();
                            enrollData = activityBean.getData().getRecord().getDataSign();
                            //是否下单
                            if (activityBean.getData().getOrder() == null) {
                                llEnrollBalance.setVisibility(View.VISIBLE);
                                initBalance();
                            } else {
                                //是否冻结余额
                                if (activityBean.getData().getCyOrder() == null) {
                                    llEnrollBalance.setVisibility(View.VISIBLE);
                                    initBalance();
                                } else {
                                    llEnrollBalance.setVisibility(View.GONE);
                                    useBalance = activityBean.getData().getCyOrder().getTransMoney();
                                    tvInputMoney.setText("¥" + DisplayUtils.decimalFormat(useBalance));
                                    tvEnrollMoney.setText(DisplayUtils.decimalFormat(BigDecimalUtils.sub(enrollFee, useBalance)));
                                }
                            }

                            initView();
                        }
                    }
                });
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
                            if (balance > 0) {
                                etInputMoney.setEnabled(true);
                                etInputMoney.setFocusable(true);
                                etInputMoney.setFocusableInTouchMode(true);
                            }
                            initUserBalance();
                        }
                    }
                });
    }

    /**
     * 余额
     */
    private void initUserBalance() {
        tvEnrollMoney.setText(DisplayUtils.decimalFormat(enrollFee));
        tvUserMoney.setText("¥" + DisplayUtils.decimalFormat(balance));

        etInputMoney.addTextChangedListener(new TextWatcher() {
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
                        useBalance = Math.min(enrollFee, balance);
                    } else {
                        useBalance = Double.parseDouble(s.toString());
                    }

                    if (useBalance > Math.min(enrollFee, balance)) {
                        etInputMoney.removeTextChangedListener(this);
                        useBalance = Math.min(enrollFee, balance);
                        etInputMoney.setText(DisplayUtils.isInteger(useBalance));
                        etInputMoney.setSelection(etInputMoney.getText().toString().length());
                        etInputMoney.addTextChangedListener(this);
                    } else if (useBalance != (int) useBalance && String.valueOf(useBalance).length()
                            - String.valueOf(useBalance).indexOf(".") >= 3) {
                        etInputMoney.removeTextChangedListener(this);
                        useBalance = Double.parseDouble(DisplayUtils.decimalFormat(useBalance));
                        etInputMoney.setText(DisplayUtils.isInteger(useBalance));
                        etInputMoney.setSelection(etInputMoney.getText().toString().length());
                        etInputMoney.addTextChangedListener(this);
                    }
                    tvInputMoney.setText("¥" + DisplayUtils.decimalFormat(useBalance));
                    tvEnrollMoney.setText(DisplayUtils.decimalFormat(BigDecimalUtils.sub(enrollFee, useBalance)));

                } else {
                    useBalance = 0;
                    tvInputMoney.setText("¥0.00");
                    tvEnrollMoney.setText(DisplayUtils.decimalFormat(enrollFee));
                }
            }
        });
    }


    /**
     * 活动
     */
    private void initView() {
        tvActivityName.setText(activityBean.getData().getActivity().getActivityTitle());
        tvActivityTime.setText(activityBean.getData().getRecord().getSignupTime());
        tvActivityFee.setText("¥" + DisplayUtils.decimalFormat(enrollFee));
    }

    private void getData() {
        Intent intent = getIntent();

        activityId = intent.getIntExtra("activity_id", -1);
        enrollId = intent.getIntExtra("enroll_id", -1);
    }

    @OnClick({R.id.iv_header_back, R.id.tv_confirm_enroll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_confirm_enroll:
                if (activityBean != null && activityBean.getData() != null) {
                    //是否下单,未下单去下单，已下单去支付
                    if (activityBean.getData().getOrder() == null) {
                        if (useBalance > 0) {
                            isSetPayPwd();
                        } else {
                            cashEnroll();
                        }
                    } else {
                        //是否冻结余额，未使用余额去冻结余额
                        if (activityBean.getData().getCyOrder() == null) {
                            if (useBalance == 0) {
                                payMoney(activityBean.getData().getOrder().getTranNo());
                            } else {
                                frozenMoney(activityBean.getData().getOrder().getTranNo());
                            }
                        } else {
                            payMoney(activityBean.getData().getOrder().getTranNo());
                        }
                    }
                }

                break;
        }
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
                                SharedPreUtils.deleteStr(ConfirmEnrollActivity.this, "is_login");
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
                Intent intent = new Intent(ConfirmEnrollActivity.this, ValidateActivity.class);
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


        final GridPasswordView pwdView = (GridPasswordView) contentView.findViewById(R.id.et_validate_pay_pwd);


        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmEnrollActivity.this, ValidateActivity.class);
                intent.putExtra("type", "reset");
                startActivity(intent);
            }
        });

        pwdView.setFocusable(true);
        pwdView.setFocusableInTouchMode(true);
        pwdView.requestFocus();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(pwdView, InputMethodManager.SHOW_IMPLICIT);
            }
        },200);

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
                DisplayUtils.setBackgroundAlpha(ConfirmEnrollActivity.this, false);
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
                            cashEnroll();

                        } else {
                            ToastUtils.showToast(ConfirmEnrollActivity.this, "密码错误");
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(ConfirmEnrollActivity.this, "is_login");
                            }
                        }
                    }
                });

    }

    /**
     * 现金报名
     */
    private void cashEnroll() {
        Map<String, String> map = new HashMap<>();

        map.put("appType", MyApp.appType);
        map.put("appVersion", MyApp.appVersion);
        map.put("organizeId", MyApp.organizeId);
        map.put("hash", SharedPreUtils.getStr(this, "hash"));
        map.put("token", SharedPreUtils.getStr(this, "token"));
        map.put("realname", enrollName);
        map.put("mobile", enrollPhone);
        map.put("id", String.valueOf(activityId));

        if (enrollData != null) {
            map.put("data", enrollData);
        }
        if (useBalance > 0) {
            map.put("enrollFee", String.valueOf(useBalance));
        }
        OkHttpUtils.get()
                .url(Constant.CASH_ENROLL_ACTIVITY)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        EnrollOrderBean orderBean = GsonUtils.jsonToBean(response, EnrollOrderBean.class);
                        if (orderBean.isFlag() && orderBean.getData() != null) {
                            if (useBalance == 0) {
                                payMoney(orderBean.getData().getOrder().getTranNo());
                            } else {
                                frozenMoney(orderBean.getData().getOrder().getTranNo());
                            }
                        } else {
                            ToastUtils.showToast(ConfirmEnrollActivity.this, orderBean.getMsg());
                        }
                    }
                });

    }

    /**
     * 付现金
     */
    private void payMoney(String orderNo) {
        List<PayOrderBean.DiscountBean> discountBeans = new ArrayList<>();
        discountBeans.add(new PayOrderBean.DiscountBean("订单金额", "¥" + DisplayUtils.decimalFormat(enrollFee)));
        discountBeans.add(new PayOrderBean.DiscountBean("余额抵扣", "-¥" + DisplayUtils.decimalFormat(useBalance)));

        List<PayOrderBean.OrderInfo> orderInfoList = new ArrayList<>();
        orderInfoList.add(new PayOrderBean.OrderInfo("订单号", orderNo));
        MyApp.orderBean = new PayOrderBean(orderNo, "ACTIVITY", "", false,
                enrollFee, BigDecimalUtils.sub(enrollFee, useBalance), discountBeans, orderInfoList);
        startActivity(new Intent(ConfirmEnrollActivity.this, ConfirmPayActivity.class));
    }


    /**
     * 冻结余额
     */
    private void frozenMoney(final String orderNo) {
        OkHttpUtils.post()
                .url(Constant.FROZEN_ENROLL_BALANCE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("orderNo", orderNo)
                .addParams("enrollFee", String.valueOf(useBalance))
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
                                if (useBalance == enrollFee) {
                                    balancePay(orderNo);
                                } else {
                                    payMoney(orderNo);
                                }
                            } else {
                                String msg = jsonObject.getString("msg");
                                ToastUtils.showToast(ConfirmEnrollActivity.this, msg);
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
        OkHttpUtils.get()
                .url(Constant.ENROLL_BALANCE_PAY)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("orderNo", orderNo)
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
                                EventBus.getDefault().post("enroll_success");
                                finish();
                            } else {
                                String msg = jsonObject.getString("msg");
                                ToastUtils.showToast(ConfirmEnrollActivity.this, msg);
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
