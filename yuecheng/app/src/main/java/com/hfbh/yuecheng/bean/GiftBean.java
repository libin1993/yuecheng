package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/5/16 09:29
 * Email：1993911441@qq.com
 * Describe：
 */
public class GiftBean {

    /**
     * flag : true
     * hash : 54ccdc1dfaef4addb104e0a9f9d1ffea
     * data : [{"accessType":null,"accessValue":null,"acivityType":null,"activityName":null,"activityPic":null,"balanceNum":null,"broughtNum":null,"couponDesc":null,"couponImage":null,"couponName":null,"couponTypeCy":null,"couponTypeKind":null,"couponValue":null,"dayBroughtNum":null,"endTime":null,"endTimeStr":null,"giftPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180408145742729.png","id":null,"limitNum":null,"limitPerDayNum":null,"memberBroughtNum":null,"memberSignupState":null,"needScore":20,"objectId":99,"relateName":"大礼包","signup":false,"sort":null,"startTime":null,"startTimeStr":null,"state":null,"stock":null,"tags":null,"useRange":null,"voteStartTime":null},{"accessType":null,"accessValue":null,"acivityType":null,"activityName":null,"activityPic":null,"balanceNum":null,"broughtNum":null,"couponDesc":null,"couponImage":null,"couponName":null,"couponTypeCy":null,"couponTypeKind":null,"couponValue":null,"dayBroughtNum":null,"endTime":null,"endTimeStr":null,"giftPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180408151052728.jpg","id":null,"limitNum":null,"limitPerDayNum":null,"memberBroughtNum":null,"memberSignupState":null,"needScore":10,"objectId":98,"relateName":"型男鞋","signup":false,"sort":null,"startTime":null,"startTimeStr":null,"state":null,"stock":null,"tags":null,"useRange":null,"voteStartTime":null},{"accessType":null,"accessValue":null,"acivityType":null,"activityName":null,"activityPic":null,"balanceNum":null,"broughtNum":null,"couponDesc":null,"couponImage":null,"couponName":null,"couponTypeCy":null,"couponTypeKind":null,"couponValue":null,"dayBroughtNum":null,"endTime":null,"endTimeStr":null,"giftPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180511092220944.png","id":null,"limitNum":null,"limitPerDayNum":null,"memberBroughtNum":null,"memberSignupState":null,"needScore":1,"objectId":171,"relateName":"11111111111111111111111111111111111111111111111111","signup":false,"sort":null,"startTime":null,"startTimeStr":null,"state":null,"stock":null,"tags":null,"useRange":null,"voteStartTime":null},{"accessType":null,"accessValue":null,"acivityType":null,"activityName":null,"activityPic":null,"balanceNum":null,"broughtNum":null,"couponDesc":null,"couponImage":null,"couponName":null,"couponTypeCy":null,"couponTypeKind":null,"couponValue":null,"dayBroughtNum":null,"endTime":null,"endTimeStr":null,"giftPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180511091805427.png","id":null,"limitNum":null,"limitPerDayNum":null,"memberBroughtNum":null,"memberSignupState":null,"needScore":100000,"objectId":173,"relateName":"礼品","signup":false,"sort":null,"startTime":null,"startTimeStr":null,"state":null,"stock":null,"tags":null,"useRange":null,"voteStartTime":null}]
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

    public static class DataBean {
        /**
         * accessType : null
         * accessValue : null
         * acivityType : null
         * activityName : null
         * activityPic : null
         * balanceNum : null
         * broughtNum : null
         * couponDesc : null
         * couponImage : null
         * couponName : null
         * couponTypeCy : null
         * couponTypeKind : null
         * couponValue : null
         * dayBroughtNum : null
         * endTime : null
         * endTimeStr : null
         * giftPicturePath : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180408145742729.png
         * id : null
         * limitNum : null
         * limitPerDayNum : null
         * memberBroughtNum : null
         * memberSignupState : null
         * needScore : 20
         * objectId : 99
         * relateName : 大礼包
         * signup : false
         * sort : null
         * startTime : null
         * startTimeStr : null
         * state : null
         * stock : null
         * tags : null
         * useRange : null
         * voteStartTime : null
         */

        private Object accessType;
        private Object accessValue;
        private Object acivityType;
        private Object activityName;
        private Object activityPic;
        private Object balanceNum;
        private Object broughtNum;
        private Object couponDesc;
        private Object couponImage;
        private Object couponName;
        private Object couponTypeCy;
        private Object couponTypeKind;
        private Object couponValue;
        private Object dayBroughtNum;
        private Object endTime;
        private Object endTimeStr;
        private String giftPicturePath;
        private Object id;
        private Object limitNum;
        private Object limitPerDayNum;
        private Object memberBroughtNum;
        private Object memberSignupState;
        private String needScore;
        private int objectId;
        private String relateName;
        private boolean signup;
        private Object sort;
        private Object startTime;
        private Object startTimeStr;
        private Object state;
        private Object stock;
        private Object tags;
        private Object useRange;
        private Object voteStartTime;

        public Object getAccessType() {
            return accessType;
        }

        public void setAccessType(Object accessType) {
            this.accessType = accessType;
        }

        public Object getAccessValue() {
            return accessValue;
        }

        public void setAccessValue(Object accessValue) {
            this.accessValue = accessValue;
        }

        public Object getAcivityType() {
            return acivityType;
        }

        public void setAcivityType(Object acivityType) {
            this.acivityType = acivityType;
        }

        public Object getActivityName() {
            return activityName;
        }

        public void setActivityName(Object activityName) {
            this.activityName = activityName;
        }

        public Object getActivityPic() {
            return activityPic;
        }

        public void setActivityPic(Object activityPic) {
            this.activityPic = activityPic;
        }

        public Object getBalanceNum() {
            return balanceNum;
        }

        public void setBalanceNum(Object balanceNum) {
            this.balanceNum = balanceNum;
        }

        public Object getBroughtNum() {
            return broughtNum;
        }

        public void setBroughtNum(Object broughtNum) {
            this.broughtNum = broughtNum;
        }

        public Object getCouponDesc() {
            return couponDesc;
        }

        public void setCouponDesc(Object couponDesc) {
            this.couponDesc = couponDesc;
        }

        public Object getCouponImage() {
            return couponImage;
        }

        public void setCouponImage(Object couponImage) {
            this.couponImage = couponImage;
        }

        public Object getCouponName() {
            return couponName;
        }

        public void setCouponName(Object couponName) {
            this.couponName = couponName;
        }

        public Object getCouponTypeCy() {
            return couponTypeCy;
        }

        public void setCouponTypeCy(Object couponTypeCy) {
            this.couponTypeCy = couponTypeCy;
        }

        public Object getCouponTypeKind() {
            return couponTypeKind;
        }

        public void setCouponTypeKind(Object couponTypeKind) {
            this.couponTypeKind = couponTypeKind;
        }

        public Object getCouponValue() {
            return couponValue;
        }

        public void setCouponValue(Object couponValue) {
            this.couponValue = couponValue;
        }

        public Object getDayBroughtNum() {
            return dayBroughtNum;
        }

        public void setDayBroughtNum(Object dayBroughtNum) {
            this.dayBroughtNum = dayBroughtNum;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public Object getEndTimeStr() {
            return endTimeStr;
        }

        public void setEndTimeStr(Object endTimeStr) {
            this.endTimeStr = endTimeStr;
        }

        public String getGiftPicturePath() {
            return giftPicturePath;
        }

        public void setGiftPicturePath(String giftPicturePath) {
            this.giftPicturePath = giftPicturePath;
        }

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getLimitNum() {
            return limitNum;
        }

        public void setLimitNum(Object limitNum) {
            this.limitNum = limitNum;
        }

        public Object getLimitPerDayNum() {
            return limitPerDayNum;
        }

        public void setLimitPerDayNum(Object limitPerDayNum) {
            this.limitPerDayNum = limitPerDayNum;
        }

        public Object getMemberBroughtNum() {
            return memberBroughtNum;
        }

        public void setMemberBroughtNum(Object memberBroughtNum) {
            this.memberBroughtNum = memberBroughtNum;
        }

        public Object getMemberSignupState() {
            return memberSignupState;
        }

        public void setMemberSignupState(Object memberSignupState) {
            this.memberSignupState = memberSignupState;
        }

        public String getNeedScore() {
            return needScore;
        }

        public void setNeedScore(String needScore) {
            this.needScore = needScore;
        }

        public int getObjectId() {
            return objectId;
        }

        public void setObjectId(int objectId) {
            this.objectId = objectId;
        }

        public String getRelateName() {
            return relateName;
        }

        public void setRelateName(String relateName) {
            this.relateName = relateName;
        }

        public boolean isSignup() {
            return signup;
        }

        public void setSignup(boolean signup) {
            this.signup = signup;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getStartTimeStr() {
            return startTimeStr;
        }

        public void setStartTimeStr(Object startTimeStr) {
            this.startTimeStr = startTimeStr;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getStock() {
            return stock;
        }

        public void setStock(Object stock) {
            this.stock = stock;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public Object getUseRange() {
            return useRange;
        }

        public void setUseRange(Object useRange) {
            this.useRange = useRange;
        }

        public Object getVoteStartTime() {
            return voteStartTime;
        }

        public void setVoteStartTime(Object voteStartTime) {
            this.voteStartTime = voteStartTime;
        }
    }
}
