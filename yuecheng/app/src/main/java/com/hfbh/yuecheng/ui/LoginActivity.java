package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.PhoneNumberUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/25 09:58
 * Email：1993911441@qq.com
 * Describe：登录
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_login_title)
    TextView tvLoginTitle;
    @BindView(R.id.iv_login_close)
    ImageView ivLoginClose;
    @BindView(R.id.rgs_login)
    RadioGroup rgsLogin;
    @BindView(R.id.et_login_phone)
    EditText etPhone;
    @BindView(R.id.et_login_code)
    EditText etCode;
    @BindView(R.id.tv_login_code)
    TextView tvLoginCode;
    @BindView(R.id.rl_login_code)
    RelativeLayout rlLoginCode;
    @BindView(R.id.iv_login_code)
    ImageView ivLoginCode;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_login_sign)
    TextView tvSign;
    @BindView(R.id.tv_wechat_login)
    RelativeLayout tvWechatLogin;

    //是否输入手机号
    private boolean isPhone;
    //是否输入验证码
    private boolean isCode;
    //0：验证码登录 1：密码登录
    private int loginType;
    private boolean isShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ((RadioButton) rgsLogin.getChildAt(0)).setChecked(true);

        rgsLogin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < rgsLogin.getChildCount(); i++) {
                    if (rgsLogin.getChildAt(i).getId() == checkedId) {
                        loginType = i;
                        loginType();
                        break;
                    }
                }
            }
        });

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (PhoneNumberUtils.judgePhoneNumber(etPhone.getText().toString().trim())) {
                    isPhone = true;
                    if (isCode) {
                        tvLogin.setEnabled(true);
                    } else {
                        tvLogin.setEnabled(false);
                    }
                } else {
                    isPhone = false;
                    tvLogin.setEnabled(false);
                }
            }
        });

        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(etCode.getText().toString().trim())) {
                    isCode = false;
                    tvLogin.setEnabled(false);
                } else {
                    isCode = true;
                    if (isPhone) {
                        tvLogin.setEnabled(true);
                    } else {
                        tvLogin.setEnabled(false);
                    }
                }

            }
        });
    }

    /**
     * 登录方式
     */
    private void loginType() {
        if (loginType == 0) {
            tvLoginTitle.setText("手机快捷登录");
            rlLoginCode.setVisibility(View.VISIBLE);
            tvForgetPassword.setVisibility(View.GONE);
            ivLoginCode.setVisibility(View.GONE);
            etCode.setText(null);
            etCode.setHint("输入验证码");
            etCode.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        } else {
            tvLoginTitle.setText("账号密码登录");
            rlLoginCode.setVisibility(View.GONE);
            tvForgetPassword.setVisibility(View.VISIBLE);
            ivLoginCode.setVisibility(View.VISIBLE);
            etCode.setText(null);
            etCode.setHint("输入密码");
            isShow = false;
            etCode.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ivLoginCode.setImageResource(R.mipmap.btn_signin_invisiable);

        }
    }

    private MyHandler mHandler = new MyHandler(this);


    private static class MyHandler extends Handler {
        private WeakReference<LoginActivity> mActivity;

        private MyHandler(LoginActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LoginActivity activity = mActivity.get();
            if (activity != null) {
                if (msg.what == 1) {
                    activity.tvLoginCode.setText(msg.arg1 + "s后重发");
                    activity.tvLoginCode.setTextColor(0xff999999);
                } else if (msg.what == 2) {
                    activity.tvLoginCode.setText("获取验证码");
                    activity.tvLoginCode.setClickable(true);
                    activity.tvLoginCode.setTextColor(0xff990000);
                }
            }
        }
    }


    @OnClick({R.id.iv_login_close, R.id.tv_login_code, R.id.iv_login_code, R.id.tv_forget_password,
            R.id.tv_login, R.id.tv_login_sign, R.id.tv_wechat_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_close:
                finish();
                break;
            case R.id.tv_login_code:
                getVerificationCode();
                break;
            case R.id.iv_login_code:
                if (isShow) {
                    ivLoginCode.setImageResource(R.mipmap.btn_signin_invisiable);
                    //隐藏密码
                    etCode.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShow = false;
                } else {
                    ivLoginCode.setImageResource(R.mipmap.btn_signin_visiable);
                    //显示密码
                    etCode.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isShow = true;
                }

                if (!TextUtils.isEmpty(etCode.getText().toString().trim())) {
                    etCode.setSelection(etCode.getText().toString().length());
                }
                break;
            case R.id.tv_forget_password:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("type", "forget");
                startActivity(intent);
                break;
            case R.id.tv_login:
                if (loginType == 0) {
                    codeLogin();
                } else {
                    pwdLogin();
                }
                break;
            case R.id.tv_login_sign:
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                intent1.putExtra("type", "register");
                startActivity(intent1);
                break;
            case R.id.tv_wechat_login:
                break;
        }
    }

    /**
     * 验证码登录
     */
    private void codeLogin() {
        Map<String, String> map = new HashMap<>();
        map.put("appType", MyApp.appType);
        map.put("appVersion", MyApp.appVersion);
        map.put("organizeId", MyApp.organizeId);
        map.put("hash", SharedPreUtils.getStr(this, "hash"));
        map.put("memberPhone", etPhone.getText().toString().trim());
        map.put("vircode", etCode.getText().toString().trim());

        OkHttpUtils.post()
                .url(Constant.CODE_LOGIN)
                .params(map)
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

                            }
                            ToastUtils.showToast(LoginActivity.this, msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }


    /**
     * 密码登录
     */
    private void pwdLogin() {

        Map<String, String> map = new HashMap<>();
        map.put("appType", MyApp.appType);
        map.put("appVersion", MyApp.appVersion);
        map.put("organizeId", MyApp.organizeId);
        map.put("hash", SharedPreUtils.getStr(this, "hash"));
        map.put("memberPhone", etPhone.getText().toString().trim());
        map.put("memberPwd", etCode.getText().toString().trim());

        OkHttpUtils.post()
                .url(Constant.PWD_LOGIN)
                .params(map)
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
                            String msg = jsonObject.getString("msg");
                            if (flag) {

                            }
                            ToastUtils.showToast(LoginActivity.this, msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }

    /**
     * 获取验证码
     */
    private void getVerificationCode() {

        if (PhoneNumberUtils.judgePhoneNumber(etPhone.getText().toString().trim())) {
            tvLoginCode.setClickable(false);
            sendPhoneNumber();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 60; i > 0; i--) {
                        Message msg = new Message();
                        msg.what = 1;
                        msg.arg1 = i;
                        mHandler.sendMessage(msg);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mHandler.sendEmptyMessage(2);
                }
            }).start();
        } else {
            ToastUtils.showToast(this, "手机号输入有误，请重新输入");
        }
    }


    /**
     * 验证码
     */
    private void sendPhoneNumber() {
        OkHttpUtils.post()
                .url(Constant.SECURITY_CODE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("memberPhone", etPhone.getText().toString().trim())
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }
}
