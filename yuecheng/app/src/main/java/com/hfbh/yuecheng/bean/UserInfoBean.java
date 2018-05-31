package com.hfbh.yuecheng.bean;

import java.io.Serializable;

/**
 * Author：Libin on 2018/5/29 11:07
 * Email：1993911441@qq.com
 * Describe：
 */
public class UserInfoBean implements Serializable{


    private static final long serialVersionUID = 2472656786375655038L;
    /**
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : {"accountBalance":0,"cardLevel":"VIP积分卡","cardLevelDO":null,"cardLevelPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171031134827341.png","cardNumber":"9900000115","couponCount":5,"currentLevel":null,"entityCardId":null,"gradeNo":3,"memberHead":null,"memberId":179178,"memberName":null,"memberPhone":"15167168495","payPassword":null,"points":0,"totalAccount":0,"version":null}
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

    public static class DataBean implements Serializable{
        private static final long serialVersionUID = 6659937028247274089L;
        /**
         * accountBalance : 0
         * cardLevel : VIP积分卡
         * cardLevelDO : null
         * cardLevelPic : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171031134827341.png
         * cardNumber : 9900000115
         * couponCount : 5
         * currentLevel : null
         * entityCardId : null
         * gradeNo : 3
         * memberHead : null
         * memberId : 179178
         * memberName : null
         * memberPhone : 15167168495
         * payPassword : null
         * points : 0
         * totalAccount : 0
         * version : null
         */

        private int accountBalance;
        private String cardLevel;
        private Object cardLevelDO;
        private String cardLevelPic;
        private String cardNumber;
        private int couponCount;
        private Object currentLevel;
        private Object entityCardId;
        private int gradeNo;
        private String memberHead;
        private int memberId;
        private String memberName;

        public int getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(int accountBalance) {
            this.accountBalance = accountBalance;
        }

        public String getCardLevel() {
            return cardLevel;
        }

        public void setCardLevel(String cardLevel) {
            this.cardLevel = cardLevel;
        }

        public Object getCardLevelDO() {
            return cardLevelDO;
        }

        public void setCardLevelDO(Object cardLevelDO) {
            this.cardLevelDO = cardLevelDO;
        }

        public String getCardLevelPic() {
            return cardLevelPic;
        }

        public void setCardLevelPic(String cardLevelPic) {
            this.cardLevelPic = cardLevelPic;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public int getCouponCount() {
            return couponCount;
        }

        public void setCouponCount(int couponCount) {
            this.couponCount = couponCount;
        }

        public Object getCurrentLevel() {
            return currentLevel;
        }

        public void setCurrentLevel(Object currentLevel) {
            this.currentLevel = currentLevel;
        }

        public Object getEntityCardId() {
            return entityCardId;
        }

        public void setEntityCardId(Object entityCardId) {
            this.entityCardId = entityCardId;
        }

        public int getGradeNo() {
            return gradeNo;
        }

        public void setGradeNo(int gradeNo) {
            this.gradeNo = gradeNo;
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

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getMemberPhone() {
            return memberPhone;
        }

        public void setMemberPhone(String memberPhone) {
            this.memberPhone = memberPhone;
        }

        public String getPayPassword() {
            return payPassword;
        }

        public void setPayPassword(String payPassword) {
            this.payPassword = payPassword;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public int getTotalAccount() {
            return totalAccount;
        }

        public void setTotalAccount(int totalAccount) {
            this.totalAccount = totalAccount;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        private String memberPhone;
        private String payPassword;
        private int points;
        private int totalAccount;
        private String version;


    }
}
