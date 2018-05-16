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
     * hash : 44c68ff36c8e40bfa6629d2593fab955
     * data : [{"functionCode":"STORE","functionIco":"http://yjwang.wmalle.com/image/20180514/6779753848088661.png","functionName":"找店铺","functionType":"PROJECT","id":192,"name":"找店铺","organizeId":null,"sort":1},{"functionCode":"PARKING","functionIco":"http://yjwang.wmalle.com/image/20180514/6779764073740853.png","functionName":"停车缴费","functionType":"PROJECT","id":190,"name":"停车缴费","organizeId":null,"sort":2},{"functionCode":"GUIDING","functionIco":null,"functionName":"室内导航","functionType":"PROJECT","id":195,"name":"室内导航","organizeId":null,"sort":null},{"functionCode":"PLAYING","functionIco":"http://yjwang.wmalle.com/image/20180514/6779831967592216.png","functionName":"我要玩","functionType":"PRODUCT","id":194,"name":"我要玩","organizeId":null,"sort":null},{"functionCode":"MEMBER_CODE","functionIco":"http://yjwang.wmalle.com/image/20180515/6875175240042319.png","functionName":"会员码","functionType":"PROJECT","id":193,"name":"会员码","organizeId":null,"sort":null}]
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
         * functionName : 找店铺
         * functionType : PROJECT
         * id : 192
         * name : 找店铺
         * organizeId : null
         * sort : 1
         */

        private String functionCode;
        private String functionIco;
        private String functionName;
        private String functionType;
        private int id;
        private String name;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
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
