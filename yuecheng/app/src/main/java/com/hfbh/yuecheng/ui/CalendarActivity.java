package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityListBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.view.FlowLayout;
import com.necer.ncalendar.calendar.NCalendar;
import com.necer.ncalendar.listener.OnCalendarChangedListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/24 12:44
 * Email：1993911441@qq.com
 * Describe：活动日历
 */
public class CalendarActivity extends BaseActivity {
    @BindView(R.id.rv_calendar)
    RecyclerView rvCalendar;
    @BindView(R.id.calendar_view)
    NCalendar calendarView;
    @BindView(R.id.tv_title_header)
    TextView tvTitleHeader;
    @BindView(R.id.iv_back_header)
    ImageView ivBackHeader;
    @BindView(R.id.tv_calendar_date)
    TextView tvCalendarDate;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView loadingView;
    @BindView(R.id.sv_no_calendar)
    NestedScrollView svNoCalendar;

    private int page = 1;
    //选择日期
    private boolean selectDate;
    //加载更多
    private boolean isLoadMore;
    private List<ActivityListBean.DataBean> dataList = new ArrayList<>();
    //总页数
    private int pages;

    private String date;

    private LoadMoreWrapper loadMoreWrapper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);

        init();
        initData();
    }

    private void init() {
        tvTitleHeader.setText("活动日历");
        loadingView.smoothToShow();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月");
        Date date = new Date(System.currentTimeMillis());
        tvCalendarDate.setText(simpleDateFormat.format(date));

    }

    /**
     * 加载数据
     */
    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("appType", MyApp.appType);
        map.put("appVersion", MyApp.appVersion);
        map.put("organizeId", MyApp.organizeId);
        map.put("hash", SharedPreUtils.getStr(this, "hash"));
        map.put("pageNum", String.valueOf(page));

        if (!TextUtils.isEmpty(date)) {
            map.put("activityTime", date);
        }

        OkHttpUtils.get()
                .url(Constant.ACTIVITY_LIST)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ActivityListBean activityListBean = GsonUtils.jsonToBean(response, ActivityListBean.class);
                        if (activityListBean.getPage() != null) {
                            pages = activityListBean.getPage().getPages();
                        }

                        if (activityListBean.isFlag() && activityListBean.getData().size() > 0) {
                            if (selectDate) {
                                dataList.clear();
                            }
                            dataList.addAll(activityListBean.getData());

                            if (selectDate) {
                                selectDate = false;
                                loadMoreWrapper.notifyDataSetChanged();
                            } else if (isLoadMore) {
                                isLoadMore = false;
                                loadMoreWrapper.notifyDataSetChanged();
                            } else {
                                loadingView.smoothToHide();
                                initView();
                            }
                            svNoCalendar.setVisibility(View.GONE);
                            rvCalendar.setVisibility(View.VISIBLE);
                        } else {
                            if (page == 1) {
                                rvCalendar.setVisibility(View.GONE);
                                svNoCalendar.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }

    /**
     * 加载视图
     */
    private void initView() {
        rvCalendar.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<ActivityListBean.DataBean> adapter = new CommonAdapter<ActivityListBean.DataBean>
                (this, R.layout.rv_activity_item, dataList) {
            @Override
            protected void convert(ViewHolder holder, ActivityListBean.DataBean dataBean, int position) {
                SimpleDraweeView ivCoupon = holder.getView(R.id.iv_home_activity);
                ivCoupon.setImageURI(dataBean.getActivityPicture());
                holder.setText(R.id.tv_home_activity_name, dataBean.getActivityTitle());
                holder.setText(R.id.tv_home_activity_time, dataBean
                        .getStartTimeStr() + " - " + dataBean.getEndTimeStr());

                TextView tvReceive = holder.getView(R.id.tv_home_activity_receive);
                if (!TextUtils.isEmpty(dataBean.getAcivityType())) {
                    switch (dataBean.getAcivityType()) {
                        case "NONEED":
                            tvReceive.setText("无需报名");
                            break;
                        case "FREE":
                            tvReceive.setText("免费报名");
                            break;
                        case "SCORE":
                            tvReceive.setText(DisplayUtils.isInteger(dataBean.getEnrollScore()) + "积分报名");
                            break;
                        case "CASH":
                            tvReceive.setText("¥" + DisplayUtils.isInteger(dataBean.getEnrollFee()) + "报名");
                            break;
                    }
                }

                FlowLayout flowLayout = holder.getView(R.id.flow_home_activity);
                flowLayout.removeAllViews();
                if (dataBean.getTags() != null && dataBean.getTags().size() > 0) {
                    addTextView(flowLayout, dataBean.getTags());
                }

            }
        };

        loadMoreWrapper = new LoadMoreWrapper(adapter);
        loadMoreWrapper.setLoadMoreView(LayoutInflater.from(this).inflate(R.layout.layout_loading, rvCalendar, false));
        rvCalendar.setAdapter(loadMoreWrapper);
        loadMoreWrapper.notifyDataSetChanged();

        loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < pages) {
                    isLoadMore = true;
                    page++;
                    initData();
                }
            }
        });

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(CalendarActivity.this, ActionDetailActivity.class);
                intent.putExtra("activity_id", dataList.get(position).getMarketingActivitySignupId());
                intent.putExtra("type", dataList.get(position).getAcivityType());
                intent.putExtra("money", dataList.get(position).getEnrollFee());
                intent.putExtra("score", dataList.get(position).getEnrollScore());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        calendarView.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChanged(LocalDate localDate) {
                tvCalendarDate.setText(localDate.getYear() + "年" + localDate.getMonthOfYear() + "月");
                String month;
                String day;

                if (localDate.getMonthOfYear() >= 10) {
                    month = String.valueOf(localDate.getMonthOfYear());
                } else {
                    month = "0" + localDate.getMonthOfYear();
                }

                if (localDate.getDayOfMonth() >= 10) {
                    day = String.valueOf(localDate.getDayOfMonth());
                } else {
                    day = "0" + localDate.getDayOfMonth();
                }


                date = localDate.getYear() + "-" + month + "-" + day;
                selectDate = true;
                page = 1;
                initData();
            }
        });

    }

    /**
     * //     * 动态添加布局
     * //
     */
    private void addTextView(FlowLayout flowLayout, List<ActivityListBean.DataBean.TagsBean> tagsBeans) {

        for (int i = 0; i < tagsBeans.size(); i++) {
            TextView tvChild = new TextView(this);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, (int) DisplayUtils.dp2px(this, 6), (int) DisplayUtils.dp2px(this, 2));
            tvChild.setLayoutParams(params);
            tvChild.setBackgroundResource(R.drawable.flowlayout_item);
            tvChild.setText(tagsBeans.get(i).getTagName());
            tvChild.setTextSize(12);
            tvChild.setTextColor(getResources().getColor(R.color.red_e6));

            flowLayout.addView(tvChild);
        }

    }

    @OnClick(R.id.iv_back_header)
    public void onViewClicked() {
        finish();
    }
}
