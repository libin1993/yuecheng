package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.BalanceRecordBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/1 16:53
 * Email：1993911441@qq.com
 * Describe：余额明细
 */
public class BalanceRecordActivity extends BaseActivity {
    @BindView(R.id.tv_pay_card_title)
    TextView tvPayCardTitle;
    @BindView(R.id.iv_pay_card_back)
    ImageView ivPayCardBack;
    @BindView(R.id.rv_pay_card)
    RecyclerView rvBalance;
    @BindView(R.id.iv_null_data)
    ImageView ivNullData;
    @BindView(R.id.tv_null_data)
    TextView tvNullData;
    @BindView(R.id.ll_null_data)
    LinearLayout llNullData;
    @BindView(R.id.layout_refresh_pay_card)
    SmartRefreshLayout refreshLayout;

    private String accountId;
    private CommonAdapter<BalanceRecordBean.DataBean> adapter;
    private List<BalanceRecordBean.DataBean> balanceList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_card);
        ButterKnife.bind(this);
        tvPayCardTitle.setText("余额明细");
        llNullData.setVisibility(View.GONE);
        ivNullData.setImageResource(R.mipmap.ic_null_card);
        tvNullData.setText("暂无交易记录");
        getData();
        initView();
        initData();
    }

    private void getData() {
        accountId = getIntent().getStringExtra("account_id");
    }

    /**
     * 加载数据
     */
    private void initData() {

        OkHttpUtils.post()
                .url(Constant.BALANCE_RECORD)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("id", accountId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        BalanceRecordBean balanceBean = GsonUtils.jsonToBean(s, BalanceRecordBean.class);
                        if (balanceBean.isFlag() && balanceBean.getData() != null && balanceBean.getData().size() > 0) {
                            balanceList.addAll(balanceBean.getData());
                            adapter.notifyDataSetChanged();
                        } else {
                            llNullData.setVisibility(View.VISIBLE);
                            if (balanceBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(BalanceRecordActivity.this, "is_login");
                            }
                        }
                    }
                });
    }


    /**
     * 加载视图
     */
    private void initView() {
        rvBalance.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<BalanceRecordBean.DataBean>(
                this, R.layout.rv_member_points_item, balanceList) {
            @Override
            protected void convert(ViewHolder holder, BalanceRecordBean.DataBean dataBean, int position) {
                holder.setText(R.id.tv_points_time, dataBean.getProcTime());
                TextView tvTitle = holder.getView(R.id.tv_points_remark);
                TextView tvPoints = holder.getView(R.id.tv_points_type);

                if (!TextUtils.isEmpty(dataBean.getProcType())) {
                    switch (dataBean.getProcType()) {
                        case "建卡":
                        case "存款":
                            tvTitle.setText(dataBean.getProcType());
                            tvPoints.setTextColor(getResources().getColor(R.color.red_99));
                            tvPoints.setText("+" + dataBean.getDebitMoney());
                            break;
                        case "取款":
                            tvTitle.setText(dataBean.getProcType());
                            tvPoints.setTextColor(getResources().getColor(R.color.gray_10));
                            tvPoints.setText("-" + dataBean.getDebitMoney());
                            break;
                        case "消费":
                            if (dataBean.getCreditMoney().contains("-")) {
                                tvTitle.setText("退款");
                                tvPoints.setTextColor(getResources().getColor(R.color.red_99));
                                tvPoints.setText("+" + dataBean.getCreditMoney().substring(1));
                            } else {
                                tvTitle.setText("消费");
                                tvPoints.setTextColor(getResources().getColor(R.color.gray_10));
                                tvPoints.setText("-" + dataBean.getCreditMoney());
                            }
                            break;
                    }
                }
            }
        };
        rvBalance.setAdapter(adapter);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);

    }


    @OnClick(R.id.iv_pay_card_back)
    public void onViewClicked() {
        finish();
    }
}
