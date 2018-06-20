package com.hfbh.yuecheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.GoodsBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.NewGoodsActivity;
import com.hfbh.yuecheng.ui.NewGoodsDetailActivity;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/14 14:22
 * Email：1993911441@qq.com
 * Describe：
 */
public class NewGoodsFragment extends BaseFragment {
    @BindView(R.id.rv_discovery_goods)
    RecyclerView rvGoods;
    @BindView(R.id.layout_refresh_goods)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;
    @BindView(R.id.rl_null_data)
    RelativeLayout rlNullData;
    private Unbinder unbinder;

    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    //当前页
    private int page = 1;
    //总页数
    private int pages;
    private List<GoodsBean.DataBean> goodsList = new ArrayList<>();
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);
        unbinder = ButterKnife.bind(this, view);
        loadingView.smoothToShow();
        initData();
        return view;
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.DISCOVERY_GOODS)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getParentFragment().getActivity(), "hash"))
                .addParams("token",SharedPreUtils.getStr(getParentFragment().getActivity(), "token"))
                .addParams("commodityType", "FIRSTLOOK")
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
                                mHeaderAndFooterWrapper.notifyDataSetChanged();
                            } else if (isLoadMore) { //加载更多
                                refreshLayout.finishLoadMore();
                                isLoadMore = false;
                                mHeaderAndFooterWrapper.notifyDataSetChanged();
                            } else {
                                loadingView.smoothToHide();
                                initView();
                            }
                            rvGoods.setVisibility(View.VISIBLE);
                            rlNullData.setVisibility(View.GONE);
                        } else {

                            refreshLayout.finishLoadMore();
                            if (page == 1) {
                                loadingView.smoothToHide();
                                rvGoods.setVisibility(View.GONE);
                                rlNullData.setVisibility(View.VISIBLE);
                            }

                        }
                    }
                });
    }

    private void initView() {
        rvGoods.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));
        CommonAdapter<GoodsBean.DataBean> adapter = new CommonAdapter<GoodsBean.DataBean>(
                getParentFragment().getActivity(), R.layout.rv_new_goods_item, goodsList) {
            @Override
            protected void convert(ViewHolder holder, GoodsBean.DataBean dataBean, int position) {
                SimpleDraweeView ivGoods = holder.getView(R.id.iv_discovery_new);
                ivGoods.setImageURI(dataBean.getPicturePath());

                holder.setText(R.id.tv_discovery_new_name, dataBean.getCommodityName());
                holder.setText(R.id.tv_discovery_new_tip, dataBean.getIndustryName());
                holder.setText(R.id.tv_discovery_new_time, dataBean.getOnlineTime());

            }
        };

        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);

        mHeaderAndFooterWrapper.addHeaderView(initTitle());

        rvGoods.setAdapter(mHeaderAndFooterWrapper);


        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getParentFragment().getActivity(), NewGoodsDetailActivity.class);
                intent.putExtra("goods_id", goodsList.get(position - 1).getCommodityId());
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

    private View initTitle() {
        View view = LayoutInflater.from(getParentFragment().getActivity()).inflate(R.layout.layout_homepage_title, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_home_title);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_home_title);
        tvTitle.setText("新品");

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getParentFragment().getActivity(), NewGoodsActivity.class));
            }
        });
        return view;
    }

    public static NewGoodsFragment newInstance() {

        Bundle args = new Bundle();

        NewGoodsFragment fragment = new NewGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
