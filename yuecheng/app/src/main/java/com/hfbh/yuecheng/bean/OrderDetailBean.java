package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/7/26 17:10
 * Email：1993911441@qq.com
 * Describe：
 */
public class OrderDetailBean {


    /**
     * flag : true
     * hash : 848489fbe87c4e88a44c2e6ad0eb8c68
     * data : {"cancelFrom":"AUTO","closeType":"AUTO","createTime":"2018-07-26 14:55:47","createTimeBegin":"2018-07-26 14:55:47","createTimeEnd":null,"dispatchingWay":"PICKUP","groupState":null,"isDelete":"N","mallShopDO":{"berthNo":"0003","buildName":"","clickNum":0,"createTime":"2017-10-11 10:21:32","creatorId":4,"firstLetter":"B","floorId":1,"isDelete":"N","isScoreShop":"Y","modifierId":4,"modifyTime":"2017-11-13 18:51:28","organizeId":2,"regionName":"","shopId":3,"shopIntro":"装简介1111","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171011102131875.jpg","shopName":"巴拉巴拉","shopNo":"0210107N10101","shopPhone":"13523223432","shopPicture":null,"showSerial":null,"spelling":"balabala"},"memberId":181166,"memberOrderId":87456,"memberOrderShopId":87963,"modifyTime":"2018-07-26 15:10:48","modifyTimeBegin":null,"modifyTimeEnd":null,"orderDtlList":[{"commodityNum":898,"commodityState":"ONLINE","createTime":"2018-07-26 14:55:47","createTimeBegin":null,"createTimeEnd":null,"creatorId":null,"detailAccount":1,"detailId":1102,"detailName":"秒杀商品测试回调","detailPicturepath":"http://yjwang.wmalle.com/image/20180725/13025405880384473.jpg","detailPrice":0.01,"endTime":"2018-08-31 00:00:00","getTimeLimit":7,"groupNum":null,"isDelete":"N","isMustGroup":null,"isRefund":"N","joinNum":null,"memberOrderDetailId":84900,"memberOrderShopId":87963,"modifierId":null,"modifyTime":null,"modifyTimeBegin":null,"modifyTimeEnd":null,"orderNo":"1011022374907201331200","originalPrice":9,"payFrom":"SHOPCART","refundFrom":null,"refundTime":null,"refundTimeBegin":null,"refundTimeEnd":null,"refundTimeLimit":7,"singInDays":null,"startTime":"2018-07-25 00:00:00","state":"NORMAL","thLeftDays":null,"useAccountBalance":null,"verifyState":"INIT","wechatPay":null}],"orderShopNumber":"1011022374907201331200","orderType":"SECKILL","organizeId":2,"payAmount":0.01,"payTime":null,"payTimeBegin":null,"payTimeEnd":null,"payWay":"WECHAT","pickupCode":"SfkJUPTV0","price":0.01,"refundState":"NORMAL","relationId":3,"relationName":"巴拉巴拉","shopPhone":null,"signinTime":null,"signinTimeBegin":null,"signinTimeEnd":null,"state":"CLOSE","sumbitTime":"2018-07-26 14:55:47","sumbitTimeBegin":null,"sumbitTimeEnd":null,"useAccountBalance":0,"wechatPay":0.01}
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
         * cancelFrom : AUTO
         * closeType : AUTO
         * createTime : 2018-07-26 14:55:47
         * createTimeBegin : 2018-07-26 14:55:47
         * createTimeEnd : null
         * dispatchingWay : PICKUP
         * groupState : null
         * isDelete : N
         * mallShopDO : {"berthNo":"0003","buildName":"","clickNum":0,"createTime":"2017-10-11 10:21:32","creatorId":4,"firstLetter":"B","floorId":1,"isDelete":"N","isScoreShop":"Y","modifierId":4,"modifyTime":"2017-11-13 18:51:28","organizeId":2,"regionName":"","shopId":3,"shopIntro":"装简介1111","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171011102131875.jpg","shopName":"巴拉巴拉","shopNo":"0210107N10101","shopPhone":"13523223432","shopPicture":null,"showSerial":null,"spelling":"balabala"}
         * memberId : 181166
         * memberOrderId : 87456
         * memberOrderShopId : 87963
         * modifyTime : 2018-07-26 15:10:48
         * modifyTimeBegin : null
         * modifyTimeEnd : null
         * orderDtlList : [{"commodityNum":898,"commodityState":"ONLINE","createTime":"2018-07-26 14:55:47","createTimeBegin":null,"createTimeEnd":null,"creatorId":null,"detailAccount":1,"detailId":1102,"detailName":"秒杀商品测试回调","detailPicturepath":"http://yjwang.wmalle.com/image/20180725/13025405880384473.jpg","detailPrice":0.01,"endTime":"2018-08-31 00:00:00","getTimeLimit":7,"groupNum":null,"isDelete":"N","isMustGroup":null,"isRefund":"N","joinNum":null,"memberOrderDetailId":84900,"memberOrderShopId":87963,"modifierId":null,"modifyTime":null,"modifyTimeBegin":null,"modifyTimeEnd":null,"orderNo":"1011022374907201331200","originalPrice":9,"payFrom":"SHOPCART","refundFrom":null,"refundTime":null,"refundTimeBegin":null,"refundTimeEnd":null,"refundTimeLimit":7,"singInDays":null,"startTime":"2018-07-25 00:00:00","state":"NORMAL","thLeftDays":null,"useAccountBalance":null,"verifyState":"INIT","wechatPay":null}]
         * orderShopNumber : 1011022374907201331200
         * orderType : SECKILL
         * organizeId : 2
         * payAmount : 0.01
         * payTime : null
         * payTimeBegin : null
         * payTimeEnd : null
         * payWay : WECHAT
         * pickupCode : SfkJUPTV0
         * price : 0.01
         * refundState : NORMAL
         * relationId : 3
         * relationName : 巴拉巴拉
         * shopPhone : null
         * signinTime : null
         * signinTimeBegin : null
         * signinTimeEnd : null
         * state : CLOSE
         * sumbitTime : 2018-07-26 14:55:47
         * sumbitTimeBegin : null
         * sumbitTimeEnd : null
         * useAccountBalance : 0
         * wechatPay : 0.01
         */

        private String cancelFrom;
        private String closeType;
        private String createTime;
        private String createTimeBegin;
        private Object createTimeEnd;
        private String dispatchingWay;
        private Object groupState;
        private String isDelete;
        private MallShopDOBean mallShopDO;
        private int memberId;
        private int memberOrderId;
        private int memberOrderShopId;
        private String modifyTime;
        private Object modifyTimeBegin;
        private Object modifyTimeEnd;
        private String orderShopNumber;
        private String orderType;
        private int organizeId;
        private double payAmount;
        private Object payTime;
        private Object payTimeBegin;
        private Object payTimeEnd;
        private String payWay;
        private String pickupCode;
        private double price;
        private String refundState;
        private int relationId;
        private String relationName;
        private Object shopPhone;
        private Object signinTime;
        private Object signinTimeBegin;
        private Object signinTimeEnd;
        private String state;
        private String sumbitTime;
        private Object sumbitTimeBegin;
        private Object sumbitTimeEnd;
        private double useAccountBalance;
        private double wechatPay;
        private List<OrderDtlListBean> orderDtlList;

        public String getCancelFrom() {
            return cancelFrom;
        }

        public void setCancelFrom(String cancelFrom) {
            this.cancelFrom = cancelFrom;
        }

        public String getCloseType() {
            return closeType;
        }

        public void setCloseType(String closeType) {
            this.closeType = closeType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateTimeBegin() {
            return createTimeBegin;
        }

        public void setCreateTimeBegin(String createTimeBegin) {
            this.createTimeBegin = createTimeBegin;
        }

        public Object getCreateTimeEnd() {
            return createTimeEnd;
        }

        public void setCreateTimeEnd(Object createTimeEnd) {
            this.createTimeEnd = createTimeEnd;
        }

        public String getDispatchingWay() {
            return dispatchingWay;
        }

        public void setDispatchingWay(String dispatchingWay) {
            this.dispatchingWay = dispatchingWay;
        }

        public Object getGroupState() {
            return groupState;
        }

        public void setGroupState(Object groupState) {
            this.groupState = groupState;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public MallShopDOBean getMallShopDO() {
            return mallShopDO;
        }

        public void setMallShopDO(MallShopDOBean mallShopDO) {
            this.mallShopDO = mallShopDO;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getMemberOrderId() {
            return memberOrderId;
        }

        public void setMemberOrderId(int memberOrderId) {
            this.memberOrderId = memberOrderId;
        }

        public int getMemberOrderShopId() {
            return memberOrderShopId;
        }

        public void setMemberOrderShopId(int memberOrderShopId) {
            this.memberOrderShopId = memberOrderShopId;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public Object getModifyTimeBegin() {
            return modifyTimeBegin;
        }

        public void setModifyTimeBegin(Object modifyTimeBegin) {
            this.modifyTimeBegin = modifyTimeBegin;
        }

        public Object getModifyTimeEnd() {
            return modifyTimeEnd;
        }

        public void setModifyTimeEnd(Object modifyTimeEnd) {
            this.modifyTimeEnd = modifyTimeEnd;
        }

        public String getOrderShopNumber() {
            return orderShopNumber;
        }

        public void setOrderShopNumber(String orderShopNumber) {
            this.orderShopNumber = orderShopNumber;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
            this.organizeId = organizeId;
        }

        public double getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(double payAmount) {
            this.payAmount = payAmount;
        }

        public Object getPayTime() {
            return payTime;
        }

        public void setPayTime(Object payTime) {
            this.payTime = payTime;
        }

        public Object getPayTimeBegin() {
            return payTimeBegin;
        }

        public void setPayTimeBegin(Object payTimeBegin) {
            this.payTimeBegin = payTimeBegin;
        }

        public Object getPayTimeEnd() {
            return payTimeEnd;
        }

        public void setPayTimeEnd(Object payTimeEnd) {
            this.payTimeEnd = payTimeEnd;
        }

        public String getPayWay() {
            return payWay;
        }

        public void setPayWay(String payWay) {
            this.payWay = payWay;
        }

        public String getPickupCode() {
            return pickupCode;
        }

        public void setPickupCode(String pickupCode) {
            this.pickupCode = pickupCode;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getRefundState() {
            return refundState;
        }

        public void setRefundState(String refundState) {
            this.refundState = refundState;
        }

        public int getRelationId() {
            return relationId;
        }

        public void setRelationId(int relationId) {
            this.relationId = relationId;
        }

        public String getRelationName() {
            return relationName;
        }

        public void setRelationName(String relationName) {
            this.relationName = relationName;
        }

        public Object getShopPhone() {
            return shopPhone;
        }

        public void setShopPhone(Object shopPhone) {
            this.shopPhone = shopPhone;
        }

        public Object getSigninTime() {
            return signinTime;
        }

        public void setSigninTime(Object signinTime) {
            this.signinTime = signinTime;
        }

        public Object getSigninTimeBegin() {
            return signinTimeBegin;
        }

        public void setSigninTimeBegin(Object signinTimeBegin) {
            this.signinTimeBegin = signinTimeBegin;
        }

        public Object getSigninTimeEnd() {
            return signinTimeEnd;
        }

        public void setSigninTimeEnd(Object signinTimeEnd) {
            this.signinTimeEnd = signinTimeEnd;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getSumbitTime() {
            return sumbitTime;
        }

        public void setSumbitTime(String sumbitTime) {
            this.sumbitTime = sumbitTime;
        }

        public Object getSumbitTimeBegin() {
            return sumbitTimeBegin;
        }

        public void setSumbitTimeBegin(Object sumbitTimeBegin) {
            this.sumbitTimeBegin = sumbitTimeBegin;
        }

        public Object getSumbitTimeEnd() {
            return sumbitTimeEnd;
        }

        public void setSumbitTimeEnd(Object sumbitTimeEnd) {
            this.sumbitTimeEnd = sumbitTimeEnd;
        }

        public double getUseAccountBalance() {
            return useAccountBalance;
        }

        public void setUseAccountBalance(double useAccountBalance) {
            this.useAccountBalance = useAccountBalance;
        }

        public double getWechatPay() {
            return wechatPay;
        }

        public void setWechatPay(double wechatPay) {
            this.wechatPay = wechatPay;
        }

        public List<OrderDtlListBean> getOrderDtlList() {
            return orderDtlList;
        }

        public void setOrderDtlList(List<OrderDtlListBean> orderDtlList) {
            this.orderDtlList = orderDtlList;
        }

        public static class MallShopDOBean {
            /**
             * berthNo : 0003
             * buildName :
             * clickNum : 0
             * createTime : 2017-10-11 10:21:32
             * creatorId : 4
             * firstLetter : B
             * floorId : 1
             * isDelete : N
             * isScoreShop : Y
             * modifierId : 4
             * modifyTime : 2017-11-13 18:51:28
             * organizeId : 2
             * regionName :
             * shopId : 3
             * shopIntro : 装简介1111
             * shopLogo : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171011102131875.jpg
             * shopName : 巴拉巴拉
             * shopNo : 0210107N10101
             * shopPhone : 13523223432
             * shopPicture : null
             * showSerial : null
             * spelling : balabala
             */

            private String berthNo;
            private String buildName;
            private int clickNum;
            private String createTime;
            private int creatorId;
            private String firstLetter;
            private int floorId;
            private String isDelete;
            private String isScoreShop;
            private int modifierId;
            private String modifyTime;
            private int organizeId;
            private String regionName;
            private int shopId;
            private String shopIntro;
            private String shopLogo;
            private String shopName;
            private String shopNo;
            private String shopPhone;
            private Object shopPicture;
            private Object showSerial;
            private String spelling;

            public String getBerthNo() {
                return berthNo;
            }

            public void setBerthNo(String berthNo) {
                this.berthNo = berthNo;
            }

            public String getBuildName() {
                return buildName;
            }

            public void setBuildName(String buildName) {
                this.buildName = buildName;
            }

            public int getClickNum() {
                return clickNum;
            }

            public void setClickNum(int clickNum) {
                this.clickNum = clickNum;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(int creatorId) {
                this.creatorId = creatorId;
            }

            public String getFirstLetter() {
                return firstLetter;
            }

            public void setFirstLetter(String firstLetter) {
                this.firstLetter = firstLetter;
            }

            public int getFloorId() {
                return floorId;
            }

            public void setFloorId(int floorId) {
                this.floorId = floorId;
            }

            public String getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public String getIsScoreShop() {
                return isScoreShop;
            }

            public void setIsScoreShop(String isScoreShop) {
                this.isScoreShop = isScoreShop;
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

            public int getOrganizeId() {
                return organizeId;
            }

            public void setOrganizeId(int organizeId) {
                this.organizeId = organizeId;
            }

            public String getRegionName() {
                return regionName;
            }

            public void setRegionName(String regionName) {
                this.regionName = regionName;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public String getShopIntro() {
                return shopIntro;
            }

            public void setShopIntro(String shopIntro) {
                this.shopIntro = shopIntro;
            }

            public String getShopLogo() {
                return shopLogo;
            }

            public void setShopLogo(String shopLogo) {
                this.shopLogo = shopLogo;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getShopNo() {
                return shopNo;
            }

            public void setShopNo(String shopNo) {
                this.shopNo = shopNo;
            }

            public String getShopPhone() {
                return shopPhone;
            }

            public void setShopPhone(String shopPhone) {
                this.shopPhone = shopPhone;
            }

            public Object getShopPicture() {
                return shopPicture;
            }

            public void setShopPicture(Object shopPicture) {
                this.shopPicture = shopPicture;
            }

            public Object getShowSerial() {
                return showSerial;
            }

            public void setShowSerial(Object showSerial) {
                this.showSerial = showSerial;
            }

            public String getSpelling() {
                return spelling;
            }

            public void setSpelling(String spelling) {
                this.spelling = spelling;
            }
        }

        public static class OrderDtlListBean {
            /**
             * commodityNum : 898
             * commodityState : ONLINE
             * createTime : 2018-07-26 14:55:47
             * createTimeBegin : null
             * createTimeEnd : null
             * creatorId : null
             * detailAccount : 1
             * detailId : 1102
             * detailName : 秒杀商品测试回调
             * detailPicturepath : http://yjwang.wmalle.com/image/20180725/13025405880384473.jpg
             * detailPrice : 0.01
             * endTime : 2018-08-31 00:00:00
             * getTimeLimit : 7
             * groupNum : null
             * isDelete : N
             * isMustGroup : null
             * isRefund : N
             * joinNum : null
             * memberOrderDetailId : 84900
             * memberOrderShopId : 87963
             * modifierId : null
             * modifyTime : null
             * modifyTimeBegin : null
             * modifyTimeEnd : null
             * orderNo : 1011022374907201331200
             * originalPrice : 9
             * payFrom : SHOPCART
             * refundFrom : null
             * refundTime : null
             * refundTimeBegin : null
             * refundTimeEnd : null
             * refundTimeLimit : 7
             * singInDays : null
             * startTime : 2018-07-25 00:00:00
             * state : NORMAL
             * thLeftDays : null
             * useAccountBalance : null
             * verifyState : INIT
             * wechatPay : null
             */

            private int commodityNum;
            private String commodityState;
            private String createTime;
            private Object createTimeBegin;
            private Object createTimeEnd;
            private Object creatorId;
            private int detailAccount;
            private int detailId;
            private String detailName;
            private String detailPicturepath;
            private double detailPrice;
            private String endTime;
            private int getTimeLimit;
            private Object groupNum;
            private String isDelete;
            private Object isMustGroup;
            private String isRefund;
            private Object joinNum;
            private int memberOrderDetailId;
            private int memberOrderShopId;
            private Object modifierId;
            private Object modifyTime;
            private Object modifyTimeBegin;
            private Object modifyTimeEnd;
            private String orderNo;
            private double originalPrice;
            private String payFrom;
            private Object refundFrom;
            private Object refundTime;
            private Object refundTimeBegin;
            private Object refundTimeEnd;
            private int refundTimeLimit;
            private Object singInDays;
            private String startTime;
            private String state;
            private Object thLeftDays;
            private Object useAccountBalance;
            private String verifyState;
            private Object wechatPay;

            public int getCommodityNum() {
                return commodityNum;
            }

            public void setCommodityNum(int commodityNum) {
                this.commodityNum = commodityNum;
            }

            public String getCommodityState() {
                return commodityState;
            }

            public void setCommodityState(String commodityState) {
                this.commodityState = commodityState;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getCreateTimeBegin() {
                return createTimeBegin;
            }

            public void setCreateTimeBegin(Object createTimeBegin) {
                this.createTimeBegin = createTimeBegin;
            }

            public Object getCreateTimeEnd() {
                return createTimeEnd;
            }

            public void setCreateTimeEnd(Object createTimeEnd) {
                this.createTimeEnd = createTimeEnd;
            }

            public Object getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(Object creatorId) {
                this.creatorId = creatorId;
            }

            public int getDetailAccount() {
                return detailAccount;
            }

            public void setDetailAccount(int detailAccount) {
                this.detailAccount = detailAccount;
            }

            public int getDetailId() {
                return detailId;
            }

            public void setDetailId(int detailId) {
                this.detailId = detailId;
            }

            public String getDetailName() {
                return detailName;
            }

            public void setDetailName(String detailName) {
                this.detailName = detailName;
            }

            public String getDetailPicturepath() {
                return detailPicturepath;
            }

            public void setDetailPicturepath(String detailPicturepath) {
                this.detailPicturepath = detailPicturepath;
            }

            public double getDetailPrice() {
                return detailPrice;
            }

            public void setDetailPrice(double detailPrice) {
                this.detailPrice = detailPrice;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public int getGetTimeLimit() {
                return getTimeLimit;
            }

            public void setGetTimeLimit(int getTimeLimit) {
                this.getTimeLimit = getTimeLimit;
            }

            public Object getGroupNum() {
                return groupNum;
            }

            public void setGroupNum(Object groupNum) {
                this.groupNum = groupNum;
            }

            public String getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public Object getIsMustGroup() {
                return isMustGroup;
            }

            public void setIsMustGroup(Object isMustGroup) {
                this.isMustGroup = isMustGroup;
            }

            public String getIsRefund() {
                return isRefund;
            }

            public void setIsRefund(String isRefund) {
                this.isRefund = isRefund;
            }

            public Object getJoinNum() {
                return joinNum;
            }

            public void setJoinNum(Object joinNum) {
                this.joinNum = joinNum;
            }

            public int getMemberOrderDetailId() {
                return memberOrderDetailId;
            }

            public void setMemberOrderDetailId(int memberOrderDetailId) {
                this.memberOrderDetailId = memberOrderDetailId;
            }

            public int getMemberOrderShopId() {
                return memberOrderShopId;
            }

            public void setMemberOrderShopId(int memberOrderShopId) {
                this.memberOrderShopId = memberOrderShopId;
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

            public Object getModifyTimeBegin() {
                return modifyTimeBegin;
            }

            public void setModifyTimeBegin(Object modifyTimeBegin) {
                this.modifyTimeBegin = modifyTimeBegin;
            }

            public Object getModifyTimeEnd() {
                return modifyTimeEnd;
            }

            public void setModifyTimeEnd(Object modifyTimeEnd) {
                this.modifyTimeEnd = modifyTimeEnd;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public double getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(double originalPrice) {
                this.originalPrice = originalPrice;
            }

            public String getPayFrom() {
                return payFrom;
            }

            public void setPayFrom(String payFrom) {
                this.payFrom = payFrom;
            }

            public Object getRefundFrom() {
                return refundFrom;
            }

            public void setRefundFrom(Object refundFrom) {
                this.refundFrom = refundFrom;
            }

            public Object getRefundTime() {
                return refundTime;
            }

            public void setRefundTime(Object refundTime) {
                this.refundTime = refundTime;
            }

            public Object getRefundTimeBegin() {
                return refundTimeBegin;
            }

            public void setRefundTimeBegin(Object refundTimeBegin) {
                this.refundTimeBegin = refundTimeBegin;
            }

            public Object getRefundTimeEnd() {
                return refundTimeEnd;
            }

            public void setRefundTimeEnd(Object refundTimeEnd) {
                this.refundTimeEnd = refundTimeEnd;
            }

            public int getRefundTimeLimit() {
                return refundTimeLimit;
            }

            public void setRefundTimeLimit(int refundTimeLimit) {
                this.refundTimeLimit = refundTimeLimit;
            }

            public Object getSingInDays() {
                return singInDays;
            }

            public void setSingInDays(Object singInDays) {
                this.singInDays = singInDays;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public Object getThLeftDays() {
                return thLeftDays;
            }

            public void setThLeftDays(Object thLeftDays) {
                this.thLeftDays = thLeftDays;
            }

            public Object getUseAccountBalance() {
                return useAccountBalance;
            }

            public void setUseAccountBalance(Object useAccountBalance) {
                this.useAccountBalance = useAccountBalance;
            }

            public String getVerifyState() {
                return verifyState;
            }

            public void setVerifyState(String verifyState) {
                this.verifyState = verifyState;
            }

            public Object getWechatPay() {
                return wechatPay;
            }

            public void setWechatPay(Object wechatPay) {
                this.wechatPay = wechatPay;
            }
        }
    }
}
