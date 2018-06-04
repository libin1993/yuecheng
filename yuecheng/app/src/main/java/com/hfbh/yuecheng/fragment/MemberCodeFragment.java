package com.hfbh.yuecheng.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.base.BaseFragment;
import com.hfbh.yuecheng.bean.UserInfoBean;
import com.hfbh.yuecheng.utils.BarcodeUtils;
import com.hfbh.yuecheng.utils.DataManagerUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.QRCodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：Libin on 2018/5/23 16:20
 * Email：1993911441@qq.com
 * Describe：会员码
 */
public class MemberCodeFragment extends BaseFragment {
    @BindView(R.id.iv_member_barcode)
    ImageView ivMemberBarcode;
    @BindView(R.id.iv_member_qrcode)
    ImageView ivMemberQrcode;
    @BindView(R.id.tv_member_qrcode)
    TextView tvMemberQrcode;
    private Unbinder unbinder;
    private UserInfoBean memberCodeBean;
    private Bitmap barBmp;
    private Bitmap qrBmp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_code, container, false);
        unbinder = ButterKnife.bind(this, view);
        getData();
        initView();
        return view;
    }

    private void getData() {
        memberCodeBean = (UserInfoBean) getArguments().getSerializable("member_code");
    }

    private void initView() {
        String cardNumber = memberCodeBean.getData().getCardNumber();
        barBmp = BarcodeUtils.creatBarcode(cardNumber,
                (int) DisplayUtils.dp2px(getActivity(), 225),
                (int) DisplayUtils.dp2px(getActivity(), 41));
        ivMemberBarcode.setImageBitmap(barBmp);

        qrBmp = QRCodeUtils.createQRCode(cardNumber,
                (int) DisplayUtils.dp2px(getActivity(), 200));
        ivMemberQrcode.setImageBitmap(qrBmp);
        tvMemberQrcode.setText("卡号：" + cardNumber);
    }

    public static MemberCodeFragment newInstance(UserInfoBean userInfoBean) {
        Bundle args = new Bundle();
        args.putSerializable("member_code", userInfoBean);
        MemberCodeFragment fragment = new MemberCodeFragment();
        fragment.setArguments(args);
        return fragment;
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
