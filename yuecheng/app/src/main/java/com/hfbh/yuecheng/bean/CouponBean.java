package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/5/16 09:29
 * Email：1993911441@qq.com
 * Describe：
 */
public class CouponBean {

    /**
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : [{"accessType":"FREE","accessValue":0,"acivityType":null,"activityName":null,"activityPic":null,"balanceNum":93,"broughtNum":7,"couponDesc":"2","couponImage":"http://yjwang.wmalle.com/image/20180514/6772728606142893.png","couponName":"礼券","couponTypeCy":1,"couponTypeKind":"ELEC","couponValue":1,"dayBroughtNum":0,"endTime":null,"endTimeStr":null,"giftPicturePath":null,"id":null,"limitNum":1,"limitPerDayNum":null,"listCouponShop":null,"memberBroughtNum":0,"memberSignupState":null,"needScore":0,"objectId":496,"relateName":null,"serviceAmount":null,"signup":false,"sort":null,"startTime":null,"startTimeStr":null,"state":null,"stock":100,"tags":null,"useRange":"2","voteStartTime":null},{"accessType":"POINT","accessValue":111111,"acivityType":null,"activityName":null,"activityPic":null,"balanceNum":52,"broughtNum":48,"couponDesc":"1","couponImage":"http://yjwang.wmalle.com/image/20180514/6775368397833198.png","couponName":"专享券","couponTypeCy":13,"couponTypeKind":"ELEC","couponValue":20,"dayBroughtNum":0,"endTime":null,"endTimeStr":null,"giftPicturePath":null,"id":null,"limitNum":0,"limitPerDayNum":null,"listCouponShop":null,"memberBroughtNum":0,"memberSignupState":null,"needScore":111111,"objectId":502,"relateName":null,"serviceAmount":null,"signup":false,"sort":null,"startTime":null,"startTimeStr":null,"state":"POINTS","stock":100,"tags":null,"useRange":"1","voteStartTime":null},{"accessType":"FREE","accessValue":0,"acivityType":null,"activityName":null,"activityPic":null,"balanceNum":0,"broughtNum":1,"couponDesc":"1","couponImage":"http://yjwang.wmalle.com/image/20180514/6773058231567001.png","couponName":"家电券","couponTypeCy":2,"couponTypeKind":"ELEC","couponValue":4,"dayBroughtNum":0,"endTime":null,"endTimeStr":null,"giftPicturePath":null,"id":null,"limitNum":0,"limitPerDayNum":null,"listCouponShop":null,"memberBroughtNum":0,"memberSignupState":null,"needScore":0,"objectId":498,"relateName":null,"serviceAmount":null,"signup":false,"sort":null,"startTime":null,"startTimeStr":null,"state":"ROB","stock":1,"tags":null,"useRange":"1","voteStartTime":null},{"accessType":"FREE","accessValue":null,"acivityType":null,"activityName":null,"activityPic":null,"balanceNum":0,"broughtNum":1,"couponDesc":"1、使用范围\n2、就覅玩发几万就覅我我案件佛我啊我范加尔拉我发拉我就覅哦啊我\n3、就覅垃圾爱覅偶啊我","couponImage":"http://yjwang.wmalle.com/image/20180514/6772994027677170.png","couponName":"家电券","couponTypeCy":2,"couponTypeKind":"ELEC","couponValue":3,"dayBroughtNum":0,"endTime":null,"endTimeStr":null,"giftPicturePath":null,"id":null,"limitNum":1,"limitPerDayNum":null,"listCouponShop":null,"memberBroughtNum":0,"memberSignupState":null,"needScore":null,"objectId":497,"relateName":null,"serviceAmount":null,"signup":false,"sort":null,"startTime":null,"startTimeStr":null,"state":"ROB","stock":1,"tags":null,"useRange":"1、使用范围\n2、就覅玩发几万就覅我我案件佛我啊我范加尔拉我发拉我就覅哦啊我\n3、就覅垃圾爱覅偶啊我","voteStartTime":null}]
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
         * accessType : FREE
         * accessValue : 0
         * acivityType : null
         * activityName : null
         * activityPic : null
         * balanceNum : 93
         * broughtNum : 7
         * couponDesc : 2
         * couponImage : http://yjwang.wmalle.com/image/20180514/6772728606142893.png
         * couponName : 礼券
         * couponTypeCy : 1
         * couponTypeKind : ELEC
         * couponValue : 1
         * dayBroughtNum : 0
         * endTime : null
         * endTimeStr : null
         * giftPicturePath : null
         * id : null
         * limitNum : 1
         * limitPerDayNum : null
         * listCouponShop : null
         * memberBroughtNum : 0
         * memberSignupState : null
         * needScore : 0
         * objectId : 496
         * relateName : null
         * serviceAmount : null
         * signup : false
         * sort : null
         * startTime : null
         * startTimeStr : null
         * state : null
         * stock : 100
         * tags : null
         * useRange : 2
         * voteStartTime : null
         */

        private String accessType;
        private int accessValue;
        private Object acivityType;
        private Object activityName;
        private Object activityPic;
        private int balanceNum;
        private int broughtNum;
        private String couponDesc;
        private String couponImage;
        private String couponName;
        private int couponTypeCy;
        private String couponTypeKind;
        private int couponValue;
        private int dayBroughtNum;
        private Object endTime;
        private Object endTimeStr;
        private Object giftPicturePath;
        private Object id;
        private int limitNum;
        private Object limitPerDayNum;
        private Object listCouponShop;
        private int memberBroughtNum;
        private Object memberSignupState;
        private int needScore;
        private int objectId;
        private Object relateName;
        private Object serviceAmount;
        private boolean signup;
        private Object sort;
        private Object startTime;
        private Object startTimeStr;
        private Object state;
        private int stock;
        private Object tags;
        private String useRange;
        private Object voteStartTime;

        public String getAccessType() {
            return accessType;
        }

        public void setAccessType(String accessType) {
            this.accessType = accessType;
        }

        public int getAccessValue() {
            return accessValue;
        }

        public void setAccessValue(int accessValue) {
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

        public int getBalanceNum() {
            return balanceNum;
        }

        public void setBalanceNum(int balanceNum) {
            this.balanceNum = balanceNum;
        }

        public int getBroughtNum() {
            return broughtNum;
        }

        public void setBroughtNum(int broughtNum) {
            this.broughtNum = broughtNum;
        }

        public String getCouponDesc() {
            return couponDesc;
        }

        public void setCouponDesc(String couponDesc) {
            this.couponDesc = couponDesc;
        }

        public String getCouponImage() {
            return couponImage;
        }

        public void setCouponImage(String couponImage) {
            this.couponImage = couponImage;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public int getCouponTypeCy() {
            return couponTypeCy;
        }

        public void setCouponTypeCy(int couponTypeCy) {
            this.couponTypeCy = couponTypeCy;
        }

        public String getCouponTypeKind() {
            return couponTypeKind;
        }

        public void setCouponTypeKind(String couponTypeKind) {
            this.couponTypeKind = couponTypeKind;
        }

        public int getCouponValue() {
            return couponValue;
        }

        public void setCouponValue(int couponValue) {
            this.couponValue = couponValue;
        }

        public int getDayBroughtNum() {
            return dayBroughtNum;
        }

        public void setDayBroughtNum(int dayBroughtNum) {
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

        public Object getGiftPicturePath() {
            return giftPicturePath;
        }

        public void setGiftPicturePath(Object giftPicturePath) {
            this.giftPicturePath = giftPicturePath;
        }

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public int getLimitNum() {
            return limitNum;
        }

        public void setLimitNum(int limitNum) {
            this.limitNum = limitNum;
        }

        public Object getLimitPerDayNum() {
            return limitPerDayNum;
        }

        public void setLimitPerDayNum(Object limitPerDayNum) {
            this.limitPerDayNum = limitPerDayNum;
        }

        public Object getListCouponShop() {
            return listCouponShop;
        }

        public void setListCouponShop(Object listCouponShop) {
            this.listCouponShop = listCouponShop;
        }

        public int getMemberBroughtNum() {
            return memberBroughtNum;
        }

        public void setMemberBroughtNum(int memberBroughtNum) {
            this.memberBroughtNum = memberBroughtNum;
        }

        public Object getMemberSignupState() {
            return memberSignupState;
        }

        public void setMemberSignupState(Object memberSignupState) {
            this.memberSignupState = memberSignupState;
        }

        public int getNeedScore() {
            return needScore;
        }

        public void setNeedScore(int needScore) {
            this.needScore = needScore;
        }

        public int getObjectId() {
            return objectId;
        }

        public void setObjectId(int objectId) {
            this.objectId = objectId;
        }

        public Object getRelateName() {
            return relateName;
        }

        public void setRelateName(Object relateName) {
            this.relateName = relateName;
        }

        public Object getServiceAmount() {
            return serviceAmount;
        }

        public void setServiceAmount(Object serviceAmount) {
            this.serviceAmount = serviceAmount;
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

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public String getUseRange() {
            return useRange;
        }

        public void setUseRange(String useRange) {
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
