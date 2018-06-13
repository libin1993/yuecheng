package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/6/13 12:47
 * Email：1993911441@qq.com
 * Describe：
 */
public class CloseGiftBean {

    /**
     * member : {"appId":"","appUuid":"c07ed299d6364f04ba33869ed2f26fc9","cardLevelId":null,"entityCardId":"600104165","idCardNumber":null,"isQuit":"N","isSubscribe":"Y","listMemberExtraInfo":null,"memberBirthday":"2017-01-01 00:00:00","memberCardNo":"9900000115","memberCity":"东莞市","memberCountry":"谢岗镇","memberHead":"http://yjwang.wmalle.com/image/20180604/8616041263101812.png","memberId":179178,"memberName":null,"memberNickName":"呼呼呼","memberPhone":"15167168495","memberProvince":"广东","memberScore":null,"memberSex":"MAN","memberSource":"APP_ORDINARY_FOCUS","memberType":"MEMBER","memberWechatId":178060,"openId":"c07ed299d6364f04ba33869ed2f26fc9","organizeId":2,"registerTime":"2018-05-30 16:21:35","subscribe":null,"subscribeTime":"2018-05-30 16:04:17","unionId":null,"useScore":null}
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : {"accessType":null,"couponTypeKind":null,"couponValue":null,"downloadNum":1,"downloadTime":"2018-06-13 11:10:18","endTime":"2018-06-30 00:00:00","gainId":3975,"gainImage":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180611200228770.png","gainName":"周末开心乐","points":800,"pointsRewardId":253,"serviceAmount":null,"startTime":"2018-06-11 00:00:00","useState":"N","verifyCode":"4LAZ8GCL3","verifyType":"GIFT"}
     * code : 0
     */

    private MemberBean member;
    private boolean flag;
    private String hash;
    private DataBean data;
    private int code;

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

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

    public static class MemberBean {
        /**
         * appId :
         * appUuid : c07ed299d6364f04ba33869ed2f26fc9
         * cardLevelId : null
         * entityCardId : 600104165
         * idCardNumber : null
         * isQuit : N
         * isSubscribe : Y
         * listMemberExtraInfo : null
         * memberBirthday : 2017-01-01 00:00:00
         * memberCardNo : 9900000115
         * memberCity : 东莞市
         * memberCountry : 谢岗镇
         * memberHead : http://yjwang.wmalle.com/image/20180604/8616041263101812.png
         * memberId : 179178
         * memberName : null
         * memberNickName : 呼呼呼
         * memberPhone : 15167168495
         * memberProvince : 广东
         * memberScore : null
         * memberSex : MAN
         * memberSource : APP_ORDINARY_FOCUS
         * memberType : MEMBER
         * memberWechatId : 178060
         * openId : c07ed299d6364f04ba33869ed2f26fc9
         * organizeId : 2
         * registerTime : 2018-05-30 16:21:35
         * subscribe : null
         * subscribeTime : 2018-05-30 16:04:17
         * unionId : null
         * useScore : null
         */

        private String appId;
        private String appUuid;
        private Object cardLevelId;
        private String entityCardId;
        private Object idCardNumber;
        private String isQuit;
        private String isSubscribe;
        private Object listMemberExtraInfo;
        private String memberBirthday;
        private String memberCardNo;
        private String memberCity;
        private String memberCountry;
        private String memberHead;
        private int memberId;
        private Object memberName;
        private String memberNickName;
        private String memberPhone;
        private String memberProvince;
        private Object memberScore;
        private String memberSex;
        private String memberSource;
        private String memberType;
        private int memberWechatId;
        private String openId;
        private int organizeId;
        private String registerTime;
        private Object subscribe;
        private String subscribeTime;
        private Object unionId;
        private Object useScore;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppUuid() {
            return appUuid;
        }

        public void setAppUuid(String appUuid) {
            this.appUuid = appUuid;
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

        public String getIsQuit() {
            return isQuit;
        }

        public void setIsQuit(String isQuit) {
            this.isQuit = isQuit;
        }

        public String getIsSubscribe() {
            return isSubscribe;
        }

        public void setIsSubscribe(String isSubscribe) {
            this.isSubscribe = isSubscribe;
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

        public String getMemberCardNo() {
            return memberCardNo;
        }

        public void setMemberCardNo(String memberCardNo) {
            this.memberCardNo = memberCardNo;
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

        public Object getMemberScore() {
            return memberScore;
        }

        public void setMemberScore(Object memberScore) {
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

        public int getMemberWechatId() {
            return memberWechatId;
        }

        public void setMemberWechatId(int memberWechatId) {
            this.memberWechatId = memberWechatId;
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

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public Object getSubscribe() {
            return subscribe;
        }

        public void setSubscribe(Object subscribe) {
            this.subscribe = subscribe;
        }

        public String getSubscribeTime() {
            return subscribeTime;
        }

        public void setSubscribeTime(String subscribeTime) {
            this.subscribeTime = subscribeTime;
        }

        public Object getUnionId() {
            return unionId;
        }

        public void setUnionId(Object unionId) {
            this.unionId = unionId;
        }

        public Object getUseScore() {
            return useScore;
        }

        public void setUseScore(Object useScore) {
            this.useScore = useScore;
        }
    }

    public static class DataBean {
        /**
         * accessType : null
         * couponTypeKind : null
         * couponValue : null
         * downloadNum : 1
         * downloadTime : 2018-06-13 11:10:18
         * endTime : 2018-06-30 00:00:00
         * gainId : 3975
         * gainImage : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180611200228770.png
         * gainName : 周末开心乐
         * points : 800
         * pointsRewardId : 253
         * serviceAmount : null
         * startTime : 2018-06-11 00:00:00
         * useState : N
         * verifyCode : 4LAZ8GCL3
         * verifyType : GIFT
         */

        private Object accessType;
        private Object couponTypeKind;
        private Object couponValue;
        private int downloadNum;
        private String downloadTime;
        private String endTime;
        private int gainId;
        private String gainImage;
        private String gainName;
        private int points;
        private int pointsRewardId;
        private Object serviceAmount;
        private String startTime;
        private String useState;
        private String verifyCode;
        private String verifyType;

        public Object getAccessType() {
            return accessType;
        }

        public void setAccessType(Object accessType) {
            this.accessType = accessType;
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

        public int getDownloadNum() {
            return downloadNum;
        }

        public void setDownloadNum(int downloadNum) {
            this.downloadNum = downloadNum;
        }

        public String getDownloadTime() {
            return downloadTime;
        }

        public void setDownloadTime(String downloadTime) {
            this.downloadTime = downloadTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getGainId() {
            return gainId;
        }

        public void setGainId(int gainId) {
            this.gainId = gainId;
        }

        public String getGainImage() {
            return gainImage;
        }

        public void setGainImage(String gainImage) {
            this.gainImage = gainImage;
        }

        public String getGainName() {
            return gainName;
        }

        public void setGainName(String gainName) {
            this.gainName = gainName;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public int getPointsRewardId() {
            return pointsRewardId;
        }

        public void setPointsRewardId(int pointsRewardId) {
            this.pointsRewardId = pointsRewardId;
        }

        public Object getServiceAmount() {
            return serviceAmount;
        }

        public void setServiceAmount(Object serviceAmount) {
            this.serviceAmount = serviceAmount;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getUseState() {
            return useState;
        }

        public void setUseState(String useState) {
            this.useState = useState;
        }

        public String getVerifyCode() {
            return verifyCode;
        }

        public void setVerifyCode(String verifyCode) {
            this.verifyCode = verifyCode;
        }

        public String getVerifyType() {
            return verifyType;
        }

        public void setVerifyType(String verifyType) {
            this.verifyType = verifyType;
        }
    }
}
