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
 * Author：Libin on 2018/5/31 12:20
 * Email：1993911441@qq.com
 * Describe：身份验证
 */
public class ValidateActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.et_validate_phone)
    EditText etValidatePhone;
    @BindView(R.id.et_validate_code)
    EditText etValidateCode;
    @BindView(R.id.tv_validate_code)
    TextView tvValidateCode;
    @BindView(R.id.tv_validate_next)
    TextView tvValidateNext;
    @BindView(R.id.tv_forget_phone)
    TextView tvForgetPhone;

    //是否输入手机号
    private boolean isPhone;
    //是否输入验证码
    private boolean isCode;
    //validate:密码验证   reset:验证码验证   bind:绑定
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);
        ButterKnife.bind(this);
        getData();
        initView();
    }

    private void getData() {
        type = getIntent().getStringExtra("type");
    }

    private void initView() {
        tvHeaderTitle.setText("身份验证");

        etValidatePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (PhoneNumberUtils.judgePhoneNumber(etValidatePhone.getText().toString().trim())) {
                    isPhone = true;
                    if (isCode) {
                        tvValidateNext.setEnabled(true);
                    } else {
                        tvValidateNext.setEnabled(false);
                    }
                } else {
                    isPhone = false;
                    tvValidateNext.setEnabled(false);
                }
            }
        });
        etValidatePhone.setText(SharedPreUtils.getStr(this, "phone"));


        etValidateCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(etValidateCode.getText().toString().trim())) {
                    isCode = false;
                    tvValidateNext.setEnabled(false);
                } else {
                    isCode = true;
                    if (isPhone) {
                        tvValidateNext.setEnabled(true);
                    } else {
                        tvValidateNext.setEnabled(false);
                    }
                }

            }
        });
    }

    private MyHandler mHandler = new MyHandler(this);


    private static class MyHandler extends Handler {
        private WeakReference<ValidateActivity> mActivity;

        private MyHandler(ValidateActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ValidateActivity activity = mActivity.get();
            if (activity != null) {
                if (msg.what == 1) {
                    activity.tvValidateCode.setText(msg.arg1 + "s后重发");
                    activity.tvValidateCode.setEnabled(false);
                } else if (msg.what == 2) {
                    activity.tvValidateCode.setText("获取验证码");
                    activity.tvValidateCode.setEnabled(true);
                }
            }
        }
    }


    @OnClick({R.id.iv_header_back, R.id.tv_validate_code, R.id.tv_validate_next, R.id.tv_forget_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_validate_code:
                getVerificationCode();
                break;
            case R.id.tv_validate_next:
                isRegister(2);
                break;
            case R.id.tv_forget_phone:
                forgetPhone();
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getVerificationCode() {
        if (!TextUtils.isEmpty(etValidatePhone.getText().toString().trim())) {
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
                .addParams("memberPhone", etValidatePhone.getText().toString().trim())
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
                                    validatePhone();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 身份验证
     */
    private void validatePhone() {
        OkHttpUtils.post()
                .url(Constant.VALIDATE_PHONE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token",SharedPreUtils.getStr(this, "token"))
                .addParams("memberPhone", etValidatePhone.getText().toString().trim())
                .addParams("vircode", etValidateCode.getText().toString().trim())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        ResponseBean responseBean = GsonUtils.jsonToBean(s, ResponseBean.class);
                        if (responseBean.isFlag()) {
                            Intent intent = new Intent(ValidateActivity.this, ResetPayPwdActivity.class);
                            intent.putExtra("type", type);
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtils.showToast(ValidateActivity.this, responseBean.getMsg());
                        }
                    }
                });
    }

    /**
     * 手机号未注册，提示用户注册
     */
    private void toRegister() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ValidateActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("提示");
        dialog.setMessage("该手机号尚未绑定，请先去绑定");
        //为“确定”按钮注册监听事件
        dialog.setPositiveButton("去绑定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ValidateActivity.this, LoginActivity.class));
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
                .addParams("memberPhone", etValidatePhone.getText().toString().trim())
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
                            ToastUtils.showToast(ValidateActivity.this, msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 已更换手机号
     */
    private void forgetPhone() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("提示");
        dialog.setMessage("为保证您的账户资金安全，请持本人身份证到服务台修改会员手机号");

        //为“取消”按钮注册监听事件
        dialog.setNegativeButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();
    }
}
