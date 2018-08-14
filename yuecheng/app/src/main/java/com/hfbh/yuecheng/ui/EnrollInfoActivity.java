package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityRecordBean;
import com.hfbh.yuecheng.bean.EnrollActivityBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.BigDecimalUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.view.FlowLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/8/14 10:33
 * Email：1993911441@qq.com
 * Describe：报名信息
 */
public class EnrollInfoActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.rv_enroll_activity)
    RecyclerView rvActivity;
    @BindView(R.id.rl_enroll_activity)
    RelativeLayout rlEnrollActivity;
    private int enrollId;

    private ActivityRecordBean activityBean;
    private List<ActivityRecordBean.DataBean.MemberValuesBean> dataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_action);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("报名信息");
        rlEnrollActivity.setVisibility(View.GONE);
        getData();
        initData();
    }


    /**
     * 活动数据
     */
    private void initData() {
        OkHttpUtils.get()
                .url(Constant.CASH_ENROLL_INFO)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("id", String.valueOf(enrollId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        activityBean = GsonUtils.jsonToBean(response, ActivityRecordBean.class);
                        if (activityBean.isFlag()) {
                            if (activityBean.getData().getMemberValues() != null
                                    && activityBean.getData().getMemberValues().size() > 0) {
                                dataList.addAll(activityBean.getData().getMemberValues());
                            }

                            initView();

                        } else if (activityBean.getCode() == 4002) {
                            SharedPreUtils.deleteStr(EnrollInfoActivity.this, "is_login");
                        }


                    }
                });
    }

    private void initView() {
        rvActivity.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<ActivityRecordBean.DataBean.MemberValuesBean> adapter = new
                CommonAdapter<ActivityRecordBean.DataBean.MemberValuesBean>(
                        EnrollInfoActivity.this, R.layout.rv_enroll_info_item, dataList) {
                    @Override
                    protected void convert(ViewHolder holder, ActivityRecordBean.DataBean.MemberValuesBean memberValuesBean, int position) {
                        holder.setText(R.id.tv_enroll_info_title, memberValuesBean.getOptionName());
                        TextView tvContent = holder.getView(R.id.tv_enroll_info_content);
                        RecyclerView rvContent = holder.getView(R.id.rv_enroll_info_content);
                        if ("UPLOAD_IMAGE".equals(memberValuesBean.getType())) {
                            rvContent.setVisibility(View.VISIBLE);
                            tvContent.setVisibility(View.GONE);
                            List<String> imgList = new ArrayList<>();
                            if (memberValuesBean.getValue().contains(",")) {
                                String[] split = memberValuesBean.getValue().split(",");
                                imgList.addAll(Arrays.asList(split));
                            } else {
                                imgList.add(memberValuesBean.getValue());
                            }

                            rvContent.setLayoutManager(new LinearLayoutManager(
                                    EnrollInfoActivity.this, LinearLayout.HORIZONTAL, false));
                            CommonAdapter<String> imgAdapter = new CommonAdapter<String>(
                                    EnrollInfoActivity.this, R.layout.rv_enroll_photo_item, imgList) {
                                @Override
                                protected void convert(ViewHolder holder, String s, int position) {
                                    ImageView img = holder.getView(R.id.iv_enroll_photo);
                                    Glide.with(EnrollInfoActivity.this)
                                            .load(s)
                                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                            .placeholder(R.mipmap.img_place)
                                            .dontAnimate()
                                            .into(img);
                                }
                            };

                            rvContent.setAdapter(imgAdapter);

                        } else {
                            rvContent.setVisibility(View.GONE);
                            tvContent.setVisibility(View.VISIBLE);
                            tvContent.setText(memberValuesBean.getValue());
                        }
                    }
                };


        HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        headerAndFooterWrapper.addHeaderView(initHeader());
        rvActivity.setAdapter(headerAndFooterWrapper);

    }

    private View initHeader() {

        View view = LayoutInflater.from(this).inflate(R.layout.rv_activity_header, null);
        TextView tvActionName = (TextView) view.findViewById(R.id.tv_action_name);
        TextView tvActionTime = (TextView) view.findViewById(R.id.tv_action_time);
        TextView tvActionAddress = (TextView) view.findViewById(R.id.tv_action_address);
        FlowLayout flowLayout = (FlowLayout) view.findViewById(R.id.flow_layout_activity);
        TextView tvName = (TextView) view.findViewById(R.id.tv_enroll_name);
        TextView tvPhoto = (TextView) view.findViewById(R.id.tv_enroll_photo);
        tvName.setVisibility(View.GONE);
        tvPhoto.setVisibility(View.GONE);
        EditText etUsername = (EditText) view.findViewById(R.id.et_action_username);
        EditText etPhone = (EditText) view.findViewById(R.id.et_action_phone);
        etUsername.setFocusable(false);
        etUsername.setFocusableInTouchMode(false);
        etPhone.setFocusable(false);
        etPhone.setFocusableInTouchMode(false);

        etUsername.setText(activityBean.getData().getRecord().getName());
        etPhone.setText(activityBean.getData().getRecord().getPhone());
        tvActionName.setText(activityBean.getData().getActivity().getActivityTitle());
        if (activityBean.getData().getActivity().getTags() != null &&
                activityBean.getData().getActivity().getTags().size() > 0) {
            addTextView(flowLayout, activityBean.getData().getActivity().getTags());
        }

        tvActionTime.setText(activityBean.getData().getActivity().getActivityStarttime() + " - "
                + activityBean.getData().getActivity().getActivityEndtime());
        tvActionAddress.setText(activityBean.getData().getActivity().getAcivityAddress());

        return view;
    }


    /**
     * 动态添加标签
     */
    private void addTextView(FlowLayout flowLayout, List<ActivityRecordBean.DataBean
            .ActivityBean.TagsBean> tagsBeans) {

        for (int i = 0; i < tagsBeans.size(); i++) {
            TextView tvChild = new TextView(this);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup
                    .MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
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
        enrollId = getIntent().getIntExtra("enroll_id", -1);
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
