package com.hfbh.yuecheng.constant;

/**
 * Author：Libin on 2018/5/15 17:17
 * Email：1993911441@qq.com
 * Describe：接口
 */
public class Constant {
    private static final String HOST = "https://yjwang.wmalle.com";
    //定位
    public static final String LOCATION = HOST + "/wechat/appRequest/nearestMall.json";
    //首页分类
    public static final String HOMEPAGE_TYPE = HOST + "/wechat/homepage/homePage.json";
    //首页内容
    public static final String HOMEPAGE_MODULE = HOST + "/wechat/homepage/queryByCode.json";
    //发现商品
    public static final String DISCOVERY_GOODS = HOST + "/wechat/commodity/listCommodity.json";
    //活动列表
    public static final String ACTIVITY_LIST = HOST + "/wechat/wechat/signup_activity/list.json";
    //切换城市
    public static final String CHANGE_CITY = HOST + "/wechat/cityConfig/cityList.json";
    //店铺列表
    public static final String SHOP_LIST = HOST + "/wechat/shop/getShopList.json";
    //
    public static final String SHOP_MORE = HOST + "/wechat/shop/loadMoreShop.json";
    //店铺详情
    public static final String SHOP_DETAIL = HOST + "/wechat/shop/shopDetail.json";
    //
    public static final String QR_CODE = "https://pan.baidu.com/share/qrcode?w=150&h=150&url=http://www.xsyrz.cn";
    //会员码
    public static final String MEMBER_CODE = HOST + "/wechat/member/queryMember.json";
    //
    public static final String PAY_CODE = HOST + "/wechat/scanCodePay/queryOrder.json";
    //优惠券列表
    public static final String EXCHANGE_GIFT_LIST = HOST + "/wechat/pointsReward/list.json";
    //礼品信息
    public static final String GIFT_INFO = HOST + "/wechat/pointsReward/info.json";
    //兑换礼品
    public static final String EXCHANGE_GIFT = HOST + "/wechat/pointsReward/exchange.json";
    //注册
    public static final String REGISTER = HOST + "/wechat/appRequest/register.json";
    //是否注册
    public static final String IS_REGISTER = HOST + "/wechat/appRequest/queryByPhone.json";
    //修改密码
    public static final String UPDATE_PWD = HOST + "/wechat/appRequest/forgetPwd.json";
    //验证码登录
    public static final String CODE_LOGIN = HOST + "/wechat/appRequest/appVerificationLogin.json";
    //密码登录
    public static final String PWD_LOGIN = HOST + "/wechat/appRequest/appLogin.json";
    //获取验证码
    public static final String SECURITY_CODE = HOST + "/wechat/register/getSecurityCode.json";
    //用户信息
    public static final String USER_INFO = HOST + "/wechat/member/queryMember.json";
    //修改用户信息
    public static final String UPDATE_USER_INFO = HOST + "/wechat/appRequest/updateMemberInfo.json";
    //图片上传
    public static final String UPLOAD_FILE = HOST + "/wechat/wechatCommon/saveAppImgToLocal.json";
    //意见反馈
    public static final String FEED_BACK = HOST + "/wechat/feedback/save.json";
    //是否绑定支付密码
    public static final String IS_SET_PAY_PWD = HOST + "/wechat/member/isBindPassword.json";
    //身份验证
    public static final String VALIDATE_PHONE = HOST + "/wechat/member/validatePhone.json";
    //绑定支付密码
    public static final String BIND_PAY_PWD = HOST + "/wechat/member/bindPassword.json";
    //重置支付密码
    public static final String RESET_PAY_PWD = HOST + "/wechat/member/resetPassword.json";
    //验证支付密码
    public static final String VALIDATE_PWD = HOST + "/wechat/member/validatePassword.json";
    //会员卡
    public static final String MEMBER_CARD = HOST + "/wechat/member/accountChangeRecord.json";
    //积分记录
    public static final String MEMBER_POINTS_RECORD = HOST + "/wechat/member/pointsRecord.json";
    //预付卡
    public static final String MEMBER_BALANCE = HOST + "/wechat/member/prePaidCardList.json";
    //删除预付卡
    public static final String DELETE_PAY_CARD = HOST + "/wechat/member/deleteCard.json";
    //绑定预付卡
    public static final String BIND_PAY_CARD = HOST + "/wechat/member/savePrePaidCard.json";
    //余额明细
    public static final String BALANCE_RECORD = HOST +"/wechat/member/accountChangeRecord.json";
}
