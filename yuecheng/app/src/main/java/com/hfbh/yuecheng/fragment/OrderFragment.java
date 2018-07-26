package com.hfbh.yuecheng.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
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
import com.hfbh.yuecheng.bean.ActivityListBean;
import com.hfbh.yuecheng.bean.MyCouponBean;
import com.hfbh.yuecheng.bean.MyOrderBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.ActionDetailActivity;
import com.hfbh.yuecheng.ui.OrderDetailActivity;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
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
 * Author：Libin on 2018/7/26 09:46
 * Email：1993911441@qq.com
 * Describe：我的订单
 */
public class OrderFragment extends BaseFragment {
    @BindView(R.id.rv_my_activity)
    RecyclerView rvOrder;
    @BindView(R.id.layout_refresh_my_activity)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_null_data)
    ImageView ivNullData;
    @BindView(R.id.tv_null_data)
    TextView tvNullData;
    @BindView(R.id.ll_null_data)
    LinearLayout llNullData;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    Unbinder unbinder;

    //分类
    private String type;
    //当前页
    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    //总页数
    private int pages;

    private List<MyOrderBean.DataBean> orderList = new ArrayList<>();
    private CommonAdapter<MyOrderBean.DataBean> adapter;

    private SparseArray<CountDownTimer> countDownMap = new SparseArray<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_activity, container, false);
        unbinder = ButterKnife.bind(this, view);
        ivNullData.setImageResource(R.mipmap.ic_null_order);
        tvNullData.setText("暂无相关订单");
        viewLoading.smoothToShow();
        getData();
        initView();
        initData();
        return view;
    }

    private void getData() {
        type = getArguments().getString("type");
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.ORDER_LIST)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                .addParams("pageNum", String.valueOf(page))
                .addParams("state", type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyOrderBean orderBean = GsonUtils.jsonToBean(response, MyOrderBean.class);
                        if (orderBean.getPage() != null) {
                            pages = orderBean.getPage().getPages();
                        }
                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                            isRefresh = false;
                            orderList.clear();
                        } else if (isLoadMore) {
                            refreshLayout.finishLoadMore();
                        } else {
                            orderList.clear();
                            viewLoading.smoothToHide();
                        }
                        if (orderBean.isFlag() && orderBean.getData() != null
                                && orderBean.getData().size() > 0) {
                            orderList.addAll(orderBean.getData());
                            llNullData.setVisibility(View.GONE);
                        } else {
                            if (!isLoadMore) {
                                llNullData.setVisibility(View.VISIBLE);
                                llNullData.setVisibility(View.VISIBLE);
                            }

                            if (orderBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(getActivity(), "is_login");
                            }
                        }
                        isLoadMore = false;
                        adapter.notifyDataSetChanged();
                    }
                });

    }

    private void initView() {
        rvOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter<MyOrderBean.DataBean>(getActivity(), R.layout.rv_order_item, orderList) {
            @Override
            protected void convert(ViewHolder holder, MyOrderBean.DataBean dataBean, int position) {
                holder.setText(R.id.tv_order_shop, dataBean.getRelationName());
                SimpleDraweeView ivGoods = holder.getView(R.id.iv_order);
                ivGoods.setImageURI(dataBean.getOrderDtlList().get(0).getDetailPicturepath());
                holder.setText(R.id.tv_order_name, dataBean.getOrderDtlList().get(0).getDetailName());
                holder.setText(R.id.tv_order_now_price, "¥" + DisplayUtils.isInteger(dataBean.getOrderDtlList().get(0).getDetailPrice()));
                TextView tvOld = holder.getView(R.id.tv_order_old_price);
                tvOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                tvOld.setText("¥" + DisplayUtils.isInteger(dataBean.getOrderDtlList().get(0).getOriginalPrice()));
                holder.setText(R.id.tv_order_num, "x" + dataBean.getOrderDtlList().get(0).getDetailAccount());
                holder.setText(R.id.tv_order_total_price, "¥" + DisplayUtils.isInteger(dataBean.getPrice()));
                final TextView tvCancel = holder.getView(R.id.tv_cancel_order);
                TextView tvConfirm = holder.getView(R.id.tv_order_confirm);

                final TextView tvStatus = holder.getView(R.id.tv_status_order);
                CountDownTimer countDownTimer = countDownMap.get(tvStatus.hashCode());
                if (countDownTimer != null) {
                    //将复用的倒计时清除
                    countDownTimer.cancel();
                    countDownTimer = null;
                }
                switch (dataBean.getState()) {
                    case "UNPAID":
                        long currentTime = System.currentTimeMillis();
                        long orderTime = DateUtils.getTime("yyyy-MM-dd HH:mm:ss", dataBean.getSumbitTime());

                        if (currentTime - orderTime < 15 * 60 * 1000) {
                            countDownTimer = new CountDownTimer(15 * 60 * 1000 + orderTime - currentTime, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    long minute = millisUntilFinished / (1000 * 60);
                                    long second = millisUntilFinished % (1000 * 60) / 1000;
                                    tvStatus.setText("待付款，" + minute + ":" + second + "后取消");
                                }

                                @Override
                                public void onFinish() {
                                    tvStatus.setText("已取消");
                                }
                            }.start();

                        }

                        tvCancel.setVisibility(View.VISIBLE);
                        tvCancel.setText("取消订单");
                        tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                        tvConfirm.setVisibility(View.VISIBLE);
                        tvConfirm.setText("去支付");
                        tvConfirm.setBackgroundResource(R.drawable.bound_red_16dp);
                        break;
                    case "PAID":
                        tvStatus.setText("待提货");

                        tvCancel.setVisibility(View.GONE);

                        tvConfirm.setVisibility(View.VISIBLE);

                        if (("GROUPON").equals(dataBean.getOrderType())) {
                            tvConfirm.setText("拼团中");
                            tvConfirm.setBackgroundResource(R.drawable.bound_gray_16dp);
                        } else {
                            tvConfirm.setText("去提货");
                            tvConfirm.setBackgroundResource(R.drawable.bound_red_16dp);
                        }

                        break;
                    case "SINGIN":
                        tvStatus.setText("已完成");

                        tvCancel.setVisibility(View.VISIBLE);
                        tvCancel.setText("查看详情");
                        tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                        tvConfirm.setVisibility(View.GONE);
                        break;
                    case "GROUP_SUCCESS":
                        tvStatus.setText("待提货");

                        tvCancel.setVisibility(View.GONE);

                        tvConfirm.setVisibility(View.VISIBLE);

                        if (("GROUPON").equals(dataBean.getOrderType())) {
                            tvConfirm.setText("去提货");
                            tvConfirm.setBackgroundResource(R.drawable.bound_red_16dp);
                        }

                        break;
                    case "CLOSE":
                        switch (dataBean.getCloseType()) {
                            case "AUTO":
                            case "HAND":
                                tvStatus.setText("已取消");
                                break;
                            case "AUTO_REFUNDED":
                            case "HAND_REFUNDED":
                                tvStatus.setText("已关闭");
                                break;
                            case "UNSIGNIN":
                                tvStatus.setText("已失效");
                                break;
                        }

                        tvCancel.setVisibility(View.VISIBLE);
                        tvCancel.setText("查看详情");
                        tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                        tvConfirm.setVisibility(View.GONE);
                        break;
                    case "EXPIRED":
                        tvStatus.setText("已失效");

                        tvCancel.setVisibility(View.VISIBLE);
                        tvCancel.setText("查看详情");
                        tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);
                        tvConfirm.setVisibility(View.GONE);
                        break;
                }

                //将此 countDownTimer 放入list.
                countDownMap.put(tvStatus.hashCode(), countDownTimer);

            }
        };

        rvOrder.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent =new Intent(getActivity(), OrderDetailActivity.class);
                intent.putExtra("order_id",orderList.get(position).getMemberOrderShopId());
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

    public static OrderFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        cancelAllTimers();
    }

    /**
     * 清空资源
     */
    public void cancelAllTimers() {
        if (countDownMap == null) {
            return;
        }
        for (int i = 0, length = countDownMap.size(); i < length; i++) {
            CountDownTimer cdt = countDownMap.get(countDownMap.keyAt(i));
            if (cdt != null) {
                cdt.cancel();
            }
        }
    }

}
