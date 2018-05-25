package com.hfbh.yuecheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.ui.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    @BindView(R.id.tv_mine_money)
    LinearLayout tvMineMoney;
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
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_mine_set, R.id.iv_mine_msg, R.id.tv_mine_username, R.id.tv_mine_money, R.id.tv_mine_score, R.id.tv_mine_grade, R.id.rl_mine_paycode, R.id.rl_mine_ticket, R.id.rl_mine_order, R.id.rl_mine_exchange, R.id.rl_mine_activity, R.id.rl_mine_tool})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_set:
                break;
            case R.id.iv_mine_msg:
                break;
            case R.id.tv_mine_username:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.tv_mine_money:
                break;
            case R.id.tv_mine_score:
                break;
            case R.id.tv_mine_grade:
                break;
            case R.id.rl_mine_paycode:
                break;
            case R.id.rl_mine_ticket:
                break;
            case R.id.rl_mine_order:
                break;
            case R.id.rl_mine_exchange:
                break;
            case R.id.rl_mine_activity:
                break;
            case R.id.rl_mine_tool:
                break;
        }
    }
}
