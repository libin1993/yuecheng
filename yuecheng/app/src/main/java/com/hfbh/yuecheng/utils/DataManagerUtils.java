package com.hfbh.yuecheng.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;

/**
 * Author：Libin on 2016/10/26 09:12
 * Email：1993911441@qq.com
 * Describe：应用数据清除管理器
 */
public class DataManagerUtils {
    /**
     * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * * @param context
     */
    private static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    /**
     * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * * @param context
     */
    private static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
                + context.getPackageName() + "/databases"));
    }

    /**
     * 清除/data/data/com.xxx.xxx/files下的内容 * * @param context
     */
    private static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
     * context
     */
    private static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }


    public static void cleanApplicationCache(Context context) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanFiles(context);
        //清除图片缓存
        deleteFilesByDirectory(new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator + "CloudWorkshop"));
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
     */
    private static void deleteFilesByDirectory(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (File childFile : childFiles) {
                deleteFilesByDirectory(childFile);
            }
            file.delete();
        }
    }


    /**
     * @param bmp bitmap回收
     */
    public static void recycleBmp(Bitmap bmp) {
        if (bmp != null && !bmp.isRecycled()) {
            bmp.recycle();
            bmp = null;
        }
        System.gc();
    }

}