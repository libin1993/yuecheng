package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/7/17 13:38
 * Email：1993911441@qq.com
 * Describe：
 */
public class EnrollOrderBean {

    /**
     * flag : true
     * hash : 848489fbe87c4e88a44c2e6ad0eb8c68
     * data : {"order":{"activitySignupPayId":512,"createTime":"2018-07-17 13:37:46","data":null,"enrollFee":0.01,"marketingActivitySignupId":754,"memberId":181166,"mobile":"13333333333","openId":"848489fbe87c4e88a44c2e6ad0eb8c68","organizeId":2,"payTime":null,"realname":"1","state":0,"tradeNo":null,"tranNo":"6001019093782778093568","userCode":null,"wechatTradeNo":null}}
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
         * order : {"activitySignupPayId":512,"createTime":"2018-07-17 13:37:46","data":null,"enrollFee":0.01,"marketingActivitySignupId":754,"memberId":181166,"mobile":"13333333333","openId":"848489fbe87c4e88a44c2e6ad0eb8c68","organizeId":2,"payTime":null,"realname":"1","state":0,"tradeNo":null,"tranNo":"6001019093782778093568","userCode":null,"wechatTradeNo":null}
         */

        private OrderBean order;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public static class OrderBean {
            /**
             * activitySignupPayId : 512
             * createTime : 2018-07-17 13:37:46
             * data : null
             * enrollFee : 0.01
             * marketingActivitySignupId : 754
             * memberId : 181166
             * mobile : 13333333333
             * openId : 848489fbe87c4e88a44c2e6ad0eb8c68
             * organizeId : 2
             * payTime : null
             * realname : 1
             * state : 0
             * tradeNo : null
             * tranNo : 6001019093782778093568
             * userCode : null
             * wechatTradeNo : null
             */

            private int activitySignupPayId;
            private String createTime;
            private Object data;
            private double enrollFee;
            private int marketingActivitySignupId;
            private int memberId;
            private String mobile;
            private String openId;
            private int organizeId;
            private Object payTime;
            private String realname;
            private int state;
            private Object tradeNo;
            private String tranNo;
            private Object userCode;
            private Object wechatTradeNo;

            public int getActivitySignupPayId() {
                return activitySignupPayId;
            }

            public void setActivitySignupPayId(int activitySignupPayId) {
                this.activitySignupPayId = activitySignupPayId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getData() {
                return data;
            }

            public void setData(Object data) {
                this.data = data;
            }

            public double getEnrollFee() {
                return enrollFee;
            }

            public void setEnrollFee(double enrollFee) {
                this.enrollFee = enrollFee;
            }

            public int getMarketingActivitySignupId() {
                return marketingActivitySignupId;
            }

            public void setMarketingActivitySignupId(int marketingActivitySignupId) {
                this.marketingActivitySignupId = marketingActivitySignupId;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getOpenId() {
                return openId;
            }

            public void setOpenId(String openId) {
                this.openId = openId;
            }

            public int getOrganizeId() {
                return organizeId;
            }

            public void setOrganizeId(int organizeId) {
                this.organizeId = organizeId;
            }

            public Object getPayTime() {
                return payTime;
            }

            public void setPayTime(Object payTime) {
                this.payTime = payTime;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public Object getTradeNo() {
                return tradeNo;
            }

            public void setTradeNo(Object tradeNo) {
                this.tradeNo = tradeNo;
            }

            public String getTranNo() {
                return tranNo;
            }

            public void setTranNo(String tranNo) {
                this.tranNo = tranNo;
            }

            public Object getUserCode() {
                return userCode;
            }

            public void setUserCode(Object userCode) {
                this.userCode = userCode;
            }

            public Object getWechatTradeNo() {
                return wechatTradeNo;
            }

            public void setWechatTradeNo(Object wechatTradeNo) {
                this.wechatTradeNo = wechatTradeNo;
            }
        }
    }
}
