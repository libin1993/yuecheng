package com.hfbh.yuecheng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.MyCouponBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
    @BindView(R.id.tv_null_activity)
    TextView tvNullCoupon;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    private int type;
    private Unbinder unbinder;

    private MyCouponBean couponBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_activity, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        tvNullCoupon.setText("暂无相关优惠券");
        viewLoading.smoothToShow();
        initData();
        return view;
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.MY_COUPON)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .addParams("cardNumber", SharedPreUtils.getStr(getActivity(), "card_number"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        viewLoading.smoothToHide();
                        couponBean = GsonUtils.jsonToBean(response, MyCouponBean.class);
                        if (couponBean.isFlag() && couponBean.getData() != null
                                && couponBean.getData().size() > 0) {
                            rvCoupon.setVisibility(View.VISIBLE);
                            tvNullCoupon.setVisibility(View.GONE);
                            initView();
                        } else {
                            rvCoupon.setVisibility(View.GONE);
                            tvNullCoupon.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void initView() {
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
        rvCoupon.setLayoutManager(new LinearLayoutManager(getActivity()));
        CommonAdapter<MyCouponBean.DataBean> adapter = new CommonAdapter<MyCouponBean.DataBean>(getActivity(),
                R.layout.rv_user_coupon_item, couponBean.getData()) {
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


                tvValue.setText(DisplayUtils.isInteger(dataBean.getBalance()));
                tvType.setText(dataBean.getCouponTypeName());
                tvTime.setText(dataBean.getValidDate());


            }
        };

        rvCoupon.setAdapter(adapter);
    }


    public static CouponFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt("type", type);
        CouponFragment fragment = new CouponFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void getData() {
        type = getArguments().getInt("type");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
