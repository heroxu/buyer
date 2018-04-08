package com.smyy.sharetour.buyer.util;

import android.content.Context;
import android.content.Intent;

import com.smyy.sharetour.buyer.ui.Test1Activity;
import com.smyy.sharetour.buyer.ui.test2.Test2Activity;

/**
 * Created by 伍振飞 on 2018/3/15 18:06
 * E-Mail Address：wuzf2012@sina.com
 */
public class ActivityLauncher {
    public static void viewTest1Activity(Context context) {
        Intent intent = new Intent(context, Test1Activity.class);
        context.startActivity(intent);
    }

    public static void viewTest2Activity(Context context) {
        Intent intent = new Intent(context, Test2Activity.class);
        context.startActivity(intent);
    }

    public static void viewActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void viewGuideLoginActivity(Context context) {
        Intent intent = new Intent(context, GuideLoginActivity.class);
        context.startActivity(intent);
    }

    public static void viewLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void viewRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public static void viewSetPwdActivity(Context context) {
        Intent intent = new Intent(context, SetPwdActivity.class);
        context.startActivity(intent);
    }

    public static void viewPwdLoginActivity(Context context) {
        Intent intent = new Intent(context, PwdLoginActivity.class);
        context.startActivity(intent);
    }
}
