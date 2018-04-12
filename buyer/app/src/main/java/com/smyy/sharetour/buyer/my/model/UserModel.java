package com.smyy.sharetour.buyer.my.model;

import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.SPConfig;
import com.smyy.sharetour.buyer.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.my.contract.IUserContract;
import com.smyy.sharetour.buyer.util.SharePreferenceUtil;

public class UserModel implements IUserContract.Model {
    @Override
    public UserInfoBean getUserInfo() {
        UserInfoBean userInfo = new UserInfoBean("悠闲的伪牧师", "一只大榴莲，两梳大香蕉。", "",
                0, 0, 0, 0);
        saveUserInfo(userInfo);
        return userInfo;
    }

    @Override
    public UserInfoBean getUserInfoCache() {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return null;
        UserInfoBean userInfo = application.getUserInfo();
        if (userInfo == null) {
            userInfo = new SharePreferenceUtil(application, SPConfig.USER_CACHE)
                    .getBeanValue(SPConfig.USER_INFO, UserInfoBean.class);
            if (userInfo == null) {
                userInfo = getUserInfo();
            }
        }
        return userInfo;
    }

    @Override
    public boolean saveUserInfo(UserInfoBean userInfo) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;
        application.setUserInfo(userInfo);
        return new SharePreferenceUtil(application, SPConfig.USER_CACHE)
                .writeBeanValue(SPConfig.USER_INFO, userInfo);
    }

    @Override
    public boolean setUserName(String userName) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;
        UserInfoBean userInfo = getUserInfoCache();
        userInfo.setUsername(userName);
        application.setUserInfo(userInfo);
        return new SharePreferenceUtil(application, SPConfig.USER_CACHE)
                .writeBeanValue(SPConfig.USER_INFO, userInfo);
    }

    @Override
    public boolean setUserIntro(String userIntro) {
        MyApplication application = MyApplication.getApplication();
        if (application == null) return false;
        UserInfoBean userInfo = getUserInfoCache();
        userInfo.setUserIntro(userIntro);
        application.setUserInfo(userInfo);
        return new SharePreferenceUtil(application, SPConfig.USER_CACHE)
                .writeBeanValue(SPConfig.USER_INFO, userInfo);
    }
}
