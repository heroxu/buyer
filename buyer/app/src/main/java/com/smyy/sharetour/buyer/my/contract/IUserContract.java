package com.smyy.sharetour.buyer.my.contract;

import android.content.Context;

import com.smyy.sharetour.buyer.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.my.base.MyIBaseView;
import com.smyy.sharetour.buyer.my.bean.UserInfoBean;

public interface IUserContract {
    interface View extends MyIBaseView {
        void showUserInfo(UserInfoBean userInfo);

        void showDialog(String s);
    }

    abstract class Presenter extends MyBasePresenter<View, Model> {
        public Presenter(View view, Model model) {
            super(view, model);
        }

        /**
         * 从网络获取用户信息
         */
        public abstract void getUserInfo(Context application);

        /**
         * 从缓存获取用户信息
         */
        public abstract void getUserInfoCache(Context application);

        public abstract void setUserName(Context application, String userName);

        public abstract void setUserIntro(Context application, String userIntro);
    }

    interface Model {
        /**
         * 从网络获取用户信息
         */
        UserInfoBean getUserInfo();

        /**
         * 从缓存获取用户信息
         */
        UserInfoBean getUserInfoCache(Context application);

        /**
         * 将用户信息缓存到本地
         *
         * @param application
         * @param userInfo
         */
        boolean saveUserInfo(Context application, UserInfoBean userInfo);

        boolean setUserName(Context application, String userName);

        boolean setUserIntro(Context application, String userIntro);
    }
}
