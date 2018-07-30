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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.MyOrderBean;
import com.hfbh.yuecheng.bean.RefundOrderBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.GroupGoodsDetailActivity;
import com.hfbh.yuecheng.ui.PopGoodsDetailActivity;
import com.hfbh.yuecheng.ui.RefundDetailActivity;
import com.hfbh.yuecheng.ui.RushGoodsDetailActivity;
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
 * Author：Libin on 2018/7/26 10:01
 * Email：1993911441@qq.com
 * Describe：退款单
 */
public class RefundOrderFragment extends BaseFragment {
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

    //当前页
    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    //总页数
    private int pages;

    private List<RefundOrderBean.DataBean> orderList = new ArrayList<>();
    private CommonAdapter<RefundOrderBean.DataBean> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_activity, container, false);
        unbinder = ButterKnife.bind(this, view);
        ivNullData.setImageResource(R.mipmap.ic_null_order);
        tvNullData.setText("暂无相关订单");
        viewLoading.smoothToShow();
        initView();
        initData();
        return view;
    }


    private void initData() {
        OkHttpUtils.post()
                .url(Constant.REFUND_LIST)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                .addParams("pageNum", String.valueOf(page))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        RefundOrderBean orderBean = GsonUtils.jsonToBean(response, RefundOrderBean.class);
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
        adapter = new CommonAdapter<RefundOrderBean.DataBean>(getActivity(), R.layout.rv_order_item, orderList) {
            @Override
            protected void convert(ViewHolder holder, final RefundOrderBean.DataBean dataBean, int position) {
                holder.setText(R.id.tv_order_shop, dataBean.getShopName());
                SimpleDraweeView ivGoods = holder.getView(R.id.iv_order);
                ivGoods.setImageURI(dataBean.getDetailPicturePath());
                holder.setText(R.id.tv_order_name, dataBean.getCommodityName());
                holder.setText(R.id.tv_order_now_price, "¥" + DisplayUtils.isInteger(dataBean.getDetailPrice()));
                TextView tvOld = holder.getView(R.id.tv_order_old_price);
                tvOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                tvOld.setText("¥" + DisplayUtils.isInteger(dataBean.getOriginalPrice()));
                holder.setText(R.id.tv_order_num, "x" + dataBean.getDetailAccount());
                holder.setText(R.id.tv_order_total_price, "¥" + DisplayUtils.isInteger(dataBean.getDealAmount()));
                RelativeLayout rlGoods = holder.getView(R.id.rl_order_goods);
                final TextView tvCancel = holder.getView(R.id.tv_cancel_order);


                TextView tvStatus = holder.getView(R.id.tv_status_order);

                tvCancel.setVisibility(View.VISIBLE);
                tvCancel.setText("查看详情");
                tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                String status = null;
                if (TextUtils.isEmpty(dataBean.getRefundType())) {
                    status = "";
                } else if ("AUDIT".equals(dataBean.getRefundType())) {
                    switch (dataBean.getRefundApplyType()) {
                        case "REFUND":
                            status = "仅退款，";
                            break;
                        case "RETURN":
                            status = "退货退款，";
                            break;
                    }
                } else {
                    status = "自动退款，";
                }

                switch (dataBean.getRefundState()) {
                    case "UNDO":
                        status += "撤销退款";
                        break;
                    case "APPLY":
                        status += "退款中";
                        break;
                    case "FAIL":
                        status += "退款失败";
                        break;
                    case "SUCCESS":
                        status += "退款成功";
                        break;
                }

                tvStatus.setText(status);

                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), RefundDetailActivity.class);
                        intent.putExtra("refund_id",dataBean.getMemberRefundApplyId());
                        startActivity(intent);
                    }
                });

//                rlGoods.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent = null;
//                        switch ("") {
//
//                            case "GROUPON":
//                                intent = new Intent(getActivity(), GroupGoodsDetailActivity.class);
//                                break;
//                            case "SPECIAL":
//                                intent = new Intent(getActivity(), PopGoodsDetailActivity.class);
//                                break;
//                            case "SECKILL":
//                                intent = new Intent(getActivity(), RushGoodsDetailActivity.class);
//                                break;
//                        }
//                        intent.putExtra("goods_id", dataBean.getDetailId());
//                        startActivity(intent);
//
//                    }
//                });
            }

        };

        rvOrder.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getActivity(), RefundDetailActivity.class);
                intent.putExtra("refund_id",orderList.get(position).getMemberRefundApplyId());
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


    public static RefundOrderFragment newInstance() {

        Bundle args = new Bundle();

        RefundOrderFragment fragment = new RefundOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
