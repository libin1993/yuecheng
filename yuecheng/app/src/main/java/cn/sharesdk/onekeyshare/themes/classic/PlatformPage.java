/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 mob.com. All rights reserved.
 */

package cn.sharesdk.onekeyshare.themes.classic;

import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.CustomerLogo;
import cn.sharesdk.onekeyshare.OnekeySharePage;
import cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;

import com.hfbh.yuecheng.R;
import com.mob.tools.gui.MobViewPager;
import com.mob.tools.utils.ResHelper;

/**
 * 九宫格的抽象类
 */
public abstract class PlatformPage extends OnekeySharePage {
    private ClassicTheme impl;
    /**
     * 点击九格宫，展示编辑界面，要执行的子线程
     */
    private Runnable beforeFinish;
    /**
     * 九宫格显示时的动画
     */
    private Animation animShow;
    /**
     * 九宫格隐藏时的动画
     */
    private Animation animHide;

    private boolean finished;

    public PlatformPage(OnekeyShareThemeImpl impl) {
        super(impl);
        this.impl = ResHelper.forceCast(impl);
    }

    public void onCreate() {

        activity.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));


        LinearLayout llPage = new LinearLayout(activity);
        llPage.setOrientation(LinearLayout.VERTICAL);
        activity.setContentView(llPage);

        View vTop = new View(activity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0);
        lp.weight = 1;
        vTop.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        llPage.addView(vTop, lp);



        lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins((int) dp2px(15), 0, (int) dp2px(15), (int) dp2px(15));

        LinearLayout llPanel = new LinearLayout(activity);
        llPanel.setBackgroundResource(R.drawable.bound_white_8dp);
        llPanel.setOrientation(LinearLayout.VERTICAL);
        llPage.addView(llPanel, lp);


        //增加分享到layout
        lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int) dp2px(60));
        RelativeLayout titleLayout = new RelativeLayout(activity);



        RelativeLayout.LayoutParams rpTitle = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rpTitle.addRule(RelativeLayout.CENTER_VERTICAL);
        rpTitle.setMargins((int) dp2px(30), 0, 0, 0);

        TextView tv = new TextView(activity);
        tv.setGravity(Gravity.CENTER);
        tv.setText("分享到");
        tv.setTextSize(18);
        tv.setTextColor(0xff000000);
        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        titleLayout.addView(tv, rpTitle);


        RelativeLayout.LayoutParams rpImg = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        rpImg.addRule(RelativeLayout.CENTER_VERTICAL);
        rpImg.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        ImageView ivCancel = new ImageView(activity);
        ivCancel.setImageResource(R.mipmap.ic_close_gray);
        ivCancel.setScaleType(ImageView.ScaleType.CENTER);
        ivCancel.setPadding((int) dp2px(18), 0, (int) dp2px(18), 0);
        ivCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleLayout.addView(ivCancel, rpImg);

        llPanel.addView(titleLayout, lp);


        MobViewPager mvp = new MobViewPager(activity);
        ArrayList<Object> cells = collectCells();
        PlatformPageAdapter adapter = newAdapter(cells);
        lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, adapter.getPanelHeight());
        llPanel.addView(mvp, lp);

        IndicatorView vInd = new IndicatorView(activity);
        lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, adapter.getBottomHeight());
        llPanel.addView(vInd, lp);

        vInd.setScreenCount(adapter.getCount());
        vInd.onScreenChange(0, 0);
        adapter.setIndicator(vInd);
        mvp.setAdapter(adapter);

        View viewBg = new View(activity);
        lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int) dp2px(20));
        llPanel.addView(viewBg, lp);












//        lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//
//
//        LinearLayout llPanel = new LinearLayout(activity);
//
//        llPanel.setOrientation(LinearLayout.VERTICAL);
//        llPage.addView(llPanel, lp);
//
//        //增加分享到layout
//        lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) dp2px(44));
//        LinearLayout buttonLayout = new LinearLayout(activity);
//        buttonLayout.setBackgroundColor(Color.WHITE);
//
//        LinearLayout.LayoutParams lpTitle = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                (int) dp2px(44));
//        TextView tv = new TextView(activity);
//        tv.setGravity(Gravity.CENTER);
//        tv.setText("分享到");
//        tv.setTextSize(16);
//        tv.setTextColor(0xff2e2e2e);
//        tv.setBackgroundColor(Color.WHITE);
//        buttonLayout.addView(tv, lpTitle);
//        llPanel.addView(buttonLayout, lp);
//
//
//
//        MobViewPager mvp = new MobViewPager(activity);
//        ArrayList<Object> cells = collectCells();
//        PlatformPageAdapter adapter = newAdapter(cells);
//        lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, adapter.getPanelHeight());
//        llPanel.addView(mvp, lp);
//
//        IndicatorView vInd = new IndicatorView(activity);
//        lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, adapter.getBottomHeight());
//        llPanel.addView(vInd, lp);
//
//        vInd.setScreenCount(adapter.getCount());
//        vInd.onScreenChange(0, 0);
//        adapter.setIndicator(vInd);
//        mvp.setAdapter(adapter);
//
//
//        lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 51);
//        LinearLayout buttonLayout3 = new LinearLayout(activity);
//        buttonLayout3.setBackgroundColor(0xffffffff);
//
//        LinearLayout.LayoutParams lpLine = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
//        lpLine.setMargins(0, 50, 0, 0);
//        View viewLine = new TextView(activity);
//        viewLine.setBackgroundColor(0xffababab);
//        buttonLayout3.addView(viewLine, lpLine);
//        llPanel.addView(buttonLayout3, lp);
//
//
//        //增加取消按钮
//        lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) dp2px(44));
//        LinearLayout buttonLayout2 = new LinearLayout(activity);
//        buttonLayout2.setBackgroundColor(Color.WHITE);
//
//        LinearLayout.LayoutParams lp22 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                (int) dp2px(44));
//        TextView tvCancel = new TextView(activity);
//        tvCancel.setText("取消");
//        tvCancel.setTextSize(15);
//        tvCancel.setGravity(Gravity.CENTER);
//        tvCancel.setBackgroundColor(Color.WHITE);
//        tvCancel.setTextColor(0xff666666);
//        tvCancel.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                activity.getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
//                finish();
//            }
//        });
//        buttonLayout2.addView(tvCancel, lp22);
//        llPanel.addView(buttonLayout2, lp);

    }

    protected abstract PlatformPageAdapter newAdapter(ArrayList<Object> cells);

    protected ArrayList<Object> collectCells() {
        ArrayList<Object> cells = new ArrayList<Object>();

        Platform[] platforms = ShareSDK.getPlatformList();
        if (platforms == null) {
            platforms = new Platform[0];
        }
        HashMap<String, String> hides = getHiddenPlatforms();
        if (hides == null) {
            hides = new HashMap<String, String>();
        }
        for (Platform p : platforms) {
            if (!hides.containsKey(p.getName())) {
                cells.add(p);
            }
        }

        ArrayList<CustomerLogo> customers = getCustomerLogos();
        if (customers != null && customers.size() > 0) {
            cells.addAll(customers);
        }

        return cells;
    }

    public final void showEditPage(final Platform platform) {
        beforeFinish = new Runnable() {
            public void run() {
                boolean isSilent = isSilent();
                boolean isCustomPlatform = platform instanceof CustomPlatform;
                boolean isUseClientToShare = isUseClientToShare(platform);
                if (isSilent || isCustomPlatform || isUseClientToShare) {
                    shareSilently(platform);
                } else {
                    ShareParams sp = formateShareData(platform);
                    if (sp != null) {
                        // 编辑分享内容的统计
                        ShareSDK.logDemoEvent(3, null);
                        if (getCustomizeCallback() != null) {
                            getCustomizeCallback().onShare(platform, sp);
                        }
                        impl.showEditPage(activity, platform, sp);
                    }
                }
            }
        };
        finish();
    }

    public final void performCustomLogoClick(final View v, final CustomerLogo logo) {
        beforeFinish = new Runnable() {
            public void run() {
                logo.listener.onClick(v);
            }
        };
        activity.getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        finish();
    }


    public boolean onFinish() {
        if (finished) {
            finished = false;
            return false;
        }

        if (beforeFinish == null) {
            // 取消分享菜单的统计
            ShareSDK.logDemoEvent(2, null);
        } else {
            beforeFinish.run();
            beforeFinish = null;
        }

        finished = true;
        finish();
        return true;

    }

    private float dp2px(float dpValue) {
        final float scale = activity.getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }
}
