package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/13 09:47
 * Email：1993911441@qq.com
 * Describe：
 */
public class MyGiftBean {

    /**
     * flag : true
     * page : {"pageNum":1,"pageSize":10,"pages":1,"total":1}
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : [{"accessType":null,"couponImage":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180611200326147.png","couponTypeKind":null,"couponValue":null,"downloadNum":1,"endTime":"2018-06-30 00:00:00","gainDesc":"先到先得","gainId":3974,"gainName":"端午节礼品","gainTime":"2018-06-13 10:46:59","giftPrice":800,"isLimitedShop":null,"isRefund":null,"objectId":254,"serviceAmount":null,"startTime":"2018-06-11 00:00:00","state":"GIFT","useState":"UNUSE"}]
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
         * total : 1
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
         * accessType : null
         * couponImage : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180611200326147.png
         * couponTypeKind : null
         * couponValue : null
         * downloadNum : 1
         * endTime : 2018-06-30 00:00:00
         * gainDesc : 先到先得
         * gainId : 3974
         * gainName : 端午节礼品
         * gainTime : 2018-06-13 10:46:59
         * giftPrice : 800
         * isLimitedShop : null
         * isRefund : null
         * objectId : 254
         * serviceAmount : null
         * startTime : 2018-06-11 00:00:00
         * state : GIFT
         * useState : UNUSE
         */

        private Object accessType;
        private String couponImage;
        private Object couponTypeKind;
        private Object couponValue;
        private int downloadNum;
        private String endTime;
        private String gainDesc;
        private int gainId;
        private String gainName;
        private String gainTime;
        private int giftPrice;
        private Object isLimitedShop;
        private Object isRefund;
        private int objectId;
        private Object serviceAmount;
        private String startTime;
        private String state;
        private String useState;

        public Object getAccessType() {
            return accessType;
        }

        public void setAccessType(Object accessType) {
            this.accessType = accessType;
        }

        public String getCouponImage() {
            return couponImage;
        }

        public void setCouponImage(String couponImage) {
            this.couponImage = couponImage;
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

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getGainDesc() {
            return gainDesc;
        }

        public void setGainDesc(String gainDesc) {
            this.gainDesc = gainDesc;
        }

        public int getGainId() {
            return gainId;
        }

        public void setGainId(int gainId) {
            this.gainId = gainId;
        }

        public String getGainName() {
            return gainName;
        }

        public void setGainName(String gainName) {
            this.gainName = gainName;
        }

        public String getGainTime() {
            return gainTime;
        }

        public void setGainTime(String gainTime) {
            this.gainTime = gainTime;
        }

        public int getGiftPrice() {
            return giftPrice;
        }

        public void setGiftPrice(int giftPrice) {
            this.giftPrice = giftPrice;
        }

        public Object getIsLimitedShop() {
            return isLimitedShop;
        }

        public void setIsLimitedShop(Object isLimitedShop) {
            this.isLimitedShop = isLimitedShop;
        }

        public Object getIsRefund() {
            return isRefund;
        }

        public void setIsRefund(Object isRefund) {
            this.isRefund = isRefund;
        }

        public int getObjectId() {
            return objectId;
        }

        public void setObjectId(int objectId) {
            this.objectId = objectId;
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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUseState() {
            return useState;
        }

        public void setUseState(String useState) {
            this.useState = useState;
        }
    }
}
