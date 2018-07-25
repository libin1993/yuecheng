package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/7/23 17:52
 * Email：1993911441@qq.com
 * Describe：
 */
public class ScanOrderBean {

    /**
     * flag : true
     * hash : 848489fbe87c4e88a44c2e6ad0eb8c68
     * data : {"cardtype":"216","totaldisc":"0","storeName":"蚌埠百大","cardownername":"","Money":"100","couponlist":[{"balance":"1750000","bestuse":"100","payable":"100","name":"返利通用券","couponid":"9","ValidDate":"","coupontype":"9"}],"cardno":"9900000115","totalmemdisc":"0","cardtypename":"WEB会员卡"}
     * code : 0
     */

    private boolean flag;
    private String hash;
    private DataBean data;
    private int code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * cardtype : 216
         * totaldisc : 0
         * storeName : 蚌埠百大
         * cardownername :
         * Money : 100
         * couponlist : [{"balance":"1750000","bestuse":"100","payable":"100","name":"返利通用券","couponid":"9","ValidDate":"","coupontype":"9"}]
         * cardno : 9900000115
         * totalmemdisc : 0
         * cardtypename : WEB会员卡
         */

        private String cardtype;
        private String totaldisc;
        private String storeName;
        private String cardownername;
        private String Money;
        private String cardno;
        private String totalmemdisc;
        private String cardtypename;
        private double accountBalance;
        private double points;
        private double exchangeRate;

        public double getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(double accountBalance) {
            this.accountBalance = accountBalance;
        }

        public double getPoints() {
            return points;
        }

        public void setPoints(double points) {
            this.points = points;
        }

        public double getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(double exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        private List<CouponlistBean> couponlist;

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public String getTotaldisc() {
            return totaldisc;
        }

        public void setTotaldisc(String totaldisc) {
            this.totaldisc = totaldisc;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getCardownername() {
            return cardownername;
        }

        public void setCardownername(String cardownername) {
            this.cardownername = cardownername;
        }

        public String getMoney() {
            return Money;
        }

        public void setMoney(String Money) {
            this.Money = Money;
        }

        public String getCardno() {
            return cardno;
        }

        public void setCardno(String cardno) {
            this.cardno = cardno;
        }

        public String getTotalmemdisc() {
            return totalmemdisc;
        }

        public void setTotalmemdisc(String totalmemdisc) {
            this.totalmemdisc = totalmemdisc;
        }

        public String getCardtypename() {
            return cardtypename;
        }

        public void setCardtypename(String cardtypename) {
            this.cardtypename = cardtypename;
        }

        public List<CouponlistBean> getCouponlist() {
            return couponlist;
        }

        public void setCouponlist(List<CouponlistBean> couponlist) {
            this.couponlist = couponlist;
        }

        public static class CouponlistBean {
            /**
             * balance : 1750000
             * bestuse : 100
             * payable : 100
             * name : 返利通用券
             * couponid : 9
             * ValidDate :
             * coupontype : 9
             */

            private String balance;
            private String bestuse;
            private String payable;
            private String name;
            private String couponid;
            private String ValidDate;
            private String coupontype;
            private boolean isCheck;
            private double useCoupon;

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }

            public double getUseCoupon() {
                return useCoupon;
            }

            public void setUseCoupon(double useCoupon) {
                this.useCoupon = useCoupon;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getBestuse() {
                return bestuse;
            }

            public void setBestuse(String bestuse) {
                this.bestuse = bestuse;
            }

            public String getPayable() {
                return payable;
            }

            public void setPayable(String payable) {
                this.payable = payable;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCouponid() {
                return couponid;
            }

            public void setCouponid(String couponid) {
                this.couponid = couponid;
            }

            public String getValidDate() {
                return ValidDate;
            }

            public void setValidDate(String ValidDate) {
                this.ValidDate = ValidDate;
            }

            public String getCoupontype() {
                return coupontype;
            }

            public void setCoupontype(String coupontype) {
                this.coupontype = coupontype;
            }
        }
    }
}
