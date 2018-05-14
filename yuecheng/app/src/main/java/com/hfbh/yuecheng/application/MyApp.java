package com.hfbh.yuecheng.application;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
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

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
