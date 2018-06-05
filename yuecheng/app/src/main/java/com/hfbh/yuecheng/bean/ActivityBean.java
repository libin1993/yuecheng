package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/5/16 09:29
 * Email：1993911441@qq.com
 * Describe：
 */
public class ActivityBean {

    /**
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : [{"accessType":null,"accessValue":null,"acivityType":"NONEED","activityName":"不要报名","activityPic":"http://m.hfbh.com.cn/image/20180425/1312842440275216.jpg","balanceNum":null,"broughtNum":null,"couponDesc":null,"couponImage":null,"couponName":null,"couponTypeCy":null,"couponTypeKind":null,"couponValue":null,"dayBroughtNum":null,"endTime":"2018-04-30 00:00:00","endTimeStr":"2018.04.30","giftPicturePath":null,"id":null,"limitNum":null,"limitPerDayNum":null,"listCouponShop":null,"memberBroughtNum":null,"memberSignupState":"已结束","needScore":null,"objectId":397,"relateName":null,"serviceAmount":null,"signup":false,"sort":null,"startTime":"2018-04-28 00:00:00","startTimeStr":"2018.04.28","state":null,"stock":null,"tags":[{"activityTagsId":null,"createTime":null,"creatorId":null,"id":null,"isDelete":null,"marketingActivitySignupId":null,"modifierId":null,"modifyTime":null,"organizeId":null,"tagName":"五一欢庆"}],"useRange":null,"voteStartTime":"2018-04-18 00:00:00"},{"accessType":null,"accessValue":null,"acivityType":"SCORE","activityName":"活动报名565655656556565565655656556565565655656556565","activityPic":"http://yjwang.wmalle.com/image/20180514/6795404613975448.jpg","balanceNum":null,"broughtNum":null,"couponDesc":null,"couponImage":null,"couponName":null,"couponTypeCy":null,"couponTypeKind":null,"couponValue":null,"dayBroughtNum":null,"endTime":"2018-05-31 00:00:00","endTimeStr":"2018.05.31","giftPicturePath":null,"id":null,"limitNum":null,"limitPerDayNum":null,"listCouponShop":null,"memberBroughtNum":null,"memberSignupState":"已结束","needScore":null,"objectId":509,"relateName":null,"serviceAmount":null,"signup":false,"sort":null,"startTime":"2018-05-14 00:00:00","startTimeStr":"2018.05.14","state":null,"stock":null,"tags":[{"activityTagsId":null,"createTime":null,"creatorId":null,"id":null,"isDelete":null,"marketingActivitySignupId":null,"modifierId":null,"modifyTime":null,"organizeId":null,"tagName":"啤酒节"},{"activityTagsId":null,"createTime":null,"creatorId":null,"id":null,"isDelete":null,"marketingActivitySignupId":null,"modifierId":null,"modifyTime":null,"organizeId":null,"tagName":"清明节"},{"activityTagsId":null,"createTime":null,"creatorId":null,"id":null,"isDelete":null,"marketingActivitySignupId":null,"modifierId":null,"modifyTime":null,"organizeId":null,"tagName":"1啦啦啦啦啦"}],"useRange":null,"voteStartTime":"2018-05-14 00:00:00"}]
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
         * acivityType : NONEED
         * activityName : 不要报名
         * activityPic : http://m.hfbh.com.cn/image/20180425/1312842440275216.jpg
         * balanceNum : null
         * broughtNum : null
         * couponDesc : null
         * couponImage : null
         * couponName : null
         * couponTypeCy : null
         * couponTypeKind : null
         * couponValue : null
         * dayBroughtNum : null
         * endTime : 2018-04-30 00:00:00
         * endTimeStr : 2018.04.30
         * giftPicturePath : null
         * id : null
         * limitNum : null
         * limitPerDayNum : null
         * listCouponShop : null
         * memberBroughtNum : null
         * memberSignupState : 已结束
         * needScore : null
         * objectId : 397
         * relateName : null
         * serviceAmount : null
         * signup : false
         * sort : null
         * startTime : 2018-04-28 00:00:00
         * startTimeStr : 2018.04.28
         * state : null
         * stock : null
         * tags : [{"activityTagsId":null,"createTime":null,"creatorId":null,"id":null,"isDelete":null,"marketingActivitySignupId":null,"modifierId":null,"modifyTime":null,"organizeId":null,"tagName":"五一欢庆"}]
         * useRange : null
         * voteStartTime : 2018-04-18 00:00:00
         */

        private Object accessType;
        private Object accessValue;
        private String acivityType;
        private String activityName;
        private String activityPic;
        private Object balanceNum;
        private Object broughtNum;
        private Object couponDesc;
        private Object couponImage;
        private Object couponName;
        private Object couponTypeCy;
        private Object couponTypeKind;
        private Object couponValue;
        private Object dayBroughtNum;
        private String endTime;
        private String endTimeStr;
        private Object giftPicturePath;
        private Object id;
        private Object limitNum;
        private Object limitPerDayNum;
        private Object listCouponShop;
        private Object memberBroughtNum;
        private String memberSignupState;
        private Object needScore;
        private int objectId;
        private Object relateName;
        private Object serviceAmount;
        private boolean signup;
        private Object sort;
        private String startTime;
        private String startTimeStr;
        private Object state;
        private Object stock;
        private Object useRange;
        private String voteStartTime;
        private List<TagsBean> tags;

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

        public String getAcivityType() {
            return acivityType;
        }

        public void setAcivityType(String acivityType) {
            this.acivityType = acivityType;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getActivityPic() {
            return activityPic;
        }

        public void setActivityPic(String activityPic) {
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

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getEndTimeStr() {
            return endTimeStr;
        }

        public void setEndTimeStr(String endTimeStr) {
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

        public Object getListCouponShop() {
            return listCouponShop;
        }

        public void setListCouponShop(Object listCouponShop) {
            this.listCouponShop = listCouponShop;
        }

        public Object getMemberBroughtNum() {
            return memberBroughtNum;
        }

        public void setMemberBroughtNum(Object memberBroughtNum) {
            this.memberBroughtNum = memberBroughtNum;
        }

        public String getMemberSignupState() {
            return memberSignupState;
        }

        public void setMemberSignupState(String memberSignupState) {
            this.memberSignupState = memberSignupState;
        }

        public Object getNeedScore() {
            return needScore;
        }

        public void setNeedScore(Object needScore) {
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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStartTimeStr() {
            return startTimeStr;
        }

        public void setStartTimeStr(String startTimeStr) {
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

        public Object getUseRange() {
            return useRange;
        }

        public void setUseRange(Object useRange) {
            this.useRange = useRange;
        }

        public String getVoteStartTime() {
            return voteStartTime;
        }

        public void setVoteStartTime(String voteStartTime) {
            this.voteStartTime = voteStartTime;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class TagsBean {
            /**
             * activityTagsId : null
             * createTime : null
             * creatorId : null
             * id : null
             * isDelete : null
             * marketingActivitySignupId : null
             * modifierId : null
             * modifyTime : null
             * organizeId : null
             * tagName : 五一欢庆
             */

            private Object activityTagsId;
            private Object createTime;
            private Object creatorId;
            private Object id;
            private Object isDelete;
            private Object marketingActivitySignupId;
            private Object modifierId;
            private Object modifyTime;
            private Object organizeId;
            private String tagName;

            public Object getActivityTagsId() {
                return activityTagsId;
            }

            public void setActivityTagsId(Object activityTagsId) {
                this.activityTagsId = activityTagsId;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(Object creatorId) {
                this.creatorId = creatorId;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(Object isDelete) {
                this.isDelete = isDelete;
            }

            public Object getMarketingActivitySignupId() {
                return marketingActivitySignupId;
            }

            public void setMarketingActivitySignupId(Object marketingActivitySignupId) {
                this.marketingActivitySignupId = marketingActivitySignupId;
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

            public Object getOrganizeId() {
                return organizeId;
            }

            public void setOrganizeId(Object organizeId) {
                this.organizeId = organizeId;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }
        }
    }
}
