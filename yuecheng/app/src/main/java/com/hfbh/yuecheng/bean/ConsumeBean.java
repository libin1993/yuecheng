package com.hfbh.yuecheng.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author：Libin on 2018/7/13 09:21
 * Email：1993911441@qq.com
 * Describe：
 */
public class ConsumeBean implements Serializable{

    private static final long serialVersionUID = -6600010983115166099L;
    /**
     * flag : true
     * hash : 5db5a630a7c1487f99b1a79d4aff7a55
     * data : [{"orderNo":"NBH18180100235","orderShopName":"18蚌埠百大","orderTime":"2018-06-26 11:28:11","salesList":[{"billId":35,"gainedCent":-0.005,"posId":"1801002","posIdSpecified":true,"productName":"奥康","productNameSpecified":true,"saleMoney":-0.01,"saleTime":"2018-06-26 11:28:11","saleTimeSpecified":true,"storeCode":"NBH18","storeCodeSpecified":true,"storeName":"18蚌埠百大","storeNameSpecified":true}],"totalMoney":"-0.01"},{"orderNo":"NBH18180100234","orderShopName":"18蚌埠百大","orderTime":"2018-06-26 11:21:21","salesList":[{"billId":34,"gainedCent":-0.005,"posId":"1801002","posIdSpecified":true,"productName":"奥康","productNameSpecified":true,"saleMoney":-0.01,"saleTime":"2018-06-26 11:21:21","saleTimeSpecified":true,"storeCode":"NBH18","storeCodeSpecified":true,"storeName":"18蚌埠百大","storeNameSpecified":true}],"totalMoney":"-0.01"},{"orderNo":"NBH18180100233","orderShopName":"18蚌埠百大","orderTime":"2018-06-26 11:19:15","salesList":[{"billId":33,"gainedCent":0.005,"posId":"1801002","posIdSpecified":true,"productName":"奥康","productNameSpecified":true,"saleMoney":0.01,"saleTime":"2018-06-26 11:19:15","saleTimeSpecified":true,"storeCode":"NBH18","storeCodeSpecified":true,"storeName":"18蚌埠百大","storeNameSpecified":true}],"totalMoney":"0.01"},{"orderNo":"NBH18180100231","orderShopName":"18蚌埠百大","orderTime":"2018-06-26 11:13:23","salesList":[{"billId":31,"gainedCent":-0.5,"posId":"1801002","posIdSpecified":true,"productName":"红蜻蜓","productNameSpecified":true,"saleMoney":-1,"saleTime":"2018-06-26 11:13:23","saleTimeSpecified":true,"storeCode":"NBH18","storeCodeSpecified":true,"storeName":"18蚌埠百大","storeNameSpecified":true}],"totalMoney":"-1.0"},{"orderNo":"NBH18180100230","orderShopName":"18蚌埠百大","orderTime":"2018-06-26 11:08:55","salesList":[{"billId":30,"gainedCent":0.5,"posId":"1801002","posIdSpecified":true,"productName":"红蜻蜓","productNameSpecified":true,"saleMoney":1,"saleTime":"2018-06-26 11:08:55","saleTimeSpecified":true,"storeCode":"NBH18","storeCodeSpecified":true,"storeName":"18蚌埠百大","storeNameSpecified":true}],"totalMoney":"1.0"},{"orderNo":"NBH18180100222","orderShopName":"18蚌埠百大","orderTime":"2018-06-26 10:52:54","salesList":[{"billId":22,"gainedCent":0.005,"posId":"1801002","posIdSpecified":true,"productName":"奥康","productNameSpecified":true,"saleMoney":0.01,"saleTime":"2018-06-26 10:52:54","saleTimeSpecified":true,"storeCode":"NBH18","storeCodeSpecified":true,"storeName":"18蚌埠百大","storeNameSpecified":true}],"totalMoney":"0.01"}]
     * code : 0
     */

    private boolean flag;
    private String hash;
    private int code;
    private List<DataBean> data;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private static final long serialVersionUID = 7092306334383081663L;
        /**
         * orderNo : NBH18180100235
         * orderShopName : 18蚌埠百大
         * orderTime : 2018-06-26 11:28:11
         * salesList : [{"billId":35,"gainedCent":-0.005,"posId":"1801002","posIdSpecified":true,"productName":"奥康","productNameSpecified":true,"saleMoney":-0.01,"saleTime":"2018-06-26 11:28:11","saleTimeSpecified":true,"storeCode":"NBH18","storeCodeSpecified":true,"storeName":"18蚌埠百大","storeNameSpecified":true}]
         * totalMoney : -0.01
         */

        private String orderNo;
        private String orderShopName;
        private String orderTime;
        private String totalMoney;
        private List<SalesListBean> salesList;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderShopName() {
            return orderShopName;
        }

        public void setOrderShopName(String orderShopName) {
            this.orderShopName = orderShopName;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        public List<SalesListBean> getSalesList() {
            return salesList;
        }

        public void setSalesList(List<SalesListBean> salesList) {
            this.salesList = salesList;
        }

        public static class SalesListBean implements Serializable{
            private static final long serialVersionUID = -2745273630633185848L;
            /**
             * billId : 35
             * gainedCent : -0.005
             * posId : 1801002
             * posIdSpecified : true
             * productName : 奥康
             * productNameSpecified : true
             * saleMoney : -0.01
             * saleTime : 2018-06-26 11:28:11
             * saleTimeSpecified : true
             * storeCode : NBH18
             * storeCodeSpecified : true
             * storeName : 18蚌埠百大
             * storeNameSpecified : true
             */

            private int billId;
            private double gainedCent;
            private String posId;
            private boolean posIdSpecified;
            private String productName;
            private boolean productNameSpecified;
            private double saleMoney;
            private String saleTime;
            private boolean saleTimeSpecified;
            private String storeCode;
            private boolean storeCodeSpecified;
            private String storeName;
            private boolean storeNameSpecified;

            public int getBillId() {
                return billId;
            }

            public void setBillId(int billId) {
                this.billId = billId;
            }

            public double getGainedCent() {
                return gainedCent;
            }

            public void setGainedCent(double gainedCent) {
                this.gainedCent = gainedCent;
            }

            public String getPosId() {
                return posId;
            }

            public void setPosId(String posId) {
                this.posId = posId;
            }

            public boolean isPosIdSpecified() {
                return posIdSpecified;
            }

            public void setPosIdSpecified(boolean posIdSpecified) {
                this.posIdSpecified = posIdSpecified;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public boolean isProductNameSpecified() {
                return productNameSpecified;
            }

            public void setProductNameSpecified(boolean productNameSpecified) {
                this.productNameSpecified = productNameSpecified;
            }

            public double getSaleMoney() {
                return saleMoney;
            }

            public void setSaleMoney(double saleMoney) {
                this.saleMoney = saleMoney;
            }

            public String getSaleTime() {
                return saleTime;
            }

            public void setSaleTime(String saleTime) {
                this.saleTime = saleTime;
            }

            public boolean isSaleTimeSpecified() {
                return saleTimeSpecified;
            }

            public void setSaleTimeSpecified(boolean saleTimeSpecified) {
                this.saleTimeSpecified = saleTimeSpecified;
            }

            public String getStoreCode() {
                return storeCode;
            }

            public void setStoreCode(String storeCode) {
                this.storeCode = storeCode;
            }

            public boolean isStoreCodeSpecified() {
                return storeCodeSpecified;
            }

            public void setStoreCodeSpecified(boolean storeCodeSpecified) {
                this.storeCodeSpecified = storeCodeSpecified;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public boolean isStoreNameSpecified() {
                return storeNameSpecified;
            }

            public void setStoreNameSpecified(boolean storeNameSpecified) {
                this.storeNameSpecified = storeNameSpecified;
            }
        }
    }
}
