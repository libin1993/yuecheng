package com.hfbh.yuecheng.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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
import com.hfbh.yuecheng.ui.ApplyRefundActivity;
import com.hfbh.yuecheng.ui.ConfirmOrderActivity;
import com.hfbh.yuecheng.ui.GroupGoodsActivity;
import com.hfbh.yuecheng.ui.GroupGoodsDetailActivity;
import com.hfbh.yuecheng.ui.OrderDetailActivity;
import com.hfbh.yuecheng.ui.PayOrderActivity;
import com.hfbh.yuecheng.ui.PopGoodsDetailActivity;
import com.hfbh.yuecheng.ui.RefundDetailActivity;
import com.hfbh.yuecheng.ui.RushGoodsDetailActivity;
import com.hfbh.yuecheng.ui.ValidateActivity;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.QRCodeUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.smarttop.library.utils.LogUtil;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

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
    private Bitmap qrBmp;

    //Fragment的View加载完毕的标记
    private boolean isViewCreated;

    //Fragment对用户可见的标记
    private boolean isUIVisible;


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
        return view;
    }

    private void getData() {
        type = getArguments().getString("type");
    }

    @Override
    public void onResume() {
        super.onResume();
        isViewCreated = true;
        if (isUIVisible) {
            page = 1;
            initData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            if (isViewCreated) {
                page = 1;
                initData();
            }
        } else {
            isUIVisible = false;
        }
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
                            if (viewLoading != null && viewLoading.isShown()) {
                                viewLoading.smoothToHide();
                            }

                        }
                        if (orderBean.isFlag() && orderBean.getData() != null
                                && orderBean.getData().size() > 0) {
                            orderList.addAll(orderBean.getData());
                            if (llNullData != null) {
                                llNullData.setVisibility(View.GONE);
                            }

                        } else {
                            if (!isLoadMore) {
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
            protected void convert(ViewHolder holder, final MyOrderBean.DataBean dataBean, int position) {
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
                final TextView tvConfirm = holder.getView(R.id.tv_order_confirm);

//                RelativeLayout rlGoods = holder.getView(R.id.rl_order_goods);

                final TextView tvStatus = holder.getView(R.id.tv_status_order);
                CountDownTimer countDownTimer = countDownMap.get(tvStatus.hashCode());
                if (countDownTimer != null) {
                    //将复用的倒计时清除
                    countDownTimer.cancel();
                    countDownTimer = null;
                }

                if ("NORMAL".equals(dataBean.getRefundState())) {
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

                                        tvCancel.setVisibility(View.VISIBLE);
                                        tvCancel.setText("取消订单");
                                        tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                                        tvConfirm.setVisibility(View.VISIBLE);
                                        tvConfirm.setText("去支付");
                                        tvConfirm.setBackgroundResource(R.drawable.bound_red_16dp);

                                        dataBean.setStatus(1);
                                    }

                                    @Override
                                    public void onFinish() {
                                        tvStatus.setText("已取消");
                                        tvCancel.setText("查看详情");
                                        tvConfirm.setVisibility(View.GONE);
                                        dataBean.setStatus(0);
                                    }
                                }.start();

                            }


                            break;
                        case "PAID":
                            tvStatus.setText("待提货");

                            tvCancel.setVisibility(View.GONE);

                            tvConfirm.setVisibility(View.VISIBLE);

                            if (("GROUPON").equals(dataBean.getOrderType())) {
                                tvConfirm.setText("拼团中");
                                tvConfirm.setBackgroundResource(R.drawable.bound_gray_16dp);
                                dataBean.setStatus(5);
                            } else {
                                tvConfirm.setText("去提货");
                                tvConfirm.setBackgroundResource(R.drawable.bound_red_16dp);
                                dataBean.setStatus(3);
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

                            dataBean.setStatus(3);
                            break;
                        case "CLOSE":
                            switch (dataBean.getCloseType()) {
                                case "AUTO":
                                case "HAND":
                                    tvStatus.setText("已取消");
                                    tvCancel.setText("查看详情");
                                    break;
                                case "AUTO_REFUNDED":
                                case "HAND_REFUNDED":
                                    tvStatus.setText("已关闭");
                                    tvCancel.setText("查看详情");
                                    break;
                                case "UNSIGNIN":
                                    tvStatus.setText("已失效");
                                    tvCancel.setText("去退款");
                                    dataBean.setStatus(4);
                                    break;
                            }

                            tvCancel.setVisibility(View.VISIBLE);

                            tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                            tvConfirm.setVisibility(View.GONE);

                            break;
                        case "EXPIRED":
                            tvStatus.setText("已失效");

                            tvCancel.setVisibility(View.VISIBLE);
                            tvCancel.setText("去退款");
                            tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);
                            tvConfirm.setVisibility(View.GONE);
                            dataBean.setStatus(4);
                            break;
                    }

                } else {
                    tvConfirm.setVisibility(View.GONE);
                    tvCancel.setVisibility(View.VISIBLE);
                    tvCancel.setVisibility(View.VISIBLE);
                    tvCancel.setText("查看详情");
                    tvCancel.setBackgroundResource(R.drawable.stroke_gray_16dp);

                    String status = null;
                    switch (dataBean.getRefundState()) {
                        case "REFUNDING":
                            status = "退款中";
                            break;
                        case "REFUNDED":
                            status = "退款成功";
                            break;
                        case "REFUND_FAIL":
                            status = "退款失败";
                            break;
                    }

                    tvStatus.setText(status);
                    dataBean.setStatus(0);
                }


                //将此 countDownTimer 放入list.
                countDownMap.put(tvStatus.hashCode(), countDownTimer);

//                rlGoods.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent = null;
//                        switch (dataBean.getOrderType()) {
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
//                        intent.putExtra("goods_id", dataBean.getOrderDtlList().get(0).getDetailId());
//                        startActivity(intent);
//
//                    }
//                });

                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch (dataBean.getStatus()) {
                            case 0:
                                orderDetail(dataBean.getMemberOrderShopId());
                                break;
                            case 1:
                                cancelOrder(dataBean.getMemberOrderShopId());
                                break;
                            case 4:
                                refundOrder(dataBean.getOrderDtlList().get(0).getMemberOrderDetailId(),
                                        dataBean.getOrderDtlList().get(0).getDetailPrice());
                                break;

                        }
                    }
                });

                tvConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (dataBean.getStatus()) {
                            case 1:
                                payMoney(dataBean.getMemberOrderShopId());
                                break;
                            case 2:
                            case 3:
                                receiveGoods(dataBean.getPickupCode());
                                break;
                        }
                    }
                });

            }
        };

        rvOrder.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                orderDetail(orderList.get(position).getMemberOrderShopId());
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

    /**
     * @param memberOrderShopId 订单详情
     */
    private void orderDetail(int memberOrderShopId) {
        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
        intent.putExtra("order_id", memberOrderShopId);
        startActivity(intent);
    }

    /**
     * 提货
     */
    private void receiveGoods(String code) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.ppw_receive_goods, null);
        int width = DisplayUtils.getMetrics(getActivity()).widthPixels;
        final PopupWindow mPopupWindow = new PopupWindow(contentView, (int) (width - DisplayUtils.dp2px(
                getActivity(), 64)), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        DisplayUtils.setBackgroundAlpha(getActivity(), true);

        ImageView ivCancel = (ImageView) contentView.findViewById(R.id.iv_cancel_receive);

        TextView tvConfirm = (TextView) contentView.findViewById(R.id.tv_goods_confirm);

        TextView tvCode = (TextView) contentView.findViewById(R.id.tv_goods_code);
        ImageView ivCode = (ImageView) contentView.findViewById(R.id.iv_goods_code);


        tvCode.setText("提货核销码：" + code);

        if (!TextUtils.isEmpty(code)) {
            qrBmp = QRCodeUtils.createQRCode(code, (int) DisplayUtils.dp2px(getActivity(), 200));
            ivCode.setImageBitmap(qrBmp);
        }

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (qrBmp != null) {
                    qrBmp.recycle();
                    qrBmp = null;
                }
                DisplayUtils.setBackgroundAlpha(getActivity(), false);

                page = 1;
                initData();
            }
        });
    }

    /**
     * 付款
     */
    private void payMoney(int orderId) {
        Intent intent = new Intent(getActivity(), PayOrderActivity.class);
        intent.putExtra("order_id", orderId);
        startActivity(intent);
    }

    /**
     * 退款
     */
    private void refundOrder(int refundId, double price) {
        Intent intent = new Intent(getActivity(), ApplyRefundActivity.class);
        intent.putExtra("refund_id", refundId);
        intent.putExtra("refund_type", "REFUND");
        intent.putExtra("money", price);
        startActivity(intent);
    }

    /**
     * 取消订单
     */
    private void cancelOrder(final int orderId) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(),
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("提示");
        dialog.setMessage("您确定要取消订单吗？");
        //为“确定”按钮注册监听事件
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OkHttpUtils.post()
                        .url(Constant.CANCEL_ORDER)
                        .addParams("appType", MyApp.appType)
                        .addParams("appVersion", MyApp.appVersion)
                        .addParams("organizeId", MyApp.organizeId)
                        .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                        .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                        .addParams("memberOrderShopId", String.valueOf(orderId))
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
                                        ToastUtils.showToast(getActivity(), "已取消订单");
                                        page = 1;
                                        initData();
                                    } else {
                                        String msg = jsonObject.getString("msg");
                                        ToastUtils.showToast(getActivity(), msg);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });

        //为“取消”按钮注册监听事件
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();

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
