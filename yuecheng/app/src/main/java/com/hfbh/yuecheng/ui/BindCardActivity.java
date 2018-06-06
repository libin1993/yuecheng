package com.hfbh.yuecheng.ui;

import android.os.Bundle;
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

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/1 16:06
 * Email：1993911441@qq.com
 * Describe：绑定预付卡
 */
public class BindCardActivity extends BaseActivity {
    @BindView(R.id.iv_bind_card_back)
    ImageView ivBindCardBack;
    @BindView(R.id.et_bind_card_number)
    EditText etBindCardNumber;
    @BindView(R.id.et_bind_card_pwd)
    EditText etBindCardPwd;
    @BindView(R.id.tv_bind_pay_card)
    TextView tvBindPayCard;

    //是否输入账号
    private boolean isCode;
    //是否输入密码
    private boolean isPwd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_card);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        etBindCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etBindCardNumber.getText().toString().trim())) {
                    isCode = true;
                    if (isPwd) {
                        tvBindPayCard.setEnabled(true);
                    } else {
                        tvBindPayCard.setEnabled(false);
                    }
                } else {
                    isCode = false;
                    tvBindPayCard.setEnabled(false);
                }
            }
        });

        etBindCardPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etBindCardPwd.getText().toString().trim())) {
                    isPwd = true;
                    if (isCode) {
                        tvBindPayCard.setEnabled(true);
                    } else {
                        tvBindPayCard.setEnabled(false);
                    }
                } else {
                    isPwd = false;
                    tvBindPayCard.setEnabled(false);
                }

            }
        });
    }

    @OnClick({R.id.iv_bind_card_back, R.id.tv_bind_pay_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_bind_card_back:
                finish();
                break;
            case R.id.tv_bind_pay_card:
                bindCard();
                break;
        }
    }

    /**
     * 绑定预付卡
     */
    private void bindCard() {
        OkHttpUtils.post()
                .url(Constant.BIND_PAY_CARD)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("prePaidCardAccount", etBindCardNumber.getText().toString().trim())
                .addParams("prePaidCardPassword", etBindCardPwd.getText().toString().trim())
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                        if (responseBean.isFlag()) {
                            ToastUtils.showToast(BindCardActivity.this, "绑定成功");
                            EventBus.getDefault().post("bind_success");
                            finish();
                        } else {
                            ToastUtils.showToast(BindCardActivity.this, "绑定失败");
                        }
                    }
                });
    }
}
