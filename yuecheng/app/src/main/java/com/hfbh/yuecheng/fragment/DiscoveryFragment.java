package com.hfbh.yuecheng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.MyFragmentAdapter;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.GoodsOrderBean;
import com.hfbh.yuecheng.ui.MainActivity;
import com.hfbh.yuecheng.ui.NewGoodsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：Libin on 2018/5/14 16:09
 * Email：1993911441@qq.com
 * Describe：发现
 */
public class DiscoveryFragment extends BaseFragment {
    @BindView(R.id.tab_discovery)
    SlidingTabLayout tabDiscovery;
    @BindView(R.id.vp_discovery)
    ViewPager vpDiscovery;
    private Unbinder unbinder;

    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (!((MainActivity) getActivity()).isBack) {
            initTitle();
        }
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
     * tab分类
     */
    private void initTitle() {
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList.add("好物");
        titleList.add("美食");
        titleList.add("电影");

        fragmentList.add(OldGoodsFragment.newInstance());
        fragmentList.add(FoodFragment.newInstance());
        fragmentList.add(MovieFragment.newInstance());

        initView();
    }


    /**
     * 绑定viewpager
     */
    private void initView() {

        MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager(), fragmentList, titleList);
        vpDiscovery.setOffscreenPageLimit(titleList.size());
        vpDiscovery.setAdapter(adapter);
        tabDiscovery.setViewPager(vpDiscovery);
        tabDiscovery.setCurrentTab(0);

    }

    public static DiscoveryFragment newInstance() {

        Bundle args = new Bundle();
        DiscoveryFragment fragment = new DiscoveryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
