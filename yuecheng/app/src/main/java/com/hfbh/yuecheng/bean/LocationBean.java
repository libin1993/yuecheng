package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/5/15 17:21
 * Email：1993911441@qq.com
 * Describe：
 */
public class LocationBean {


    /**
     * flag : true
     * hash : d12560c257c040caafd6303f3e1f53af
     * data : {"distance":null,"organizeId":36,"organizeName":"1","organizeType":"NEAREST"}
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
         * distance : null
         * organizeId : 36
         * organizeName : 1
         * organizeType : NEAREST
         */

        private String distance;
        private int organizeId;
        private String organizeName;
        private String organizeType;

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
            this.organizeId = organizeId;
        }

        public String getOrganizeName() {
            return organizeName;
        }

        public void setOrganizeName(String organizeName) {
            this.organizeName = organizeName;
        }

        public String getOrganizeType() {
            return organizeType;
        }

        public void setOrganizeType(String organizeType) {
            this.organizeType = organizeType;
        }
    }
}
