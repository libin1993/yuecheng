package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/7/12 15:34
 * Email：1993911441@qq.com
 * Describe：
 */
public class BroadMsgBean {

    /**
     * flag : true
     * hash : 848489fbe87c4e88a44c2e6ad0eb8c68
     * data : [{"broadcastId":16,"content":"端午节祝广大用户端午节快乐！！！","createTime":"2018-07-11 10:54:43","isDelete":"N","isRead":"N","memberBroadcastId":3,"memberId":181166,"organizeId":2,"readTime":null},{"broadcastId":17,"content":"618年中大促","createTime":"2018-07-11 10:54:43","isDelete":"N","isRead":"N","memberBroadcastId":2,"memberId":181166,"organizeId":2,"readTime":null}]
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
         * broadcastId : 16
         * content : 端午节祝广大用户端午节快乐！！！
         * createTime : 2018-07-11 10:54:43
         * isDelete : N
         * isRead : N
         * memberBroadcastId : 3
         * memberId : 181166
         * organizeId : 2
         * readTime : null
         */

        private int broadcastId;
        private String content;
        private String createTime;
        private String isDelete;
        private String isRead;
        private int memberBroadcastId;
        private int memberId;
        private int organizeId;
        private Object readTime;

        public int getBroadcastId() {
            return broadcastId;
        }

        public void setBroadcastId(int broadcastId) {
            this.broadcastId = broadcastId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getIsRead() {
            return isRead;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }

        public int getMemberBroadcastId() {
            return memberBroadcastId;
        }

        public void setMemberBroadcastId(int memberBroadcastId) {
            this.memberBroadcastId = memberBroadcastId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
            this.organizeId = organizeId;
        }

        public Object getReadTime() {
            return readTime;
        }

        public void setReadTime(Object readTime) {
            this.readTime = readTime;
        }
    }
}
