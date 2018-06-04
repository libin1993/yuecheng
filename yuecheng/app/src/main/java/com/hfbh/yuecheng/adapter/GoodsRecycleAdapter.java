package com.hfbh.yuecheng.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.bean.GoodsBean;
import com.hfbh.yuecheng.ui.GoodsDetailActivity;
import com.hfbh.yuecheng.ui.NewGoodsActivity;
import com.hfbh.yuecheng.ui.PopGoodsActivity;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.ToastUtils;

import java.util.List;

/**
 * Author：Libin on 2018/5/21 15:05
 * Email：1993911441@qq.com
 * Describe：商品列表
 */
public class GoodsRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mInflater;
    private List<GoodsBean.DataBean> dataList;
    private Context mContext;
    //图片宽度
    private int imgWidth;

    public GoodsRecycleAdapter(Context mContext, List<GoodsBean.DataBean> dataList) {
        this.dataList = dataList;
        this.mContext = mContext;

        this.mInflater = LayoutInflater.from(mContext);
        //布局宽高
        int widthPixels = DisplayUtils.getMetrics((Activity) mContext).widthPixels;
        imgWidth = (int) ((widthPixels - DisplayUtils.dp2px(mContext, 35)) / 2);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                View view1 = mInflater.inflate(R.layout.layout_homepage_title, parent, false);
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, PopGoodsActivity.class));
                    }
                });
                return new ViewHolder1(view1);
            case 2:
                View view2 = mInflater.inflate(R.layout.rv_pop_goods_item, parent, false);
                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag();
                        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                        intent.putExtra("goods_id", dataList.get(position).getCommodityId());
                        mContext.startActivity(intent);
                    }
                });
                return new ViewHolder2(view2);
            case 3:
                View view3 = mInflater.inflate(R.layout.layout_homepage_title, parent, false);
                view3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, NewGoodsActivity.class));
                    }
                });
                return new ViewHolder3(view3);
            case 4:
                View view4 = mInflater.inflate(R.layout.rv_new_goods_item, parent, false);
                view4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag();
                        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                        intent.putExtra("goods_id", dataList.get(position).getCommodityId());
                        mContext.startActivity(intent);
                    }
                });
                return new ViewHolder4(view4);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder1) {
            ((ViewHolder1) holder).tvTitle.setText(dataList.get(position).getTitle());

        } else if (holder instanceof ViewHolder2) {

            ViewGroup.LayoutParams layoutParams = ((ViewHolder2) holder).ivPop.getLayoutParams();
            layoutParams.width = imgWidth;
            layoutParams.height = imgWidth;
            ((ViewHolder2) holder).ivPop.setLayoutParams(layoutParams);

            Glide.with(mContext)
                    .load(dataList.get(position).getPicturePath())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(((ViewHolder2) holder).ivPop);
            ((ViewHolder2) holder).tvName.setText(dataList.get(position).getCommodityName());
            ((ViewHolder2) holder).tvPrice.setText("¥" + dataList.get(position).getNowPrice());

            ((ViewHolder2) holder).tvOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
            ((ViewHolder2) holder).tvOld.setText("¥" + dataList.get(position).getOldPrice());
            holder.itemView.setTag(position);

        } else if (holder instanceof ViewHolder3) {

            ((ViewHolder3) holder).tvTitle.setText(dataList.get(position).getTitle());
        } else if (holder instanceof ViewHolder4) {

            ((ViewHolder4) holder).ivGoods.setImageURI(dataList.get(position).getPicturePath());

            ((ViewHolder4) holder).tvName.setText(dataList.get(position).getCommodityName());
            ((ViewHolder4) holder).tvTip.setText(dataList.get(position).getIndustryName());
            ((ViewHolder4) holder).tvTime.setText(dataList.get(position).getModifyTime());

            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        public TextView tvTitle;

        public ViewHolder1(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_home_title);


        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        public ImageView ivPop;
        public TextView tvName;
        public TextView tvPrice;
        public TextView tvOld;

        public ViewHolder2(View itemView) {
            super(itemView);
            ivPop = (ImageView) itemView.findViewById(R.id.iv_discovery_pop);
            tvName = (TextView) itemView.findViewById(R.id.tv_discovery_pop_name);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_discovery_pop_price);
            tvOld = (TextView) itemView.findViewById(R.id.tv_discovery_pop_original);
        }
    }

    class ViewHolder3 extends RecyclerView.ViewHolder {
        public TextView tvTitle;

        public ViewHolder3(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_home_title);
        }
    }

    class ViewHolder4 extends RecyclerView.ViewHolder {
        public SimpleDraweeView ivGoods;
        public TextView tvName;
        public TextView tvTip;
        public TextView tvTime;

        public ViewHolder4(View itemView) {
            super(itemView);
            ivGoods = (SimpleDraweeView) itemView.findViewById(R.id.iv_discovery_new);
            tvName = (TextView) itemView.findViewById(R.id.tv_discovery_new_name);
            tvTip = (TextView) itemView.findViewById(R.id.tv_discovery_new_tip);
            tvTime = (TextView) itemView.findViewById(R.id.tv_discovery_new_time);
        }
    }
}
