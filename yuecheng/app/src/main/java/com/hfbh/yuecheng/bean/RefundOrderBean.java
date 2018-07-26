package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/7/26 10:45
 * Email：1993911441@qq.com
 * Describe：
 */
public class RefundOrderBean {

    /**
     * flag : true
     * page : {"pageNum":1,"pageSize":10,"pages":1,"total":1}
     * hash : 848489fbe87c4e88a44c2e6ad0eb8c68
     * data : [{"applyTime":"2018-07-25 00:00:02","applyTimeBegin":null,"applyTimeEnd":null,"commodityName":"编辑iiiiiiii","createTime":"2018-07-25 00:00:02","createTimeBegin":null,"createTimeEnd":null,"creatorId":null,"dealAmount":1,"detailAccount":1,"detailPicturePath":"http://yjwang.wmalle.com/image/20180606/8783861542277200.jpg","detailPrice":1,"isDelete":"N","memberId":181166,"memberOrderDetailId":84809,"memberRefundApplyId":1130,"mobilePhone":"13515815616","modifierId":null,"modifyTime":null,"modifyTimeBegin":null,"modifyTimeEnd":null,"openId":null,"orderNo":"1011020193260515237888","organizeId":2,"originalPrice":100,"refundAmount":1,"refundApplyDesc":"团购失败自动退款","refundApplyNumber":"1031021787093321523200","refundApplyPic":null,"refundApplyType":"REFUND","refundCause":"团购失败自动退款","refundDeal":null,"refundDealDesc":null,"refundDealManagerId":null,"refundDealTime":"2018-07-25 00:00:02","refundDealTimeBegin":null,"refundDealTimeEnd":null,"refundState":"SUCCESS","refundType":"GROUP_FAIL_SYS_AUTO","shopName":"红蜻蜓","useAccountBalance":null,"wechatPay":null}]
     * code : 0
     */

    private boolean flag;
    private PageBean page;
    private String hash;
    private int code;
    private List<DataBean> data;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
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

    public static class PageBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * total : 1
         */

        private int pageNum;
        private int pageSize;
        private int pages;
        private int total;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class DataBean {
        /**
         * applyTime : 2018-07-25 00:00:02
         * applyTimeBegin : null
         * applyTimeEnd : null
         * commodityName : 编辑iiiiiiii
         * createTime : 2018-07-25 00:00:02
         * createTimeBegin : null
         * createTimeEnd : null
         * creatorId : null
         * dealAmount : 1
         * detailAccount : 1
         * detailPicturePath : http://yjwang.wmalle.com/image/20180606/8783861542277200.jpg
         * detailPrice : 1
         * isDelete : N
         * memberId : 181166
         * memberOrderDetailId : 84809
         * memberRefundApplyId : 1130
         * mobilePhone : 13515815616
         * modifierId : null
         * modifyTime : null
         * modifyTimeBegin : null
         * modifyTimeEnd : null
         * openId : null
         * orderNo : 1011020193260515237888
         * organizeId : 2
         * originalPrice : 100
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
         * refundDealTimeBegin : null
         * refundDealTimeEnd : null
         * refundState : SUCCESS
         * refundType : GROUP_FAIL_SYS_AUTO
         * shopName : 红蜻蜓
         * useAccountBalance : null
         * wechatPay : null
         */

        private String applyTime;
        private Object applyTimeBegin;
        private Object applyTimeEnd;
        private String commodityName;
        private String createTime;
        private Object createTimeBegin;
        private Object createTimeEnd;
        private Object creatorId;
        private double dealAmount;
        private int detailAccount;
        private String detailPicturePath;
        private double detailPrice;
        private String isDelete;
        private int memberId;
        private int memberOrderDetailId;
        private int memberRefundApplyId;
        private String mobilePhone;
        private Object modifierId;
        private Object modifyTime;
        private Object modifyTimeBegin;
        private Object modifyTimeEnd;
        private Object openId;
        private String orderNo;
        private int organizeId;
        private double originalPrice;
        private double refundAmount;
        private String refundApplyDesc;
        private String refundApplyNumber;
        private Object refundApplyPic;
        private String refundApplyType;
        private String refundCause;
        private Object refundDeal;
        private Object refundDealDesc;
        private Object refundDealManagerId;
        private String refundDealTime;
        private Object refundDealTimeBegin;
        private Object refundDealTimeEnd;
        private String refundState;
        private String refundType;
        private String shopName;
        private Object useAccountBalance;
        private Object wechatPay;

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }

        public Object getApplyTimeBegin() {
            return applyTimeBegin;
        }

        public void setApplyTimeBegin(Object applyTimeBegin) {
            this.applyTimeBegin = applyTimeBegin;
        }

        public Object getApplyTimeEnd() {
            return applyTimeEnd;
        }

        public void setApplyTimeEnd(Object applyTimeEnd) {
            this.applyTimeEnd = applyTimeEnd;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
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

        public String getDetailPicturePath() {
            return detailPicturePath;
        }

        public void setDetailPicturePath(String detailPicturePath) {
            this.detailPicturePath = detailPicturePath;
        }

        public double getDetailPrice() {
            return detailPrice;
        }

        public void setDetailPrice(double detailPrice) {
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

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
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

        public Object getOpenId() {
            return openId;
        }

        public void setOpenId(Object openId) {
            this.openId = openId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
            this.organizeId = organizeId;
        }

        public double getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(double originalPrice) {
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

        public Object getRefundApplyPic() {
            return refundApplyPic;
        }

        public void setRefundApplyPic(Object refundApplyPic) {
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

        public Object getRefundDeal() {
            return refundDeal;
        }

        public void setRefundDeal(Object refundDeal) {
            this.refundDeal = refundDeal;
        }

        public Object getRefundDealDesc() {
            return refundDealDesc;
        }

        public void setRefundDealDesc(Object refundDealDesc) {
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

        public Object getRefundDealTimeBegin() {
            return refundDealTimeBegin;
        }

        public void setRefundDealTimeBegin(Object refundDealTimeBegin) {
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

        public Object getUseAccountBalance() {
            return useAccountBalance;
        }

        public void setUseAccountBalance(Object useAccountBalance) {
            this.useAccountBalance = useAccountBalance;
        }

        public Object getWechatPay() {
            return wechatPay;
        }

        public void setWechatPay(Object wechatPay) {
            this.wechatPay = wechatPay;
        }
    }
}
