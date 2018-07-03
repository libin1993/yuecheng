package com.hfbh.yuecheng.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.hfbh.yuecheng.bean.MemberBalanceBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.bean.UserInfoBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
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
        tvTitleHeader.setText("会员卡余额");
        initBalance();
        initData();
    }

    private void initBalance() {
        OkHttpUtils.post()
                .url(Constant.USER_INFO)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token",SharedPreUtils.getStr(this, "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        UserInfoBean userInfoBean = GsonUtils.jsonToBean(response, UserInfoBean.class);
                        if (userInfoBean.isFlag()) {
                            tvMemberBalance.setText(DisplayUtils.isInteger(userInfoBean.getData().getAccountBalance()));
                        }else if (userInfoBean.getCode() == 4002) {
                            SharedPreUtils.deleteStr(MemberBalanceActivity.this, "is_login");
                        }
                    }
                });
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.MEMBER_BALANCE)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token",SharedPreUtils.getStr(this, "token"))
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
                            if (balanceBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(MemberBalanceActivity.this, "is_login");
                            }
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
                holder.setText(R.id.tv_pay_card_number, dataBean.getPrePaidCardAccount());
                holder.setText(R.id.tv_pay_card_name, dataBean.getAccountName());
                holder.setText(R.id.tv_pay_card_money, DisplayUtils.isInteger(dataBean.getBalance()) + "元");
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
                isSetPayPwd();
                break;
        }
    }

    /**
     * 是否设置支付密码
     */
    private void isSetPayPwd() {
        OkHttpUtils.post()
                .url(Constant.IS_SET_PAY_PWD)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token",SharedPreUtils.getStr(this, "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);

                        if (responseBean.isFlag()) {
                            startActivity(new Intent(MemberBalanceActivity.this, BindCardActivity.class));
                        } else {
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(MemberBalanceActivity.this, "is_login");
                            } else {
                                setPayPwd();
                            }
                        }
                    }
                });
    }

    /**
     * 设置支付密码
     */
    private void setPayPwd() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("提示");
        dialog.setMessage("您尚未设置支付密码，是否前去设置？");
        //为“确定”按钮注册监听事件
        dialog.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MemberBalanceActivity.this, ValidateActivity.class);
                intent.putExtra("type", "bind");
                startActivity(intent);
            }
        });

        //为“取消”按钮注册监听事件
        dialog.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();
    }


    @Subscribe
    public void bindOrDelete(String msg) {
        if ("delete_success".equals(msg) || "bind_success".equals(msg)) {
            initBalance();
            initData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
