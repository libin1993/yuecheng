package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Libin on 2018/7/17 14:51
 * Email：1993911441@qq.com
 * Describe：支付结果
 */
public class PayResultActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.iv_pay_result)
    ImageView ivPayResult;
    @BindView(R.id.tv_pay_result)
    TextView tvPayResult;
    @BindView(R.id.tv_order_total)
    TextView tvOrderTotal;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.tv_order_pay_type)
    TextView tvOrderPayType;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_back_homepage)
    TextView tvBackHomepage;
    @BindView(R.id.tv_join_activity)
    TextView tvJoinActivity;
    @BindView(R.id.tv_order_tips)
    TextView tvOrderTips;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_header_back, R.id.tv_back_homepage, R.id.tv_join_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_back_homepage:
                break;
            case R.id.tv_join_activity:
                break;
        }
    }
}
