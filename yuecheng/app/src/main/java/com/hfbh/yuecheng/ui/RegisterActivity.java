package com.hfbh.yuecheng.ui;

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
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.PhoneNumberUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/28 11:17
 * Email：1993911441@qq.com
 * Describe：注册
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.tv_register_title)
    TextView tvRegisterTitle;
    @BindView(R.id.tv_register_close)
    ImageView tvRegisterClose;
    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @BindView(R.id.et_register_code)
    EditText etRegisterCode;
    @BindView(R.id.tv_register_code)
    TextView tvRegisterCode;
    @BindView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @BindView(R.id.iv_register_code)
    ImageView ivRegisterCode;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.checkbox_register)
    CheckBox checkbox;


    //是否输入手机号
    private boolean isPhone;
    //是否输入验证码
    private boolean isCode;
    //是否输入密码
    private boolean isPwd;
    private boolean isShow;

    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getData();
        initView();
    }

    private void getData() {
        type = getIntent().getStringExtra("type");
    }


    private void initView() {

        if ("register".equals(type)) {
            tvRegisterTitle.setText("注册");
            etRegisterPwd.setHint("设置密码");
            tvRegister.setText("注册");
            checkbox.setVisibility(View.VISIBLE);
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked && isPhone && isCode && isPwd) {
                        tvRegister.setEnabled(true);
                    } else {
                        tvRegister.setEnabled(false);
                    }
                }
            });
        } else if ("forget".equals(type)) {
            tvRegisterTitle.setText("忘记密码");
            etRegisterPwd.setHint("设置新密码");
            tvRegister.setText("确定");
        }

        etRegisterPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

        etRegisterPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (PhoneNumberUtils.judgePhoneNumber(etRegisterPhone.getText().toString().trim())) {
                    isPhone = true;
                    if (isCode && isPwd && checkbox.isChecked()) {
                        tvRegister.setEnabled(true);
                    } else {
                        tvRegister.setEnabled(false);
                    }
                } else {
                    isPhone = false;
                    tvRegister.setEnabled(false);
                }
            }
        });

        etRegisterCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etRegisterCode.getText().toString().trim())) {
                    isCode = true;
                    if (isPhone && isPwd && checkbox.isChecked()) {
                        tvRegister.setEnabled(true);
                    } else {
                        tvRegister.setEnabled(false);
                    }

                } else {
                    isCode = false;
                    tvRegister.setEnabled(false);
                }

            }
        });

        etRegisterPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etRegisterPwd.getText().toString().trim())) {
                    isPwd = true;
                    if (isPhone && isCode && checkbox.isChecked()) {
                        tvRegister.setEnabled(true);
                    } else {
                        tvRegister.setEnabled(false);
                    }
                } else {
                    isCode = false;
                    tvRegister.setEnabled(false);
                }

            }
        });


    }

    private MyHandler mHandler = new MyHandler(this);


    private static class MyHandler extends Handler {
        private WeakReference<RegisterActivity> mActivity;

        private MyHandler(RegisterActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            RegisterActivity activity = mActivity.get();
            if (activity != null) {
                if (msg.what == 1) {
                    activity.tvRegisterCode.setText(msg.arg1 + "s后重发");
                    activity.tvRegisterCode.setTextColor(0xff999999);
                } else if (msg.what == 2) {
                    activity.tvRegisterCode.setText("获取验证码");
                    activity.tvRegisterCode.setClickable(true);
                    activity.tvRegisterCode.setTextColor(0xff990000);
                }
            }
        }
    }

    @OnClick({R.id.tv_register_close, R.id.tv_register_code, R.id.iv_register_code, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register_close:
                finish();
                break;
            case R.id.tv_register_code:
                getVerificationCode();
                break;
            case R.id.iv_register_code:
                if (isShow) {
                    ivRegisterCode.setImageResource(R.mipmap.btn_signin_invisiable);
                    //隐藏密码
                    etRegisterPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShow = false;
                } else {
                    ivRegisterCode.setImageResource(R.mipmap.btn_signin_visiable);
                    //显示密码
                    etRegisterPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isShow = true;
                }

                if (!TextUtils.isEmpty(etRegisterPwd.getText().toString().trim())) {
                    etRegisterPwd.setSelection(etRegisterPwd.getText().toString().length());
                }
                break;
            case R.id.tv_register:
                register();
                break;
        }
    }

    /**
     * 注册
     */
    private void register() {
        OkHttpUtils.post()
                .url(Constant.REGISTER)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("memberPhone", etRegisterPhone.getText().toString().trim())
                .addParams("vircode", etRegisterCode.getText().toString().trim())
                .addParams("isValidate", "N")
                .addParams("memberPwd", etRegisterPwd.getText().toString().trim())
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
                            ToastUtils.showToast(RegisterActivity.this, msg);
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
        if (PhoneNumberUtils.judgePhoneNumber(etRegisterPhone.getText().toString().trim())) {
            tvRegister.setClickable(false);
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
                .addParams("memberPhone", etRegisterPhone.getText().toString().trim())
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
                            ToastUtils.showToast(RegisterActivity.this, msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
