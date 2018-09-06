package com.hfbh.yuecheng.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.jungly.gridpasswordview.GridPasswordView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.mob.MobSDK.getContext;

/**
 * Author：Libin on 2018/5/31 10:55
 * Email：1993911441@qq.com
 * Describe：重置支付密码
 */
public class ResetPayPwdActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_validate_title)
    TextView tvTitle;
    @BindView(R.id.et_validate_pwd)
    GridPasswordView etValidatePwd;
    @BindView(R.id.tv_validate_forget_pwd)
    TextView tvForgetPwd;
    //validate:密码验证   reset:验证码验证   bind:绑定
    private String type;
    //输入次数
    private int count;
    //验证密码
    private String validatePwd;
    //新密码
    private String newPwd;
    //第二次密码
    private String secPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pay_pwd);
        ButterKnife.bind(this);
        getData();
        initView();
    }

    private void initView() {
        switch (type) {
            case "validate":
                switch (count) {
                    case 0:
                        etValidatePwd.clearPassword();
                        tvHeaderTitle.setText("验证余额支付密码");
                        tvTitle.setText("请输入支付密码");
                        tvForgetPwd.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        etValidatePwd.clearPassword();
                        tvHeaderTitle.setText("设置余额支付密码");
                        tvTitle.setText("请输入新的支付密码");
                        tvForgetPwd.setVisibility(View.GONE);
                        break;
                    case 2:
                        etValidatePwd.clearPassword();
                        tvHeaderTitle.setText("确认余额支付密码");
                        tvTitle.setText("请再次输入新的支付密码");
                        tvForgetPwd.setVisibility(View.GONE);
                        break;
                }
                break;
            case "reset":
            case "bind":
                switch (count) {
                    case 0:
                        etValidatePwd.clearPassword();
                        tvHeaderTitle.setText("设置余额支付密码");
                        tvTitle.setText("请输入新的支付密码");
                        tvForgetPwd.setVisibility(View.GONE);
                        break;
                    case 1:
                        etValidatePwd.clearPassword();
                        tvHeaderTitle.setText("确认余额支付密码");
                        tvTitle.setText("请再次输入新的支付密码");
                        tvForgetPwd.setVisibility(View.GONE);
                        break;
                }
                break;
        }

    }

    private void getData() {
        type = getIntent().getStringExtra("type");

        etValidatePwd.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String s) {

            }

            @Override
            public void onInputFinish(String s) {
                switch (type) {
                    case "validate":
                        switch (count) {
                            case 0:
                                validatePwd = s;
                                validatePwd();
                                break;
                            case 1:
                                newPwd = s;
                                count++;
                                initView();
                                break;
                            case 2:
                                secPwd = s;
                                resetPwd(Constant.RESET_PAY_PWD);
                                break;
                        }
                        break;
                    case "reset":
                        switch (count) {
                            case 0:
                                newPwd = s;
                                count++;
                                initView();
                                break;
                            case 1:
                                secPwd = s;
                                resetPwd(Constant.RESET_PAY_PWD);
                                break;
                        }
                        break;
                    case "bind":
                        switch (count) {
                            case 0:
                                newPwd = s;
                                count++;
                                initView();
                                break;
                            case 1:
                                secPwd = s;
                                resetPwd(Constant.BIND_PAY_PWD);
                                break;
                        }
                        break;
                }
            }
        });
    }

    /**
     * 重置密码
     */
    private void resetPwd(String url) {
        if (newPwd.equals(secPwd)) {

            OkHttpUtils.post()
                    .url(url)
                    .addParams("appType", MyApp.appType)
                    .addParams("appVersion", MyApp.appVersion)
                    .addParams("organizeId", MyApp.organizeId)
                    .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                    .addParams("token", SharedPreUtils.getStr(this, "token"))
                    .addParams("payPassword", newPwd)
                    .addParams("confirmPayPassword", secPwd)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                            if (responseBean.isFlag()) {
                                ToastUtils.showToast(ResetPayPwdActivity.this, "密码设置成功");
                                finish();
                            } else {
                                ToastUtils.showToast(ResetPayPwdActivity.this, responseBean.getMsg());
                                if (responseBean.getCode() == 4002) {
                                    SharedPreUtils.deleteStr(ResetPayPwdActivity.this, "is_login");
                                }
                            }
                        }
                    });
        } else {
            ToastUtils.showToast(this, "两次输入密码不一致");
        }

    }

    /**
     * 密码验证
     */
    private void validatePwd() {
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
                            count++;
                            initView();
                        } else {
                            ToastUtils.showToast(ResetPayPwdActivity.this, responseBean.getMsg());
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(ResetPayPwdActivity.this, "is_login");
                            }
                        }
                    }
                });


    }

    @OnClick({R.id.iv_header_back, R.id.tv_validate_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                if (count < 1) {
                    finish();
                } else {
                    count--;
                    initView();
                }
                break;
            case R.id.tv_validate_forget_pwd:
                Intent intent = new Intent(ResetPayPwdActivity.this, ValidateActivity.class);
                intent.putExtra("type", "reset");
                startActivity(intent);
                finish();
                break;
        }
    }
}
