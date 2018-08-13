package com.hfbh.yuecheng.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfbh.yuecheng.R;
import com.hfbh.yuecheng.application.MyApp;
import com.hfbh.yuecheng.base.BaseActivity;
import com.hfbh.yuecheng.bean.BroadMsgBean;
import com.hfbh.yuecheng.bean.ConfirmOrderBean;
import com.hfbh.yuecheng.bean.PayOrderBean;
import com.hfbh.yuecheng.bean.ResponseBean;
import com.hfbh.yuecheng.bean.ScanOrderBean;
import com.hfbh.yuecheng.constant.Constant;
import com.hfbh.yuecheng.utils.BigDecimalUtils;
import com.hfbh.yuecheng.utils.DisplayUtils;
import com.hfbh.yuecheng.utils.GsonUtils;
import com.hfbh.yuecheng.utils.LogUtils;
import com.hfbh.yuecheng.utils.MoneyInputFilter;
import com.hfbh.yuecheng.utils.MyLinearLayoutManager;
import com.hfbh.yuecheng.utils.SharedPreUtils;
import com.hfbh.yuecheng.utils.ToastUtils;
import com.jungly.gridpasswordview.GridPasswordView;
import com.smarttop.library.utils.LogUtil;
import com.wang.avi.AVLoadingIndicatorView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author：Libin on 2018/7/16 13:50
 * Email：1993911441@qq.com
 * Describe：主扫订单
 */
public class ScanOrderActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_order_money)
    TextView tvOrderMoney;
    @BindView(R.id.tv_confirm_order)
    TextView tvConfirmOrder;
    @BindView(R.id.checkbox_order)
    CheckBox cbOrder;
    @BindView(R.id.tv_scan_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_scan_order_shop)
    TextView tvOrderShop;
    @BindView(R.id.tv_scan_order_money)
    TextView tvOrderPrice;
    @BindView(R.id.tv_scan_order_card)
    TextView tvMemberCard;
    @BindView(R.id.tv_order_card_discount)
    TextView tvCardDiscount;
    @BindView(R.id.tv_order_other_discount)
    TextView tvOtherDiscount;
    @BindView(R.id.rv_order_coupon)
    RecyclerView rvCoupon;
    @BindView(R.id.iv_scan_order)
    ImageView ivCoupon;
    @BindView(R.id.tv_order_points_value)
    TextView tvPointsValue;
    @BindView(R.id.tv_user_points)
    TextView tvUserPoints;
    @BindView(R.id.et_use_points)
    EditText etUsePoints;
    @BindView(R.id.tv_use_points)
    TextView tvUsePoints;
    @BindView(R.id.tv_user_balance)
    TextView tvUserBalance;
    @BindView(R.id.et_balance_use)
    EditText etUseBalance;
    @BindView(R.id.tv_balance_use)
    TextView tvUseBalance;
    @BindView(R.id.ll_order_coupon)
    LinearLayout llOrderCoupon;
    @BindView(R.id.rl_scan_order)
    RelativeLayout rlScanOrder;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    //ip
    private String ip;
    //订单号
    private String orderNo;

    private ScanOrderBean orderBean;
    //会员余额
    private double userBalance;
    //使用余额
    private double balanceDiscount;
    //会员积分
    private double userPoints;
    //积分抵扣金额
    private double pointsDiscount;
    //积分抵扣比例
    private double pointsRatio;
    //会员卡
    private double cardDiscount;
    //其他折扣
    private double otherDiscount;
    //订单总价
    private double totalPrice;
    //优惠券优惠总金额
    private double couponDiscount;
    //还需支付金额
    private double needPayMoney;
    //优惠券展开
    private boolean isOpen;

    List<ScanOrderBean.DataBean.CouponlistBean> couponList = new ArrayList<>();
    private CommonAdapter<ScanOrderBean.DataBean.CouponlistBean> adapter;
    private PopupWindow mPopupWindow;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_order);
        ButterKnife.bind(this);
        getData();
        tvHeaderTitle.setText("自助买单");
        etUsePoints.setFocusable(false);
        etUsePoints.setFocusableInTouchMode(false);
        etUseBalance.setFocusable(false);
        etUseBalance.setFocusableInTouchMode(false);
        viewLoading.smoothToShow();
        initData();
    }


    private void initData() {
        OkHttpUtils.post()
                .url(Constant.SCAN_ORDER)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("serverIp", ip)
                .addParams("ordernum", orderNo)
                .addParams("cardno", SharedPreUtils.getStr(this, "card_number"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        viewLoading.smoothToHide();
                        orderBean = GsonUtils.jsonToBean(response, ScanOrderBean.class);
                        if (orderBean.isFlag()) {
                            initView();
                        } else {
                            ToastUtils.showToast(ScanOrderActivity.this, orderBean.getMsg());
                            finish();
                        }
                    }
                });
    }

    private void initView() {

        tvOrderNo.setText(orderNo);
        tvOrderShop.setText(orderBean.getData().getStoreName());
        totalPrice = Double.parseDouble(orderBean.getData().getMoney()) / 100;
        tvOrderPrice.setText("¥" + DisplayUtils.decimalFormat(totalPrice));
        tvMemberCard.setText(orderBean.getData().getCardtypename());

        double totalDiscount = Double.parseDouble(orderBean.getData().getTotaldisc()) / 100;
        cardDiscount = Double.parseDouble(orderBean.getData().getTotalmemdisc()) / 100;
        otherDiscount = totalDiscount - cardDiscount;

        tvCardDiscount.setText("-¥" + DisplayUtils.decimalFormat(cardDiscount));
        tvOtherDiscount.setText("-¥" + DisplayUtils.decimalFormat(otherDiscount));
        if (orderBean.getData().getCouponlist() != null && orderBean.getData().getCouponlist().size() > 0) {

            showMoreCoupon();
            MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(this);
            linearLayoutManager.setScrollEnabled(false);
            rvCoupon.setLayoutManager(linearLayoutManager);
            adapter = new CommonAdapter<ScanOrderBean.DataBean.CouponlistBean>(this,
                    R.layout.rv_order_coupon_item, couponList) {
                @Override
                protected void convert(ViewHolder holder, final ScanOrderBean.DataBean.CouponlistBean
                        couponlistBean, final int position) {

                    ImageView ivCoupon = holder.getView(R.id.iv_select_coupon);

                    holder.setText(R.id.tv_order_coupon_name, couponlistBean.getName());
                    holder.setText(R.id.tv_order_coupon_value, DisplayUtils.isInteger(
                            Double.parseDouble(couponlistBean.getBalance()) / 100));
                    final double usableMoney = Double.parseDouble(couponlistBean.getPayable()) / 100;
                    holder.setText(R.id.tv_coupon_limit_value, "¥" + DisplayUtils.decimalFormat(usableMoney));
                    final EditText etCoupon = holder.getView(R.id.et_use_coupon);


                    //1.根据Tag移除掉监听
                    if (etCoupon.getTag() instanceof TextWatcher) {
                        etCoupon.removeTextChangedListener((TextWatcher) etCoupon.getTag());
                    }
                    //2.set文字
                    if (couponlistBean.getUseCoupon() > 0) {
                        etCoupon.setText(DisplayUtils.isInteger(couponlistBean.getUseCoupon()));
                    } else {
                        etCoupon.setText("");
                    }

                    etCoupon.setEnabled(couponlistBean.isCheck());
                    etCoupon.setFocusable(couponlistBean.isCheck());
                    etCoupon.setFocusableInTouchMode(couponlistBean.isCheck());

                    if (couponlistBean.isCheck()) {
                        ivCoupon.setImageResource(R.mipmap.ic_register_checked);
                    } else {
                        ivCoupon.setImageResource(R.mipmap.ic_register_normal);
                    }

//                    etCoupon.setFilters(new InputFilter[]{new MoneyInputFilter(usableMoney)});

                    //3.新建一个监听
                    TextWatcher watcher = new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (couponlistBean.isCheck()) {
                                double useCoupon = 0;
                                if (!TextUtils.isEmpty(s.toString())) {
                                    //置零
                                    orderBean.getData().getCouponlist().get(position).setUseCoupon(0);
                                    couponlistBean.setUseCoupon(0);

                                    double needPay = needPay();

                                    if (".".equals(s.toString())) {
                                        useCoupon = Math.min(needPay, usableMoney);
                                    } else {
                                        useCoupon = Double.parseDouble(s.toString());
                                    }

                                    if (useCoupon > Math.min(needPay, usableMoney)) {
                                        etCoupon.removeTextChangedListener(this);
                                        useCoupon = Math.min(needPay, usableMoney);
                                        etCoupon.setText(DisplayUtils.isInteger(useCoupon));
                                        etCoupon.setSelection(etCoupon.getText().toString().length());
                                        etCoupon.addTextChangedListener(this);
                                    } else if (useCoupon != (int) useCoupon && String.valueOf(useCoupon).length()
                                            - String.valueOf(useCoupon).indexOf(".") >= 3) {
                                        etCoupon.removeTextChangedListener(this);
                                        useCoupon = Double.parseDouble(DisplayUtils.decimalFormat(useCoupon));
                                        etCoupon.setText(DisplayUtils.isInteger(useCoupon));
                                        etCoupon.setSelection(etCoupon.getText().toString().length());
                                        etCoupon.addTextChangedListener(this);
                                    }

                                }
                                couponlistBean.setUseCoupon(useCoupon);
                                orderBean.getData().getCouponlist().get(position).setUseCoupon(useCoupon);
                                needPay();
                            }
                        }
                    };

                    //4.添加一个监听
                    etCoupon.addTextChangedListener(watcher);
                    //5.设置一个Tag
                    etCoupon.setTag(watcher);

                    ivCoupon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            double money = 0;
                            if (!TextUtils.isEmpty(etCoupon.getText().toString().trim())) {
                                money = Double.parseDouble(etCoupon.getText().toString().trim());
                            }

                            if (couponlistBean.isCheck()) {
                                couponlistBean.setCheck(false);
                                orderBean.getData().getCouponlist().get(position).setCheck(false);
                                couponlistBean.setUseCoupon(0);
                                orderBean.getData().getCouponlist().get(position).setUseCoupon(0);
                            } else {
                                couponlistBean.setCheck(true);
                                orderBean.getData().getCouponlist().get(position).setCheck(true);
                                couponlistBean.setUseCoupon(money);
                                orderBean.getData().getCouponlist().get(position).setUseCoupon(money);
                            }

                            adapter.notifyDataSetChanged();
                            needPay();
                        }
                    });

                }
            };
            rvCoupon.setAdapter(adapter);
        } else {
            llOrderCoupon.setVisibility(View.GONE);
            rvCoupon.setVisibility(View.GONE);
            ivCoupon.setVisibility(View.GONE);
        }


        userBalance = orderBean.getData().getAccountBalance();
        userPoints = orderBean.getData().getPoints();
        if (userBalance > 0) {
            etUseBalance.setEnabled(true);
            etUseBalance.setFocusable(true);
            etUseBalance.setFocusableInTouchMode(true);
        }

        if (userPoints > 0) {
            etUsePoints.setEnabled(true);
            etUsePoints.setFocusable(true);
            etUsePoints.setFocusableInTouchMode(true);
        }


        tvUserBalance.setText("¥" + DisplayUtils.decimalFormat(userBalance));
        tvUserPoints.setText(DisplayUtils.isInteger(userPoints) + "分");

        pointsRatio = orderBean.getData().getExchangeRate();
        tvPointsValue.setText("(" + DisplayUtils.isInteger(1.0 / pointsRatio) + "分=1元)");

//        etUseBalance.setFilters(new InputFilter[]{new MoneyInputFilter(userBalance)});
//        etUsePoints.setFilters(new InputFilter[]{new MoneyInputFilter(userPoints)});


        etUseBalance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    //置零
                    balanceDiscount = 0;
                    double needPay = needPay();

                    if (".".equals(s.toString())) {
                        balanceDiscount = Math.min(needPay, userBalance);
                    } else {
                        balanceDiscount = Double.parseDouble(s.toString());
                    }

                    if (balanceDiscount > Math.min(needPay, userBalance)) {
                        etUseBalance.removeTextChangedListener(this);
                        balanceDiscount = Math.min(needPay, userBalance);
                        etUseBalance.setText(DisplayUtils.isInteger(balanceDiscount));
                        etUseBalance.setSelection(etUseBalance.getText().toString().length());
                        etUseBalance.addTextChangedListener(this);
                    } else if (balanceDiscount != (int) balanceDiscount && String.valueOf(balanceDiscount).length()
                            - String.valueOf(balanceDiscount).indexOf(".") >= 3) {
                        etUseBalance.removeTextChangedListener(this);
                        balanceDiscount = Double.parseDouble(DisplayUtils.decimalFormat(balanceDiscount));
                        etUseBalance.setText(DisplayUtils.isInteger(balanceDiscount));
                        etUseBalance.setSelection(etUseBalance.getText().toString().length());
                        etUseBalance.addTextChangedListener(this);
                    }

                } else {
                    balanceDiscount = 0;
                }

                tvUseBalance.setText("余额实付：-¥" + DisplayUtils.decimalFormat(balanceDiscount));

                needPay();
            }
        });

        etUsePoints.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    //置零
                    pointsDiscount = 0;
                    double needPay = needPay();

                    if (".".equals(s.toString())) {
                        pointsDiscount = Math.min(needPay, userBalance);
                    } else {
                        pointsDiscount = Double.parseDouble(s.toString()) * pointsRatio;
                    }

                    if (pointsDiscount > Math.min(needPay, userPoints * pointsRatio)) {
                        etUsePoints.removeTextChangedListener(this);
                        pointsDiscount = (int) Math.min(needPay, userPoints * pointsRatio);
                        etUsePoints.setText(DisplayUtils.isInteger(pointsDiscount / pointsRatio));
                        etUsePoints.setSelection(etUsePoints.getText().toString().length());
                        etUsePoints.addTextChangedListener(this);
                    }

                } else {
                    pointsDiscount = 0;
                }
                tvUsePoints.setText("积分实际抵扣：-¥" + DisplayUtils.decimalFormat(pointsDiscount));
                needPay();
            }
        });

        cbOrder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbOrder.setChecked(isChecked);
                if (isChecked) {
                    showOrderDetail();
                }
            }
        });

        needPay();
    }

    /**
     * 优惠券展开收缩
     */
    private void showMoreCoupon() {
        if (orderBean.getData().getCouponlist().size() <= 3) {
            couponList.addAll(orderBean.getData().getCouponlist());
            ivCoupon.setVisibility(View.GONE);
        } else {
            int size = 3;
            if (isOpen) {
                size = orderBean.getData().getCouponlist().size();
            }
            ivCoupon.setVisibility(View.VISIBLE);
            for (int i = 0; i < size; i++) {
                couponList.add(orderBean.getData().getCouponlist().get(i));
            }
        }
    }


    /**
     * 还需支付
     */
    private double needPay() {
        couponDiscount = 0;

        if (orderBean.getData().getCouponlist() != null && orderBean.getData().getCouponlist().size() > 0) {
            for (int i = 0; i < orderBean.getData().getCouponlist().size(); i++) {
                if (orderBean.getData().getCouponlist().get(i).isCheck()) {
                    couponDiscount = BigDecimalUtils.add(couponDiscount, orderBean.getData().getCouponlist().get(i).getUseCoupon());
                }
            }
        }

        needPayMoney = BigDecimalUtils.sub(totalPrice, cardDiscount);
        needPayMoney = BigDecimalUtils.sub(needPayMoney, otherDiscount);
        needPayMoney = BigDecimalUtils.sub(needPayMoney, balanceDiscount);
        needPayMoney = BigDecimalUtils.sub(needPayMoney, pointsDiscount);
        needPayMoney = BigDecimalUtils.sub(needPayMoney, couponDiscount);

        tvOrderMoney.setText(DisplayUtils.decimalFormat(needPayMoney));
        return needPayMoney;
    }


    private void getData() {
        Intent intent = getIntent();
        ip = intent.getStringExtra("ip");
        orderNo = intent.getStringExtra("order_no");
    }

    @OnClick({R.id.iv_header_back, R.id.iv_scan_order, R.id.tv_confirm_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.iv_scan_order:
                if (isOpen) {
                    isOpen = false;
                    ivCoupon.setImageResource(R.mipmap.btn_grey_down);
                } else {
                    isOpen = true;
                    ivCoupon.setImageResource(R.mipmap.btn_grey_up);
                }

                couponList.clear();
                showMoreCoupon();
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_confirm_order:
                if (orderBean != null) {
//                    calculatePrice();
                    if (balanceDiscount > 0) {
                        isSetPayPwd();
                    } else {
                        submitOrder();
                    }
                }
                break;
        }
    }


    /**
     * 计算价格
     */
    private void calculatePrice() {

        Map<String, String> map = new HashMap<>();
        map.put("appType", MyApp.appType);
        map.put("appVersion", MyApp.appVersion);
        map.put("organizeId", MyApp.organizeId);
        map.put("hash", SharedPreUtils.getStr(this, "hash"));
        map.put("token", SharedPreUtils.getStr(this, "token"));

        map.put("serverIp", ip);

        List<ConfirmOrderBean.CouponList> list = new ArrayList<>();
        for (int i = 0; i < orderBean.getData().getCouponlist().size(); i++) {
            if (orderBean.getData().getCouponlist().get(i).isCheck()) {
                ConfirmOrderBean.CouponList couponBean = new ConfirmOrderBean.CouponList(
                        orderBean.getData().getCouponlist().get(i).getCouponid(),
                        String.valueOf(DisplayUtils.doubleToInt((orderBean.getData().getCouponlist().get(i).getUseCoupon() * 100))));
                list.add(couponBean);
            }
        }
        ConfirmOrderBean confirmOrderBean = new ConfirmOrderBean(orderNo,
                SharedPreUtils.getStr(this, "card_number"),
                String.valueOf(DisplayUtils.doubleToInt(totalPrice * 100)), list);

        map.put("dataStr", GsonUtils.beanToJson(confirmOrderBean));

        OkHttpUtils.post()
                .url(Constant.SCAN_ORDER_MONEY)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });


    }


    /**
     * 是否设置支付密码
     */
    private void isSetPayPwd() {
        OkHttpUtils.post()
                .url(Constant.IS_SET_PAY_PWD)
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
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                        if (responseBean.isFlag()) {
                            inputPwd();
                        } else {
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(ScanOrderActivity.this, "is_login");
                            } else {
                                setPayPwd();
                            }
                        }
                    }
                });
    }

    /**
     * 设置支付密码
     */
    private void setPayPwd() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setTitle("提示");
        dialog.setMessage("您尚未设置支付密码，是否前去设置？");
        //为“确定”按钮注册监听事件
        dialog.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ScanOrderActivity.this, ValidateActivity.class);
                intent.putExtra("type", "bind");
                startActivity(intent);
            }
        });

        //为“取消”按钮注册监听事件
        dialog.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();
    }


    /**
     * 输入密码弹窗
     */
    private void inputPwd() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.ppw_validate_pwd, null);

        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                (int) DisplayUtils.dp2px(this, 191));
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        DisplayUtils.setBackgroundAlpha(this, true);

        ImageView ivCancel = (ImageView) contentView.findViewById(R.id.iv_cancel_pay);

        TextView tvForget = (TextView) contentView.findViewById(R.id.tv_forget_pay_pwd);


        GridPasswordView pwdView = (GridPasswordView) contentView.findViewById(R.id.et_validate_pay_pwd);


        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanOrderActivity.this, ValidateActivity.class);
                intent.putExtra("type", "reset");
                startActivity(intent);
            }
        });

        pwdView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String s) {

            }

            @Override
            public void onInputFinish(String s) {
                validatePwd(s);
            }
        });


        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                DisplayUtils.setBackgroundAlpha(ScanOrderActivity.this, false);
            }
        });
    }

    /**
     * 密码验证
     */
    private void validatePwd(String validatePwd) {
        OkHttpUtils.post()
                .url(Constant.VALIDATE_PWD)
                .addParams("appType", MyApp.appType)
                .addParams("appVersion", MyApp.appVersion)
                .addParams("organizeId", MyApp.organizeId)
                .addParams("hash", SharedPreUtils.getStr(this, "hash"))
                .addParams("token", SharedPreUtils.getStr(this, "token"))
                .addParams("payPassword", validatePwd)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResponseBean responseBean = GsonUtils.jsonToBean(response, ResponseBean.class);
                        if (responseBean.isFlag()) {
                            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                                mPopupWindow.dismiss();
                            }
                            submitOrder();

                        } else {
                            ToastUtils.showToast(ScanOrderActivity.this, "密码错误");
                            if (responseBean.getCode() == 4002) {
                                SharedPreUtils.deleteStr(ScanOrderActivity.this, "is_login");
                            }
                        }
                    }
                });
    }

    /**
     * 提交订单
     */
    private void submitOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("appType", MyApp.appType);
        map.put("appVersion", MyApp.appVersion);
        map.put("organizeId", MyApp.organizeId);
        map.put("hash", SharedPreUtils.getStr(this, "hash"));
        map.put("token", SharedPreUtils.getStr(this, "token"));
        if (needPayMoney > 0) {
            map.put("existOtherPay", "Y");
        } else {
            map.put("existOtherPay", "N");
        }
        map.put("serverIp", ip);
        map.put("cashCardVal", String.valueOf(DisplayUtils.doubleToInt((balanceDiscount * 100))));
        if (pointsDiscount > 0) {
            map.put("scoreVal", String.valueOf(DisplayUtils.doubleToInt((pointsDiscount / pointsRatio))));
        }


        List<ConfirmOrderBean.CouponList> list = new ArrayList<>();
        for (int i = 0; i < orderBean.getData().getCouponlist().size(); i++) {
            if (orderBean.getData().getCouponlist().get(i).isCheck()) {
                ConfirmOrderBean.CouponList couponBean = new ConfirmOrderBean.CouponList(
                        orderBean.getData().getCouponlist().get(i).getCouponid(),
                        String.valueOf(DisplayUtils.doubleToInt((orderBean.getData().getCouponlist().get(i).getUseCoupon() * 100))));
                list.add(couponBean);
            }
        }
        ConfirmOrderBean confirmOrderBean = new ConfirmOrderBean(orderNo,
                SharedPreUtils.getStr(this, "card_number"),
                String.valueOf(DisplayUtils.doubleToInt((totalPrice * 100))),
                list);

        map.put("dataStr", GsonUtils.beanToJson(confirmOrderBean));


        OkHttpUtils.post()
                .url(Constant.SCAN_ORDER_SUBMIT)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean flag = jsonObject.getBoolean("flag");
                            if (flag) {

                                List<PayOrderBean.DiscountBean> discountBeans = new ArrayList<>();
                                discountBeans.add(new PayOrderBean.DiscountBean("订单金额",
                                        "¥" + DisplayUtils.decimalFormat(totalPrice)));
                                discountBeans.add(new PayOrderBean.DiscountBean("会员折扣",
                                        "-¥" + DisplayUtils.decimalFormat(cardDiscount)));
                                discountBeans.add(new PayOrderBean.DiscountBean("其他折扣",
                                        "-¥" + DisplayUtils.decimalFormat(otherDiscount)));
                                discountBeans.add(new PayOrderBean.DiscountBean("电子券减免",
                                        "-¥" + DisplayUtils.decimalFormat(couponDiscount)));
                                discountBeans.add(new PayOrderBean.DiscountBean("积分抵扣",
                                        "-¥" + DisplayUtils.decimalFormat(pointsDiscount)));
                                discountBeans.add(new PayOrderBean.DiscountBean("余额抵扣",
                                        "-¥" + DisplayUtils.decimalFormat(balanceDiscount)));


                                List<PayOrderBean.OrderInfo> orderInfoList = new ArrayList<>();
                                orderInfoList.add(new PayOrderBean.OrderInfo("订单号", orderNo));
                                orderInfoList.add(new PayOrderBean.OrderInfo("支付商家", orderBean.getData().getStoreName()));
                                String payType = "主扫，";
                                if (needPayMoney == 0) {
                                    if (couponDiscount > 0 && pointsDiscount > 0) {
                                        payType += "余额、积分支付";
                                    } else {
                                        if (couponDiscount > 0) {
                                            payType += "余额支付";
                                        }

                                        if (pointsDiscount > 0) {
                                            payType += "积分支付";
                                        }
                                    }
                                }

                                MyApp.orderBean = new PayOrderBean(orderNo, "SCANCODE",
                                        payType, false, totalPrice, needPayMoney, discountBeans, orderInfoList);

                                if (needPayMoney > 0) {
                                    startActivity(new Intent(ScanOrderActivity.this, ConfirmPayActivity.class));
                                } else {
                                    startActivity(new Intent(ScanOrderActivity.this, PayResultActivity.class));
                                }
                                finish();
                            } else {
                                String msg = jsonObject.getString("msg");
                                ToastUtils.showToast(ScanOrderActivity.this, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    /**
     * 订单明细
     */
    private void showOrderDetail() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.ppw_order_detail, null);
        int widthPixels = DisplayUtils.getMetrics(this).widthPixels;
        PopupWindow mPopupWindow = new PopupWindow(contentView, (int) (widthPixels
                - DisplayUtils.dp2px(this, 24)), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, (int) DisplayUtils.dp2px(this, 60));
        DisplayUtils.setBackgroundAlpha(this, true);


        TextView tvTotalPrice = (TextView) contentView.findViewById(R.id.tv_ppw_order_money);
        TextView tvCard = (TextView) contentView.findViewById(R.id.tv_ppw_member_discount);
        TextView tvOther = (TextView) contentView.findViewById(R.id.tv_ppw_other_discount);
        TextView tvCoupon = (TextView) contentView.findViewById(R.id.tv_ppw_coupon);
        TextView tvPoints = (TextView) contentView.findViewById(R.id.tv_ppw_points);
        TextView tvBalance = (TextView) contentView.findViewById(R.id.tv_ppw_balance);
        TextView tvNeedPay = (TextView) contentView.findViewById(R.id.tv_ppw_need_pay);


        tvTotalPrice.setText("¥" + DisplayUtils.decimalFormat(totalPrice));
        tvCard.setText("-¥" + DisplayUtils.decimalFormat(cardDiscount));
        tvOther.setText("-¥" + DisplayUtils.decimalFormat(otherDiscount));
        tvCoupon.setText("-¥" + DisplayUtils.decimalFormat(couponDiscount));
        tvPoints.setText("-¥" + DisplayUtils.decimalFormat(pointsDiscount));
        tvBalance.setText("-¥" + DisplayUtils.decimalFormat(balanceDiscount));
        tvNeedPay.setText("¥" + DisplayUtils.decimalFormat(needPayMoney));


        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                DisplayUtils.setBackgroundAlpha(ScanOrderActivity.this, false);
                cbOrder.setChecked(false);
            }
        });
    }


}
