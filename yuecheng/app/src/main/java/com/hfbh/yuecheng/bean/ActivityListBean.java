package com.hfbh.yuecheng.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author：Libin on 2018/5/17 14:49
 * Email：1993911441@qq.com
 * Describe：
 */
public class ActivityListBean {


    /**
     * flag : true
     * page : {"pageNum":1,"pageSize":10,"pages":5,"total":41}
     * hash : 36b5876d897940bea6558f101dcd8fcb
     * data : [{"acivityAddress":"123","acivityType":"CASH","activityEndtime":"2018-06-30 00:00:00","activityIntroduce":"123123123","activityPicture":"http://ceshi.wmalle.com/image/20180621/549870177198900.png","activityStarttime":"2018-06-21 00:00:00","activityTitle":"活动9999","createTime":"2018-06-21 22:55:22","creatorId":4,"endTime":"2018-06-30 00:00:00","endTimeStr":"2018/06/30","enrollFee":0.01,"enrollScore":0,"extraIntroduce":"123123","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":87,"memberLevel":null,"memberSignupState":"去报名","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-06-21 00:00:00","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":0,"signupState":"DOING","signupTime":null,"startTime":"2018-06-21 00:00:00","startTimeStr":"2018/06/21","tags":[{"tagType":"public","tagName":"测试"}],"telephone":"","useCrowd":null,"verifyCode":null},{"acivityAddress":"12","acivityType":"NONEED","activityEndtime":"2018-06-30 00:00:00","activityIntroduce":"123","activityPicture":"http://ceshi.wmalle.com/image/20180621/549767050774457.png","activityStarttime":"2018-06-21 00:00:00","activityTitle":"活动778","createTime":"2018-06-21 22:53:26","creatorId":4,"endTime":"2018-06-30 00:00:00","endTimeStr":"2018/06/30","enrollFee":null,"enrollScore":0,"extraIntroduce":"","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":86,"memberLevel":null,"memberSignupState":"去报名","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-06-21 00:00:00","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":0,"signupState":"DOING","signupTime":null,"startTime":"2018-06-21 00:00:00","startTimeStr":"2018/06/21","tags":[{"tagType":"extra","tagName":"12566"}],"telephone":"","useCrowd":null,"verifyCode":null},{"acivityAddress":"1231321312","acivityType":"CASH","activityEndtime":"2018-06-30 00:00:00","activityIntroduce":"123123","activityPicture":"http://ceshi.wmalle.com/image/20180621/538729569102439.png","activityStarttime":"2018-06-21 00:00:00","activityTitle":"活动123123","createTime":"2018-06-21 19:50:49","creatorId":4,"endTime":"2018-06-22 00:00:00","endTimeStr":"2018/06/22","enrollFee":0.01,"enrollScore":0,"extraIntroduce":"123123","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":83,"memberLevel":null,"memberSignupState":"去报名","modifierId":4,"modifyTime":"2018-06-21 22:03:00","offlineTime":null,"onlineTime":"2018-06-21 00:00:00","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":0,"signupState":"DOING","signupTime":null,"startTime":"2018-06-21 00:00:00","startTimeStr":"2018/06/21","tags":[{"tagType":"public","tagName":"测试"}],"telephone":"","useCrowd":"MEMBER","verifyCode":null},{"acivityAddress":"ttt","acivityType":"CASH","activityEndtime":"2018-06-30 00:00:00","activityIntroduce":"1111","activityPicture":"http://ceshi.wmalle.com/image/20180621/529586399859147.png","activityStarttime":"2018-06-21 00:00:00","activityTitle":"aaaa","createTime":"2018-06-21 17:17:23","creatorId":4,"endTime":"2018-06-30 00:00:00","endTimeStr":"2018/06/30","enrollFee":0.01,"enrollScore":0,"extraIntroduce":"11","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":75,"memberLevel":null,"memberSignupState":"去报名","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-06-21 00:00:00","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":1,"signupNumber":0,"signupState":"DOING","signupTime":null,"startTime":"2018-06-21 00:00:00","startTimeStr":"2018/06/21","tags":[{"tagType":"public","tagName":"测试"}],"telephone":"","useCrowd":null,"verifyCode":null},{"acivityAddress":"啊飒飒","acivityType":"CASH","activityEndtime":"2018-06-29 00:00:00","activityIntroduce":"飒飒飒飒","activityPicture":"http://ceshi.wmalle.com/image/20180621/529354532211631.jpg","activityStarttime":"2018-06-21 00:00:00","activityTitle":"再加一次现金","createTime":"2018-06-21 17:13:19","creatorId":4,"endTime":"2018-06-29 00:00:00","endTimeStr":"2018/06/29","enrollFee":0.88,"enrollScore":0,"extraIntroduce":"","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":73,"memberLevel":null,"memberSignupState":"去报名","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-06-21 00:00:00","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":0,"signupState":"DOING","signupTime":null,"startTime":"2018-06-18 00:00:00","startTimeStr":"2018/06/18","tags":[{"tagType":"public","tagName":"测试"}],"telephone":"","useCrowd":null,"verifyCode":null},{"acivityAddress":"西湖区西斗门路3号天堂软件园10楼慧优科技","acivityType":"CASH","activityEndtime":"2018-06-30 00:00:00","activityIntroduce":"啊啊","activityPicture":"http://ceshi.wmalle.com/image/20180621/528458429950274.png","activityStarttime":"2018-06-21 00:00:00","activityTitle":"现金活动","createTime":"2018-06-21 16:58:54","creatorId":4,"endTime":"2018-06-29 00:00:00","endTimeStr":"2018/06/29","enrollFee":0.01,"enrollScore":0,"extraIntroduce":"123","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":70,"memberLevel":null,"memberSignupState":"去报名","modifierId":4,"modifyTime":"2018-06-21 16:59:26","offlineTime":null,"onlineTime":"2018-06-21 00:00:00","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":1,"signupNumber":0,"signupState":"DOING","signupTime":null,"startTime":"2018-06-21 00:00:00","startTimeStr":"2018/06/21","tags":[{"tagType":"public","tagName":"测试"}],"telephone":"","useCrowd":"MEMBER","verifyCode":null},{"acivityAddress":"tt","acivityType":"CASH","activityEndtime":"2018-06-30 00:00:00","activityIntroduce":"11","activityPicture":"http://ceshi.wmalle.com/image/20180621/523469789963962.png","activityStarttime":"2018-06-21 00:00:00","activityTitle":"aaa","createTime":"2018-06-21 15:35:35","creatorId":4,"endTime":"2018-06-30 00:00:00","endTimeStr":"2018/06/30","enrollFee":0.01,"enrollScore":0,"extraIntroduce":"111","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":65,"memberLevel":null,"memberSignupState":"去报名","modifierId":4,"modifyTime":"2018-06-21 15:44:46","offlineTime":null,"onlineTime":"2018-06-21 00:00:00","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":0,"signupState":"DOING","signupTime":null,"startTime":"2018-06-21 00:00:00","startTimeStr":"2018/06/21","tags":[{"tagType":"extra","tagName":"1"}],"telephone":"","useCrowd":"MEMBER","verifyCode":null},{"acivityAddress":"活动123","acivityType":"SCORE","activityEndtime":"2018-06-29 00:00:00","activityIntroduce":"123","activityPicture":"http://ceshi.wmalle.com/image/20180621/521183179290675.png","activityStarttime":"2018-06-22 00:00:00","activityTitle":"未开始活动","createTime":"2018-06-21 15:01:32","creatorId":4,"endTime":"2018-06-28 00:00:00","endTimeStr":"2018/06/28","enrollFee":null,"enrollScore":3,"extraIntroduce":"","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":62,"memberLevel":null,"memberSignupState":"去报名","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-06-21 00:00:00","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":0,"signupState":"DOING","signupTime":null,"startTime":"2018-06-22 00:00:00","startTimeStr":"2018/06/22","tags":[{"tagType":"extra","tagName":"6666"}],"telephone":"","useCrowd":null,"verifyCode":null},{"acivityAddress":"12389182391杭州","acivityType":"CASH","activityEndtime":"2018-06-30 00:00:00","activityIntroduce":"12312","activityPicture":"http://ceshi.wmalle.com/image/20180621/520564451601717.png","activityStarttime":"2018-06-21 00:00:00","activityTitle":"现金报名2","createTime":"2018-06-21 14:47:14","creatorId":4,"endTime":"2018-06-21 16:46:00","endTimeStr":"2018/06/21","enrollFee":0.01,"enrollScore":0,"extraIntroduce":"","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":61,"memberLevel":null,"memberSignupState":"去报名","modifierId":4,"modifyTime":"2018-06-21 14:56:31","offlineTime":null,"onlineTime":"2018-06-21 00:00:00","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":5,"signupNumber":0,"signupState":"DOING","signupTime":null,"startTime":"2018-06-21 00:00:00","startTimeStr":"2018/06/21","tags":[{"tagType":"extra","tagName":"666"}],"telephone":"","useCrowd":"MEMBER","verifyCode":null},{"acivityAddress":"杭州市西湖区","acivityType":"CASH","activityEndtime":"2018-06-30 00:00:00","activityIntroduce":"1、飞机拉我及哦哇","activityPicture":"http://ceshi.wmalle.com/image/20180621/520452684929935.png","activityStarttime":"2018-06-21 00:00:00","activityTitle":"现金报名活动","createTime":"2018-06-21 14:45:29","creatorId":4,"endTime":"2018-06-21 16:45:00","endTimeStr":"2018/06/21","enrollFee":0.01,"enrollScore":0,"extraIntroduce":"","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":60,"memberLevel":null,"memberSignupState":"去报名","modifierId":4,"modifyTime":"2018-06-21 14:46:09","offlineTime":null,"onlineTime":"2018-06-21 00:00:00","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":10,"signupNumber":0,"signupState":"DOING","signupTime":null,"startTime":"2018-06-21 00:00:00","startTimeStr":"2018/06/21","tags":[{"tagType":"extra","tagName":"现金报名"}],"telephone":"13533334444","useCrowd":"MEMBER","verifyCode":null}]
     * tagList : [{"createTime":"2018-06-21 16:53:39","creatorId":4,"id":1,"isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"showSerial":1,"tagName":"测试"}]
     * code : 0
     */

    private boolean flag;
    private PageBean page;
    private String hash;
    private int code;
    private List<DataBean> data;
    private List<TagListBean> tagList;

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

    public List<TagListBean> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagListBean> tagList) {
        this.tagList = tagList;
    }

    public static class PageBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * pages : 5
         * total : 41
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
         * acivityAddress : 123
         * acivityType : CASH
         * activityEndtime : 2018-06-30 00:00:00
         * activityIntroduce : 123123123
         * activityPicture : http://ceshi.wmalle.com/image/20180621/549870177198900.png
         * activityStarttime : 2018-06-21 00:00:00
         * activityTitle : 活动9999
         * createTime : 2018-06-21 22:55:22
         * creatorId : 4
         * endTime : 2018-06-30 00:00:00
         * endTimeStr : 2018/06/30
         * enrollFee : 0.01
         * enrollScore : 0
         * extraIntroduce : 123123
         * isAudit : N
         * isDelete : N
         * isSignup : false
         * joinNumber : 0
         * marketingActivitySignupId : 87
         * memberLevel : null
         * memberSignupState : 去报名
         * modifierId : null
         * modifyTime : null
         * offlineTime : null
         * onlineTime : 2018-06-21 00:00:00
         * organizeId : 2
         * signStateText : 进行中
         * signinOffline : OPEN
         * signupLimitNumber : 0
         * signupNumber : 0
         * signupState : DOING
         * signupTime : null
         * startTime : 2018-06-21 00:00:00
         * startTimeStr : 2018/06/21
         * tags : [{"tagType":"public","tagName":"测试"}]
         * telephone :
         * useCrowd : null
         * verifyCode : null
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
        private Object verifyCode;
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

        public Object getVerifyCode() {
            return verifyCode;
        }

        public void setVerifyCode(Object verifyCode) {
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
             * tagName : 测试
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

    public static class TagListBean {
        /**
         * createTime : 2018-06-21 16:53:39
         * creatorId : 4
         * id : 1
         * isDelete : N
         * modifierId : null
         * modifyTime : null
         * organizeId : 2
         * showSerial : 1
         * tagName : 测试
         */

        private String createTime;
        private int creatorId;
        private int id;
        private String isDelete;
        private Object modifierId;
        private Object modifyTime;
        private int organizeId;
        private int showSerial;
        private String tagName;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
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

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
            this.organizeId = organizeId;
        }

        public int getShowSerial() {
            return showSerial;
        }

        public void setShowSerial(int showSerial) {
            this.showSerial = showSerial;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }
    }
}
