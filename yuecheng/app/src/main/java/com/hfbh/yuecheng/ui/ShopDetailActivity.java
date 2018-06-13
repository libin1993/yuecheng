package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.GoodsBean;
import com.hfbh.yuecheng.bean.ShopDetailBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
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
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/23 09:03
 * Email：1993911441@qq.com
 * Describe：店铺详情
 */
public class ShopDetailActivity extends BaseActivity {

    @BindView(R.id.rv_shop_detail)
    RecyclerView rvShopDetail;
    @BindView(R.id.layout_refresh_shop)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_shop_back)
    ImageView ivShopBack;
    //店铺id
    private int shopId;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    //当前页
    private int page = 1;
    //总页数
    private int pages;

    private ShopDetailBean shopBean;
    private List<GoodsBean.DataBean> goodsList = new ArrayList<>();
    private boolean isShop;
    private boolean isGoods;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private String type;
    private String address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        getData();
        initShopData();
        initGoodsData();
    }

    private void getData() {
        Intent intent = getIntent();
        shopId = intent.getIntExtra("shop_id", 0);
        type = intent.getStringExtra("type");
        address = intent.getStringExtra("address");
    }

    /**
     * 店铺数据
     */
    private void initShopData() {
        OkHttpUtils.post()
                .url(Constant.SHOP_DETAIL)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("shopId", String.valueOf(shopId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        LogUtils.log(s);
                        shopBean = GsonUtils.jsonToBean(s, ShopDetailBean.class);
                        if (shopBean.isFlag()) {
                            isShop = true;
                            if (isGoods) {
                                initGoodsList();
                            }
                        }
                    }
                });
    }


    private void initGoodsData() {
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
                            isGoods = true;
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
                                if (isShop && isGoods) {
                                    initGoodsList();
                                }
                            }
                        }
                    }
                });

    }

    /**
     * 店铺信息
     */
    private View initShopInfo() {

        View view = LayoutInflater.from(this).inflate(R.layout.layout_shop_detail_header, null);
        SimpleDraweeView ivShopDetail = (SimpleDraweeView) view.findViewById(R.id.iv_shop_detail);
        SimpleDraweeView ivShopDetailAvatar = (SimpleDraweeView) view.findViewById(R.id.iv_shop_detail_avatar);
        TextView tvShopDetailName = (TextView) view.findViewById(R.id.tv_shop_detail_name);
        TextView tvShopDetailType = (TextView) view.findViewById(R.id.tv_shop_detail_type);
        TextView tvShopDetailLocation = (TextView) view.findViewById(R.id.tv_shop_detail_location);
        TextView tvShopDetailInfo = (TextView) view.findViewById(R.id.tv_shop_detail_info);


        ivShopDetail.setImageURI(shopBean.getMall().getOrganizePicturePath());
        ivShopDetailAvatar.setImageURI(shopBean.getShop().getShopLogo());
        tvShopDetailName.setText(shopBean.getShop().getShopName());
        tvShopDetailType.setText(type);
        tvShopDetailLocation.setText(shopBean.getMall().getOrganizeName() + address);

        tvShopDetailInfo.setText(shopBean.getShop().getShopIntro());

        return view;
    }

    /**
     * 店铺列表
     */
    private void initGoodsList() {
        rvShopDetail.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<GoodsBean.DataBean> adapter = new CommonAdapter<GoodsBean.DataBean>(
                this, R.layout.rv_new_goods_item, goodsList) {
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

        mHeaderAndFooterWrapper.addHeaderView(initShopInfo());

        rvShopDetail.setAdapter(mHeaderAndFooterWrapper);


        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(ShopDetailActivity.this, NewGoodsDetailActivity.class);
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
                    initGoodsData();
                } else {
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isRefresh = true;
                page = 1;
                initGoodsData();
            }
        });

    }


    @OnClick(R.id.iv_shop_back)
    public void onViewClicked() {
        finish();
    }
}
