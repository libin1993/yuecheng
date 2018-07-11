package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.MyFragmentAdapter;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.fragment.MyActivityFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Libin on 2018/5/29 12:36
 * Email：1993911441@qq.com
 * Describe：我的活动
 */
public class MyActionActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tab_my_activity)
    SlidingTabLayout tabMyActivity;
    @BindView(R.id.vp_my_activity)
    ViewPager vpMyActivity;


    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity);
        ButterKnife.bind(this);
        initTitle();
    }

    /**
     * tab分类
     */
    private void initTitle() {
        tvHeaderTitle.setText("活动");
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList.add("待参加");
        titleList.add("全部活动");

        fragmentList.add(MyActivityFragment.newInstance(0));
        fragmentList.add(MyActivityFragment.newInstance(1));

        initView();
    }


    /**
     * 绑定viewpager
     */
    private void initView() {

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),
                fragmentList, titleList);
//        vpMyActivity.setOffscreenPageLimit(titleList.size());
        vpMyActivity.setAdapter(adapter);
        tabMyActivity.setViewPager(vpMyActivity);
        tabMyActivity.setCurrentTab(0);
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
