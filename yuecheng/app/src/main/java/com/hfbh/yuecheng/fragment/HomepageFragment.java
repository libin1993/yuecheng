package com.hfbh.yuecheng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：Libin on 2018/5/14 16:08
 * Email：1993911441@qq.com
 * Describe：首页
 */
public class HomepageFragment extends BaseFragment{
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static HomepageFragment newInstance() {

        Bundle args = new Bundle();
        HomepageFragment fragment = new HomepageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
