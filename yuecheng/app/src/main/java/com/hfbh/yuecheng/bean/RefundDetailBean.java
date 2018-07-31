package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/7/30 10:48
 * Email：1993911441@qq.com
 * Describe：
 */
public class RefundDetailBean {

    /**
     * flag : true
     * data : {"applyTime":"2018-07-25 00:00:02","applyTimeBegin":"2018-07-25 00:00:02","applyTimeEnd":null,"commodityName":null,"createTime":"2018-07-25 00:00:02","createTimeBegin":null,"createTimeEnd":null,"creatorId":null,"dealAmount":1,"detailAccount":1,"detailPicturePath":null,"detailPrice":null,"isDelete":"N","memberId":181166,"memberOrderDetailId":84809,"memberRefundApplyId":1130,"mobilePhone":null,"modifierId":null,"modifyTime":null,"modifyTimeBegin":null,"modifyTimeEnd":null,"openId":"848489fbe87c4e88a44c2e6ad0eb8c68","orderNo":null,"organizeId":2,"originalPrice":null,"refundAmount":1,"refundApplyDesc":"团购失败自动退款","refundApplyNumber":"1031021787093321523200","refundApplyPic":null,"refundApplyType":"REFUND","refundCause":"团购失败自动退款","refundDeal":null,"refundDealDesc":null,"refundDealManagerId":null,"refundDealTime":"2018-07-25 00:00:02","refundDealTimeBegin":"2018-07-25 00:00:02","refundDealTimeEnd":null,"refundState":"SUCCESS","refundType":"GROUP_FAIL_SYS_AUTO","shopName":"红蜻蜓","useAccountBalance":1,"wechatPay":0}
     * code : 0
     */

    private boolean flag;
    private DataBean data;
    private int code;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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
         * applyTime : 2018-07-25 00:00:02
         * applyTimeBegin : 2018-07-25 00:00:02
         * applyTimeEnd : null
         * commodityName : null
         * createTime : 2018-07-25 00:00:02
         * createTimeBegin : null
         * createTimeEnd : null
         * creatorId : null
         * dealAmount : 1
         * detailAccount : 1
         * detailPicturePath : null
         * detailPrice : null
         * isDelete : N
         * memberId : 181166
         * memberOrderDetailId : 84809
         * memberRefundApplyId : 1130
         * mobilePhone : null
         * modifierId : null
         * modifyTime : null
         * modifyTimeBegin : null
         * modifyTimeEnd : null
         * openId : 848489fbe87c4e88a44c2e6ad0eb8c68
         * orderNo : null
         * organizeId : 2
         * originalPrice : null
         * refundAmount : 1
         * refundApplyDesc : 团购失败自动退款
         * refundApplyNumber : 1031021787093321523200
         * refundApplyPic : null
         * refundApplyType : REFUND
         * refundCause : 团购失败自动退款
         * refundDeal : null
         * refundDealDesc : null
         * refundDealManagerId : null
         * refundDealTime : 2018-07-25 00:00:02
         * refundDealTimeBegin : 2018-07-25 00:00:02
         * refundDealTimeEnd : null
         * refundState : SUCCESS
         * refundType : GROUP_FAIL_SYS_AUTO
         * shopName : 红蜻蜓
         * useAccountBalance : 1
         * wechatPay : 0
         */

        private String applyTime;
        private String applyTimeBegin;
        private Object applyTimeEnd;
        private Object commodityName;
        private String createTime;
        private Object createTimeBegin;
        private Object createTimeEnd;
        private Object creatorId;
        private double dealAmount;
        private int detailAccount;
        private Object detailPicturePath;
        private Object detailPrice;
        private String isDelete;
        private int memberId;
        private int memberOrderDetailId;
        private int memberRefundApplyId;
        private Object mobilePhone;
        private Object modifierId;
        private Object modifyTime;
        private Object modifyTimeBegin;
        private Object modifyTimeEnd;
        private String openId;
        private Object orderNo;
        private int organizeId;
        private Object originalPrice;
        private double refundAmount;
        private String refundApplyDesc;
        private String refundApplyNumber;
        private String refundApplyPic;
        private String refundApplyType;
        private String refundCause;
        private String refundDeal;
        private String refundDealDesc;
        private Object refundDealManagerId;
        private String refundDealTime;
        private String refundDealTimeBegin;
        private Object refundDealTimeEnd;
        private String refundState;
        private String refundType;
        private String shopName;
        private double useAccountBalance;
        private double wechatPay;

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public String getApplyTimeBegin() {
            return applyTimeBegin;
        }

        public void setApplyTimeBegin(String applyTimeBegin) {
            this.applyTimeBegin = applyTimeBegin;
        }

        public Object getApplyTimeEnd() {
            return applyTimeEnd;
        }

        public void setApplyTimeEnd(Object applyTimeEnd) {
            this.applyTimeEnd = applyTimeEnd;
        }

        public Object getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(Object commodityName) {
            this.commodityName = commodityName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getCreateTimeBegin() {
            return createTimeBegin;
        }

        public void setCreateTimeBegin(Object createTimeBegin) {
            this.createTimeBegin = createTimeBegin;
        }

        public Object getCreateTimeEnd() {
            return createTimeEnd;
        }

        public void setCreateTimeEnd(Object createTimeEnd) {
            this.createTimeEnd = createTimeEnd;
        }

        public Object getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(Object creatorId) {
            this.creatorId = creatorId;
        }

        public double getDealAmount() {
            return dealAmount;
        }

        public void setDealAmount(double dealAmount) {
            this.dealAmount = dealAmount;
        }

        public int getDetailAccount() {
            return detailAccount;
        }

        public void setDetailAccount(int detailAccount) {
            this.detailAccount = detailAccount;
        }

        public Object getDetailPicturePath() {
            return detailPicturePath;
        }

        public void setDetailPicturePath(Object detailPicturePath) {
            this.detailPicturePath = detailPicturePath;
        }

        public Object getDetailPrice() {
            return detailPrice;
        }

        public void setDetailPrice(Object detailPrice) {
            this.detailPrice = detailPrice;
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

        public int getMemberOrderDetailId() {
            return memberOrderDetailId;
        }

        public void setMemberOrderDetailId(int memberOrderDetailId) {
            this.memberOrderDetailId = memberOrderDetailId;
        }

        public int getMemberRefundApplyId() {
            return memberRefundApplyId;
        }

        public void setMemberRefundApplyId(int memberRefundApplyId) {
            this.memberRefundApplyId = memberRefundApplyId;
        }

        public Object getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(Object mobilePhone) {
            this.mobilePhone = mobilePhone;
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

        public Object getModifyTimeBegin() {
            return modifyTimeBegin;
        }

        public void setModifyTimeBegin(Object modifyTimeBegin) {
            this.modifyTimeBegin = modifyTimeBegin;
        }

        public Object getModifyTimeEnd() {
            return modifyTimeEnd;
        }

        public void setModifyTimeEnd(Object modifyTimeEnd) {
            this.modifyTimeEnd = modifyTimeEnd;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public Object getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Object orderNo) {
            this.orderNo = orderNo;
        }

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
            this.organizeId = organizeId;
        }

        public Object getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(Object originalPrice) {
            this.originalPrice = originalPrice;
        }

        public double getRefundAmount() {
            return refundAmount;
        }

        public void setRefundAmount(double refundAmount) {
            this.refundAmount = refundAmount;
        }

        public String getRefundApplyDesc() {
            return refundApplyDesc;
        }

        public void setRefundApplyDesc(String refundApplyDesc) {
            this.refundApplyDesc = refundApplyDesc;
        }

        public String getRefundApplyNumber() {
            return refundApplyNumber;
        }

        public void setRefundApplyNumber(String refundApplyNumber) {
            this.refundApplyNumber = refundApplyNumber;
        }

        public String getRefundApplyPic() {
            return refundApplyPic;
        }

        public void setRefundApplyPic(String refundApplyPic) {
            this.refundApplyPic = refundApplyPic;
        }

        public String getRefundApplyType() {
            return refundApplyType;
        }

        public void setRefundApplyType(String refundApplyType) {
            this.refundApplyType = refundApplyType;
        }

        public String getRefundCause() {
            return refundCause;
        }

        public void setRefundCause(String refundCause) {
            this.refundCause = refundCause;
        }

        public String getRefundDeal() {
            return refundDeal;
        }

        public void setRefundDeal(String refundDeal) {
            this.refundDeal = refundDeal;
        }

        public String getRefundDealDesc() {
            return refundDealDesc;
        }

        public void setRefundDealDesc(String refundDealDesc) {
            this.refundDealDesc = refundDealDesc;
        }

        public Object getRefundDealManagerId() {
            return refundDealManagerId;
        }

        public void setRefundDealManagerId(Object refundDealManagerId) {
            this.refundDealManagerId = refundDealManagerId;
        }

        public String getRefundDealTime() {
            return refundDealTime;
        }

        public void setRefundDealTime(String refundDealTime) {
            this.refundDealTime = refundDealTime;
        }

        public String getRefundDealTimeBegin() {
            return refundDealTimeBegin;
        }

        public void setRefundDealTimeBegin(String refundDealTimeBegin) {
            this.refundDealTimeBegin = refundDealTimeBegin;
        }

        public Object getRefundDealTimeEnd() {
            return refundDealTimeEnd;
        }

        public void setRefundDealTimeEnd(Object refundDealTimeEnd) {
            this.refundDealTimeEnd = refundDealTimeEnd;
        }

        public String getRefundState() {
            return refundState;
        }

        public void setRefundState(String refundState) {
            this.refundState = refundState;
        }

        public String getRefundType() {
            return refundType;
        }

        public void setRefundType(String refundType) {
            this.refundType = refundType;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
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
    }
}
