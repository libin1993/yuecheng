package com.hfbh.yuecheng.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.BaseDelegateAdapter;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.ActivityBean;
import com.hfbh.yuecheng.bean.BannerBean;
import com.hfbh.yuecheng.bean.BroadcastBean;
import com.hfbh.yuecheng.bean.CouponBean;
import com.hfbh.yuecheng.bean.FunctionBean;
import com.hfbh.yuecheng.bean.GiftBean;
import com.hfbh.yuecheng.bean.GroupGoodsBean;
import com.hfbh.yuecheng.bean.HomepageTypeBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.bean.TopicBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.ActionDetailActivity;
import com.hfbh.yuecheng.ui.BannerInfoActivity;
import com.hfbh.yuecheng.ui.ChangeMarketActivity;
import com.hfbh.yuecheng.ui.CouponDetailActivity;
import com.hfbh.yuecheng.ui.ExchangeCouponActivity;
import com.hfbh.yuecheng.ui.ExchangeGiftActivity;
import com.hfbh.yuecheng.ui.GameActivity;
import com.hfbh.yuecheng.ui.GiftDetailActivity;
import com.hfbh.yuecheng.ui.GroupGoodsActivity;
import com.hfbh.yuecheng.ui.GroupGoodsDetailActivity;
import com.hfbh.yuecheng.ui.GuideActivity;
import com.hfbh.yuecheng.ui.LoginActivity;
import com.hfbh.yuecheng.ui.MainActivity;
import com.hfbh.yuecheng.ui.MemberCardActivity;
import com.hfbh.yuecheng.ui.NewGoodsDetailActivity;
import com.hfbh.yuecheng.ui.OrderDetailActivity;
import com.hfbh.yuecheng.ui.PayActivity;
import com.hfbh.yuecheng.ui.PopGoodsDetailActivity;
import com.hfbh.yuecheng.ui.RushGoodsActivity;
import com.hfbh.yuecheng.ui.RushGoodsDetailActivity;
import com.hfbh.yuecheng.ui.ScanCodeActivity;
import com.hfbh.yuecheng.ui.SearchShopActivity;
import com.hfbh.yuecheng.ui.ValidateActivity;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.NetworkImageHolderView;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.hfbh.yuecheng.view.FlowLayout;
import com.hfbh.yuecheng.view.PermissionDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarttop.library.utils.LogUtil;
import com.sunfusheng.marqueeview.MarqueeView;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import pub.devrel.easypermissions.EasyPermissions;

import static com.mob.tools.gui.BitmapProcessor.start;

/**
 * Author：Libin on 2018/5/14 16:08
 * Email：1993911441@qq.com
 * Describe：首页
 */
public class HomepageFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {

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
    //广播
    private BroadcastBean broadcastBean;
    //主题
    private TopicBean topicBean;
    //活动
    private ActivityBean activityBean;
    //优惠券
    private CouponBean couponBean;
    //礼品兑换
    private GiftBean giftBean;
    //团购
    private GroupGoodsBean groupBean;
    //秒杀
    private GroupGoodsBean rushBean;
    //模块数量
    private int count;
    private List<DelegateAdapter.Adapter> mAdapters;
    private boolean isRefresh;
    //相机权限
    private String[] permissionStr = {Manifest.permission.CAMERA};
    private BaseDelegateAdapter couponAdapter;
    private MarqueeView marqueeView;


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
    public void initType() {
        tvHomeLocation.setText(MyApp.organizeName);
        loadingView.smoothToShow();
        OkHttpUtils.post()
                .url(Constant.HOMEPAGE_TYPE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        typeBean = GsonUtils.jsonToBean(s, HomepageTypeBean.class);
                        if (typeBean.isFlag() && typeBean.getData() != null && typeBean.getData().size() > 0) {
                            initData();
                        } else {
                            rvHomepage.setAdapter(null);
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
                    .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                    .addParams("moduleCode", typeBean.getData().get(i).getModuleCode())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean flag = jsonObject.getBoolean("flag");
                                if (flag) {
                                    switch (typeBean.getData().get(type).getModuleCode()) {
                                        case "BANNER":
                                            bannerBean = GsonUtils.jsonToBean(response, BannerBean.class);
                                            break;
                                        case "MAIN":
                                            functionBean = GsonUtils.jsonToBean(response, FunctionBean.class);
                                            break;
                                        case "EXTRA":
                                            break;
                                        case "BROAD":
                                            broadcastBean = GsonUtils.jsonToBean(response, BroadcastBean.class);
                                            break;
                                        case "TOPIC":
                                            topicBean = GsonUtils.jsonToBean(response, TopicBean.class);
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
                                        case "GROUPON":
                                            groupBean = GsonUtils.jsonToBean(response, GroupGoodsBean.class);
                                            break;
                                        case "SECKILL":
                                            rushBean = GsonUtils.jsonToBean(response, GroupGoodsBean.class);
                                            break;
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            count++;
                            if (count == typeBean.getData().size()) {
                                loadingView.smoothToHide();
                                count = 0;
//                                if (isRefresh) {
//                                    refreshLayout.finishRefresh();
//                                    isRefresh = false;
//                                    couponAdapter.notifyDataSetChanged();
//                                    for (int j = 0; j < mAdapters.size(); j++) {
//                                        BaseDelegateAdapter adapter = (BaseDelegateAdapter) mAdapters.get(j);
//                                        adapter.notifyDataSetChanged();
//                                    }
//                                } else {
//                                    initView();
//                                }
                                initView();
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

        if (bannerBean != null && bannerBean.getData() != null && bannerBean.getData().size() > 0) {
//            BannerAdapter bannerAdapter = new BannerAdapter(getActivity(), new LinearLayoutHelper(),
//                    R.layout.layout_homepage_banner, bannerBean.getData(), 1, 1);
            BaseDelegateAdapter bannerAdapter = new BaseDelegateAdapter(getActivity(), new LinearLayoutHelper(),
                    R.layout.layout_homepage_banner, 1, 1) {
                @Override
                public void onBindViewHolder(ViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    ConvenientBanner banner = holder.getView(R.id.banner_homepage);
                    banner.startTurning(5000);
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

                    banner.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            String moduleCode = bannerBean.getData().get(position).getModuleCode();
                            int id = bannerBean.getData().get(position).getObjectId();
                            if (TextUtils.isEmpty(moduleCode)) {
                                Intent intent = new Intent(getActivity(), BannerInfoActivity.class);
                                intent.putExtra("url", bannerBean.getData().get(position).getAdvertUrl());
                                startActivity(intent);
                            } else {
                                switch (moduleCode) {
                                    case "ACTIVITY":
                                        Intent intent1 = new Intent(getActivity(), ActionDetailActivity.class);
                                        intent1.putExtra("activity_id", id);
                                        startActivity(intent1);
                                        break;
                                    case "COUPON":
                                        Intent intent2 = new Intent(getActivity(), CouponDetailActivity.class);
                                        intent2.putExtra("coupon_id", id);
                                        startActivity(intent2);
                                        break;
                                    case "GIFT":
                                        Intent intent3 = new Intent(getActivity(), GiftDetailActivity.class);
                                        intent3.putExtra("gift_id", id);
                                        startActivity(intent3);
                                        break;
                                    case "GROUPON":
                                        Intent intent4 = new Intent(getActivity(), GroupGoodsDetailActivity.class);
                                        intent4.putExtra("goods_id", id);
                                        startActivity(intent4);
                                        break;
                                    case "SECKILL":
                                        Intent intent5 = new Intent(getActivity(), RushGoodsDetailActivity.class);
                                        intent5.putExtra("goods_id", id);
                                        startActivity(intent5);
                                        break;
                                    case "SPECIAL":
                                        Intent intent6 = new Intent(getActivity(), PopGoodsDetailActivity.class);
                                        intent6.putExtra("goods_id", id);
                                        startActivity(intent6);
                                        break;
                                    case "FIRSTLOOK":
                                        Intent intent7 = new Intent(getActivity(), NewGoodsDetailActivity.class);
                                        intent7.putExtra("goods_id", id);
                                        startActivity(intent7);
                                        break;

                                }
                            }
                        }
                    });
                }
            };
            mAdapters.add(bannerAdapter);
        }

        if (functionBean != null && functionBean.getData() != null && functionBean.getData().size() > 0) {
            //功能模块
//            FunctionAdapter functionAdapter = new FunctionAdapter(getActivity(), new
//                    GridLayoutHelper(1), R.layout.layout_homepage_function,
//                    functionBean.getData(), 1, 2);

            //布局宽高
            int size = functionBean.getData().size();
            int spanCount = 4;
            if (size <= 4) {
                spanCount = size;
            }
            final int width = DisplayUtils.getMetrics(getActivity()).widthPixels / spanCount;

            BaseDelegateAdapter functionAdapter = new BaseDelegateAdapter(getActivity(), new
                    GridLayoutHelper(1), R.layout.layout_homepage_function,
                    1, 2) {
                @Override
                public void onBindViewHolder(ViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    RecyclerView rvFunction = holder.getView(R.id.rv_homepage_function);
                    rvFunction.setLayoutManager(new LinearLayoutManager(getActivity(),
                            LinearLayout.HORIZONTAL, false));


                    CommonAdapter<FunctionBean.DataBean> adapter = new CommonAdapter<FunctionBean
                            .DataBean>(getActivity(),
                            R.layout.rv_founction_item, functionBean.getData()) {
                        @Override
                        protected void convert(ViewHolder holder, FunctionBean.DataBean dataBean, int position) {
                            LinearLayout llFunction = holder.getView(R.id.ll_function_item);

                            ViewGroup.LayoutParams layoutParams = llFunction.getLayoutParams();
                            layoutParams.width = width;
                            llFunction.setLayoutParams(layoutParams);

                            Glide.with(getActivity())
                                    .load(dataBean.getFunctionIco())
                                    .dontAnimate()
                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                    .placeholder(R.mipmap.img_place_circle)
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
                                    startActivity(new Intent(getActivity(), GuideActivity.class));
                                    break;
                                case "PLAYING"://我要玩
                                    break;
                                case "MEMBER_CODE"://会员码
                                    toMemberCard();
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
        }


        if (broadcastBean != null && broadcastBean.getData().size() > 0) {
            if (broadcastBean.getData() != null && broadcastBean.getData().size() > 0) {
                final List<String> dataList = new ArrayList<>();
                for (int i = 0; i < broadcastBean.getData().size(); i++) {
                    dataList.add(broadcastBean.getData().get(i).getContent());
                }
                BaseDelegateAdapter broadAdapter = new BaseDelegateAdapter(getActivity(), new LinearLayoutHelper(),
                        R.layout.layout_homepage_broad, 1, 3) {
                    @Override
                    public void onBindViewHolder(ViewHolder holder, int position) {
                        super.onBindViewHolder(holder, position);
                        marqueeView = holder.getView(R.id.marqueeView);
                        marqueeView.startWithList(dataList);
                    }
                };
                mAdapters.add(broadAdapter);
            }
        }

        if (topicBean != null && topicBean.getData() != null && topicBean.getData().size() > 0) {
            BaseDelegateAdapter topicAdapter = new BaseDelegateAdapter(getActivity(), new LinearLayoutHelper(),
                    R.layout.layout_homepage_topic, 1, 12) {
                @Override
                public void onBindViewHolder(ViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    SimpleDraweeView ivTopic = holder.getView(R.id.iv_homepage_topic);
                    ivTopic.setImageURI(topicBean.getData().get(0).getTopicPic());

                }
            };
            mAdapters.add(topicAdapter);


            if (topicBean.getData().get(0).getActivityList().size() > 0) {
                GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
                gridLayoutHelper.setPadding(0, 0, 0,
                        (int) DisplayUtils.dp2px(getActivity(), 1));
                gridLayoutHelper.setHGap((int) DisplayUtils.dp2px(getActivity(), 1));// 控制子元素之间的水平间距
                gridLayoutHelper.setAutoExpand(false);
                gridLayoutHelper.setBgColor(Color.WHITE);


                BaseDelegateAdapter actionAdapter = new BaseDelegateAdapter(getActivity(), gridLayoutHelper,
                        R.layout.layout_homepage_game, topicBean.getData().get(0).getActivityList().size(), 13) {
                    @Override
                    public void onBindViewHolder(ViewHolder holder, final int position) {
                        super.onBindViewHolder(holder, position);

                        ImageView ivActivity = holder.getView(R.id.iv_homepage_game);

                        Glide.with(getActivity())
                                .load(topicBean.getData().get(0).getActivityList().get(position).getPic())
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .dontAnimate()
                                .placeholder(R.mipmap.img_place)
                                .into(ivActivity);

                        holder.setText(R.id.tv_action_title, topicBean.getData().get(0).getActivityList().get(position).getName());

                        TextView tvSubtitle = holder.getView(R.id.tv_action_sub_title);
//                        if (topicBean.getData().get(0).getActivityList().get(position).getActivityType().equals("NONEED")) {
//                            tvSubtitle.setText("");
//                        } else {
//                            String status = topicBean.getData().get(0).getActivityList().get(position).getMemberSignupState();
//                            tvSubtitle.setText(status);
//                        }
//
                        switch (topicBean.getData().get(0).getActivityList().get(position).getActivityType()) {
                            case "NONEED":
                                tvSubtitle.setText("无需报名");
                                break;
                            case "FREE":
                                tvSubtitle.setText("免费参加");
                                break;
                            case "SCORE":
                                tvSubtitle.setText("积分参加");
                                break;
                            case "CASH":
                                tvSubtitle.setText("现金参加");
                                break;
                        }


                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Intent intent;
//                                if ("去参加".equals(status)) {
//                                    if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
//                                        intent = new Intent(getActivity(), CloseActionActivity.class);
//                                        intent.putExtra("activity_id", topicBean.getData().get(0).getActivityList().get(position).getActivityId());
//                                    } else {
//                                        intent = new Intent(getActivity(), LoginActivity.class);
//                                    }
//
//                                } else {
//                                    intent = new Intent(getActivity(), ActionDetailActivity.class);
//                                    intent.putExtra("activity_id", topicBean.getData().get(0).getActivityList().get(position).getActivityId());
//                                }
//
//                                startActivity(intent);

                                Intent intent = new Intent(getActivity(), ActionDetailActivity.class);
                                intent.putExtra("activity_id", topicBean.getData().get(0)
                                        .getActivityList().get(position).getActivityId());
                                startActivity(intent);
                            }
                        });
                    }
                };
                mAdapters.add(actionAdapter);
            }

            if (topicBean.getData().get(0).getGameList().size() > 0) {
                GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
                gridLayoutHelper.setPadding(0, 0, 0,
                        (int) DisplayUtils.dp2px(getActivity(), 1));
                gridLayoutHelper.setHGap((int) DisplayUtils.dp2px(getActivity(), 1));// 控制子元素之间的水平间距
                gridLayoutHelper.setAutoExpand(false);
                gridLayoutHelper.setBgColor(Color.WHITE);


                BaseDelegateAdapter gameAdapter = new BaseDelegateAdapter(getActivity(), gridLayoutHelper,
                        R.layout.layout_homepage_game, topicBean.getData().get(0).getGameList().size(), 14) {
                    @Override
                    public void onBindViewHolder(ViewHolder holder, final int position) {
                        super.onBindViewHolder(holder, position);

                        holder.setText(R.id.tv_action_title, topicBean.getData().get(0)
                                .getGameList().get(position).getName());
                        holder.setText(R.id.tv_action_sub_title, topicBean.getData().get(0)
                                .getGameList().get(position).getByname());
                        ImageView ivActivity = holder.getView(R.id.iv_homepage_game);


                        Glide.with(getActivity())
                                .load(topicBean.getData().get(0).getGameList().get(position).getPic())
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .dontAnimate()
                                .placeholder(R.mipmap.img_place)
                                .into(ivActivity);
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
                                    Intent intent = new Intent(getActivity(), GameActivity.class);
                                    intent.putExtra("game_url", topicBean.getData().get(0)
                                            .getGameList().get(position).getUrl());
                                    intent.putExtra("app_id", topicBean.getData().get(0)
                                            .getGameList().get(position).getAppId());
                                    intent.putExtra("private_token", topicBean.getData()
                                            .get(0).getGameList().get(position).getPrivateToken());
                                    startActivity(intent);
                                } else {
                                    startActivity(new Intent(getActivity(), LoginActivity.class));
                                }

                            }
                        });
                    }
                };
                mAdapters.add(gameAdapter);
            }
        }

        final int width = (int) (DisplayUtils.getMetrics(getActivity()).widthPixels / 2 - DisplayUtils.dp2px(getActivity(),17));

        BaseDelegateAdapter groupAdapter = new BaseDelegateAdapter(getActivity(), new LinearLayoutHelper(),
                R.layout.layout_homepage_goods, 1, 15) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                final LinearLayout llRushGoods = holder.getView(R.id.ll_rush_goods);
                final LinearLayout llEndTime = holder.getView(R.id.ll_end_time);
                final TextView tvHour = holder.getView(R.id.tv_goods_hour);
                final TextView tvMinute = holder.getView(R.id.tv_goods_minute);
                final TextView tvSecond = holder.getView(R.id.tv_goods_second);
                LinearLayout llGroupGoods = holder.getView(R.id.ll_group_goods);
                TextView tvLowestPrice = holder.getView(R.id.tv_lowest_price);
                RecyclerView rvRushGoods = holder.getView(R.id.rv_rush_goods);
                RecyclerView rvGroupGoods = holder.getView(R.id.rv_group_goods);


                llRushGoods.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), RushGoodsActivity.class));
                    }
                });

                llGroupGoods.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), GroupGoodsActivity.class));
                    }
                });


                if (rushBean != null && rushBean.getData().size() > 0) {

                    llRushGoods.setVisibility(View.VISIBLE);

                    ViewGroup.LayoutParams rvParams = rvRushGoods.getLayoutParams();
                    rvParams.width = width;
                    rvRushGoods.setLayoutParams(rvParams);

                    boolean isFinish = !TextUtils.isEmpty(rushBean.getData().get(0).getEndTime()) &&
                            System.currentTimeMillis() > DateUtils.getTime("yyyy-MM-dd HH:mm:ss",
                                    rushBean.getData().get(0).getEndTime());

                    boolean isStart = !TextUtils.isEmpty(rushBean.getData().get(0).getStartTime()) &&
                            System.currentTimeMillis() > DateUtils.getTime("yyyy-MM-dd HH:mm:ss",
                                    rushBean.getData().get(0).getStartTime());
                    if (isStart && !isFinish) {
                        llEndTime.setVisibility(View.VISIBLE);
                        long time = DateUtils.getTime("yyyy-MM-dd HH:mm:ss",
                                rushBean.getData().get(0).getEndTime()) - System.currentTimeMillis();

                        new CountDownTimer(time, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                tvHour.setText(String.valueOf(millisUntilFinished / (1000 * 60 * 60)));
                                tvMinute.setText(String.valueOf(millisUntilFinished % (1000 * 60 * 60) / (1000 * 60)));
                                tvSecond.setText(String.valueOf(millisUntilFinished % (1000 * 60) / 1000));
                            }

                            @Override
                            public void onFinish() {
                                llEndTime.setVisibility(View.GONE);
                            }
                        }.start();
                    }


                    rvRushGoods.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    CommonAdapter<GroupGoodsBean.DataBean> rushAdapter = new CommonAdapter
                            <GroupGoodsBean.DataBean>(getActivity(), R.layout.rv_homepage_group_item, rushBean.getData()) {
                        @Override
                        protected void convert(ViewHolder holder, GroupGoodsBean.DataBean dataBean, int position) {
                            SimpleDraweeView ivGoods = holder.getView(R.id.iv_homepage_group);
                            ivGoods.setImageURI(dataBean.getPicturePath());

                        }
                    };
                    rvRushGoods.setAdapter(rushAdapter);

                    rushAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            startActivity(new Intent(getActivity(), RushGoodsActivity.class));
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });

                }

                if (groupBean != null && groupBean.getData().size() > 0) {

                    llGroupGoods.setVisibility(View.VISIBLE);


                    ViewGroup.LayoutParams rvParams = rvGroupGoods.getLayoutParams();
                    rvParams.width = width;
                    rvGroupGoods.setLayoutParams(rvParams);

                    tvLowestPrice.setVisibility(View.VISIBLE);
                    tvLowestPrice.setText("低至" + DisplayUtils.isInteger(groupBean.getData().get(0).getNowPrice()) + "元");


                    rvGroupGoods.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    CommonAdapter<GroupGoodsBean.DataBean> groupAdapter = new CommonAdapter
                            <GroupGoodsBean.DataBean>(getActivity(), R.layout.rv_homepage_group_item, groupBean.getData()) {
                        @Override
                        protected void convert(ViewHolder holder, GroupGoodsBean.DataBean dataBean, int position) {
                            SimpleDraweeView ivGoods = holder.getView(R.id.iv_homepage_group);
                            ivGoods.setImageURI(dataBean.getPicturePath());
                        }
                    };
                    rvGroupGoods.setAdapter(groupAdapter);
                    groupAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            startActivity(new Intent(getActivity(), GroupGoodsActivity.class));
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                }

            }
        };
        mAdapters.add(groupAdapter);


        if (couponBean != null && couponBean.getData() != null && couponBean.getData().size() > 0) {

            //优惠券
            initTitle("优惠券", 5);

            LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
            linearLayoutHelper.setBgColor(Color.WHITE);
//            linearLayoutHelper.setDividerHeight((int) DisplayUtils.dp2px(getActivity(), 15));

            couponAdapter = new BaseDelegateAdapter(getActivity(), linearLayoutHelper,
                    R.layout.rv_coupon_item, couponBean.getData().size(), 6) {
                @Override
                public void onBindViewHolder(final ViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);
                    SimpleDraweeView ivCoupon = holder.getView(R.id.iv_home_coupon);
                    ivCoupon.setImageURI(couponBean.getData().get(position).getCouponImage());

                    TextView tvCouponName = holder.getView(R.id.tv_home_coupon_title);


                    if (couponBean.getData().get(position).getCouponTypeKind() != null &&
                            couponBean.getData().get(position).getCouponTypeKind().equals("VOUCHER")) {
                        if (couponBean.getData().get(position).getListCouponShop() != null &&
                                couponBean.getData().get(position).getListCouponShop().size() > 0) {
                            StringBuilder shop = new StringBuilder();
                            for (int i = 0; i < couponBean.getData().get(position).getListCouponShop().size(); i++) {
                                if (i < couponBean.getData().get(position).getListCouponShop().size() - 1) {
                                    shop.append(couponBean.getData().get(position).getListCouponShop()
                                            .get(i).getShopName()).append("、");
                                } else {
                                    shop.append(couponBean.getData().get(position).getListCouponShop()
                                            .get(i).getShopName());
                                }
                            }

                            holder.setText(R.id.tv_home_coupon_content, "满" +
                                    DisplayUtils.isInteger(couponBean.getData().get(position)
                                            .getServiceAmount()) + "元可用,限" + shop.toString());
                        } else {
                            holder.setText(R.id.tv_home_coupon_content, "满" +
                                    DisplayUtils.isInteger(couponBean.getData().get(position)
                                            .getServiceAmount()) + "元可用");
                        }

                    } else {
                        holder.setText(R.id.tv_home_coupon_content, couponBean.getData().get(position).getUseRange());
                    }


                    holder.setText(R.id.tv_home_coupon_remain, "剩余" + couponBean.getData()
                            .get(position).getBalanceNum());

                    TextView tvReceive = holder.getView(R.id.tv_home_coupon_receive);

                    String accessType = couponBean.getData().get(position).getAccessType();
                    double needScore = couponBean.getData().get(position).getAccessValue();


                    if (couponBean.getData().get(position).getCouponTypeCy() == 9) {
                        //返利
                        double rebate = 0.015;
                        if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
                            switch (SharedPreUtils.getStr(getActivity(), "member_card")) {
                                case "VIP积分卡":
                                    rebate = 0.015;
                                    break;
                                case "三星贵宾卡":
                                    rebate = 0.02;
                                    break;
                                case "五星贵宾卡":
                                    rebate = 0.03;
                                    break;
                            }
                        }

                        if (!TextUtils.isEmpty(accessType) && accessType.equals("POINT")) {
                            tvCouponName.setText(DisplayUtils.isInteger(needScore * rebate)
                                    + "元-" + couponBean.getData().get(position).getCouponName());
                        } else {
                            tvCouponName.setText(couponBean.getData().get(position).getCouponName());
                        }

                    } else {
                        tvCouponName.setText(DisplayUtils.isInteger(couponBean.getData().get(position)
                                .getCouponValue()) + "元-" + couponBean.getData().get(position).getCouponName());
                    }


                    if (couponBean.getData().get(position).getBalanceNum() > 0) {

                        tvReceive.setBackgroundResource(R.color.red_99);
                        tvReceive.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray_e0));

                        int limitNum = couponBean.getData().get(position).getLimitNum();
                        int getNum = couponBean.getData().get(position).getMemberBroughtNum();

                        if (limitNum == 0) {
                            if (!TextUtils.isEmpty(accessType)) {
                                switch (accessType) {
                                    case "FREE":
                                        tvReceive.setText("免费\n领取");
                                        break;
                                    case "POINT":
                                        tvReceive.setText(DisplayUtils.isInteger(needScore) + "积分\n领取");
                                        break;
                                    case "BUY":
                                        tvReceive.setText(DisplayUtils.isInteger(needScore) + "元\n领取");
                                        break;
                                }
                            }
                        } else {
                            if (getNum > 0 && getNum >= limitNum) {
                                tvReceive.setText("已领取");
                            } else {
                                if (!TextUtils.isEmpty(accessType)) {
                                    switch (accessType) {
                                        case "FREE":
                                            tvReceive.setText("免费\n领取");
                                            break;
                                        case "POINT":
                                            tvReceive.setText(DisplayUtils.isInteger(needScore) + "积分\n领取");
                                            break;
                                        case "BUY":
                                            tvReceive.setText(DisplayUtils.isInteger(needScore) + "元\n领取");
                                            break;
                                    }
                                }
                            }
                        }
                    } else {
                        tvReceive.setBackgroundResource(R.color.gray_9f);
                        tvReceive.setTextColor(Color.WHITE);
                        tvReceive.setText("已抢光");
                    }


                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), CouponDetailActivity.class);
                            intent.putExtra("coupon_id", couponBean.getData().get(position).getObjectId());
                            startActivity(intent);
                        }
                    });
                    tvReceive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
                                exchangeCoupon(position);
                            } else {
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                            }
                        }
                    });
                }
            };
            mAdapters.add(couponAdapter);
        }


        if (giftBean != null && giftBean.getData() != null && giftBean.getData().size() > 0) {

            //积分兑换
            initTitle("积分兑礼", 7);

            GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
            gridLayoutHelper.setPadding((int) DisplayUtils.dp2px(getActivity(), 12),
                    0, (int) DisplayUtils.dp2px(getActivity(), 12), 0);
            gridLayoutHelper.setHGap((int) DisplayUtils.dp2px(getActivity(), 11));// 控制子元素之间的水平间距
            gridLayoutHelper.setAutoExpand(false);
            gridLayoutHelper.setBgColor(Color.WHITE);

            BaseDelegateAdapter giftAdapter = new BaseDelegateAdapter(getActivity(), gridLayoutHelper,
                    R.layout.rv_gift_item, giftBean.getData().size(), 8) {
                @Override
                public void onBindViewHolder(ViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);
                    SimpleDraweeView ivGift = holder.getView(R.id.iv_home_gift);


                    ivGift.setImageURI(giftBean.getData().get(position).getGiftPicturePath());

                    holder.setText(R.id.tv_home_gift_name, giftBean.getData().get(position).getRelateName());
                    holder.setText(R.id.tv_home_gift_score, DisplayUtils.isInteger(
                            giftBean.getData().get(position).getNeedScore()) + "积分");

                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), GiftDetailActivity.class);
                            intent.putExtra("gift_id", giftBean.getData().get(position).getObjectId());
                            startActivity(intent);
                        }
                    });
                }
            };
            mAdapters.add(giftAdapter);
        }


        if (activityBean != null && activityBean.getData() != null && activityBean.getData().size() > 0) {

            //精彩活动
            initTitle("精彩活动", 9);

            BaseDelegateAdapter activityAdapter = new BaseDelegateAdapter(getActivity(), new LinearLayoutHelper(),
                    R.layout.rv_activity_item, activityBean.getData().size(), 10) {

                @Override
                public void onBindViewHolder(ViewHolder holder, final int position) {
                    super.onBindViewHolder(holder, position);
                    SimpleDraweeView ivCoupon = holder.getView(R.id.iv_home_activity);
                    ivCoupon.setImageURI(activityBean.getData().get(position).getActivityPic());
                    holder.setText(R.id.tv_home_activity_name, activityBean.getData().get(position).getActivityName());
                    holder.setText(R.id.tv_home_activity_time, DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                            "yyyy.MM.dd", activityBean.getData().get(position).getStartTime()) + " - " +
                            DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                                    "yyyy.MM.dd", activityBean.getData().get(position).getEndTime()));

                    TextView tvReceive = holder.getView(R.id.tv_home_activity_receive);
                    final boolean isFinish = System.currentTimeMillis() > DateUtils.getTime(
                            "yyyy-MM-dd HH:mm:ss", activityBean.getData().get(position).getEndTime());

                    final boolean isStart = System.currentTimeMillis() >= DateUtils.getTime(
                            "yyyy-MM-dd HH:mm:ss", activityBean.getData().get(position).getVoteStartTime());
                    final boolean isEnroll = activityBean.getData().get(position).isSignup();

                    if (!TextUtils.isEmpty(activityBean.getData().get(position).getAcivityType())
                            && activityBean.getData().get(position).getAcivityType().equals("NONEED")) {
                        tvReceive.setVisibility(View.GONE);
                    } else {
                        tvReceive.setVisibility(View.VISIBLE);
                        if (!isFinish) {
                            if (isStart) {
                                tvReceive.setBackgroundResource(R.drawable.bound_red_15dp);
                                if (isEnroll) {
                                    tvReceive.setText("去参加");
                                } else {
                                    if (!TextUtils.isEmpty(activityBean.getData().get(position).getAcivityType())) {
                                        switch (activityBean.getData().get(position).getAcivityType()) {
                                            case "FREE":
                                                tvReceive.setText("免费报名");
                                                break;
                                            case "SCORE":
                                                tvReceive.setText(DisplayUtils.isInteger(activityBean
                                                        .getData().get(position).getNeedScore()) + "积分报名");
                                                break;
                                            case "CASH":
                                                tvReceive.setText("¥" + DisplayUtils.isInteger(activityBean
                                                        .getData().get(position).getAccessValue()) + "报名");
                                                break;
                                        }
                                    }

                                }
                            } else {
                                tvReceive.setText("待报名");
                                tvReceive.setBackgroundResource(R.drawable.bound_gray_15dp);
                            }
                        } else {
                            tvReceive.setText("已结束");
                            tvReceive.setBackgroundResource(R.drawable.bound_gray_15dp);
                        }
                    }


                    FlowLayout flowLayout = holder.getView(R.id.flow_home_activity);
                    flowLayout.removeAllViews();
                    if (activityBean.getData().get(position).getTags() != null &&
                            activityBean.getData().get(position).getTags().size() > 0) {
                        addTextView(flowLayout, activityBean.getData().get(position).getTags());
                    }

                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ActionDetailActivity.class);
                            intent.putExtra("activity_id", activityBean.getData().get(position).getObjectId());
                            startActivity(intent);
                        }
                    });


//                    tvReceive.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if (!isFinish && isStart) {
//                                Intent intent;
//                                if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
//                                    if (isEnroll) {
//                                        intent = new Intent(getActivity(), CloseActionActivity.class);
//                                    } else {
//                                        intent = new Intent(getActivity(), EnrollActionActivity.class);
//                                    }
//                                    intent.putExtra("activity_id", activityBean.getData().get(position).getObjectId());
//                                } else {
//                                    intent = new Intent(getActivity(), LoginActivity.class);
//                                }
//                                startActivity(intent);
//                            }
//
//                        }
//                    });

                }
            };

            mAdapters.add(activityAdapter);
        }

        //底部
        BaseDelegateAdapter footerAdapter = new BaseDelegateAdapter(getActivity(), new LinearLayoutHelper(),
                R.layout.layout_homepage_footer, 1, 11);
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
     * 领取优惠券
     */
    private void exchangeCoupon(final int position) {
        OkHttpUtils.post()
                .url(Constant.EXCHANGE_COUPON)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                .addParams("cyCouponId", String.valueOf(couponBean.getData().get(position).getCouponTypeCy()))
                .addParams("couponId", String.valueOf(couponBean.getData().get(position).getObjectId()))
                .addParams("exchangeValue", String.valueOf(couponBean.getData().get(position).getAccessValue()))
                .addParams("exchangeNum", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            boolean flag = jsonObject.getBoolean("flag");
                            String msg = jsonObject.getString("msg");
                            ToastUtils.showToast(getActivity(), msg);
                            if (flag) {
                                int data = jsonObject.getInt("data");
                                couponBean.getData().get(position).setBalanceNum(data);
                                couponAdapter.notifyDataSetChanged();
                            } else if (jsonObject.getInt("code") == 4002) {
                                SharedPreUtils.deleteStr(getActivity(), "is_login");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    /**
     * 会员卡
     */
    private void toMemberCard() {

        if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
            isSetPayPwd();
        } else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }

    }


    /**
     * 是否设置支付密码
     */
    private void isSetPayPwd() {
        OkHttpUtils.post()
                .url(Constant.IS_SET_PAY_PWD)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);

                        if (responseBean.isFlag()) {
                            startActivity(new Intent(getActivity(), MemberCardActivity.class));
                        } else {
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(getActivity(), "is_login");
                            } else {
                                setPayPwd();
                            }
                        }
                    }
                });
    }

    /**
     * 设置支付密码
     */
    private void setPayPwd() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(),
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("提示");
        dialog.setMessage("您尚未设置支付密码，是否前去设置？");
        //为“确定”按钮注册监听事件
        dialog.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), ValidateActivity.class);
                intent.putExtra("type", "bind");
                startActivity(intent);

            }
        });

        //为“取消”按钮注册监听事件
        dialog.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();
    }


    /**
     * 动态添加标签
     */
    private void addTextView(FlowLayout flowLayout, List<ActivityBean.DataBean.TagsBean> tagsBeans) {

        for (int i = 0; i < tagsBeans.size(); i++) {
            TextView tvChild = new TextView(getActivity());
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, (int) DisplayUtils.dp2px(getActivity(), 6),
                    (int) DisplayUtils.dp2px(getActivity(), 2));
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
    private void initTitle(final String title, final int type) {
        BaseDelegateAdapter titleAdapter = new BaseDelegateAdapter(getActivity(), new LinearLayoutHelper(),
                R.layout.layout_homepage_title, 1, type) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                LinearLayout linearLayout = holder.getView(R.id.ll_home_title);
                TextView tvTitle = holder.getView(R.id.tv_home_title);
                tvTitle.setText(title);

                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (type) {
                            case 5:
                                startActivity(new Intent(getActivity(), ExchangeCouponActivity.class));
                                break;
                            case 7:
                                startActivity(new Intent(getActivity(), ExchangeGiftActivity.class));
                                break;
                            case 9:
                                EventBus.getDefault().post("activity");
                                break;
                        }
                    }
                });

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
                if (!EasyPermissions.hasPermissions(getActivity(), permissionStr)) {
                    EasyPermissions.requestPermissions(this, "", 123, permissionStr);
                } else {
                    if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
                        startActivity(new Intent(getActivity(), ScanCodeActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    }

                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        PermissionDialog.showPermissionDialog(getActivity(), "相机");
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((MainActivity) getActivity()).isBack) {
            initType();
        }

        if (marqueeView != null) {
            marqueeView.startFlipping();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (marqueeView != null) {
            marqueeView.stopFlipping();
        }
    }
}
