package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/5/16 09:29
 * Email：1993911441@qq.com
 * Describe：
 */
public class FunctionBean {


    /**
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : [{"functionCode":"STORE","functionIco":"http://yjwang.wmalle.com/image/20180514/6779753848088661.png","functionIcoExtra":null,"functionMark":"MAIN","functionName":"找店铺","functionType":"PROJECT","id":192,"isOpen":"Y","name":null,"organizeId":null,"sort":1},{"functionCode":"SHOPPING","functionIco":null,"functionIcoExtra":null,"functionMark":"MAIN","functionName":"我要买","functionType":"PRODUCT","id":197,"isOpen":"Y","name":null,"organizeId":null,"sort":null},{"functionCode":"GUIDING","functionIco":null,"functionIcoExtra":null,"functionMark":"MAIN","functionName":"室内导航","functionType":"PROJECT","id":195,"isOpen":"Y","name":null,"organizeId":null,"sort":null},{"functionCode":"PLAYING","functionIco":"http://yjwang.wmalle.com/image/20180514/6779831967592216.png","functionIcoExtra":null,"functionMark":"MAIN","functionName":"我要玩","functionType":"PRODUCT","id":194,"isOpen":"Y","name":null,"organizeId":null,"sort":null},{"functionCode":"MEMBER_CODE","functionIco":"http://yjwang.wmalle.com/image/20180515/6875175240042319.png","functionIcoExtra":null,"functionMark":"MAIN","functionName":"会员码","functionType":"PROJECT","id":193,"isOpen":"Y","name":null,"organizeId":null,"sort":null},{"functionCode":null,"functionIco":null,"functionIcoExtra":null,"functionMark":"EXTRA","functionName":"自助积分","functionType":null,"id":201,"isOpen":"Y","name":null,"organizeId":null,"sort":1},{"functionCode":null,"functionIco":null,"functionIcoExtra":null,"functionMark":"EXTRA","functionName":"发票助手","functionType":null,"id":202,"isOpen":"Y","name":null,"organizeId":null,"sort":2},{"functionCode":"WIFI","functionIco":null,"functionIcoExtra":null,"functionMark":"EXTRA","functionName":"免费Wi-Fi","functionType":null,"id":200,"isOpen":"Y","name":null,"organizeId":null,"sort":null}]
     * code : 0
     */

    private boolean flag;
    private String hash;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * functionCode : STORE
         * functionIco : http://yjwang.wmalle.com/image/20180514/6779753848088661.png
         * functionIcoExtra : null
         * functionMark : MAIN
         * functionName : 找店铺
         * functionType : PROJECT
         * id : 192
         * isOpen : Y
         * name : null
         * organizeId : null
         * sort : 1
         */

        private String functionCode;
        private String functionIco;
        private Object functionIcoExtra;
        private String functionMark;
        private String functionName;
        private String functionType;
        private int id;
        private String isOpen;
        private Object name;
        private Object organizeId;
        private int sort;

        public String getFunctionCode() {
            return functionCode;
        }

        public void setFunctionCode(String functionCode) {
            this.functionCode = functionCode;
        }

        public String getFunctionIco() {
            return functionIco;
        }

        public void setFunctionIco(String functionIco) {
            this.functionIco = functionIco;
        }

        public Object getFunctionIcoExtra() {
            return functionIcoExtra;
        }

        public void setFunctionIcoExtra(Object functionIcoExtra) {
            this.functionIcoExtra = functionIcoExtra;
        }

        public String getFunctionMark() {
            return functionMark;
        }

        public void setFunctionMark(String functionMark) {
            this.functionMark = functionMark;
        }

        public String getFunctionName() {
            return functionName;
        }

        public void setFunctionName(String functionName) {
            this.functionName = functionName;
        }

        public String getFunctionType() {
            return functionType;
        }

        public void setFunctionType(String functionType) {
            this.functionType = functionType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIsOpen() {
            return isOpen;
        }

        public void setIsOpen(String isOpen) {
            this.isOpen = isOpen;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(Object organizeId) {
            this.organizeId = organizeId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
