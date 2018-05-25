package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.MemberCodeBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.fragment.ActivityFragment;
import com.hfbh.yuecheng.fragment.DiscoveryFragment;
import com.hfbh.yuecheng.fragment.HomepageFragment;
import com.hfbh.yuecheng.fragment.MemberCodeFragment;
import com.hfbh.yuecheng.fragment.MineFragment;
import com.hfbh.yuecheng.fragment.PayCodeFragment;
import com.hfbh.yuecheng.utils.FragmentTabUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


/**
 * Author：Libin on 2018/5/23 09:17
 * Email：1993911441@qq.com
 * Describe：电子会员卡
 */
public class MemberCardActivity extends BaseActivity {


    @BindView(R.id.iv_member_back)
    ImageView ivMemberBack;
    @BindView(R.id.rgs_member_code)
    RadioGroup rgsMemberCode;
    @BindView(R.id.fl_member_container)
    FrameLayout flMemberContainer;
    private MemberCodeBean memberCodeBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_card);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.MEMBER_CODE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        memberCodeBean = GsonUtils.jsonToBean(s, MemberCodeBean.class);
                        if (memberCodeBean.isFlag()) {
                            initView();
                        }

                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MemberCodeFragment.newInstance(memberCodeBean));
        fragmentList.add(PayCodeFragment.newInstance(memberCodeBean));
        new FragmentTabUtils(this, getSupportFragmentManager(), fragmentList,
                R.id.fl_member_container, rgsMemberCode);
    }


    @OnClick(R.id.iv_member_back)
    public void onViewClicked() {
        finish();
    }

}
