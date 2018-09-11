package com.hfbh.yuecheng.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.bean.MemberRightsBean;

import java.util.List;

/**
 * Author：Libin on 2018/6/4 10:38
 * Email：1993911441@qq.com
 * Describe：
 */
public class MyPagerAdapter extends PagerAdapter {
    private List<MemberRightsBean.DataBean> dataList;
    private Context context;

    public MyPagerAdapter(List<MemberRightsBean.DataBean> designerList, Context context) {
        this.dataList = designerList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.vp_member_card_item, null);

        SimpleDraweeView ivCard = (SimpleDraweeView) view.findViewById(R.id.iv_member_card_bg);
        ivCard.setImageURI(dataList.get(position).getAppPic());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
