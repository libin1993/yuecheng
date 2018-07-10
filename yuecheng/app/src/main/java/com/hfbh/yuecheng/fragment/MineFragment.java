package com.hfbh.yuecheng.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.bean.UserInfoBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.CouponActivity;
import com.hfbh.yuecheng.ui.LoginActivity;
import com.hfbh.yuecheng.ui.MainActivity;
import com.hfbh.yuecheng.ui.MemberBalanceActivity;
import com.hfbh.yuecheng.ui.MemberCardActivity;
import com.hfbh.yuecheng.ui.MemberPointsActivity;
import com.hfbh.yuecheng.ui.MsgCenterActivity;
import com.hfbh.yuecheng.ui.MyActionActivity;
import com.hfbh.yuecheng.ui.MyExchangeActivity;
import com.hfbh.yuecheng.ui.MyMemberCardActivity;
import com.hfbh.yuecheng.ui.SetUpActivity;
import com.hfbh.yuecheng.ui.UserInfoActivity;
import com.hfbh.yuecheng.ui.ValidateActivity;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.ShareUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.smarttop.library.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/14 16:10
 * Email：1993911441@qq.com
 * Describe：我的
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_mine_set)
    ImageView ivMineSet;
    @BindView(R.id.iv_mine_msg)
    ImageView ivMineMsg;
    @BindView(R.id.tv_mine_username)
    TextView tvMineUsername;
    @BindView(R.id.iv_mine_avatar)
    SimpleDraweeView ivMineAvatar;
    @BindView(R.id.tv_mine_score)
    TextView tvMineScore;
    @BindView(R.id.tv_mine_grade)
    TextView tvMineGrade;
    @BindView(R.id.rl_mine_paycode)
    RelativeLayout rlMinePaycode;
    @BindView(R.id.rl_mine_ticket)
    RelativeLayout rlMineTicket;
    @BindView(R.id.rl_mine_order)
    RelativeLayout rlMineOrder;
    @BindView(R.id.rl_mine_exchange)
    RelativeLayout rlMineExchange;
    @BindView(R.id.rl_mine_activity)
    RelativeLayout rlMineActivity;
    @BindView(R.id.rl_mine_tool)
    RelativeLayout rlMineTool;
    @BindView(R.id.tv_mine_money)
    TextView tvMineMoney;
    @BindView(R.id.ll_mine_money)
    LinearLayout llMineMoney;
    @BindView(R.id.ll_mine_score)
    LinearLayout llMineScore;
    @BindView(R.id.ll_mine_grade)
    LinearLayout llMineGrade;
    @BindView(R.id.rl_mine_info)
    RelativeLayout rlMineInfo;
    private Unbinder unbinder;
    private UserInfoBean userInfoBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }
    }


    /**
     * 会员数据
     */
    private void initData() {
        if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
            OkHttpUtils.post()
                    .url(Constant.USER_INFO)
                    .addParams("appType", MyApp.appType)
                    .addParams("appVersion", MyApp.appVersion)
                    .addParams("organizeId", MyApp.organizeId)
                    .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                    .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            userInfoBean = GsonUtils.jsonToBean(response, UserInfoBean.class);
                            if (userInfoBean.isFlag()) {
                                initView();
                            } else if (userInfoBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(getActivity(), "is_login");
                            }
                        }
                    });
        } else {
            tvMineUsername.setText("注册/登录");
            ivMineAvatar.setImageResource(R.mipmap.img_default_avatar);
            tvMineMoney.setText("0");
            tvMineScore.setText("0");
            tvMineGrade.setText("领取会员卡");
        }

    }

    /**
     * 用户信息
     */
    private void initView() {
        tvMineUsername.setText(userInfoBean.getData().getMemberNickname());
        if (!TextUtils.isEmpty(userInfoBean.getData().getMemberHead())) {
            ivMineAvatar.setImageURI(userInfoBean.getData().getMemberHead());
        }
        tvMineMoney.setText(DisplayUtils.isInteger(userInfoBean.getData().getAccountBalance()));
        tvMineScore.setText(String.valueOf((int) userInfoBean.getData().getPoints()));
        tvMineGrade.setText(userInfoBean.getData().getCardLevel());
        SharedPreUtils.saveStr(getActivity(), "member_card",
                userInfoBean.getData().getCardLevel());
    }

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @OnClick({R.id.iv_mine_set, R.id.iv_mine_msg, R.id.rl_mine_info, R.id.rl_mine_paycode,
            R.id.rl_mine_ticket, R.id.rl_mine_order, R.id.rl_mine_exchange, R.id.rl_mine_activity,
            R.id.rl_mine_tool, R.id.ll_mine_money, R.id.ll_mine_score, R.id.ll_mine_grade})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_set:
                startActivity(new Intent(getActivity(), SetUpActivity.class));
                break;
            case R.id.iv_mine_msg:
                startActivity(new Intent(getActivity(), MsgCenterActivity.class));
                break;
            case R.id.rl_mine_info:
                toLogin(UserInfoActivity.class);
                break;
            case R.id.rl_mine_paycode:
                toLogin(MemberCardActivity.class);
                break;
            case R.id.rl_mine_ticket:
                toLogin(CouponActivity.class);
                break;
            case R.id.rl_mine_order:
                break;
            case R.id.rl_mine_exchange:
                toLogin(MyExchangeActivity.class);
                break;
            case R.id.rl_mine_activity:
                isSetPayPwd();
                break;
            case R.id.rl_mine_tool:
                break;
            case R.id.ll_mine_money:
                toMemberBalance();
                break;
            case R.id.ll_mine_score:
                toMemberPoints();
                break;
            case R.id.ll_mine_grade:
                toLogin(MyMemberCardActivity.class);
                break;
        }
    }

    /**
     * 是否设置支付密码
     */
    private void isSetPayPwd() {
        if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
            OkHttpUtils.post()
                    .url(Constant.IS_SET_PAY_PWD)
                    .addParams("appType", MyApp.appType)
                    .addParams("appVersion", MyApp.appVersion)
                    .addParams("organizeId", MyApp.organizeId)
                    .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                    .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);

                            if (responseBean.isFlag()) {
                                startActivity(new Intent(getActivity(), MemberCardActivity.class));
                            } else {
                                if (responseBean.getCode() == 4002) {
                                    SharedPreUtils.deleteStr(getActivity(), "is_login");
                                } else {
                                    setPayPwd();
                                }
                            }
                        }
                    });
        } else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }

    }

    /**
     * 设置支付密码
     */
    private void setPayPwd() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(),
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("提示");
        dialog.setMessage("您尚未设置支付密码，是否前去设置？");
        //为“确定”按钮注册监听事件
        dialog.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), ValidateActivity.class);
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
        Intent intent;
        if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
            if (userInfoBean != null) {
                startActivity(new Intent(getActivity(), MemberBalanceActivity.class));
            }
        } else {
            intent = new Intent(getActivity(), LoginActivity.class);
            intent.putExtra("type", 1);
            startActivity(intent);
        }
    }

    /**
     * 会员积分
     */
    private void toMemberPoints() {
        Intent intent;
        if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
            if (userInfoBean != null) {
                intent = new Intent(getActivity(), MemberPointsActivity.class);
                intent.putExtra("points", userInfoBean.getData().getPoints());
                startActivity(intent);
            }
        } else {
            intent = new Intent(getActivity(), LoginActivity.class);
            intent.putExtra("type", 1);
            startActivity(intent);
        }
    }

    /**
     * @param cls 是否登录
     */
    private void toLogin(Class<?> cls) {
        Intent intent;
        if (SharedPreUtils.getBoolean(getActivity(), "is_login", false)) {
            intent = new Intent(getActivity(), cls);
        } else {
            intent = new Intent(getActivity(), LoginActivity.class);
            intent.putExtra("type", 1);
        }
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
