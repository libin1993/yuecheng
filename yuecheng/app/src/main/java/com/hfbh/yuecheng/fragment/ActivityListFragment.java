package com.hfbh.yuecheng.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.ActivityListBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
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
 * Describe：
 */
public class ActivityListFragment extends BaseFragment {

    @BindView(R.id.rv_activity_list)
    LRecyclerView rvActivity;
    @BindView(R.id.sv_no_activity)
    NestedScrollView svNoActivity;
    private Unbinder unbinder;
    private int tagId;
    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    private List<ActivityListBean.DataBean> dataList = new ArrayList<>();
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private int pages= 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
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
                .addParams("tagId", String.valueOf(tagId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ActivityListBean activityListBean = GsonUtils.jsonToBean(response, ActivityListBean.class);
                        pages = activityListBean.getPage().getPages();
                        if (activityListBean.isFlag() && activityListBean.getData().size() > 0) {
                            if (isRefresh) {
                                dataList.clear();
                            }
                            dataList.addAll(activityListBean.getData());
                            if (isRefresh || isLoadMore) {
                                rvActivity.refreshComplete(0);
                                mLRecyclerViewAdapter.notifyDataSetChanged();
                            } else {
                                initView();
                            }
                            isRefresh = false;
                            isLoadMore = false;
                            svNoActivity.setVisibility(View.GONE);
                        } else {
                            RecyclerViewStateUtils.setFooterViewState(getParentFragment().getActivity(),
                                    rvActivity, 0, LoadingFooter.State.NoMore, null);
                            if (page == 1) {
                                svNoActivity.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }

    private void initView() {
        rvActivity.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getParentFragment().getActivity(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getParentFragment().getActivity(),R.drawable.custom_divider));
        rvActivity.addItemDecoration(divider);
        CommonAdapter<ActivityListBean.DataBean> adapter = new CommonAdapter<ActivityListBean.DataBean>
                (getParentFragment().getActivity(), R.layout.rv_activity_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, ActivityListBean.DataBean dataBean, int position) {
                SimpleDraweeView ivCoupon = holder.getView(R.id.iv_home_activity);
                ivCoupon.setImageURI(dataBean.getActivityPicture());
                holder.setText(R.id.tv_home_activity_name, dataBean.getActivityTitle());
                holder.setText(R.id.tv_home_activity_time, dataBean
                        .getStartTimeStr() + " - " + dataBean.getEndTimeStr());

                TextView tvReceive = holder.getView(R.id.tv_home_activity_receive);
                String needScore = (String) dataBean.getVerifyCode();
                if (!TextUtils.isEmpty(needScore)) {
                    tvReceive.setText(needScore + "积分报名");
                } else {
                    tvReceive.setText("免费报名");
                }

//                FlowLayout flowLayout = holder.getView(R.id.flow_home_activity);
//                if (flowLayout.getChildCount() == 0) {
//                    addTextView(flowLayout, dataList.get(position).getTags());
//                }
            }
        };


        mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        rvActivity.setAdapter(mLRecyclerViewAdapter);

        //刷新
        rvActivity.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        isRefresh = true;
                        page = 1;
                        initData();
                    }
                }, 1000);
            }
        });

        //加载更多
        rvActivity.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (page < pages){
                    RecyclerViewStateUtils.setFooterViewState(getParentFragment().getActivity(), rvActivity,
                            0, LoadingFooter.State.Loading, null);
                    isLoadMore = true;
                    page++;
                    initData();
                }else {
                    RecyclerViewStateUtils.setFooterViewState(getParentFragment().getActivity(),
                            rvActivity, 0, LoadingFooter.State.NoMore, null);
                }
            }
        });

    }

    /**
     * //     * 动态添加布局
     * //
     */
//    private void addTextView(FlowLayout flowLayout, List<ActivityListBean.DataBean.> tagsBeans) {
//
//        for (int i = 0; i < tagsBeans.size(); i++) {
//            TextView tvChild = new TextView(getActivity());
//            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
//            params.setMargins(0, 0, (int) DisplayUtils.dp2px(getActivity(), 6), 0);
//            tvChild.setLayoutParams(params);
//            tvChild.setBackgroundResource(R.drawable.flowlayout_item);
//            tvChild.setText(tagsBeans.get(i).getTagName());
//            tvChild.setTextSize(12);
//            tvChild.setTextColor(getActivity().getResources().getColor(R.color.red_e6));
//
//            flowLayout.addView(tvChild);
//        }
//
//    }
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
