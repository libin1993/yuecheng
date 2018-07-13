package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.BroadMsgBean;
import com.hfbh.yuecheng.bean.ConsumeBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarttop.library.utils.LogUtil;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
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
 * Author：Libin on 2018/7/12 16:17
 * Email：1993911441@qq.com
 * Describe：消费记录
 */
public class ConsumeRecordActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.rv_now_activity)
    RecyclerView rvConsume;
    @BindView(R.id.layout_refresh_now_activity)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @BindView(R.id.iv_null_data)
    ImageView ivNullData;
    @BindView(R.id.tv_null_data)
    TextView tvNullData;
    @BindView(R.id.ll_null_data)
    LinearLayout llNullData;

    private List<ConsumeBean.DataBean> dataList = new ArrayList<>();
    //活动总数量
    private CommonAdapter<ConsumeBean.DataBean> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_activity);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("我的消费记录");
        llNullData.setVisibility(View.GONE);
        ivNullData.setImageResource(R.mipmap.ic_null_card);
        tvNullData.setText("暂无消费记录");
        initView();
        initData();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.CONSUME_RECORD)
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
                        viewLoading.smoothToHide();
                        ConsumeBean msgBean = GsonUtils.jsonToBean(response, ConsumeBean.class);
                        if (msgBean.isFlag() && msgBean.getData() != null && msgBean.getData().size() > 0) {
                            dataList.clear();
                            dataList.addAll(msgBean.getData());
                            adapter.notifyDataSetChanged();
                            llNullData.setVisibility(View.GONE);
                        } else {
                            dataList.clear();
                            adapter.notifyDataSetChanged();
                            llNullData.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }


    /**
     * 加载视图
     */
    private void initView() {
        rvConsume.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<ConsumeBean.DataBean>(this, R.layout.rv_member_points_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, ConsumeBean.DataBean dataBean, int position) {
                holder.setText(R.id.tv_points_time, dataBean.getOrderTime());
                holder.setText(R.id.tv_points_remark, dataBean.getOrderShopName());
                TextView tvMoney = holder.getView(R.id.tv_points_type);

                if (!TextUtils.isEmpty(dataBean.getTotalMoney())) {
                    if (Double.parseDouble(dataBean.getTotalMoney()) > 0) {
                        tvMoney.setTextColor(getResources().getColor(R.color.red_99));
                        tvMoney.setText("+" + dataBean.getTotalMoney());
                    } else {
                        tvMoney.setTextColor(getResources().getColor(R.color.gray_10));
                        tvMoney.setText(dataBean.getTotalMoney());
                    }

                }
            }
        };

        rvConsume.setAdapter(adapter);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(ConsumeRecordActivity.this, ConsumeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("consume_detail", dataList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
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
