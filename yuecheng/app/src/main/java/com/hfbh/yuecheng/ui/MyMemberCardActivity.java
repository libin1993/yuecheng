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
 * Author：Libin on 2018/5/29 12:41
 * Email：1993911441@qq.com
 * Describe：会员卡
 */
public class MyMemberCardActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_member_card);


    }

}
