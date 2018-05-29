package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/5/29 11:03
 * Email：1993911441@qq.com
 * Describe：
 */
public class ResponseBean {

    /**
     * flag : true
     * token : token
     * hash : 7317ea0c5b2e4aa4845e616e47ab67bb
     * code : 0
     */

    private boolean flag;
    private String hash;
    private int code;
    private String msg;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
