package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/8 10:53
 * Email：1993911441@qq.com
 * Describe：
 */
public class MyCouponBean {


    /**
     * flag : true
     * page : null
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : [{"balance":20,"content":null,"couponType":1,"couponTypeKind":null,"couponTypeName":"礼券","couponTypeNameSpecified":true,"gainId":null,"startValidDate":null,"validDate":"2018-05-29","validDateSpecified":true},{"balance":20,"content":null,"couponType":2,"couponTypeKind":null,"couponTypeName":"家电券","couponTypeNameSpecified":true,"gainId":null,"startValidDate":null,"validDate":"2018-05-31","validDateSpecified":true},{"balance":20,"content":null,"couponType":2,"couponTypeKind":null,"couponTypeName":"家电券","couponTypeNameSpecified":true,"gainId":null,"startValidDate":null,"validDate":"2018-06-21","validDateSpecified":true},{"balance":80,"content":null,"couponType":15,"couponTypeKind":null,"couponTypeName":"满额赠","couponTypeNameSpecified":true,"gainId":null,"startValidDate":null,"validDate":"2018-05-29","validDateSpecified":true},{"balance":40,"content":null,"couponType":17,"couponTypeKind":null,"couponTypeName":"测试1","couponTypeNameSpecified":true,"gainId":null,"startValidDate":null,"validDate":"2018-05-29","validDateSpecified":true}]
     * code : 0
     */

    private boolean flag;
    private Object page;
    private String hash;
    private int code;
    private List<DataBean> data;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
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

    public static class DataBean {
        /**
         * balance : 20
         * content : null
         * couponType : 1
         * couponTypeKind : null
         * couponTypeName : 礼券
         * couponTypeNameSpecified : true
         * gainId : null
         * startValidDate : null
         * validDate : 2018-05-29
         * validDateSpecified : true
         */

        private double balance;
        private Object content;
        private int couponType;
        private Object couponTypeKind;
        private String couponTypeName;
        private boolean couponTypeNameSpecified;
        private Object gainId;
        private Object startValidDate;
        private String validDate;
        private boolean validDateSpecified;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public int getCouponType() {
            return couponType;
        }

        public void setCouponType(int couponType) {
            this.couponType = couponType;
        }

        public Object getCouponTypeKind() {
            return couponTypeKind;
        }

        public void setCouponTypeKind(Object couponTypeKind) {
            this.couponTypeKind = couponTypeKind;
        }

        public String getCouponTypeName() {
            return couponTypeName;
        }

        public void setCouponTypeName(String couponTypeName) {
            this.couponTypeName = couponTypeName;
        }

        public boolean isCouponTypeNameSpecified() {
            return couponTypeNameSpecified;
        }

        public void setCouponTypeNameSpecified(boolean couponTypeNameSpecified) {
            this.couponTypeNameSpecified = couponTypeNameSpecified;
        }

        public Object getGainId() {
            return gainId;
        }

        public void setGainId(Object gainId) {
            this.gainId = gainId;
        }

        public Object getStartValidDate() {
            return startValidDate;
        }

        public void setStartValidDate(Object startValidDate) {
            this.startValidDate = startValidDate;
        }

        public String getValidDate() {
            return validDate;
        }

        public void setValidDate(String validDate) {
            this.validDate = validDate;
        }

        public boolean isValidDateSpecified() {
            return validDateSpecified;
        }

        public void setValidDateSpecified(boolean validDateSpecified) {
            this.validDateSpecified = validDateSpecified;
        }
    }
}
