package com.hfbh.yuecheng.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.bean.BannerBean;
import com.hfbh.yuecheng.ui.ActionDetailActivity;
import com.hfbh.yuecheng.ui.BannerInfoActivity;
import com.hfbh.yuecheng.ui.CouponDetailActivity;
import com.hfbh.yuecheng.ui.GiftDetailActivity;
import com.hfbh.yuecheng.utils.NetworkImageHolderView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Libin on 2018/7/12 14:32
 * Email：1993911441@qq.com
 * Describe：
 */
public class BannerAdapter extends DelegateAdapter.Adapter<BannerAdapter.BannerViewHolder> {
    private LayoutHelper layoutHelper;
    private List<BannerBean.DataBean> bannerList;
    private int mLayoutId;
    private Context mContext;
    private int mViewType;
    private int mCount;

    public BannerAdapter(Context mContext, LayoutHelper layoutHelper, int mLayoutId,
                         List<BannerBean.DataBean> bannerList, int mCount, int mViewType) {
        this.layoutHelper = layoutHelper;
        this.bannerList = bannerList;
        this.mLayoutId = mLayoutId;
        this.mContext = mContext;
        this.mCount = mCount;
        this.mViewType = mViewType;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == mViewType) {
            return new BannerViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false));
        }
        return null;

    }

    @Override
    public void onBindViewHolder(BannerAdapter.BannerViewHolder holder, int position) {
        holder.banner.startTurning(4000);
        List<String> bannerImg = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            bannerImg.add(bannerList.get(i).getAdvertPic());
        }
        holder.banner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, bannerImg)
                //设置两个点图片作为翻页指示器
                .setPageIndicator(new int[]{R.drawable.indicator_normal, R.drawable.indicator_focus})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    @Override
    public int getItemViewType(int position) {
        return mViewType;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ConvenientBanner banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = (ConvenientBanner) itemView.findViewById(R.id.banner_homepage);

            banner.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            String moduleCode = bannerList.get(position).getModuleCode();
                            int id = bannerList.get(position).getObjectId();
                            if (TextUtils.isEmpty(moduleCode) || id == 0) {
                                Intent intent = new Intent(mContext, BannerInfoActivity.class);
                                intent.putExtra("url", bannerList.get(position).getAdvertUrl());
                                mContext.startActivity(intent);
                            } else {
                                switch (moduleCode) {
                                    case "ACTIVITY":
                                        Intent intent1 = new Intent(mContext, ActionDetailActivity.class);
                                        intent1.putExtra("activity_id", id);
                                        mContext.startActivity(intent1);
                                        break;
                                    case "COUPON":
                                        Intent intent2 = new Intent(mContext, CouponDetailActivity.class);
                                        intent2.putExtra("coupon_id", id);
                                        mContext.startActivity(intent2);
                                        break;
                                    case "GIFT":
                                        Intent intent3 = new Intent(mContext, GiftDetailActivity.class);
                                        intent3.putExtra("gift_id", id);
                                        mContext.startActivity(intent3);
                                        break;

                                }
                            }
                        }
                    });
        }
    }

}
