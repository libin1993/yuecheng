package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/7/11 16:41
 * Email：1993911441@qq.com
 * Describe：
 */
public class PayDataBean {

    /**
     * flag : true
     * hash : 848489fbe87c4e88a44c2e6ad0eb8c68
     * data : {"orderString":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=+2018052160180146&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22out_trade_no%22%3A%2220180711164009000015%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fdemo.payshell.cn%3A8084%2F%2Fnotify%2Fpay%2FaliPayNotifyRes.htm&sign=BjVCICO9YzSab2AVlWERrN6SYCXhgwBOxRuSpz3R76mtGVwFEFmeYpMeWMfawVMrwirBnNozog3CTJUzkyW%2FioaGlWIxO8Zr96At4PWzaCFOTCOe0XWg6CB5PdXf5W9QW3RI7B%2BtHNAC%2BYUCIj%2BbnECAAYh2QwQiuTon%2Fxn2%2F1hsFqpNeeCFFMQ3%2B%2BpDj9MzKBPivDEVPJRtcFH0JITzOvksXuZiDu4Tb2AkipAaMXBRSW1oc1HiatpNZFEa33Nak7PDFeCXfFJmpfdi8TD4mtqn%2BO96FrDSdSQbpzZX2MSVqNtLxuhKOhQ%2BuPSP6GY2yO4zMq%2FRL9xmW%2FYUaGBW0Q%3D%3D&sign_type=RSA2&timestamp=2018-07-11+16%3A40%3A09&version=1.0"}
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
         * orderString : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=+2018052160180146&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22out_trade_no%22%3A%2220180711164009000015%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fdemo.payshell.cn%3A8084%2F%2Fnotify%2Fpay%2FaliPayNotifyRes.htm&sign=BjVCICO9YzSab2AVlWERrN6SYCXhgwBOxRuSpz3R76mtGVwFEFmeYpMeWMfawVMrwirBnNozog3CTJUzkyW%2FioaGlWIxO8Zr96At4PWzaCFOTCOe0XWg6CB5PdXf5W9QW3RI7B%2BtHNAC%2BYUCIj%2BbnECAAYh2QwQiuTon%2Fxn2%2F1hsFqpNeeCFFMQ3%2B%2BpDj9MzKBPivDEVPJRtcFH0JITzOvksXuZiDu4Tb2AkipAaMXBRSW1oc1HiatpNZFEa33Nak7PDFeCXfFJmpfdi8TD4mtqn%2BO96FrDSdSQbpzZX2MSVqNtLxuhKOhQ%2BuPSP6GY2yO4zMq%2FRL9xmW%2FYUaGBW0Q%3D%3D&sign_type=RSA2&timestamp=2018-07-11+16%3A40%3A09&version=1.0
         */

        private String orderString;

        public String getOrderString() {
            return orderString;
        }

        public void setOrderString(String orderString) {
            this.orderString = orderString;
        }
    }
}
