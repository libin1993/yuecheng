package com.hfbh.yuecheng.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityRecordBean;
import com.hfbh.yuecheng.bean.EnrollOrderBean;
import com.hfbh.yuecheng.bean.PayOrderBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.bean.UserBalanceBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.MoneyInputFilter;
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

    //报名参数
    private Map<String, String> map;
    //活动id
    private int activityId;
    //报名记录id
    private int enrollId;
    private ActivityRecordBean activityBean;
    private UserBalanceBean balanceBean;
    //用户余额
    private double balance;
    //活动接口是否成功
    private boolean isActivity;
    //余额接口是否成功
    private boolean isBalance;
    //活动报名金额
    private double enrollFee;
    //使用余额
    private double useBalance;
    private PopupWindow mPopupWindow;
    private EnrollOrderBean orderBean;


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
        tvConfirmEnroll.setEnabled(false);

        getData();
        initData();
        initBalance();
    }

    /**
     * 活动数据
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

                            isActivity = true;
                            initView();
                            if (isBalance) {
                                initUserBalance();
                            }

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
                                etInputMoney.setFocusable(true);
                                etInputMoney.setFocusableInTouchMode(true);
                            }
                            isBalance = true;
                            if (isActivity) {
                                initUserBalance();
                            }
                        }
                    }
                });
    }

    /**
     * 余额
     */
    private void initUserBalance() {
        tvEnrollMoney.setText(DisplayUtils.decimalFormat(enrollFee));
        tvConfirmEnroll.setEnabled(true);
        tvUserMoney.setText("¥" + DisplayUtils.decimalFormat(balance));
        etInputMoney.setEnabled(true);
//        etInputMoney.setFilters(new InputFilter[]{new MoneyInputFilter(Math.min(balance, enrollFee))});


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
                    if (".".equals(s.toString())){
                        useBalance = Math.min(enrollFee, balance);
                    }else {
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
                    tvEnrollMoney.setText(DisplayUtils.decimalFormat(enrollFee - useBalance));

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
        Bundle bundle = getIntent().getExtras();
        SerializableMap serializableMap = (SerializableMap) bundle.getSerializable("map");
        map = serializableMap.getMap();
        activityId = bundle.getInt("activity_id");
        enrollId = bundle.getInt("enroll_id");
    }

    @OnClick({R.id.iv_header_back, R.id.tv_confirm_enroll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_confirm_enroll:
                if (useBalance > 0) {
                    isSetPayPwd();
                } else {
                    cashEnroll();
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

        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, (int) DisplayUtils.dp2px(this, 191));
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
                Intent intent = new Intent(ConfirmEnrollActivity.this, ValidateActivity.class);
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
                        orderBean = GsonUtils.jsonToBean(response, EnrollOrderBean.class);
                        if (orderBean.isFlag() && orderBean.getData() != null) {
                            if (useBalance == 0) {
                                payMoney();
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
    private void payMoney() {
        List<PayOrderBean.DiscountBean> discountBeans = new ArrayList<>();
        discountBeans.add(new PayOrderBean.DiscountBean("订单金额", "¥" + DisplayUtils.decimalFormat(enrollFee)));
        discountBeans.add(new PayOrderBean.DiscountBean("余额抵扣", "-¥" + DisplayUtils.decimalFormat(useBalance)));

        List<PayOrderBean.OrderInfo> orderInfoList = new ArrayList<>();
        orderInfoList.add(new PayOrderBean.OrderInfo("订单号", orderBean.getData().getOrder().getTranNo()));
        MyApp.orderBean = new PayOrderBean(orderBean.getData().getOrder().getTranNo(),
                "ACTIVITY", "", false, enrollFee-useBalance, discountBeans, orderInfoList);
        startActivity(new Intent(ConfirmEnrollActivity.this, ConfirmPayActivity.class));
    }


    /**
     * 冻结余额
     */
    private void frozenMoney(String orderNo) {
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
                                    balancePay();
                                } else {
                                    payMoney();
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
    private void balancePay() {
        OkHttpUtils.get()
                .url(Constant.ENROLL_BALANCE_PAY)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("orderNo", orderBean.getData().getOrder().getTranNo())
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
