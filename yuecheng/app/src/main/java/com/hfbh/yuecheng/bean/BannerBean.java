package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/5/16 09:28
 * Email：1993911441@qq.com
 * Describe：
 */
public class BannerBean {


    /**
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : [{"advertPic":"http://yjwang.wmalle.com/image/20180514/6776355874561876.jpg","advertUrl":"/pages/activityDetail/activityDetail?id=477","createTime":null,"creatorId":null,"id":90,"isDelete":null,"modifierId":null,"modifyTime":null,"moduleCode":"ACTIVITY","objectId":477,"objectName":"活动报名20180508001","organizeId":null,"sort":1},{"advertPic":"http://yjwang.wmalle.com/image/20180514/6775832843091741.png","advertUrl":"/pages/discover/newProductsDetail/newProductsDetail?id=783","createTime":null,"creatorId":null,"id":88,"isDelete":null,"modifierId":null,"modifyTime":null,"moduleCode":"FIRSTLOOK","objectId":783,"objectName":"新品速递","organizeId":null,"sort":3},{"advertPic":"http://yjwang.wmalle.com/image/20180515/6875166629799224.png","advertUrl":"/pages/pointsDetail/pointsDetail?id=118","createTime":null,"creatorId":null,"id":99,"isDelete":null,"modifierId":null,"modifyTime":null,"moduleCode":"GIFT","objectId":118,"objectName":"礼品234234","organizeId":null,"sort":null},{"advertPic":"http://yjwang.wmalle.com/image/20180514/6775444663824549.jpg","advertUrl":"/pages/pointsDetail/pointsDetail?id=99","createTime":null,"creatorId":null,"id":87,"isDelete":null,"modifierId":null,"modifyTime":null,"moduleCode":"GIFT","objectId":99,"objectName":"大礼包","organizeId":null,"sort":null},{"advertPic":"http://yjwang.wmalle.com/image/20180514/6775062796327504.jpg","advertUrl":"/pages/activityDetail/activityDetail?id=501","createTime":null,"creatorId":null,"id":86,"isDelete":null,"modifierId":null,"modifyTime":null,"moduleCode":"ACTIVITY","objectId":501,"objectName":"05110511","organizeId":null,"sort":null}]
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
         * advertPic : http://yjwang.wmalle.com/image/20180514/6776355874561876.jpg
         * advertUrl : /pages/activityDetail/activityDetail?id=477
         * createTime : null
         * creatorId : null
         * id : 90
         * isDelete : null
         * modifierId : null
         * modifyTime : null
         * moduleCode : ACTIVITY
         * objectId : 477
         * objectName : 活动报名20180508001
         * organizeId : null
         * sort : 1
         */

        private String advertPic;
        private String advertUrl;
        private Object createTime;
        private Object creatorId;
        private int id;
        private Object isDelete;
        private Object modifierId;
        private Object modifyTime;
        private String moduleCode;
        private int objectId;
        private String objectName;
        private Object organizeId;
        private int sort;

        public String getAdvertPic() {
            return advertPic;
        }

        public void setAdvertPic(String advertPic) {
            this.advertPic = advertPic;
        }

        public String getAdvertUrl() {
            return advertUrl;
        }

        public void setAdvertUrl(String advertUrl) {
            this.advertUrl = advertUrl;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(Object isDelete) {
            this.isDelete = isDelete;
        }

        public Object getModifierId() {
            return modifierId;
        }

        public void setModifierId(Object modifierId) {
            this.modifierId = modifierId;
        }

        public Object getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Object modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getModuleCode() {
            return moduleCode;
        }

        public void setModuleCode(String moduleCode) {
            this.moduleCode = moduleCode;
        }

        public int getObjectId() {
            return objectId;
        }

        public void setObjectId(int objectId) {
            this.objectId = objectId;
        }

        public String getObjectName() {
            return objectName;
        }

        public void setObjectName(String objectName) {
            this.objectName = objectName;
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
