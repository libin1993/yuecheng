package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/1 14:11
 * Email：1993911441@qq.com
 * Describe：
 */
public class MemberBalanceBean {

    /**
     * flag : true
     * page : {"pageNum":1,"pageSize":1,"pages":2,"total":2}
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : [{"accountId":"4745864","accountName":"","accountPassword":"688759494ADAF327","balance":1000,"createTime":null,"creatorId":null,"id":3,"isDelete":"N","memberId":179178,"modifierId":null,"modifyTime":null,"organizeId":2,"prePaidCardAccount":"100000490605"}]
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
         * pageSize : 1
         * pages : 2
         * total : 2
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
         * accountId : 4745864
         * accountName :
         * accountPassword : 688759494ADAF327
         * balance : 1000
         * createTime : null
         * creatorId : null
         * id : 3
         * isDelete : N
         * memberId : 179178
         * modifierId : null
         * modifyTime : null
         * organizeId : 2
         * prePaidCardAccount : 100000490605
         */

        private String accountId;
        private String accountName;
        private String accountPassword;
        private double balance;
        private Object createTime;
        private Object creatorId;
        private int id;
        private String isDelete;
        private int memberId;
        private Object modifierId;
        private Object modifyTime;
        private int organizeId;
        private String prePaidCardAccount;

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getAccountPassword() {
            return accountPassword;
        }

        public void setAccountPassword(String accountPassword) {
            this.accountPassword = accountPassword;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(Object creatorId) {
            this.creatorId = creatorId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
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

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
            this.organizeId = organizeId;
        }

        public String getPrePaidCardAccount() {
            return prePaidCardAccount;
        }

        public void setPrePaidCardAccount(String prePaidCardAccount) {
            this.prePaidCardAccount = prePaidCardAccount;
        }
    }
}
