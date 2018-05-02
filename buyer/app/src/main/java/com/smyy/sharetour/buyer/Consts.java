package com.smyy.sharetour.buyer;

/**
 * Created by 伍振飞 on 2018/4/8 14:28
 * E-Mail Address：wuzf2012@sina.com
 */
public class Consts {
    /**
     * 判断是否平台账号
     */
    public static boolean isPhoneNum(String num) {
        boolean isPhoneNum = false;
        for (int i = 0; i < mTitles.length; i++) {
            if (mTitles[i].equals(num)) {
                isPhoneNum = true;
            }
        }
        return isPhoneNum;
    }

    /**
     * 默认账号
     */
    private static String[] mTitles = {"18680445592", "15607003736", "12345678901"};
    /**
     * 默认密码
     */
    public static String DEFAULT_LOGIN_PWD = "123456";
    /**
     * 默认验证码
     */
    public static String DEFAULT_SMS_CODE = "111111";
    /**
     * 默认倒计时时间
     */
    public static int DEFAULT_COUNTDOWN_TIME = 60;
    /**
     * 默认手机最小长度
     */
    public static int MIN_PHONE_LENGTH = 10;
    /**
     * 默认密码最小长度
     */
    public static int MIN_PWD_LENGTH = 5;
    /**
     * 默认验证码最小长度
     */
    public static int MIN_SMS_LENGTH = 5;

    public static String noteDetailsData = "{\n" +
            "\t\"mainList\": [{\n" +
            "\t\t\"imageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\"name\": \"老王\",\n" +
            "\t\t\"praise\": 88,\n" +
            "\t\t\"content\": \"看起来不错，不知道实际用了会怎样？看起来不错，不知道实际用了会怎样？看起来不错，不知道实际用了会怎样？\",\n" +
            "\t\t\"time\": \"12-03\",\n" +
            "\t\t\"csList\": [{\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"老赵\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}, {\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}, {\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}, {\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}, {\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"imageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\"name\": \"老王\",\n" +
            "\t\t\"praise\": 88,\n" +
            "\t\t\"content\": \"看起来不错，不知道实际用了会怎样？看起来不错，不知道实际用了会怎样？看起来不错，不知道实际用了会怎样？\",\n" +
            "\t\t\"time\": \"12-03\",\n" +
            "\t\t\"csList\": []\n" +
            "\t}, {\n" +
            "\t\t\"imageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\"name\": \"老王\",\n" +
            "\t\t\"praise\": 88,\n" +
            "\t\t\"content\": \"看起来不错，不知道实际用了会怎样？看起来不错，不知道实际用了会怎样？看起来不错，不知道实际用了会怎样？\",\n" +
            "\t\t\"time\": \"12-03\",\n" +
            "\t\t\"csList\": [{\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"老赵\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}, {\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"imageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\"name\": \"老王\",\n" +
            "\t\t\"praise\": 88,\n" +
            "\t\t\"content\": \"看起来不错，不知道实际用了会怎样？看起来不错，不知道实际用了会怎样？看起来不错，不知道实际用了会怎样？\",\n" +
            "\t\t\"time\": \"12-03\",\n" +
            "\t\t\"csList\": [{\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"老赵\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}, {\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"imageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\"name\": \"老王\",\n" +
            "\t\t\"praise\": 88,\n" +
            "\t\t\"content\": \"看起来不错，不知道实际用了会怎样？看起来不错，不知道实际用了会怎样？看起来不错，不知道实际用了会怎样？\",\n" +
            "\t\t\"time\": \"12-03\",\n" +
            "\t\t\"csList\": [{\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"老赵\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}, {\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"imageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\"name\": \"老王\",\n" +
            "\t\t\"praise\": 88,\n" +
            "\t\t\"content\": \"看起来不错，不知道实际用了会怎样？看起来不错，不知道实际用了会怎样？看起来不错，不知道实际用了会怎样？\",\n" +
            "\t\t\"time\": \"12-03\",\n" +
            "\t\t\"csList\": [{\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"老赵\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}, {\n" +
            "\t\t\t\"TImageUrl\": \"http://cdn.haidii.com/v/1519779911/app/swg_xhzd/swg_xhzd_r_1.png\",\n" +
            "\t\t\t\"TName\": \"老王\",\n" +
            "\t\t\t\"TPassiveName\": \"\",\n" +
            "\t\t\t\"TContent\": \"应该是蛮好的，我自己试过了呢～应该是蛮好的，我自己试过了呢～\",\n" +
            "\t\t\t\"TPraise\": 88\n" +
            "\t\t}]\n" +
            "\t}]\n" +
            "}";

    //需求的状态
    public static final int REQUIRE_STATE_WAIT_SELLER = 0;
    public static final int REQUIRE_STATE_WAIT_POINT = 1;
    public static final int REQUIRE_STATE_WAIT_SEND_GOOD = 2;
    public static final int REQUIRE_STATE_WAIT_RECEIVE_GOOD = 3;
    public static final int REQUIRE_STATE_REQUIRE_DONE = 4;
    public static final int REQUIRE_STATE_INVALID = 5;

    public static final String[] REQUIRE_SELLER_STATE_STRINGS = {"待受理", "等待买家指定", "待发货", "已发货", "已完成", "已失效"};
    public static final String[] REQUIRE_STATE_STRINGS = {"等待买手受理", "待指定", "买手已受理", "买手已发货", "已完成", "已失效"};

    //商品类型
    public static final int GOODS_TYPE_DEMAND = 1;//需求型商品
    public static final int GOODS_TYPE_PRESELL = 2;//预售型商品
    public static final int GOODS_TYPE_STOCK = 3;//现货型商品

    public static final String ORDER_ID = "order_id";
    //订单类型
    public static final String ORDER_TYPE = "order_type";
    public static final int ORDER_TYPE_ALL = 0;
    public static final int ORDER_TYPE_AWAIT_PAY = 1;
    public static final int ORDER_TYPE_AWAIT_SHIPPING = 2;//待发货
    public static final int ORDER_TYPE_AWAIT_CONFIRM = 3;//待收货
    public static final int ORDER_TYPE_AWAIT_REVIEW = 4;//待评价

    //订单状态
    public static final int ORDER_STATUS_AWAIT_PAY = 1;
    public static final int ORDER_STATUS_AWAIT_SHIPPING = 2;//买家已付款 待发货
    public static final int ORDER_STATUS_AWAIT_CONFIRM = 3;//买手已发货 待收货
    public static final int ORDER_STATUS_CONFIRMED = 4;//交易成功
    public static final int ORDER_STATUS_OTHER = 5;

    public static final String[] ORDER_STATUS_STRINGS_BUYER = {"", "等待买家付款", "买家已付款",
            "买手已发货", "交易成功", "交易关闭"};

    //******** 背包客模式 ********
//订单状态对应文本
    public static final String[] ORDER_STATUS_STRINGS_PACKER = {"", "等待买家付款", "待发货",
            "已发货", "交易成功", "需求已取消"};

    //******** 买手 ********
//订单状态对应文本
    public static final String[] ORDER_STATUS_STRINGS_SELLER = {"", "等待买家付款", "待发货",
            "已发货", "交易成功", "交易关闭"};

    //订单操作
    public static final int ORDER_OPERATE_VERIFY_VIDEO = 1;
    public static final int ORDER_OPERATE_CONTACT_SELLER = 2;
    public static final int ORDER_OPERATE_CONTACT_BUYER = 3;
    public static final int ORDER_OPERATE_REMIND_SHIPPING = 4;
    public static final int ORDER_OPERATE_VIEW_SHIPPING = 5;
    public static final int ORDER_OPERATE_DELETE = 6;
    public static final int ORDER_OPERATE_CANCEL = 7;
    public static final int ORDER_OPERATE_PAY = 8;
    public static final int ORDER_OPERATE_CONFIRM = 9;
    public static final int ORDER_OPERATE_REVIEW = 10;
    public static final int ORDER_OPERATE_VIEW_REVIEWS = 11;
    public static final int ORDER_OPERATE_CONTACT_SERVICE = 12;
    public static final int ORDER_OPERATE_TO_SHIPPING = 13;//发货
    public static final int ORDER_OPERATE_ = 0;

    //用户类型
    public static final String USER_TYPE = "user_type";
    public static final int USER_TYPE_BUYER = 1;
    public static final int USER_TYPE_BACK_PACKER = 2;
    public static final int USER_TYPE_SELLER = 3;


    /**
     * 客服电话
     */
    public static final String SERVICE_TEL = "020-1234567";
}
