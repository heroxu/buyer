package com.smyy.sharetour.buyer.util;

import android.content.Context;
import android.content.Intent;

import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.bean.CommentsBean;
import com.smyy.sharetour.buyer.home.detail.HomeDetailActivity;
import com.smyy.sharetour.buyer.home.search.activity.HomeSearchActivity;
import com.smyy.sharetour.buyer.home.search.activity.SearchDetailActivity;
import com.smyy.sharetour.buyer.publish.PublishRequireActivity;
import com.smyy.sharetour.buyer.publish.RequireListActivity;
import com.smyy.sharetour.buyer.ui.GuideLoginActivity;
import com.smyy.sharetour.buyer.ui.LoginActivity;
import com.smyy.sharetour.buyer.ui.MoreReplyActivity;
import com.smyy.sharetour.buyer.ui.MyCollectionActivity;
import com.smyy.sharetour.buyer.ui.NoteDetailsActivity;
import com.smyy.sharetour.buyer.ui.PersonalTagActivity;
import com.smyy.sharetour.buyer.ui.PwdLoginActivity;
import com.smyy.sharetour.buyer.ui.RegisterActivity;
import com.smyy.sharetour.buyer.ui.SelectAreaCodeActivity;
import com.smyy.sharetour.buyer.ui.SetPwdActivity;
import com.smyy.sharetour.buyer.ui.Test1Activity;
import com.smyy.sharetour.buyer.ui.VerifyPhoneActivity;
import com.smyy.sharetour.buyer.ui.VideoDetailsActivity;
import com.smyy.sharetour.buyer.ui.buyCommodity.BuyHomePageActivity;
import com.smyy.sharetour.buyer.ui.buyCommodity.RecommendBuyActivity;
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

    /**
     * 跳转到登录引导
     */
    public static void viewGuideLoginActivity(Context context) {
        Intent intent = new Intent(context, GuideLoginActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到登录
     */
    public static void viewLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到注册
     */
    public static void viewRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到设置密码
     */
    public static void viewSetPwdActivity(Context context) {
        Intent intent = new Intent(context, SetPwdActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到密码登录
     */
    public static void viewPwdLoginActivity(Context context) {
        Intent intent = new Intent(context, PwdLoginActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到验证手机号找回密码
     */
    public static void viewVerifyPhoneActivity(Context context) {
        Intent intent = new Intent(context, VerifyPhoneActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到选择区号
     */
    public static void viewSelectAreaCodeActivity(Context context) {
        Intent intent = new Intent(context, SelectAreaCodeActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到笔记详情
     */
    public static void viewNoteDetailsActivity(Context context) {
        if (isLogin(context)){
            Intent intent = new Intent(context, NoteDetailsActivity.class);
            context.startActivity(intent);
        }
    }
    /**
     * 跳转到视频详情
     */
    public static void viewVideoDetailsActivity(Context context) {
        if (isLogin(context)){
            Intent intent = new Intent(context, VideoDetailsActivity.class);
            context.startActivity(intent);
        }
    }
    /**
     * 跳转到更多回复
     */
    public static void viewMoreReplyActivityty(Context context, CommentsBean.MainList data) {
        Intent intent = new Intent(context, MoreReplyActivity.class);
        intent.putExtra(MoreReplyActivity.BUNDLE_REPLY_DATA,data);
        context.startActivity(intent);
    }
    /**
     * 跳转到收藏列表
     */
    public static void viewMyCollectionActivity(Context context) {
        Intent intent = new Intent(context, MyCollectionActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到推荐买手
     */
    public static void viewRecommendBuyActivity(Context context) {
        Intent intent = new Intent(context,RecommendBuyActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到买手主页
     */
    public static void viewBuyHomePageActivity(Context context) {
        Intent intent = new Intent(context,BuyHomePageActivity.class);
        context.startActivity(intent);
    }

    public static void viewHomeDetail(Context context) {
        Intent intent = new Intent(context, HomeDetailActivity.class);
        context.startActivity(intent);
    }

    public static void viewHomeSearch(Context context) {
        Intent intent = new Intent(context, HomeSearchActivity.class);
        context.startActivity(intent);
    }

    public static void viewSearchDetail(Context context) {
        Intent intent = new Intent(context, SearchDetailActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到个人标签选择页面
     */
    public static void viewPersonalTagActivity(Context context) {
        Intent intent = new Intent(context, PersonalTagActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到发布需求页面
     */
    public static void viewPublishRequireActivity(Context context) {
        Intent intent = new Intent(context, PublishRequireActivity.class);
        context.startActivity(intent);
    }

    private static boolean isLogin(Context context){
        if (MyApplication.getApplication().isLogin()){
            return true;
        }
        viewGuideLoginActivity(context);
        return false;
    }

    /**
     * 跳转到需求列表页面
     */
    public static void viewMyRequireActivity(Context context) {
        Intent intent = new Intent(context, RequireListActivity.class);
        context.startActivity(intent);
    }
}

