package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.GoodsBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
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
 * Author：Libin on 2018/7/13 16:03
 * Email：1993911441@qq.com
 * Describe：抢购列表
 */
public class RushGoodsActivity extends BaseActivity {

    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.rv_now_activity)
    RecyclerView rvGoods;
    @BindView(R.id.layout_refresh_now_activity)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
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
        tvHeaderTitle.setText("限时秒杀");
        ivNullData.setImageResource(R.mipmap.ic_null_goods);
        tvNullData.setText("暂无商品");
        viewLoading.smoothToShow();
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
                .addParams("commodityType", "SECKILL")
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
                        } else {
                            goodsList.clear();
                            viewLoading.smoothToHide();
                        }

                        if (goodsBean.isFlag() && goodsBean.getData() != null && goodsBean.getData().size() > 0) {
                            goodsList.addAll(goodsBean.getData());
                            llNullData.setVisibility(View.GONE);
                        }else {
                            if (!isLoadMore) {
                                llNullData.setVisibility(View.VISIBLE);
                            }
                        }

                        isLoadMore = false;
                        adapter.notifyDataSetChanged();
                    }
                });

    }

    private void initView() {
        rvGoods.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CommonAdapter<GoodsBean.DataBean>(this, R.layout.rv_group_goods_item, goodsList) {
            @Override
            protected void convert(ViewHolder holder, GoodsBean.DataBean dataBean, int position) {

                SimpleDraweeView ivGoods = holder.getView(R.id.iv_group_goods);
                ivGoods.setImageURI(dataBean.getPicturePath());

                holder.setText(R.id.tv_group_goods_name, dataBean.getCommodityName());

                holder.setVisible(R.id.ll_rush_buy, true);
                holder.setText(R.id.tv_group_goods_num, "已抢" + dataBean.getSaleNum() + "件");

                ProgressBar progressBar = holder.getView(R.id.progressbar_goods);

                int progress = 0;
                if (dataBean.getSaleNum() + dataBean.getCommodityNum() > 0) {
                    progress = dataBean.getSaleNum() * 100 / (dataBean.getSaleNum() + dataBean.getCommodityNum());
                }

                progressBar.setProgress(progress);
                holder.setText(R.id.tv_group_goods_limit, progress + "%");
                holder.setText(R.id.tv_group_goods_discount, "¥" + DisplayUtils.isInteger(dataBean.getNowPrice()));
                TextView tvOld = holder.getView(R.id.tv_group_goods_price);
                tvOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                tvOld.setText("¥" + DisplayUtils.isInteger(dataBean.getOldPrice()));

                boolean isFinish = !TextUtils.isEmpty(dataBean.getEndTime()) &&
                        System.currentTimeMillis() >
                                DateUtils.getTime("yyyy-MM-dd HH:mm:ss", dataBean.getEndTime());
                boolean isStart = !TextUtils.isEmpty(dataBean.getStartTime()) &&
                        System.currentTimeMillis() >
                                DateUtils.getTime("yyyy-MM-dd HH:mm:ss", dataBean.getStartTime());
                boolean isBuy = "Y".equals(dataBean.getIsJoin());
                boolean isNull = dataBean.getCommodityNum() == 0;


                TextView tvStatus = holder.getView(R.id.tv_group_goods_buy);
                if (isFinish) {
                    tvStatus.setText("已结束");
                    tvStatus.setBackgroundResource(R.drawable.bound_gray_15dp);
                } else if (!isStart) {
                    tvStatus.setText("未开始");
                    tvStatus.setBackgroundResource(R.drawable.bound_gray_15dp);
                } else if (isBuy) {
                    tvStatus.setText("已抢购");
                    tvStatus.setBackgroundResource(R.drawable.bound_red_15dp);
                } else if (isNull) {
                    tvStatus.setText("已抢光");
                    tvStatus.setBackgroundResource(R.drawable.bound_gray_15dp);
                } else {
                    tvStatus.setText("去抢购");
                    tvStatus.setBackgroundResource(R.drawable.bound_red_15dp);
                }
            }
        };

        rvGoods.setAdapter(adapter);


        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(RushGoodsActivity.this, RushGoodsDetailActivity.class);
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
