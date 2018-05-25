package com.hfbh.yuecheng.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.MemberCodeBean;
import com.hfbh.yuecheng.utils.BarcodeUtils;
import com.hfbh.yuecheng.utils.DataManagerUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.QRCodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    private MemberCodeBean memberCodeBean;

    private boolean isShow;

    private Bitmap barBmp;
    private Bitmap qrBmp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_code, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        initView();
        return view;
    }

    private void getData() {
        memberCodeBean = (MemberCodeBean) getArguments().getSerializable("pay_code");
    }

    private void initView() {
        int memberId = memberCodeBean.getData().getMemberId();

        barBmp = BarcodeUtils.creatBarcode(String.valueOf(memberId),
                (int) DisplayUtils.dp2px(getActivity(), 225),
                (int) DisplayUtils.dp2px(getActivity(), 41));
        ivPayBarcode.setImageBitmap(barBmp);

        qrBmp = QRCodeUtils.createQRCode(String.valueOf(memberId),
                (int) DisplayUtils.dp2px(getActivity(), 200));

        ivPayQrcode.setImageBitmap(qrBmp);

        tvPaycodeMoney.setText(String.valueOf(memberCodeBean.getData().getAccountBalance()));
        tvPaycodeScore.setText(String.valueOf(memberCodeBean.getData().getPoints()));
        tvPaycodeTicket.setText(String.valueOf(memberCodeBean.getData().getCouponCount()));
    }

    public static PayCodeFragment newInstance(MemberCodeBean memberCodeBean) {
        Bundle args = new Bundle();
        args.putSerializable("pay_code", memberCodeBean);
        PayCodeFragment fragment = new PayCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @OnClick({R.id.tv_pay_barcode, R.id.ll_paycode_money, R.id.ll_paycode_score, R.id.ll_paycode_ticket})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pay_barcode:
                if (isShow) {
                    tvPayBarcode.setText(String.valueOf(memberCodeBean.getData().getMemberId()));
                    tvPayBarcode.setTextColor(getResources().getColor(R.color.gray_10));
                    isShow = false;
                } else {
                    tvPayBarcode.setText("点击查看数字");
                    tvPayBarcode.setTextColor(getResources().getColor(R.color.blue_a4));
                    isShow = true;
                }
                break;
            case R.id.ll_paycode_money:
                break;
            case R.id.ll_paycode_score:
                break;
            case R.id.ll_paycode_ticket:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        recycleBitmap();
    }


    /**
     * bitmap回收
     */
    private void recycleBitmap() {
        DataManagerUtils.recycleBmp(barBmp);
        DataManagerUtils.recycleBmp(qrBmp);
    }
}