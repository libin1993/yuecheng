package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/7/19 16:19
 * Email：1993911441@qq.com
 * Describe：
 */
public class GoodsOrderBean {

    /**
     * flag : true
     * hash : e917421db5b54348b094ea0b0c2c40b6
     * data : {"allPrice":0.02,"count":1,"couponId":null,"createTime":"2018-07-19 16:17:59","creatorId":null,"isDelete":"N","memberId":182265,"memberOrderId":87346,"modifierId":null,"modifyTime":null,"orderNumber":"1011019858877820313600","orderState":"UNPAID","organizeId":2,"useAccountBalance":0,"wechatPay":0.02}
     * code : 0
     */

    private boolean flag;
    private String hash;
    private DataBean data;
    private int code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * allPrice : 0.02
         * count : 1
         * couponId : null
         * createTime : 2018-07-19 16:17:59
         * creatorId : null
         * isDelete : N
         * memberId : 182265
         * memberOrderId : 87346
         * modifierId : null
         * modifyTime : null
         * orderNumber : 1011019858877820313600
         * orderState : UNPAID
         * organizeId : 2
         * useAccountBalance : 0
         * wechatPay : 0.02
         */

        private double allPrice;
        private int count;
        private Object couponId;
        private String createTime;
        private Object creatorId;
        private String isDelete;
        private int memberId;
        private int memberOrderId;
        private Object modifierId;
        private Object modifyTime;
        private String orderNumber;
        private String orderState;
        private int organizeId;
        private int useAccountBalance;
        private double wechatPay;

        public double getAllPrice() {
            return allPrice;
        }

        public void setAllPrice(double allPrice) {
            this.allPrice = allPrice;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Object getCouponId() {
            return couponId;
        }

        public void setCouponId(Object couponId) {
            this.couponId = couponId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(Object creatorId) {
            this.creatorId = creatorId;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getMemberOrderId() {
            return memberOrderId;
        }

        public void setMemberOrderId(int memberOrderId) {
            this.memberOrderId = memberOrderId;
        }

        public Object getModifierId() {
            return modifierId;
        }

        public void setModifierId(Object modifierId) {
            this.modifierId = modifierId;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getOrderState() {
            return orderState;
        }

        public void setOrderState(String orderState) {
            this.orderState = orderState;
        }

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
            this.organizeId = organizeId;
        }

        public int getUseAccountBalance() {
            return useAccountBalance;
        }

        public void setUseAccountBalance(int useAccountBalance) {
            this.useAccountBalance = useAccountBalance;
        }

        public double getWechatPay() {
            return wechatPay;
        }

        public void setWechatPay(double wechatPay) {
            this.wechatPay = wechatPay;
        }
    }
}
