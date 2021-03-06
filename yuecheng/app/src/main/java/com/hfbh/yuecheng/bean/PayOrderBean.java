package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/7/19 14:20
 * Email：1993911441@qq.com
 * Describe：
 */
public class PayOrderBean {

    private String orderNo;  //订单号
    private String orderType;   //订单类型
    private String payType;   //支付方式
    private boolean payResult;  //是否支付成功
    private double orderPrice; //订单金额
    private double money;  //支付金额
    private String goodsType;  //商品类型
    private int limitTime;  //提货时间
    private List<DiscountBean> discountList;

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    private List<OrderInfo> orderList;

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public PayOrderBean(String orderNo, String orderType, String payType, boolean payResult, double orderPrice,
                        double money, List<DiscountBean> discountList, List<OrderInfo> orderList) {
        this.orderNo = orderNo;
        this.orderType = orderType;
        this.payType = payType;
        this.payResult = payResult;
        this.orderPrice = orderPrice;
        this.money = money;
        this.discountList = discountList;
        this.orderList = orderList;
    }

    public PayOrderBean(String orderNo, String orderType, String payType, boolean payResult, double orderPrice, double
            money, String goodsType, int limitTime, List<DiscountBean> discountList, List<OrderInfo> orderList) {
        this.orderNo = orderNo;
        this.orderType = orderType;
        this.payType = payType;
        this.payResult = payResult;
        this.orderPrice = orderPrice;
        this.money = money;
        this.goodsType = goodsType;
        this.limitTime = limitTime;
        this.discountList = discountList;
        this.orderList = orderList;
    }

    public List<OrderInfo> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderInfo> orderList) {
        this.orderList = orderList;
    }


    public PayOrderBean() {
    }

    public List<DiscountBean> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<DiscountBean> discountList) {
        this.discountList = discountList;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getOrderNo() {
        return orderNo;

    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public boolean isPayResult() {
        return payResult;
    }

    public void setPayResult(boolean payResult) {
        this.payResult = payResult;
    }


    public static class DiscountBean {
        private String title;
        private String content;

        public DiscountBean(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class OrderInfo {
        private String title;
        private String content;

        public OrderInfo(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;

        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
