package com.smyy.sharetour.buyer.my.model;

import android.content.Context;

import com.smyy.sharetour.buyer.SPConfig;
import com.smyy.sharetour.buyer.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.my.contract.IUserContract;
import com.smyy.sharetour.buyer.util.SharePreferenceUtil;

/**
 * Created by simt180321 on 2018/4/11.
 */

public class UserModel implements IUserContract.Model {
    @Override
    public UserInfoBean getUserInfo() {
        return new UserInfoBean("悠闲的伪牧师", "一只大榴莲，两梳大香蕉。", "",
                0, 0, 0, 0);
    }

    @Override
    public UserInfoBean getUserInfoCache(Context application) {
        return new SharePreferenceUtil(application, SPConfig.USER_CACHE)
                .getBeanValue(SPConfig.USER_INFO, UserInfoBean.class);
    }

    @Override
    public boolean saveUserInfo(Context application, UserInfoBean userInfo) {
        return new SharePreferenceUtil(application, SPConfig.USER_CACHE)
                .writeBeanValue(SPConfig.USER_INFO, userInfo);
    }

    @Override
    public boolean setUserName(Context application, String userName) {
        UserInfoBean userInfo = getUserInfoCache(application);
        userInfo.setUsername(userName);
        return new SharePreferenceUtil(application, SPConfig.USER_CACHE)
                .writeBeanValue(SPConfig.USER_INFO, userInfo);
    }

    @Override
    public boolean setUserIntro(Context application, String userIntro) {
        UserInfoBean userInfo = getUserInfoCache(application);
        userInfo.setUserIntro(userIntro);
        return new SharePreferenceUtil(application, SPConfig.USER_CACHE)
                .writeBeanValue(SPConfig.USER_INFO, userInfo);
    }
}
