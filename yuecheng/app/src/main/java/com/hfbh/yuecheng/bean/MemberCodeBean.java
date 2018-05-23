package com.hfbh.yuecheng.bean;

import java.io.Serializable;

/**
 * Author：Libin on 2018/5/23 17:42
 * Email：1993911441@qq.com
 * Describe：
 */
public class MemberCodeBean implements Serializable{

    /**
     * flag : true
     * hash : 7b6a9dd027be424790cb909091ceb712
     * data : {"accountBalance":0,"cardLevel":null,"cardLevelDO":null,"cardLevelPic":null,"cardNumber":null,"couponCount":0,"currentLevel":null,"entityCardId":null,"gradeNo":null,"memberHead":null,"memberId":178409,"memberName":null,"memberPhone":null,"payPassword":null,"points":0,"totalAccount":null,"version":null}
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
         * accountBalance : 0
         * cardLevel : null
         * cardLevelDO : null
         * cardLevelPic : null
         * cardNumber : null
         * couponCount : 0
         * currentLevel : null
         * entityCardId : null
         * gradeNo : null
         * memberHead : null
         * memberId : 178409
         * memberName : null
         * memberPhone : null
         * payPassword : null
         * points : 0
         * totalAccount : null
         * version : null
         */

        private int accountBalance;
        private Object cardLevel;
        private Object cardLevelDO;
        private Object cardLevelPic;
        private Object cardNumber;
        private int couponCount;
        private Object currentLevel;
        private Object entityCardId;
        private Object gradeNo;
        private Object memberHead;
        private int memberId;
        private Object memberName;
        private Object memberPhone;
        private Object payPassword;
        private int points;
        private Object totalAccount;
        private Object version;

        public int getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(int accountBalance) {
            this.accountBalance = accountBalance;
        }

        public Object getCardLevel() {
            return cardLevel;
        }

        public void setCardLevel(Object cardLevel) {
            this.cardLevel = cardLevel;
        }

        public Object getCardLevelDO() {
            return cardLevelDO;
        }

        public void setCardLevelDO(Object cardLevelDO) {
            this.cardLevelDO = cardLevelDO;
        }

        public Object getCardLevelPic() {
            return cardLevelPic;
        }

        public void setCardLevelPic(Object cardLevelPic) {
            this.cardLevelPic = cardLevelPic;
        }

        public Object getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(Object cardNumber) {
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

        public Object getGradeNo() {
            return gradeNo;
        }

        public void setGradeNo(Object gradeNo) {
            this.gradeNo = gradeNo;
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

        public Object getMemberName() {
            return memberName;
        }

        public void setMemberName(Object memberName) {
            this.memberName = memberName;
        }

        public Object getMemberPhone() {
            return memberPhone;
        }

        public void setMemberPhone(Object memberPhone) {
            this.memberPhone = memberPhone;
        }

        public Object getPayPassword() {
            return payPassword;
        }

        public void setPayPassword(Object payPassword) {
            this.payPassword = payPassword;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public Object getTotalAccount() {
            return totalAccount;
        }

        public void setTotalAccount(Object totalAccount) {
            this.totalAccount = totalAccount;
        }

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }
    }
}
