package com.hfbh.yuecheng.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.GoodsRecycleAdapter;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.GoodsBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.view.GridItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.indicators.BallSpinFadeLoaderIndicator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/21 11:36
 * Email：1993911441@qq.com
 * Describe：好物
 */
public class GoodsFragment extends BaseFragment {

    @BindView(R.id.rv_discovery_goods)
    RecyclerView rvGoods;
    @BindView(R.id.layout_refresh_goods)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;
    private Unbinder unbinder;


    private List<GoodsBean.DataBean> popGoods = new ArrayList<>();
    private List<GoodsBean.DataBean> newGoods = new ArrayList<>();

    private List<GoodsBean.DataBean> itemList = new ArrayList<>();

    //好物模块数量
    private int count;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    //人气当前页
    private int page1 = 1;
    //新品当前页
    private int page2 = 1;
    //人气总页数
    private int pages1;
    //新品总页数
    private int pages2;
    private GoodsRecycleAdapter adapter;

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
    private void initData(final String type, final int page) {
        OkHttpUtils.post()
                .url(Constant.DISCOVERY_GOODS)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getParentFragment().getActivity(), "hash"))
                .addParams("token",SharedPreUtils.getStr(getParentFragment().getActivity(), "token"))
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

                        if (goodsBean.isFlag() && goodsBean.getData().size() > 0) {
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

                                //重新赋值
                                itemList.clear();

                                GoodsBean.DataBean dataBean1 = new GoodsBean.DataBean();
                                dataBean1.setType(1);
                                dataBean1.setTitle("人气");
                                itemList.add(dataBean1);

                                for (int i = 0; i < popGoods.size(); i++) {
                                    GoodsBean.DataBean dataBean2 = popGoods.get(i);
                                    dataBean2.setType(2);
                                    itemList.add(dataBean2);
                                }


                                GoodsBean.DataBean dataBean3 = new GoodsBean.DataBean();
                                dataBean3.setType(3);
                                dataBean3.setTitle("新品");
                                itemList.add(dataBean3);

                                for (int j = 0; j < newGoods.size(); j++) {
                                    GoodsBean.DataBean dataBean4 = newGoods.get(j);
                                    dataBean4.setType(4);
                                    itemList.add(dataBean4);
                                }


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
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getParentFragment().getActivity(), 2);
        //SpanSize为多少，表示占用几个item
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = rvGoods.getAdapter().getItemViewType(position);
                switch (type) {
                    case 1:
                        return 2;
                    case 2:
                        return 1;
                    case 3:
                        return 2;
                    case 4:
                        return 2;
                }
                return 1;
            }
        });
        //间距
        rvGoods.addItemDecoration(new GridItemDecoration((int) DisplayUtils.dp2px(getParentFragment()
                .getActivity(), 6), popGoods.size()));
        rvGoods.setLayoutManager(gridLayoutManager);
        adapter = new GoodsRecycleAdapter(getParentFragment().getActivity(), itemList);
        rvGoods.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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

    public static GoodsFragment newInstance() {
        Bundle args = new Bundle();
        GoodsFragment fragment = new GoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
