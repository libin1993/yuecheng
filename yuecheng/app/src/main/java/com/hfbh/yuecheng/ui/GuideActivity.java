package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.GuideBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.FrescoUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.smarttop.library.utils.LogUtil;
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
 * Author：Libin on 2018/6/20 11:04
 * Email：1993911441@qq.com
 * Describe：室内导航
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.rv_guide)
    RecyclerView rvGuide;
    private CommonAdapter<GuideBean.DataBean> adapter;
    private List<GuideBean.DataBean> dataList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("室内导航");
        initView();
        initData();

    }

    
    private void initData() {
        OkHttpUtils.get()
                .url(Constant.FLOOR_GUIDE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        GuideBean guideBean = GsonUtils.jsonToBean(response, GuideBean.class);
                        if (guideBean.isFlag() && guideBean.getData() != null && guideBean.getData().size() > 0) {
                            dataList.addAll(guideBean.getData());
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initView() {
        rvGuide.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<GuideBean.DataBean>(
                GuideActivity.this, R.layout.rv_guide_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, GuideBean.DataBean dataBean, int position) {
                SimpleDraweeView ivGuide = holder.getView(R.id.iv_guide);
                ivGuide.setImageURI(dataBean.getFloorPicturePath());
                FrescoUtils.setControllerListener(ivGuide, dataBean.getFloorPicturePath(),
                        DisplayUtils.getMetrics(GuideActivity.this).widthPixels);
            }
        };
        rvGuide.setAdapter(adapter);
    }


    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
