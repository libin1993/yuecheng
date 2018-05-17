package com.hfbh.yuecheng.fragment;

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
import com.hfbh.yuecheng.utils.SharedPreUtils;
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
 * Describe：
 */
public class GoodsFragment extends BaseFragment {
    @BindView(R.id.rv_discovery_goods)
    RecyclerView rvGoods;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;
    private Unbinder unbinder;

    private int count;
    private List<GoodsBean.DataBean> popGoods = new ArrayList<>();
    private List<GoodsBean.DataBean> newGoods = new ArrayList<>();
    List<DelegateAdapter.Adapter> mAdapters;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);
        unbinder = ButterKnife.bind(this, view);
        loadingView.setIndicator(new BallSpinFadeLoaderIndicator());
        loadingView.setIndicatorColor(Color.GRAY);
        loadingView.smoothToShow();
        initData("SPECIAL");
        initData("FIRSTLOOK");

        return view;
    }

    private void initData(final String type) {
        OkHttpUtils.post()
                .url(Constant.DISCOVERY_GOODS)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getParentFragment().getActivity(), "hash"))
                .addParams("commodityType", type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        GoodsBean goodsBean = GsonUtils.jsonToBean(response, GoodsBean.class);
                        switch (type) {
                            case "SPECIAL":
                                if (goodsBean.getData() != null && goodsBean.getData().size() > 0) {
                                    if (goodsBean.getData().size() > 4) {
                                        for (int i = 0; i < 4; i++) {
                                            popGoods.add(goodsBean.getData().get(i));
                                        }
                                    } else {
                                        popGoods.addAll(goodsBean.getData());
                                    }

                                }
                                break;
                            case "FIRSTLOOK":
                                if (goodsBean.getData() != null && goodsBean.getData().size() > 0) {
                                    if (goodsBean.getData().size() > 5) {
                                        for (int i = 0; i < 5; i++) {
                                            newGoods.add(goodsBean.getData().get(i));
                                        }
                                    } else {
                                        newGoods.addAll(goodsBean.getData());
                                    }
                                }
                                break;
                        }
                        count++;
                        if (count == 2) {
                            loadingView.smoothToHide();
                            initView();
                            count = 0;
                        }
                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {

        mAdapters = new LinkedList<>();

        //初始化
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getParentFragment().getActivity());
        rvGoods.setLayoutManager(layoutManager);

        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rvGoods.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        rvGoods.setAdapter(delegateAdapter);

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


        BaseDelegateAdapter footerAdapter = new BaseDelegateAdapter(getParentFragment().getActivity(), new LinearLayoutHelper(),
                R.layout.layout_homepage_footer, 1, 5);
        mAdapters.add(footerAdapter);

        delegateAdapter.setAdapters(mAdapters);

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
