package com.hfbh.yuecheng.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.hfbh.yuecheng.utils.LogUtils;
import com.smarttop.library.utils.LogUtil;

/**
 * Author：Libin on 2018/9/4 09:08
 * Email：1993911441@qq.com
 * Describe：webview滑动监听
 */
public class ScrollWebView extends WebView {


    private OnScrollChangeListener onScrollChangeListener;

    public ScrollWebView(Context context) {
        super(context);
    }

    public ScrollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        LogUtils.log(t+","+oldt);
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChanged(t - oldt);
        }
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

    public interface OnScrollChangeListener {
        public void onScrollChanged(int y);

    }
}
