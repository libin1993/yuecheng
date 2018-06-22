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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.MD5Utils;
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
 * Author：Libin on 2018/5/30 15:05
 * Email：1993911441@qq.com
 * Describe：重置登录密码
 */
public class ResetLoginPwdActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.et_reset_phone)
    EditText etResetPhone;
    @BindView(R.id.et_reset_code)
    EditText etResetCode;
    @BindView(R.id.tv_reset_code)
    TextView tvResetCode;
    @BindView(R.id.et_reset_pwd)
    EditText etResetPwd;
    @BindView(R.id.iv_reset_pwd)
    ImageView ivResetPwd;
    @BindView(R.id.tv_reset_pwd)
    TextView tvResetPwd;

    //是否输入手机号
    private boolean isPhone;
    //是否输入验证码
    private boolean isCode;
    //是否输入密码
    private boolean isPwd;
    //是否显示密码
    private boolean isShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_login_pwd);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvHeaderTitle.setText("重置密码");
        etResetPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());

        etResetPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (PhoneNumberUtils.judgePhoneNumber(etResetPhone.getText().toString().trim())) {
                    isPhone = true;
                    if (isCode && isPwd) {
                        tvResetPwd.setEnabled(true);
                    } else {
                        tvResetPwd.setEnabled(false);
                    }
                } else {
                    isPhone = false;
                    tvResetPwd.setEnabled(false);
                }
            }
        });

        etResetPhone.setText(SharedPreUtils.getStr(this, "phone"));

        etResetCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etResetCode.getText().toString().trim())) {
                    isCode = true;
                    if (isPhone && isPwd) {
                        tvResetPwd.setEnabled(true);
                    } else {
                        tvResetPwd.setEnabled(false);
                    }

                } else {
                    isCode = false;
                    tvResetPwd.setEnabled(false);
                }

            }
        });

        etResetPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etResetPwd.getText().toString().trim())) {
                    isPwd = true;
                    if (isPhone && isCode) {
                        tvResetPwd.setEnabled(true);
                    } else {
                        tvResetPwd.setEnabled(false);
                    }
                } else {
                    isPwd = false;
                    tvResetPwd.setEnabled(false);
                }

            }
        });
    }


    @OnClick({R.id.iv_header_back, R.id.tv_reset_code, R.id.iv_reset_pwd, R.id.tv_reset_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_reset_code:
                getVerificationCode();
                break;
            case R.id.iv_reset_pwd:
                if (isShow) {
                    ivResetPwd.setImageResource(R.mipmap.btn_signin_invisiable);
                    //隐藏密码
                    etResetPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShow = false;
                } else {
                    ivResetPwd.setImageResource(R.mipmap.btn_signin_visiable);
                    //显示密码
                    etResetPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isShow = true;
                }

                if (!TextUtils.isEmpty(etResetPwd.getText().toString().trim())) {
                    etResetPwd.setSelection(etResetPwd.getText().toString().length());
                }
                break;
            case R.id.tv_reset_pwd:
                isRegister(2);
                break;
        }

    }


    private MyHandler mHandler = new MyHandler(this);


    private static class MyHandler extends Handler {
        private WeakReference<ResetLoginPwdActivity> mActivity;

        private MyHandler(ResetLoginPwdActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ResetLoginPwdActivity activity = mActivity.get();
            if (activity != null) {
                if (msg.what == 1) {
                    activity.tvResetCode.setText(msg.arg1 + "s后重发");
                    activity.tvResetCode.setEnabled(false);
                } else if (msg.what == 2) {
                    activity.tvResetCode.setText("获取验证码");
                    activity.tvResetCode.setEnabled(true);
                }
            }
        }
    }


    /**
     * 修改密码
     */
    private void updatePwd() {
        String phone = etResetPhone.getText().toString().trim();
        String pwd = etResetPwd.getText().toString().trim();
        if (pwd.length() >= 6 && pwd.length() <= 16 && pwd.matches(".*[a-zA-z].*")) {
            String md5 = MD5Utils.md5(pwd + phone.substring(7));
            OkHttpUtils.post()
                    .url(Constant.UPDATE_PWD)
                    .addParams("appType", MyApp.appType)
                    .addParams("appVersion", MyApp.appVersion)
                    .addParams("organizeId", MyApp.organizeId)
                    .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                    .addParams("token",SharedPreUtils.getStr(this, "token"))
                    .addParams("memberPhone", phone)
                    .addParams("vircode", etResetCode.getText().toString().trim())
                    .addParams("memberPwd", md5)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                            if (responseBean.isFlag()) {
                                ToastUtils.showToast(ResetLoginPwdActivity.this, "修改成功");
                                finish();
                            } else {
                                ToastUtils.showToast(ResetLoginPwdActivity.this, responseBean.getMsg());
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
        if (!TextUtils.isEmpty(etResetPhone.getText().toString().trim())) {
            if (isPhone) {
                isRegister(1);
            } else {
                ToastUtils.showToast(this, "手机号格式不正确，请重新输入");
            }
        } else {
            ToastUtils.showToast(this, "请输入手机号");
        }
    }

    /**
     * 检测手机号是否注册
     */
    private void isRegister(final int type) {
        OkHttpUtils.post()
                .url(Constant.IS_REGISTER)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token",SharedPreUtils.getStr(this, "token"))
                .addParams("memberPhone", etResetPhone.getText().toString().trim())
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
                            //是否已注册 false已注册 true未注册
                            if (flag) {
                                toRegister();
                            } else {
                                if (type == 1) {
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
                                } else if (type == 2) {
                                    updatePwd();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }

    /**
     * 手机号未注册，提示用户注册
     */
    private void toRegister() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("提示");
        dialog.setMessage("该手机号尚未绑定，请先去绑定");
        //为“确定”按钮注册监听事件
        dialog.setPositiveButton("去绑定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ResetLoginPwdActivity.this, LoginActivity.class));
            }
        });

        //为“取消”按钮注册监听事件
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();
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
                .addParams("token",SharedPreUtils.getStr(this, "token"))
                .addParams("memberPhone", etResetPhone.getText().toString().trim())
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

                            ToastUtils.showToast(ResetLoginPwdActivity.this, msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }



}
