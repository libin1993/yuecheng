package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.CouponListBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.fragment.ExchangeFragment;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.hfbh.yuecheng.view.GridItemDecoration;
import com.hfbh.yuecheng.view.SpaceItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.smarttop.library.utils.LogUtil;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
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
    @BindView(R.id.iv_null_data)
    ImageView ivNullData;
    @BindView(R.id.tv_null_data)
    TextView tvNullData;
    @BindView(R.id.ll_null_data)
    LinearLayout llNullData;

    //当前页数
    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    //搜索
    private boolean isSearch;

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
        EventBus.getDefault().register(this);
        tvTitleHeader.setText("优惠券");
        ivNullData.setImageResource(R.mipmap.ic_null_coupon);
        tvNullData.setText("暂无优惠券");
        loadingView.smoothToShow();
        initView();
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
        map.put("token", SharedPreUtils.getStr(this, "token"));
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
                        if (ListBean.getPage() != null) {
                            pages = ListBean.getPage().getPages();
                        }

                        if (isRefresh) {
                            dataList.clear();
                            refreshLayout.finishRefresh();
                            isRefresh = false;
                        } else if (isSearch) {
                            dataList.clear();
                            loadingView.smoothToHide();
                            isSearch = false;
                        } else if (isLoadMore) {
                            refreshLayout.finishLoadMore();
                        } else {
                            dataList.clear();
                            loadingView.smoothToHide();
                        }


                        if (ListBean.isFlag() && ListBean.getData() != null && ListBean.getData().size() > 0) {
                            dataList.addAll(ListBean.getData());
                            llNullData.setVisibility(View.GONE);
                        } else {
                            if (!isLoadMore) {
                                llNullData.setVisibility(View.VISIBLE);
                            }
                        }
                        isLoadMore = false;
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void initView() {
        rvExchangeCoupon.setLayoutManager(new LinearLayoutManager(this));
        rvExchangeCoupon.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildAdapterPosition(view);
                if (position == 0) {
                    outRect.set(0, (int) DisplayUtils.dp2px(ExchangeCouponActivity.this, 9), 0, 0);
                }
            }
        });
        adapter = new CommonAdapter<CouponListBean.DataBean>(ExchangeCouponActivity.this,
                R.layout.rv_coupon_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, final CouponListBean.DataBean dataBean, final int position) {
                SimpleDraweeView ivCoupon = holder.getView(R.id.iv_home_coupon);
                ivCoupon.setImageURI(dataBean.getCouponImage());

                TextView tvCouponName = holder.getView(R.id.tv_home_coupon_title);

                if (dataBean.getCouponTypeKind() != null && dataBean.getCouponTypeKind().equals("VOUCHER")) {
                    if (dataBean.getListCouponShop() != null && dataBean.getListCouponShop().size() > 0) {
                        StringBuilder shop = new StringBuilder();
                        for (int i = 0; i < dataBean.getListCouponShop().size(); i++) {
                            if (i < dataBean.getListCouponShop().size() - 1) {
                                shop.append(dataBean.getListCouponShop().get(i).getShopName()).append("、");
                            } else {
                                shop.append(dataBean.getListCouponShop().get(i).getShopName());
                            }
                        }

                        holder.setText(R.id.tv_home_coupon_content, "满" + DisplayUtils
                                .isInteger(dataBean.getServiceAmount()) + "元可用,限" + shop.toString());
                    } else {
                        holder.setText(R.id.tv_home_coupon_content, "满" + DisplayUtils
                                .isInteger(dataBean.getServiceAmount()) + "元可用");
                    }

                } else {
                    holder.setText(R.id.tv_home_coupon_content, dataBean.getUseRange());
                }


                holder.setText(R.id.tv_home_coupon_remain, "剩余" + dataBean.getBalanceNum());

                TextView tvReceive = holder.getView(R.id.tv_home_coupon_receive);

                String accessType = dataBean.getAccessType();
                double needScore = dataBean.getAccessValue();

                if (dataBean.getCouponTypeCy() == 9) {
                    //返利
                    double rebate = 0.015;
                    if (SharedPreUtils.getBoolean(ExchangeCouponActivity.this, "is_login", false)) {
                        switch (SharedPreUtils.getStr(ExchangeCouponActivity.this, "member_card")) {
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
                    //积分返利
                    if (!TextUtils.isEmpty(accessType) && accessType.equals("POINT")) {
                        tvCouponName.setText(DisplayUtils.isInteger(needScore * rebate)
                                + "元-" + dataBean.getCouponName());
                    } else {
                        tvCouponName.setText(dataBean.getCouponName());
                    }
                } else {
                    tvCouponName.setText(DisplayUtils.isInteger(dataBean.getCouponValue())
                            + "元-" + dataBean.getCouponName());
                }

                if (dataBean.getBalanceNum() > 0) {

                    tvReceive.setBackgroundResource(R.color.red_99);
                    tvReceive.setTextColor(ContextCompat.getColor(ExchangeCouponActivity.this, R.color.gray_e0));
                    int limitNum = dataBean.getLimitNum();
                    int getNum = dataBean.getMemberBroughtNum();

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
                isSearch = true;
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
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("couponId", String.valueOf(dataList.get(position).getCouponId()))
                .addParams("cyCouponId", String.valueOf(dataList.get(position).getCouponTypeCy()))
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
                                dataList.get(position).setMemberBroughtNum(dataList.get(position).getMemberBroughtNum() + 1);
                                adapter.notifyDataSetChanged();
                            } else if (jsonObject.getInt("code") == 4002) {
                                SharedPreUtils.deleteStr(ExchangeCouponActivity.this, "is_login");
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

    @Subscribe
    public void isLogin(String msg) {
        if ("login_success".equals(msg)) {
            isRefresh = true;
            page = 1;
            initData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
