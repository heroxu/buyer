package com.smyy.sharetour.buyer.module.common.code.model;

import android.text.TextUtils;

import com.smyy.sharetour.buyer.module.common.code.contract.ISmsCodeContract;
import com.smyy.sharetour.buyer.module.my.model.UserModel;

public class SmsCodeModel implements ISmsCodeContract.Model {

    private String mPhoneNum;

    @Override
    public boolean getSmsCode(String internationalCode, String phoneNum) {
        this.mPhoneNum = phoneNum;
        if (TextUtils.isEmpty(phoneNum) || phoneNum.length() < 4) {
            return false;
        }
        return true;
    }

    @Override
    public boolean verifySmsCode(String smsCode) {
        if (TextUtils.equals(smsCode, "123456")) {
            UserModel userModel = new UserModel();
            userModel.setLinkedPhone(mPhoneNum);
            return true;
        }
        return false;
    }
}
