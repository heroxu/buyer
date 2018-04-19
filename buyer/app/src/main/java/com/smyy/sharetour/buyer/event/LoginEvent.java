package com.smyy.sharetour.buyer.event;

/**
 * Created by hasee on 2018/4/19.
 */

public class LoginEvent {
    private boolean isLogin;//true表示登录，false表示登出

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public LoginEvent(boolean isLogin) {
        this.isLogin = isLogin;
    }
}
