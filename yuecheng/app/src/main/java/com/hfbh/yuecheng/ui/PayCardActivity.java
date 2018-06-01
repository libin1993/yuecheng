package com.hfbh.yuecheng.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.MemberBalanceBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
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
 * Author：Libin on 2018/6/1 15:40
 * Email：1993911441@qq.com
 * Describe：预付卡
 */
public class PayCardActivity extends BaseActivity {
    @BindView(R.id.iv_pay_card_back)
    ImageView ivPayCardBack;
    @BindView(R.id.rv_pay_card)
    RecyclerView rvCard;
    @BindView(R.id.layout_refresh_pay_card)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_pay_card_title)
    TextView tvPayCardTitle;

    private int page = 1;
    //刷新
    private boolean isRefresh;
    //加载更多
    private boolean isLoadMore;
    private List<MemberBalanceBean.DataBean> dataList = new ArrayList<>();
    //总页数
    private int pages;
    //活动总数量
    private CommonAdapter<MemberBalanceBean.DataBean> adapter;
    private int[] imgCard = new int[]{R.mipmap.img_balance_pink, R.mipmap.img_balance_purple,
            R.mipmap.img_balance_green, R.mipmap.img_balance_orange, R.mipmap.img_balance_blue};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_card);
        ButterKnife.bind(this);
        tvPayCardTitle.setText("预付卡");

        initData();
    }


    private void initData() {
        OkHttpUtils.post()
                .url(Constant.MEMBER_BALANCE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("pageNum", String.valueOf(page))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        MemberBalanceBean balanceBean = GsonUtils.jsonToBean(s, MemberBalanceBean.class);
                        if (balanceBean.getPage() != null) {
                            pages = balanceBean.getPage().getPages();
                        }

                        if (balanceBean.isFlag() && balanceBean.getData().size() > 0) {
                            if (isRefresh) {
                                dataList.clear();
                            }
                            dataList.addAll(balanceBean.getData());

                            if (isRefresh) {
                                refreshLayout.finishRefresh();
                                isRefresh = false;
                                adapter.notifyDataSetChanged();
                            } else if (isLoadMore) {
                                refreshLayout.finishLoadMore();
                                isLoadMore = false;
                                adapter.notifyDataSetChanged();
                            } else {
                                initView();
                            }
                        } else {
                            refreshLayout.finishLoadMore();
                        }
                    }
                });

    }

    /**
     * 加载视图
     */
    private void initView() {
        rvCard.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<MemberBalanceBean.DataBean>(
                this, R.layout.rv_member_balance_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, MemberBalanceBean.DataBean dataBean, int position) {
                SimpleDraweeView ivBg = holder.getView(R.id.iv_pay_card_bg);
                ivBg.setImageResource(imgCard[position % 5]);
                holder.setText(R.id.tv_pay_card_number, dataBean.getAccountId());
                holder.setText(R.id.tv_pay_card_name, dataBean.getAccountName());
                holder.setText(R.id.tv_pay_card_money, dataBean.getBalance() + "元");
            }
        };
        rvCard.setAdapter(adapter);

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (page < pages) {
                    isLoadMore = true;
                    page++;
                    initData();
                } else {
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                page = 1;
                initData();
            }
        });

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(PayCardActivity.this, BalanceRecordActivity.class);
                intent.putExtra("account_id", dataList.get(position).getAccountId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    /**
     * 删除预付卡
     */
    private void deleteCard(final int position, final int cardId) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        dialog.setMessage("客官，您确定要删除吗？");
        //为“确定”按钮注册监听事件
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                submit(position, cardId);
            }
        });

        //为“取消”按钮注册监听事件
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();

    }

    /**
     * @param position
     */
    private void submit(final int position, int cardId) {
        OkHttpUtils.post()
                .url(Constant.DELETE_PAY_CARD)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("id", String.valueOf(cardId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.log(response);
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                        if (responseBean.isFlag()) {
                            dataList.remove(position);
                            adapter.notifyItemRemoved(position);
                            if (position != dataList.size()) {
                                adapter.notifyItemRangeChanged(position, dataList.size() - position);
                            }
                            EventBus.getDefault().post("delete_success");

                            ToastUtils.showToast(PayCardActivity.this, "删除成功");
                        } else {
                            ToastUtils.showToast(PayCardActivity.this, "删除失败");
                        }
                    }
                });

    }

    @OnClick(R.id.iv_pay_card_back)
    public void onViewClicked() {
        finish();
    }
}
