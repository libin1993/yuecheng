package com.hfbh.yuecheng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ActivityListBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DateUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.view.FlowLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/6/6 14:43
 * Email：1993911441@qq.com
 * Describe：
 */
public class CalendarActivity extends BaseActivity {
    @BindView(R.id.tv_title_header)
    TextView tvTitleHeader;
    @BindView(R.id.iv_back_header)
    ImageView ivBackHeader;
    @BindView(R.id.tv_calendar_day)
    TextView tvCalendarDay;
    @BindView(R.id.calendarview_activity)
    CalendarView calendarView;
    @BindView(R.id.rv_calendar_activity)
    RecyclerView rvCalendar;
    private int page = 1;
    //是否选择日期
    private boolean isSelect;
    //加载更多
    private boolean isLoadMore;
    private List<ActivityListBean.DataBean> dataList = new ArrayList<>();
    //总页数
    private int pages;
    //选择日期
    private String selectDate;

    private LoadMoreWrapper loadMoreWrapper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
        tvTitleHeader.setText("活动日历");
        initView();
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

        if (!TextUtils.isEmpty(selectDate)) {
            map.put("activityTime", selectDate);
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
                            if (isSelect) {
                                dataList.clear();
                            }
                            dataList.addAll(activityListBean.getData());

                            if (isSelect) {
                                isSelect = false;
                                loadMoreWrapper.notifyDataSetChanged();
                            } else if (isLoadMore) {
                                isLoadMore = false;
                                loadMoreWrapper.notifyDataSetChanged();
                            }
                            rvCalendar.setVisibility(View.VISIBLE);
                        } else {
                            if (page == 1) {
                                rvCalendar.setVisibility(View.GONE);
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
            protected void convert(ViewHolder holder, final ActivityListBean.DataBean dataBean, int position) {
                SimpleDraweeView ivCoupon = holder.getView(R.id.iv_home_activity);
                ivCoupon.setImageURI(dataBean.getActivityPicture());
                holder.setText(R.id.tv_home_activity_name, dataBean.getActivityTitle());
                holder.setText(R.id.tv_home_activity_time, dataBean
                        .getStartTimeStr() + " - " + dataBean.getEndTimeStr());

                TextView tvReceive = holder.getView(R.id.tv_home_activity_receive);
                final boolean isFinish = System.currentTimeMillis() > DateUtils.getTime(
                        "yyyy-MM-dd HH:mm:ss", dataBean.getEndTime());
                final boolean isStart = System.currentTimeMillis() >= DateUtils.getTime(
                        "yyyy-MM-dd HH:mm:ss", dataBean.getStartTime());

                final boolean isLimit = dataBean.getSignupLimitNumber() > 0 && dataBean.getSignupNumber()
                        == dataBean.getSignupLimitNumber();

                final boolean isEnroll = dataBean.getMemberSignupState() != null && dataBean.getMemberSignupState().equals("去参加");
                if (!isFinish) {
                    if (isStart) {
                        if (!TextUtils.isEmpty(dataBean.getAcivityType())) {
                            if (isEnroll) {
                                tvReceive.setVisibility(View.VISIBLE);
                                tvReceive.setText("去参加");
                                tvReceive.setBackgroundResource(R.drawable.bound_red_15dp);
                            } else {
                                if (isLimit) {
                                    tvReceive.setVisibility(View.VISIBLE);
                                    tvReceive.setBackgroundResource(R.drawable.bound_gray_15dp);
                                    tvReceive.setText("名额已满");
                                } else {
                                    tvReceive.setBackgroundResource(R.drawable.bound_red_15dp);
                                    switch (dataBean.getAcivityType()) {
                                        case "NONEED":
                                            tvReceive.setVisibility(View.GONE);
                                            break;
                                        case "FREE":
                                            tvReceive.setVisibility(View.VISIBLE);
                                            tvReceive.setText("免费报名");
                                            break;
                                        case "SCORE":
                                            tvReceive.setVisibility(View.VISIBLE);
                                            tvReceive.setText(DisplayUtils.isInteger(dataBean.getEnrollScore()) + "积分报名");
                                            break;
                                        case "CASH":
                                            tvReceive.setVisibility(View.VISIBLE);
                                            tvReceive.setText("¥" + DisplayUtils.isInteger(dataBean.getEnrollFee()) + "报名");
                                            break;
                                    }
                                }
                            }

                        }
                    } else {
                        tvReceive.setVisibility(View.VISIBLE);
                        tvReceive.setText("待报名");
                        tvReceive.setBackgroundResource(R.drawable.bound_gray_15dp);
                    }

                } else {
                    tvReceive.setVisibility(View.VISIBLE);
                    tvReceive.setText("已结束");
                    tvReceive.setBackgroundResource(R.drawable.bound_gray_15dp);

                }

                FlowLayout flowLayout = holder.getView(R.id.flow_home_activity);
                flowLayout.removeAllViews();
                if (dataBean.getTags() != null && dataBean.getTags().size() > 0) {
                    addTextView(flowLayout, dataBean.getTags());
                }

                tvReceive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isFinish && isStart) {
                            Intent intent;
                            if (SharedPreUtils.getBoolean(CalendarActivity.this, "is_login", false)) {
                                if (isEnroll) {
                                    intent = new Intent(CalendarActivity.this, CloseActionActivity.class);
                                    intent.putExtra("activity_id", dataBean.getMarketingActivitySignupId());
                                    startActivity(intent);
                                } else if (!isLimit) {
                                    intent = new Intent(CalendarActivity.this, EnrollActionActivity.class);
                                    intent.putExtra("activity_id", dataBean.getMarketingActivitySignupId());
                                    startActivity(intent);
                                }
                            } else {
                                intent = new Intent(CalendarActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }


                        }
                    }
                });

            }
        };

        loadMoreWrapper = new LoadMoreWrapper(adapter);
        loadMoreWrapper.setLoadMoreView(LayoutInflater.from(this).inflate(R.layout.layout_loading,
                rvCalendar, false));
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
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Calendar calendar, boolean isClick) {

                tvCalendarDay.setText(calendar.getYear() + "年" + calendar.getMonth() + "月");
                String month;
                String day;

                if (calendar.getMonth() >= 10) {
                    month = String.valueOf(calendar.getMonth());
                } else {
                    month = "0" + calendar.getMonth();
                }

                if (calendar.getDay() >= 10) {
                    day = String.valueOf(calendar.getDay());
                } else {
                    day = "0" + calendar.getDay();
                }


                selectDate = calendar.getYear() + "-" + month + "-" + day;
                isSelect = true;
                page = 1;
                initData();
            }
        });

        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                tvCalendarDay.setText(year + "年" + month + "月");
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
