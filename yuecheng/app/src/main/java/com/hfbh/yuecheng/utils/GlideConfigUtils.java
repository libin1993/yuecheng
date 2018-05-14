package com.hfbh.yuecheng.utils;

import android.Manifest;
import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Author：Libin on 2018/5/14 14:12
 * Email：1993911441@qq.com
 * Describe：Glide图片清晰度,缓存
 */
public class GlideConfigUtils implements GlideModule {
    @Override
    public void applyOptions(final Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        if (EasyPermissions.hasPermissions(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            builder.setDiskCache(new DiskCache.Factory() {
                @Override
                public DiskCache build() {
                    File file = new File(Environment.getExternalStorageDirectory()
                            .getAbsolutePath() + "/yuecheng/GlideCache");
                    return DiskLruCacheWrapper.get(file, 1024 * 1024 * 100);
                }
            });
        }

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
