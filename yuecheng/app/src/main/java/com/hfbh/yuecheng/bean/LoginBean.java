package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/5/30 11:17
 * Email：1993911441@qq.com
 * Describe：
 */
public class LoginBean {

    /**
     * flag : true
     * token : token
     * hash : ba2976c371084acea8860f88981fd12c
     * data : {"appUuid":"ba2976c371084acea8860f88981fd12c","balanceScore":36,"cardLevelId":null,"entityCardId":"600104166","idCardNumber":null,"isDelete":"N","isQuit":"N","isSubscribe":"N","memberBirthday":null,"memberCardNo":"9900000116","memberCity":null,"memberCountry":null,"memberHead":null,"memberId":178988,"memberName":"餐厅的设计","memberNickName":"合肥百大","memberPhone":"13732113831","memberProvince":null,"memberPwd":"0317B4712E15B5A3DC7DA06B37AC6F98","memberScore":36,"memberSex":"UNKNOW","memberSource":"APP_ORDINARY_FOCUS","memberType":"MEMBER","modifyTime":null,"organizeId":0,"registerOrganizeId":null,"registerTime":"2018-05-29 15:00:36","subscribeChangeTime":null,"subscribeTime":null,"unionId":null,"unsubscribeChangeTime":null,"useScore":0}
     * code : 0
     */

    private boolean flag;
    private String token;
    private String hash;
    private DataBean data;
    private int code;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
         * appUuid : ba2976c371084acea8860f88981fd12c
         * balanceScore : 36
         * cardLevelId : null
         * entityCardId : 600104166
         * idCardNumber : null
         * isDelete : N
         * isQuit : N
         * isSubscribe : N
         * memberBirthday : null
         * memberCardNo : 9900000116
         * memberCity : null
         * memberCountry : null
         * memberHead : null
         * memberId : 178988
         * memberName : 餐厅的设计
         * memberNickName : 合肥百大
         * memberPhone : 13732113831
         * memberProvince : null
         * memberPwd : 0317B4712E15B5A3DC7DA06B37AC6F98
         * memberScore : 36
         * memberSex : UNKNOW
         * memberSource : APP_ORDINARY_FOCUS
         * memberType : MEMBER
         * modifyTime : null
         * organizeId : 0
         * registerOrganizeId : null
         * registerTime : 2018-05-29 15:00:36
         * subscribeChangeTime : null
         * subscribeTime : null
         * unionId : null
         * unsubscribeChangeTime : null
         * useScore : 0
         */

        private String appUuid;
        private int balanceScore;
        private Object cardLevelId;
        private String entityCardId;
        private Object idCardNumber;
        private String isDelete;
        private String isQuit;
        private String isSubscribe;
        private Object memberBirthday;
        private String memberCardNo;
        private Object memberCity;
        private Object memberCountry;
        private Object memberHead;
        private int memberId;
        private String memberName;
        private String memberNickName;
        private String memberPhone;
        private Object memberProvince;
        private String memberPwd;
        private int memberScore;
        private String memberSex;
        private String memberSource;
        private String memberType;
        private Object modifyTime;
        private int organizeId;
        private Object registerOrganizeId;
        private String registerTime;
        private Object subscribeChangeTime;
        private Object subscribeTime;
        private Object unionId;
        private Object unsubscribeChangeTime;
        private int useScore;

        public String getAppUuid() {
            return appUuid;
        }

        public void setAppUuid(String appUuid) {
            this.appUuid = appUuid;
        }

        public int getBalanceScore() {
            return balanceScore;
        }

        public void setBalanceScore(int balanceScore) {
            this.balanceScore = balanceScore;
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

        public String getIsSubscribe() {
            return isSubscribe;
        }

        public void setIsSubscribe(String isSubscribe) {
            this.isSubscribe = isSubscribe;
        }

        public Object getMemberBirthday() {
            return memberBirthday;
        }

        public void setMemberBirthday(Object memberBirthday) {
            this.memberBirthday = memberBirthday;
        }

        public String getMemberCardNo() {
            return memberCardNo;
        }

        public void setMemberCardNo(String memberCardNo) {
            this.memberCardNo = memberCardNo;
        }

        public Object getMemberCity() {
            return memberCity;
        }

        public void setMemberCity(Object memberCity) {
            this.memberCity = memberCity;
        }

        public Object getMemberCountry() {
            return memberCountry;
        }

        public void setMemberCountry(Object memberCountry) {
            this.memberCountry = memberCountry;
        }

        public Object getMemberHead() {
            return memberHead;
        }

        public void setMemberHead(Object memberHead) {
            this.memberHead = memberHead;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
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

        public Object getMemberProvince() {
            return memberProvince;
        }

        public void setMemberProvince(Object memberProvince) {
            this.memberProvince = memberProvince;
        }

        public String getMemberPwd() {
            return memberPwd;
        }

        public void setMemberPwd(String memberPwd) {
            this.memberPwd = memberPwd;
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

        public Object getSubscribeChangeTime() {
            return subscribeChangeTime;
        }

        public void setSubscribeChangeTime(Object subscribeChangeTime) {
            this.subscribeChangeTime = subscribeChangeTime;
        }

        public Object getSubscribeTime() {
            return subscribeTime;
        }

        public void setSubscribeTime(Object subscribeTime) {
            this.subscribeTime = subscribeTime;
        }

        public Object getUnionId() {
            return unionId;
        }

        public void setUnionId(Object unionId) {
            this.unionId = unionId;
        }

        public Object getUnsubscribeChangeTime() {
            return unsubscribeChangeTime;
        }

        public void setUnsubscribeChangeTime(Object unsubscribeChangeTime) {
            this.unsubscribeChangeTime = unsubscribeChangeTime;
        }

        public int getUseScore() {
            return useScore;
        }

        public void setUseScore(int useScore) {
            this.useScore = useScore;
        }
    }
}
