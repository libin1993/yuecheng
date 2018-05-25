package com.hfbh.yuecheng.bean;

/**
 * Author：Libin on 2018/5/23 11:19
 * Email：1993911441@qq.com
 * Describe：
 */
public class ShopDetailBean {


    /**
     * shop : {"berthNo":"102","buildName":"","clickNum":0,"createTime":"2017-12-05 21:25:56","creatorId":4,"firstLetter":"C","floorId":34,"isDelete":"N","isScoreShop":"N","modifierId":4,"modifyTime":"2018-05-16 11:41:47","organizeId":2,"regionName":"","shopId":97,"shopIntro":"123","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171205212533730.jpg","shopName":"餐厅02","shopNo":"","shopPhone":"13212312312","shopPicture":"http://yjwang.wmalle.com/image/20180516/6953495764437616.jpg","showSerial":null,"spelling":"canting02"}
     * flag : true
     * floor : {"createTime":"2017-10-27 10:41:42","creatorId":4,"floorDesc":"","floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171101110046186.png","floorId":34,"floorName":"B1","floorPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027104131557.jpg","isDelete":"N","modifierId":4,"modifyTime":"2017-11-02 11:25:48","organizeId":2,"showSerial":1}
     * hash : f821322aead14fa6aae01e6ae4ba6aef
     * code : 0
     * mall : {"createTime":"2017-10-10 19:59:09","creatorId":2,"emailAddress":"lin389190263zhen@qq.com","endTime":null,"isDelete":"N","isEnabled":"Y","lat":30.2962,"lng":120.1236,"managerId":4,"modifierId":null,"modifyTime":null,"openType":"OFFICIAL","organizeAddress":"浙江省杭州市西湖区天堂软件园D幢10楼","organizeFirstLetter":"g","organizeId":2,"organizeName":"滁州百大3","organizePicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180413103830882.png","organizeRemark":null,"organizeSpelling":"gouwuzhongxinwushan","organizeType":"MARKET","parentOrganizeId":1,"startTime":null}
     */

    private ShopBean shop;
    private boolean flag;
    private FloorBean floor;
    private String hash;
    private int code;
    private MallBean mall;

    public ShopBean getShop() {
        return shop;
    }

    public void setShop(ShopBean shop) {
        this.shop = shop;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public FloorBean getFloor() {
        return floor;
    }

    public void setFloor(FloorBean floor) {
        this.floor = floor;
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

    public MallBean getMall() {
        return mall;
    }

    public void setMall(MallBean mall) {
        this.mall = mall;
    }

    public static class ShopBean {
        /**
         * berthNo : 102
         * buildName :
         * clickNum : 0
         * createTime : 2017-12-05 21:25:56
         * creatorId : 4
         * firstLetter : C
         * floorId : 34
         * isDelete : N
         * isScoreShop : N
         * modifierId : 4
         * modifyTime : 2018-05-16 11:41:47
         * organizeId : 2
         * regionName :
         * shopId : 97
         * shopIntro : 123
         * shopLogo : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171205212533730.jpg
         * shopName : 餐厅02
         * shopNo :
         * shopPhone : 13212312312
         * shopPicture : http://yjwang.wmalle.com/image/20180516/6953495764437616.jpg
         * showSerial : null
         * spelling : canting02
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
        private String shopPicture;
        private String showSerial;
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

        public String getShopPicture() {
            return shopPicture;
        }

        public void setShopPicture(String shopPicture) {
            this.shopPicture = shopPicture;
        }

        public String getShowSerial() {
            return showSerial;
        }

        public void setShowSerial(String showSerial) {
            this.showSerial = showSerial;
        }

        public String getSpelling() {
            return spelling;
        }

        public void setSpelling(String spelling) {
            this.spelling = spelling;
        }
    }

    public static class FloorBean {
        /**
         * createTime : 2017-10-27 10:41:42
         * creatorId : 4
         * floorDesc :
         * floorIcon : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171101110046186.png
         * floorId : 34
         * floorName : B1
         * floorPicturePath : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027104131557.jpg
         * isDelete : N
         * modifierId : 4
         * modifyTime : 2017-11-02 11:25:48
         * organizeId : 2
         * showSerial : 1
         */

        private String createTime;
        private int creatorId;
        private String floorDesc;
        private String floorIcon;
        private int floorId;
        private String floorName;
        private String floorPicturePath;
        private String isDelete;
        private int modifierId;
        private String modifyTime;
        private int organizeId;
        private int showSerial;

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

        public String getFloorDesc() {
            return floorDesc;
        }

        public void setFloorDesc(String floorDesc) {
            this.floorDesc = floorDesc;
        }

        public String getFloorIcon() {
            return floorIcon;
        }

        public void setFloorIcon(String floorIcon) {
            this.floorIcon = floorIcon;
        }

        public int getFloorId() {
            return floorId;
        }

        public void setFloorId(int floorId) {
            this.floorId = floorId;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        public String getFloorPicturePath() {
            return floorPicturePath;
        }

        public void setFloorPicturePath(String floorPicturePath) {
            this.floorPicturePath = floorPicturePath;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
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

        public int getShowSerial() {
            return showSerial;
        }

        public void setShowSerial(int showSerial) {
            this.showSerial = showSerial;
        }
    }

    public static class MallBean {
        /**
         * createTime : 2017-10-10 19:59:09
         * creatorId : 2
         * emailAddress : lin389190263zhen@qq.com
         * endTime : null
         * isDelete : N
         * isEnabled : Y
         * lat : 30.2962
         * lng : 120.1236
         * managerId : 4
         * modifierId : null
         * modifyTime : null
         * openType : OFFICIAL
         * organizeAddress : 浙江省杭州市西湖区天堂软件园D幢10楼
         * organizeFirstLetter : g
         * organizeId : 2
         * organizeName : 滁州百大3
         * organizePicturePath : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180413103830882.png
         * organizeRemark : null
         * organizeSpelling : gouwuzhongxinwushan
         * organizeType : MARKET
         * parentOrganizeId : 1
         * startTime : null
         */

        private String createTime;
        private int creatorId;
        private String emailAddress;
        private Object endTime;
        private String isDelete;
        private String isEnabled;
        private double lat;
        private double lng;
        private int managerId;
        private Object modifierId;
        private Object modifyTime;
        private String openType;
        private String organizeAddress;
        private String organizeFirstLetter;
        private int organizeId;
        private String organizeName;
        private String organizePicturePath;
        private Object organizeRemark;
        private String organizeSpelling;
        private String organizeType;
        private int parentOrganizeId;
        private Object startTime;

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

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getIsEnabled() {
            return isEnabled;
        }

        public void setIsEnabled(String isEnabled) {
            this.isEnabled = isEnabled;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public int getManagerId() {
            return managerId;
        }

        public void setManagerId(int managerId) {
            this.managerId = managerId;
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

        public String getOpenType() {
            return openType;
        }

        public void setOpenType(String openType) {
            this.openType = openType;
        }

        public String getOrganizeAddress() {
            return organizeAddress;
        }

        public void setOrganizeAddress(String organizeAddress) {
            this.organizeAddress = organizeAddress;
        }

        public String getOrganizeFirstLetter() {
            return organizeFirstLetter;
        }

        public void setOrganizeFirstLetter(String organizeFirstLetter) {
            this.organizeFirstLetter = organizeFirstLetter;
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

        public String getOrganizePicturePath() {
            return organizePicturePath;
        }

        public void setOrganizePicturePath(String organizePicturePath) {
            this.organizePicturePath = organizePicturePath;
        }

        public Object getOrganizeRemark() {
            return organizeRemark;
        }

        public void setOrganizeRemark(Object organizeRemark) {
            this.organizeRemark = organizeRemark;
        }

        public String getOrganizeSpelling() {
            return organizeSpelling;
        }

        public void setOrganizeSpelling(String organizeSpelling) {
            this.organizeSpelling = organizeSpelling;
        }

        public String getOrganizeType() {
            return organizeType;
        }

        public void setOrganizeType(String organizeType) {
            this.organizeType = organizeType;
        }

        public int getParentOrganizeId() {
            return parentOrganizeId;
        }

        public void setParentOrganizeId(int parentOrganizeId) {
            this.parentOrganizeId = parentOrganizeId;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }
    }
}
