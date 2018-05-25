package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
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
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.utils.PhoneNumberUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    //是否输入验证码
    private boolean isPwd;
    private String logId;
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
                        initLayout();
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

    private void initLayout() {
        if (loginType == 0) {
            tvLoginTitle.setText("手机快捷登录");
            rlLoginCode.setVisibility(View.VISIBLE);
            tvForgetPassword.setVisibility(View.GONE);
            ivLoginCode.setVisibility(View.GONE);
            etCode.setText(null);
            etCode.setHint("输入验证码");
            etCode.setInputType(InputType.TYPE_CLASS_PHONE);
        } else {
            tvLoginTitle.setText("账号密码登录");
            rlLoginCode.setVisibility(View.GONE);
            tvForgetPassword.setVisibility(View.VISIBLE);
            ivLoginCode.setVisibility(View.VISIBLE);
            etCode.setText(null);
            etCode.setHint("输入密码");
            etCode.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etCode.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
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
                break;
            case R.id.iv_login_code:
                if (isShow) {
                    ivLoginCode.setImageResource(R.mipmap.btn_signin_invisiable);
                    //显示密码
                    etCode.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShow = false;
                } else {
                    ivLoginCode.setImageResource(R.mipmap.btn_signin_visiable);
                    //隐藏密码
                    etCode.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isShow = true;
                }
                break;
            case R.id.tv_forget_password:
                break;
            case R.id.tv_login:
                break;
            case R.id.tv_login_sign:
                break;
            case R.id.tv_wechat_login:
                break;
        }
    }
}
