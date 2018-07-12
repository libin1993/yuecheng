package com.hfbh.yuecheng.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.bean.FunctionBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.GuideActivity;
import com.hfbh.yuecheng.ui.LoginActivity;
import com.hfbh.yuecheng.ui.MemberCardActivity;
import com.hfbh.yuecheng.ui.SearchShopActivity;
import com.hfbh.yuecheng.ui.ValidateActivity;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Author：Libin on 2018/7/12 15:07
 * Email：1993911441@qq.com
 * Describe：
 */
public class FunctionAdapter extends DelegateAdapter.Adapter<FunctionAdapter.FunctionViewHolder> {
    private LayoutHelper layoutHelper;
    private List<FunctionBean.DataBean> functionList;
    private int mLayoutId;
    private Context mContext;
    private int mViewType;
    private int mCount;

    public FunctionAdapter(Context mContext, LayoutHelper layoutHelper, int mLayoutId,
                           List<FunctionBean.DataBean> functionList, int mCount, int mViewType) {
        this.layoutHelper = layoutHelper;
        this.functionList = functionList;
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
    public FunctionAdapter.FunctionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == mViewType) {
            return new FunctionAdapter.FunctionViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false));
        }
        return null;

    }

    @Override
    public void onBindViewHolder(FunctionAdapter.FunctionViewHolder holder, int position) {
        holder.rvFunction.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.HORIZONTAL,
                false));

        //布局宽高
        final int width = DisplayUtils.getMetrics((Activity) mContext).widthPixels / 4;
        CommonAdapter<FunctionBean.DataBean> adapter = new CommonAdapter<FunctionBean
                .DataBean>(mContext, R.layout.rv_founction_item, functionList) {
            @Override
            protected void convert(ViewHolder holder, FunctionBean.DataBean dataBean, int position) {
                LinearLayout llFunction = holder.getView(R.id.ll_function_item);

                ViewGroup.LayoutParams layoutParams = llFunction.getLayoutParams();
                layoutParams.width = width;
                llFunction.setLayoutParams(layoutParams);

                Glide.with(mContext)
                        .load(dataBean.getFunctionIco())
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .placeholder(R.mipmap.img_place_circle)
                        .into((ImageView) holder.getView(R.id.iv_function_item));
                holder.setText(R.id.tv_function_name, dataBean.getFunctionName());
            }
        };

        holder.rvFunction.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                switch (functionList.get(position).getFunctionCode()) {
                    case "STORE"://找店铺
                        mContext.startActivity(new Intent(mContext, SearchShopActivity.class));
                        break;
                    case "SHOPPING"://我要买
                        break;
                    case "GUIDING"://室内导航
                        mContext.startActivity(new Intent(mContext, GuideActivity.class));
                        break;
                    case "PLAYING"://我要玩
                        break;
                    case "MEMBER_CODE"://会员码
                        toMemberCard();
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    @Override
    public int getItemViewType(int position) {
        return mViewType;
    }

    class FunctionViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvFunction;

        public FunctionViewHolder(View itemView) {
            super(itemView);
            rvFunction = (RecyclerView) itemView.findViewById(R.id.rv_homepage_function);
            rvFunction.setLayoutManager(new LinearLayoutManager(mContext,
                    LinearLayout.HORIZONTAL, false));
        }
    }

    /**
     * 会员卡
     */
    private void toMemberCard() {

        if (SharedPreUtils.getBoolean(mContext, "is_login", false)) {
            isSetPayPwd();
        } else {
            mContext.startActivity(new Intent(mContext, LoginActivity.class));
        }

    }


    /**
     * 是否设置支付密码
     */
    private void isSetPayPwd() {
        OkHttpUtils.post()
                .url(Constant.IS_SET_PAY_PWD)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(mContext, "hash"))
                .addParams("token", SharedPreUtils.getStr(mContext, "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);

                        if (responseBean.isFlag()) {
                            mContext.startActivity(new Intent(mContext, MemberCardActivity.class));
                        } else {
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(mContext, "is_login");
                            } else {
                                setPayPwd();
                            }
                        }
                    }
                });
    }

    /**
     * 设置支付密码
     */
    private void setPayPwd() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("提示");
        dialog.setMessage("您尚未设置支付密码，是否前去设置？");
        //为“确定”按钮注册监听事件
        dialog.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(mContext, ValidateActivity.class);
                intent.putExtra("type", "bind");
                mContext.startActivity(intent);

            }
        });

        //为“取消”按钮注册监听事件
        dialog.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();
    }
}
