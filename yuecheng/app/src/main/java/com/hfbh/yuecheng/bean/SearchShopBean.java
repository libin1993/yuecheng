package com.hfbh.yuecheng.bean;

import java.util.List;

/**
 * Author：Libin on 2018/5/22 15:00
 * Email：1993911441@qq.com
 * Describe：
 */
public class SearchShopBean {

    /**
     * flag : true
     * shopList : [{"berthNo":"102","buildName":"","clickNum":0,"createTime":"2017-12-05 21:25:56","creatorId":4,"firstLetter":"C","floorId":34,"floorName":"B1","industryList":[{"createTime":null,"creatorId":null,"industryId":42,"industryName":"餐饮","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null}],"industryName":null,"isCoupon":"Y","isDelete":"N","isGroup":"N","isScoreShop":"N","isSeckill":"N","isSpecial":"N","modifierId":4,"modifyTime":"2018-05-16 11:41:47","organizeId":2,"organizeIndustryIds":null,"regionName":"","shopId":97,"shopIntro":"123","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171205212533730.jpg","shopName":"餐厅02","shopNo":"","shopPhone":"13212312312","shopPicture":"http://yjwang.wmalle.com/image/20180516/6953495764437616.jpg","shopScores":null,"showSerial":null,"spelling":"canting02"},{"berthNo":"101","buildName":"","clickNum":0,"createTime":"2017-12-05 21:25:17","creatorId":4,"firstLetter":"C","floorId":34,"floorName":"B1","industryList":[{"createTime":null,"creatorId":null,"industryId":42,"industryName":"餐饮","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null}],"industryName":null,"isCoupon":"Y","isDelete":"N","isGroup":"N","isScoreShop":"Y","isSeckill":"N","isSpecial":"N","modifierId":null,"modifyTime":null,"organizeId":2,"organizeIndustryIds":null,"regionName":"","shopId":96,"shopIntro":"123","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171205212356383.gif","shopName":"餐厅01","shopNo":"","shopPhone":"13812345645","shopPicture":null,"shopScores":null,"showSerial":null,"spelling":"canting01"},{"berthNo":"02002","buildName":"A栋","clickNum":0,"createTime":"2018-05-07 17:15:28","creatorId":4,"firstLetter":"N","floorId":55,"floorName":"1","industryList":[{"createTime":null,"creatorId":null,"industryId":42,"industryName":"餐饮","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null}],"industryName":null,"isCoupon":"Y","isDelete":"N","isGroup":"N","isScoreShop":"N","isSeckill":"N","isSpecial":"N","modifierId":4,"modifyTime":"2018-05-16 17:35:45","organizeId":2,"organizeIndustryIds":null,"regionName":"C","shopId":164,"shopIntro":"弄堂里简介","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180507171527724.jpg","shopName":"弄堂里3","shopNo":"00095","shopPhone":"15732635776","shopPicture":"http://yjwang.wmalle.com/image/20180516/6974733826611184.png","shopScores":null,"showSerial":null,"spelling":"nongtangli3"},{"berthNo":"02001","buildName":"A栋","clickNum":0,"createTime":"2018-05-07 13:46:20","creatorId":4,"firstLetter":"N","floorId":55,"floorName":"1","industryList":[{"createTime":null,"creatorId":null,"industryId":42,"industryName":"餐饮","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null}],"industryName":null,"isCoupon":"Y","isDelete":"N","isGroup":"N","isScoreShop":"N","isSeckill":"N","isSpecial":"N","modifierId":null,"modifyTime":null,"organizeId":2,"organizeIndustryIds":null,"regionName":"C","shopId":163,"shopIntro":"弄堂里简介","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180507134619344.jpg","shopName":"弄堂里2","shopNo":"00094","shopPhone":"15732635776","shopPicture":null,"shopScores":null,"showSerial":null,"spelling":"nongtangli2"},{"berthNo":"02000","buildName":"A栋","clickNum":0,"createTime":"2018-05-07 11:20:39","creatorId":4,"firstLetter":"N","floorId":55,"floorName":"1","industryList":[{"createTime":null,"creatorId":null,"industryId":42,"industryName":"餐饮","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null}],"industryName":null,"isCoupon":"Y","isDelete":"N","isGroup":"N","isScoreShop":"N","isSeckill":"N","isSpecial":"N","modifierId":4,"modifyTime":"2018-05-07 13:43:05","organizeId":2,"organizeIndustryIds":null,"regionName":"C","shopId":162,"shopIntro":"弄堂里简介","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180507112039392.jpg","shopName":"弄堂里","shopNo":"000999","shopPhone":"15732635776","shopPicture":null,"shopScores":null,"showSerial":null,"spelling":"nongtangli"},{"berthNo":"51","buildName":"c4","clickNum":0,"createTime":"2017-11-02 11:16:52","creatorId":4,"firstLetter":"H","floorId":34,"floorName":"B1","industryList":[{"createTime":null,"creatorId":null,"industryId":42,"industryName":"餐饮","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null},{"createTime":null,"creatorId":null,"industryId":18,"industryName":"钟表珠宝","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null}],"industryName":null,"isCoupon":"Y","isDelete":"N","isGroup":"N","isScoreShop":"Y","isSeckill":"N","isSpecial":"N","modifierId":4,"modifyTime":"2017-11-03 09:55:58","organizeId":2,"organizeIndustryIds":null,"regionName":"1","shopId":44,"shopIntro":"1234","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171102111519514.jpg","shopName":"哈","shopNo":"","shopPhone":"15077836022","shopPicture":null,"shopScores":null,"showSerial":null,"spelling":"ha"},{"berthNo":"111132","buildName":"1","clickNum":0,"createTime":"2017-12-11 10:20:57","creatorId":4,"firstLetter":"C","floorId":34,"floorName":"B1","industryList":[{"createTime":null,"creatorId":null,"industryId":42,"industryName":"餐饮","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null}],"industryName":null,"isCoupon":"Y","isDelete":"N","isGroup":"N","isScoreShop":"Y","isSeckill":"N","isSpecial":"N","modifierId":null,"modifyTime":null,"organizeId":2,"organizeIndustryIds":null,"regionName":"1","shopId":99,"shopIntro":"2sdfd","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171211102004814.png","shopName":"ceshi111","shopNo":"","shopPhone":"13723423222","shopPicture":null,"shopScores":null,"showSerial":null,"spelling":"ceshi111"},{"berthNo":"5645245","buildName":"2","clickNum":0,"createTime":"2017-11-29 10:10:57","creatorId":4,"firstLetter":"其他","floorId":61,"floorName":"BDCS22","industryList":[{"createTime":null,"creatorId":null,"industryId":17,"industryName":"鞋包皮具","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null}],"industryName":null,"isCoupon":"Y","isDelete":"N","isGroup":"N","isScoreShop":"Y","isSeckill":"N","isSpecial":"N","modifierId":4,"modifyTime":"2017-11-29 13:59:03","organizeId":2,"organizeIndustryIds":null,"regionName":"231","shopId":86,"shopIntro":"22","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171129101034951.jpg","shopName":"522","shopNo":"1324","shopPhone":"15077842222","shopPicture":null,"shopScores":null,"showSerial":null,"spelling":"522"},{"berthNo":"885522","buildName":"2","clickNum":0,"createTime":"2017-11-29 10:00:08","creatorId":4,"firstLetter":"其他","floorId":55,"floorName":"1","industryList":[{"createTime":null,"creatorId":null,"industryId":30,"industryName":"生活服务","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null},{"createTime":null,"creatorId":null,"industryId":29,"industryName":"手机数码","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null}],"industryName":null,"isCoupon":"Y","isDelete":"N","isGroup":"N","isScoreShop":"Y","isSeckill":"N","isSpecial":"N","modifierId":null,"modifyTime":null,"organizeId":2,"organizeIndustryIds":null,"regionName":"12","shopId":85,"shopIntro":"3","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171129095947463.jpg","shopName":"123355","shopNo":"123455","shopPhone":"15077452333","shopPicture":null,"shopScores":null,"showSerial":null,"spelling":"123355"},{"berthNo":"31313","buildName":"2","clickNum":0,"createTime":"2017-11-29 16:03:05","creatorId":4,"firstLetter":"其他","floorId":55,"floorName":"1","industryList":[{"createTime":null,"creatorId":null,"industryId":28,"industryName":"内衣配饰","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null}],"industryName":null,"isCoupon":"Y","isDelete":"N","isGroup":"N","isScoreShop":"Y","isSeckill":"N","isSpecial":"N","modifierId":4,"modifyTime":"2017-11-30 10:15:28","organizeId":2,"organizeIndustryIds":null,"regionName":"2313","shopId":89,"shopIntro":"1","shopLogo":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171129160249314.jpg","shopName":"123123","shopNo":"12312312","shopPhone":"15077845557","shopPicture":null,"shopScores":null,"showSerial":null,"spelling":"123123"}]
     * page : {"pageNum":1,"pageSize":10,"pages":4,"total":40}
     * hash : 16aeaf8a567a4ce99f4c2299c8907305
     * floorList : [{"createTime":"2018-01-03 16:57:33","creatorId":4,"floorDesc":"","floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180103165731213.jpg","floorId":79,"floorName":"1sfwegwer1","floorPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180103165728205.jpg","isDelete":"N","modifierId":4,"modifyTime":"2018-05-07 10:53:37","organizeId":2,"shopId":null,"showSerial":1},{"createTime":"2017-11-27 15:03:16","creatorId":4,"floorDesc":"","floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171127150311773.jpg","floorId":61,"floorName":"BDCS22","floorPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171127150303351.jpg","isDelete":"N","modifierId":4,"modifyTime":"2018-01-03 13:35:21","organizeId":2,"shopId":null,"showSerial":1},{"createTime":"2017-11-22 17:17:14","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171122171709996.png","floorId":55,"floorName":"1","floorPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171122171706417.png","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":1},{"createTime":"2017-10-27 10:41:42","creatorId":4,"floorDesc":"","floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171101110046186.png","floorId":34,"floorName":"B1","floorPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027104131557.jpg","isDelete":"N","modifierId":4,"modifyTime":"2017-11-02 11:25:48","organizeId":2,"shopId":null,"showSerial":1},{"createTime":"2017-10-11 09:41:48","creatorId":4,"floorDesc":"","floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171013142132025.png","floorId":1,"floorName":"1F","floorPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171011094217784.jpg","isDelete":"N","modifierId":4,"modifyTime":"2017-11-02 11:25:55","organizeId":2,"shopId":null,"showSerial":2},{"createTime":"2017-10-19 14:56:42","creatorId":4,"floorDesc":"","floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027161552775.png","floorId":29,"floorName":"2F","floorPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171019145606816.jpg","isDelete":"N","modifierId":4,"modifyTime":"2017-11-02 11:26:09","organizeId":2,"shopId":null,"showSerial":3},{"createTime":"2017-10-15 15:17:47","creatorId":15,"floorDesc":"","floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027161600400.png","floorId":2,"floorName":"3F","floorPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171015151522866.jpg","isDelete":"N","modifierId":4,"modifyTime":"2017-11-02 11:26:04","organizeId":2,"shopId":null,"showSerial":4},{"createTime":"2017-10-20 08:55:13","creatorId":4,"floorDesc":"","floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027161608568.png","floorId":30,"floorName":"4F","floorPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171020085426945.jpg","isDelete":"N","modifierId":4,"modifyTime":"2017-11-30 13:43:13","organizeId":2,"shopId":null,"showSerial":5},{"createTime":"2017-12-22 11:25:11","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171222112455911.jpg","floorId":77,"floorName":"B1","floorPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171222112449380.jpg","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":11},{"createTime":"2018-05-07 09:37:55","creatorId":4,"floorDesc":"","floorIcon":"http://yjwang.wmalle.com/image/20180507/6168423543101335.jpg","floorId":100,"floorName":"abcd13456efghij12345abcdefghi12345j11111","floorPicturePath":"","isDelete":"N","modifierId":4,"modifyTime":"2018-05-07 09:50:12","organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2018-05-04 11:11:00","creatorId":4,"floorDesc":null,"floorIcon":"http://yjwang.wmalle.com/image/20180504/5914823680361081.jpg","floorId":98,"floorName":"122","floorPicturePath":"http://yjwang.wmalle.com/image/20180504/5914739560619144.jpg","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-12-28 09:27:52","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171228092750795.jpg","floorId":78,"floorName":"2","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-29 16:01:56","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171129160154800.jpg","floorId":74,"floorName":"333","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-29 16:01:18","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171129160114619.jpg","floorId":73,"floorName":"333","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-29 10:09:37","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171129100936006.jpg","floorId":72,"floorName":"54546","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-29 09:54:36","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171129095434301.jpg","floorId":71,"floorName":"333","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-28 18:26:31","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171128182627871.jpg","floorId":70,"floorName":"124124","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-28 18:25:53","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171128182549437.jpg","floorId":69,"floorName":"213123","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-28 17:49:19","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171128174915672.jpg","floorId":68,"floorName":"333","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-28 17:45:16","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171128174512524.jpg","floorId":67,"floorName":"1231","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-28 16:13:17","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171128161308635.jpg","floorId":66,"floorName":"55","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-28 16:09:55","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171128160908910.jpg","floorId":65,"floorName":"333","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-28 16:08:46","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171128160839255.jpg","floorId":64,"floorName":"123","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-27 17:12:20","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171127171215210.jpg","floorId":63,"floorName":"cs","floorPicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171127171212617.jpg","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-27 15:36:05","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171127153549266.png","floorId":62,"floorName":"23","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null},{"createTime":"2017-11-22 17:18:31","creatorId":4,"floorDesc":null,"floorIcon":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171122171829577.png","floorId":56,"floorName":"2","floorPicturePath":"","isDelete":"N","modifierId":null,"modifyTime":null,"organizeId":2,"shopId":null,"showSerial":null}]
     * data : null
     * code : 0
     * mall : {"createTime":"2017-10-10 19:59:09","creatorId":2,"emailAddress":"lin389190263zhen@qq.com","endTime":null,"isDelete":"N","isEnabled":"Y","lat":30.2962,"lng":120.1236,"managerId":4,"modifierId":null,"modifyTime":null,"openType":"OFFICIAL","organizeAddress":"浙江省杭州市西湖区天堂软件园D幢10楼","organizeFirstLetter":"g","organizeId":2,"organizeName":"滁州百大3","organizePicturePath":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180413103830882.png","organizeRemark":null,"organizeSpelling":"gouwuzhongxinwushan","organizeType":"MARKET","parentOrganizeId":1,"startTime":null}
     * industryList : [{"industryId":2,"industryName":"时尚服装","organizeId":2,"organizeIndustryId":1,"shopCount":6,"shopId":null,"styleName":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171012175450625.png"},{"industryId":17,"industryName":"鞋包皮具","organizeId":2,"organizeIndustryId":3,"shopCount":8,"shopId":null,"styleName":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027160859907.png"},{"industryId":30,"industryName":"生活服务","organizeId":2,"organizeIndustryId":4,"shopCount":4,"shopId":null,"styleName":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027161109458.png"},{"industryId":16,"industryName":"母婴童装","organizeId":2,"organizeIndustryId":9,"shopCount":1,"shopId":null,"styleName":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027160841305.png"},{"industryId":27,"industryName":"家居生活","organizeId":2,"organizeIndustryId":19,"shopCount":5,"shopId":null,"styleName":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027161004275.png"},{"industryId":21,"industryName":"休闲娱乐","organizeId":2,"organizeIndustryId":26,"shopCount":5,"shopId":null,"styleName":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027160931665.png"},{"industryId":29,"industryName":"手机数码","organizeId":2,"organizeIndustryId":27,"shopCount":4,"shopId":null,"styleName":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027161033492.png"},{"industryId":18,"industryName":"钟表珠宝","organizeId":2,"organizeIndustryId":34,"shopCount":1,"shopId":null,"styleName":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027160915674.png"},{"industryId":42,"industryName":"餐饮","organizeId":2,"organizeIndustryId":69,"shopCount":15,"shopId":null,"styleName":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027161213167.png"},{"industryId":26,"industryName":"运动户外","organizeId":2,"organizeIndustryId":128,"shopCount":1,"shopId":null,"styleName":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027160946737.png"},{"industryId":28,"industryName":"内衣配饰","organizeId":2,"organizeIndustryId":132,"shopCount":3,"shopId":null,"styleName":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027161016918.png"},{"industryId":41,"industryName":"便利超市","organizeId":2,"organizeIndustryId":140,"shopCount":1,"shopId":null,"styleName":"http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171027161200038.png"}]
     */

    private boolean flag;
    private PageBean page;
    private String hash;
    private Object data;
    private int code;
    private MallBean mall;
    private List<ShopListBean> shopList;
    private List<FloorListBean> floorList;
    private List<IndustryListBeanX> industryList;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    public List<ShopListBean> getShopList() {
        return shopList;
    }

    public void setShopList(List<ShopListBean> shopList) {
        this.shopList = shopList;
    }

    public List<FloorListBean> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<FloorListBean> floorList) {
        this.floorList = floorList;
    }

    public List<IndustryListBeanX> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(List<IndustryListBeanX> industryList) {
        this.industryList = industryList;
    }

    public static class PageBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * pages : 4
         * total : 40
         */

        private int pageNum;
        private int pageSize;
        private int pages;
        private int total;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
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

    public static class ShopListBean {
        /**
         * berthNo : 102
         * buildName :
         * clickNum : 0
         * createTime : 2017-12-05 21:25:56
         * creatorId : 4
         * firstLetter : C
         * floorId : 34
         * floorName : B1
         * industryList : [{"createTime":null,"creatorId":null,"industryId":42,"industryName":"餐饮","industryRemark":null,"isDelete":null,"modifierId":null,"modifyTime":null,"styleName":null}]
         * industryName : null
         * isCoupon : Y
         * isDelete : N
         * isGroup : N
         * isScoreShop : N
         * isSeckill : N
         * isSpecial : N
         * modifierId : 4
         * modifyTime : 2018-05-16 11:41:47
         * organizeId : 2
         * organizeIndustryIds : null
         * regionName :
         * shopId : 97
         * shopIntro : 123
         * shopLogo : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171205212533730.jpg
         * shopName : 餐厅02
         * shopNo :
         * shopPhone : 13212312312
         * shopPicture : http://yjwang.wmalle.com/image/20180516/6953495764437616.jpg
         * shopScores : null
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
        private String floorName;
        private String industryName;
        private String isCoupon;
        private String isDelete;
        private String isGroup;
        private String isScoreShop;
        private String isSeckill;
        private String isSpecial;
        private int modifierId;
        private String modifyTime;
        private int organizeId;
        private Object organizeIndustryIds;
        private String regionName;
        private int shopId;
        private String shopIntro;
        private String shopLogo;
        private String shopName;
        private String shopNo;
        private String shopPhone;
        private String shopPicture;
        private Object shopScores;
        private Object showSerial;
        private String spelling;
        private List<IndustryListBean> industryList;

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

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public String getIsCoupon() {
            return isCoupon;
        }

        public void setIsCoupon(String isCoupon) {
            this.isCoupon = isCoupon;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getIsGroup() {
            return isGroup;
        }

        public void setIsGroup(String isGroup) {
            this.isGroup = isGroup;
        }

        public String getIsScoreShop() {
            return isScoreShop;
        }

        public void setIsScoreShop(String isScoreShop) {
            this.isScoreShop = isScoreShop;
        }

        public String getIsSeckill() {
            return isSeckill;
        }

        public void setIsSeckill(String isSeckill) {
            this.isSeckill = isSeckill;
        }

        public String getIsSpecial() {
            return isSpecial;
        }

        public void setIsSpecial(String isSpecial) {
            this.isSpecial = isSpecial;
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

        public Object getOrganizeIndustryIds() {
            return organizeIndustryIds;
        }

        public void setOrganizeIndustryIds(Object organizeIndustryIds) {
            this.organizeIndustryIds = organizeIndustryIds;
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

        public Object getShopScores() {
            return shopScores;
        }

        public void setShopScores(Object shopScores) {
            this.shopScores = shopScores;
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

        public List<IndustryListBean> getIndustryList() {
            return industryList;
        }

        public void setIndustryList(List<IndustryListBean> industryList) {
            this.industryList = industryList;
        }

        public static class IndustryListBean {
            /**
             * createTime : null
             * creatorId : null
             * industryId : 42
             * industryName : 餐饮
             * industryRemark : null
             * isDelete : null
             * modifierId : null
             * modifyTime : null
             * styleName : null
             */

            private Object createTime;
            private Object creatorId;
            private int industryId;
            private String industryName;
            private Object industryRemark;
            private Object isDelete;
            private Object modifierId;
            private Object modifyTime;
            private Object styleName;

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

            public int getIndustryId() {
                return industryId;
            }

            public void setIndustryId(int industryId) {
                this.industryId = industryId;
            }

            public String getIndustryName() {
                return industryName;
            }

            public void setIndustryName(String industryName) {
                this.industryName = industryName;
            }

            public Object getIndustryRemark() {
                return industryRemark;
            }

            public void setIndustryRemark(Object industryRemark) {
                this.industryRemark = industryRemark;
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

            public Object getStyleName() {
                return styleName;
            }

            public void setStyleName(Object styleName) {
                this.styleName = styleName;
            }
        }
    }

    public static class FloorListBean {
        /**
         * createTime : 2018-01-03 16:57:33
         * creatorId : 4
         * floorDesc :
         * floorIcon : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180103165731213.jpg
         * floorId : 79
         * floorName : 1sfwegwer1
         * floorPicturePath : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20180103165728205.jpg
         * isDelete : N
         * modifierId : 4
         * modifyTime : 2018-05-07 10:53:37
         * organizeId : 2
         * shopId : null
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
        private Object shopId;
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

        public Object getShopId() {
            return shopId;
        }

        public void setShopId(Object shopId) {
            this.shopId = shopId;
        }

        public int getShowSerial() {
            return showSerial;
        }

        public void setShowSerial(int showSerial) {
            this.showSerial = showSerial;
        }
    }

    public static class IndustryListBeanX {
        /**
         * industryId : 2
         * industryName : 时尚服装
         * organizeId : 2
         * organizeIndustryId : 1
         * shopCount : 6
         * shopId : null
         * styleName : http://wmalle.oss-cn-hangzhou.aliyuncs.com/20171012175450625.png
         */

        private int industryId;
        private String industryName;
        private int organizeId;
        private int organizeIndustryId;
        private int shopCount;
        private Object shopId;
        private String styleName;

        public int getIndustryId() {
            return industryId;
        }

        public void setIndustryId(int industryId) {
            this.industryId = industryId;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public int getOrganizeId() {
            return organizeId;
        }

        public void setOrganizeId(int organizeId) {
            this.organizeId = organizeId;
        }

        public int getOrganizeIndustryId() {
            return organizeIndustryId;
        }

        public void setOrganizeIndustryId(int organizeIndustryId) {
            this.organizeIndustryId = organizeIndustryId;
        }

        public int getShopCount() {
            return shopCount;
        }

        public void setShopCount(int shopCount) {
            this.shopCount = shopCount;
        }

        public Object getShopId() {
            return shopId;
        }

        public void setShopId(Object shopId) {
            this.shopId = shopId;
        }

        public String getStyleName() {
            return styleName;
        }

        public void setStyleName(String styleName) {
            this.styleName = styleName;
        }
    }
}
