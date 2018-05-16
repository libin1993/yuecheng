package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/5/16 09:14
 * Email：1993911441@qq.com
 * Describe：
 */
public class HomepageTypeBean {

    /**
     * flag : true
     * hash : 3bc720ea1f5f472bad8b580df1f0f2d2
     * data : [{"id":3,"moduleCode":"BANNER","moduleName":"轮播图"},{"id":4,"moduleCode":"FUNCTION","moduleName":"功能菜单"},{"id":1,"moduleCode":"COUPON","moduleName":"优惠券"},{"id":2,"moduleCode":"ACTIVITY","moduleName":"活动"},{"id":5,"moduleCode":"GIFT","moduleName":"积分兑礼"}]
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
         * id : 3
         * moduleCode : BANNER
         * moduleName : 轮播图
         */

        private int id;
        private String moduleCode;
        private String moduleName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getModuleCode() {
            return moduleCode;
        }

        public void setModuleCode(String moduleCode) {
            this.moduleCode = moduleCode;
        }

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }
    }
}
