package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/7/23 17:32
 * Email：1993911441@qq.com
 * Describe：
 */
public class OrderDiscountBean {
    private String ordernum;
    private String totalmoney;
    private List<CouponList> couponList;

    public OrderDiscountBean(String ordernum, String totalmoney, List<CouponList>  couponList) {
        this.ordernum = ordernum;
        this.totalmoney = totalmoney;
        this.couponList = couponList;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(String totalmoney) {
        this.totalmoney = totalmoney;
    }

    public List<CouponList>  getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponList>  couponList) {
        this.couponList = couponList;
    }

    public static class CouponList {
        private String couponid;
        private String paymoney;

        public CouponList(String couponid, String paymoney) {
            this.couponid = couponid;
            this.paymoney = paymoney;
        }

        public String getCouponid() {
            return couponid;
        }

        public void setCouponid(String couponid) {
            this.couponid = couponid;
        }

        public String getPaymoney() {
            return paymoney;
        }

        public void setPaymoney(String paymoney) {
            this.paymoney = paymoney;
        }
    }
}
