package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/1 17:02
 * Email：1993911441@qq.com
 * Describe：
 */
public class BalanceRecordBean {

    /**
     * flag : true
     * page : {"pageNum":1,"pageSize":10,"pages":1,"total":1}
     * hash : CC2A91E00EB59CEA37FC1906574F5E2A95C9BED6583D2A40F6D78C5926769E5D
     * data : [{"beforeChangeAmount":1,"changeAmount":1,"changeWay":"RECHARGE","createTime":"2018-03-27 15:31:32","fromTo":"1","fundDesc":"","id":1,"isDelete":null,"memberId":1,"organizeId":null,"remark":"","type":"INCREASE"}]
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
         * beforeChangeAmount : 1
         * changeAmount : 1
         * changeWay : RECHARGE
         * createTime : 2018-03-27 15:31:32
         * fromTo : 1
         * fundDesc :
         * id : 1
         * isDelete : null
         * memberId : 1
         * organizeId : null
         * remark :
         * type : INCREASE
         */

        private double beforeChangeAmount;
        private double changeAmount;
        private String changeWay;
        private String createTime;
        private String fromTo;
        private String fundDesc;
        private int id;
        private Object isDelete;
        private int memberId;
        private Object organizeId;
        private String remark;
        private String type;

        public double getBeforeChangeAmount() {
            return beforeChangeAmount;
        }

        public void setBeforeChangeAmount(double beforeChangeAmount) {
            this.beforeChangeAmount = beforeChangeAmount;
        }

        public double getChangeAmount() {
            return changeAmount;
        }

        public void setChangeAmount(double changeAmount) {
            this.changeAmount = changeAmount;
        }

        public String getChangeWay() {
            return changeWay;
        }

        public void setChangeWay(String changeWay) {
            this.changeWay = changeWay;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getFromTo() {
            return fromTo;
        }

        public void setFromTo(String fromTo) {
            this.fromTo = fromTo;
        }

        public String getFundDesc() {
            return fundDesc;
        }

        public void setFundDesc(String fundDesc) {
            this.fundDesc = fundDesc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(Object isDelete) {
            this.isDelete = isDelete;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public Object getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(Object organizeId) {
            this.organizeId = organizeId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
