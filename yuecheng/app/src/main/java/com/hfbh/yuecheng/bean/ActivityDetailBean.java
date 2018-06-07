package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/7 14:00
 * Email：1993911441@qq.com
 * Describe：
 */
public class ActivityDetailBean {

    /**
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : {"statistic":null,"member":{"balanceScore":39990,"birthday":null,"cardLevelId":null,"entityCardId":"600104165","idCardNumber":null,"isDelete":"N","isQuit":"N","listMemberExtraInfo":null,"memberBirthday":"1997-01-01 00:00:00","memberBirthdayBegin":null,"memberBirthdayEnd":null,"memberCardNo":"9900000115","memberCarnumber":null,"memberCity":"杭州市","memberCountry":"下城区","memberHead":"http://yjwang.wmalle.com/image/20180604/8616041263101812.png","memberId":179178,"memberName":null,"memberNickName":"6688875","memberPhone":"15167168495","memberProvince":"浙江省","memberScore":214,"memberSex":"MAN","memberSource":"APP_ORDINARY_FOCUS","memberType":"MEMBER","modifyTime":"2018-06-02 13:05:55","modifyTimeBegin":null,"modifyTimeEnd":null,"openId":null,"organizeId":0,"registerOrganizeId":null,"registerTime":"2018-05-30 16:21:35","registerTimeBegin":null,"registerTimeEnd":null,"subscribeTime":null,"subscribeTimeBegin":null,"subscribeTimeEnd":null,"useScore":192},"seconds":-1,"signupDo":{"acivityAddress":"1","acivityType":"NONEED","activityEndtime":"2018-04-30 00:00:00","activityIntroduce":"21","activityPicture":"http://m.hfbh.com.cn/image/20180425/1312842440275216.jpg","activityStarttime":"2018-04-28 00:00:00","activityTitle":"不要报名","createTime":"2018-04-25 15:30:31","creatorId":4,"endTime":"2018-04-26 00:00:00","endTimeStr":"2018/04/26","enrollFee":null,"enrollScore":null,"extraIntroduce":"","isAudit":"N","isDelete":"N","isSignup":null,"joinNumber":0,"marketingActivitySignupId":397,"memberLevel":null,"memberSignupState":"活动已结束","modifierId":4,"modifyTime":"2018-04-27 18:02:35","offlineTime":null,"onlineTime":"2018-04-27 18:02:35","organizeId":2,"signStateText":"已结束","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":0,"signupState":"END","signupTime":null,"startTime":"2018-04-18 00:00:00","startTimeStr":"2018/04/18","tags":[{"tagType":"public","tagName":"五一欢庆"}],"telephone":"15044512366","useCrowd":"MEMBER","verifyCode":null},"memberType":"MEMBER"}
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
         * statistic : null
         * member : {"balanceScore":39990,"birthday":null,"cardLevelId":null,"entityCardId":"600104165","idCardNumber":null,"isDelete":"N","isQuit":"N","listMemberExtraInfo":null,"memberBirthday":"1997-01-01 00:00:00","memberBirthdayBegin":null,"memberBirthdayEnd":null,"memberCardNo":"9900000115","memberCarnumber":null,"memberCity":"杭州市","memberCountry":"下城区","memberHead":"http://yjwang.wmalle.com/image/20180604/8616041263101812.png","memberId":179178,"memberName":null,"memberNickName":"6688875","memberPhone":"15167168495","memberProvince":"浙江省","memberScore":214,"memberSex":"MAN","memberSource":"APP_ORDINARY_FOCUS","memberType":"MEMBER","modifyTime":"2018-06-02 13:05:55","modifyTimeBegin":null,"modifyTimeEnd":null,"openId":null,"organizeId":0,"registerOrganizeId":null,"registerTime":"2018-05-30 16:21:35","registerTimeBegin":null,"registerTimeEnd":null,"subscribeTime":null,"subscribeTimeBegin":null,"subscribeTimeEnd":null,"useScore":192}
         * seconds : -1
         * signupDo : {"acivityAddress":"1","acivityType":"NONEED","activityEndtime":"2018-04-30 00:00:00","activityIntroduce":"21","activityPicture":"http://m.hfbh.com.cn/image/20180425/1312842440275216.jpg","activityStarttime":"2018-04-28 00:00:00","activityTitle":"不要报名","createTime":"2018-04-25 15:30:31","creatorId":4,"endTime":"2018-04-26 00:00:00","endTimeStr":"2018/04/26","enrollFee":null,"enrollScore":null,"extraIntroduce":"","isAudit":"N","isDelete":"N","isSignup":null,"joinNumber":0,"marketingActivitySignupId":397,"memberLevel":null,"memberSignupState":"活动已结束","modifierId":4,"modifyTime":"2018-04-27 18:02:35","offlineTime":null,"onlineTime":"2018-04-27 18:02:35","organizeId":2,"signStateText":"已结束","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":0,"signupState":"END","signupTime":null,"startTime":"2018-04-18 00:00:00","startTimeStr":"2018/04/18","tags":[{"tagType":"public","tagName":"五一欢庆"}],"telephone":"15044512366","useCrowd":"MEMBER","verifyCode":null}
         * memberType : MEMBER
         */

        private Object statistic;
        private MemberBean member;
        private int seconds;
        private SignupDoBean signupDo;
        private String memberType;

        public Object getStatistic() {
            return statistic;
        }

        public void setStatistic(Object statistic) {
            this.statistic = statistic;
        }

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public SignupDoBean getSignupDo() {
            return signupDo;
        }

        public void setSignupDo(SignupDoBean signupDo) {
            this.signupDo = signupDo;
        }

        public String getMemberType() {
            return memberType;
        }

        public void setMemberType(String memberType) {
            this.memberType = memberType;
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
             * memberBirthday : 1997-01-01 00:00:00
             * memberBirthdayBegin : null
             * memberBirthdayEnd : null
             * memberCardNo : 9900000115
             * memberCarnumber : null
             * memberCity : 杭州市
             * memberCountry : 下城区
             * memberHead : http://yjwang.wmalle.com/image/20180604/8616041263101812.png
             * memberId : 179178
             * memberName : null
             * memberNickName : 6688875
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

        public static class SignupDoBean {
            /**
             * acivityAddress : 1
             * acivityType : NONEED
             * activityEndtime : 2018-04-30 00:00:00
             * activityIntroduce : 21
             * activityPicture : http://m.hfbh.com.cn/image/20180425/1312842440275216.jpg
             * activityStarttime : 2018-04-28 00:00:00
             * activityTitle : 不要报名
             * createTime : 2018-04-25 15:30:31
             * creatorId : 4
             * endTime : 2018-04-26 00:00:00
             * endTimeStr : 2018/04/26
             * enrollFee : null
             * enrollScore : null
             * extraIntroduce :
             * isAudit : N
             * isDelete : N
             * isSignup : null
             * joinNumber : 0
             * marketingActivitySignupId : 397
             * memberLevel : null
             * memberSignupState : 活动已结束
             * modifierId : 4
             * modifyTime : 2018-04-27 18:02:35
             * offlineTime : null
             * onlineTime : 2018-04-27 18:02:35
             * organizeId : 2
             * signStateText : 已结束
             * signinOffline : OPEN
             * signupLimitNumber : 0
             * signupNumber : 0
             * signupState : END
             * signupTime : null
             * startTime : 2018-04-18 00:00:00
             * startTimeStr : 2018/04/18
             * tags : [{"tagType":"public","tagName":"五一欢庆"}]
             * telephone : 15044512366
             * useCrowd : MEMBER
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
            private int modifierId;
            private String modifyTime;
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
            private String useCrowd;
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

            public int getModifierId() {
                return modifierId;
            }

            public void setModifierId(int modifierId) {
                this.modifierId = modifierId;
            }

            public String getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(String modifyTime) {
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

            public String getUseCrowd() {
                return useCrowd;
            }

            public void setUseCrowd(String useCrowd) {
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
                 * tagName : 五一欢庆
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
