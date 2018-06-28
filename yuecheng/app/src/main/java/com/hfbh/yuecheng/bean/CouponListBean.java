package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/4 13:16
 * Email：1993911441@qq.com
 * Describe：
 */
public class CouponListBean {

    /**
     * flag : true
     * page : {"pageNum":1,"pageSize":10,"pages":1,"total":3}
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : [{"accessType":"FREE","accessValue":0,"balanceNum":1000,"broughtNum":0,"couponId":603,"couponImage":"http://yjwang.wmalle.com/image/20180601/8365178859019941.jpg","couponName":"意义","couponNum":1000,"couponTypeCy":-1,"couponTypeKind":"VOUCHER","couponValue":111,"dayBroughtNum":0,"limitNum":1,"limitPerDayNum":0,"listCouponShop":[{"couponId":603,"couponShopId":197,"shopId":31,"shopName":"巴宝莉11111111111111111111111111111111111111111111111"}],"memberBroughtNum":0,"memberCouponState":null,"organizeId":2,"percentage":null,"serviceAmount":0,"startTime":"2018-06-01 00:00:00","state":"ONLINE","useRange":"456499"},{"accessType":"FREE","accessValue":0,"balanceNum":98,"broughtNum":2,"couponId":602,"couponImage":"http://yjwang.wmalle.com/image/20180601/8364786081971022.jpg","couponName":"永远永远","couponNum":100,"couponTypeCy":-1,"couponTypeKind":"VOUCHER","couponValue":100,"dayBroughtNum":0,"limitNum":10,"limitPerDayNum":0,"listCouponShop":[{"couponId":602,"couponShopId":196,"shopId":33,"shopName":"商户2"}],"memberBroughtNum":0,"memberCouponState":null,"organizeId":2,"percentage":null,"serviceAmount":0,"startTime":"2018-06-01 00:00:00","state":"ONLINE","useRange":"111"},{"accessType":"FREE","accessValue":0,"balanceNum":0,"broughtNum":2,"couponId":536,"couponImage":"http://yjwang.wmalle.com/image/20180523/7575892169282473.jpg","couponName":"百货礼券","couponNum":2,"couponTypeCy":5,"couponTypeKind":"ELEC","couponValue":2,"dayBroughtNum":0,"limitNum":0,"limitPerDayNum":0,"listCouponShop":null,"memberBroughtNum":0,"memberCouponState":"UNDO","organizeId":2,"percentage":null,"serviceAmount":0,"startTime":"2018-06-30 00:00:00","state":"ONLINE","useRange":"不要点"}]
     * code : 0
     */

    private boolean flag;
    private PageBean page;
    private String hash;
    private int code;
    private List<DataBean> data;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * total : 3
         */

        private int pageNum;
        private int pageSize;
        private int pages;
        private int total;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class DataBean {
        /**
         * accessType : FREE
         * accessValue : 0
         * balanceNum : 1000
         * broughtNum : 0
         * couponId : 603
         * couponImage : http://yjwang.wmalle.com/image/20180601/8365178859019941.jpg
         * couponName : 意义
         * couponNum : 1000
         * couponTypeCy : -1
         * couponTypeKind : VOUCHER
         * couponValue : 111
         * dayBroughtNum : 0
         * limitNum : 1
         * limitPerDayNum : 0
         * listCouponShop : [{"couponId":603,"couponShopId":197,"shopId":31,"shopName":"巴宝莉11111111111111111111111111111111111111111111111"}]
         * memberBroughtNum : 0
         * memberCouponState : null
         * organizeId : 2
         * percentage : null
         * serviceAmount : 0
         * startTime : 2018-06-01 00:00:00
         * state : ONLINE
         * useRange : 456499
         */

        private String accessType;
        private double accessValue;
        private int balanceNum;
        private int broughtNum;
        private int couponId;
        private String couponImage;
        private String couponName;
        private int couponNum;
        private int couponTypeCy;
        private String couponTypeKind;
        private double couponValue;
        private int dayBroughtNum;
        private int limitNum;
        private int limitPerDayNum;
        private int memberBroughtNum;
        private Object memberCouponState;
        private int organizeId;
        private Object percentage;
        private double serviceAmount;
        private String startTime;
        private String state;
        private String useRange;
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

        public int getBroughtNum() {
            return broughtNum;
        }

        public void setBroughtNum(int broughtNum) {
            this.broughtNum = broughtNum;
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

        public int getCouponNum() {
            return couponNum;
        }

        public void setCouponNum(int couponNum) {
            this.couponNum = couponNum;
        }

        public int getCouponTypeCy() {
            return couponTypeCy;
        }

        public void setCouponTypeCy(int couponTypeCy) {
            this.couponTypeCy = couponTypeCy;
        }

        public String getCouponTypeKind() {
            return couponTypeKind;
        }

        public void setCouponTypeKind(String couponTypeKind) {
            this.couponTypeKind = couponTypeKind;
        }

        public double getCouponValue() {
            return couponValue;
        }

        public void setCouponValue(double couponValue) {
            this.couponValue = couponValue;
        }

        public int getDayBroughtNum() {
            return dayBroughtNum;
        }

        public void setDayBroughtNum(int dayBroughtNum) {
            this.dayBroughtNum = dayBroughtNum;
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

        public Object getMemberCouponState() {
            return memberCouponState;
        }

        public void setMemberCouponState(Object memberCouponState) {
            this.memberCouponState = memberCouponState;
        }

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
            this.organizeId = organizeId;
        }

        public Object getPercentage() {
            return percentage;
        }

        public void setPercentage(Object percentage) {
            this.percentage = percentage;
        }

        public double getServiceAmount() {
            return serviceAmount;
        }

        public void setServiceAmount(double serviceAmount) {
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

        public List<ListCouponShopBean> getListCouponShop() {
            return listCouponShop;
        }

        public void setListCouponShop(List<ListCouponShopBean> listCouponShop) {
            this.listCouponShop = listCouponShop;
        }

        public static class ListCouponShopBean {
            /**
             * couponId : 603
             * couponShopId : 197
             * shopId : 31
             * shopName : 巴宝莉11111111111111111111111111111111111111111111111
             */

            private int couponId;
            private int couponShopId;
            private int shopId;
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

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }
        }
    }
}
