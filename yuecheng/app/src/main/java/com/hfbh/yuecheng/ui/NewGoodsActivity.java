package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.GoodsBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
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
 * Author：Libin on 2018/5/25 14:55
 * Email：1993911441@qq.com
 * Describe：新品速递
 */
public class NewGoodsActivity extends BaseActivity {

    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.rv_now_activity)
    RecyclerView rvNewGoods;
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
        tvHeaderTitle.setText("新品");
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
        rvNewGoods.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<GoodsBean.DataBean>(this, R.layout.rv_new_goods_item, goodsList) {
            @Override
            protected void convert(ViewHolder holder, GoodsBean.DataBean dataBean, int position) {
                SimpleDraweeView ivGoods = holder.getView(R.id.iv_discovery_new);
                ivGoods.setImageURI(dataBean.getPicturePath());

                holder.setText(R.id.tv_discovery_new_name, dataBean.getCommodityName());
                holder.setText(R.id.tv_discovery_new_tip, dataBean.getIndustryName());
                holder.setText(R.id.tv_discovery_new_tip, dataBean.getModifyTime());

            }
        };


        rvNewGoods.setAdapter(adapter);

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