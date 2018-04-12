package com.smyy.sharetour.buyer.my.base;

/**
 * Created by justin on 17/9/9.
 */

public interface MyIBaseView {
    void showProgressDialog();

    void hideProgressDialog();

    void finish();

    void showToast(String s);

    void showToast(int stringRes);
}
