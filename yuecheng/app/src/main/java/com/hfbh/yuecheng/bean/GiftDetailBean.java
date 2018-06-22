package com.hfbh.yuecheng.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Author：Libin on 2018/5/25 09:05
 * Email：1993911441@qq.com
 * Describe：
 */
public class GiftDetailBean {

    /**
     * flag : true
     * hash : d24ee8ce3b63493c99ed77ebd9c9cb9a
     * data : {"accessValue":null,"balanceGetNum":null,"balanceNum":11,"couponValue":null,"createTime":"2018-05-23 19:13:59","creatorId":4,"exchangeIntro":"q23rf2qrt2","exchangeLimitNum":11,"exchangeNum":0,"giftIntro":"1","isDelete":"N","isZeroOffline":"Y","limitGetNum":0,"modifierId":null,"modifyTime":null,"needScore":0,"offlineTime":null,"onlineTime":"2018-05-23 19:13:59","organizeId":2,"picUrl":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180417102435054.jpg","pointsRewardId":182,"relateId":133,"relateName":"美3","rewardState":"ONLINE","rewardType":"GIFT","serviceAmount":null,"showSerial":null,"singleGetNum":null,"state":"CANEXCHANGE"}
     * 7a250566221248a0a854116de02d6e3c : 07496c931ecb46ca81010e73cfdea6c8
     * submit_tokens_name : 7a250566221248a0a854116de02d6e3c
     * code : 0
     */

    private boolean flag;
    private String hash;
    private DataBean data;
    @SerializedName("7a250566221248a0a854116de02d6e3c")
    private String _$7a250566221248a0a854116de02d6e3c;
    private String submit_tokens_name;
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

    public String get_$7a250566221248a0a854116de02d6e3c() {
        return _$7a250566221248a0a854116de02d6e3c;
    }

    public void set_$7a250566221248a0a854116de02d6e3c(String _$7a250566221248a0a854116de02d6e3c) {
        this._$7a250566221248a0a854116de02d6e3c = _$7a250566221248a0a854116de02d6e3c;
    }

    public String getSubmit_tokens_name() {
        return submit_tokens_name;
    }

    public void setSubmit_tokens_name(String submit_tokens_name) {
        this.submit_tokens_name = submit_tokens_name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * accessValue : null
         * balanceGetNum : null
         * balanceNum : 11
         * couponValue : null
         * createTime : 2018-05-23 19:13:59
         * creatorId : 4
         * exchangeIntro : q23rf2qrt2
         * exchangeLimitNum : 11
         * exchangeNum : 0
         * giftIntro : 1
         * isDelete : N
         * isZeroOffline : Y
         * limitGetNum : 0
         * modifierId : null
         * modifyTime : null
         * needScore : 0
         * offlineTime : null
         * onlineTime : 2018-05-23 19:13:59
         * organizeId : 2
         * picUrl : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180417102435054.jpg
         * pointsRewardId : 182
         * relateId : 133
         * relateName : 美3
         * rewardState : ONLINE
         * rewardType : GIFT
         * serviceAmount : null
         * showSerial : null
         * singleGetNum : null
         * state : CANEXCHANGE
         */

        private Object accessValue;
        private int balanceGetNum;
        private int balanceNum;
        private Object couponValue;
        private String createTime;
        private int creatorId;
        private String exchangeIntro;
        private int exchangeLimitNum;
        private int exchangeNum;
        private String giftIntro;
        private String isDelete;
        private String isZeroOffline;
        private int limitGetNum;
        private Object modifierId;
        private Object modifyTime;
        private int needScore;
        private String offlineTime;
        private String onlineTime;
        private int organizeId;
        private String picUrl;
        private int pointsRewardId;
        private int relateId;
        private String relateName;
        private String rewardState;
        private String rewardType;
        private Object serviceAmount;
        private Object showSerial;
        private Object singleGetNum;
        private String state;

        public Object getAccessValue() {
            return accessValue;
        }

        public void setAccessValue(Object accessValue) {
            this.accessValue = accessValue;
        }

        public int getBalanceGetNum() {
            return balanceGetNum;
        }

        public void setBalanceGetNum(int balanceGetNum) {
            this.balanceGetNum = balanceGetNum;
        }

        public int getBalanceNum() {
            return balanceNum;
        }

        public void setBalanceNum(int balanceNum) {
            this.balanceNum = balanceNum;
        }

        public Object getCouponValue() {
            return couponValue;
        }

        public void setCouponValue(Object couponValue) {
            this.couponValue = couponValue;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(int creatorId) {
            this.creatorId = creatorId;
        }

        public String getExchangeIntro() {
            return exchangeIntro;
        }

        public void setExchangeIntro(String exchangeIntro) {
            this.exchangeIntro = exchangeIntro;
        }

        public int getExchangeLimitNum() {
            return exchangeLimitNum;
        }

        public void setExchangeLimitNum(int exchangeLimitNum) {
            this.exchangeLimitNum = exchangeLimitNum;
        }

        public int getExchangeNum() {
            return exchangeNum;
        }

        public void setExchangeNum(int exchangeNum) {
            this.exchangeNum = exchangeNum;
        }

        public String getGiftIntro() {
            return giftIntro;
        }

        public void setGiftIntro(String giftIntro) {
            this.giftIntro = giftIntro;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getIsZeroOffline() {
            return isZeroOffline;
        }

        public void setIsZeroOffline(String isZeroOffline) {
            this.isZeroOffline = isZeroOffline;
        }

        public int getLimitGetNum() {
            return limitGetNum;
        }

        public void setLimitGetNum(int limitGetNum) {
            this.limitGetNum = limitGetNum;
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

        public int getNeedScore() {
            return needScore;
        }

        public void setNeedScore(int needScore) {
            this.needScore = needScore;
        }

        public String getOfflineTime() {
            return offlineTime;
        }

        public void setOfflineTime(String offlineTime) {
            this.offlineTime = offlineTime;
        }

        public String getOnlineTime() {
            return onlineTime;
        }

        public void setOnlineTime(String onlineTime) {
            this.onlineTime = onlineTime;
        }

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
            this.organizeId = organizeId;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public int getPointsRewardId() {
            return pointsRewardId;
        }

        public void setPointsRewardId(int pointsRewardId) {
            this.pointsRewardId = pointsRewardId;
        }

        public int getRelateId() {
            return relateId;
        }

        public void setRelateId(int relateId) {
            this.relateId = relateId;
        }

        public String getRelateName() {
            return relateName;
        }

        public void setRelateName(String relateName) {
            this.relateName = relateName;
        }

        public String getRewardState() {
            return rewardState;
        }

        public void setRewardState(String rewardState) {
            this.rewardState = rewardState;
        }

        public String getRewardType() {
            return rewardType;
        }

        public void setRewardType(String rewardType) {
            this.rewardType = rewardType;
        }

        public Object getServiceAmount() {
            return serviceAmount;
        }

        public void setServiceAmount(Object serviceAmount) {
            this.serviceAmount = serviceAmount;
        }

        public Object getShowSerial() {
            return showSerial;
        }

        public void setShowSerial(Object showSerial) {
            this.showSerial = showSerial;
        }

        public Object getSingleGetNum() {
            return singleGetNum;
        }

        public void setSingleGetNum(Object singleGetNum) {
            this.singleGetNum = singleGetNum;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
