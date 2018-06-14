package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/6/14 13:55
 * Email：1993911441@qq.com
 * Describe：
 */
public class UpdateBean {

    /**
     * flag : true
     * hash :
     * data : {"content":"sdsad啊啊啊","createTime":null,"creatorId":null,"isDelete":"N","isPublish":"Y","modifierId":2,"modifyTime":"2018-06-13 19:30:02","platform":"Android","publishTime":"2018-06-13 19:30:02","url":"wwwwwwwww","versionId":2,"versionName":"1.0.1"}
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
         * content : sdsad啊啊啊
         * createTime : null
         * creatorId : null
         * isDelete : N
         * isPublish : Y
         * modifierId : 2
         * modifyTime : 2018-06-13 19:30:02
         * platform : Android
         * publishTime : 2018-06-13 19:30:02
         * url : wwwwwwwww
         * versionId : 2
         * versionName : 1.0.1
         */

        private String content;
        private Object createTime;
        private Object creatorId;
        private String isDelete;
        private String isPublish;
        private int modifierId;
        private String modifyTime;
        private String platform;
        private String publishTime;
        private String url;
        private int versionId;
        private String versionName;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(Object creatorId) {
            this.creatorId = creatorId;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getIsPublish() {
            return isPublish;
        }

        public void setIsPublish(String isPublish) {
            this.isPublish = isPublish;
        }

        public int getModifierId() {
            return modifierId;
        }

        public void setModifierId(int modifierId) {
            this.modifierId = modifierId;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getVersionId() {
            return versionId;
        }

        public void setVersionId(int versionId) {
            this.versionId = versionId;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
    }
}
