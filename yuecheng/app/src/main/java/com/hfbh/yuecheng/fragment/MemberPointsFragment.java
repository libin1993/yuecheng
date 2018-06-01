package com.hfbh.yuecheng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.HomepageTypeBean;
import com.hfbh.yuecheng.bean.MemberPointsBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.wang.avi.AVLoadingIndicatorView;
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
 * Author：Libin on 2018/6/1 12:14
 * Email：1993911441@qq.com
 * Describe：会员积分明细
 */
public class MemberPointsFragment extends BaseFragment {
    @BindView(R.id.rv_member_points)
    RecyclerView rvMemberPoints;
    @BindView(R.id.tv_no_points)
    TextView tvNoPoints;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    private Unbinder unbinder;
    //类型
    private String type;
    //日期
    private String time;

    private boolean isRefresh;

    private List<MemberPointsBean.DataBean.PointsChangeListBean> dataList = new ArrayList<>();
    private CommonAdapter<MemberPointsBean.DataBean.PointsChangeListBean> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_points, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        initData();
        return view;
    }

    private void getData() {
        Bundle bundle = getArguments();
        type = bundle.getString("type");
        time = bundle.getString("time");
    }

    private void initData() {
        viewLoading.smoothToShow();
        OkHttpUtils.post()
                .url(Constant.MEMBER_POINTS_RECORD)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .addParams("changeType", type)
                .addParams("createTimeStr", time)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        LogUtils.log(type + time + s);
                        viewLoading.smoothToHide();
                        MemberPointsBean memberPointsBean = GsonUtils.jsonToBean(s, MemberPointsBean.class);
                        if (memberPointsBean.isFlag() && memberPointsBean.getData().getPointsChangeList()
                                != null && memberPointsBean.getData().getPointsChangeList().size() > 0) {
                            if (isRefresh) {
                                dataList.clear();
                            }
                            dataList.addAll(memberPointsBean.getData().getPointsChangeList());
                            if (isRefresh) {
                                adapter.notifyDataSetChanged();
                                isRefresh = false;
                            } else {
                                initView();
                            }

                            rvMemberPoints.setVisibility(View.VISIBLE);
                            tvNoPoints.setVisibility(View.GONE);
                        } else {
                            rvMemberPoints.setVisibility(View.GONE);
                            tvNoPoints.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {
        rvMemberPoints.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter<MemberPointsBean.DataBean.PointsChangeListBean>(getActivity(),
                R.layout.rv_member_points_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, MemberPointsBean.DataBean.PointsChangeListBean
                    pointsChangeListBean, int position) {
                holder.setText(R.id.tv_points_remark, pointsChangeListBean.getPointsRemark());
                holder.setText(R.id.tv_points_time, pointsChangeListBean.getCreateTime());
                TextView tvPoints = holder.getView(R.id.tv_points_type);
                if (pointsChangeListBean.getChangeType() != null) {
                    if (pointsChangeListBean.getChangeType().equals("INCREASE")) {
                        tvPoints.setTextColor(getActivity().getResources().getColor(R.color.red_99));
                        tvPoints.setText("+" + pointsChangeListBean.getPoints());
                    } else {
                        tvPoints.setTextColor(getActivity().getResources().getColor(R.color.gray_10));
                        tvPoints.setText("-" + pointsChangeListBean.getPoints());
                    }
                }
            }
        };
        rvMemberPoints.setAdapter(adapter);
    }

    public static MemberPointsFragment newInstance(String type, String time) {
        Bundle args = new Bundle();
        args.putString("type", type);
        args.putString("time", time);
        MemberPointsFragment fragment = new MemberPointsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * @param selectTime 刷新数据
     */
    public void refreshData(String selectTime) {
        time = selectTime;
        isRefresh = true;
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
