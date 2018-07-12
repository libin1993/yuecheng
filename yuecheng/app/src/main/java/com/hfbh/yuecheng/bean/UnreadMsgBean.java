package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/7/12 14:08
 * Email：1993911441@qq.com
 * Describe：
 */
public class UnreadMsgBean {

    /**
     * flag : true
     * hash : 848489fbe87c4e88a44c2e6ad0eb8c68
     * data : 0
     * code : 0
     */

    private boolean flag;
    private String hash;
    private int data;
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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
