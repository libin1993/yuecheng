package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityBean;
import com.hfbh.yuecheng.bean.ActivityDetailBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.view.FlowLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/7 09:05
 * Email：1993911441@qq.com
 * Describe：活动报名
 */
public class EnrollActionActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_action_name)
    TextView tvActionName;
    @BindView(R.id.flow_layout_activity)
    FlowLayout flowLayout;
    @BindView(R.id.tv_action_time)
    TextView tvActionTime;
    @BindView(R.id.tv_action_address)
    TextView tvActionAddress;
    @BindView(R.id.et_action_username)
    EditText etUsername;
    @BindView(R.id.et_action_phone)
    EditText etPhone;
    @BindView(R.id.tv_enroll_score)
    TextView tvEnrollScore;
    @BindView(R.id.tv_enroll_type)
    TextView tvEnrollType;
    @BindView(R.id.tv_enroll_activity)
    TextView tvEnrollActivity;
    //活动id
    private int activityId;
    private ActivityDetailBean activityBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_action);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("活动报名");
        getData();
        initData();
    }

    private void initData() {
        OkHttpUtils.get()
                .url(Constant.ACTIVITY_INFO)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("id", String.valueOf(activityId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        activityBean = GsonUtils.jsonToBean(response, ActivityDetailBean.class);
                        if (activityBean.isFlag()) {
                            initView();
                        }
                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {
        tvActionName.setText(activityBean.getData().getSignupDo().getActivityTitle());
        if (activityBean.getData().getSignupDo().getTags() != null &&
                activityBean.getData().getSignupDo().getTags().size() > 0) {
            addTextView(activityBean.getData().getSignupDo().getTags());
        }

        tvActionTime.setText(activityBean.getData().getSignupDo().getStartTimeStr() + " - "
                + activityBean.getData().getSignupDo().getEndTimeStr());
        tvActionAddress.setText(activityBean.getData().getSignupDo().getAcivityAddress());

        String type = activityBean.getData().getSignupDo().getAcivityType();
        if (!TextUtils.isEmpty(type)) {
            switch (type) {
                case "NONEED":
                    tvEnrollScore.setText("无需报名");
                    break;
                case "FREE":
                    tvEnrollScore.setText("免费");
                    break;
                case "SCORE":
                    tvEnrollScore.setText(DisplayUtils.isInteger(activityBean.getData().getSignupDo()
                            .getEnrollScore()));
                    tvEnrollType.setVisibility(View.VISIBLE);
                    break;
                case "CASH":
                    tvEnrollScore.setText("¥" + DisplayUtils.isInteger(activityBean.getData()
                            .getSignupDo().getEnrollFee()));
                    break;
            }
        }

    }

    /**
     * 动态添加标签
     */
    private void addTextView(List<ActivityDetailBean.DataBean.SignupDoBean.TagsBean> tagsBeans) {

        for (int i = 0; i < tagsBeans.size(); i++) {
            TextView tvChild = new TextView(this);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, (int) DisplayUtils.dp2px(this, 6),
                    (int) DisplayUtils.dp2px(this, 2));
            tvChild.setLayoutParams(params);
            tvChild.setBackgroundResource(R.drawable.flowlayout_item);
            tvChild.setText(tagsBeans.get(i).getTagName());
            tvChild.setTextSize(12);
            tvChild.setTextColor(getResources().getColor(R.color.red_e6));

            flowLayout.addView(tvChild);
        }

    }

    private void getData() {
        activityId = getIntent().getIntExtra("activity_id", 0);
    }

    @OnClick({R.id.iv_header_back, R.id.tv_enroll_activity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_enroll_activity:
                break;
        }
    }
}
