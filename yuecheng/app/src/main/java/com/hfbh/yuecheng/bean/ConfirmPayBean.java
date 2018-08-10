package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/8/9 10:27
 * Email：1993911441@qq.com
 * Describe：
 */
public class ConfirmPayBean {

    /**
     * flag : true
     * hash : 848489fbe87c4e88a44c2e6ad0eb8c68
     * data : {"closeType":null,"dispatchingWay":null,"isDelete":null,"isFrozen":true,"memberId":181166,"memberOrderId":87639,"memberOrderShopId":88146,"orderDtlList":[{"commodityNum":null,"createTime":null,"detailAccount":1,"detailId":888,"detailName":"tututyuiut","detailPicturepath":"http://yjwang.wmalle.com/image/20180521/7404416813894129.jpg","detailPrice":100,"endTime":null,"getTimeLimit":7,"groupNum":null,"isDelete":null,"isMustGroup":null,"isRefund":"N","joinNum":null,"memberOrderDetailId":85083,"memberOrderShopId":88146,"orderNo":null,"originalPrice":1000,"payFrom":null,"refundFrom":null,"refundTime":null,"refundTimeLimit":null,"startTime":null,"state":"NORMAL","thLeftDays":null,"verifyState":"INIT"}],"orderNo":null,"orderShopNumber":"1011027376931131236352","orderType":"SPECIAL","organizeId":null,"payTime":null,"payWay":"WECHAT","pickupCode":"rW3rsDBX","price":100,"refundState":"NORMAL","relationId":1,"relationName":"GXG","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171011094453823.jpg","shopPhone":null,"signinTime":null,"state":null,"sumbitTime":"2018-08-09 10:12:03","useAccountBalance":50,"wechatPay":50}
     * code : 0
     */

    private boolean flag;
    private String hash;
    private DataBean data;
    private int code;

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
         * closeType : null
         * dispatchingWay : null
         * isDelete : null
         * isFrozen : true
         * memberId : 181166
         * memberOrderId : 87639
         * memberOrderShopId : 88146
         * orderDtlList : [{"commodityNum":null,"createTime":null,"detailAccount":1,"detailId":888,"detailName":"tututyuiut","detailPicturepath":"http://yjwang.wmalle.com/image/20180521/7404416813894129.jpg","detailPrice":100,"endTime":null,"getTimeLimit":7,"groupNum":null,"isDelete":null,"isMustGroup":null,"isRefund":"N","joinNum":null,"memberOrderDetailId":85083,"memberOrderShopId":88146,"orderNo":null,"originalPrice":1000,"payFrom":null,"refundFrom":null,"refundTime":null,"refundTimeLimit":null,"startTime":null,"state":"NORMAL","thLeftDays":null,"verifyState":"INIT"}]
         * orderNo : null
         * orderShopNumber : 1011027376931131236352
         * orderType : SPECIAL
         * organizeId : null
         * payTime : null
         * payWay : WECHAT
         * pickupCode : rW3rsDBX
         * price : 100
         * refundState : NORMAL
         * relationId : 1
         * relationName : GXG
         * shopLogo : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171011094453823.jpg
         * shopPhone : null
         * signinTime : null
         * state : null
         * sumbitTime : 2018-08-09 10:12:03
         * useAccountBalance : 50
         * wechatPay : 50
         */

        private Object closeType;
        private Object dispatchingWay;
        private Object isDelete;
        private boolean isFrozen;
        private int memberId;
        private int memberOrderId;
        private int memberOrderShopId;
        private Object orderNo;
        private String orderShopNumber;
        private String orderType;
        private Object organizeId;
        private Object payTime;
        private String payWay;
        private String pickupCode;
        private double price;
        private String refundState;
        private int relationId;
        private String relationName;
        private String shopLogo;
        private Object shopPhone;
        private Object signinTime;
        private Object state;
        private String sumbitTime;
        private double useAccountBalance;
        private double wechatPay;
        private List<OrderDtlListBean> orderDtlList;

        public Object getCloseType() {
            return closeType;
        }

        public void setCloseType(Object closeType) {
            this.closeType = closeType;
        }

        public Object getDispatchingWay() {
            return dispatchingWay;
        }

        public void setDispatchingWay(Object dispatchingWay) {
            this.dispatchingWay = dispatchingWay;
        }

        public Object getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(Object isDelete) {
            this.isDelete = isDelete;
        }

        public boolean isIsFrozen() {
            return isFrozen;
        }

        public void setIsFrozen(boolean isFrozen) {
            this.isFrozen = isFrozen;
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

        public int getMemberOrderShopId() {
            return memberOrderShopId;
        }

        public void setMemberOrderShopId(int memberOrderShopId) {
            this.memberOrderShopId = memberOrderShopId;
        }

        public Object getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Object orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderShopNumber() {
            return orderShopNumber;
        }

        public void setOrderShopNumber(String orderShopNumber) {
            this.orderShopNumber = orderShopNumber;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public Object getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(Object organizeId) {
            this.organizeId = organizeId;
        }

        public Object getPayTime() {
            return payTime;
        }

        public void setPayTime(Object payTime) {
            this.payTime = payTime;
        }

        public String getPayWay() {
            return payWay;
        }

        public void setPayWay(String payWay) {
            this.payWay = payWay;
        }

        public String getPickupCode() {
            return pickupCode;
        }

        public void setPickupCode(String pickupCode) {
            this.pickupCode = pickupCode;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getRefundState() {
            return refundState;
        }

        public void setRefundState(String refundState) {
            this.refundState = refundState;
        }

        public int getRelationId() {
            return relationId;
        }

        public void setRelationId(int relationId) {
            this.relationId = relationId;
        }

        public String getRelationName() {
            return relationName;
        }

        public void setRelationName(String relationName) {
            this.relationName = relationName;
        }

        public String getShopLogo() {
            return shopLogo;
        }

        public void setShopLogo(String shopLogo) {
            this.shopLogo = shopLogo;
        }

        public Object getShopPhone() {
            return shopPhone;
        }

        public void setShopPhone(Object shopPhone) {
            this.shopPhone = shopPhone;
        }

        public Object getSigninTime() {
            return signinTime;
        }

        public void setSigninTime(Object signinTime) {
            this.signinTime = signinTime;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public String getSumbitTime() {
            return sumbitTime;
        }

        public void setSumbitTime(String sumbitTime) {
            this.sumbitTime = sumbitTime;
        }

        public double getUseAccountBalance() {
            return useAccountBalance;
        }

        public void setUseAccountBalance(double useAccountBalance) {
            this.useAccountBalance = useAccountBalance;
        }

        public double getWechatPay() {
            return wechatPay;
        }

        public void setWechatPay(double wechatPay) {
            this.wechatPay = wechatPay;
        }

        public List<OrderDtlListBean> getOrderDtlList() {
            return orderDtlList;
        }

        public void setOrderDtlList(List<OrderDtlListBean> orderDtlList) {
            this.orderDtlList = orderDtlList;
        }

        public static class OrderDtlListBean {
            /**
             * commodityNum : null
             * createTime : null
             * detailAccount : 1
             * detailId : 888
             * detailName : tututyuiut
             * detailPicturepath : http://yjwang.wmalle.com/image/20180521/7404416813894129.jpg
             * detailPrice : 100
             * endTime : null
             * getTimeLimit : 7
             * groupNum : null
             * isDelete : null
             * isMustGroup : null
             * isRefund : N
             * joinNum : null
             * memberOrderDetailId : 85083
             * memberOrderShopId : 88146
             * orderNo : null
             * originalPrice : 1000
             * payFrom : null
             * refundFrom : null
             * refundTime : null
             * refundTimeLimit : null
             * startTime : null
             * state : NORMAL
             * thLeftDays : null
             * verifyState : INIT
             */

            private Object commodityNum;
            private Object createTime;
            private int detailAccount;
            private int detailId;
            private String detailName;
            private String detailPicturepath;
            private double detailPrice;
            private Object endTime;
            private int getTimeLimit;
            private Object groupNum;
            private Object isDelete;
            private Object isMustGroup;
            private String isRefund;
            private Object joinNum;
            private int memberOrderDetailId;
            private int memberOrderShopId;
            private Object orderNo;
            private double originalPrice;
            private Object payFrom;
            private Object refundFrom;
            private Object refundTime;
            private Object refundTimeLimit;
            private Object startTime;
            private String state;
            private Object thLeftDays;
            private String verifyState;

            public Object getCommodityNum() {
                return commodityNum;
            }

            public void setCommodityNum(Object commodityNum) {
                this.commodityNum = commodityNum;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public int getDetailAccount() {
                return detailAccount;
            }

            public void setDetailAccount(int detailAccount) {
                this.detailAccount = detailAccount;
            }

            public int getDetailId() {
                return detailId;
            }

            public void setDetailId(int detailId) {
                this.detailId = detailId;
            }

            public String getDetailName() {
                return detailName;
            }

            public void setDetailName(String detailName) {
                this.detailName = detailName;
            }

            public String getDetailPicturepath() {
                return detailPicturepath;
            }

            public void setDetailPicturepath(String detailPicturepath) {
                this.detailPicturepath = detailPicturepath;
            }

            public double getDetailPrice() {
                return detailPrice;
            }

            public void setDetailPrice(double detailPrice) {
                this.detailPrice = detailPrice;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public int getGetTimeLimit() {
                return getTimeLimit;
            }

            public void setGetTimeLimit(int getTimeLimit) {
                this.getTimeLimit = getTimeLimit;
            }

            public Object getGroupNum() {
                return groupNum;
            }

            public void setGroupNum(Object groupNum) {
                this.groupNum = groupNum;
            }

            public Object getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(Object isDelete) {
                this.isDelete = isDelete;
            }

            public Object getIsMustGroup() {
                return isMustGroup;
            }

            public void setIsMustGroup(Object isMustGroup) {
                this.isMustGroup = isMustGroup;
            }

            public String getIsRefund() {
                return isRefund;
            }

            public void setIsRefund(String isRefund) {
                this.isRefund = isRefund;
            }

            public Object getJoinNum() {
                return joinNum;
            }

            public void setJoinNum(Object joinNum) {
                this.joinNum = joinNum;
            }

            public int getMemberOrderDetailId() {
                return memberOrderDetailId;
            }

            public void setMemberOrderDetailId(int memberOrderDetailId) {
                this.memberOrderDetailId = memberOrderDetailId;
            }

            public int getMemberOrderShopId() {
                return memberOrderShopId;
            }

            public void setMemberOrderShopId(int memberOrderShopId) {
                this.memberOrderShopId = memberOrderShopId;
            }

            public Object getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(Object orderNo) {
                this.orderNo = orderNo;
            }

            public double getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(double originalPrice) {
                this.originalPrice = originalPrice;
            }

            public Object getPayFrom() {
                return payFrom;
            }

            public void setPayFrom(Object payFrom) {
                this.payFrom = payFrom;
            }

            public Object getRefundFrom() {
                return refundFrom;
            }

            public void setRefundFrom(Object refundFrom) {
                this.refundFrom = refundFrom;
            }

            public Object getRefundTime() {
                return refundTime;
            }

            public void setRefundTime(Object refundTime) {
                this.refundTime = refundTime;
            }

            public Object getRefundTimeLimit() {
                return refundTimeLimit;
            }

            public void setRefundTimeLimit(Object refundTimeLimit) {
                this.refundTimeLimit = refundTimeLimit;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public Object getThLeftDays() {
                return thLeftDays;
            }

            public void setThLeftDays(Object thLeftDays) {
                this.thLeftDays = thLeftDays;
            }

            public String getVerifyState() {
                return verifyState;
            }

            public void setVerifyState(String verifyState) {
                this.verifyState = verifyState;
            }
        }
    }
}
