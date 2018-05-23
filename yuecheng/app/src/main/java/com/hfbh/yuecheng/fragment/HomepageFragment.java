package com.hfbh.yuecheng.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.BaseDelegateAdapter;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.ActivityBean;
import com.hfbh.yuecheng.bean.BannerBean;
import com.hfbh.yuecheng.bean.CouponBean;
import com.hfbh.yuecheng.bean.FunctionBean;
import com.hfbh.yuecheng.bean.GiftBean;
import com.hfbh.yuecheng.bean.HomepageTypeBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.ChangeMarketActivity;
import com.hfbh.yuecheng.ui.MemberCardActivity;
import com.hfbh.yuecheng.ui.SearchShopActivity;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.NetworkImageHolderView;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.view.FlowLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.indicators.BallSpinFadeLoaderIndicator;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/14 16:08
 * Email：1993911441@qq.com
 * Describe：首页
 */
public class HomepageFragment extends BaseFragment {

    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;
    @BindView(R.id.rv_homepage)
    RecyclerView rvHomepage;
    @BindView(R.id.layout_refresh_home)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_home_location)
    TextView tvHomeLocation;
    @BindView(R.id.iv_home_scan)
    ImageView ivHomeScan;


    private Unbinder unbinder;
    private HomepageTypeBean typeBean;
    //banner
    private BannerBean bannerBean;
    //功能
    private FunctionBean functionBean;
    //活动
    private ActivityBean activityBean;
    //优惠券
    private CouponBean couponBean;
    //礼品兑换
    private GiftBean giftBean;
    //模块数量
    private int count;
    private List<DelegateAdapter.Adapter> mAdapters;
    private boolean isRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        unbinder = ButterKnife.bind(this, view);
        initType();
        return view;
    }

    /**
     * 加载模块
     */
    private void initType() {
        tvHomeLocation.setText(MyApp.organizeName);

        loadingView.smoothToShow();
        OkHttpUtils.post()
                .url(Constant.HOMEPAGE_TYPE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        typeBean = GsonUtils.jsonToBean(s, HomepageTypeBean.class);
                        if (typeBean.isFlag()) {
                            initData();
                        }
                    }
                });

    }

    /**
     * 加载模块数据
     */
    private void initData() {

        for (int i = 0; i < typeBean.getData().size(); i++) {
            final int type = i;
            OkHttpUtils.post()
                    .url(Constant.HOMEPAGE_MODULE)
                    .addParams("appType", MyApp.appType)
                    .addParams("appVersion", MyApp.appVersion)
                    .addParams("organizeId", MyApp.organizeId)
                    .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                    .addParams("moduleCode", typeBean.getData().get(i).getModuleCode())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {

                            switch (typeBean.getData().get(type).getModuleCode()) {
                                case "BANNER":
                                    bannerBean = GsonUtils.jsonToBean(response, BannerBean.class);
                                    break;
                                case "FUNCTION":
                                    functionBean = GsonUtils.jsonToBean(response, FunctionBean.class);
                                    break;
                                case "COUPON":
                                    couponBean = GsonUtils.jsonToBean(response, CouponBean.class);
                                    break;
                                case "ACTIVITY":
                                    activityBean = GsonUtils.jsonToBean(response, ActivityBean.class);
                                    break;
                                case "GIFT":
                                    giftBean = GsonUtils.jsonToBean(response, GiftBean.class);
                                    break;
                            }
                            count++;
                            if (count == 5) {
                                count = 0;
                                if (isRefresh) {
                                    refreshLayout.finishRefresh();
                                    isRefresh = false;
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
                    });
        }

    }

    /**
     * 加载视图
     */
    private void initView() {
        mAdapters = new LinkedList<>();

        //初始化
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        rvHomepage.setLayoutManager(layoutManager);

        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rvHomepage.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        rvHomepage.setAdapter(delegateAdapter);

        BaseDelegateAdapter bannerAdapter = new BaseDelegateAdapter(getActivity(), new LinearLayoutHelper(),
                R.layout.layout_homepage_banner, 1, 1) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ConvenientBanner banner = holder.getView(R.id.banner_homepage);
                banner.startTurning(4000);
                List<String> bannerImg = new ArrayList<>();
                for (int i = 0; i < bannerBean.getData().size(); i++) {
                    bannerImg.add(bannerBean.getData().get(i).getAdvertPic());
                }

                banner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, bannerImg)
                        //设置两个点图片作为翻页指示器
                        .setPageIndicator(new int[]{R.drawable.indicator_normal, R.drawable.indicator_focus})
                        //设置指示器的方向
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            }
        };
        mAdapters.add(bannerAdapter);

        //功能模块
        BaseDelegateAdapter functionAdapter = new BaseDelegateAdapter(getActivity(), new GridLayoutHelper(2, 1),
                R.layout.layout_homepage_function, 1, 2) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                RecyclerView rvFunction = holder.getView(R.id.rv_homepage_function);
                rvFunction.setLayoutManager(new LinearLayoutManager(getActivity(),
                        LinearLayout.HORIZONTAL, false));

                //布局宽高
                final int width = DisplayUtils.getMetrics(getActivity()).widthPixels / 4;
                CommonAdapter<FunctionBean.DataBean> adapter = new CommonAdapter<FunctionBean
                        .DataBean>(getActivity(), R.layout.rv_founction_item, functionBean.getData()) {
                    @Override
                    protected void convert(ViewHolder holder, FunctionBean.DataBean dataBean, int position) {
                        LinearLayout llFunction = holder.getView(R.id.ll_function_item);

                        ViewGroup.LayoutParams layoutParams = llFunction.getLayoutParams();
                        layoutParams.width = width;
                        llFunction.setLayoutParams(layoutParams);

                        Glide.with(getActivity())
                                .load(dataBean.getFunctionIco())
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .into((ImageView) holder.getView(R.id.iv_function_item));
                        holder.setText(R.id.tv_function_name, dataBean.getFunctionName());
                    }
                };

                rvFunction.setAdapter(adapter);
                adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        switch (functionBean.getData().get(position).getFunctionCode()) {
                            case "STORE"://找店铺
                                startActivity(new Intent(getActivity(), SearchShopActivity.class));
                                break;
                            case "SHOPPING"://我要买
                                break;
                            case "GUIDING"://室内导航
                                break;
                            case "PLAYING"://我要玩
                                break;
                            case "MEMBER_CODE"://会员码
                                startActivity(new Intent(getActivity(), MemberCardActivity.class));
                                break;
                        }
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });

            }
        };
        mAdapters.add(functionAdapter);


        //优惠券
        initTitle("优惠券", 3);

        BaseDelegateAdapter couponAdapter = new BaseDelegateAdapter(getActivity(), new LinearLayoutHelper(),
                R.layout.rv_coupon_item, couponBean.getData().size(), 4) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                SimpleDraweeView ivCoupon = holder.getView(R.id.iv_home_coupon);
                ivCoupon.setImageURI(couponBean.getData().get(position).getCouponImage());

                holder.setText(R.id.tv_home_coupon_title, couponBean.getData().get(position).getCouponName());
                holder.setText(R.id.tv_home_coupon_content, couponBean.getData().get(position).getCouponDesc());
                holder.setText(R.id.tv_home_coupon_remain, "剩余" + couponBean.getData().get(position).getStock());

                TextView tvReceive = holder.getView(R.id.tv_home_coupon_receive);
                int needScore = couponBean.getData().get(position).getNeedScore();
                if (needScore > 0) {
                    tvReceive.setText(needScore + "积分\n领取");
                } else {
                    tvReceive.setText("免费\n领取");
                }
            }
        };
        mAdapters.add(couponAdapter);

        //积分兑换
        initTitle("积分兑换", 5);

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setPadding((int) DisplayUtils.dp2px(getActivity(), 12),
                0, (int) DisplayUtils.dp2px(getActivity(), 12), 0);
        gridLayoutHelper.setHGap((int) DisplayUtils.dp2px(getActivity(), 11));// 控制子元素之间的水平间距
        gridLayoutHelper.setBgColor(Color.WHITE);
        BaseDelegateAdapter giftAdapter = new BaseDelegateAdapter(getActivity(), gridLayoutHelper,
                R.layout.rv_gift_item, giftBean.getData().size(), 6) {
            //布局宽高
            int widthPixels = DisplayUtils.getMetrics(getActivity()).widthPixels;
            final int width = (int) ((widthPixels - DisplayUtils.dp2px(getActivity(), 35)) / 2);

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ImageView ivGift = holder.getView(R.id.iv_home_gift);
                ViewGroup.LayoutParams layoutParams = ivGift.getLayoutParams();
                layoutParams.width = width;
                layoutParams.height = width;
                ivGift.setLayoutParams(layoutParams);

                Glide.with(getActivity())
                        .load(giftBean.getData().get(position).getGiftPicturePath())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(ivGift);
                holder.setText(R.id.tv_home_gift_name, giftBean.getData().get(position).getRelateName());
                holder.setText(R.id.tv_home_gift_score, giftBean.getData().get(position).getNeedScore() + "积分");
            }
        };
        mAdapters.add(giftAdapter);

        //精彩活动
        initTitle("精彩活动", 7);
        GridLayoutHelper gridLayoutHelper1 = new GridLayoutHelper(1);
        gridLayoutHelper1.setVGap((int) DisplayUtils.dp2px(getActivity(), 5));// 控制子元素之间的水平间距

        BaseDelegateAdapter activityAdapter = new BaseDelegateAdapter(getActivity(), gridLayoutHelper1,
                R.layout.rv_activity_item, activityBean.getData().size(), 8) {

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                SimpleDraweeView ivCoupon = holder.getView(R.id.iv_home_activity);
                ivCoupon.setImageURI(activityBean.getData().get(position).getActivityPic());
                holder.setText(R.id.tv_home_activity_name, activityBean.getData().get(position).getActivityName());
                holder.setText(R.id.tv_home_activity_time, activityBean.getData().get(position)
                        .getStartTimeStr() + " - " + activityBean.getData().get(position).getEndTimeStr());

                TextView tvReceive = holder.getView(R.id.tv_home_activity_receive);
                String needScore = (String) activityBean.getData().get(position).getNeedScore();
                if (!TextUtils.isEmpty(needScore)) {
                    tvReceive.setText(needScore + "积分报名");
                } else {
                    tvReceive.setText("免费报名");
                }

                FlowLayout flowLayout = holder.getView(R.id.flow_home_activity);
                if (flowLayout.getChildCount() == 0) {
                    addTextView(flowLayout, activityBean.getData().get(position).getTags());
                }
            }
        };

        mAdapters.add(activityAdapter);
        //底部
        BaseDelegateAdapter footerAdapter = new BaseDelegateAdapter(getActivity(), new LinearLayoutHelper(),
                R.layout.layout_homepage_footer, 1, 9);
        mAdapters.add(footerAdapter);

        delegateAdapter.setAdapters(mAdapters);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000, true);
                isRefresh = true;
                initData();
            }
        });
        refreshLayout.setEnableLoadMore(false);
    }

    /**
     * 动态添加标签
     */
    private void addTextView(FlowLayout flowLayout, List<ActivityBean.DataBean.TagsBean> tagsBeans) {

        for (int i = 0; i < tagsBeans.size(); i++) {
            TextView tvChild = new TextView(getActivity());
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, (int) DisplayUtils.dp2px(getActivity(), 6), 0);
            tvChild.setLayoutParams(params);
            tvChild.setBackgroundResource(R.drawable.flowlayout_item);
            tvChild.setText(tagsBeans.get(i).getTagName());
            tvChild.setTextSize(12);
            tvChild.setTextColor(getActivity().getResources().getColor(R.color.red_e6));

            flowLayout.addView(tvChild);
        }

    }


    /**
     * @param title
     * @param type  标题
     */
    private void initTitle(final String title, int type) {
        BaseDelegateAdapter titleAdapter = new BaseDelegateAdapter(getActivity(), new LinearLayoutHelper(),
                R.layout.layout_homepage_title, 1, type) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.tv_home_title, title);
            }
        };
        mAdapters.add(titleAdapter);
    }

    public static HomepageFragment newInstance() {

        Bundle args = new Bundle();
        HomepageFragment fragment = new HomepageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.tv_home_location, R.id.iv_home_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_home_location:
                startActivity(new Intent(getActivity(), ChangeMarketActivity.class));
                break;
            case R.id.iv_home_scan:
                break;
        }
    }
}
