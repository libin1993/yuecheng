package com.hfbh.yuecheng.ui;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.BaseAdapter;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.GoodsBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;


public class GoodsActivity extends BaseActivity {
    @BindView(R.id.rv_now_activity)
    RecyclerView rvGoods;
    @BindView(R.id.layout_refresh_now_activity)
    SmartRefreshLayout refreshLayout;
    //当前页数
    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    private List<GoodsBean.DataBean> goodsList = new ArrayList<>();
    //总页数
    private int pages;
    private List<DelegateAdapter.Adapter> mAdapters = new LinkedList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {

        //初始化
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        rvGoods.setLayoutManager(layoutManager);

        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rvGoods.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(1, 20);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        rvGoods.setAdapter(delegateAdapter);

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setAutoExpand(false);
        gridLayoutHelper.setPadding((int) DisplayUtils.dp2px(this, 12),
                0, (int) DisplayUtils.dp2px(this, 12), 0);
        gridLayoutHelper.setHGap((int) DisplayUtils.dp2px(this, 11));// 控制子元素之间的水平间距
        gridLayoutHelper.setBgColor(Color.WHITE);

//        MyAdapter adapter = new MyAdapter(gridLayoutHelper);


        BaseAdapter adapter = new BaseAdapter(this, gridLayoutHelper,
                R.layout.rv_pop_goods_item, goodsList.size(), 1) {
            //布局宽高
            int widthPixels = DisplayUtils.getMetrics(GoodsActivity.this).widthPixels;
            final int width = (int) ((widthPixels - DisplayUtils.dp2px(GoodsActivity.this, 35)) / 2);

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ImageView ivPop = holder.getView(R.id.iv_discovery_pop);
                ViewGroup.LayoutParams layoutParams = ivPop.getLayoutParams();
                layoutParams.width = width;
                layoutParams.height = width;
                ivPop.setLayoutParams(layoutParams);

                Glide.with(GoodsActivity.this)
                        .load(goodsList.get(position).getPicturePath())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(ivPop);
                holder.setText(R.id.tv_discovery_pop_name, goodsList.get(position).getCommodityName());
                holder.setText(R.id.tv_discovery_pop_price, "¥" + DisplayUtils.isInteger(goodsList.get(position).getNowPrice()));

                TextView tvOld = holder.getView(R.id.tv_discovery_pop_original);
                tvOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                tvOld.setText("¥" + DisplayUtils.isInteger(goodsList.get(position).getOldPrice()));

            }
        };


        mAdapters.add(adapter);
        delegateAdapter.setAdapters(mAdapters);


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
                                goodsList.clear();
                            } else if (isLoadMore) { //加载更多
                                refreshLayout.finishLoadMore();
                                isLoadMore = false;
                            }
                            for (int j = 0; j < mAdapters.size(); j++) {
                                DelegateAdapter.Adapter adapter = mAdapters.get(j);
                                adapter.notifyDataSetChanged();
                            }

                        }
                    }
                });

    }


    class MyAdapter extends DelegateAdapter.Adapter<MyAdapter.MainViewHolder> {
        LayoutHelper layoutHelper;

        public MyAdapter(LayoutHelper layoutHelper) {
            this.layoutHelper = layoutHelper;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return layoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(GoodsActivity.this)
                    .inflate(R.layout.rv_pop_goods_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            ViewGroup.LayoutParams layoutParams = holder.ivGoods.getLayoutParams();
            layoutParams.width = 100;
            layoutParams.height = 100;
            holder.ivGoods.setLayoutParams(layoutParams);
            Glide.with(GoodsActivity.this)
                    .load(goodsList.get(position).getPicturePath())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.ivGoods);
            holder.tvTitle.setText(goodsList.get(position).getCommodityName());
        }

        @Override
        public int getItemCount() {
            return goodsList.size();
        }

        class MainViewHolder extends RecyclerView.ViewHolder {
            ImageView ivGoods;
            TextView tvTitle;

            public MainViewHolder(View itemView) {
                super(itemView);
                ivGoods = (ImageView) itemView.findViewById(R.id.iv_discovery_pop);
                tvTitle = (TextView) itemView.findViewById(R.id.tv_discovery_pop_name);
            }
        }

    }
}
