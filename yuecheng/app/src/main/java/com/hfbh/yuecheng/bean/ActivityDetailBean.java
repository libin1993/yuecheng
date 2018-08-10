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
     * hash : 36b5876d897940bea6558f101dcd8fcb
     * data : {"statistic":{"auditState":"AUDIT","auditStateText":null,"createTime":"2018-08-09 15:15:07","creatorId":null,"endTimeText":null,"isDelete":"N","marketingActivitySignupId":225,"marketingActivitySignupStatisticsId":226,"memberId":"72","modifierId":null,"modifyTime":null,"name":"222222","organizeId":2,"phone":"15167168495","signupTime":"2018-08-09 15:15:07","startTimeText":null,"state":"UNDESTROY","stateText":null,"verifyCode":"vKrPM7w5"},"member":{"balanceScore":5835,"birthday":null,"cardLevelId":null,"entityCardId":"600104165","idCardNumber":null,"isDelete":"N","isQuit":"N","listMemberExtraInfo":null,"memberBirthday":"1980-01-01 00:00:00","memberBirthdayBegin":null,"memberBirthdayEnd":null,"memberCardNo":"9900000115","memberCarnumber":null,"memberCity":"黄冈市","memberCountry":"蕲春县","memberHead":"http://ceshi.wmalle.com/image/20180703/1548150014946696.png","memberId":72,"memberName":null,"memberNickName":"几点的车吃","memberPhone":"15167168495","memberProvince":"湖北","memberScore":1007,"memberSex":"MAN","memberSource":"APP_ORDINARY_FOCUS","memberType":"MEMBER","modifyTime":null,"modifyTimeBegin":null,"modifyTimeEnd":null,"openId":null,"organizeId":0,"registerOrganizeId":null,"registerTime":"2018-06-20 15:34:45","registerTimeBegin":null,"registerTimeEnd":null,"subscribeTime":null,"subscribeTimeBegin":null,"subscribeTimeEnd":null,"useScore":62},"order":{"activitySignupPayId":81,"createTime":"2018-08-09 15:15:23","data":null,"enrollFee":0.03,"marketingActivitySignupId":225,"memberId":72,"mobile":"15167168495","openId":"36b5876d897940bea6558f101dcd8fcb","organizeId":2,"payTime":null,"realname":"222222","state":0,"tradeNo":null,"tranNo":"6001027453268823646208","userCode":null,"wechatTradeNo":null},"seconds":1845846,"signupDo":{"acivityAddress":"西湖区西斗门路3号天堂软件园10楼慧优科技","acivityType":"CASH","activityEndtime":"2018-09-30 00:00:00","activityIntroduce":"阿斯达","activityPicture":"http://ceshi.wmalle.com/image/20180809/4736314399931896.jpg","activityStarttime":"2018-08-09 00:00:00","activityTitle":"现金活动5","createTime":"2018-08-09 09:49:18","creatorId":4,"endTime":"2018-08-31 00:00:00","endTimeStr":"2018/08/31","enrollFee":0.03,"enrollScore":0,"extraIntroduce":"1111","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":225,"memberLevel":null,"memberSignupState":"去报名","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-08-09 09:49:18","organizeId":2,"signStateText":"已上架","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":1,"signupState":"ONLINE","signupTime":null,"startTime":"2018-08-01 00:00:00","startTimeStr":"2018/08/01","tags":[{"tagType":"public","tagName":"测试"}],"telephone":"","useCrowd":null,"verifyCode":null},"memberType":"MEMBER","signupStatId":226}
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
         * statistic : {"auditState":"AUDIT","auditStateText":null,"createTime":"2018-08-09 15:15:07","creatorId":null,"endTimeText":null,"isDelete":"N","marketingActivitySignupId":225,"marketingActivitySignupStatisticsId":226,"memberId":"72","modifierId":null,"modifyTime":null,"name":"222222","organizeId":2,"phone":"15167168495","signupTime":"2018-08-09 15:15:07","startTimeText":null,"state":"UNDESTROY","stateText":null,"verifyCode":"vKrPM7w5"}
         * member : {"balanceScore":5835,"birthday":null,"cardLevelId":null,"entityCardId":"600104165","idCardNumber":null,"isDelete":"N","isQuit":"N","listMemberExtraInfo":null,"memberBirthday":"1980-01-01 00:00:00","memberBirthdayBegin":null,"memberBirthdayEnd":null,"memberCardNo":"9900000115","memberCarnumber":null,"memberCity":"黄冈市","memberCountry":"蕲春县","memberHead":"http://ceshi.wmalle.com/image/20180703/1548150014946696.png","memberId":72,"memberName":null,"memberNickName":"几点的车吃","memberPhone":"15167168495","memberProvince":"湖北","memberScore":1007,"memberSex":"MAN","memberSource":"APP_ORDINARY_FOCUS","memberType":"MEMBER","modifyTime":null,"modifyTimeBegin":null,"modifyTimeEnd":null,"openId":null,"organizeId":0,"registerOrganizeId":null,"registerTime":"2018-06-20 15:34:45","registerTimeBegin":null,"registerTimeEnd":null,"subscribeTime":null,"subscribeTimeBegin":null,"subscribeTimeEnd":null,"useScore":62}
         * order : {"activitySignupPayId":81,"createTime":"2018-08-09 15:15:23","data":null,"enrollFee":0.03,"marketingActivitySignupId":225,"memberId":72,"mobile":"15167168495","openId":"36b5876d897940bea6558f101dcd8fcb","organizeId":2,"payTime":null,"realname":"222222","state":0,"tradeNo":null,"tranNo":"6001027453268823646208","userCode":null,"wechatTradeNo":null}
         * seconds : 1845846
         * signupDo : {"acivityAddress":"西湖区西斗门路3号天堂软件园10楼慧优科技","acivityType":"CASH","activityEndtime":"2018-09-30 00:00:00","activityIntroduce":"阿斯达","activityPicture":"http://ceshi.wmalle.com/image/20180809/4736314399931896.jpg","activityStarttime":"2018-08-09 00:00:00","activityTitle":"现金活动5","createTime":"2018-08-09 09:49:18","creatorId":4,"endTime":"2018-08-31 00:00:00","endTimeStr":"2018/08/31","enrollFee":0.03,"enrollScore":0,"extraIntroduce":"1111","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":225,"memberLevel":null,"memberSignupState":"去报名","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-08-09 09:49:18","organizeId":2,"signStateText":"已上架","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":1,"signupState":"ONLINE","signupTime":null,"startTime":"2018-08-01 00:00:00","startTimeStr":"2018/08/01","tags":[{"tagType":"public","tagName":"测试"}],"telephone":"","useCrowd":null,"verifyCode":null}
         * memberType : MEMBER
         * signupStatId : 226
         */

        private StatisticBean statistic;
        private MemberBean member;
        private OrderBean order;
        private int seconds;
        private SignupDoBean signupDo;
        private String memberType;
        private int signupStatId;

        public StatisticBean getStatistic() {
            return statistic;
        }

        public void setStatistic(StatisticBean statistic) {
            this.statistic = statistic;
        }

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
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

        public int getSignupStatId() {
            return signupStatId;
        }

        public void setSignupStatId(int signupStatId) {
            this.signupStatId = signupStatId;
        }

        public static class StatisticBean {
            /**
             * auditState : AUDIT
             * auditStateText : null
             * createTime : 2018-08-09 15:15:07
             * creatorId : null
             * endTimeText : null
             * isDelete : N
             * marketingActivitySignupId : 225
             * marketingActivitySignupStatisticsId : 226
             * memberId : 72
             * modifierId : null
             * modifyTime : null
             * name : 222222
             * organizeId : 2
             * phone : 15167168495
             * signupTime : 2018-08-09 15:15:07
             * startTimeText : null
             * state : UNDESTROY
             * stateText : null
             * verifyCode : vKrPM7w5
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

        public static class MemberBean {
            /**
             * balanceScore : 5835
             * birthday : null
             * cardLevelId : null
             * entityCardId : 600104165
             * idCardNumber : null
             * isDelete : N
             * isQuit : N
             * listMemberExtraInfo : null
             * memberBirthday : 1980-01-01 00:00:00
             * memberBirthdayBegin : null
             * memberBirthdayEnd : null
             * memberCardNo : 9900000115
             * memberCarnumber : null
             * memberCity : 黄冈市
             * memberCountry : 蕲春县
             * memberHead : http://ceshi.wmalle.com/image/20180703/1548150014946696.png
             * memberId : 72
             * memberName : null
             * memberNickName : 几点的车吃
             * memberPhone : 15167168495
             * memberProvince : 湖北
             * memberScore : 1007
             * memberSex : MAN
             * memberSource : APP_ORDINARY_FOCUS
             * memberType : MEMBER
             * modifyTime : null
             * modifyTimeBegin : null
             * modifyTimeEnd : null
             * openId : null
             * organizeId : 0
             * registerOrganizeId : null
             * registerTime : 2018-06-20 15:34:45
             * registerTimeBegin : null
             * registerTimeEnd : null
             * subscribeTime : null
             * subscribeTimeBegin : null
             * subscribeTimeEnd : null
             * useScore : 62
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
            private Object modifyTime;
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

        public static class OrderBean {
            /**
             * activitySignupPayId : 81
             * createTime : 2018-08-09 15:15:23
             * data : null
             * enrollFee : 0.03
             * marketingActivitySignupId : 225
             * memberId : 72
             * mobile : 15167168495
             * openId : 36b5876d897940bea6558f101dcd8fcb
             * organizeId : 2
             * payTime : null
             * realname : 222222
             * state : 0
             * tradeNo : null
             * tranNo : 6001027453268823646208
             * userCode : null
             * wechatTradeNo : null
             */

            private int activitySignupPayId;
            private String createTime;
            private Object data;
            private double enrollFee;
            private int marketingActivitySignupId;
            private int memberId;
            private String mobile;
            private String openId;
            private int organizeId;
            private Object payTime;
            private String realname;
            private int state;
            private Object tradeNo;
            private String tranNo;
            private Object userCode;
            private Object wechatTradeNo;

            public int getActivitySignupPayId() {
                return activitySignupPayId;
            }

            public void setActivitySignupPayId(int activitySignupPayId) {
                this.activitySignupPayId = activitySignupPayId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getData() {
                return data;
            }

            public void setData(Object data) {
                this.data = data;
            }

            public double getEnrollFee() {
                return enrollFee;
            }

            public void setEnrollFee(double enrollFee) {
                this.enrollFee = enrollFee;
            }

            public int getMarketingActivitySignupId() {
                return marketingActivitySignupId;
            }

            public void setMarketingActivitySignupId(int marketingActivitySignupId) {
                this.marketingActivitySignupId = marketingActivitySignupId;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getOpenId() {
                return openId;
            }

            public void setOpenId(String openId) {
                this.openId = openId;
            }

            public int getOrganizeId() {
                return organizeId;
            }

            public void setOrganizeId(int organizeId) {
                this.organizeId = organizeId;
            }

            public Object getPayTime() {
                return payTime;
            }

            public void setPayTime(Object payTime) {
                this.payTime = payTime;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public Object getTradeNo() {
                return tradeNo;
            }

            public void setTradeNo(Object tradeNo) {
                this.tradeNo = tradeNo;
            }

            public String getTranNo() {
                return tranNo;
            }

            public void setTranNo(String tranNo) {
                this.tranNo = tranNo;
            }

            public Object getUserCode() {
                return userCode;
            }

            public void setUserCode(Object userCode) {
                this.userCode = userCode;
            }

            public Object getWechatTradeNo() {
                return wechatTradeNo;
            }

            public void setWechatTradeNo(Object wechatTradeNo) {
                this.wechatTradeNo = wechatTradeNo;
            }
        }

        public static class SignupDoBean {
            /**
             * acivityAddress : 西湖区西斗门路3号天堂软件园10楼慧优科技
             * acivityType : CASH
             * activityEndtime : 2018-09-30 00:00:00
             * activityIntroduce : 阿斯达
             * activityPicture : http://ceshi.wmalle.com/image/20180809/4736314399931896.jpg
             * activityStarttime : 2018-08-09 00:00:00
             * activityTitle : 现金活动5
             * createTime : 2018-08-09 09:49:18
             * creatorId : 4
             * endTime : 2018-08-31 00:00:00
             * endTimeStr : 2018/08/31
             * enrollFee : 0.03
             * enrollScore : 0
             * extraIntroduce : 1111
             * isAudit : N
             * isDelete : N
             * isSignup : false
             * joinNumber : 0
             * marketingActivitySignupId : 225
             * memberLevel : null
             * memberSignupState : 去报名
             * modifierId : null
             * modifyTime : null
             * offlineTime : null
             * onlineTime : 2018-08-09 09:49:18
             * organizeId : 2
             * signStateText : 已上架
             * signinOffline : OPEN
             * signupLimitNumber : 0
             * signupNumber : 1
             * signupState : ONLINE
             * signupTime : null
             * startTime : 2018-08-01 00:00:00
             * startTimeStr : 2018/08/01
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
            private int enrollScore;
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

            public int getEnrollScore() {
                return enrollScore;
            }

            public void setEnrollScore(int enrollScore) {
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
    }
}
