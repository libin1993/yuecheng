package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.UserInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private UserInfoBean userInfoBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_member_card);
        ButterKnife.bind(this);
        getData();
        initView();
    }

    private void getData() {
        userInfoBean = (UserInfoBean) getIntent().getSerializableExtra("user_info");
    }

    private void initView() {
        if (userInfoBean != null){
            ivMemberCard.setImageURI(userInfoBean.getData().getCardLevelPic());
            tvMemberCardMoney.setText(String.valueOf(userInfoBean.getData().getAccountBalance()));
            tvMemberCardScore.setText(String.valueOf(userInfoBean.getData().getPoints()));
            tvMemberCardGrade.setText(String.valueOf(userInfoBean.getData().getCardLevel()));
        }

    }

    @OnClick({R.id.iv_back_header_white, R.id.ll_member_card_money, R.id.ll_member_card_score,
            R.id.ll_member_card_grade, R.id.ll_member_card_rights, R.id.rl_member_recode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_header_white:
                finish();
                break;
            case R.id.ll_member_card_money:
                break;
            case R.id.ll_member_card_score:
                break;
            case R.id.ll_member_card_grade:
                break;
            case R.id.ll_member_card_rights:
                break;
            case R.id.rl_member_recode:
                break;
        }
    }
}
