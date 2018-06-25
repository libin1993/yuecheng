package com.hfbh.yuecheng.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.hfbh.yuecheng.bean.MyCouponBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.CloseCouponActivity;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
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
 * Author：Libin on 2018/6/8 10:39
 * Email：1993911441@qq.com
 * Describe：我的优惠券
 */
public class CouponFragment extends BaseFragment {
    @BindView(R.id.rv_my_activity)
    RecyclerView rvCoupon;
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

    private Unbinder unbinder;

    private List<MyCouponBean.DataBean> dataList = new ArrayList<>();
    private String type;
    private CommonAdapter<MyCouponBean.DataBean> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_activity, container, false);
        unbinder = ButterKnife.bind(this, view);
        ivNullData.setImageResource(R.mipmap.ic_null_coupon);
        tvNullData.setText("暂无优惠券");
        getData();
        initData();
        initView();
        return view;
    }


    /**
     * 获取优惠券
     */
    private void initData() {
        viewLoading.smoothToShow();
        OkHttpUtils.post()
                .url(Constant.MY_COUPON)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                .addParams("cardNumber", SharedPreUtils.getStr(getActivity(), "card_number"))
                .addParams("couponTypeKind", "VOUCHER")
                .addParams("queryType", type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        viewLoading.smoothToHide();
                        MyCouponBean couponBean = GsonUtils.jsonToBean(response, MyCouponBean.class);
                        if (couponBean.isFlag() && couponBean.getData() != null
                                && couponBean.getData().size() > 0) {
                            dataList.addAll(couponBean.getData());
                            adapter.notifyDataSetChanged();
                            llNullData.setVisibility(View.GONE);
                        } else {
                            llNullData.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }


    private void initView() {
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
        rvCoupon.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter<MyCouponBean.DataBean>(getActivity(),
                R.layout.rv_user_coupon_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, MyCouponBean.DataBean dataBean, int position) {
                SimpleDraweeView ivCouponBg = holder.getView(R.id.iv_coupon_bg);
                TextView tvCouponName = holder.getView(R.id.tv_my_coupon_name);
                TextView tvContent = holder.getView(R.id.tv_my_coupon_info);
                TextView tvTime = holder.getView(R.id.tv_my_coupon_time);
                TextView tvMoney = holder.getView(R.id.tv_coupon_type_money);
                TextView tvValue = holder.getView(R.id.tv_coupon_value);
                TextView tvPark = holder.getView(R.id.tv_coupon_type_park);
                TextView tvType = holder.getView(R.id.tv_coupon_type_name);
                ImageView ivType = holder.getView(R.id.iv_coupon_type);


                tvCouponName.setText(dataBean.getCouponTypeName());
                tvValue.setText(DisplayUtils.isInteger(dataBean.getBalance()));
                tvContent.setText(dataBean.getContent());
                tvTime.setText(dataBean.getValidDate());

                if (TextUtils.isEmpty(dataBean.getCouponTypeKind())) {
                    tvType.setText("电子券");
                    tvMoney.setVisibility(View.VISIBLE);
                    tvPark.setVisibility(View.GONE);
                } else {
                    if ("VOUCHER".equals(dataBean.getCouponTypeKind())) {
                        tvType.setText("满减券");
                        tvMoney.setVisibility(View.VISIBLE);
                        tvPark.setVisibility(View.GONE);
                    } else if ("PARKING".equals(dataBean.getCouponTypeKind())) {
                        tvType.setText("停车券");
                        tvMoney.setVisibility(View.GONE);
                        tvPark.setVisibility(View.VISIBLE);
                    }
                }
                switch (type) {
                    case "UNUSE":
                        if (TextUtils.isEmpty(dataBean.getCouponTypeKind())) {
                            ivCouponBg.setImageResource(R.mipmap.img_user_bigcoupon_red);
                        } else {
                            if ("VOUCHER".equals(dataBean.getCouponTypeKind())) {
                                ivCouponBg.setImageResource(R.mipmap.img_user_bigcoupon_blue);
                            } else if ("PARKING".equals(dataBean.getCouponTypeKind())) {
                                ivCouponBg.setImageResource(R.mipmap.img_user_bigcoupon_green);
                            }
                        }
                        tvCouponName.setTextColor(getResources().getColor(R.color.gray_10));
                        tvContent.setTextColor(getResources().getColor(R.color.gray_10));
                        tvTime.setTextColor(getResources().getColor(R.color.gray_10));
                        tvMoney.setTextColor(getResources().getColor(R.color.gray_10));
                        tvValue.setTextColor(getResources().getColor(R.color.gray_10));
                        tvPark.setTextColor(getResources().getColor(R.color.gray_10));
                        tvType.setTextColor(getResources().getColor(R.color.gray_10));
                        break;
                    case "USE":
                        ivCouponBg.setImageResource(R.mipmap.img_user_bigcoupon_grey);
                        ivType.setImageResource(R.mipmap.img_user_coupon_used);
                        tvCouponName.setTextColor(getResources().getColor(R.color.gray_c2));
                        tvContent.setTextColor(getResources().getColor(R.color.gray_c2));
                        tvTime.setTextColor(getResources().getColor(R.color.gray_c2));
                        tvMoney.setTextColor(getResources().getColor(R.color.gray_c2));
                        tvValue.setTextColor(getResources().getColor(R.color.gray_c2));
                        tvPark.setTextColor(getResources().getColor(R.color.gray_c2));
                        tvType.setTextColor(getResources().getColor(R.color.gray_c2));
                        break;
                    case "EXPIRE":
                        ivCouponBg.setImageResource(R.mipmap.img_user_bigcoupon_grey);
                        ivType.setImageResource(R.mipmap.img_user_coupon_expired);
                        tvCouponName.setTextColor(getResources().getColor(R.color.gray_c2));
                        tvContent.setTextColor(getResources().getColor(R.color.gray_c2));
                        tvTime.setTextColor(getResources().getColor(R.color.gray_c2));
                        tvMoney.setTextColor(getResources().getColor(R.color.gray_c2));
                        tvValue.setTextColor(getResources().getColor(R.color.gray_c2));
                        tvPark.setTextColor(getResources().getColor(R.color.gray_c2));
                        tvType.setTextColor(getResources().getColor(R.color.gray_c2));
                        break;
                }

            }
        };

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);

        rvCoupon.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (type.equals("UNUSE") && dataList.get(position).getCouponTypeKind() != null &&
                        "VOUCHER".equals(dataList.get(position).getCouponTypeKind())) {
                    Intent intent = new Intent(getActivity(), CloseCouponActivity.class);
                    intent.putExtra("coupon_id", dataList.get(position).getGainId());
                    intent.putExtra("coupon_type", dataList.get(position).getCouponType());
                    startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


    }


    public static CouponFragment newInstance(String type) {

        Bundle args = new Bundle();
        args.putString("type", type);
        CouponFragment fragment = new CouponFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void getData() {
        type = getArguments().getString("type");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
