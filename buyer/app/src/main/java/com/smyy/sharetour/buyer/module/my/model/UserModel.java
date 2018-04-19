package com.smyy.sharetour.buyer.module.my.model;

import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.SPConfig;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.module.my.contract.IUserContract;
import com.smyy.sharetour.buyer.util.SharePreferenceUtil;

public class UserModel implements IUserContract.Model {
    @Override
    public UserInfoBean getUserInfoFromNet() {
        UserInfoBean userInfo = new UserInfoBean("", "悠闲的伪牧师", "一只大榴莲，两梳大香蕉。", "",
                1, 2, 8, 0);
        saveUserInfo(userInfo);
        return userInfo;
    }

    @Override
    public UserInfoBean getUserInfo() {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return null;
        UserInfoBean userInfo = application.getUserInfo();
        if (userInfo == null) {
            userInfo = new SharePreferenceUtil(application, SPConfig.NAME_USER_CACHE)
                    .getBeanValue(SPConfig.KEY_USER_INFO, UserInfoBean.class);
            if (userInfo == null) {
                userInfo = getUserInfoFromNet();
            }
        }
        return userInfo;
    }

    @Override
    public boolean saveUserInfo(UserInfoBean userInfo) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;
        application.setUserInfo(userInfo);
        return new SharePreferenceUtil(application, SPConfig.NAME_USER_CACHE)
                .writeBeanValue(SPConfig.KEY_USER_INFO, userInfo);
    }

    @Override
    public boolean setUserName(String userName) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;
        UserInfoBean userInfo = getUserInfo();
        userInfo.setUsername(userName);
        application.setUserInfo(userInfo);
        return new SharePreferenceUtil(application, SPConfig.NAME_USER_CACHE)
                .writeBeanValue(SPConfig.KEY_USER_INFO, userInfo);
    }

    @Override
    public boolean setUserIntro(String userIntro) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;
        UserInfoBean userInfo = getUserInfo();
        userInfo.setUserIntro(userIntro);
        application.setUserInfo(userInfo);
        return new SharePreferenceUtil(application, SPConfig.NAME_USER_CACHE)
                .writeBeanValue(SPConfig.KEY_USER_INFO, userInfo);
    }

    @Override
    public boolean setLinkedPhone(String linkedPhoneNum) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;
        UserInfoBean userInfo = getUserInfo();
        userInfo.setLinkedPhoneNum(linkedPhoneNum);
        application.setUserInfo(userInfo);
        return new SharePreferenceUtil(application, SPConfig.NAME_USER_CACHE)
                .writeBeanValue(SPConfig.KEY_USER_INFO, userInfo);
    }
}
