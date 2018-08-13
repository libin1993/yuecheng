package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/7/16 17:02
 * Email：1993911441@qq.com
 * Describe：
 */
public class ActivityRecordBean {


    /**
     * flag : true
     * hash : 848489fbe87c4e88a44c2e6ad0eb8c68
     * data : {"record":{"auditState":"AUDIT","createTime":"2018-08-09 16:38:21","creatorId":null,"endTimeText":null,"infoList":[],"isDelete":"N","marketingActivitySignupId":775,"marketingActivitySignupStatisticsId":1081,"memberHeadPic":null,"memberId":"181166","memberSex":"MAN","modifierId":null,"modifyTime":null,"name":"1","organizeId":2,"phone":"15167168495","signupTime":"2018-08-09 16:38:21","startTimeText":null,"state":"UNDESTROY","stateText":null,"verifyCode":"FXguGvH8"},"order":{"activitySignupPayId":610,"appType":"Android","createTime":"2018-08-09 16:38:26","data":null,"enrollFee":0.01,"marketingActivitySignupId":775,"memberId":181166,"mobile":"15167168495","openId":"848489fbe87c4e88a44c2e6ad0eb8c68","organizeId":2,"payTime":null,"realname":"1","state":0,"tradeNo":null,"tranNo":"6001027474169749315584","userCode":null,"wechatTradeNo":null},"memberValues":null,"cyOrder":{"createTime":"2018-08-09 16:38:26","id":801,"memberId":null,"orderNo":"6001027474169749315584","orderPayNo":null,"state":"PREPAID","transId":"203511575","transMoney":0.01,"type":"ACTIVITY"},"activity":{"acivityAddress":"123123123","acivityType":"CASH","activityEndtime":"2018-08-18 00:00:00","activityIntroduce":"111111111111112222222222222222222333333333333333333","activityPicture":"http://yjwang.wmalle.com/image/20180808/14220388533199809.png","activityStarttime":"2018-08-08 00:00:00","activityTitle":"活动报名1416","createTime":"2018-08-08 14:20:56","creatorId":4,"endTime":"2018-08-16 00:00:00","endTimeStr":"2018/08/16","enrollFee":0.02,"enrollScore":0,"extraIntroduce":"1231321","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":775,"memberLevel":null,"memberSignupState":"去报名","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-08-08 14:20:56","organizeId":2,"signStateText":"已上架","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":1,"signupState":"ONLINE","signupTime":null,"startTime":"2018-08-08 00:00:00","startTimeStr":"2018/08/08","tags":[{"tagType":"public","tagName":"啦啦啦啦啦"},{"tagType":"public","tagName":"西湖"}],"telephone":"","useCrowd":null,"verifyCode":null}}
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
         * record : {"auditState":"AUDIT","createTime":"2018-08-09 16:38:21","creatorId":null,"endTimeText":null,"infoList":[],"isDelete":"N","marketingActivitySignupId":775,"marketingActivitySignupStatisticsId":1081,"memberHeadPic":null,"memberId":"181166","memberSex":"MAN","modifierId":null,"modifyTime":null,"name":"1","organizeId":2,"phone":"15167168495","signupTime":"2018-08-09 16:38:21","startTimeText":null,"state":"UNDESTROY","stateText":null,"verifyCode":"FXguGvH8"}
         * order : {"activitySignupPayId":610,"appType":"Android","createTime":"2018-08-09 16:38:26","data":null,"enrollFee":0.01,"marketingActivitySignupId":775,"memberId":181166,"mobile":"15167168495","openId":"848489fbe87c4e88a44c2e6ad0eb8c68","organizeId":2,"payTime":null,"realname":"1","state":0,"tradeNo":null,"tranNo":"6001027474169749315584","userCode":null,"wechatTradeNo":null}
         * memberValues : null
         * cyOrder : {"createTime":"2018-08-09 16:38:26","id":801,"memberId":null,"orderNo":"6001027474169749315584","orderPayNo":null,"state":"PREPAID","transId":"203511575","transMoney":0.01,"type":"ACTIVITY"}
         * activity : {"acivityAddress":"123123123","acivityType":"CASH","activityEndtime":"2018-08-18 00:00:00","activityIntroduce":"111111111111112222222222222222222333333333333333333","activityPicture":"http://yjwang.wmalle.com/image/20180808/14220388533199809.png","activityStarttime":"2018-08-08 00:00:00","activityTitle":"活动报名1416","createTime":"2018-08-08 14:20:56","creatorId":4,"endTime":"2018-08-16 00:00:00","endTimeStr":"2018/08/16","enrollFee":0.02,"enrollScore":0,"extraIntroduce":"1231321","isAudit":"N","isDelete":"N","isSignup":false,"joinNumber":0,"marketingActivitySignupId":775,"memberLevel":null,"memberSignupState":"去报名","modifierId":null,"modifyTime":null,"offlineTime":null,"onlineTime":"2018-08-08 14:20:56","organizeId":2,"signStateText":"已上架","signinOffline":"OPEN","signupLimitNumber":0,"signupNumber":1,"signupState":"ONLINE","signupTime":null,"startTime":"2018-08-08 00:00:00","startTimeStr":"2018/08/08","tags":[{"tagType":"public","tagName":"啦啦啦啦啦"},{"tagType":"public","tagName":"西湖"}],"telephone":"","useCrowd":null,"verifyCode":null}
         */

        private RecordBean record;
        private OrderBean order;
        private Object memberValues;
        private CyOrderBean cyOrder;
        private ActivityBean activity;

        public RecordBean getRecord() {
            return record;
        }

        public void setRecord(RecordBean record) {
            this.record = record;
        }

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public Object getMemberValues() {
            return memberValues;
        }

        public void setMemberValues(Object memberValues) {
            this.memberValues = memberValues;
        }

        public CyOrderBean getCyOrder() {
            return cyOrder;
        }

        public void setCyOrder(CyOrderBean cyOrder) {
            this.cyOrder = cyOrder;
        }

        public ActivityBean getActivity() {
            return activity;
        }

        public void setActivity(ActivityBean activity) {
            this.activity = activity;
        }

        public static class RecordBean {
            /**
             * auditState : AUDIT
             * createTime : 2018-08-09 16:38:21
             * creatorId : null
             * endTimeText : null
             * infoList : []
             * isDelete : N
             * marketingActivitySignupId : 775
             * marketingActivitySignupStatisticsId : 1081
             * memberHeadPic : null
             * memberId : 181166
             * memberSex : MAN
             * modifierId : null
             * modifyTime : null
             * name : 1
             * organizeId : 2
             * phone : 15167168495
             * signupTime : 2018-08-09 16:38:21
             * startTimeText : null
             * state : UNDESTROY
             * stateText : null
             * verifyCode : FXguGvH8
             */

            private String auditState;
            private String createTime;
            private Object creatorId;
            private Object endTimeText;
            private String isDelete;
            private int marketingActivitySignupId;
            private int marketingActivitySignupStatisticsId;
            private Object memberHeadPic;
            private String memberId;
            private String memberSex;
            private Object modifierId;
            private Object modifyTime;
            private String dataSign;
            private String name;
            private int organizeId;
            private String phone;
            private String signupTime;
            private Object startTimeText;
            private String state;
            private Object stateText;
            private String verifyCode;
            private List<?> infoList;



            public String getDataSign() {
                return dataSign;
            }

            public void setDataSign(String dataSign) {
                this.dataSign = dataSign;
            }
            public String getAuditState() {
                return auditState;
            }

            public void setAuditState(String auditState) {
                this.auditState = auditState;
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

            public Object getMemberHeadPic() {
                return memberHeadPic;
            }

            public void setMemberHeadPic(Object memberHeadPic) {
                this.memberHeadPic = memberHeadPic;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public String getMemberSex() {
                return memberSex;
            }

            public void setMemberSex(String memberSex) {
                this.memberSex = memberSex;
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

            public List<?> getInfoList() {
                return infoList;
            }

            public void setInfoList(List<?> infoList) {
                this.infoList = infoList;
            }
        }

        public static class OrderBean {
            /**
             * activitySignupPayId : 610
             * appType : Android
             * createTime : 2018-08-09 16:38:26
             * data : null
             * enrollFee : 0.01
             * marketingActivitySignupId : 775
             * memberId : 181166
             * mobile : 15167168495
             * openId : 848489fbe87c4e88a44c2e6ad0eb8c68
             * organizeId : 2
             * payTime : null
             * realname : 1
             * state : 0
             * tradeNo : null
             * tranNo : 6001027474169749315584
             * userCode : null
             * wechatTradeNo : null
             */

            private int activitySignupPayId;
            private String appType;
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

            public String getAppType() {
                return appType;
            }

            public void setAppType(String appType) {
                this.appType = appType;
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

        public static class CyOrderBean {
            /**
             * createTime : 2018-08-09 16:38:26
             * id : 801
             * memberId : null
             * orderNo : 6001027474169749315584
             * orderPayNo : null
             * state : PREPAID
             * transId : 203511575
             * transMoney : 0.01
             * type : ACTIVITY
             */

            private String createTime;
            private int id;
            private Object memberId;
            private String orderNo;
            private Object orderPayNo;
            private String state;
            private String transId;
            private double transMoney;
            private String type;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getMemberId() {
                return memberId;
            }

            public void setMemberId(Object memberId) {
                this.memberId = memberId;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public Object getOrderPayNo() {
                return orderPayNo;
            }

            public void setOrderPayNo(Object orderPayNo) {
                this.orderPayNo = orderPayNo;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getTransId() {
                return transId;
            }

            public void setTransId(String transId) {
                this.transId = transId;
            }

            public double getTransMoney() {
                return transMoney;
            }

            public void setTransMoney(double transMoney) {
                this.transMoney = transMoney;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class ActivityBean {
            /**
             * acivityAddress : 123123123
             * acivityType : CASH
             * activityEndtime : 2018-08-18 00:00:00
             * activityIntroduce : 111111111111112222222222222222222333333333333333333
             * activityPicture : http://yjwang.wmalle.com/image/20180808/14220388533199809.png
             * activityStarttime : 2018-08-08 00:00:00
             * activityTitle : 活动报名1416
             * createTime : 2018-08-08 14:20:56
             * creatorId : 4
             * endTime : 2018-08-16 00:00:00
             * endTimeStr : 2018/08/16
             * enrollFee : 0.02
             * enrollScore : 0
             * extraIntroduce : 1231321
             * isAudit : N
             * isDelete : N
             * isSignup : false
             * joinNumber : 0
             * marketingActivitySignupId : 775
             * memberLevel : null
             * memberSignupState : 去报名
             * modifierId : null
             * modifyTime : null
             * offlineTime : null
             * onlineTime : 2018-08-08 14:20:56
             * organizeId : 2
             * signStateText : 已上架
             * signinOffline : OPEN
             * signupLimitNumber : 0
             * signupNumber : 1
             * signupState : ONLINE
             * signupTime : null
             * startTime : 2018-08-08 00:00:00
             * startTimeStr : 2018/08/08
             * tags : [{"tagType":"public","tagName":"啦啦啦啦啦"},{"tagType":"public","tagName":"西湖"}]
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
                 * tagName : 啦啦啦啦啦
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
