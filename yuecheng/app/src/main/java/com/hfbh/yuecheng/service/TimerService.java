package com.hfbh.yuecheng.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.bean.ScannedDetailBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.ui.ScanedOrderActivity;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Author：Libin on 2018/6/12 13:48
 * Email：1993911441@qq.com
 * Describe：付款码定时查询
 */
public class TimerService extends Service {
    private boolean pushThread = true;
    private static Context context;

    public TimerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if ("3".equals(intent.getStringExtra("type"))) {
            //判断当系统版本大于20，即超过Android5.0时，我们采用线程循环的方式请求。
            //当小于5.0时的系统则采用定时唤醒服务的方式执行循环
            int currentapiVersion = android.os.Build.VERSION.SDK_INT;
            if (currentapiVersion > 20) {
                getPushThread();
            } else {
                getHttp();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //循环请求的线程
    public void getPushThread() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pushThread) {
                    try {
                        Thread.sleep(3000);
                        if (pushThread) {
                            getHttp();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //请求网络获取数据
    private void getHttp() {
        OkHttpUtils.post()
                .url(Constant.QUERY_ORDER)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(context, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        ScannedDetailBean orderDetailBean = GsonUtils.jsonToBean(response, ScannedDetailBean.class);
                        if (orderDetailBean.isFlag()) {
                            pushThread = false;
                            stop(context);

                            Intent intent1 = new Intent(context, TimerService.class);
                            stopService(intent1);

                            Intent intent = new Intent(TimerService.this, ScanedOrderActivity.class);
                            intent.putExtra("order", orderDetailBean);
                            startActivity(intent);
                        } else if (orderDetailBean.getCode() == 4002) {
                            SharedPreUtils.deleteStr(TimerService.this, "is_login");
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        pushThread = false;

        super.onDestroy();
    }

    //启动服务和定时器
    public static void getConnect(Context mContext) {

        try {
            context = mContext;
            Intent intent = new Intent(mContext, TimerService.class);
            intent.putExtra("type", "3");
            if (Build.VERSION.SDK_INT > 20) {
                //一般的启动服务的方式
                mContext.startService(intent);
            } else {
                //定时唤醒服务的启动方式
                PendingIntent pIntent = PendingIntent.getService(mContext, 0,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) mContext
                        .getSystemService(Context.ALARM_SERVICE);
                if (alarmManager != null) {
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                            System.currentTimeMillis(), 3000, pIntent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //停止由AlarmManager启动的循环
    public static void stop(Context mContext) {
        Intent intent = new Intent(mContext, TimerService.class);
        PendingIntent pIntent = PendingIntent.getService(mContext, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) mContext
                .getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(pIntent);
        }
    }
}
