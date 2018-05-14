package com.hfbh.yuecheng.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author：Libin on 2018/5/14 14:21
 * Email：1993911441@qq.com
 * Describe：
 */
public class BaseFragment extends Fragment {
    private BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (BaseActivity) context;
    }

}
