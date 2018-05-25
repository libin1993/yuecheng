package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/5/25 13:27
 * Email：1993911441@qq.com
 * Describe：
 */
public class ExchangeGiftBean {

    /**
     * flag : true
     * hash : bf308c480d7a4745836f3462e6628918
     * data : {"balanceScore":0,"balanceGetNum":98,"limitGetNum":0}
     * code : 0
     * msg : 兑换成功
     */

    private boolean flag;
    private String hash;
    private DataBean data;
    private int code;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * balanceScore : 0
         * balanceGetNum : 98
         * limitGetNum : 0
         */

        private int balanceScore;
        private int balanceGetNum;
        private int limitGetNum;

        public int getBalanceScore() {
            return balanceScore;
        }

        public void setBalanceScore(int balanceScore) {
            this.balanceScore = balanceScore;
        }

        public int getBalanceGetNum() {
            return balanceGetNum;
        }

        public void setBalanceGetNum(int balanceGetNum) {
            this.balanceGetNum = balanceGetNum;
        }

        public int getLimitGetNum() {
            return limitGetNum;
        }

        public void setLimitGetNum(int limitGetNum) {
            this.limitGetNum = limitGetNum;
        }
    }
}
