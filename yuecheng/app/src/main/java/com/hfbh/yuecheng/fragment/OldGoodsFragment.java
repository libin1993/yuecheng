package com.hfbh.yuecheng.fragment;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.BaseDelegateAdapter;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.GoodsBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.indicators.BallSpinFadeLoaderIndicator;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/17 09:51
 * Email：1993911441@qq.com
 * Describe：好物
 */
public class OldGoodsFragment extends BaseFragment {
    @BindView(R.id.rv_discovery_goods)
    RecyclerView rvGoods;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;
    @BindView(R.id.layout_refresh_goods)
    SmartRefreshLayout refreshLayout;
    private Unbinder unbinder;
    //好物模块数量
    private int count;
    private List<GoodsBean.DataBean> popGoods = new ArrayList<>();
    private List<GoodsBean.DataBean> newGoods = new ArrayList<>();
    private List<DelegateAdapter.Adapter> mAdapters = new LinkedList<>();

    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;

    private int page1 = 1;
    private int page2 = 1;


    private int pages1;
    private int pages2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);
        unbinder = ButterKnife.bind(this, view);
        loadingView.smoothToShow();
        initData("SPECIAL", page1);
        initData("FIRSTLOOK", page2);

        return view;
    }

    /**
     * @param type 加载数据
     */
    private void initData(final String type, int page) {
        OkHttpUtils.post()
                .url(Constant.DISCOVERY_GOODS)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getParentFragment().getActivity(), "hash"))
                .addParams("commodityType", type)
                .addParams("pageNum", String.valueOf(page))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        GoodsBean goodsBean = GsonUtils.jsonToBean(response, GoodsBean.class);
                        if (goodsBean.isFlag()) {
                            switch (type) {
                                case "SPECIAL":
                                    if (goodsBean.getData() != null && goodsBean.getData().size() > 0) {
                                        pages1 = goodsBean.getPage().getPages();
                                        if (isRefresh) {
                                            popGoods.clear();
                                        }
                                        popGoods.addAll(goodsBean.getData());
                                    }
                                    break;
                                case "FIRSTLOOK":
                                    if (goodsBean.getData() != null && goodsBean.getData().size() > 0) {
                                        pages2 = goodsBean.getPage().getPages();
                                        if (isRefresh) {
                                            newGoods.clear();
                                        }
                                        newGoods.addAll(goodsBean.getData());
                                    }
                                    break;
                            }

                            count++;
                            if (count == 2) {
                                count = 0;
                                //是否刷新状态
                                if (isRefresh) {
                                    refreshLayout.finishRefresh();
                                    isRefresh = false;
                                    for (int j = 0; j < mAdapters.size(); j++) {
                                        BaseDelegateAdapter adapter = (BaseDelegateAdapter) mAdapters.get(j);
                                        adapter.notifyDataSetChanged();
                                    }
                                } else if (isLoadMore) {
                                    refreshLayout.finishLoadMore();
                                    isLoadMore = false;
                                    for (int j = 0; j < mAdapters.size(); j++) {
                                        BaseDelegateAdapter adapter = (BaseDelegateAdapter) mAdapters.get(j);
                                        adapter.notifyDataSetChanged();
                                    }
                                } else {
                                    loadingView.smoothToHide();
                                    initView();
                                }
                            }
                        }
                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {

        //初始化
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getParentFragment().getActivity());
        rvGoods.setLayoutManager(layoutManager);

        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rvGoods.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(4, 20);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        rvGoods.setAdapter(delegateAdapter);

        //人气
        initTitle("人气", 1);
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setAutoExpand(false);
        gridLayoutHelper.setPadding((int) DisplayUtils.dp2px(getParentFragment().getActivity(), 12),
                0, (int) DisplayUtils.dp2px(getParentFragment().getActivity(), 12), 0);
        gridLayoutHelper.setHGap((int) DisplayUtils.dp2px(getParentFragment().getActivity(), 11));// 控制子元素之间的水平间距
        gridLayoutHelper.setBgColor(Color.WHITE);
        BaseDelegateAdapter popAdapter = new BaseDelegateAdapter(getParentFragment().getActivity(), gridLayoutHelper,
                R.layout.rv_pop_goods_item, popGoods.size(), 2) {
            //布局宽高
            int widthPixels = DisplayUtils.getMetrics(getParentFragment().getActivity()).widthPixels;
            final int width = (int) ((widthPixels - DisplayUtils.dp2px(getParentFragment().getActivity(), 35)) / 2);

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ImageView ivPop = holder.getView(R.id.iv_discovery_pop);
                ViewGroup.LayoutParams layoutParams = ivPop.getLayoutParams();
                layoutParams.width = width;
                layoutParams.height = width;
                ivPop.setLayoutParams(layoutParams);

                Glide.with(getParentFragment().getActivity())
                        .load(popGoods.get(position).getPicturePath())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(ivPop);
                holder.setText(R.id.tv_discovery_pop_name, popGoods.get(position).getCommodityName());
                holder.setText(R.id.tv_discovery_pop_price, "¥" + popGoods.get(position).getNowPrice());

                TextView tvOld = holder.getView(R.id.tv_discovery_pop_original);
                tvOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                tvOld.setText("¥" + popGoods.get(position).getOldPrice());

            }
        };
        mAdapters.add(popAdapter);

        //新品
        initTitle("新品", 3);
        BaseDelegateAdapter newAdapter = new BaseDelegateAdapter(getParentFragment().getActivity(),
                new LinearLayoutHelper(), R.layout.rv_new_goods_item, newGoods.size(), 4) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                SimpleDraweeView ivNewGoods = holder.getView(R.id.iv_discovery_new);
                ivNewGoods.setImageURI(newGoods.get(position).getPicturePath());

                holder.setText(R.id.tv_discovery_new_name, newGoods.get(position).getCommodityName());
                holder.setText(R.id.tv_discovery_new_tip, newGoods.get(position).getIndustryName());
                holder.setText(R.id.tv_discovery_new_time, newGoods.get(position).getModifyTime());

            }
        };


        mAdapters.add(newAdapter);
        delegateAdapter.setAdapters(mAdapters);

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                count = 0;
                if (page1 < pages1 || page2 < pages2) {
                    isLoadMore = true;
                    if (page1 < pages1) {
                        page1++;
                        initData("SPECIAL", page1);
                    } else {
                        count++;
                    }
                    if (page2 < pages2) {
                        page2++;
                        initData("FIRSTLOOK", page2);
                    } else {
                        count++;
                    }
                } else {
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                count = 0;
                page1 = 1;
                page2 = 1;
                initData("SPECIAL", page1);
                initData("FIRSTLOOK", page2);
            }
        });

    }

    /**
     * @param title
     * @param type  标题
     */
    private void initTitle(final String title, int type) {
        BaseDelegateAdapter titleAdapter = new BaseDelegateAdapter(getParentFragment().getActivity(), new LinearLayoutHelper(),
                R.layout.layout_homepage_title, 1, type) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.tv_home_title, title);
            }
        };
        mAdapters.add(titleAdapter);

    }


    public static OldGoodsFragment newInstance() {
        Bundle args = new Bundle();
        OldGoodsFragment fragment = new OldGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
