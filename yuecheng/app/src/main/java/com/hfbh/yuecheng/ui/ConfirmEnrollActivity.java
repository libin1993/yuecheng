package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityRecordBean;
import com.hfbh.yuecheng.bean.UserBalanceBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.InputMoneyFilter;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SerializableMap;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/7/12 17:06
 * Email：1993911441@qq.com
 * Describe：确认报名
 */
public class ConfirmEnrollActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_enroll_activity_name)
    TextView tvActivityName;
    @BindView(R.id.tv_enroll_activity_time)
    TextView tvActivityTime;
    @BindView(R.id.tv_enroll_activity_fee)
    TextView tvActivityFee;
    @BindView(R.id.tv_user_money)
    TextView tvUserMoney;
    @BindView(R.id.et_input_money)
    EditText etInputMoney;
    @BindView(R.id.tv_input_money)
    TextView tvInputMoney;
    @BindView(R.id.tv_enroll_money)
    TextView tvEnrollMoney;
    @BindView(R.id.tv_confirm_enroll)
    TextView tvConfirmEnroll;

    private Map<String, String> map;
    private int activityId;

    private ActivityRecordBean activityBean;
    private UserBalanceBean balanceBean;
    private int enrollId;
    private double balance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_enroll);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("确认报名");
        etInputMoney.setEnabled(false);
        getData();
        initData();
        initBalance();
    }

    private void initBalance() {
        OkHttpUtils.get()
                .url(Constant.USER_BALANCE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        balanceBean = GsonUtils.jsonToBean(response, UserBalanceBean.class);
                        if (balanceBean.isFlag() && balanceBean.getData() != null) {
                            initUserInfo();
                        }
                    }
                });
    }

    private void initUserInfo() {
        balance = balanceBean.getData().getAccountBalance();
        tvUserMoney.setText("¥" + DisplayUtils.decimalFormat(balance));
        etInputMoney.setEnabled(true);
        etInputMoney.setFilters(new InputFilter[]{new InputMoneyFilter(balance)});
        etInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    String money = DisplayUtils.isInteger(Double.parseDouble(s.toString()));
//                    etInputMoney.setText(money);
                    tvInputMoney.setText("¥" + money);
                } else {
                    tvInputMoney.setText("¥0.00");
                }
            }
        });

    }

    private void initData() {
        OkHttpUtils.get()
                .url(Constant.CASH_ENROLL_INFO)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("id", String.valueOf(enrollId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        activityBean = GsonUtils.jsonToBean(response, ActivityRecordBean.class);
                        if (activityBean.isFlag() && activityBean.getData() != null) {
                            initView();
                        }
                    }
                });
    }

    private void initView() {
        tvActivityName.setText(activityBean.getData().getActivity().getActivityTitle());
        tvActivityTime.setText(activityBean.getData().getRecord().getSignupTime());
        tvActivityFee.setText("¥" + activityBean.getData().getActivity().getEnrollFee());
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        SerializableMap serializableMap = (SerializableMap) bundle.getSerializable("map");
        map = serializableMap.getMap();
        activityId = bundle.getInt("activity_id");
        enrollId = bundle.getInt("enroll_id");
    }

    @OnClick({R.id.iv_header_back, R.id.tv_confirm_enroll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_confirm_enroll:
                break;
        }
    }
}
