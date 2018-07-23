package com.hfbh.yuecheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.flyco.tablayout.SlidingTabLayout;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.MyFragmentAdapter;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.ActivityListBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.CalendarActivity;
import com.hfbh.yuecheng.ui.MainActivity;
import com.hfbh.yuecheng.ui.NowActionActivity;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.smarttop.library.utils.LogUtil;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/14 16:12
 * Email：1993911441@qq.com
 * Describe：活动
 */
public class ActivityFragment extends BaseFragment {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_activity_now)
    SimpleDraweeView ivActivityNow;
    @BindView(R.id.iv_activity_calendar)
    SimpleDraweeView ivActivityCalendar;
    @BindView(R.id.tab_activity)
    SlidingTabLayout tabActivity;
    @BindView(R.id.vp_activity)
    ViewPager vpActivity;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;

    private Unbinder unbinder;

    private List<ActivityListBean.TagListBean> tagList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvHeaderTitle.setText("活动");
        ivHeaderBack.setVisibility(View.GONE);
        loadingView.smoothToShow();
        initTitle();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((MainActivity) getActivity()).isBack) {
            initTitle();
        }
    }

    /**
     * 活动标签
     */
    public void initTitle() {
        OkHttpUtils.get()
                .url(Constant.ACTIVITY_LIST)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                .addParams("pageNum", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        loadingView.smoothToHide();
                        ActivityListBean activityListBean = GsonUtils.jsonToBean(response, ActivityListBean.class);
                        tagList = new ArrayList<>();
                        if (activityListBean.isFlag() && activityListBean.getTagList().size() > 0) {
                            tagList.addAll(activityListBean.getTagList());
                            initView();
                        }
                    }
                });
    }

    /**
     * tab关联viewpager
     */
    private void initView() {
        List<String> titleList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        for (int i = 0; i < tagList.size(); i++) {
            titleList.add(tagList.get(i).getTagName());
            fragmentList.add(ActivityListFragment.newInstance(tagList.get(i).getId()));
        }

        //tab个数低于5个平分，不可滑动
        if (tagList.size() <= 5) {
            tabActivity.setTabSpaceEqual(true);
        } else {
            tabActivity.setTabSpaceEqual(false);
        }
        MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager(),
                fragmentList, titleList);
        vpActivity.setOffscreenPageLimit(titleList.size());
        vpActivity.setAdapter(adapter);

        tabActivity.setViewPager(vpActivity);
        tabActivity.setCurrentTab(0);
    }

    public static ActivityFragment newInstance() {
        Bundle args = new Bundle();
        ActivityFragment fragment = new ActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_activity_now, R.id.iv_activity_calendar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_activity_now:
                startActivity(new Intent(getActivity(), NowActionActivity.class));
                break;
            case R.id.iv_activity_calendar:
                startActivity(new Intent(getActivity(), CalendarActivity.class));
                break;
        }
    }
}
