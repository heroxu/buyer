package com.smyy.sharetour.buyer.module.my.contract;

import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.base.MyIBaseView;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;

public interface IUserContract {
    interface View extends MyIBaseView {
        void showUserInfo(UserInfoBean userInfo);
    }

    abstract class Presenter extends MyBasePresenter<View, Model> {
        private String avatar;

        public Presenter(View view, Model model) {
            super(view, model);
        }

        /**
         * 从网络获取用户信息
         */
        public abstract void getUserInfoFromNet();

        /**
         * 从缓存获取用户信息
         */
        public abstract void getUserInfo();

        public abstract void setUserName(String userName);

        public abstract void setUserIntro(String userIntro);

        public abstract void setAvatar(String avatar);
    }

    interface Model {
        /**
         * 从网络获取用户信息
         */
        UserInfoBean getUserInfoFromNet();

        /**
         * 从缓存获取用户信息
         */
        UserInfoBean getUserInfo();

        /**
         * 将用户信息缓存到本地
         */
        boolean saveUserInfo(UserInfoBean userInfo);

        boolean setUserName(String userName);

        boolean setUserIntro(String userIntro);

        boolean setLinkedPhone(String linkedPhoneNum);

        boolean setUserAvatar(String avatar);
    }
}
