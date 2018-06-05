package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/5 17:14
 * Email：1993911441@qq.com
 * Describe：
 */
public class BroadcastBean {

    /**
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : [{"content":"第八套广播体操现在开始!","id":1,"isOpen":"Y","organizeId":2,"sort":1},{"content":"测试广播1","id":2,"isOpen":"Y","organizeId":2,"sort":2},{"content":"测试广播2","id":3,"isOpen":"Y","organizeId":2,"sort":4}]
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
         * content : 第八套广播体操现在开始!
         * id : 1
         * isOpen : Y
         * organizeId : 2
         * sort : 1
         */

        private String content;
        private int id;
        private String isOpen;
        private int organizeId;
        private int sort;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
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
