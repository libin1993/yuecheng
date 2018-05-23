package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.SearchShopBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.indicators.BallSpinFadeLoaderIndicator;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/22 13:36
 * Email：1993911441@qq.com
 * Describe：找店铺
 */
public class SearchShopActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.et_search_market)
    EditText etSearch;
    @BindView(R.id.tv_market_type)
    TextView tvMarketType;
    @BindView(R.id.tv_market_floor)
    TextView tvMarketFloor;
    @BindView(R.id.ll_search_market)
    LinearLayout llSearchMarket;
    @BindView(R.id.rv_search_market_list)
    RecyclerView rvMarketList;
    @BindView(R.id.layout_refresh_market)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_no_market)
    TextView tvNoMarket;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;
    @BindView(R.id.view_header_line)
    View viewHeaderLine;
    //业态id
    private int industryId;
    //楼层id
    private int floorId;
    //楼层名称
    private String floorName;
    //类型名称
    private String industryName;
    private int industryNum = -1;
    private int floorNum = -1;

    private List<SearchShopBean.ShopListBean> shopList = new ArrayList<>();
    private List<SearchShopBean.FloorListBean> floorList = new ArrayList<>();
    private List<SearchShopBean.IndustryListBeanX> industryList = new ArrayList<>();

    //页数
    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    //总页数
    private int pages;
    //首次进入
    private boolean firstIn = true;
    CommonAdapter<SearchShopBean.ShopListBean> shopAdapter;
    private Drawable grayDrawable;
    private Drawable redDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shop);
        ButterKnife.bind(this);
        init();
        initData();
    }

    private void init() {
        tvHeaderTitle.setText("找店铺");
        viewHeaderLine.setVisibility(View.GONE);
        loadingView.smoothToShow();

        grayDrawable = ContextCompat.getDrawable(this, R.mipmap.ic_triangle_graydown);
        grayDrawable.setBounds(0, 0, grayDrawable.getMinimumWidth(),
                grayDrawable.getMinimumHeight());

        redDrawable = ContextCompat.getDrawable(this, R.mipmap.ic_home_triangle);
        redDrawable.setBounds(0, 0, redDrawable.getMinimumWidth(),
                redDrawable.getMinimumHeight());
    }

    /**
     * 加载数据
     */
    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("appType", MyApp.appType);
        map.put("appVersion", MyApp.appVersion);
        map.put("organizeId", MyApp.organizeId);
        map.put("hash", SharedPreUtils.getStr(this, "hash"));
        map.put("pageNum", String.valueOf(page));
        if (floorNum >= 0) {
            map.put("floorId", String.valueOf(floorId));
        }
        if (industryNum >= 0) {
            map.put("industryId", String.valueOf(industryId));
        }
        if (!TextUtils.isEmpty(etSearch.getText().toString().trim())) {
            map.put("shopName", etSearch.getText().toString().trim());
        }
        OkHttpUtils.post()
                .url(Constant.SHOP_LIST)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        SearchShopBean marketListBean = GsonUtils.jsonToBean(response, SearchShopBean.class);
                        if (marketListBean.isFlag() && marketListBean.getShopList().size() > 0) {
                            if (isRefresh) {
                                shopList.clear();
                            }
                            shopList.addAll(marketListBean.getShopList());
                            pages = marketListBean.getPage().getPages();
                            if (firstIn) {
                                if (marketListBean.getFloorList().size() > 0) {
                                    floorList.addAll(marketListBean.getFloorList());
                                    floorNum = 0;
                                    floorId = floorList.get(0).getFloorId();
                                    floorName = floorList.get(0).getFloorName();
                                    tvMarketFloor.setText(floorName);
                                }
                                if (marketListBean.getIndustryList().size() > 0) {
                                    industryList.addAll(marketListBean.getIndustryList());
                                    industryId = industryList.get(0).getIndustryId();
                                    industryNum = 0;
                                    industryName = industryList.get(0).getIndustryName();
                                    tvMarketType.setText(industryName);
                                }
                                firstIn = false;
                            }

                            if (isRefresh) {
                                refreshLayout.finishRefresh();
                                isRefresh = false;
                                shopAdapter.notifyDataSetChanged();
                            } else if (isLoadMore) {
                                refreshLayout.finishLoadMore();
                                isLoadMore = false;
                                shopAdapter.notifyDataSetChanged();
                            } else {
                                loadingView.smoothToHide();
                                initView();
                            }
                            rvMarketList.setVisibility(View.VISIBLE);
                            tvNoMarket.setVisibility(View.GONE);
                        } else {
                            if (page == 1) {
                                rvMarketList.setVisibility(View.GONE);
                                tvNoMarket.setVisibility(View.VISIBLE);
                            }
                            if (isLoadMore) {
                                refreshLayout.finishLoadMore();
                                isLoadMore = false;
                            }
                        }
                    }
                });

    }

    /**
     * 加载视图
     */
    private void initView() {
        rvMarketList.setLayoutManager(new LinearLayoutManager(this));
        shopAdapter = new CommonAdapter<SearchShopBean.ShopListBean>(this,
                R.layout.rv_search_market_item, shopList) {
            @Override
            protected void convert(ViewHolder holder, SearchShopBean.ShopListBean shopListBean, int position) {
                SimpleDraweeView ivMarket = holder.getView(R.id.iv_search_market);
                ivMarket.setImageURI(shopListBean.getShopPicture());
                holder.setText(R.id.tv_search_market_name, shopListBean.getShopName());

                TextView tvType = holder.getView(R.id.tv_search_market_type);
                StringBuilder type = new StringBuilder();
                for (int i = 0; i < shopListBean.getIndustryList().size(); i++) {
                    type.append(shopListBean.getIndustryList().get(i).getIndustryName()).append(",");
                }
                tvType.setText(type.toString().substring(0, type.length() - 1));

                holder.setText(R.id.tv_search_market_address, shopListBean.getRegionName() +
                        shopListBean.getBerthNo());

                TextView tvScore = holder.getView(R.id.tv_search_market_tip);
                if ("Y".equals(shopListBean.getIsScoreShop())) {
                    tvScore.setVisibility(View.VISIBLE);
                } else {
                    tvScore.setVisibility(View.GONE);
                }
            }
        };
        rvMarketList.setAdapter(shopAdapter);

        shopAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                int shopId = shopList.get(position).getShopId();
                Intent intent = new Intent(SearchShopActivity.this, ShopDetailActivity.class);
                intent.putExtra("shop_id", shopId);
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
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isRefresh = true;
                page = 1;
                initData();
            }
        });

        etSearch.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                //禁止输入空格
                if (source.equals(" ")) {
                    return "";
                } else {
                    return null;
                }
            }
        }});

        //监听
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isRefresh = true;
                page = 1;
                initData();
            }
        });

    }


    /**
     * 选择楼层
     */
    private void selectFloor() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.ppw_select_floor, null);
        final PopupWindow mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        if (!isFinishing()) {
            mPopupWindow.showAsDropDown(llSearchMarket);
        }


        RecyclerView rvFloor = (RecyclerView) contentView.findViewById(R.id.rv_select_floor);

        rvFloor.setLayoutManager(new LinearLayoutManager(this));
        final CommonAdapter<SearchShopBean.FloorListBean> floorAdapter = new CommonAdapter
                <SearchShopBean.FloorListBean>(this, R.layout.rv_floor_list_item, floorList) {
            @Override
            protected void convert(ViewHolder holder, SearchShopBean.FloorListBean floorListBean, int position) {
                holder.setText(R.id.tv_floor_name, floorListBean.getFloorName());
                RelativeLayout rlFloor = holder.getView(R.id.rl_floor_item);
                ImageView ivFloor = holder.getView(R.id.iv_floor_current);
                if (position == floorNum) {
                    rlFloor.setBackgroundResource(R.color.gray_f5);
                    ivFloor.setVisibility(View.VISIBLE);
                } else {
                    rlFloor.setBackgroundColor(Color.WHITE);
                    ivFloor.setVisibility(View.GONE);
                }
            }
        };

        rvFloor.setAdapter(floorAdapter);
        floorAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                floorNum = holder.getLayoutPosition();
                floorId = floorList.get(floorNum).getFloorId();
                floorName = floorList.get(floorNum).getFloorName();
                tvMarketFloor.setText(floorName);
                floorAdapter.notifyDataSetChanged();
                mPopupWindow.dismiss();
                page = 1;
                isRefresh = true;
                initData();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tvMarketType.setTextColor(getResources().getColor(R.color.gray_1a));
                tvMarketType.setCompoundDrawables(null, null, grayDrawable, null);
                tvMarketFloor.setTextColor(getResources().getColor(R.color.gray_1a));
                tvMarketFloor.setCompoundDrawables(null, null, grayDrawable, null);

            }
        });
    }

    /**
     * 选择分类
     */
    private void selectIndustry() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.ppw_select_floor, null);
        final PopupWindow mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        if (!isFinishing()) {
            mPopupWindow.showAsDropDown(llSearchMarket);
        }

        RecyclerView rvType = (RecyclerView) contentView.findViewById(R.id.rv_select_floor);

        rvType.setLayoutManager(new LinearLayoutManager(this));
        final CommonAdapter<SearchShopBean.IndustryListBeanX> typeAdapter = new CommonAdapter
                <SearchShopBean.IndustryListBeanX>(this,
                R.layout.rv_floor_list_item, industryList) {
            @Override
            protected void convert(ViewHolder holder, SearchShopBean.IndustryListBeanX industryListBeanX, int position) {
                holder.setText(R.id.tv_floor_name, industryListBeanX.getIndustryName());
                RelativeLayout rlFloor = holder.getView(R.id.rl_floor_item);
                ImageView ivFloor = holder.getView(R.id.iv_floor_current);
                if (position == industryNum) {
                    rlFloor.setBackgroundResource(R.color.gray_f5);
                    ivFloor.setVisibility(View.VISIBLE);
                } else {
                    rlFloor.setBackgroundColor(Color.WHITE);
                    ivFloor.setVisibility(View.GONE);
                }
            }

        };

        rvType.setAdapter(typeAdapter);
        typeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                industryNum = holder.getLayoutPosition();
                industryId = industryList.get(industryNum).getIndustryId();
                industryName = industryList.get(industryNum).getIndustryName();
                tvMarketType.setText(industryName);
                typeAdapter.notifyDataSetChanged();
                mPopupWindow.dismiss();
                page = 1;
                isRefresh = true;
                initData();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tvMarketType.setTextColor(getResources().getColor(R.color.gray_1a));
                tvMarketType.setCompoundDrawables(null, null, grayDrawable, null);
                tvMarketFloor.setTextColor(getResources().getColor(R.color.gray_1a));
                tvMarketFloor.setCompoundDrawables(null, null, grayDrawable, null);
            }
        });
    }

    @OnClick({R.id.iv_header_back, R.id.tv_market_type, R.id.tv_market_floor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_market_type:
                tvMarketType.setTextColor(getResources().getColor(R.color.red_99));
                tvMarketType.setCompoundDrawables(null, null, redDrawable, null);
                tvMarketFloor.setTextColor(getResources().getColor(R.color.gray_1a));
                tvMarketFloor.setCompoundDrawables(null, null, grayDrawable, null);
                selectIndustry();
                break;
            case R.id.tv_market_floor:
                tvMarketFloor.setTextColor(getResources().getColor(R.color.red_99));
                tvMarketFloor.setCompoundDrawables(null, null, redDrawable, null);
                tvMarketType.setTextColor(getResources().getColor(R.color.gray_1a));
                tvMarketType.setCompoundDrawables(null, null, grayDrawable, null);
                selectFloor();
                break;
        }
    }
}
