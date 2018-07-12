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
import com.hfbh.yuecheng.utils.LogUtils;
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
 * Author：Libin on 2018/5/29 09:28
 * Email：1993911441@qq.com
 * Describe：忘记密码
 */
public class ForgetPwdActivity extends BaseActivity {
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
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvRegisterTitle.setText("忘记密码");
        etRegisterPwd.setHint("设置新密码");
        tvRegister.setText("确定");

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
                    if (isCode && isPwd) {
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
                    if (isPhone && isPwd) {
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
                    if (isPhone && isCode) {
                        tvRegister.setEnabled(true);
                    } else {
                        tvRegister.setEnabled(false);
                    }
                } else {
                    isPwd = false;
                    tvRegister.setEnabled(false);
                }

            }
        });
    }


    private MyHandler mHandler = new MyHandler(this);


    private static class MyHandler extends Handler {
        private WeakReference<ForgetPwdActivity> mActivity;

        private MyHandler(ForgetPwdActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ForgetPwdActivity activity = mActivity.get();
            if (activity != null) {
                if (msg.what == 1) {
                    activity.tvRegisterCode.setText(msg.arg1 + "s后重发");

                    activity.tvRegisterCode.setEnabled(false);
                } else if (msg.what == 2) {
                    activity.tvRegisterCode.setText("获取验证码");
                    activity.tvRegisterCode.setEnabled(true);
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
                isRegister(2);
                break;
        }
    }

    /**
     * 修改密码
     */
    private void updatePwd() {
        String phone = etRegisterPhone.getText().toString().trim();
        String pwd = etRegisterPwd.getText().toString().trim();
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
                    .addParams("vircode", etRegisterCode.getText().toString().trim())
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
                                ToastUtils.showToast(ForgetPwdActivity.this, "修改成功");
                                finish();
                            } else {
                                ToastUtils.showToast(ForgetPwdActivity.this, "修改失败");
                                if (responseBean.getCode() == 4002) {
                                    SharedPreUtils.deleteStr(ForgetPwdActivity.this, "is_login");
                                }
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
        if (!TextUtils.isEmpty(etRegisterPhone.getText().toString().trim())) {
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
                            //是否已注册 false已注册 true未注册
                            if (flag) {
                                toRegister();
                            } else {
                                if (type == 1) {
                                    sendPhoneNumber();
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
        AlertDialog.Builder dialog = new AlertDialog.Builder(ForgetPwdActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("提示");
        dialog.setMessage("该手机号尚未绑定，请先去绑定");
        //为“确定”按钮注册监听事件
        dialog.setPositiveButton("去绑定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
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
                .addParams("code", SharedPreUtils.getStr(this, "hash"))
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token",SharedPreUtils.getStr(this, "token"))
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
                            if (jsonObject.getBoolean("flag")){
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
                            }
                            String msg = jsonObject.getString("msg");

                            ToastUtils.showToast(ForgetPwdActivity.this, msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
