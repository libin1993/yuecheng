package com.hfbh.yuecheng.application;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.v4.content.ContextCompat;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hfbh.yuecheng.R;
import com.mob.MobSDK;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.commonsdk.UMConfigure;
import com.wang.avi.indicators.BallSpinFadeLoaderIndicator;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Author：Libin on 2018/5/14 13:49
 * Email：1993911441@qq.com
 * Describe：全局对象
 */
public class MyApp extends Application {
    public static String appVersion;
    public static String appType = "Android";
    public static String organizeId = "";
    public static String organizeName = "";
    //更新链接
    public static String updateUrl;
    //更新版本
    public static String updateVersion;
    //更新内容
    public static String updateContent;

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColors(0xffededed, 0xffaaaaaa);//全局设置主题颜色

                ClassicsHeader classicsHeader = new ClassicsHeader(context);
                classicsHeader.setEnableLastTime(false);
                classicsHeader.setArrowDrawable(ContextCompat.getDrawable(context, R.mipmap.ic_pulltorefresh_arrow));
                BallSpinFadeLoaderIndicator ball = new BallSpinFadeLoaderIndicator();
                ball.setColor(0xffaaaaaa);
                classicsHeader.setProgressDrawable(ball);
                classicsHeader.setTextSizeTitle(12);
                return classicsHeader;
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                layout.setPrimaryColors(0xffededed, 0xffaaaaaa);//全局设置主题颜色
                ClassicsFooter classicsFooter = new ClassicsFooter(context);
                BallSpinFadeLoaderIndicator ball = new BallSpinFadeLoaderIndicator();
                ball.setColor(0xffaaaaaa);
                classicsFooter.setProgressDrawable(ball);
                classicsFooter.setTextSizeTitle(12);

                return classicsFooter;
            }
        });

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //fresco
        Fresco.initialize(this);
        //okHttp
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/yuecheng/Cache");
            builder.cache(new Cache(file, 1024 * 1024 * 100));
        }
        OkHttpClient okHttpClient = builder.connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);

        getVersionName();
        //友盟
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        //ShareSdk
        MobSDK.init(this);


    }

    /**
     * 获取版本名称
     */
    private void getVersionName() {
        try {
            PackageManager packageManager = getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            appVersion = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
