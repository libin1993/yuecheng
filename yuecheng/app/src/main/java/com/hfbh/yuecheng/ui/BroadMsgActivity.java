package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityListBean;
import com.hfbh.yuecheng.bean.BroadMsgBean;
import com.hfbh.yuecheng.bean.BroadcastBean;
import com.hfbh.yuecheng.bean.UnreadMsgBean;
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
 * Author：Libin on 2018/7/12 14:12
 * Email：1993911441@qq.com
 * Describe：广播消息
 */
public class BroadMsgActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.rv_now_activity)
    RecyclerView rvMsg;
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

    private List<BroadMsgBean.DataBean> dataList = new ArrayList<>();
    private CommonAdapter<BroadMsgBean.DataBean> adapter;
    //刷新
    private boolean isRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_activity);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("头条广播");
        ivNullData.setImageResource(R.mipmap.ic_null_order);
        tvNullData.setText("暂无消息");
        viewLoading.smoothToShow();
        initView();
        initData();
    }

    /**
     * 加载数据
     */
    private void initData() {
        OkHttpUtils.post()
                .url(Constant.MSG_LIST)
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
                        BroadMsgBean msgBean = GsonUtils.jsonToBean(response, BroadMsgBean.class);
                        dataList.clear();
                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                            isRefresh = false;
                        }else {
                            viewLoading.smoothToHide();
                        }

                        if (msgBean.isFlag() && msgBean.getData() != null && msgBean.getData().size() > 0) {
                            dataList.addAll(msgBean.getData());
                            llNullData.setVisibility(View.GONE);
                            readMsg();
                        } else {
                            llNullData.setVisibility(View.VISIBLE);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 设置已读
     */
    private void readMsg() {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getIsRead() != null && dataList.get(i).getIsRead().equals("N")) {
                OkHttpUtils.post()
                        .url(Constant.READ_MSG)
                        .addParams("appType", MyApp.appType)
                        .addParams("appVersion", MyApp.appVersion)
                        .addParams("organizeId", MyApp.organizeId)
                        .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                        .addParams("token", SharedPreUtils.getStr(this, "token"))
                        .addParams("memberBroadcastId", String.valueOf(dataList.get(i).getMemberBroadcastId()))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                            }
                        });
            }
        }
    }

    /**
     * 加载视图
     */
    private void initView() {
        rvMsg.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<BroadMsgBean.DataBean>(this, R.layout.rv_msg_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, BroadMsgBean.DataBean dataBean, int position) {
                holder.setText(R.id.tv_msg_content, dataBean.getContent());
                holder.setText(R.id.tv_msg_time, dataBean.getCreateTime());
                if (dataBean.getIsRead() != null && dataBean.getIsRead().equals("Y")) {
                    holder.setVisible(R.id.view_unread_msg, false);
                } else {
                    holder.setVisible(R.id.view_unread_msg, true);
                }
            }
        };
        rvMsg.setAdapter(adapter);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                isRefresh = true;
                initData();
            }
        });
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
