package com.hfbh.yuecheng.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author：Libin on 2018/6/12 15:20
 * Email：1993911441@qq.com
 * Describe：
 */
public class OrderDetailBean implements Serializable{

    private static final long serialVersionUID = -8151729678366616379L;
    /**
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : {"orderNo":"00017170300100001149","FDMC":"百大名品中心","money":"2980","totalzk":"","czklist":[{"czkname":"百货贵宾券","money":"10"}],"member_zk":"","yhqlist":[{"yhqname":"返利通用券","yhqid":"9","money":"1.00"},{"yhqname":"百货贵宾券","yhqid":"10","money":"1.00"}]}
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
        private static final long serialVersionUID = -2952010740480575997L;
        /**
         * orderNo : 00017170300100001149
         * FDMC : 百大名品中心
         * money : 2980
         * totalzk :
         * czklist : [{"czkname":"百货贵宾券","money":"10"}]
         * member_zk :
         * yhqlist : [{"yhqname":"返利通用券","yhqid":"9","money":"1.00"},{"yhqname":"百货贵宾券","yhqid":"10","money":"1.00"}]
         */

        private String orderNo;
        private String FDMC;
        private String money;
        private String totalzk;
        private String member_zk;
        private List<CzklistBean> czklist;
        private List<YhqlistBean> yhqlist;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getFDMC() {
            return FDMC;
        }

        public void setFDMC(String FDMC) {
            this.FDMC = FDMC;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTotalzk() {
            return totalzk;
        }

        public void setTotalzk(String totalzk) {
            this.totalzk = totalzk;
        }

        public String getMember_zk() {
            return member_zk;
        }

        public void setMember_zk(String member_zk) {
            this.member_zk = member_zk;
        }

        public List<CzklistBean> getCzklist() {
            return czklist;
        }

        public void setCzklist(List<CzklistBean> czklist) {
            this.czklist = czklist;
        }

        public List<YhqlistBean> getYhqlist() {
            return yhqlist;
        }

        public void setYhqlist(List<YhqlistBean> yhqlist) {
            this.yhqlist = yhqlist;
        }

        public static class CzklistBean implements Serializable{
            private static final long serialVersionUID = -6056052449561866271L;
            /**
             * czkname : 百货贵宾券
             * money : 10
             */

            private String czkname;
            private String money;

            public String getCzkname() {
                return czkname;
            }

            public void setCzkname(String czkname) {
                this.czkname = czkname;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }

        public static class YhqlistBean implements Serializable{
            private static final long serialVersionUID = -8995413201148842125L;
            /**
             * yhqname : 返利通用券
             * yhqid : 9
             * money : 1.00
             */

            private String yhqname;
            private String yhqid;
            private String money;

            public String getYhqname() {
                return yhqname;
            }

            public void setYhqname(String yhqname) {
                this.yhqname = yhqname;
            }

            public String getYhqid() {
                return yhqid;
            }

            public void setYhqid(String yhqid) {
                this.yhqid = yhqid;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }
}
