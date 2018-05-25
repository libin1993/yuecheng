package com.hfbh.yuecheng.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author：Libin on 2018/5/24 17:50
 * Email：1993911441@qq.com
 * Describe：
 */
public class GridItemDecoration1 extends RecyclerView.ItemDecoration {
    //间距
    private int mSpace;

    public GridItemDecoration1(int space) {
        this.mSpace = space;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        if (position >= 0) {
            if (position % 2 == 0) {
                outRect.set(mSpace * 2, 0, mSpace, 0);
            } else {
                outRect.set(mSpace, 0, mSpace * 2, 0);
            }
        }
    }
}