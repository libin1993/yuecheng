package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/1 12:08
 * Email：1993911441@qq.com
 * Describe：
 */
public class MemberPointsBean {


    /**
     * flag : true
     * hash : c07ed299d6364f04ba33869ed2f26fc9
     * data : {"pointsChangeList":[{"changeType":null,"changeWay":"积分变动单","createTime":"2018-06-01 11:38:45","createTimeStr":null,"memberId":null,"organizeId":null,"points":20000,"pointsChangeId":null,"pointsRemark":"增加积分测试","pointsStr":"20000.0","type":1},{"changeType":null,"changeWay":"积分变动单","createTime":"2018-06-01 11:37:17","createTimeStr":null,"memberId":null,"organizeId":null,"points":20000,"pointsChangeId":null,"pointsRemark":"增加积分测试","pointsStr":"20000.0","type":1}],"balanceScore":40000}
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
         * pointsChangeList : [{"changeType":null,"changeWay":"积分变动单","createTime":"2018-06-01 11:38:45","createTimeStr":null,"memberId":null,"organizeId":null,"points":20000,"pointsChangeId":null,"pointsRemark":"增加积分测试","pointsStr":"20000.0","type":1},{"changeType":null,"changeWay":"积分变动单","createTime":"2018-06-01 11:37:17","createTimeStr":null,"memberId":null,"organizeId":null,"points":20000,"pointsChangeId":null,"pointsRemark":"增加积分测试","pointsStr":"20000.0","type":1}]
         * balanceScore : 40000
         */

        private int balanceScore;
        private List<PointsChangeListBean> pointsChangeList;

        public int getBalanceScore() {
            return balanceScore;
        }

        public void setBalanceScore(int balanceScore) {
            this.balanceScore = balanceScore;
        }

        public List<PointsChangeListBean> getPointsChangeList() {
            return pointsChangeList;
        }

        public void setPointsChangeList(List<PointsChangeListBean> pointsChangeList) {
            this.pointsChangeList = pointsChangeList;
        }

        public static class PointsChangeListBean {
            /**
             * changeType : null
             * changeWay : 积分变动单
             * createTime : 2018-06-01 11:38:45
             * createTimeStr : null
             * memberId : null
             * organizeId : null
             * points : 20000
             * pointsChangeId : null
             * pointsRemark : 增加积分测试
             * pointsStr : 20000.0
             * type : 1
             */

            private Object changeType;
            private String changeWay;
            private String createTime;
            private Object createTimeStr;
            private Object memberId;
            private Object organizeId;
            private int points;
            private Object pointsChangeId;
            private String pointsRemark;
            private String pointsStr;
            private int type;

            public Object getChangeType() {
                return changeType;
            }

            public void setChangeType(Object changeType) {
                this.changeType = changeType;
            }

            public String getChangeWay() {
                return changeWay;
            }

            public void setChangeWay(String changeWay) {
                this.changeWay = changeWay;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getCreateTimeStr() {
                return createTimeStr;
            }

            public void setCreateTimeStr(Object createTimeStr) {
                this.createTimeStr = createTimeStr;
            }

            public Object getMemberId() {
                return memberId;
            }

            public void setMemberId(Object memberId) {
                this.memberId = memberId;
            }

            public Object getOrganizeId() {
                return organizeId;
            }

            public void setOrganizeId(Object organizeId) {
                this.organizeId = organizeId;
            }

            public int getPoints() {
                return points;
            }

            public void setPoints(int points) {
                this.points = points;
            }

            public Object getPointsChangeId() {
                return pointsChangeId;
            }

            public void setPointsChangeId(Object pointsChangeId) {
                this.pointsChangeId = pointsChangeId;
            }

            public String getPointsRemark() {
                return pointsRemark;
            }

            public void setPointsRemark(String pointsRemark) {
                this.pointsRemark = pointsRemark;
            }

            public String getPointsStr() {
                return pointsStr;
            }

            public void setPointsStr(String pointsStr) {
                this.pointsStr = pointsStr;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
