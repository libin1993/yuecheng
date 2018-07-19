package com.hfbh.yuecheng.bean;

import java.io.Serializable;

/**
 * Author：Libin on 2018/7/19 14:20
 * Email：1993911441@qq.com
 * Describe：
 */
public class PayOrderBean {
    private String orderNo;
    private String orderType;
    private String orderName;
    private String payType;
    private boolean payResult;
    private double money;

    public PayOrderBean(String orderNo, String orderType, String orderName, String payType, boolean payResult,double money) {
        this.orderNo = orderNo;
        this.orderType = orderType;
        this.orderName = orderName;
        this.payType = payType;
        this.payResult = payResult;
        this.money = money;
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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
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
}
