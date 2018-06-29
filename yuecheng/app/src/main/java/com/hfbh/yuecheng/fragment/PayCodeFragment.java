package com.hfbh.yuecheng.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.MyCouponBean;
import com.hfbh.yuecheng.bean.UserInfoBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.service.TimerService;
import com.hfbh.yuecheng.ui.CouponActivity;
import com.hfbh.yuecheng.ui.MemberBalanceActivity;
import com.hfbh.yuecheng.ui.MemberPointsActivity;
import com.hfbh.yuecheng.utils.BarcodeUtils;
import com.hfbh.yuecheng.utils.DataManagerUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.QRCodeUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.smarttop.library.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Author：Libin on 2018/5/23 16:20
 * Email：1993911441@qq.com
 * Describe：付款码
 */
public class PayCodeFragment extends BaseFragment {
    @BindView(R.id.iv_pay_barcode)
    ImageView ivPayBarcode;
    @BindView(R.id.tv_pay_barcode)
    TextView tvPayBarcode;
    @BindView(R.id.iv_pay_qrcode)
    ImageView ivPayQrcode;
    @BindView(R.id.tv_paycode_money)
    TextView tvPaycodeMoney;
    @BindView(R.id.ll_paycode_money)
    LinearLayout llPaycodeMoney;
    @BindView(R.id.tv_paycode_score)
    TextView tvPaycodeScore;
    @BindView(R.id.ll_paycode_score)
    LinearLayout llPaycodeScore;
    @BindView(R.id.tv_paycode_ticket)
    TextView tvPaycodeTicket;
    @BindView(R.id.ll_paycode_ticket)
    LinearLayout llPaycodeTicket;
    private Unbinder unbinder;
    private UserInfoBean userInfoBean;
    //显示隐藏付款码
    private boolean isShow;

    private Bitmap barBmp;
    private Bitmap qrBmp;

    private Timer mTimer;
    private TimerTask mTimerTask;
    //付款码
    private String payCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_code, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        getCouponCount();
        initView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshCode();
        TimerService.getConnect(getActivity());
    }

    /**
     * 获取优惠券数量
     */
    private void getCouponCount() {
        OkHttpUtils.post()
                .url(Constant.MY_COUPON)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(getActivity(), "hash"))
                .addParams("token", SharedPreUtils.getStr(getActivity(), "token"))
                .addParams("cardNumber", SharedPreUtils.getStr(getActivity(), "card_number"))
                .addParams("couponTypeKind", "VOUCHER")
                .addParams("queryType", "UNUSE")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyCouponBean couponBean = GsonUtils.jsonToBean(response, MyCouponBean.class);
                        if (couponBean.isFlag() && couponBean.getData() != null
                                && couponBean.getData().size() > 0) {
                            tvPaycodeTicket.setText(String.valueOf(couponBean.getData().size()));
                        }
                    }
                });

    }

    private void getData() {
        userInfoBean = (UserInfoBean) getArguments().getSerializable("pay_code");
    }

    private void initView() {
        tvPaycodeMoney.setText(DisplayUtils.isInteger(userInfoBean.getData().getAccountBalance()));
        tvPaycodeScore.setText(String.valueOf((int) userInfoBean.getData().getPoints()));
    }

    /**
     * 定时刷新付款码
     */
    private void refreshCode() {
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        payCode = userInfoBean.getData().getCardNumber() + System.currentTimeMillis();
                        if (isShow) {
                            tvPayBarcode.setText(payCode);
                        } else {
                            tvPayBarcode.setText("点击查看数字");
                        }

                        barBmp = BarcodeUtils.creatBarcode(payCode,
                                (int) DisplayUtils.dp2px(getActivity(), 225),
                                (int) DisplayUtils.dp2px(getActivity(), 41));
                        ivPayBarcode.setImageBitmap(barBmp);

                        qrBmp = QRCodeUtils.createQRCode(payCode, (int) DisplayUtils.dp2px(getActivity(), 200));

                        ivPayQrcode.setImageBitmap(qrBmp);
                    }
                });

            }
        };
        mTimer.schedule(mTimerTask, 0, 60000);
    }

    public static PayCodeFragment newInstance(UserInfoBean userInfoBean) {
        Bundle args = new Bundle();
        args.putSerializable("pay_code", userInfoBean);
        PayCodeFragment fragment = new PayCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @OnClick({R.id.tv_pay_barcode, R.id.ll_paycode_money, R.id.ll_paycode_score, R.id.ll_paycode_ticket})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pay_barcode:
                if (!isShow) {
                    tvPayBarcode.setText(payCode);
                    tvPayBarcode.setTextColor(getResources().getColor(R.color.gray_10));
                    isShow = true;
                } else {
                    tvPayBarcode.setText("点击查看数字");
                    tvPayBarcode.setTextColor(getResources().getColor(R.color.blue_a4));
                    isShow = false;
                }
                break;
            case R.id.ll_paycode_money:
                startActivity(new Intent(getActivity(), MemberBalanceActivity.class));
                break;
            case R.id.ll_paycode_score:
                startActivity(new Intent(getActivity(), MemberPointsActivity.class));
                break;
            case R.id.ll_paycode_ticket:
                startActivity(new Intent(getActivity(), CouponActivity.class));
                break;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        stopTimer();

    }

    /**
     * 取消定时器
     */
    private void stopTimer() {
        //停止由AlarmManager启动的循环
        TimerService.stop(getActivity());
        //停止由服务启动的循环
        Intent intent = new Intent(getActivity(), TimerService.class);
        getActivity().stopService(intent);
        if (mTimer != null) {
            mTimer.cancel();
            mTimerTask.cancel();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        recycleBitmap();
        stopTimer();
    }


    /**
     * bitmap回收
     */
    private void recycleBitmap() {
        DataManagerUtils.recycleBmp(barBmp);
        DataManagerUtils.recycleBmp(qrBmp);
    }
}
