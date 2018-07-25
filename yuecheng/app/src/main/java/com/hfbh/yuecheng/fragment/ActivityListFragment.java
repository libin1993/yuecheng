package com.hfbh.yuecheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
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
import com.hfbh.yuecheng.bean.ActivityListBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.ActionDetailActivity;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.view.FlowLayout;
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
 * Author：Libin on 2018/5/17 14:53
 * Email：1993911441@qq.com
 * Describe：活动列表
 */
public class ActivityListFragment extends BaseFragment {

    @BindView(R.id.sv_no_activity)
    NestedScrollView svNoActivity;
    @BindView(R.id.rv_activity_list)
    RecyclerView rvActivity;
    @BindView(R.id.layout_refresh_activity)
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
    //标签id
    private int tagId;
    //当前页
    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    private List<ActivityListBean.DataBean> dataList = new ArrayList<>();
    //总页数
    private int pages;
    private CommonAdapter<ActivityListBean.DataBean> adapter;

    //Fragment的View加载完毕的标记
    private boolean isViewCreated;

    //Fragment对用户可见的标记
    private boolean isUIVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        ivNullData.setImageResource(R.mipmap.ic_null_activity);
        tvNullData.setText("暂无活动");
        isViewCreated = true;
        getData();
        initView();
        isViewCreated = true;
        lazyLoad();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    /**
     * 懒加载
     */
    private void lazyLoad() {
        if (isViewCreated && isUIVisible) {
            viewLoading.smoothToShow();
            initData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    /**
     * 加载数据
     */
    private void initData() {
        OkHttpUtils.get()
                .url(Constant.ACTIVITY_LIST)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                .addParams("pageNum", String.valueOf(page))
                .addParams("tagId", String.valueOf(tagId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ActivityListBean activityListBean = GsonUtils.jsonToBean(response, ActivityListBean.class);
                        if (activityListBean.getPage() != null) {
                            pages = activityListBean.getPage().getPages();
                        }

                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                            isRefresh = false;
                            dataList.clear();
                        } else if (isLoadMore) {
                            refreshLayout.finishLoadMore();
                        } else {
                            dataList.clear();
                            viewLoading.smoothToHide();
                        }

                        if (activityListBean.isFlag() && activityListBean.getData() != null &&
                                activityListBean.getData().size() > 0) {
                            dataList.addAll(activityListBean.getData());
                            svNoActivity.setVisibility(View.GONE);
                        } else {
                            if (!isLoadMore) {
                                svNoActivity.setVisibility(View.VISIBLE);
                                llNullData.setVisibility(View.VISIBLE);
                            }
                        }
                        isLoadMore = false;
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {
        rvActivity.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));
        adapter = new CommonAdapter<ActivityListBean.DataBean>
                (getParentFragment().getActivity(), R.layout.rv_activity_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, final ActivityListBean.DataBean dataBean, final int position) {
                SimpleDraweeView ivCoupon = holder.getView(R.id.iv_home_activity);
                ivCoupon.setImageURI(dataBean.getActivityPicture());
                holder.setText(R.id.tv_home_activity_name, dataBean.getActivityTitle());
                holder.setText(R.id.tv_home_activity_time, DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                        "yyyy.MM.dd", dataBean.getActivityStarttime()) + " - " +
                        DateUtils.formatTime("yyyy-MM-dd HH:mm:ss",
                                "yyyy.MM.dd", dataBean.getActivityEndtime()));

                TextView tvReceive = holder.getView(R.id.tv_home_activity_receive);
                //活动结束
                final boolean isActivityEnd = System.currentTimeMillis() > DateUtils.getTime(
                        "yyyy-MM-dd HH:mm:ss", dataBean.getActivityEndtime());
                //报名开始
                final boolean isEnrollStart = System.currentTimeMillis() >= DateUtils.getTime(
                        "yyyy-MM-dd HH:mm:ss", dataBean.getStartTime());
//                //报名结束
//                final boolean isEnrollEnd = System.currentTimeMillis() > DateUtils.getTime(
//                        "yyyy-MM-dd HH:mm:ss", dataBean.getEndTime());
//                //报名满额
//                final boolean isLimit = dataBean.getSignupLimitNumber() > 0 && dataBean.getSignupNumber()
//                        == dataBean.getSignupLimitNumber();
                //是否报名
                final boolean isEnroll = dataBean.isIsSignup();

                if (!TextUtils.isEmpty(dataBean.getAcivityType()) && dataBean.getAcivityType().equals("NONEED")) {
                    tvReceive.setVisibility(View.GONE);
                } else {
                    tvReceive.setVisibility(View.VISIBLE);

                    if (!isActivityEnd) {
                        if (isEnrollStart) {
                            if (!TextUtils.isEmpty(dataBean.getAcivityType())) {
                                if (isEnroll) {
                                    tvReceive.setBackgroundResource(R.drawable.bound_red_15dp);
                                    tvReceive.setText("去参加");
                                } else {
//                                    if (isEnrollEnd) {
//                                        tvReceive.setBackgroundResource(R.drawable.bound_gray_15dp);
//                                        tvReceive.setText("已结束");
//                                    } else {
//                                        tvReceive.setBackgroundResource(R.drawable.bound_red_15dp);
//                                        switch (dataBean.getAcivityType()) {
//                                            case "FREE":
//                                                tvReceive.setText("免费报名");
//                                                break;
//                                            case "SCORE":
//                                                tvReceive.setText(DisplayUtils.isInteger(dataBean.getEnrollScore()) + "积分报名");
//                                                break;
//                                            case "CASH":
//                                                tvReceive.setText("¥" + DisplayUtils.isInteger(dataBean.getEnrollFee()) + "报名");
//                                                break;
//                                        }
//                                    }
                                    tvReceive.setBackgroundResource(R.drawable.bound_red_15dp);
                                    switch (dataBean.getAcivityType()) {
                                        case "FREE":
                                            tvReceive.setText("免费报名");
                                            break;
                                        case "SCORE":
                                            tvReceive.setText(DisplayUtils.isInteger(dataBean.getEnrollScore()) + "积分报名");
                                            break;
                                        case "CASH":
                                            tvReceive.setText("¥" + DisplayUtils.isInteger(dataBean.getEnrollFee()) + "报名");
                                            break;
                                    }
                                }
                            }
                        } else {
                            tvReceive.setVisibility(View.VISIBLE);
                            tvReceive.setText("待报名");
                            tvReceive.setBackgroundResource(R.drawable.bound_gray_15dp);
                        }
                    } else {
                        tvReceive.setVisibility(View.VISIBLE);
                        tvReceive.setText("已结束");
                        tvReceive.setBackgroundResource(R.drawable.bound_gray_15dp);
                    }
                }

                FlowLayout flowLayout = holder.getView(R.id.flow_home_activity);
                flowLayout.removeAllViews();
                if (dataBean.getTags() != null && dataBean.getTags().size() > 0) {
                    addTextView(flowLayout, dataBean.getTags());
                }
//
//                tvReceive.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (!isActivityEnd && isEnrollStart) {
//                            Intent intent;
//                            if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
//                                if (isEnroll) {
//                                    intent = new Intent(getActivity(), CloseActionActivity.class);
//                                    intent.putExtra("activity_id", dataBean.getMarketingActivitySignupId());
//                                    startActivity(intent);
//                                } else if (!isLimit && !isEnrollEnd) {
//                                    intent = new Intent(getActivity(), EnrollActionActivity.class);
//                                    intent.putExtra("activity_id", dataBean.getMarketingActivitySignupId());
//                                    startActivity(intent);
//                                }
//                            } else {
//                                intent = new Intent(getActivity(), LoginActivity.class);
//                                startActivity(intent);
//                            }
//
//                        }
//                    }
//                });

            }
        };


        rvActivity.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getParentFragment().getActivity(), ActionDetailActivity.class);
                intent.putExtra("activity_id", dataList.get(position).getMarketingActivitySignupId());
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

    /**
     * //     * 动态添加布局
     * //
     */
    private void addTextView(FlowLayout flowLayout, List<ActivityListBean.DataBean.TagsBean> tagsBeans) {

        for (int i = 0; i < tagsBeans.size(); i++) {
            TextView tvChild = new TextView(getActivity());
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, (int) DisplayUtils.dp2px(getActivity(), 6), (int) DisplayUtils.dp2px(getActivity(), 2));
            tvChild.setLayoutParams(params);
            tvChild.setBackgroundResource(R.drawable.flowlayout_item);
            tvChild.setText(tagsBeans.get(i).getTagName());
            tvChild.setTextSize(12);
            tvChild.setTextColor(getActivity().getResources().getColor(R.color.red_e6));

            flowLayout.addView(tvChild);
        }

    }

    private void getData() {
        Bundle bundle = getArguments();
        tagId = bundle.getInt("tag_id");
    }

    public static ActivityListFragment newInstance(int tagId) {
        Bundle args = new Bundle();
        args.putInt("tag_id", tagId);
        ActivityListFragment fragment = new ActivityListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
