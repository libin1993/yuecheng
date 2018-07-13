package com.hfbh.yuecheng.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.bean.UserInfoBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.smarttop.library.utils.LogUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
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
 * Author：Libin on 2018/5/29 12:41
 * Email：1993911441@qq.com
 * Describe：会员卡
 */
public class MyMemberCardActivity extends BaseActivity {

    @BindView(R.id.tv_title_header_white)
    TextView tvTitleHeaderWhite;
    @BindView(R.id.iv_back_header_white)
    ImageView ivBackHeaderWhite;
    @BindView(R.id.iv_member_card)
    SimpleDraweeView ivMemberCard;
    @BindView(R.id.tv_member_card_money)
    TextView tvMemberCardMoney;
    @BindView(R.id.ll_member_card_money)
    LinearLayout llMemberCardMoney;
    @BindView(R.id.tv_member_card_score)
    TextView tvMemberCardScore;
    @BindView(R.id.ll_member_card_score)
    LinearLayout llMemberCardScore;
    @BindView(R.id.tv_member_card_grade)
    TextView tvMemberCardGrade;
    @BindView(R.id.ll_member_card_grade)
    LinearLayout llMemberCardGrade;
    @BindView(R.id.ll_member_card_rights)
    LinearLayout llMemberCardRights;
    @BindView(R.id.rv_member_rights)
    RecyclerView rvMemberRights;
    @BindView(R.id.rl_member_recode)
    RelativeLayout rlMemberRecode;
    @BindView(R.id.iv_member_card_code)
    ImageView ivMemberCardCode;
    @BindView(R.id.tv_member_card_no)
    TextView tvMemberCardNo;
    private UserInfoBean userInfoBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_member_card);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
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
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.log(response);
                        userInfoBean = GsonUtils.jsonToBean(response, UserInfoBean.class);
                        if (userInfoBean.isFlag()) {
                            initView();
                        } else if (userInfoBean.getCode() == 4002) {
                            SharedPreUtils.deleteStr(MyMemberCardActivity.this, "is_login");
                        }
                    }
                });

    }


    private void initView() {
        tvTitleHeaderWhite.setText("电子会员卡");

        if (userInfoBean.getData().getMemberCardGradeDTO() != null) {
            ivMemberCard.setImageURI(userInfoBean.getData().getMemberCardGradeDTO().getAppPic());
        }
        tvMemberCardMoney.setText(DisplayUtils.isInteger(userInfoBean.getData().getAccountBalance()));
        tvMemberCardScore.setText(String.valueOf((int) userInfoBean.getData().getPoints()));
        tvMemberCardGrade.setText(String.valueOf(userInfoBean.getData().getCardLevel()));
        tvMemberCardNo.setText("NO." + userInfoBean.getData().getCardNumber());
        if (userInfoBean.getData().getMemberCardGradeDTO() != null) {
            rvMemberRights.setLayoutManager(new GridLayoutManager(this, 3));
            CommonAdapter<UserInfoBean.DataBean.MemberCardGradeDTOBean.ListPrivilegeBean> adapter = new
                    CommonAdapter<UserInfoBean.DataBean.MemberCardGradeDTOBean.ListPrivilegeBean>(
                            MyMemberCardActivity.this, R.layout.rv_member_rights_item,
                            userInfoBean.getData().getMemberCardGradeDTO().getListPrivilege()) {
                        @Override
                        protected void convert(ViewHolder holder, UserInfoBean.DataBean.MemberCardGradeDTOBean
                                .ListPrivilegeBean listPrivilegeBean, int position) {

                            Glide.with(MyMemberCardActivity.this)
                                    .load(listPrivilegeBean.getAppPic())
                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                    .into((ImageView) holder.getView(R.id.iv_member_rights));

                            holder.setText(R.id.tv_member_rights, listPrivilegeBean.getPrivilegeName());
                        }
                    };
            rvMemberRights.setAdapter(adapter);
        }

    }

    @OnClick({R.id.iv_back_header_white, R.id.ll_member_card_money, R.id.ll_member_card_score,
            R.id.ll_member_card_grade, R.id.ll_member_card_rights, R.id.rl_member_recode, R.id.iv_member_card_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_header_white:
                finish();
                break;
            case R.id.ll_member_card_money:
                toMemberBalance();
                break;
            case R.id.ll_member_card_score:
                toMemberPoints();
                break;
            case R.id.ll_member_card_grade:
                break;
            case R.id.ll_member_card_rights:
                startActivity(new Intent(this, MemberRightsActivity.class));
                break;
            case R.id.rl_member_recode:
                startActivity(new Intent(this, ConsumeRecordActivity.class));
                break;
            case R.id.iv_member_card_code:
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
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);

                        if (responseBean.isFlag()) {
                            startActivity(new Intent(MyMemberCardActivity.this, MemberCardActivity.class));
                        } else {
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(MyMemberCardActivity.this, "is_login");
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
                Intent intent = new Intent(MyMemberCardActivity.this, ValidateActivity.class);
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


    /**
     * 会员余额
     */
    private void toMemberBalance() {
        if (userInfoBean != null) {
            startActivity(new Intent(this, MemberBalanceActivity.class));
        }
    }

    /**
     * 会员积分
     */
    private void toMemberPoints() {
        if (userInfoBean != null) {
            Intent intent = new Intent(this, MemberPointsActivity.class);
            intent.putExtra("points", userInfoBean.getData().getPoints());
            startActivity(intent);
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
