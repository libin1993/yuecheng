package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/8 14:44
 * Email：1993911441@qq.com
 * Describe：
 */
public class EnrollActivityBean {


    /**
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : {"member":{"balanceScore":39990,"birthday":null,"cardLevelId":null,"entityCardId":"600104165","idCardNumber":null,"isDelete":"N","isQuit":"N","listMemberExtraInfo":null,"memberBirthday":"1999-01-01 00:00:00","memberBirthdayBegin":null,"memberBirthdayEnd":null,"memberCardNo":"9900000115","memberCarnumber":null,"memberCity":"杭州市","memberCountry":"下城区","memberHead":"http://yjwang.wmalle.com/image/20180604/8616041263101812.png","memberId":179178,"memberName":null,"memberNickName":"呼呼呼","memberPhone":"15167168495","memberProvince":"浙江省","memberScore":214,"memberSex":"MAN","memberSource":"APP_ORDINARY_FOCUS","memberType":"MEMBER","modifyTime":"2018-06-02 13:05:55","modifyTimeBegin":null,"modifyTimeEnd":null,"openId":null,"organizeId":0,"registerOrganizeId":null,"registerTime":"2018-05-30 16:21:35","registerTimeBegin":null,"registerTimeEnd":null,"subscribeTime":null,"subscribeTimeBegin":null,"subscribeTimeEnd":null,"useScore":192},"signupId":null,"signupActivity":{"acivityAddress":"速度速度","acivityType":"FREE","activityEndtime":"2018-07-31 00:00:00","activityIntroduce":"我去打球我我顶我顶","activityPicture":"http://yjwang.wmalle.com/image/20180606/8797272413088819.jpg","activityStarttime":"2018-06-06 00:00:00","activityTitle":"免费0606","createTime":"2018-06-06 19:52:03","creatorId":4,"endTime":"2018-07-30 00:00:00","endTimeStr":"2018/07/30","enrollFee":null,"enrollScore":null,"extraIntroduce":"速度速度实打实大苏打","isAudit":"N","isDelete":"N","isSignup":null,"joinNumber":0,"marketingActivitySignupId":624,"memberLevel":null,"memberSignupState":"去报名","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-06-06 19:52:03","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":100,"signupNumber":0,"signupState":"DOING","signupTime":null,"startTime":"2018-06-01 00:00:00","startTimeStr":"2018/06/01","tags":[{"tagType":"public","tagName":"西湖"},{"tagType":"public","tagName":"粽子"},{"tagType":"public","tagName":"清明节"}],"telephone":"15000002222","useCrowd":null,"verifyCode":null},"memberLevelObj":null,"signupStatistics":null,"columnIsActive":"N","optionList":[{"id":374,"itemList":[{"id":437,"title":"男"},{"id":438,"title":"女"}],"maxNum":null,"minNum":null,"required":"true","title":"性别","type":"RADIO"},{"id":375,"itemList":[],"maxNum":null,"minNum":null,"required":"true","title":"年龄","type":"SINGLE"}]}
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
         * member : {"balanceScore":39990,"birthday":null,"cardLevelId":null,"entityCardId":"600104165","idCardNumber":null,"isDelete":"N","isQuit":"N","listMemberExtraInfo":null,"memberBirthday":"1999-01-01 00:00:00","memberBirthdayBegin":null,"memberBirthdayEnd":null,"memberCardNo":"9900000115","memberCarnumber":null,"memberCity":"杭州市","memberCountry":"下城区","memberHead":"http://yjwang.wmalle.com/image/20180604/8616041263101812.png","memberId":179178,"memberName":null,"memberNickName":"呼呼呼","memberPhone":"15167168495","memberProvince":"浙江省","memberScore":214,"memberSex":"MAN","memberSource":"APP_ORDINARY_FOCUS","memberType":"MEMBER","modifyTime":"2018-06-02 13:05:55","modifyTimeBegin":null,"modifyTimeEnd":null,"openId":null,"organizeId":0,"registerOrganizeId":null,"registerTime":"2018-05-30 16:21:35","registerTimeBegin":null,"registerTimeEnd":null,"subscribeTime":null,"subscribeTimeBegin":null,"subscribeTimeEnd":null,"useScore":192}
         * signupId : null
         * signupActivity : {"acivityAddress":"速度速度","acivityType":"FREE","activityEndtime":"2018-07-31 00:00:00","activityIntroduce":"我去打球我我顶我顶","activityPicture":"http://yjwang.wmalle.com/image/20180606/8797272413088819.jpg","activityStarttime":"2018-06-06 00:00:00","activityTitle":"免费0606","createTime":"2018-06-06 19:52:03","creatorId":4,"endTime":"2018-07-30 00:00:00","endTimeStr":"2018/07/30","enrollFee":null,"enrollScore":null,"extraIntroduce":"速度速度实打实大苏打","isAudit":"N","isDelete":"N","isSignup":null,"joinNumber":0,"marketingActivitySignupId":624,"memberLevel":null,"memberSignupState":"去报名","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-06-06 19:52:03","organizeId":2,"signStateText":"进行中","signinOffline":"OPEN","signupLimitNumber":100,"signupNumber":0,"signupState":"DOING","signupTime":null,"startTime":"2018-06-01 00:00:00","startTimeStr":"2018/06/01","tags":[{"tagType":"public","tagName":"西湖"},{"tagType":"public","tagName":"粽子"},{"tagType":"public","tagName":"清明节"}],"telephone":"15000002222","useCrowd":null,"verifyCode":null}
         * memberLevelObj : null
         * signupStatistics : null
         * columnIsActive : N
         * optionList : [{"id":374,"itemList":[{"id":437,"title":"男"},{"id":438,"title":"女"}],"maxNum":null,"minNum":null,"required":"true","title":"性别","type":"RADIO"},{"id":375,"itemList":[],"maxNum":null,"minNum":null,"required":"true","title":"年龄","type":"SINGLE"}]
         */

        private MemberBean member;
        private int signupId;
        private SignupActivityBean signupActivity;
        private Object memberLevelObj;
        private Object signupStatistics;
        private String columnIsActive;
        private List<OptionListBean> optionList;

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public int getSignupId() {
            return signupId;
        }

        public void setSignupId(int signupId) {
            this.signupId = signupId;
        }

        public SignupActivityBean getSignupActivity() {
            return signupActivity;
        }

        public void setSignupActivity(SignupActivityBean signupActivity) {
            this.signupActivity = signupActivity;
        }

        public Object getMemberLevelObj() {
            return memberLevelObj;
        }

        public void setMemberLevelObj(Object memberLevelObj) {
            this.memberLevelObj = memberLevelObj;
        }

        public Object getSignupStatistics() {
            return signupStatistics;
        }

        public void setSignupStatistics(Object signupStatistics) {
            this.signupStatistics = signupStatistics;
        }

        public String getColumnIsActive() {
            return columnIsActive;
        }

        public void setColumnIsActive(String columnIsActive) {
            this.columnIsActive = columnIsActive;
        }

        public List<OptionListBean> getOptionList() {
            return optionList;
        }

        public void setOptionList(List<OptionListBean> optionList) {
            this.optionList = optionList;
        }

        public static class MemberBean {
            /**
             * balanceScore : 39990
             * birthday : null
             * cardLevelId : null
             * entityCardId : 600104165
             * idCardNumber : null
             * isDelete : N
             * isQuit : N
             * listMemberExtraInfo : null
             * memberBirthday : 1999-01-01 00:00:00
             * memberBirthdayBegin : null
             * memberBirthdayEnd : null
             * memberCardNo : 9900000115
             * memberCarnumber : null
             * memberCity : 杭州市
             * memberCountry : 下城区
             * memberHead : http://yjwang.wmalle.com/image/20180604/8616041263101812.png
             * memberId : 179178
             * memberName : null
             * memberNickName : 呼呼呼
             * memberPhone : 15167168495
             * memberProvince : 浙江省
             * memberScore : 214
             * memberSex : MAN
             * memberSource : APP_ORDINARY_FOCUS
             * memberType : MEMBER
             * modifyTime : 2018-06-02 13:05:55
             * modifyTimeBegin : null
             * modifyTimeEnd : null
             * openId : null
             * organizeId : 0
             * registerOrganizeId : null
             * registerTime : 2018-05-30 16:21:35
             * registerTimeBegin : null
             * registerTimeEnd : null
             * subscribeTime : null
             * subscribeTimeBegin : null
             * subscribeTimeEnd : null
             * useScore : 192
             */

            private int balanceScore;
            private Object birthday;
            private Object cardLevelId;
            private String entityCardId;
            private Object idCardNumber;
            private String isDelete;
            private String isQuit;
            private Object listMemberExtraInfo;
            private String memberBirthday;
            private Object memberBirthdayBegin;
            private Object memberBirthdayEnd;
            private String memberCardNo;
            private Object memberCarnumber;
            private String memberCity;
            private String memberCountry;
            private String memberHead;
            private int memberId;
            private Object memberName;
            private String memberNickName;
            private String memberPhone;
            private String memberProvince;
            private int memberScore;
            private String memberSex;
            private String memberSource;
            private String memberType;
            private String modifyTime;
            private Object modifyTimeBegin;
            private Object modifyTimeEnd;
            private Object openId;
            private int organizeId;
            private Object registerOrganizeId;
            private String registerTime;
            private Object registerTimeBegin;
            private Object registerTimeEnd;
            private Object subscribeTime;
            private Object subscribeTimeBegin;
            private Object subscribeTimeEnd;
            private int useScore;

            public int getBalanceScore() {
                return balanceScore;
            }

            public void setBalanceScore(int balanceScore) {
                this.balanceScore = balanceScore;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public Object getCardLevelId() {
                return cardLevelId;
            }

            public void setCardLevelId(Object cardLevelId) {
                this.cardLevelId = cardLevelId;
            }

            public String getEntityCardId() {
                return entityCardId;
            }

            public void setEntityCardId(String entityCardId) {
                this.entityCardId = entityCardId;
            }

            public Object getIdCardNumber() {
                return idCardNumber;
            }

            public void setIdCardNumber(Object idCardNumber) {
                this.idCardNumber = idCardNumber;
            }

            public String getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public String getIsQuit() {
                return isQuit;
            }

            public void setIsQuit(String isQuit) {
                this.isQuit = isQuit;
            }

            public Object getListMemberExtraInfo() {
                return listMemberExtraInfo;
            }

            public void setListMemberExtraInfo(Object listMemberExtraInfo) {
                this.listMemberExtraInfo = listMemberExtraInfo;
            }

            public String getMemberBirthday() {
                return memberBirthday;
            }

            public void setMemberBirthday(String memberBirthday) {
                this.memberBirthday = memberBirthday;
            }

            public Object getMemberBirthdayBegin() {
                return memberBirthdayBegin;
            }

            public void setMemberBirthdayBegin(Object memberBirthdayBegin) {
                this.memberBirthdayBegin = memberBirthdayBegin;
            }

            public Object getMemberBirthdayEnd() {
                return memberBirthdayEnd;
            }

            public void setMemberBirthdayEnd(Object memberBirthdayEnd) {
                this.memberBirthdayEnd = memberBirthdayEnd;
            }

            public String getMemberCardNo() {
                return memberCardNo;
            }

            public void setMemberCardNo(String memberCardNo) {
                this.memberCardNo = memberCardNo;
            }

            public Object getMemberCarnumber() {
                return memberCarnumber;
            }

            public void setMemberCarnumber(Object memberCarnumber) {
                this.memberCarnumber = memberCarnumber;
            }

            public String getMemberCity() {
                return memberCity;
            }

            public void setMemberCity(String memberCity) {
                this.memberCity = memberCity;
            }

            public String getMemberCountry() {
                return memberCountry;
            }

            public void setMemberCountry(String memberCountry) {
                this.memberCountry = memberCountry;
            }

            public String getMemberHead() {
                return memberHead;
            }

            public void setMemberHead(String memberHead) {
                this.memberHead = memberHead;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public Object getMemberName() {
                return memberName;
            }

            public void setMemberName(Object memberName) {
                this.memberName = memberName;
            }

            public String getMemberNickName() {
                return memberNickName;
            }

            public void setMemberNickName(String memberNickName) {
                this.memberNickName = memberNickName;
            }

            public String getMemberPhone() {
                return memberPhone;
            }

            public void setMemberPhone(String memberPhone) {
                this.memberPhone = memberPhone;
            }

            public String getMemberProvince() {
                return memberProvince;
            }

            public void setMemberProvince(String memberProvince) {
                this.memberProvince = memberProvince;
            }

            public int getMemberScore() {
                return memberScore;
            }

            public void setMemberScore(int memberScore) {
                this.memberScore = memberScore;
            }

            public String getMemberSex() {
                return memberSex;
            }

            public void setMemberSex(String memberSex) {
                this.memberSex = memberSex;
            }

            public String getMemberSource() {
                return memberSource;
            }

            public void setMemberSource(String memberSource) {
                this.memberSource = memberSource;
            }

            public String getMemberType() {
                return memberType;
            }

            public void setMemberType(String memberType) {
                this.memberType = memberType;
            }

            public String getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(String modifyTime) {
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

            public int getOrganizeId() {
                return organizeId;
            }

            public void setOrganizeId(int organizeId) {
                this.organizeId = organizeId;
            }

            public Object getRegisterOrganizeId() {
                return registerOrganizeId;
            }

            public void setRegisterOrganizeId(Object registerOrganizeId) {
                this.registerOrganizeId = registerOrganizeId;
            }

            public String getRegisterTime() {
                return registerTime;
            }

            public void setRegisterTime(String registerTime) {
                this.registerTime = registerTime;
            }

            public Object getRegisterTimeBegin() {
                return registerTimeBegin;
            }

            public void setRegisterTimeBegin(Object registerTimeBegin) {
                this.registerTimeBegin = registerTimeBegin;
            }

            public Object getRegisterTimeEnd() {
                return registerTimeEnd;
            }

            public void setRegisterTimeEnd(Object registerTimeEnd) {
                this.registerTimeEnd = registerTimeEnd;
            }

            public Object getSubscribeTime() {
                return subscribeTime;
            }

            public void setSubscribeTime(Object subscribeTime) {
                this.subscribeTime = subscribeTime;
            }

            public Object getSubscribeTimeBegin() {
                return subscribeTimeBegin;
            }

            public void setSubscribeTimeBegin(Object subscribeTimeBegin) {
                this.subscribeTimeBegin = subscribeTimeBegin;
            }

            public Object getSubscribeTimeEnd() {
                return subscribeTimeEnd;
            }

            public void setSubscribeTimeEnd(Object subscribeTimeEnd) {
                this.subscribeTimeEnd = subscribeTimeEnd;
            }

            public int getUseScore() {
                return useScore;
            }

            public void setUseScore(int useScore) {
                this.useScore = useScore;
            }
        }

        public static class SignupActivityBean {
            /**
             * acivityAddress : 速度速度
             * acivityType : FREE
             * activityEndtime : 2018-07-31 00:00:00
             * activityIntroduce : 我去打球我我顶我顶
             * activityPicture : http://yjwang.wmalle.com/image/20180606/8797272413088819.jpg
             * activityStarttime : 2018-06-06 00:00:00
             * activityTitle : 免费0606
             * createTime : 2018-06-06 19:52:03
             * creatorId : 4
             * endTime : 2018-07-30 00:00:00
             * endTimeStr : 2018/07/30
             * enrollFee : null
             * enrollScore : null
             * extraIntroduce : 速度速度实打实大苏打
             * isAudit : N
             * isDelete : N
             * isSignup : null
             * joinNumber : 0
             * marketingActivitySignupId : 624
             * memberLevel : null
             * memberSignupState : 去报名
             * modifierId : null
             * modifyTime : null
             * offlineTime : null
             * onlineTime : 2018-06-06 19:52:03
             * organizeId : 2
             * signStateText : 进行中
             * signinOffline : OPEN
             * signupLimitNumber : 100
             * signupNumber : 0
             * signupState : DOING
             * signupTime : null
             * startTime : 2018-06-01 00:00:00
             * startTimeStr : 2018/06/01
             * tags : [{"tagType":"public","tagName":"西湖"},{"tagType":"public","tagName":"粽子"},{"tagType":"public","tagName":"清明节"}]
             * telephone : 15000002222
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
            private Object isSignup;
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

            public Object getIsSignup() {
                return isSignup;
            }

            public void setIsSignup(Object isSignup) {
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

        public static class OptionListBean {
            /**
             * id : 374
             * itemList : [{"id":437,"title":"男"},{"id":438,"title":"女"}]
             * maxNum : null
             * minNum : null
             * required : true
             * title : 性别
             * type : RADIO
             */

            private int id;
            private Object maxNum;
            private Object minNum;
            private String required;
            private String title;
            private String type;
            private boolean isFinish;

            public boolean isFinish() {
                return isFinish;
            }

            public void setFinish(boolean finish) {
                isFinish = finish;
            }

            private List<ItemListBean> itemList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getMaxNum() {
                return maxNum;
            }

            public void setMaxNum(Object maxNum) {
                this.maxNum = maxNum;
            }

            public Object getMinNum() {
                return minNum;
            }

            public void setMinNum(Object minNum) {
                this.minNum = minNum;
            }

            public String getRequired() {
                return required;
            }

            public void setRequired(String required) {
                this.required = required;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<ItemListBean> getItemList() {
                return itemList;
            }

            public void setItemList(List<ItemListBean> itemList) {
                this.itemList = itemList;
            }

            public static class ItemListBean {
                /**
                 * id : 437
                 * title : 男
                 */

                private int id;
                private String title;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }
    }
}
