package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.UserInfoBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.fragment.MemberCodeFragment;
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


    @BindView(R.id.rgs_member_code)
    RadioGroup rgsMemberCode;
    @BindView(R.id.fl_member_container)
    FrameLayout flMemberContainer;
    @BindView(R.id.tv_title_header_white)
    TextView tvTitleHeaderWhite;
    @BindView(R.id.iv_back_header_white)
    ImageView ivBackHeaderWhite;
    private UserInfoBean userInfoBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_card);
        ButterKnife.bind(this);
        tvTitleHeaderWhite.setText("会员卡二维码");
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.USER_INFO)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        userInfoBean = GsonUtils.jsonToBean(s, UserInfoBean.class);
                        if (userInfoBean.isFlag()) {
                            initView();
                        } else if (userInfoBean.getCode() == 4002) {
                            SharedPreUtils.deleteStr(MemberCardActivity.this, "is_login");
                        }

                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MemberCodeFragment.newInstance(userInfoBean));
        fragmentList.add(PayCodeFragment.newInstance(userInfoBean));
        new FragmentTabUtils(this, getSupportFragmentManager(), fragmentList,
                R.id.fl_member_container, rgsMemberCode);
    }


    @OnClick(R.id.iv_back_header_white)
    public void onViewClicked() {
        finish();
    }
}
