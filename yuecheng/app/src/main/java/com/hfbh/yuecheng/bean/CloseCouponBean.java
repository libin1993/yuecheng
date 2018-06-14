package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/14 10:49
 * Email：1993911441@qq.com
 * Describe：
 */
public class CloseCouponBean {

    /**
     * eb5554671f9f418a9d5de38b8da991c6 : 9ef095d4be4f42e9b307165a939c31d4
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : {"accessType":"FREE","accessValue":0,"balanceNum":85,"balanceScore":39996,"couponDesc":"123456","couponId":602,"couponImage":"http://yjwang.wmalle.com/image/20180601/8364786081971022.jpg","couponName":"永远永远","couponRecom":{"accessType":"FREE","accessValue":0,"balanceNum":12333,"couponId":633,"couponImage":"http://yjwang.wmalle.com/image/20180605/8714830107320977.jpg","couponName":"而且范围"},"couponTypeKind":"VOUCHER","couponValue":100,"endTime":"2018-06-23 00:00:00","exchangeTime":"2018-06-05 14:54:22","isLimitedShop":"Y","isRefund":"N","limitNum":10,"limitPerDayNum":0,"listCouponShop":[{"couponId":602,"couponShopId":196,"shopId":33,"shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171020095052006.jpg","shopName":"商户2"}],"memberBroughtNum":10,"organizeId":2,"organizeName":"滁州百大3滁州百大3滁州百大3","quantityPerDay":0,"receivePerDayNum":0,"serviceAmount":0,"startTime":"2018-06-01 00:00:00","state":"OFFLINE","useRange":"111","useState":"UNUSE","useTime":null,"verifyCode":"qMmmrz8t"}
     * submit_tokens_name : eb5554671f9f418a9d5de38b8da991c6
     * code : 0
     */

    private String eb5554671f9f418a9d5de38b8da991c6;
    private boolean flag;
    private String hash;
    private DataBean data;
    private String submit_tokens_name;
    private int code;

    public String getEb5554671f9f418a9d5de38b8da991c6() {
        return eb5554671f9f418a9d5de38b8da991c6;
    }

    public void setEb5554671f9f418a9d5de38b8da991c6(String eb5554671f9f418a9d5de38b8da991c6) {
        this.eb5554671f9f418a9d5de38b8da991c6 = eb5554671f9f418a9d5de38b8da991c6;
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

    public String getSubmit_tokens_name() {
        return submit_tokens_name;
    }

    public void setSubmit_tokens_name(String submit_tokens_name) {
        this.submit_tokens_name = submit_tokens_name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * accessType : FREE
         * accessValue : 0
         * balanceNum : 85
         * balanceScore : 39996
         * couponDesc : 123456
         * couponId : 602
         * couponImage : http://yjwang.wmalle.com/image/20180601/8364786081971022.jpg
         * couponName : 永远永远
         * couponRecom : {"accessType":"FREE","accessValue":0,"balanceNum":12333,"couponId":633,"couponImage":"http://yjwang.wmalle.com/image/20180605/8714830107320977.jpg","couponName":"而且范围"}
         * couponTypeKind : VOUCHER
         * couponValue : 100
         * endTime : 2018-06-23 00:00:00
         * exchangeTime : 2018-06-05 14:54:22
         * isLimitedShop : Y
         * isRefund : N
         * limitNum : 10
         * limitPerDayNum : 0
         * listCouponShop : [{"couponId":602,"couponShopId":196,"shopId":33,"shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171020095052006.jpg","shopName":"商户2"}]
         * memberBroughtNum : 10
         * organizeId : 2
         * organizeName : 滁州百大3滁州百大3滁州百大3
         * quantityPerDay : 0
         * receivePerDayNum : 0
         * serviceAmount : 0
         * startTime : 2018-06-01 00:00:00
         * state : OFFLINE
         * useRange : 111
         * useState : UNUSE
         * useTime : null
         * verifyCode : qMmmrz8t
         */

        private String accessType;
        private double accessValue;
        private int balanceNum;
        private int balanceScore;
        private String couponDesc;
        private int couponId;
        private String couponImage;
        private String couponName;
        private CouponRecomBean couponRecom;
        private String couponTypeKind;
        private int couponValue;
        private String endTime;
        private String exchangeTime;
        private String isLimitedShop;
        private String isRefund;
        private int limitNum;
        private int limitPerDayNum;
        private int memberBroughtNum;
        private int organizeId;
        private String organizeName;
        private int quantityPerDay;
        private int receivePerDayNum;
        private int serviceAmount;
        private String startTime;
        private String state;
        private String useRange;
        private String useState;
        private Object useTime;
        private String verifyCode;
        private List<ListCouponShopBean> listCouponShop;

        public String getAccessType() {
            return accessType;
        }

        public void setAccessType(String accessType) {
            this.accessType = accessType;
        }

        public double getAccessValue() {
            return accessValue;
        }

        public void setAccessValue(double accessValue) {
            this.accessValue = accessValue;
        }

        public int getBalanceNum() {
            return balanceNum;
        }

        public void setBalanceNum(int balanceNum) {
            this.balanceNum = balanceNum;
        }

        public int getBalanceScore() {
            return balanceScore;
        }

        public void setBalanceScore(int balanceScore) {
            this.balanceScore = balanceScore;
        }

        public String getCouponDesc() {
            return couponDesc;
        }

        public void setCouponDesc(String couponDesc) {
            this.couponDesc = couponDesc;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public String getCouponImage() {
            return couponImage;
        }

        public void setCouponImage(String couponImage) {
            this.couponImage = couponImage;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public CouponRecomBean getCouponRecom() {
            return couponRecom;
        }

        public void setCouponRecom(CouponRecomBean couponRecom) {
            this.couponRecom = couponRecom;
        }

        public String getCouponTypeKind() {
            return couponTypeKind;
        }

        public void setCouponTypeKind(String couponTypeKind) {
            this.couponTypeKind = couponTypeKind;
        }

        public int getCouponValue() {
            return couponValue;
        }

        public void setCouponValue(int couponValue) {
            this.couponValue = couponValue;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getExchangeTime() {
            return exchangeTime;
        }

        public void setExchangeTime(String exchangeTime) {
            this.exchangeTime = exchangeTime;
        }

        public String getIsLimitedShop() {
            return isLimitedShop;
        }

        public void setIsLimitedShop(String isLimitedShop) {
            this.isLimitedShop = isLimitedShop;
        }

        public String getIsRefund() {
            return isRefund;
        }

        public void setIsRefund(String isRefund) {
            this.isRefund = isRefund;
        }

        public int getLimitNum() {
            return limitNum;
        }

        public void setLimitNum(int limitNum) {
            this.limitNum = limitNum;
        }

        public int getLimitPerDayNum() {
            return limitPerDayNum;
        }

        public void setLimitPerDayNum(int limitPerDayNum) {
            this.limitPerDayNum = limitPerDayNum;
        }

        public int getMemberBroughtNum() {
            return memberBroughtNum;
        }

        public void setMemberBroughtNum(int memberBroughtNum) {
            this.memberBroughtNum = memberBroughtNum;
        }

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
            this.organizeId = organizeId;
        }

        public String getOrganizeName() {
            return organizeName;
        }

        public void setOrganizeName(String organizeName) {
            this.organizeName = organizeName;
        }

        public int getQuantityPerDay() {
            return quantityPerDay;
        }

        public void setQuantityPerDay(int quantityPerDay) {
            this.quantityPerDay = quantityPerDay;
        }

        public int getReceivePerDayNum() {
            return receivePerDayNum;
        }

        public void setReceivePerDayNum(int receivePerDayNum) {
            this.receivePerDayNum = receivePerDayNum;
        }

        public int getServiceAmount() {
            return serviceAmount;
        }

        public void setServiceAmount(int serviceAmount) {
            this.serviceAmount = serviceAmount;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUseRange() {
            return useRange;
        }

        public void setUseRange(String useRange) {
            this.useRange = useRange;
        }

        public String getUseState() {
            return useState;
        }

        public void setUseState(String useState) {
            this.useState = useState;
        }

        public Object getUseTime() {
            return useTime;
        }

        public void setUseTime(Object useTime) {
            this.useTime = useTime;
        }

        public String getVerifyCode() {
            return verifyCode;
        }

        public void setVerifyCode(String verifyCode) {
            this.verifyCode = verifyCode;
        }

        public List<ListCouponShopBean> getListCouponShop() {
            return listCouponShop;
        }

        public void setListCouponShop(List<ListCouponShopBean> listCouponShop) {
            this.listCouponShop = listCouponShop;
        }

        public static class CouponRecomBean {
            /**
             * accessType : FREE
             * accessValue : 0
             * balanceNum : 12333
             * couponId : 633
             * couponImage : http://yjwang.wmalle.com/image/20180605/8714830107320977.jpg
             * couponName : 而且范围
             */

            private String accessType;
            private int accessValue;
            private int balanceNum;
            private int couponId;
            private String couponImage;
            private String couponName;

            public String getAccessType() {
                return accessType;
            }

            public void setAccessType(String accessType) {
                this.accessType = accessType;
            }

            public int getAccessValue() {
                return accessValue;
            }

            public void setAccessValue(int accessValue) {
                this.accessValue = accessValue;
            }

            public int getBalanceNum() {
                return balanceNum;
            }

            public void setBalanceNum(int balanceNum) {
                this.balanceNum = balanceNum;
            }

            public int getCouponId() {
                return couponId;
            }

            public void setCouponId(int couponId) {
                this.couponId = couponId;
            }

            public String getCouponImage() {
                return couponImage;
            }

            public void setCouponImage(String couponImage) {
                this.couponImage = couponImage;
            }

            public String getCouponName() {
                return couponName;
            }

            public void setCouponName(String couponName) {
                this.couponName = couponName;
            }
        }

        public static class ListCouponShopBean {
            /**
             * couponId : 602
             * couponShopId : 196
             * shopId : 33
             * shopLogo : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171020095052006.jpg
             * shopName : 商户2
             */

            private int couponId;
            private int couponShopId;
            private int shopId;
            private String shopLogo;
            private String shopName;

            public int getCouponId() {
                return couponId;
            }

            public void setCouponId(int couponId) {
                this.couponId = couponId;
            }

            public int getCouponShopId() {
                return couponShopId;
            }

            public void setCouponShopId(int couponShopId) {
                this.couponShopId = couponShopId;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getShopLogo() {
                return shopLogo;
            }

            public void setShopLogo(String shopLogo) {
                this.shopLogo = shopLogo;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }
        }
    }
}
