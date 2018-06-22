package com.hfbh.yuecheng.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Author：Libin on 2018/6/21 15:17
 * Email：1993911441@qq.com
 * Describe：在部分Android 7.0上PopupWindow.showAsDropDown不起作用
 */
public class DropDownPopupWindow extends PopupWindow{

    public DropDownPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }
}
