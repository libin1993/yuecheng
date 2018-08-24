package com.hfbh.yuecheng.fragment;

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

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.MemberPointsBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.ForgetPwdActivity;
import com.hfbh.yuecheng.ui.MemberPointsActivity;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.smarttop.library.utils.LogUtil;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @BindView(R.id.iv_null_data)
    ImageView ivNullData;
    @BindView(R.id.tv_null_data)
    TextView tvNullData;
    @BindView(R.id.ll_null_data)
    LinearLayout llNullData;
    private Unbinder unbinder;
    //类型
    private String type;
    //日期
    private String time;

    private List<MemberPointsBean.DataBean.PointsChangeListBean> dataList = new ArrayList<>();
    private CommonAdapter<MemberPointsBean.DataBean.PointsChangeListBean> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_points, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        tvNullData.setText("暂无积分明细");
        ivNullData.setImageResource(R.mipmap.ic_null_order);
        viewLoading.smoothToShow();
        initView();
        initData();
        return view;
    }

    private void getData() {
        Bundle bundle = getArguments();
        type = bundle.getString("type");
    }

    private void initData() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appType", MyApp.appType);
        paramMap.put("appVersion", MyApp.appVersion);
        paramMap.put("organizeId", MyApp.organizeId);
        paramMap.put("hash", SharedPreUtils.getStr(getActivity(), "hash"));
        paramMap.put("token", SharedPreUtils.getStr(getActivity(), "token"));
        paramMap.put("changeType", type);
        if (!TextUtils.isEmpty(time)) {
            paramMap.put("createTimeStr", time);
        }

        OkHttpUtils.post()
                .url(Constant.MEMBER_POINTS_RECORD)
                .params(paramMap)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {

                        MemberPointsBean memberPointsBean = GsonUtils.jsonToBean(s, MemberPointsBean.class);
                        viewLoading.smoothToHide();
                        dataList.clear();

                        if (memberPointsBean.isFlag() && memberPointsBean.getData().getPointsChangeList()
                                != null && memberPointsBean.getData().getPointsChangeList().size() > 0) {
                            dataList.addAll(memberPointsBean.getData().getPointsChangeList());

                            llNullData.setVisibility(View.GONE);
                        } else {
                            llNullData.setVisibility(View.VISIBLE);
                            if (memberPointsBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(getActivity(), "is_login");
                            }
                        }
                        adapter.notifyDataSetChanged();
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
                TextView tvRemark = holder.getView(R.id.tv_points_remark);
                if (pointsChangeListBean.getType() == 20 || pointsChangeListBean.getType() == 21) {
                    tvRemark.setText(TextUtils.isEmpty(pointsChangeListBean.getPointsRemark()) ?
                            "线上积分变动" : pointsChangeListBean.getPointsRemark());
                } else {
                    tvRemark.setText(pointsChangeListBean.getChangeWay());
                }

                holder.setText(R.id.tv_points_time, pointsChangeListBean.getCreateTime());
                TextView tvPoints = holder.getView(R.id.tv_points_type);

                double points = pointsChangeListBean.getPoints();
                if (points > 0) {
                    tvPoints.setTextColor(getActivity().getResources().getColor(R.color.red_99));
                    tvPoints.setText("+" + DisplayUtils.isInteger(points));
                } else {
                    tvPoints.setTextColor(getActivity().getResources().getColor(R.color.gray_10));
                    tvPoints.setText(DisplayUtils.isInteger(points));
                }
            }
        };
        rvMemberPoints.setAdapter(adapter);
    }

    public static MemberPointsFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        MemberPointsFragment fragment = new MemberPointsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * @param selectTime 刷新数据
     */
    public void refreshData(String selectTime) {
        time = selectTime;
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
