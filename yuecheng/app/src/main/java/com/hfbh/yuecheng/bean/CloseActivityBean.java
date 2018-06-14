package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/14 10:23
 * Email：1993911441@qq.com
 * Describe：
 */
public class CloseActivityBean {

    /**
     * flag : true
     * data : {"statistic":{"auditState":"AUDIT","auditStateText":null,"createTime":"2018-06-12 20:09:51","creatorId":null,"endTimeText":null,"isDelete":"N","marketingActivitySignupId":630,"marketingActivitySignupStatisticsId":788,"memberId":"179178","modifierId":null,"modifyTime":null,"name":"22","organizeId":2,"phone":"13333333333","signupTime":"2018-06-12 20:09:51","startTimeText":null,"state":"UNDESTROY","stateText":null,"verifyCode":"t24Vpbmf"},"activity":{"acivityAddress":"西湖断桥残雪","acivityType":"FREE","activityEndtime":"2018-06-30 00:00:00","activityIntroduce":"人人可参加","activityPicture":"http://yjwang.wmalle.com/image/20180611/9216099017020355.png","activityStarttime":"2018-06-11 00:00:00","activityTitle":"618美食节","createTime":"2018-06-11 16:11:51","creatorId":4,"endTime":"2018-06-15 00:00:00","endTimeStr":"2018/06/15","enrollFee":null,"enrollScore":null,"extraIntroduce":"","isAudit":"N","isDelete":"N","isSignup":true,"joinNumber":0,"marketingActivitySignupId":630,"memberLevel":null,"memberSignupState":"去参加","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-06-11 16:11:51","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":8,"signupState":"DOING","signupTime":null,"startTime":"2018-06-11 00:00:00","startTimeStr":"2018/06/11","tags":[{"tagType":"public","tagName":"西湖"}],"telephone":"","useCrowd":null,"verifyCode":"t24Vpbmf"}}
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
         * statistic : {"auditState":"AUDIT","auditStateText":null,"createTime":"2018-06-12 20:09:51","creatorId":null,"endTimeText":null,"isDelete":"N","marketingActivitySignupId":630,"marketingActivitySignupStatisticsId":788,"memberId":"179178","modifierId":null,"modifyTime":null,"name":"22","organizeId":2,"phone":"13333333333","signupTime":"2018-06-12 20:09:51","startTimeText":null,"state":"UNDESTROY","stateText":null,"verifyCode":"t24Vpbmf"}
         * activity : {"acivityAddress":"西湖断桥残雪","acivityType":"FREE","activityEndtime":"2018-06-30 00:00:00","activityIntroduce":"人人可参加","activityPicture":"http://yjwang.wmalle.com/image/20180611/9216099017020355.png","activityStarttime":"2018-06-11 00:00:00","activityTitle":"618美食节","createTime":"2018-06-11 16:11:51","creatorId":4,"endTime":"2018-06-15 00:00:00","endTimeStr":"2018/06/15","enrollFee":null,"enrollScore":null,"extraIntroduce":"","isAudit":"N","isDelete":"N","isSignup":true,"joinNumber":0,"marketingActivitySignupId":630,"memberLevel":null,"memberSignupState":"去参加","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-06-11 16:11:51","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":8,"signupState":"DOING","signupTime":null,"startTime":"2018-06-11 00:00:00","startTimeStr":"2018/06/11","tags":[{"tagType":"public","tagName":"西湖"}],"telephone":"","useCrowd":null,"verifyCode":"t24Vpbmf"}
         */

        private StatisticBean statistic;
        private ActivityBean activity;

        public StatisticBean getStatistic() {
            return statistic;
        }

        public void setStatistic(StatisticBean statistic) {
            this.statistic = statistic;
        }

        public ActivityBean getActivity() {
            return activity;
        }

        public void setActivity(ActivityBean activity) {
            this.activity = activity;
        }

        public static class StatisticBean {
            /**
             * auditState : AUDIT
             * auditStateText : null
             * createTime : 2018-06-12 20:09:51
             * creatorId : null
             * endTimeText : null
             * isDelete : N
             * marketingActivitySignupId : 630
             * marketingActivitySignupStatisticsId : 788
             * memberId : 179178
             * modifierId : null
             * modifyTime : null
             * name : 22
             * organizeId : 2
             * phone : 13333333333
             * signupTime : 2018-06-12 20:09:51
             * startTimeText : null
             * state : UNDESTROY
             * stateText : null
             * verifyCode : t24Vpbmf
             */

            private String auditState;
            private Object auditStateText;
            private String createTime;
            private Object creatorId;
            private Object endTimeText;
            private String isDelete;
            private int marketingActivitySignupId;
            private int marketingActivitySignupStatisticsId;
            private String memberId;
            private Object modifierId;
            private Object modifyTime;
            private String name;
            private int organizeId;
            private String phone;
            private String signupTime;
            private Object startTimeText;
            private String state;
            private Object stateText;
            private String verifyCode;

            public String getAuditState() {
                return auditState;
            }

            public void setAuditState(String auditState) {
                this.auditState = auditState;
            }

            public Object getAuditStateText() {
                return auditStateText;
            }

            public void setAuditStateText(Object auditStateText) {
                this.auditStateText = auditStateText;
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

            public Object getEndTimeText() {
                return endTimeText;
            }

            public void setEndTimeText(Object endTimeText) {
                this.endTimeText = endTimeText;
            }

            public String getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public int getMarketingActivitySignupId() {
                return marketingActivitySignupId;
            }

            public void setMarketingActivitySignupId(int marketingActivitySignupId) {
                this.marketingActivitySignupId = marketingActivitySignupId;
            }

            public int getMarketingActivitySignupStatisticsId() {
                return marketingActivitySignupStatisticsId;
            }

            public void setMarketingActivitySignupStatisticsId(int marketingActivitySignupStatisticsId) {
                this.marketingActivitySignupStatisticsId = marketingActivitySignupStatisticsId;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOrganizeId() {
                return organizeId;
            }

            public void setOrganizeId(int organizeId) {
                this.organizeId = organizeId;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getSignupTime() {
                return signupTime;
            }

            public void setSignupTime(String signupTime) {
                this.signupTime = signupTime;
            }

            public Object getStartTimeText() {
                return startTimeText;
            }

            public void setStartTimeText(Object startTimeText) {
                this.startTimeText = startTimeText;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public Object getStateText() {
                return stateText;
            }

            public void setStateText(Object stateText) {
                this.stateText = stateText;
            }

            public String getVerifyCode() {
                return verifyCode;
            }

            public void setVerifyCode(String verifyCode) {
                this.verifyCode = verifyCode;
            }
        }

        public static class ActivityBean {
            /**
             * acivityAddress : 西湖断桥残雪
             * acivityType : FREE
             * activityEndtime : 2018-06-30 00:00:00
             * activityIntroduce : 人人可参加
             * activityPicture : http://yjwang.wmalle.com/image/20180611/9216099017020355.png
             * activityStarttime : 2018-06-11 00:00:00
             * activityTitle : 618美食节
             * createTime : 2018-06-11 16:11:51
             * creatorId : 4
             * endTime : 2018-06-15 00:00:00
             * endTimeStr : 2018/06/15
             * enrollFee : null
             * enrollScore : null
             * extraIntroduce :
             * isAudit : N
             * isDelete : N
             * isSignup : true
             * joinNumber : 0
             * marketingActivitySignupId : 630
             * memberLevel : null
             * memberSignupState : 去参加
             * modifierId : null
             * modifyTime : null
             * offlineTime : null
             * onlineTime : 2018-06-11 16:11:51
             * organizeId : 2
             * signStateText : 进行中
             * signinOffline : OPEN
             * signupLimitNumber : 0
             * signupNumber : 8
             * signupState : DOING
             * signupTime : null
             * startTime : 2018-06-11 00:00:00
             * startTimeStr : 2018/06/11
             * tags : [{"tagType":"public","tagName":"西湖"}]
             * telephone :
             * useCrowd : null
             * verifyCode : t24Vpbmf
             */

            private String acivityAddress;
            private String acivityType;
            private String activityEndtime;
            private String activityIntroduce;
            private String activityPicture;
            private String activityStarttime;
            private String activityTitle;
            private String createTime;
            private int creatorId;
            private String endTime;
            private String endTimeStr;
            private double enrollFee;
            private double enrollScore;
            private String extraIntroduce;
            private String isAudit;
            private String isDelete;
            private boolean isSignup;
            private int joinNumber;
            private int marketingActivitySignupId;
            private Object memberLevel;
            private String memberSignupState;
            private Object modifierId;
            private Object modifyTime;
            private Object offlineTime;
            private String onlineTime;
            private int organizeId;
            private String signStateText;
            private String signinOffline;
            private int signupLimitNumber;
            private int signupNumber;
            private String signupState;
            private Object signupTime;
            private String startTime;
            private String startTimeStr;
            private String telephone;
            private Object useCrowd;
            private String verifyCode;
            private List<TagsBean> tags;

            public String getAcivityAddress() {
                return acivityAddress;
            }

            public void setAcivityAddress(String acivityAddress) {
                this.acivityAddress = acivityAddress;
            }

            public String getAcivityType() {
                return acivityType;
            }

            public void setAcivityType(String acivityType) {
                this.acivityType = acivityType;
            }

            public String getActivityEndtime() {
                return activityEndtime;
            }

            public void setActivityEndtime(String activityEndtime) {
                this.activityEndtime = activityEndtime;
            }

            public String getActivityIntroduce() {
                return activityIntroduce;
            }

            public void setActivityIntroduce(String activityIntroduce) {
                this.activityIntroduce = activityIntroduce;
            }

            public String getActivityPicture() {
                return activityPicture;
            }

            public void setActivityPicture(String activityPicture) {
                this.activityPicture = activityPicture;
            }

            public String getActivityStarttime() {
                return activityStarttime;
            }

            public void setActivityStarttime(String activityStarttime) {
                this.activityStarttime = activityStarttime;
            }

            public String getActivityTitle() {
                return activityTitle;
            }

            public void setActivityTitle(String activityTitle) {
                this.activityTitle = activityTitle;
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

            public double getEnrollFee() {
                return enrollFee;
            }

            public void setEnrollFee(double enrollFee) {
                this.enrollFee = enrollFee;
            }

            public double getEnrollScore() {
                return enrollScore;
            }

            public void setEnrollScore(double enrollScore) {
                this.enrollScore = enrollScore;
            }

            public String getExtraIntroduce() {
                return extraIntroduce;
            }

            public void setExtraIntroduce(String extraIntroduce) {
                this.extraIntroduce = extraIntroduce;
            }

            public String getIsAudit() {
                return isAudit;
            }

            public void setIsAudit(String isAudit) {
                this.isAudit = isAudit;
            }

            public String getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public boolean isIsSignup() {
                return isSignup;
            }

            public void setIsSignup(boolean isSignup) {
                this.isSignup = isSignup;
            }

            public int getJoinNumber() {
                return joinNumber;
            }

            public void setJoinNumber(int joinNumber) {
                this.joinNumber = joinNumber;
            }

            public int getMarketingActivitySignupId() {
                return marketingActivitySignupId;
            }

            public void setMarketingActivitySignupId(int marketingActivitySignupId) {
                this.marketingActivitySignupId = marketingActivitySignupId;
            }

            public Object getMemberLevel() {
                return memberLevel;
            }

            public void setMemberLevel(Object memberLevel) {
                this.memberLevel = memberLevel;
            }

            public String getMemberSignupState() {
                return memberSignupState;
            }

            public void setMemberSignupState(String memberSignupState) {
                this.memberSignupState = memberSignupState;
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

            public Object getOfflineTime() {
                return offlineTime;
            }

            public void setOfflineTime(Object offlineTime) {
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

            public String getSignStateText() {
                return signStateText;
            }

            public void setSignStateText(String signStateText) {
                this.signStateText = signStateText;
            }

            public String getSigninOffline() {
                return signinOffline;
            }

            public void setSigninOffline(String signinOffline) {
                this.signinOffline = signinOffline;
            }

            public int getSignupLimitNumber() {
                return signupLimitNumber;
            }

            public void setSignupLimitNumber(int signupLimitNumber) {
                this.signupLimitNumber = signupLimitNumber;
            }

            public int getSignupNumber() {
                return signupNumber;
            }

            public void setSignupNumber(int signupNumber) {
                this.signupNumber = signupNumber;
            }

            public String getSignupState() {
                return signupState;
            }

            public void setSignupState(String signupState) {
                this.signupState = signupState;
            }

            public Object getSignupTime() {
                return signupTime;
            }

            public void setSignupTime(Object signupTime) {
                this.signupTime = signupTime;
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

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public Object getUseCrowd() {
                return useCrowd;
            }

            public void setUseCrowd(Object useCrowd) {
                this.useCrowd = useCrowd;
            }

            public String getVerifyCode() {
                return verifyCode;
            }

            public void setVerifyCode(String verifyCode) {
                this.verifyCode = verifyCode;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public static class TagsBean {
                /**
                 * tagType : public
                 * tagName : 西湖
                 */

                private String tagType;
                private String tagName;

                public String getTagType() {
                    return tagType;
                }

                public void setTagType(String tagType) {
                    this.tagType = tagType;
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
}
