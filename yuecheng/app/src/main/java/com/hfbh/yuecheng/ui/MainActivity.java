package com.hfbh.yuecheng.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.fragment.ActivityFragment;
import com.hfbh.yuecheng.fragment.DiscoveryFragment;
import com.hfbh.yuecheng.fragment.HomepageFragment;
import com.hfbh.yuecheng.fragment.MineFragment;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.FragmentTabUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Author：Libin on 2017/05/14 10:35
 * Email：1993911441@qq.com
 * Describe：首页
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.rgs_main_tab)
    RadioGroup rgsMainTab;

    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentTabUtils fragmentUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 加载视图
     */
    private void initView() {

        fragmentList.add(HomepageFragment.newInstance());
        fragmentList.add(ActivityFragment.newInstance());
        fragmentList.add(DiscoveryFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());
        fragmentUtils = new FragmentTabUtils(this,getSupportFragmentManager(), fragmentList,
                R.id.fl_main_container, rgsMainTab);
    }
}
