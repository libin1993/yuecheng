package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/30 14:57
 * Email：1993911441@qq.com
 * Describe：密码设置
 */
public class SetPwdActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.ll_reset_login_pwd)
    LinearLayout llResetLoginPwd;
    @BindView(R.id.ll_reset_pay_pwd)
    LinearLayout llResetPayPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pwd);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("密码设置");
    }

    @OnClick({R.id.iv_header_back, R.id.ll_reset_login_pwd, R.id.ll_reset_pay_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.ll_reset_login_pwd:
                startActivity(new Intent(this, ResetLoginPwdActivity.class));
                break;
            case R.id.ll_reset_pay_pwd:
                isSetPayPwd();
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
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                        Intent intent;
                        if (responseBean.isFlag()) {
                            intent = new Intent(SetPwdActivity.this, ResetPayPwdActivity.class);
                            intent.putExtra("type", "validate");
                        } else {
                            intent = new Intent(SetPwdActivity.this, ValidateActivity.class);
                            intent.putExtra("type", "bind");
                        }
                        startActivity(intent);

                    }
                });
    }
}
