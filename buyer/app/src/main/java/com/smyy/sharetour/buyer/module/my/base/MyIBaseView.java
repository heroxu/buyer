package com.smyy.sharetour.buyer.module.my.base;

/**
 * Created by justin on 17/9/9.
 */

public interface MyIBaseView {
    void showProgressDialog();
    void showProgressDialog(String msg);

    void hideProgressDialog();

    void finish();

    void showToast(String s);

    void showToast(int stringRes);
}
