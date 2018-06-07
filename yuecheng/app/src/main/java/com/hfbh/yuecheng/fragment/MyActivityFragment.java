package com.hfbh.yuecheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.ActivityListBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.ActionDetailActivity;
import com.hfbh.yuecheng.ui.CloseActionActivity;
import com.hfbh.yuecheng.ui.EnrollActionActivity;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
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
 * Author：Libin on 2018/5/29 12:49
 * Email：1993911441@qq.com
 * Describe：
 */
public class MyActivityFragment extends BaseFragment {
    @BindView(R.id.rv_my_activity)
    RecyclerView rvActivity;
    @BindView(R.id.layout_refresh_my_activity)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;
    @BindView(R.id.tv_null_activity)
    TextView tvNullActivity;
    private Unbinder unbinder;

    //分类
    private int type;
    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    private List<ActivityListBean.DataBean> dataList = new ArrayList<>();
    //总页数
    private int pages;
    private CommonAdapter<ActivityListBean.DataBean> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_activity, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        loadingView.smoothToShow();
        initData();
        return view;
    }

    private void initData() {
        OkHttpUtils.get()
                .url(Constant.ACTIVITY_LIST)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))

                .addParams("pageNum", String.valueOf(page))
                .addParams("type", String.valueOf(type))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
// .addParams("memberId", SharedPreUtils.getStr(getActivity(), "member_id"))
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ActivityListBean activityListBean = GsonUtils.jsonToBean(response, ActivityListBean.class);
                        if (activityListBean.getPage() != null) {
                            pages = activityListBean.getPage().getPages();
                        }

                        if (activityListBean.isFlag() && activityListBean.getData().size() > 0) {
                            if (isRefresh) {
                                dataList.clear();
                            }
                            dataList.addAll(activityListBean.getData());

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
                            rvActivity.setVisibility(View.VISIBLE);
                            tvNullActivity.setVisibility(View.GONE);
                        } else {
                            refreshLayout.finishLoadMore();
                            if (page == 1) {
                                loadingView.smoothToHide();
                                rvActivity.setVisibility(View.GONE);
                                tvNullActivity.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {
        rvActivity.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter<ActivityListBean.DataBean>(getActivity(),
                R.layout.rv_my_activity_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, ActivityListBean.DataBean dataBean, final int position) {
                SimpleDraweeView ivCoupon = holder.getView(R.id.iv_my_activity);
                ivCoupon.setImageURI(dataBean.getActivityPicture());

                holder.setText(R.id.tv_my_activity_name, dataBean.getActivityTitle());
                holder.setText(R.id.tv_activity_range, "有效时间： " + dataBean
                        .getStartTimeStr() + " - " + dataBean.getEndTimeStr());

                FlowLayout flowLayout = holder.getView(R.id.flow_my_activity);
                flowLayout.removeAllViews();
                if (dataBean.getTags() != null && dataBean.getTags().size() > 0) {
                    addTextView(flowLayout, dataBean.getTags());
                }

                TextView tvCost = holder.getView(R.id.tv_activity_cost);
                if (!TextUtils.isEmpty(dataBean.getAcivityType())) {
                    switch (dataBean.getAcivityType()) {
                        case "NONEED":
                            tvCost.setText("无需报名");
                            break;
                        case "FREE":
                            tvCost.setText("免费");
                            break;
                        case "SCORE":
                            tvCost.setText("已报名： " + DisplayUtils.isInteger(dataBean.getEnrollScore()) + "积分");
                            break;
                        case "CASH":
                            tvCost.setText("已支付： ¥" + DisplayUtils.isInteger(dataBean.getEnrollFee()));
                            break;
                    }
                }
                final TextView tvJoin = holder.getView(R.id.tv_activity_join);
                final TextView tvStatus = holder.getView(R.id.tv_activity_status);

                final String status = dataBean.getMemberSignupState();
                if (!TextUtils.isEmpty(status)) {
                    switch (status) {
                        case "待报名":
                        case "已结束":
                            tvStatus.setVisibility(View.VISIBLE);
                            tvJoin.setVisibility(View.GONE);
                            tvStatus.setText(status);
                            break;
                        case "去报名":
                        case "去参加":
                            tvStatus.setVisibility(View.GONE);
                            tvJoin.setVisibility(View.VISIBLE);
                            tvJoin.setText(status);
                            break;

                    }
                }

                tvJoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = null;
                        if (!TextUtils.isEmpty(status)) {
                            switch (status) {
                                case "去报名":
                                    intent = new Intent(getActivity(), EnrollActionActivity.class);
                                    intent.putExtra("activity_id", dataList.get(position).getMarketingActivitySignupId());
                                    break;
                                case "去参加":
                                    intent = new Intent(getActivity(), CloseActionActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("activity_info", dataList.get(position));
                                    intent.putExtras(bundle);
                                    break;
                            }
                        }
                        startActivity(intent);
                    }
                });

            }
        };


        rvActivity.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {


                Intent intent = new Intent(getActivity(), ActionDetailActivity.class);
                intent.putExtra("activity_id", dataList.get(position).getMarketingActivitySignupId());
                intent.putExtra("type", dataList.get(position).getAcivityType());
                intent.putExtra("money", dataList.get(position).getEnrollFee());
                intent.putExtra("score", dataList.get(position).getEnrollScore());
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
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(
                    ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, (int) DisplayUtils.dp2px(getActivity(), 6),
                    (int) DisplayUtils.dp2px(getActivity(), 2));
            tvChild.setLayoutParams(params);
            tvChild.setBackgroundResource(R.drawable.flowlayout_item);
            tvChild.setText(tagsBeans.get(i).getTagName());
            tvChild.setTextSize(12);
            tvChild.setTextColor(getActivity().getResources().getColor(R.color.red_e6));

            flowLayout.addView(tvChild);
        }

    }

    private void getData() {
        type = getArguments().getInt("type");
    }

    public static MyActivityFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt("type", type);
        MyActivityFragment fragment = new MyActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
