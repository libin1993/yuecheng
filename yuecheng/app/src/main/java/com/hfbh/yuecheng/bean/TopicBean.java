package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/5 17:14
 * Email：1993911441@qq.com
 * Describe：
 */
public class TopicBean {

    /**
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : [{"activityList":[{"activityEndTime":"2018-06-12 00:00:00","activityId":501,"activityStartTime":"2018-05-11 00:00:00","activityType":"CASH","name":"05110511","organizeId":2,"pic":"http://yjwang.wmalle.com/image/20180511/6557313482086367.jpg","relationTopicId":5}],"gameList":[{"byname":"游戏1别名","gameId":1,"name":"游戏1","organizeId":2,"pic":"http://yjwang.wmalle.com/image/20180602/8437618300446467.png","relationTopicId":1,"url":"sdsds"},{"byname":"游戏2别名","gameId":2,"name":"游戏2","organizeId":2,"pic":"http://yjwang.wmalle.com/image/20180529/8104581694574435.png","relationTopicId":2,"url":"aaaaa"}],"topicId":1,"topicName":"qqqqqqqqq","topicPic":"http://yjwang.wmalle.com/image/20180604/8588166013792020.png"}]
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
         * activityList : [{"activityEndTime":"2018-06-12 00:00:00","activityId":501,"activityStartTime":"2018-05-11 00:00:00","activityType":"CASH","name":"05110511","organizeId":2,"pic":"http://yjwang.wmalle.com/image/20180511/6557313482086367.jpg","relationTopicId":5}]
         * gameList : [{"byname":"游戏1别名","gameId":1,"name":"游戏1","organizeId":2,"pic":"http://yjwang.wmalle.com/image/20180602/8437618300446467.png","relationTopicId":1,"url":"sdsds"},{"byname":"游戏2别名","gameId":2,"name":"游戏2","organizeId":2,"pic":"http://yjwang.wmalle.com/image/20180529/8104581694574435.png","relationTopicId":2,"url":"aaaaa"}]
         * topicId : 1
         * topicName : qqqqqqqqq
         * topicPic : http://yjwang.wmalle.com/image/20180604/8588166013792020.png
         */

        private int topicId;
        private String topicName;
        private String topicPic;
        private List<ActivityListBean> activityList;
        private List<GameListBean> gameList;

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getTopicPic() {
            return topicPic;
        }

        public void setTopicPic(String topicPic) {
            this.topicPic = topicPic;
        }

        public List<ActivityListBean> getActivityList() {
            return activityList;
        }

        public void setActivityList(List<ActivityListBean> activityList) {
            this.activityList = activityList;
        }

        public List<GameListBean> getGameList() {
            return gameList;
        }

        public void setGameList(List<GameListBean> gameList) {
            this.gameList = gameList;
        }

        public static class ActivityListBean {
            /**
             * activityEndTime : 2018-06-12 00:00:00
             * activityId : 501
             * activityStartTime : 2018-05-11 00:00:00
             * activityType : CASH
             * name : 05110511
             * organizeId : 2
             * pic : http://yjwang.wmalle.com/image/20180511/6557313482086367.jpg
             * relationTopicId : 5
             */

            private String activityEndTime;
            private int activityId;
            private String activityStartTime;
            private String activityType;
            private String name;
            private int organizeId;
            private String pic;
            private int relationTopicId;

            public String getActivityEndTime() {
                return activityEndTime;
            }

            public void setActivityEndTime(String activityEndTime) {
                this.activityEndTime = activityEndTime;
            }

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public String getActivityStartTime() {
                return activityStartTime;
            }

            public void setActivityStartTime(String activityStartTime) {
                this.activityStartTime = activityStartTime;
            }

            public String getActivityType() {
                return activityType;
            }

            public void setActivityType(String activityType) {
                this.activityType = activityType;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOrganizeId() {
                return organizeId;
            }

            public void setOrganizeId(int organizeId) {
                this.organizeId = organizeId;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getRelationTopicId() {
                return relationTopicId;
            }

            public void setRelationTopicId(int relationTopicId) {
                this.relationTopicId = relationTopicId;
            }
        }

        public static class GameListBean {
            /**
             * byname : 游戏1别名
             * gameId : 1
             * name : 游戏1
             * organizeId : 2
             * pic : http://yjwang.wmalle.com/image/20180602/8437618300446467.png
             * relationTopicId : 1
             * url : sdsds
             */

            private String byname;
            private int gameId;
            private String name;
            private int organizeId;
            private String pic;
            private int relationTopicId;
            private String url;

            public String getByname() {
                return byname;
            }

            public void setByname(String byname) {
                this.byname = byname;
            }

            public int getGameId() {
                return gameId;
            }

            public void setGameId(int gameId) {
                this.gameId = gameId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOrganizeId() {
                return organizeId;
            }

            public void setOrganizeId(int organizeId) {
                this.organizeId = organizeId;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getRelationTopicId() {
                return relationTopicId;
            }

            public void setRelationTopicId(int relationTopicId) {
                this.relationTopicId = relationTopicId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
