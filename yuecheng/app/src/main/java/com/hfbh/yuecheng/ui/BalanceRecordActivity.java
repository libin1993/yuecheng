package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.BalanceRecordBean;
import com.hfbh.yuecheng.bean.MemberBalanceBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
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
    @BindView(R.id.layout_refresh_pay_card)
    SmartRefreshLayout refreshLayout;

    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    private List<BalanceRecordBean.DataBean> dataList = new ArrayList<>();
    //总页数
    private int pages;
    //活动总数量
    private CommonAdapter<BalanceRecordBean.DataBean> adapter;
    private int accountId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_card);
        ButterKnife.bind(this);
        tvPayCardTitle.setText("余额明细");
        getData();
        initData();
    }

    private void getData() {
        accountId = getIntent().getIntExtra("account_id", 0);
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
                .addParams("id", String.valueOf(accountId))
                .addParams("pageNum", String.valueOf(page))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        BalanceRecordBean balanceBean = GsonUtils.jsonToBean(s, BalanceRecordBean.class);
                        if (balanceBean.getPage() != null) {
                            pages = balanceBean.getPage().getPages();
                        }

                        if (balanceBean.isFlag() && balanceBean.getData() != null && balanceBean.getData().size() > 0) {
                            if (isRefresh) {
                                dataList.clear();
                            }
                            dataList.addAll(balanceBean.getData());

                            if (isRefresh) {
                                refreshLayout.finishRefresh();
                                isRefresh = false;
                                adapter.notifyDataSetChanged();
                            } else if (isLoadMore) {
                                refreshLayout.finishLoadMore();
                                isLoadMore = false;
                                adapter.notifyDataSetChanged();
                            } else {
                                initView();
                            }
                        } else {
                            refreshLayout.finishLoadMore();
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
                this, R.layout.rv_member_points_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, BalanceRecordBean.DataBean dataBean, int position) {
                holder.setText(R.id.tv_points_remark, dataBean.getRemark());
                holder.setText(R.id.tv_points_time, dataBean.getCreateTime());
                TextView tvPoints = holder.getView(R.id.tv_points_type);
                if (dataBean.getType() != null) {
                    if (dataBean.getType().equals("INCREASE")) {
                        tvPoints.setTextColor(getResources().getColor(R.color.red_99));
                        tvPoints.setText("+" + dataBean.getChangeAmount());
                    } else {
                        tvPoints.setTextColor(getResources().getColor(R.color.gray_10));
                        tvPoints.setText("-" + dataBean.getChangeAmount());
                    }
                }
            }
        };
        rvBalance.setAdapter(adapter);

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (page < pages) {
                    isLoadMore = true;
                    page++;
                    initData();
                } else {
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                page = 1;
                initData();
            }
        });

    }


    @OnClick(R.id.iv_pay_card_back)
    public void onViewClicked() {
        finish();
    }
}
