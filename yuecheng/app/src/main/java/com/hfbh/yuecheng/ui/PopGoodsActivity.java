package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
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
    @BindView(R.id.iv_null_data)
    ImageView ivNullData;
    @BindView(R.id.tv_null_data)
    TextView tvNullData;
    @BindView(R.id.ll_null_data)
    LinearLayout llNullData;

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
        rvPopGoods.setBackgroundColor(Color.WHITE);
        ivNullData.setImageResource(R.mipmap.ic_null_goods);
        tvNullData.setText("暂无商品");
        loadingView.smoothToShow();
        initView();
        initData();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.DISCOVERY_GOODS)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
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

                        if (goodsBean.getPage() != null) {
                            pages = goodsBean.getPage().getPages();
                        }

                        //是否刷新状态
                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                            isRefresh = false;
                            goodsList.clear();
                        } else if (isLoadMore) { //加载更多
                            refreshLayout.finishLoadMore();
                            isLoadMore = false;
                        } else {
                            goodsList.clear();
                            loadingView.smoothToHide();
                        }

                        if (goodsBean.isFlag() && goodsBean.getData() != null && goodsBean.getData().size() > 0) {
                            goodsList.addAll(goodsBean.getData());
                            llNullData.setVisibility(View.GONE);
                            rvPopGoods.setBackgroundColor(Color.WHITE);
                        } else {
                            if (!isLoadMore) {
                                llNullData.setVisibility(View.VISIBLE);
                                rvPopGoods.setBackgroundColor(ContextCompat.getColor(PopGoodsActivity.this,R.color.gray_f2));
                            }
                        }
                        isLoadMore = false;
                        adapter.notifyDataSetChanged();

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
                holder.setText(R.id.tv_discovery_pop_price, "¥" + DisplayUtils.isInteger(dataBean.getNowPrice()));

                TextView tvOld = holder.getView(R.id.tv_discovery_pop_original);
                tvOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                tvOld.setText("¥" + DisplayUtils.isInteger(dataBean.getOldPrice()));

            }
        };


        rvPopGoods.setAdapter(adapter);


        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(PopGoodsActivity.this, PopGoodsDetailActivity.class);
                intent.putExtra("goods_id", goodsList.get(position).getCommodityId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

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
