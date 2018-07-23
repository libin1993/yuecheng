package com.hfbh.yuecheng.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.CityListBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.smarttop.library.utils.LogUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/21 18:14
 * Email：1993911441@qq.com
 * Describe：商场切换
 */
public class ChangeMarketActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.rv_city_list)
    RecyclerView rvCityList;
    @BindView(R.id.rv_market_list)
    RecyclerView rvMarketList;

    //当前城市
    private int currentCity;
    private CommonAdapter<CityListBean.DataBean> cityAdapter;
    private List<CityListBean.DataBean> cityList = new ArrayList<>();
    private List<CityListBean.DataBean.OrganizeListBean> marketList = new ArrayList<>();
    private CommonAdapter<CityListBean.DataBean.OrganizeListBean> marketAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_market);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("商场切换");
        initView();
        initData();
    }

    /**
     * 加载数据
     */
    private void initData() {

        OkHttpUtils.post()
                .url(Constant.CHANGE_CITY)
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
                        CityListBean cityBean = GsonUtils.jsonToBean(s, CityListBean.class);
                        if (cityBean.isFlag() && cityBean.getData().size() > 0) {
                            cityList.addAll(cityBean.getData());
                            for (int j = 0; j < cityBean.getData().size(); j++) {
                                if ("Y".equals(cityBean.getData().get(j).getIsSelected())) {
                                    currentCity = j;
                                    break;
                                }
                            }
                            cityAdapter.notifyDataSetChanged();

                            marketList.addAll(cityList.get(currentCity).getOrganizeList());
                            marketAdapter.notifyDataSetChanged();

                        }
                    }
                });

    }

    /**
     * 加载城市
     */
    private void initView() {

        rvCityList.setLayoutManager(new LinearLayoutManager(this));
        cityAdapter = new CommonAdapter<CityListBean.DataBean>
                (this, R.layout.rv_city_item, cityList) {
            @Override
            protected void convert(ViewHolder holder, CityListBean.DataBean dataBean, int position) {
                TextView tvCity = holder.getView(R.id.tv_city_name);
                tvCity.setText(dataBean.getCityName());
                if (position == currentCity) {
                    tvCity.setTextColor(Color.WHITE);
                    tvCity.setBackgroundResource(R.drawable.gradient_red_15dp);
                } else {
                    tvCity.setTextColor(getResources().getColor(R.color.gray_10));
                    tvCity.setBackgroundResource(0);
                }
            }
        };

        rvCityList.setAdapter(cityAdapter);

        cityAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                currentCity = holder.getLayoutPosition();
                cityAdapter.notifyDataSetChanged();

                marketList.clear();
                marketList.addAll(cityList.get(currentCity).getOrganizeList());
                marketAdapter.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


        rvMarketList.setLayoutManager(new LinearLayoutManager(this));
        marketAdapter = new CommonAdapter<CityListBean.DataBean.OrganizeListBean>(this,
                R.layout.rv_market_item, marketList) {
            @Override
            protected void convert(ViewHolder holder, CityListBean.DataBean.OrganizeListBean
                    organizeListBean, int position) {
                SimpleDraweeView ivMarket = holder.getView(R.id.iv_market_bg);
                ivMarket.setImageURI(organizeListBean.getOrganizePicturePath());
                holder.setText(R.id.tv_market_name, organizeListBean.getOrganizeName());
                holder.setText(R.id.tv_market_address, organizeListBean.getOrganizeAddress());

            }
        };

        rvMarketList.setAdapter(marketAdapter);

        marketAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                int currentMarket = holder.getLayoutPosition();
                MyApp.organizeId = String.valueOf(marketList.get(currentMarket).getOrganizeId());
                MyApp.organizeName = marketList.get(currentMarket).getOrganizeName();
                EventBus.getDefault().post("change_market");
                startActivity(new Intent(ChangeMarketActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }



    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
