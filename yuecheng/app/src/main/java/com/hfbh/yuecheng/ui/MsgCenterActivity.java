package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.UnreadMsgBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.smarttop.library.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/4 17:04
 * Email：1993911441@qq.com
 * Describe：消息中心
 */
public class MsgCenterActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_broad_msg)
    TextView tvBroadMsg;
    @BindView(R.id.ll_broad_msg)
    LinearLayout llBroadMsg;
    @BindView(R.id.tv_system_msg)
    TextView tvSystemMsg;
    @BindView(R.id.ll_system_msg)
    LinearLayout llSystemMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_center);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("消息");

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    /**
     * 加载数据
     */
    private void initData() {
        OkHttpUtils.post()
                .url(Constant.UNREAD_MSG)
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
                        UnreadMsgBean msgBean = GsonUtils.jsonToBean(response, UnreadMsgBean.class);
                        if (msgBean.isFlag()) {

                            int unreadNum = msgBean.getData();
                            if (unreadNum == 0) {
                                tvBroadMsg.setVisibility(View.GONE);
                            } else if (unreadNum > 0 && unreadNum <= 99) {
                                tvBroadMsg.setVisibility(View.VISIBLE);
                                tvBroadMsg.setText(String.valueOf(unreadNum));
                            } else {
                                tvBroadMsg.setVisibility(View.VISIBLE);
                                tvBroadMsg.setText("99+");
                            }
                        } else {
                            tvBroadMsg.setVisibility(View.GONE);
                        }
                    }
                });
    }



    @OnClick({R.id.iv_header_back, R.id.ll_broad_msg, R.id.ll_system_msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.ll_broad_msg:
                startActivity(new Intent(this, BroadMsgActivity.class));
                break;
            case R.id.ll_system_msg:
                break;
        }
    }
}
