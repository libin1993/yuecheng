package com.hfbh.yuecheng.constant;

/**
 * Author：Libin on 2018/5/15 17:17
 * Email：1993911441@qq.com
 * Describe：接口
 */
public class Constant {
    private static final String HOST = "http://ceshi.wmalle.com";
    //    private static final String HOST = "https://m.hfbh.com.cn";
    //定位
    public static final String LOCATION = HOST + "/wechat/appRequest/nearestMall.json";
    //首页分类
    public static final String HOMEPAGE_TYPE = HOST + "/wechat/app/homepage/homePage.json";
    //首页内容
    public static final String HOMEPAGE_MODULE = HOST + "/wechat/app/homepage/queryByCode.json";
    //发现商品
    public static final String DISCOVERY_GOODS = HOST + "/wechat/commodity/listCommodity.json";
    //活动列表
    public static final String ACTIVITY_LIST = HOST + "/wechat/signupActivity/list.json";
    //我的活动列表
    public static final String MY_ACTIVITY_LIST = HOST + "/wechat/wechat/signup_activity/list.json";
    //活动详情
    public static final String ACTIVITY_INFO = HOST + "/wechat/wechat/signup_activity/detail.json";
    //活动日历
    public static final String CALENDAR_ACTIVITY = HOST + "/wechat/signupActivity/list.json";
    //活动核销
    public static final String CLOSE_ACTIVITY = HOST + "/wechat/wechat/signup_activity/verify_codes.json";
    //活动报名数据
    public static final String ENROLL_ACTIVITY_INFO = HOST + "/wechat/wechat/signup_activity/signup_form.json";
    //活动报名
    public static final String ENROLL_ACTIVITY = HOST + "/wechat/wechat/signup_activity/signup_submit.json";
    //切换城市
    public static final String CHANGE_CITY = HOST + "/wechat/cityConfig/cityList.json";
    //店铺列表
    public static final String SHOP_LIST = HOST + "/wechat/shop/getShopList.json";
    //店铺详情
    public static final String SHOP_DETAIL = HOST + "/wechat/shop/shopDetail.json";
    //礼品列表
    public static final String EXCHANGE_GIFT_LIST = HOST + "/wechat/pointsReward/list.json";
    //兑换优惠券列表
    public static final String EXCHANGE_COUPON_LIST = HOST + "/wechat/webcoupon/listCouponDefault.json";
    //我的优惠券
    public static final String MY_COUPON = HOST + "/wechat/memberCoupon/memberCoupon.json";
    //礼品信息
    public static final String GIFT_DETAIL = HOST + "/wechat/pointsReward/info.json";
    //优惠券详情
    public static final String COUPON_DETAIL = HOST + "/wechat/webcoupon/getCouponDesc.json";
    //兑换礼品
    public static final String EXCHANGE_GIFT = HOST + "/wechat/pointsReward/exchange.json";
    //我的兑换礼品
    public static final String MY_EXCHANGE_GIFT = HOST + "/wechat/memberCoupon/listMemberCoupon.json";
    //礼品核销
    public static final String CLOSE_GIFT = HOST + "/wechat/memberCoupon/getVerifyCoupon.json";
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
    //积分记录
    public static final String MEMBER_POINTS_RECORD = HOST + "/wechat/member/pointsRecord.json";
    //预付卡
    public static final String MEMBER_BALANCE = HOST + "/wechat/member/prePaidCardList.json";
    //删除预付卡
    public static final String DELETE_PAY_CARD = HOST + "/wechat/member/deleteCard.json";
    //绑定预付卡
    public static final String BIND_PAY_CARD = HOST + "/wechat/member/savePrePaidCard.json";
    //余额明细
    public static final String BALANCE_RECORD = HOST + "/wechat/member/accountChangeRecord.json";
    //权限列表
    public static final String MEMBER_RIGHTS = HOST + "/wechat/memberCard/listCardLevel.json";
    //领取优惠券
    public static final String EXCHANGE_COUPON = HOST + "/wechat/webcoupon/exchangeByPoints.json";
    //退出登录
    public static final String LOG_OUT = HOST + "/wechat/appRequest/loginOut.json";
    //检测是否登录
    public static final String IS_LOGIN = HOST + "/wechat/appRequest/memberIsLogin.json";
    //检测订单
    public static final String QUERY_ORDER = HOST + "/wechat/scanCodePay/queryOrder.json";
    //确认订单
    public static final String CONFIRM_ORDER = HOST + "/wechat/scanCodePay/confirmOrder.json";
    //检测更新
    public static final String CHECK_UPDATE = HOST + "/wechat/appRequest/appVersion.json";
    //室内导航
    public static final String FLOOR_GUIDE = HOST + "/wechat/mallFloor/floorList.json";

    //WebView
    //活动详情
    public static final String ACTIVITY_DETAIL = HOST + "/mall/baida-app-h5/index.html#/activity";
    //人气单品详情
    public static final String POP_GOODS_DETAIL = HOST + "/mall/baida-app-h5/index.html#/commodity/specialDetail";
    //新品详情
    public static final String NEW_GOODS_DETAIL = HOST + "/mall/baida-app-h5/index.html#/commodity/newDetail";
    //用户协议
    public static final String USER_AGREEMENT = HOST + "/mall/baida-app-h5/index.html#/protocol";

}
