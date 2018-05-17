package com.hfbh.yuecheng.utils;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.bean.TabIconBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面 底部切换tab工具类（加载本地图片）
 */
public class FragmentTabUtils implements RadioGroup.OnCheckedChangeListener {
    private List<Fragment> fragmentList; // 一个tab页面对应一个Fragment
    private RadioGroup rgs; // 用于切换tab
    private FragmentManager fragmentManager; // Fragment所属的Activity
    private int flContainerId; // Activity中当前fragment的区域的id
    private int currentTab; // 当前Tab页面索引
    private Context context;


    /**
     * @param fragmentManager
     * @param fragmentList
     * @param flContainerId
     * @param rgs
     */
    public FragmentTabUtils(Context context, FragmentManager fragmentManager, List<Fragment> fragmentList,
                            int flContainerId, RadioGroup rgs) {

        this.context = context;
        this.fragmentList = fragmentList;
        this.rgs = rgs;
        this.fragmentManager = fragmentManager;
        this.flContainerId = flContainerId;

        initIcon();
        rgs.setOnCheckedChangeListener(this);

        ((RadioButton) rgs.getChildAt(0)).setChecked(true);

    }

    /**
     * 初始化icon
     */
    private void initIcon() {
        int width = (int) DisplayUtils.dp2px(context, 22);
        int height = (int) DisplayUtils.dp2px(context, 20);
        Drawable drawableHome = context.getResources().getDrawable(R.drawable.selector_homepage_tab);
        drawableHome.setBounds(0, 0, width, height);
        ((RadioButton) rgs.getChildAt(0)).setCompoundDrawables(null, drawableHome, null, null);

        Drawable drawableActivity = context.getResources().getDrawable(R.drawable.selector_activity_tab);
        drawableActivity.setBounds(0, 0, width, height);
        ((RadioButton) rgs.getChildAt(1)).setCompoundDrawables(null, drawableActivity, null, null);

        Drawable drawableDiscovery = context.getResources().getDrawable(R.drawable.selector_discovery_tab);
        drawableDiscovery.setBounds(0, 0, width, height);
        ((RadioButton) rgs.getChildAt(2)).setCompoundDrawables(null, drawableDiscovery, null, null);

        Drawable drawableMine = context.getResources().getDrawable(R.drawable.selector_mine_tab);
        drawableMine.setBounds(0, 0, width, height);
        ((RadioButton) rgs.getChildAt(3)).setCompoundDrawables(null, drawableMine, null, null);

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        for (int i = 0; i < rgs.getChildCount(); i++) {
            RadioButton rBtn = ((RadioButton) rgs.getChildAt(i));
            if (rBtn.getId() == checkedId) {
                switchFragment(i);
            }

        }

    }

    /**
     * 点击切换fragment
     *
     * @param position
     */
    private void switchFragment(int position) {
        //开启事务
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //遍历集合
        for (int i = 0; i < fragmentList.size(); i++) {
            Fragment fragment = fragmentList.get(i);
            if (i == position) {
                //显示fragment
                if (fragment.isAdded()) {
                    //如果这个fragment已经被事务添加,显示
                    ft.show(fragment);
                } else {
                    //如果这个fragment没有被事务添加过,添加
                    ft.add(flContainerId, fragment);
                }
            } else {
                //隐藏fragment
                if (fragment.isAdded()) {
                    ft.hide(fragment);
                }
            }
        }
        //提交事务
        ft.commit();
    }


    /**
     * @param i 加载fragment
     */
    private void initFragment(int i) {
        Fragment fragment = fragmentList.get(i);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        fragmentList.get(currentTab).onStop(); // 暂停当前tab
        if (fragment.isAdded()) {
            fragment.onStart(); // 启动目标tab的fragment onStart()
        } else {
            ft.add(flContainerId, fragment, fragment.getClass().getName());
            ft.commit();
        }
        showTab(i); // 显示目标tab
    }

    /**
     * @param position 设置当前fragment
     */
    public void setCurrentFragment(int position) {
        ((RadioButton) rgs.getChildAt(position)).setChecked(true);
    }


    /**
     * 切换tab
     *
     * @param index
     */
    private void showTab(int index) {
        for (int i = 0; i < fragmentList.size(); i++) {
            Fragment fragment = fragmentList.get(i);
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (index == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commit();
        }
        currentTab = index; // 更新目标tab为当前tab
    }
}