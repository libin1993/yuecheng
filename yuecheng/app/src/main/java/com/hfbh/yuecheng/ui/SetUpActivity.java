package com.hfbh.yuecheng.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DataManagerUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static java.util.ResourceBundle.clearCache;

/**
 * Author：Libin on 2018/5/30 13:10
 * Email：1993911441@qq.com
 * Describe：设置
 */
public class SetUpActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.ll_set_pwd)
    LinearLayout llSetPwd;
    @BindView(R.id.ll_set_pay_type)
    LinearLayout llSetPayType;
    @BindView(R.id.tv_clear_cache)
    TextView tvClearCache;
    @BindView(R.id.ll_clear_cache)
    LinearLayout llClearCache;
    @BindView(R.id.ll_feed_back)
    LinearLayout llFeedBack;
    @BindView(R.id.ll_about_app)
    LinearLayout llAboutApp;
    @BindView(R.id.tv_log_out)
    TextView tvLogOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        tvHeaderTitle.setText("设置");
        String totalCacheSize = DataManagerUtils.getTotalCacheSize(this);
        tvClearCache.setText(totalCacheSize);
        if (SharedPreUtils.getBoolean(this, "is_login", false)) {
            tvLogOut.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.iv_header_back, R.id.ll_set_pwd, R.id.ll_set_pay_type, R.id.ll_clear_cache,
            R.id.ll_feed_back, R.id.ll_about_app, R.id.tv_log_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.ll_set_pwd:
                toLogin(SetPwdActivity.class);
                break;
            case R.id.ll_set_pay_type:
                break;
            case R.id.ll_clear_cache:
                clearAppCache();
                break;
            case R.id.ll_feed_back:
                startActivity(new Intent(this, FeedBackActivity.class));
                break;
            case R.id.ll_about_app:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            case R.id.tv_log_out:
                logoutDialog();
                break;
        }
    }

    /**
     * @param cls 是否登录
     */
    private void toLogin(Class<?> cls) {
        Intent intent;
        if (SharedPreUtils.getBoolean(this, "is_login", false)) {
            intent = new Intent(this, cls);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 退出登录提示
     */
    public void logoutDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("退出登录");
        dialog.setMessage("您确定要退出登录吗？");
        //为“确定”按钮注册监听事件
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                logOut();


            }
        });
        //为“取消”按钮注册监听事件
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();

    }

    /**
     * 退出登录
     */
    private void logOut() {

        OkHttpUtils.post()
                .url(Constant.LOG_OUT)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                        if (responseBean.isFlag()) {
                            SharedPreUtils.deleteStr(SetUpActivity.this, "is_login");
                            ToastUtils.showToast(SetUpActivity.this, "退出成功");
                            Intent intent = new Intent(SetUpActivity.this, MainActivity.class);
                            intent.putExtra("change_market", true);
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtils.showToast(SetUpActivity.this, responseBean.getMsg());
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(SetUpActivity.this, "is_login");
                            }
                        }
                    }
                });
    }

    /**
     * 清除缓存
     */
    private void clearAppCache() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("清除缓存");
        dialog.setMessage("您确定要清空缓存吗？");
        //确定
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataManagerUtils.cleanApplicationCache(SetUpActivity.this);
                ToastUtils.showToast(SetUpActivity.this, "清除成功");
                tvClearCache.setText(null);
            }
        });
        //取消
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();
    }


    @Subscribe
    public void isLogin(String msg) {
        if ("login_success".equals(msg)) {
            tvLogOut.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
