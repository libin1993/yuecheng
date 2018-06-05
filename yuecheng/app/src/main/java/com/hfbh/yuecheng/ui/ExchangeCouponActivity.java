package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.CouponListBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/25 13:39
 * Email：1993911441@qq.com
 * Describe：领取优惠券
 */
public class ExchangeCouponActivity extends BaseActivity {
    @BindView(R.id.tv_title_header)
    TextView tvTitleHeader;
    @BindView(R.id.iv_back_header)
    ImageView ivBackHeader;
    @BindView(R.id.et_search_coupon)
    EditText etSearchCoupon;
    @BindView(R.id.rv_exchange_coupon)
    RecyclerView rvExchangeCoupon;
    @BindView(R.id.layout_refresh_exchange_coupon)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;
    @BindView(R.id.tv_no_coupon)
    TextView tvNoCoupon;

    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    //关键字
    private List<CouponListBean.DataBean> dataList = new ArrayList<>();
    //总页数
    private int pages;
    private CommonAdapter<CouponListBean.DataBean> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_coupon);
        ButterKnife.bind(this);
        tvTitleHeader.setText("优惠券");
        loadingView.smoothToShow();

        initData();

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
        if (!TextUtils.isEmpty(etSearchCoupon.getText().toString().trim())) {
            map.put("key", etSearchCoupon.getText().toString().trim());
        }
        OkHttpUtils.get()
                .url(Constant.EXCHANGE_COUPON_LIST)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        CouponListBean ListBean = GsonUtils.jsonToBean(response, CouponListBean.class);
                        pages = ListBean.getPage().getPages();
                        if (ListBean.isFlag() && ListBean.getData() != null && ListBean.getData().size() > 0) {
                            if (isRefresh) {
                                dataList.clear();
                            }
                            dataList.addAll(ListBean.getData());

                            if (isRefresh) {
                                refreshLayout.finishRefresh();
                                isRefresh = false;
                                adapter.notifyDataSetChanged();
                            } else if (isLoadMore) {
                                refreshLayout.finishLoadMore();
                                isLoadMore = false;
                                adapter.notifyDataSetChanged();
                            } else {
                                loadingView.smoothToHide();
                                initView();
                            }
                            tvNoCoupon.setVisibility(View.GONE);
                            rvExchangeCoupon.setVisibility(View.VISIBLE);
                        } else {
                            if (page == 1) {
                                loadingView.smoothToHide();
                                tvNoCoupon.setVisibility(View.VISIBLE);
                                rvExchangeCoupon.setVisibility(View.GONE);
                            }
                            refreshLayout.finishLoadMore();
                        }
                    }
                });
    }

    private void initView() {

        rvExchangeCoupon.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<CouponListBean.DataBean>(ExchangeCouponActivity.this,
                R.layout.rv_coupon_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, final CouponListBean.DataBean dataBean, final int position) {
                SimpleDraweeView ivCoupon = holder.getView(R.id.iv_home_coupon);
                ivCoupon.setImageURI(dataBean.getCouponImage());

                holder.setText(R.id.tv_home_coupon_title, dataBean.getCouponName());
//                holder.setText(R.id.tv_home_coupon_content, dataBean.getCoupon);
                holder.setText(R.id.tv_home_coupon_remain, "剩余" + dataBean.getBalanceNum());

                TextView tvReceive = holder.getView(R.id.tv_home_coupon_receive);

                String accessType = dataBean.getAccessType();
                double needScore = dataBean.getAccessValue();

                if (!TextUtils.isEmpty(accessType)) {
                    switch (accessType) {
                        case "FREE":
                            tvReceive.setText("免费\n领取");
                            break;
                        case "POINT":
                            tvReceive.setText(needScore + "积分\n领取");
                            break;
                        case "BUY":
                            tvReceive.setText(needScore + "元\n领取");
                            break;
                    }
                }


                RelativeLayout rlCoupon = holder.getView(R.id.rl_coupon_item);
                rlCoupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ExchangeCouponActivity.this, CouponDetailActivity.class);
                        intent.putExtra("coupon_id", dataBean.getCouponId());
                        startActivity(intent);
                    }
                });

                tvReceive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (SharedPreUtils.getBoolean(ExchangeCouponActivity.this, "is_login", false)) {
                            exchangeCoupon(position);
                        } else {
                            startActivity(new Intent(ExchangeCouponActivity.this, LoginActivity.class));
                        }
                    }
                });

            }
        };
        rvExchangeCoupon.setAdapter(adapter);

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

        etSearchCoupon.setFilters(new InputFilter[]{new InputFilter() {
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
        etSearchCoupon.addTextChangedListener(new TextWatcher() {
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
     * 领取优惠券
     */
    private void exchangeCoupon(final int position) {
        OkHttpUtils.post()
                .url(Constant.EXCHANGE_COUPON)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("couponId", String.valueOf(dataList.get(position).getCouponId()))
                .addParams("exchangeValue", String.valueOf(dataList.get(position).getAccessValue()))
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
                            ToastUtils.showToast(ExchangeCouponActivity.this, msg);
                            if (flag) {
                                int data = jsonObject.getInt("data");
                                dataList.get(position).setBalanceNum(data);
                                adapter.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }


    @OnClick(R.id.iv_back_header)
    public void onViewClicked() {
        finish();
    }
}
