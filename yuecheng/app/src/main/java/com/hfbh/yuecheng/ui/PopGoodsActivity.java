package com.hfbh.yuecheng.ui;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.GoodsRecycleAdapter;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityListBean;
import com.hfbh.yuecheng.bean.GoodsBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.view.GridItemDecoration1;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/25 14:54
 * Email：1993911441@qq.com
 * Describe：人气单品
 */
public class PopGoodsActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.rv_now_activity)
    RecyclerView rvPopGoods;
    @BindView(R.id.layout_refresh_now_activity)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;

    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    private List<GoodsBean.DataBean> goodsList = new ArrayList<>();
    //总页数
    private int pages;
    private CommonAdapter<GoodsBean.DataBean> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_activity);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("人气");
        loadingView.smoothToShow();
        initData();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.DISCOVERY_GOODS)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("commodityType", "SPECIAL")
                .addParams("pageNum", String.valueOf(page))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        GoodsBean goodsBean = GsonUtils.jsonToBean(response, GoodsBean.class);

                        if (goodsBean.isFlag() && goodsBean.getData().size() > 0) {

                            pages = goodsBean.getPage().getPages();
                            if (isRefresh) {
                                goodsList.clear();
                            }
                            goodsList.addAll(goodsBean.getData());

                            //是否刷新状态
                            if (isRefresh) {
                                refreshLayout.finishRefresh();
                                isRefresh = false;
                                adapter.notifyDataSetChanged();
                            } else if (isLoadMore) { //加载更多
                                refreshLayout.finishLoadMore();
                                isLoadMore = false;
                                adapter.notifyDataSetChanged();
                            } else {
                                loadingView.smoothToHide();
                                refreshLayout.finishLoadMore();
                                initView();
                            }
                        }
                    }
                });

    }

    private void initView() {
        rvPopGoods.setLayoutManager(new GridLayoutManager(this, 2));
        //布局宽高
        final int widthPixels = DisplayUtils.getMetrics(this).widthPixels;
        final int imgWidth = (int) ((widthPixels - DisplayUtils.dp2px(this, 35)) / 2);
        rvPopGoods.addItemDecoration(new GridItemDecoration1((int) DisplayUtils.dp2px(this, 6)));
        adapter = new CommonAdapter<GoodsBean.DataBean>(this, R.layout.rv_pop_goods_item, goodsList) {
            @Override
            protected void convert(ViewHolder holder, GoodsBean.DataBean dataBean, int position) {

                ImageView ivPop = holder.getView(R.id.iv_discovery_pop);
                ViewGroup.LayoutParams layoutParams = ivPop.getLayoutParams();
                layoutParams.width = imgWidth;
                layoutParams.height = imgWidth;
                ivPop.setLayoutParams(layoutParams);

                Glide.with(PopGoodsActivity.this)
                        .load(dataBean.getPicturePath())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(ivPop);

                holder.setText(R.id.tv_discovery_pop_name, dataBean.getCommodityName());
                holder.setText(R.id.tv_discovery_pop_price, "¥" + dataBean.getNowPrice());

                TextView tvOld = holder.getView(R.id.tv_discovery_pop_original);
                tvOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                tvOld.setText("¥" + dataBean.getOldPrice());

            }
        };


        rvPopGoods.setAdapter(adapter);

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (page < pages) {
                    isLoadMore = true;
                    page++;
                    initData();
                } else {
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isRefresh = true;
                page = 1;
                initData();
            }
        });
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
