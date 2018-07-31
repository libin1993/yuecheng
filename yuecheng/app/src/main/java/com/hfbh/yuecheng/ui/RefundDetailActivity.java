package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
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
import com.hfbh.yuecheng.bean.RefundDetailBean;
import com.hfbh.yuecheng.bean.RefundOrderBean;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/7/27 17:00
 * Email：1993911441@qq.com
 * Describe：退款详情
 */
public class RefundDetailActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_refund_status)
    TextView tvRefundStatus;
    @BindView(R.id.tv_refund_num)
    TextView tvRefundNum;
    @BindView(R.id.tv_refund_money)
    TextView tvRefundMoney;
    @BindView(R.id.tv_refund_info)
    TextView tvRefundInfo;
    @BindView(R.id.rl_refund_intro)
    RelativeLayout rlRefundIntro;
    @BindView(R.id.rv_refund_reason)
    RecyclerView rvRefundReason;
    @BindView(R.id.tv_refund_shop)
    TextView tvRefundShop;
    @BindView(R.id.tv_refund_order_reason)
    TextView tvRefundReason;
    @BindView(R.id.tv_refund_no)
    TextView tvRefundNo;
    @BindView(R.id.tv_apply_refund_time)
    TextView tvApplyTime;
    @BindView(R.id.tv_refund_result)
    TextView tvRefundResult;
    @BindView(R.id.rl_refund_result)
    RelativeLayout rlRefundResult;
    @BindView(R.id.tv_refund_time)
    TextView tvRefundTime;
    @BindView(R.id.rl_refund_time)
    RelativeLayout rlRefundTime;
    @BindView(R.id.tv_refund_success)
    TextView tvRefundSuccess;
    @BindView(R.id.rl_refund_success)
    RelativeLayout rlRefundSuccess;
    @BindView(R.id.tv_refund_fail)
    TextView tvRefundFail;
    @BindView(R.id.rl_refund_fail)
    RelativeLayout rlRefundFail;
    @BindView(R.id.view_refund_fail_reason)
    View viewFailReason;
    @BindView(R.id.tv_refund_fail_reason)
    TextView tvFailReason;
    @BindView(R.id.rl_refund_fail_reason)
    RelativeLayout rlFailReason;

    private int refundId;
    private RefundDetailBean orderBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_detail);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("退款详情");
        getData();
        initData();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(Constant.REFUND_DETAIL)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("dataId", String.valueOf(refundId))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        orderBean = GsonUtils.jsonToBean(response, RefundDetailBean.class);
                        if (orderBean.isFlag() && orderBean.getData() != null) {
                            initView();
                        }

                    }
                });
    }

    private void initView() {
        String status = null;
        if (TextUtils.isEmpty(orderBean.getData().getRefundType())) {
            status = "";
        } else if ("AUDIT".equals(orderBean.getData().getRefundType())) {
            switch (orderBean.getData().getRefundApplyType()) {
                case "REFUND":
                    status = "仅退款，";
                    break;
                case "RETURN":
                    status = "退货退款，";
                    break;
            }
        } else {
            status = "自动退款，";
        }

        switch (orderBean.getData().getRefundState()) {
            case "UNDO":
                status += "撤销退款";
                tvRefundStatus.setTextColor(ContextCompat.getColor(this, R.color.gray_10));
                break;
            case "APPLY":
                status += "退款中";
                tvRefundStatus.setTextColor(ContextCompat.getColor(this, R.color.gray_10));
                break;
            case "FAIL":
                status += "退款失败";
                tvRefundStatus.setTextColor(ContextCompat.getColor(this, R.color.red_99));
                rlRefundResult.setVisibility(View.VISIBLE);
                rlRefundFail.setVisibility(View.VISIBLE);
                viewFailReason.setVisibility(View.VISIBLE);
                rlFailReason.setVisibility(View.VISIBLE);

                tvRefundResult.setText("不同意");
                tvRefundFail.setText(orderBean.getData().getRefundDealTime());
                tvFailReason.setText(orderBean.getData().getRefundDealDesc());
                break;
            case "SUCCESS":
                status += "退款成功";
                tvRefundStatus.setTextColor(ContextCompat.getColor(this, R.color.green_46));
                rlRefundResult.setVisibility(View.VISIBLE);
                rlRefundSuccess.setVisibility(View.VISIBLE);
                rlRefundTime.setVisibility(View.VISIBLE);

                tvRefundResult.setText("同意");
                tvRefundTime.setText(orderBean.getData().getRefundDealTime());
                tvRefundSuccess.setText(orderBean.getData().getRefundDealTime());
                break;
        }

        tvRefundStatus.setText(status);

        tvRefundNum.setText(String.valueOf(orderBean.getData().getDetailAccount()));
        tvRefundMoney.setText("¥" + DisplayUtils.decimalFormat(orderBean.getData().getRefundAmount()));
        if (!TextUtils.isEmpty(orderBean.getData().getRefundApplyDesc())) {
            rlRefundIntro.setVisibility(View.VISIBLE);
            tvRefundInfo.setText(orderBean.getData().getRefundApplyDesc());
        }

        if (!TextUtils.isEmpty(orderBean.getData().getRefundApplyPic())) {
            rvRefundReason.setVisibility(View.VISIBLE);
            rvRefundReason.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayout.HORIZONTAL, false));
            String[] refundPic = orderBean.getData().getRefundApplyPic().split(",");
            List<String> picList = new ArrayList<>(Arrays.asList(refundPic));
            CommonAdapter<String> adapter = new CommonAdapter<String>(this,
                    R.layout.rv_refund_picture_item, picList) {
                @Override
                protected void convert(ViewHolder holder, String s, int position) {
                    SimpleDraweeView ivPic = holder.getView(R.id.iv_refund_order);
                    ivPic.setImageURI(s);
                }
            };

            rvRefundReason.setAdapter(adapter);
        }

        tvRefundShop.setText(orderBean.getData().getShopName());
        tvRefundReason.setText(orderBean.getData().getRefundCause());
        tvRefundNo.setText(orderBean.getData().getRefundApplyNumber());
        tvApplyTime.setText(orderBean.getData().getApplyTime());
    }

    private void getData() {
        refundId = getIntent().getIntExtra("refund_id", 0);
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
