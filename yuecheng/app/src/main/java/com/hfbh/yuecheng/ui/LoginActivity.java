package com.hfbh.yuecheng.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.UserInfoBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.MD5Utils;
import com.hfbh.yuecheng.utils.PhoneNumberUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
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
    @BindView(R.id.tv_wechat_login)
    RelativeLayout tvWechatLogin;
    @BindView(R.id.checkbox_login)
    CheckBox checkboxLogin;
    @BindView(R.id.tv_login_agreement)
    TextView tvLoginAgreement;

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
        EventBus.getDefault().register(this);
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

        checkboxLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && isPhone && isCode) {
                    tvLogin.setEnabled(true);
                } else {
                    tvLogin.setEnabled(false);
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
                    if (isCode && checkboxLogin.isChecked()) {
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
                    if (isPhone && checkboxLogin.isChecked()) {
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
                    activity.tvLoginCode.setEnabled(false);
                } else if (msg.what == 2) {
                    activity.tvLoginCode.setText("获取验证码");
                    activity.tvLoginCode.setEnabled(true);
                }
            }
        }
    }


    @OnClick({R.id.iv_login_close, R.id.tv_login_code, R.id.iv_login_code, R.id.tv_forget_password,
            R.id.tv_login, R.id.tv_wechat_login, R.id.tv_login_agreement})
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
                Intent intent = new Intent(LoginActivity.this, ForgetPwdActivity.class);
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
            case R.id.tv_wechat_login:
                break;
            case R.id.tv_login_agreement:
                startActivity(new Intent(this, UserAgreementActivity.class));
                break;
        }
    }

//    /**
//     * 监测手机号是否注册
//     */
//    private void isRegister(final int type) {
//        OkHttpUtils.post()
//                .url(Constant.IS_REGISTER)
//                .addParams("appType", MyApp.appType)
//                .addParams("appVersion", MyApp.appVersion)
//                .addParams("organizeId", MyApp.organizeId)
//                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
//                .addParams("memberPhone", etPhone.getText().toString().trim())
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            boolean flag = jsonObject.getBoolean("flag");
//                            //是否已注册 false已注册 true未注册
//                            if (flag) {
//                                toRegister();
//                            } else {
//                                if (type == 1) {
//                                    sendPhoneNumber();
//                                    new Thread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            for (int i = 60; i > 0; i--) {
//                                                Message msg = new Message();
//                                                msg.what = 1;
//                                                msg.arg1 = i;
//                                                mHandler.sendMessage(msg);
//                                                try {
//                                                    Thread.sleep(1000);
//                                                } catch (InterruptedException e) {
//                                                    e.printStackTrace();
//                                                }
//                                            }
//                                            mHandler.sendEmptyMessage(2);
//                                        }
//                                    }).start();
//                                } else if (type == 2) {
//                                    if (loginType == 0) {
//                                        codeLogin();
//                                    } else {
//                                        pwdLogin();
//                                    }
//                                }
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//
//    }

//    /**
//     * 手机号未注册，提示用户注册
//     */
//    private void toRegister() {
//        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this,
//                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
//        dialog.setTitle("提示");
//        dialog.setMessage("该手机号尚未注册，请先去注册");
//        //为“确定”按钮注册监听事件
//        dialog.setPositiveButton("去注册", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//            }
//        });
//
//        //为“取消”按钮注册监听事件
//        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        dialog.create();
//        dialog.show();
//    }

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
                        UserInfoBean userInfoBean = GsonUtils.jsonToBean(response, UserInfoBean.class);
                        if (userInfoBean.isFlag()) {
                            ToastUtils.showToast(LoginActivity.this, "登录成功");
                            SharedPreUtils.saveStr(LoginActivity.this, "hash", userInfoBean.getHash());
                            SharedPreUtils.saveStr(LoginActivity.this, "token", userInfoBean.getToken());
                            SharedPreUtils.saveStr(LoginActivity.this, "member_id",
                                    String.valueOf(userInfoBean.getData().getMemberId()));
                            SharedPreUtils.saveBoolean(LoginActivity.this, "is_login", true);
                            SharedPreUtils.saveStr(LoginActivity.this, "phone",
                                    String.valueOf(userInfoBean.getData().getMemberPhone()));
                            SharedPreUtils.saveStr(LoginActivity.this, "avatar",
                                    userInfoBean.getData().getMemberHead());
                            SharedPreUtils.saveStr(LoginActivity.this, "card_number",
                                    userInfoBean.getData().getMemberCardNo());
                            SharedPreUtils.saveStr(LoginActivity.this, "member_card",
                                    userInfoBean.getData().getCardLevel());

                            EventBus.getDefault().post("login_success");
                            if (getIntent().getIntExtra("type",0) == 1){
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("change_market", true);
                                startActivity(intent);
                            }
                            finish();

                        } else {
                            ToastUtils.showToast(LoginActivity.this, userInfoBean.getMsg());
                        }
                    }
                });


    }


    /**
     * 密码登录
     */
    private void pwdLogin() {


        String phone = etPhone.getText().toString().trim();
        String pwd = etCode.getText().toString().trim();

        if (pwd.length() >= 6 && pwd.length() <= 16 && pwd.matches(".*[a-zA-z].*")) {
            final String md5 = MD5Utils.md5(pwd + phone.substring(7));

            Map<String, String> map = new HashMap<>();
            map.put("appType", MyApp.appType);
            map.put("appVersion", MyApp.appVersion);
            map.put("organizeId", MyApp.organizeId);
            map.put("hash", SharedPreUtils.getStr(this, "hash"));
            map.put("memberPhone", phone);
            map.put("memberPwd", md5);

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
                            UserInfoBean userInfoBean = GsonUtils.jsonToBean(response, UserInfoBean.class);
                            if (userInfoBean.isFlag()) {
                                ToastUtils.showToast(LoginActivity.this, "登录成功");
                                SharedPreUtils.saveStr(LoginActivity.this, "hash", userInfoBean.getHash());
                                SharedPreUtils.saveStr(LoginActivity.this, "token", userInfoBean.getToken());
                                SharedPreUtils.saveStr(LoginActivity.this, "member_id",
                                        String.valueOf(userInfoBean.getData().getMemberId()));
                                SharedPreUtils.saveBoolean(LoginActivity.this, "is_login", true);
                                SharedPreUtils.saveStr(LoginActivity.this, "phone",
                                        String.valueOf(userInfoBean.getData().getMemberPhone()));
                                SharedPreUtils.saveStr(LoginActivity.this, "avatar",
                                        userInfoBean.getData().getMemberHead());
                                SharedPreUtils.saveStr(LoginActivity.this, "card_number",
                                        userInfoBean.getData().getMemberCardNo());
                                SharedPreUtils.saveStr(LoginActivity.this, "member_card",
                                        userInfoBean.getData().getCardLevel());

                                EventBus.getDefault().post("login_success");
                                if (getIntent().getIntExtra("type",0) == 1){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("change_market", true);
                                    startActivity(intent);
                                }
                                finish();
                            } else {
                                ToastUtils.showToast(LoginActivity.this, userInfoBean.getMsg());
                            }
                        }
                    });
        } else {
            ToastUtils.showToast(this, "密码要求6-16位数字与字母组合");
        }


    }

    /**
     * 获取验证码
     */
    private void getVerificationCode() {
        if (!TextUtils.isEmpty(etPhone.getText().toString().trim())) {
            if (isPhone) {
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
                ToastUtils.showToast(this, "手机号格式不正确，请重新输入");
            }
        } else {
            ToastUtils.showToast(this, "请输入手机号");
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
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("memberPhone", etPhone.getText().toString().trim())
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String msg = jsonObject.getString("msg");
                            ToastUtils.showToast(LoginActivity.this, msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Subscribe
    public void isLogin(String msg) {
        if ("login_success".equals(msg)) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
