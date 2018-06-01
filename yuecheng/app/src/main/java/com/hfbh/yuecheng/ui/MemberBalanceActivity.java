package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.BalanceRecordBean;
import com.hfbh.yuecheng.bean.MemberBalanceBean;
import com.hfbh.yuecheng.bean.MemberCodeBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/1 11:03
 * Email：1993911441@qq.com
 * Describe:会员卡余额
 */
public class MemberBalanceActivity extends BaseActivity {
    @BindView(R.id.tv_title_header)
    TextView tvTitleHeader;
    @BindView(R.id.iv_back_header)
    ImageView ivBackHeader;
    @BindView(R.id.tv_member_balance)
    TextView tvMemberBalance;
    @BindView(R.id.tv_balance_count)
    TextView tvBalanceCount;
    @BindView(R.id.ll_balance_count)
    LinearLayout llBalanceCount;
    @BindView(R.id.rv_member_balance)
    RecyclerView rvMemberBalance;
    @BindView(R.id.tv_bind_card)
    TextView tvBindCard;

    private MemberBalanceBean balanceBean;
    private int[] imgCard = new int[]{R.mipmap.img_balance_pink, R.mipmap.img_balance_purple,
            R.mipmap.img_balance_green, R.mipmap.img_balance_orange, R.mipmap.img_balance_blue};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_balance);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        getData();
        initData();

    }

    private void getData() {
        tvTitleHeader.setText("会员卡余额");
        tvMemberBalance.setText(String.valueOf(getIntent().getDoubleExtra("balance", 0)));
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.MEMBER_BALANCE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("pageSize", "2")
                .addParams("pageNum", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(String s, int i) {
                        balanceBean = GsonUtils.jsonToBean(s, MemberBalanceBean.class);
                        if (balanceBean.isFlag() && balanceBean.getPage() != null
                                && balanceBean.getPage().getTotal() > 0) {
                            tvBalanceCount.setText("预付卡  (" + balanceBean.getPage().getTotal() + ")");
                            rvMemberBalance.setVisibility(View.VISIBLE);
                            initView();
                        } else {
                            tvBalanceCount.setText("预付卡  (0)");
                            rvMemberBalance.setVisibility(View.GONE);
                        }
                    }
                });

    }

    /**
     * 加载视图
     */
    private void initView() {
        rvMemberBalance.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<MemberBalanceBean.DataBean> adapter = new CommonAdapter<MemberBalanceBean.DataBean>(
                this, R.layout.rv_member_balance_item, balanceBean.getData()) {
            @Override
            protected void convert(ViewHolder holder, MemberBalanceBean.DataBean dataBean, int position) {
                SimpleDraweeView ivBg = holder.getView(R.id.iv_pay_card_bg);
                ivBg.setImageResource(imgCard[position % 5]);
                holder.setText(R.id.tv_pay_card_number, dataBean.getAccountId());
                holder.setText(R.id.tv_pay_card_name, dataBean.getAccountName());
                holder.setText(R.id.tv_pay_card_money, dataBean.getBalance() + "元");
            }
        };
        rvMemberBalance.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(MemberBalanceActivity.this, BalanceRecordActivity.class);
                intent.putExtra("account_id", balanceBean.getData().get(position).getAccountId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }


    @OnClick({R.id.iv_back_header, R.id.tv_member_balance, R.id.ll_balance_count, R.id.tv_bind_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_header:
                finish();
                break;
            case R.id.tv_member_balance:
                break;
            case R.id.ll_balance_count:
                startActivity(new Intent(this, PayCardActivity.class));
                break;
            case R.id.tv_bind_card:
                startActivity(new Intent(this, BindCardActivity.class));
                break;
        }
    }

    @Subscribe
    public void bindOrDelete(String msg) {
        if ("delete_success".equals(msg) || "bind_success".equals(msg)) {
            initData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
