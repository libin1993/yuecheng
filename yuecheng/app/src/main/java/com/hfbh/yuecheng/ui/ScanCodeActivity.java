package com.hfbh.yuecheng.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.yanzhenjie.zbar.camera.CameraPreview;
import com.yanzhenjie.zbar.camera.ScanCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Libin on 2018/6/4 15:33
 * Email：1993911441@qq.com
 * Describe：扫描二维码
 */
public class ScanCodeActivity extends BaseActivity {
    @BindView(R.id.capture_preview)
    CameraPreview capturePreview;
    @BindView(R.id.capture_crop_view)
    RelativeLayout captureCropView;
    @BindView(R.id.iv_scan_back)
    ImageView ivScanBack;
    @BindView(R.id.capture_scan_line)
    ImageView captureScanLine;

    private ValueAnimator mScanAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        capturePreview.setScanCallback(new ScanCallback() {
            @Override
            public void onScanResult(String content) {
                vibrator();
            }
        });
    }

    /**
     * 二维码扫描结果非本平台商品
     */
    private void scanFail() {
        ToastUtils.showToast(this, "仅支持本平台商品");
        finish();
    }


    /**
     * 振动
     */
    private void vibrator() {
        Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        if (vib != null) {
            vib.vibrate(100);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mScanAnimator != null) {
            if (capturePreview.start()) {
                mScanAnimator.start();
            }
        }
    }

    @Override
    public void onPause() {
        // Must be called here, otherwise the camera should not be released properly.
        stopScan();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mScanAnimator.cancel();
        capturePreview.stop();
    }

    /**
     * Stop scan.
     */
    private void stopScan() {
        mScanAnimator.cancel();
        capturePreview.stop();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (mScanAnimator == null) {
            int height = captureCropView.getMeasuredHeight() - 25;
            mScanAnimator = ObjectAnimator.ofFloat(captureScanLine, "translationY", 0F, height).setDuration(3000);
            mScanAnimator.setInterpolator(new LinearInterpolator());
            mScanAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mScanAnimator.setRepeatMode(ValueAnimator.REVERSE);

            if (capturePreview.start()) {
                mScanAnimator.start();
            }
        }
    }

    @OnClick(R.id.iv_scan_back)
    public void onViewClicked() {
        finish();
    }
}
