package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/6/4 10:10
 * Email：1993911441@qq.com
 * Describe：
 */
public class MemberRightsBean {


    /**
     * flag : true
     * data : [{"appPic":"http://yjwang.wmalle.com/image/20180604/8586689027146936.png","gradeId":7,"gradeName":"会员卡1","gradeNo":null,"listPrivilege":[{"appPic":"http://yjwang.wmalle.com/image/20180602/8433304449143812.png","createId":155,"createTime":"2018-06-02 14:45:16","isDelete":"N","modifyId":155,"modifyTime":"2018-06-02 15:25:00","pic":"http://yjwang.wmalle.com/image/20180602/8433301781067906.png","privilegeDesc":"1212121","privilegeId":8,"privilegeName":"特权222"}],"maxScore":null,"minScore":1,"pic":"http://yjwang.wmalle.com/image/20180604/8586689027146936.png"},{"appPic":"http://yjwang.wmalle.com/image/20180604/8586691635435798.jpg","gradeId":6,"gradeName":"会员卡1","gradeNo":null,"listPrivilege":[{"appPic":"http://yjwang.wmalle.com/image/20180602/8433304449143812.png","createId":155,"createTime":"2018-06-02 14:45:16","isDelete":"N","modifyId":155,"modifyTime":"2018-06-02 15:25:00","pic":"http://yjwang.wmalle.com/image/20180602/8433301781067906.png","privilegeDesc":"1212121","privilegeId":8,"privilegeName":"特权222"}],"maxScore":null,"minScore":1,"pic":"http://yjwang.wmalle.com/image/20180604/8586689027146936.png"},{"appPic":"http://yjwang.wmalle.com/image/20180602/8447586532838997.png","gradeId":5,"gradeName":"1212","gradeNo":null,"listPrivilege":[{"appPic":"http://yjwang.wmalle.com/image/20180602/8437633679792106.png","createId":155,"createTime":"2018-06-02 15:57:24","isDelete":"N","modifyId":null,"modifyTime":null,"pic":"http://yjwang.wmalle.com/image/20180602/8437630574868406.png","privilegeDesc":"888","privilegeId":14,"privilegeName":"8"}],"maxScore":22,"minScore":1,"pic":"http://yjwang.wmalle.com/image/20180602/8447583170213266.png"},{"appPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134056834.png","gradeId":4,"gradeName":"测试1","gradeNo":99,"listPrivilege":[],"maxScore":38000,"minScore":19000,"pic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134056834.png"},{"appPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134056834.png","gradeId":3,"gradeName":"VIP积分卡","gradeNo":3,"listPrivilege":[],"maxScore":10000,"minScore":null,"pic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134056834.png"},{"appPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134148822.png","gradeId":2,"gradeName":"三星贵宾卡","gradeNo":2,"listPrivilege":[],"maxScore":50000,"minScore":10001,"pic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134148822.png"},{"appPic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134218548.png","gradeId":1,"gradeName":"五星贵宾卡","gradeNo":1,"listPrivilege":[],"maxScore":null,"minScore":50000,"pic":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180329134218548.png"}]
     * code : 0
     * msg : 查询会员卡配置列表成功.
     */

    private boolean flag;
    private int code;
    private String msg;
    private List<DataBean> data;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appPic : http://yjwang.wmalle.com/image/20180604/8586689027146936.png
         * gradeId : 7
         * gradeName : 会员卡1
         * gradeNo : null
         * listPrivilege : [{"appPic":"http://yjwang.wmalle.com/image/20180602/8433304449143812.png","createId":155,"createTime":"2018-06-02 14:45:16","isDelete":"N","modifyId":155,"modifyTime":"2018-06-02 15:25:00","pic":"http://yjwang.wmalle.com/image/20180602/8433301781067906.png","privilegeDesc":"1212121","privilegeId":8,"privilegeName":"特权222"}]
         * maxScore : null
         * minScore : 1
         * pic : http://yjwang.wmalle.com/image/20180604/8586689027146936.png
         */

        private String appPic;
        private int gradeId;
        private String gradeName;
        private Object gradeNo;
        private Object maxScore;
        private int minScore;
        private String pic;
        private List<ListPrivilegeBean> listPrivilege;

        public String getAppPic() {
            return appPic;
        }

        public void setAppPic(String appPic) {
            this.appPic = appPic;
        }

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public Object getGradeNo() {
            return gradeNo;
        }

        public void setGradeNo(Object gradeNo) {
            this.gradeNo = gradeNo;
        }

        public Object getMaxScore() {
            return maxScore;
        }

        public void setMaxScore(Object maxScore) {
            this.maxScore = maxScore;
        }

        public int getMinScore() {
            return minScore;
        }

        public void setMinScore(int minScore) {
            this.minScore = minScore;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public List<ListPrivilegeBean> getListPrivilege() {
            return listPrivilege;
        }

        public void setListPrivilege(List<ListPrivilegeBean> listPrivilege) {
            this.listPrivilege = listPrivilege;
        }

        public static class ListPrivilegeBean {
            /**
             * appPic : http://yjwang.wmalle.com/image/20180602/8433304449143812.png
             * createId : 155
             * createTime : 2018-06-02 14:45:16
             * isDelete : N
             * modifyId : 155
             * modifyTime : 2018-06-02 15:25:00
             * pic : http://yjwang.wmalle.com/image/20180602/8433301781067906.png
             * privilegeDesc : 1212121
             * privilegeId : 8
             * privilegeName : 特权222
             */

            private String appPic;
            private int createId;
            private String createTime;
            private String isDelete;
            private int modifyId;
            private String modifyTime;
            private String pic;
            private String privilegeDesc;
            private int privilegeId;
            private String privilegeName;

            public String getAppPic() {
                return appPic;
            }

            public void setAppPic(String appPic) {
                this.appPic = appPic;
            }

            public int getCreateId() {
                return createId;
            }

            public void setCreateId(int createId) {
                this.createId = createId;
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

            public int getModifyId() {
                return modifyId;
            }

            public void setModifyId(int modifyId) {
                this.modifyId = modifyId;
            }

            public String getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(String modifyTime) {
                this.modifyTime = modifyTime;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPrivilegeDesc() {
                return privilegeDesc;
            }

            public void setPrivilegeDesc(String privilegeDesc) {
                this.privilegeDesc = privilegeDesc;
            }

            public int getPrivilegeId() {
                return privilegeId;
            }

            public void setPrivilegeId(int privilegeId) {
                this.privilegeId = privilegeId;
            }

            public String getPrivilegeName() {
                return privilegeName;
            }

            public void setPrivilegeName(String privilegeName) {
                this.privilegeName = privilegeName;
            }
        }
    }
}
