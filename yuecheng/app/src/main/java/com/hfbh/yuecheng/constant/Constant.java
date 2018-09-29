package com.hfbh.yuecheng.constant;

/**
 * Author：Libin on 2018/5/15 17:17
 * Email：1993911441@qq.com
 * Describe：接口
 */
public class Constant {

    private static final String HOST = "https://m.hfbh.com.cn";
//        private static final String HOST = "http://ceshi.wmalle.com";
//    private static final String HOST = "http://yjwang.wmalle.com";
    //微信appid
    public static final String APP_ID = "wxde304ec90e9db692";
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
    //现金报名数据
    public static final String CASH_ENROLL_INFO = HOST + "/wechat/wechat/signup_activity/signup_record.json";
    //用户余额
    public static final String USER_BALANCE = HOST + "/wechat/member/queryAmount.json";
    //活动报名冻结余额
    public static final String FROZEN_ENROLL_BALANCE = HOST + "/wechat/wechat/signup_activity/preparePay.json";
    //活动余额全额支付
    public static final String ENROLL_BALANCE_PAY = HOST + "/wechat/wechat/signup_activity/getPayDataSuccess.json";
    //积分报名
    public static final String POINTS_ENROLL_ACTIVITY = HOST + "/wechat/wechat/signup_activity/signup_submit.json";
    //现金报名
    public static final String CASH_ENROLL_ACTIVITY = HOST + "/wechat/wechat/signup_activity/getPayData.json";
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
    public static final String MEMBER_CARD_BALANCE = HOST + "/wechat/member/prePaidCardList.json";
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
    //消费记录
    public static final String CONSUME_RECORD = HOST + "/wechat/member/saleIntems.json";
    //未读消息
    public static final String UNREAD_MSG = HOST + "/wechat/broadcast/notReadNum.json";
    //消息列表
    public static final String MSG_LIST = HOST + "/wechat/broadcast/list.json";
    //设置已读消息
    public static final String READ_MSG = HOST + "/wechat/broadcast/update.json";
    //支付参数
    public static final String PAY_DATA = HOST + "/wechat/order/getPayData.json";
    //商品详情
    public static final String GOODS_DETAIL = HOST + "/wechat/commodity/commodity.json";
    //提交订单
    public static final String SUBMIT_ORDER = HOST + "/wechat/order/submitOrder.json";
    //商品余额全额支付
    public static final String PAY_ORDER_BALANCE = HOST + "/wechat/order/confirmBalance.json";
    //商品余额冻结
    public static final String FROZEN_ORDER_BALANCE = HOST + "/wechat/order/freezeBalance.json";
    //主扫订单
    public static final String SCAN_ORDER = HOST + "/wechat/scanCodePay/getMemberPromotion.json";
    //主扫订单金额计算
    public static final String SCAN_ORDER_MONEY = HOST + "/wechat/scanCodePay/recountMoney.json";
    //提交主扫订单
    public static final String SCAN_ORDER_SUBMIT = HOST + "/wechat/scanCodePay/confirmPay.json";
    //订单列表
    public static final String ORDER_LIST = HOST + "/wechat/memberOrderShop/listOrder.json";
    //去支付
    public static final String PAY_ORDER = HOST + "/wechat/order/orderView.json";
    //取消订单
    public static final String CANCEL_ORDER = HOST + "/wechat/memberOrderShop/cancelOrder.json";
    //订单详情
    public static final String ORDER_DETAIL = HOST + "/wechat/memberOrderShop/queryOrderDtl.json";
    //申请退款
    public static final String APPLY_REFUND = HOST + "/wechat/memberRefundApply/save.json";
    //退款列表
    public static final String REFUND_LIST = HOST + "/wechat/memberRefundApply/queryMyRefundList.json";
    //退款详情
    public static final String REFUND_DETAIL = HOST + "/wechat/memberRefundApply/queryOne.json";
    //个推提交clientId
    public static final String CLIENT_ID = HOST + "/wechat/appRequest/addClientId.json";

    //WebView
    //活动详情
    public static final String ACTIVITY_DETAIL = HOST + "/mall/baida-app-h5/index.html#/activity";
    //人气单品详情
    public static final String POP_GOODS_DETAIL = HOST + "/mall/baida-app-h5/index.html#/commodity/specialDetail";
    //新品详情
    public static final String NEW_GOODS_DETAIL = HOST + "/mall/baida-app-h5/index.html#/commodity/newDetail";
    //用户协议
    public static final String USER_AGREEMENT = HOST + "/mall/baida-app-h5/index.html#/protocol";
    //秒杀商品详情
    public static final String RUSH_GOODS_DETAIL = HOST + "/mall/baida-app-h5/index.html#/commodity/secKill";
    //团购商品详情
    public static final String GROUP_GOODS_DETAIL = HOST + "/mall/baida-app-h5/index.html#/commodity/group";
    //积分兑礼详情
    public static final String EXCHANGE_GIFT_DETAIL = HOST +"/mall/baida-app-h5/index.html#/points";

}
