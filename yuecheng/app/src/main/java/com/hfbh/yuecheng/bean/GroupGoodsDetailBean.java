package com.hfbh.yuecheng.bean;

import java.io.Serializable;

/**
 * Author：Libin on 2018/7/18 13:54
 * Email：1993911441@qq.com
 * Describe：
 */
public class GroupGoodsDetailBean implements Serializable{

    private static final long serialVersionUID = -3161702943416117028L;
    /**
     * flag : true
     * hash :
     * data : {"activityDesc":"","berthNo":"0004","buyLimitNum":2,"buyNum":0,"clickNum":null,"commodityId":804,"commodityIntro":"333","commodityName":"的","commodityNum":2,"commodityState":"ONLINE","commodityType":"GROUPON","createTime":null,"createTimeBegin":null,"createTimeEnd":null,"creatorId":null,"endTime":"2018-05-25 00:00:00","endTimeBegin":"2018-05-25 00:00:00","endTimeEnd":null,"floorName":"1F","getTimeLimit":7,"groupNum":2,"industryId":2,"industryName":"时尚服装","isDelete":null,"isJoin":"N","isMustGroup":"N","isRefund":"N","isZeroOffline":null,"joinNum":0,"modifierId":null,"modifyTime":null,"modifyTimeBegin":null,"modifyTimeEnd":null,"nowDate":"2018-07-18 13:53:36","nowPrice":1,"offlineTime":null,"offlineTimeBegin":null,"offlineTimeEnd":null,"oldPrice":3,"onlineTime":"2018-05-18 10:31:11","onlineTimeBegin":null,"onlineTimeEnd":null,"organizeId":null,"picturePath":"http://yjwang.wmalle.com/image/20180514/6791042387367398.jpg","refundTimeLimit":null,"saleNum":0,"shopId":4,"shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171011103212332.jpg","shopName":"耐克","showSerial":null,"startTime":"2018-05-14 00:00:00","startTimeBegin":"2018-05-14 00:00:00","startTimeEnd":null,"verifyNum":null}
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

    public static class DataBean implements Serializable{
        private static final long serialVersionUID = -6291664858009450380L;
        /**
         * activityDesc :
         * berthNo : 0004
         * buyLimitNum : 2
         * buyNum : 0
         * clickNum : null
         * commodityId : 804
         * commodityIntro : 333
         * commodityName : 的
         * commodityNum : 2
         * commodityState : ONLINE
         * commodityType : GROUPON
         * createTime : null
         * createTimeBegin : null
         * createTimeEnd : null
         * creatorId : null
         * endTime : 2018-05-25 00:00:00
         * endTimeBegin : 2018-05-25 00:00:00
         * endTimeEnd : null
         * floorName : 1F
         * getTimeLimit : 7
         * groupNum : 2
         * industryId : 2
         * industryName : 时尚服装
         * isDelete : null
         * isJoin : N
         * isMustGroup : N
         * isRefund : N
         * isZeroOffline : null
         * joinNum : 0
         * modifierId : null
         * modifyTime : null
         * modifyTimeBegin : null
         * modifyTimeEnd : null
         * nowDate : 2018-07-18 13:53:36
         * nowPrice : 1
         * offlineTime : null
         * offlineTimeBegin : null
         * offlineTimeEnd : null
         * oldPrice : 3
         * onlineTime : 2018-05-18 10:31:11
         * onlineTimeBegin : null
         * onlineTimeEnd : null
         * organizeId : null
         * picturePath : http://yjwang.wmalle.com/image/20180514/6791042387367398.jpg
         * refundTimeLimit : null
         * saleNum : 0
         * shopId : 4
         * shopLogo : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171011103212332.jpg
         * shopName : 耐克
         * showSerial : null
         * startTime : 2018-05-14 00:00:00
         * startTimeBegin : 2018-05-14 00:00:00
         * startTimeEnd : null
         * verifyNum : null
         */

        private String activityDesc;
        private String berthNo;
        private int buyLimitNum;
        private int buyNum;
        private Object clickNum;
        private int commodityId;
        private String commodityIntro;
        private String commodityName;
        private int commodityNum;
        private String commodityState;
        private String commodityType;
        private Object createTime;
        private Object createTimeBegin;
        private Object createTimeEnd;
        private Object creatorId;
        private String endTime;
        private String endTimeBegin;
        private Object endTimeEnd;
        private String floorName;
        private int getTimeLimit;
        private int groupNum;
        private int industryId;
        private String industryName;
        private Object isDelete;
        private String isJoin;
        private String isMustGroup;
        private String isRefund;
        private Object isZeroOffline;
        private int joinNum;
        private Object modifierId;
        private Object modifyTime;
        private Object modifyTimeBegin;
        private Object modifyTimeEnd;
        private String nowDate;
        private double nowPrice;
        private Object offlineTime;
        private Object offlineTimeBegin;
        private Object offlineTimeEnd;
        private double oldPrice;
        private String onlineTime;
        private Object onlineTimeBegin;
        private Object onlineTimeEnd;
        private Object organizeId;
        private String picturePath;
        private Object refundTimeLimit;
        private int saleNum;
        private int shopId;
        private String shopLogo;
        private String shopName;
        private Object showSerial;
        private String startTime;
        private String startTimeBegin;
        private Object startTimeEnd;
        private Object verifyNum;

        public String getActivityDesc() {
            return activityDesc;
        }

        public void setActivityDesc(String activityDesc) {
            this.activityDesc = activityDesc;
        }

        public String getBerthNo() {
            return berthNo;
        }

        public void setBerthNo(String berthNo) {
            this.berthNo = berthNo;
        }

        public int getBuyLimitNum() {
            return buyLimitNum;
        }

        public void setBuyLimitNum(int buyLimitNum) {
            this.buyLimitNum = buyLimitNum;
        }

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public Object getClickNum() {
            return clickNum;
        }

        public void setClickNum(Object clickNum) {
            this.clickNum = clickNum;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityIntro() {
            return commodityIntro;
        }

        public void setCommodityIntro(String commodityIntro) {
            this.commodityIntro = commodityIntro;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public int getCommodityNum() {
            return commodityNum;
        }

        public void setCommodityNum(int commodityNum) {
            this.commodityNum = commodityNum;
        }

        public String getCommodityState() {
            return commodityState;
        }

        public void setCommodityState(String commodityState) {
            this.commodityState = commodityState;
        }

        public String getCommodityType() {
            return commodityType;
        }

        public void setCommodityType(String commodityType) {
            this.commodityType = commodityType;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
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

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getEndTimeBegin() {
            return endTimeBegin;
        }

        public void setEndTimeBegin(String endTimeBegin) {
            this.endTimeBegin = endTimeBegin;
        }

        public Object getEndTimeEnd() {
            return endTimeEnd;
        }

        public void setEndTimeEnd(Object endTimeEnd) {
            this.endTimeEnd = endTimeEnd;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        public int getGetTimeLimit() {
            return getTimeLimit;
        }

        public void setGetTimeLimit(int getTimeLimit) {
            this.getTimeLimit = getTimeLimit;
        }

        public int getGroupNum() {
            return groupNum;
        }

        public void setGroupNum(int groupNum) {
            this.groupNum = groupNum;
        }

        public int getIndustryId() {
            return industryId;
        }

        public void setIndustryId(int industryId) {
            this.industryId = industryId;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public Object getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(Object isDelete) {
            this.isDelete = isDelete;
        }

        public String getIsJoin() {
            return isJoin;
        }

        public void setIsJoin(String isJoin) {
            this.isJoin = isJoin;
        }

        public String getIsMustGroup() {
            return isMustGroup;
        }

        public void setIsMustGroup(String isMustGroup) {
            this.isMustGroup = isMustGroup;
        }

        public String getIsRefund() {
            return isRefund;
        }

        public void setIsRefund(String isRefund) {
            this.isRefund = isRefund;
        }

        public Object getIsZeroOffline() {
            return isZeroOffline;
        }

        public void setIsZeroOffline(Object isZeroOffline) {
            this.isZeroOffline = isZeroOffline;
        }

        public int getJoinNum() {
            return joinNum;
        }

        public void setJoinNum(int joinNum) {
            this.joinNum = joinNum;
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

        public String getNowDate() {
            return nowDate;
        }

        public void setNowDate(String nowDate) {
            this.nowDate = nowDate;
        }

        public double getNowPrice() {
            return nowPrice;
        }

        public void setNowPrice(double nowPrice) {
            this.nowPrice = nowPrice;
        }

        public Object getOfflineTime() {
            return offlineTime;
        }

        public void setOfflineTime(Object offlineTime) {
            this.offlineTime = offlineTime;
        }

        public Object getOfflineTimeBegin() {
            return offlineTimeBegin;
        }

        public void setOfflineTimeBegin(Object offlineTimeBegin) {
            this.offlineTimeBegin = offlineTimeBegin;
        }

        public Object getOfflineTimeEnd() {
            return offlineTimeEnd;
        }

        public void setOfflineTimeEnd(Object offlineTimeEnd) {
            this.offlineTimeEnd = offlineTimeEnd;
        }

        public double getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(double oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getOnlineTime() {
            return onlineTime;
        }

        public void setOnlineTime(String onlineTime) {
            this.onlineTime = onlineTime;
        }

        public Object getOnlineTimeBegin() {
            return onlineTimeBegin;
        }

        public void setOnlineTimeBegin(Object onlineTimeBegin) {
            this.onlineTimeBegin = onlineTimeBegin;
        }

        public Object getOnlineTimeEnd() {
            return onlineTimeEnd;
        }

        public void setOnlineTimeEnd(Object onlineTimeEnd) {
            this.onlineTimeEnd = onlineTimeEnd;
        }

        public Object getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(Object organizeId) {
            this.organizeId = organizeId;
        }

        public String getPicturePath() {
            return picturePath;
        }

        public void setPicturePath(String picturePath) {
            this.picturePath = picturePath;
        }

        public Object getRefundTimeLimit() {
            return refundTimeLimit;
        }

        public void setRefundTimeLimit(Object refundTimeLimit) {
            this.refundTimeLimit = refundTimeLimit;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopLogo() {
            return shopLogo;
        }

        public void setShopLogo(String shopLogo) {
            this.shopLogo = shopLogo;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public Object getShowSerial() {
            return showSerial;
        }

        public void setShowSerial(Object showSerial) {
            this.showSerial = showSerial;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStartTimeBegin() {
            return startTimeBegin;
        }

        public void setStartTimeBegin(String startTimeBegin) {
            this.startTimeBegin = startTimeBegin;
        }

        public Object getStartTimeEnd() {
            return startTimeEnd;
        }

        public void setStartTimeEnd(Object startTimeEnd) {
            this.startTimeEnd = startTimeEnd;
        }

        public Object getVerifyNum() {
            return verifyNum;
        }

        public void setVerifyNum(Object verifyNum) {
            this.verifyNum = verifyNum;
        }
    }
}
