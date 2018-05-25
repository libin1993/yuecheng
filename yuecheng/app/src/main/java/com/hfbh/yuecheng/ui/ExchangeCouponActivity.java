package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.BaseDelegateAdapter;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.GiftListBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.view.GridItemDecoration1;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wang.avi.AVLoadingIndicatorView;
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
 * Author：Libin on 2018/5/25 13:39
 * Email：1993911441@qq.com
 * Describe：领取优惠券
 */
public class ExchangeCouponActivity extends BaseActivity {
    @BindView(R.id.tv_title_header)
    TextView tvTitleHeader;
    @BindView(R.id.iv_back_header)
    ImageView ivBackHeader;
    @BindView(R.id.et_search_coupon)
    EditText etSearchCoupon;
    @BindView(R.id.rv_exchange_coupon)
    RecyclerView rvExchangeCoupon;
    @BindView(R.id.layout_refresh_exchange_coupon)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;

    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    private List<GiftListBean.DataBean> dataList = new ArrayList<>();
    //总页数
    private int pages;
    private CommonAdapter<GiftListBean.DataBean> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_coupon);
        ButterKnife.bind(this);
        tvTitleHeader.setText("优惠券");
        loadingView.smoothToShow();

        initData();

    }


    private void initData() {
        OkHttpUtils.get()
                .url(Constant.EXCHANGE_GIFT_LIST)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("pageNum", String.valueOf(page))
                .addParams("pointsRewardType", "COUPON")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        GiftListBean ListBean = GsonUtils.jsonToBean(response, GiftListBean.class);
                        pages = ListBean.getPage().getPages();
                        if (ListBean.isFlag() && ListBean.getData() != null && ListBean.getData().size() > 0) {
                            if (isRefresh) {
                                dataList.clear();
                            }
                            dataList.addAll(ListBean.getData());

                            if (isRefresh) {
                                refreshLayout.finishRefresh();
                                isRefresh = false;
                                adapter.notifyDataSetChanged();
                            } else if (isLoadMore) {
                                refreshLayout.finishLoadMore();
                                isLoadMore = false;
                                adapter.notifyDataSetChanged();
                            } else {
                                loadingView.smoothToHide();
                                initView();
                            }
                        } else {
                            if (page == 1) {
                                loadingView.smoothToHide();
                            }
                            refreshLayout.finishLoadMore();
                        }
                    }
                });
    }

    private void initView() {

        rvExchangeCoupon.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<GiftListBean.DataBean>(ExchangeCouponActivity.this,
                R.layout.rv_coupon_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, GiftListBean.DataBean dataBean, int position) {
                SimpleDraweeView ivCoupon = holder.getView(R.id.iv_home_coupon);
                ivCoupon.setImageURI(dataBean.getPicUrl());

                holder.setText(R.id.tv_home_coupon_title, dataBean.getRelateName());
                holder.setText(R.id.tv_home_coupon_content, dataBean.getExchangeIntro());
                holder.setText(R.id.tv_home_coupon_remain, "剩余" + dataBean.getBalanceNum());

                TextView tvReceive = holder.getView(R.id.tv_home_coupon_receive);
                int needScore = dataBean.getNeedScore();
                if (needScore > 0) {
                    tvReceive.setText(needScore + "积分\n领取");
                } else {
                    tvReceive.setText("免费\n领取");
                }
            }
        };
        rvExchangeCoupon.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                Intent intent = new Intent(ExchangeCouponActivity.this, CouponDetailActivity.class);
                intent.putExtra("coupon_id", dataList.get(position).getRelateId());
                startActivity(intent);

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


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


    @OnClick(R.id.iv_back_header)
    public void onViewClicked() {
        finish();
    }
}
