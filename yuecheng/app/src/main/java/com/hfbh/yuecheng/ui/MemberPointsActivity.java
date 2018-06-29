package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.MyFragmentAdapter;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.fragment.MemberPointsFragment;
import com.hfbh.yuecheng.utils.DisplayUtils;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;

/**
 * Author：Libin on 2018/6/1 11:07
 * Email：1993911441@qq.com
 * Describe：会员积分
 */
public class MemberPointsActivity extends BaseActivity {
    @BindView(R.id.tv_title_header)
    TextView tvTitleHeader;
    @BindView(R.id.iv_back_header)
    ImageView ivBackHeader;
    @BindView(R.id.tv_member_points)
    TextView tvMemberPoints;
    @BindView(R.id.tv_points_month)
    TextView tvPointsMonth;
    @BindView(R.id.iv_points_calendar)
    ImageView ivPointsCalendar;
    @BindView(R.id.tab_member_points)
    SlidingTabLayout tabMemberPoints;
    @BindView(R.id.vp_member_points)
    ViewPager vpMemberPoints;

    private List<String> titleList;
    private List<Fragment> fragmentList;
    //年
    private int selectYear;
    //月
    private int selectMonth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_points);
        ButterKnife.bind(this);
        initTitle();

    }


    /**
     * tab分类
     */
    private void initTitle() {

        tvTitleHeader.setText("会员卡积分");
        tvMemberPoints.setText(DisplayUtils.isInteger(getIntent().getDoubleExtra("points", 0)));

        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList.add("全部");
        titleList.add("获取");
        titleList.add("消费");

        fragmentList.add(MemberPointsFragment.newInstance(""));
        fragmentList.add(MemberPointsFragment.newInstance("INCREASE"));
        fragmentList.add(MemberPointsFragment.newInstance("DECREASE"));

        initView();
    }


    /**
     * 绑定viewpager
     */
    private void initView() {

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),
                fragmentList, titleList);
        vpMemberPoints.setOffscreenPageLimit(titleList.size());
        vpMemberPoints.setAdapter(adapter);
        tabMemberPoints.setViewPager(vpMemberPoints);
        tabMemberPoints.setCurrentTab(0);

    }


    @OnClick({R.id.iv_back_header, R.id.iv_points_calendar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_header:
                finish();
                break;
            case R.id.iv_points_calendar:
                selectDate();
                break;
        }
    }

    /**
     * 选择日期
     */
    private void selectDate() {
        DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH);
        picker.setSubmitTextColor(getResources().getColor(R.color.red_99));
        picker.setOffset(3);
        picker.setTopLineColor(getResources().getColor(R.color.gray_ed));

        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH) + 1;

        if (selectYear == 0){
            selectYear = currentYear;
            selectMonth = currentMonth;
        }

        picker.setRangeStart(2000, 1);
        picker.setRangeEnd(currentYear, currentMonth);

        picker.setSelectedItem(selectYear, selectMonth);

        picker.setOnDatePickListener(new DatePicker.OnYearMonthPickListener() {
            @Override
            public void onDatePicked(String year, String month) {
                selectYear = Integer.parseInt(year);
                selectMonth = Integer.parseInt(month);
                tvPointsMonth.setText(year + "年" + month + "月");
                refreshData(year + "-" + month + "-01");
            }

        });
        picker.show();
    }

    /**
     * @param time 刷新数据
     */
    private void refreshData(String time) {

        for (int i = 0; i < fragmentList.size(); i++) {
            ((MemberPointsFragment) fragmentList.get(i)).refreshData(time);
        }
    }
}
