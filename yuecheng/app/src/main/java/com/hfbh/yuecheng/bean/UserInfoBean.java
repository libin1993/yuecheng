package com.hfbh.yuecheng.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author：Libin on 2018/5/29 11:07
 * Email：1993911441@qq.com
 * Describe：
 */
public class UserInfoBean implements Serializable {


    private static final long serialVersionUID = 2472656786375655038L;
    /**
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : {"accountBalance":1487.89,"cardLevel":"VIP积分卡","cardLevelDO":null,"cardLevelPic":null,"cardNumber":"9900000115","couponCount":5,"currentLevel":null,"entityCardId":null,"gradeNo":3,"memberBirthday":null,"memberCardGradeDTO":{"appPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134056834.png","gradeId":3,"gradeName":"VIP积分卡","gradeNo":3,"listPrivilege":[{"appPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110248008.png","createId":null,"createTime":null,"isDelete":"N","modifyId":null,"modifyTime":null,"pic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110236496.png","privilegeDesc":"描述描述","privilegeId":1,"privilegeName":"特权1"},{"appPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110236496.png","createId":null,"createTime":null,"isDelete":"N","modifyId":null,"modifyTime":null,"pic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110248008.png","privilegeDesc":"描述描述1","privilegeId":2,"privilegeName":"特权2"}],"maxScore":10000,"minScore":null,"pic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134056834.png"},"memberCity":null,"memberCountry":null,"memberHead":"http://yjwang.wmalle.com/image/20180601/8331683277500671.png","memberId":179178,"memberName":null,"memberNickname":"555","memberPhone":"15167168495","memberProvince":null,"memberSex":"UNKNOW","payPassword":null,"points":40000,"totalAccount":0,"version":null}
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

    public static class DataBean implements Serializable {
        private static final long serialVersionUID = 1637596220579495100L;
        /**
         * accountBalance : 1487.89
         * cardLevel : VIP积分卡
         * cardLevelDO : null
         * cardLevelPic : null
         * cardNumber : 9900000115
         * couponCount : 5
         * currentLevel : null
         * entityCardId : null
         * gradeNo : 3
         * memberBirthday : null
         * memberCardGradeDTO : {"appPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134056834.png","gradeId":3,"gradeName":"VIP积分卡","gradeNo":3,"listPrivilege":[{"appPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110248008.png","createId":null,"createTime":null,"isDelete":"N","modifyId":null,"modifyTime":null,"pic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110236496.png","privilegeDesc":"描述描述","privilegeId":1,"privilegeName":"特权1"},{"appPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110236496.png","createId":null,"createTime":null,"isDelete":"N","modifyId":null,"modifyTime":null,"pic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110248008.png","privilegeDesc":"描述描述1","privilegeId":2,"privilegeName":"特权2"}],"maxScore":10000,"minScore":null,"pic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134056834.png"}
         * memberCity : null
         * memberCountry : null
         * memberHead : http://yjwang.wmalle.com/image/20180601/8331683277500671.png
         * memberId : 179178
         * memberName : null
         * memberNickname : 555
         * memberPhone : 15167168495
         * memberProvince : null
         * memberSex : UNKNOW
         * payPassword : null
         * points : 40000
         * totalAccount : 0
         * version : null
         */

        private double accountBalance;
        private String cardLevel;
        private Object cardLevelDO;
        private String cardLevelPic;
        private String cardNumber;
        private int couponCount;
        private Object currentLevel;
        private Object entityCardId;
        private int gradeNo;
        private String memberBirthday;
        private MemberCardGradeDTOBean memberCardGradeDTO;
        private String memberCity;
        private String memberCountry;
        private String memberHead;
        private int memberId;
        private Object memberName;
        private String memberNickname;
        private String memberPhone;
        private String memberProvince;
        private String memberSex;
        private String payPassword;
        private double points;
        private int totalAccount;
        private Object version;

        public double getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(double accountBalance) {
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

        public String getMemberBirthday() {
            return memberBirthday;
        }

        public void setMemberBirthday(String memberBirthday) {
            this.memberBirthday = memberBirthday;
        }

        public MemberCardGradeDTOBean getMemberCardGradeDTO() {
            return memberCardGradeDTO;
        }

        public void setMemberCardGradeDTO(MemberCardGradeDTOBean memberCardGradeDTO) {
            this.memberCardGradeDTO = memberCardGradeDTO;
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

        public String getMemberNickname() {
            return memberNickname;
        }

        public void setMemberNickname(String memberNickname) {
            this.memberNickname = memberNickname;
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

        public String getMemberSex() {
            return memberSex;
        }

        public void setMemberSex(String memberSex) {
            this.memberSex = memberSex;
        }

        public String getPayPassword() {
            return payPassword;
        }

        public void setPayPassword(String payPassword) {
            this.payPassword = payPassword;
        }

        public double getPoints() {
            return points;
        }

        public void setPoints(double points) {
            this.points = points;
        }

        public int getTotalAccount() {
            return totalAccount;
        }

        public void setTotalAccount(int totalAccount) {
            this.totalAccount = totalAccount;
        }

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }

        public static class MemberCardGradeDTOBean implements Serializable {
            private static final long serialVersionUID = 2014496416043004504L;
            /**
             * appPic : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134056834.png
             * gradeId : 3
             * gradeName : VIP积分卡
             * gradeNo : 3
             * listPrivilege : [{"appPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110248008.png","createId":null,"createTime":null,"isDelete":"N","modifyId":null,"modifyTime":null,"pic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110236496.png","privilegeDesc":"描述描述","privilegeId":1,"privilegeName":"特权1"},{"appPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110236496.png","createId":null,"createTime":null,"isDelete":"N","modifyId":null,"modifyTime":null,"pic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110248008.png","privilegeDesc":"描述描述1","privilegeId":2,"privilegeName":"特权2"}]
             * maxScore : 10000
             * minScore : null
             * pic : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134056834.png
             */

            private String appPic;
            private int gradeId;
            private String gradeName;
            private int gradeNo;
            private int maxScore;
            private Object minScore;
            private String pic;
            private List<ListPrivilegeBean> listPrivilege;

            public String getAppPic() {
                return appPic;
            }

            public void setAppPic(String appPic) {
                this.appPic = appPic;
            }

            public int getGradeId() {
                return gradeId;
            }

            public void setGradeId(int gradeId) {
                this.gradeId = gradeId;
            }

            public String getGradeName() {
                return gradeName;
            }

            public void setGradeName(String gradeName) {
                this.gradeName = gradeName;
            }

            public int getGradeNo() {
                return gradeNo;
            }

            public void setGradeNo(int gradeNo) {
                this.gradeNo = gradeNo;
            }

            public int getMaxScore() {
                return maxScore;
            }

            public void setMaxScore(int maxScore) {
                this.maxScore = maxScore;
            }

            public Object getMinScore() {
                return minScore;
            }

            public void setMinScore(Object minScore) {
                this.minScore = minScore;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public List<ListPrivilegeBean> getListPrivilege() {
                return listPrivilege;
            }

            public void setListPrivilege(List<ListPrivilegeBean> listPrivilege) {
                this.listPrivilege = listPrivilege;
            }

            public static class ListPrivilegeBean implements Serializable {
                private static final long serialVersionUID = 5383658936716515758L;
                /**
                 * appPic : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110248008.png
                 * createId : null
                 * createTime : null
                 * isDelete : N
                 * modifyId : null
                 * modifyTime : null
                 * pic : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180409110236496.png
                 * privilegeDesc : 描述描述
                 * privilegeId : 1
                 * privilegeName : 特权1
                 */

                private String appPic;
                private Object createId;
                private Object createTime;
                private String isDelete;
                private Object modifyId;
                private Object modifyTime;
                private String pic;
                private String privilegeDesc;
                private int privilegeId;
                private String privilegeName;

                public String getAppPic() {
                    return appPic;
                }

                public void setAppPic(String appPic) {
                    this.appPic = appPic;
                }

                public Object getCreateId() {
                    return createId;
                }

                public void setCreateId(Object createId) {
                    this.createId = createId;
                }

                public Object getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(Object createTime) {
                    this.createTime = createTime;
                }

                public String getIsDelete() {
                    return isDelete;
                }

                public void setIsDelete(String isDelete) {
                    this.isDelete = isDelete;
                }

                public Object getModifyId() {
                    return modifyId;
                }

                public void setModifyId(Object modifyId) {
                    this.modifyId = modifyId;
                }

                public Object getModifyTime() {
                    return modifyTime;
                }

                public void setModifyTime(Object modifyTime) {
                    this.modifyTime = modifyTime;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getPrivilegeDesc() {
                    return privilegeDesc;
                }

                public void setPrivilegeDesc(String privilegeDesc) {
                    this.privilegeDesc = privilegeDesc;
                }

                public int getPrivilegeId() {
                    return privilegeId;
                }

                public void setPrivilegeId(int privilegeId) {
                    this.privilegeId = privilegeId;
                }

                public String getPrivilegeName() {
                    return privilegeName;
                }

                public void setPrivilegeName(String privilegeName) {
                    this.privilegeName = privilegeName;
                }
            }
        }
    }
}
