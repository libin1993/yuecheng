package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.adapter.MyPagerAdapter;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.MemberRightsBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.FadePageTransformer;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.view.CustomViewPager;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/4 10:11
 * Email：1993911441@qq.com
 * Describe：会员权限
 */
public class MemberRightsActivity extends BaseActivity {
    @BindView(R.id.tv_title_header)
    TextView tvTitleHeader;
    @BindView(R.id.iv_back_header)
    ImageView ivBackHeader;
    @BindView(R.id.tv_member_rights_name)
    TextView tvMemberRights;
    @BindView(R.id.rv_member_rights_info)
    RecyclerView rvMemberRights;
    @BindView(R.id.vp_member_rights)
    CustomViewPager vpMemberRights;
    private MemberRightsBean memberBean;

    private CommonAdapter<MemberRightsBean.DataBean.ListPrivilegeBean> rightsAdapter;
    private List<MemberRightsBean.DataBean.ListPrivilegeBean> dataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_rights);
        ButterKnife.bind(this);
        tvTitleHeader.setText("会员卡特权");
        initData();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.MEMBER_RIGHTS)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        memberBean = GsonUtils.jsonToBean(response, MemberRightsBean.class);
                        if (memberBean.isFlag() && memberBean.getData() != null && memberBean.getData().size() > 0) {
                            initView();
                        }
                    }
                });

    }

    private void initView() {
        vpMemberRights.setOffscreenPageLimit(memberBean.getData().size());
        vpMemberRights.setPageTransformer(false, new FadePageTransformer());
        vpMemberRights.setPageMargin((int) DisplayUtils.dp2px(this, 10));
        final MyPagerAdapter adapter = new MyPagerAdapter(memberBean.getData(), this);
        vpMemberRights.setAdapter(adapter);

        rvMemberRights.setLayoutManager(new GridLayoutManager(this, 3));
        rightsAdapter = new CommonAdapter<MemberRightsBean.DataBean.ListPrivilegeBean>(
                this, R.layout.rv_member_rights_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, MemberRightsBean.DataBean.ListPrivilegeBean
                    listPrivilegeBean, int position) {
                Glide.with(MemberRightsActivity.this)
                        .load(listPrivilegeBean.getAppPic())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into((ImageView) holder.getView(R.id.iv_member_rights));

                holder.setText(R.id.tv_member_rights, listPrivilegeBean.getPrivilegeName());
            }
        };
        rvMemberRights.setAdapter(rightsAdapter);

        vpMemberRights.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                initRights(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initRights(0);


    }

    private void initRights(int position) {
        tvMemberRights.setText(memberBean.getData().get(position).getGradeName());
        dataList.clear();
        if (memberBean.getData().get(position).getListPrivilege() != null) {
            dataList.addAll(memberBean.getData().get(position).getListPrivilege());
        }

        rightsAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.iv_back_header)
    public void onViewClicked() {
        finish();
    }
}
