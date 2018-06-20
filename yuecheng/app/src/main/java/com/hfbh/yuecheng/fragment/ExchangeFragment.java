package com.hfbh.yuecheng.fragment;

import android.content.Intent;
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

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.MyGiftBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.CloseGiftActivity;
import com.hfbh.yuecheng.ui.GiftDetailActivity;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/8 18:26
 * Email：1993911441@qq.com
 * Describe：我的兑换
 */
public class ExchangeFragment extends BaseFragment {
    @BindView(R.id.rv_my_activity)
    RecyclerView rvGift;
    @BindView(R.id.layout_refresh_my_activity)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @BindView(R.id.iv_null_data)
    ImageView ivNullData;
    @BindView(R.id.tv_null_data)
    TextView tvNullData;
    @BindView(R.id.ll_null_data)
    LinearLayout llNullData;
    private Unbinder unbinder;
    //分类
    private String type;
    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    //总页数
    private int pages;
    private List<MyGiftBean.DataBean> dataList = new ArrayList<>();
    private CommonAdapter<MyGiftBean.DataBean> adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_activity, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        ivNullData.setImageResource(R.mipmap.ic_null_gift);
        tvNullData.setText("暂无兑换信息");
        viewLoading.smoothToShow();
        initData();
        return view;
    }

    private void getData() {
        type = getArguments().getString("type");
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.MY_EXCHANGE_GIFT)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .addParams("token",SharedPreUtils.getStr(getActivity(), "token"))
                .addParams("queryType", type)
                .addParams("pageNum", String.valueOf(page))
                .addParams("rewardType","GIFT")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyGiftBean giftBean = GsonUtils.jsonToBean(response, MyGiftBean.class);
                        if (giftBean.getPage() != null) {
                            pages = giftBean.getPage().getPages();
                        }

                        if (giftBean.isFlag() && giftBean.getData().size() > 0) {
                            if (isRefresh) {
                                dataList.clear();
                            }
                            dataList.addAll(giftBean.getData());

                            if (isRefresh) {
                                refreshLayout.finishRefresh();
                                isRefresh = false;
                                adapter.notifyDataSetChanged();
                            } else if (isLoadMore) {
                                refreshLayout.finishLoadMore();
                                isLoadMore = false;
                                adapter.notifyDataSetChanged();
                            } else {
                                if (viewLoading!=null){
                                    viewLoading.smoothToHide();
                                }

                                initView();
                            }
                            rvGift.setVisibility(View.VISIBLE);
                            llNullData.setVisibility(View.GONE);
                        } else {
                            refreshLayout.finishLoadMore();
                            if (page == 1) {
                                viewLoading.smoothToHide();
                                rvGift.setVisibility(View.GONE);
                                llNullData.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {
        rvGift.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter<MyGiftBean.DataBean>(getActivity(),
                R.layout.rv_my_exchange_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, MyGiftBean.DataBean dataBean, final int position) {
                SimpleDraweeView ivCoupon = holder.getView(R.id.iv_my_exchange);
                ivCoupon.setImageURI(dataBean.getCouponImage());

                holder.setText(R.id.tv_my_exchange_name, dataBean.getGainName());
                holder.setText(R.id.tv_exchange_time, "有效时间： "
                        + DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                        "yyyy.MM.dd", dataBean.getStartTime())
                        + " - "
                        + DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                        "yyyy.MM.dd", dataBean.getEndTime()));


                holder.setText(R.id.tv_exchange_cost, dataBean.getGiftPrice() + "积分");

                final TextView tvStatus = holder.getView(R.id.tv_exchange_status);
                final TextView tvJoin = holder.getView(R.id.tv_exchange_join);

                final String status = dataBean.getUseState();

                final boolean isFinish = System.currentTimeMillis() > DateUtils.getTime(
                        "yyyy-MM-dd HH:mm:ss", dataBean.getEndTime());

                if (isFinish) {
                    tvStatus.setVisibility(View.VISIBLE);
                    tvJoin.setVisibility(View.GONE);
                    tvStatus.setTextColor(getActivity().getResources().getColor(R.color.gray_9f));
                    tvStatus.setText("已失效");
                } else {
                    if (!TextUtils.isEmpty(status)) {
                        switch (status) {
                            case "UNUSE":
                                tvStatus.setVisibility(View.GONE);
                                tvJoin.setVisibility(View.VISIBLE);
                                tvJoin.setText("去核销");
                                break;
                            case "USE":
                                tvStatus.setVisibility(View.VISIBLE);
                                tvJoin.setVisibility(View.GONE);
                                tvStatus.setTextColor(getActivity().getResources().getColor(R.color.red_99));
                                tvStatus.setText("已完成");
                                break;
                        }
                    }
                }
                tvStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                tvJoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), CloseGiftActivity.class);
                        intent.putExtra("gift_id", dataList.get(position).getGainId());

                        startActivity(intent);
                    }
                });

            }
        };


        rvGift.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getActivity(), GiftDetailActivity.class);
                intent.putExtra("id", dataList.get(position).getObjectId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (page < pages) {
                    isLoadMore = true;
                    page++;
                    initData();
                } else {
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                page = 1;
                initData();
            }
        });

    }


    public static ExchangeFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        ExchangeFragment fragment = new ExchangeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
