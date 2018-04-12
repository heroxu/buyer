package com.smyy.sharetour.buyer.my.contract;

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
        public abstract void getUserInfo();

        /**
         * 从缓存获取用户信息
         */
        public abstract void getUserInfoCache();

        public abstract void setUserName(String userName);

        public abstract void setUserIntro(String userIntro);
    }

    interface Model {
        /**
         * 从网络获取用户信息
         */
        UserInfoBean getUserInfo();

        /**
         * 从缓存获取用户信息
         */
        UserInfoBean getUserInfoCache();

        /**
         * 将用户信息缓存到本地
         */
        boolean saveUserInfo(UserInfoBean userInfo);

        boolean setUserName(String userName);

        boolean setUserIntro(String userIntro);
    }
}
