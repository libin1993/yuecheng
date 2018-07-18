package com.hfbh.yuecheng.ui;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.GroupGoodsDetailBean;
import com.hfbh.yuecheng.bean.UserBalanceBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/7/18 15:23
 * Email：1993911441@qq.com
 * Describe：确认订单
 */
public class ConfirmOrderActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.iv_order_shop_logo)
    SimpleDraweeView ivShopLogo;
    @BindView(R.id.tv_order_shop_name)
    TextView tvShopName;
    @BindView(R.id.iv_order_goods_logo)
    SimpleDraweeView ivGoodsLogo;
    @BindView(R.id.tv_order_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_order_goods_new_price)
    TextView tvNewPrice;
    @BindView(R.id.tv_order_goods_old_price)
    TextView tvOldPrice;
    @BindView(R.id.tv_order_goods_reduce)
    TextView tvGoodsReduce;
    @BindView(R.id.tv_order_goods_num)
    TextView tvrGoodsNum;
    @BindView(R.id.tv_order_goods_add)
    TextView tvGoodsAdd;
    @BindView(R.id.tv_order_balance)
    TextView tvBalance;
    @BindView(R.id.et_use_balance)
    EditText etUseBalance;
    @BindView(R.id.tv_use_balance)
    TextView tvUseBalance;
    @BindView(R.id.tv_enroll_money)
    TextView tvNeedMoney;
    @BindView(R.id.tv_confirm_enroll)
    TextView tvConfirmOrder;

    private GroupGoodsDetailBean goodsBean;
    private UserBalanceBean balanceBean;
    //用户余额
    private double balance;
    //商品总价格
    private double totalPrice;
    //使用余额
    private double useBalance;
    private int num = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        getData();
        initBalance();
        initView();
    }

    private void initView() {
        if (goodsBean != null) {
            ivShopLogo.setImageURI(goodsBean.getData().getShopLogo());
            tvShopName.setText(goodsBean.getData().getShopName());
            ivGoodsLogo.setImageURI(goodsBean.getData().getPicturePath());
            tvGoodsName.setText(goodsBean.getData().getCommodityName());
            tvNewPrice.setText("¥" + DisplayUtils.isInteger(goodsBean.getData().getNowPrice()));
            tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
            tvOldPrice.setText("¥" + DisplayUtils.isInteger(goodsBean.getData().getOldPrice()));


            initPrice();
        }
    }

    /**
     * 付款金额
     */
    private void initPrice() {
        if (num == 1) {
            tvGoodsReduce.setTextColor(getResources().getColor(R.color.gray_ee));
            tvGoodsAdd.setTextColor(getResources().getColor(R.color.gray_99));
        } else if (num < goodsBean.getData().getBuyLimitNum() - goodsBean.getData().getBuyNum()) {
            tvGoodsReduce.setTextColor(getResources().getColor(R.color.gray_99));
            tvGoodsAdd.setTextColor(getResources().getColor(R.color.gray_99));
        } else {
            tvGoodsReduce.setTextColor(getResources().getColor(R.color.gray_99));
            tvGoodsAdd.setTextColor(getResources().getColor(R.color.gray_ee));
        }

        totalPrice = goodsBean.getData().getNowPrice() * num;
    }

    /**
     * 余额
     */
    private void initBalance() {
        OkHttpUtils.get()
                .url(Constant.USER_BALANCE)
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
                        balanceBean = GsonUtils.jsonToBean(response, UserBalanceBean.class);
                        if (balanceBean.isFlag() && balanceBean.getData() != null) {
                            balance = balanceBean.getData().getAccountBalance();
                            if (balance > 0) {
                                etUseBalance.setFocusable(true);
                                etUseBalance.setFocusableInTouchMode(true);
                            }
                        }
                    }
                });
    }

    private void getData() {
        goodsBean = (GroupGoodsDetailBean) getIntent().getSerializableExtra("goods");
    }

    @OnClick({R.id.iv_header_back, R.id.tv_order_goods_reduce, R.id.tv_order_goods_add, R.id.tv_confirm_enroll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_order_goods_reduce:
                break;
            case R.id.tv_order_goods_add:
                break;
            case R.id.tv_confirm_enroll:
                break;
        }
    }
}
