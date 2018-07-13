package com.hfbh.yuecheng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ConsumeBean;
import com.hfbh.yuecheng.utils.LogUtils;
import com.smarttop.library.utils.LogUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Libin on 2018/7/13 09:51
 * Email：1993911441@qq.com
 * Describe：消费明细
 */
public class ConsumeDetailActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_consume_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_consume_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_consume_order_shop)
    TextView tvOrderShop;
    @BindView(R.id.tv_consume_order_money)
    TextView tvOrderMoney;
    @BindView(R.id.rv_consume_goods)
    RecyclerView rvGoods;

    private ConsumeBean.DataBean dataBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_detail);
        ButterKnife.bind(this);
        tvHeaderTitle.setText("消费明细");
        getData();
        initView();
    }

    private void initView() {
        tvOrderNo.setText(dataBean.getOrderNo());
        tvOrderTime.setText(dataBean.getOrderTime());
        tvOrderShop.setText(dataBean.getOrderShopName());
        tvOrderMoney.setText("¥" + dataBean.getTotalMoney());

        rvGoods.setLayoutManager(new LinearLayoutManager(this));
        CommonAdapter<ConsumeBean.DataBean.SalesListBean> adapter = new CommonAdapter<ConsumeBean
                .DataBean.SalesListBean>(this, R.layout.rv_consume_goods_item, dataBean.getSalesList()) {
            @Override
            protected void convert(ViewHolder holder, ConsumeBean.DataBean.SalesListBean salesListBean, int position) {
                holder.setText(R.id.tv_consume_goods_name, salesListBean.getProductName());
                holder.setText(R.id.tv_consume_goods_money, "¥" + salesListBean.getSaleMoney());
            }
        };
        rvGoods.setAdapter(adapter);
    }

    private void getData() {
        dataBean = (ConsumeBean.DataBean) getIntent().getExtras().getSerializable("consume_detail");
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
