package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hfbh.yuecheng.R;
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
 * Author：Libin on 2018/5/24 17:03
 * Email：1993911441@qq.com
 * Describe：积分兑礼
 */
public class ExchangeGiftActivity extends BaseActivity {
    @BindView(R.id.iv_exchange_gift_back)
    ImageView ivExchangeGiftBack;
    @BindView(R.id.tv_exchange_gift_score)
    TextView tvGiftScore;
    @BindView(R.id.rv_exchange_gift)
    RecyclerView rvExchange;
    @BindView(R.id.layout_refresh_exchange_gift)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;
    //积分排序是否高到低
    private boolean isUp;
    //积分排序
    private String sortType = "DEFAULT";
    //当前页
    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    private List<GiftListBean.DataBean> dataList = new ArrayList<>();
    //总页数
    private int pages;
    private CommonAdapter<GiftListBean.DataBean> adapter;
    private Drawable drawableUp;
    private Drawable drawableDown;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_gift);
        ButterKnife.bind(this);
        loadingView.smoothToShow();
        init();
        initData();
    }

    private void init() {
        drawableUp = ContextCompat.getDrawable(this, R.mipmap.btn_headerbar_sequence_up);
        drawableUp.setBounds(0, 0, drawableUp.getMinimumWidth(),
                drawableUp.getMinimumHeight());
        drawableDown = ContextCompat.getDrawable(this, R.mipmap.btn_headerbar_sequence_down);
        drawableDown.setBounds(0, 0, drawableDown.getMinimumWidth(),
                drawableDown.getMinimumHeight());

    }

    private void initData() {
        OkHttpUtils.get()
                .url(Constant.EXCHANGE_GIFT_LIST)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("pageNum", String.valueOf(page))
                .addParams("pointsRewardType", "GIFT")
                .addParams("sortType", sortType)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        tvGiftScore.setEnabled(true);
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

        rvExchange.setLayoutManager(new GridLayoutManager(this, 2));
        //布局宽高
        int widthPixels = DisplayUtils.getMetrics(this).widthPixels;
        final int width = (int) ((widthPixels - DisplayUtils.dp2px(this, 35)) / 2);
        //间距
        rvExchange.addItemDecoration(new GridItemDecoration1((int) DisplayUtils.dp2px(this, 6)));
        adapter = new CommonAdapter<GiftListBean.DataBean>(ExchangeGiftActivity.this,
                R.layout.rv_gift_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, GiftListBean.DataBean dataBean, int position) {
                ImageView ivGift = holder.getView(R.id.iv_home_gift);
                ViewGroup.LayoutParams layoutParams = ivGift.getLayoutParams();
                layoutParams.width = width;
                layoutParams.height = width;
                ivGift.setLayoutParams(layoutParams);

                Glide.with(ExchangeGiftActivity.this)
                        .load(dataBean.getPicUrl())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(ivGift);
                holder.setText(R.id.tv_home_gift_name, dataBean.getRelateName());
                holder.setText(R.id.tv_home_gift_score, dataBean.getNeedScore() + "积分");
            }
        };
        rvExchange.setAdapter(adapter);


        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                Intent intent = new Intent(ExchangeGiftActivity.this, GiftDetailActivity.class);
                intent.putExtra("id", dataList.get(position).getPointsRewardId());
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

    @OnClick({R.id.iv_exchange_gift_back, R.id.tv_exchange_gift_score})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_exchange_gift_back:
                finish();
                break;
            case R.id.tv_exchange_gift_score:
                tvGiftScore.setEnabled(false);
                if (isUp) {
                    isUp = false;
                    tvGiftScore.setCompoundDrawables(null, null, drawableDown, null);
                    sortType = "ASC";
                    isRefresh = true;
                    page = 1;
                    initData();
                } else {
                    isUp = true;
                    tvGiftScore.setCompoundDrawables(null, null, drawableUp, null);
                    sortType = "DESC";
                    isRefresh = true;
                    page = 1;
                    initData();
                }
                break;
        }
    }
}
