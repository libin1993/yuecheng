package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/7/17 17:25
 * Email：1993911441@qq.com
 * Describe：
 */
public class WechatPayBean {

    /**
     * flag : true
     * hash : 848489fbe87c4e88a44c2e6ad0eb8c68
     * data : {"timestamp":"20180717173503","sign":"CD1EB01B1A2C136BB9D613738BE0DD09","mch_id":"1275285601","prepay_id":"wx17173503555611874416c9201461119444","sub_mch_id":"1504858901","appid":"wxc03c6d0d6a10cf18","nonce_str":"a2LpPgxhjAtzfion9Lnk8s3rff6KkdPM","trade_type":"APP"}
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

    public static class DataBean {
        /**
         * timestamp : 20180717173503
         * sign : CD1EB01B1A2C136BB9D613738BE0DD09
         * mch_id : 1275285601
         * prepay_id : wx17173503555611874416c9201461119444
         * sub_mch_id : 1504858901
         * appid : wxc03c6d0d6a10cf18
         * nonce_str : a2LpPgxhjAtzfion9Lnk8s3rff6KkdPM
         * trade_type : APP
         */

        private String timestamp;
        private String sign;
        private String mch_id;
        private String prepay_id;
        private String sub_mch_id;
        private String appid;
        private String nonce_str;
        private String trade_type;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getSub_mch_id() {
            return sub_mch_id;
        }

        public void setSub_mch_id(String sub_mch_id) {
            this.sub_mch_id = sub_mch_id;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }
    }
}
