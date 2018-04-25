package com.smyy.sharetour.buyer.BackPacker.payPwd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.dialog.SmsCodeDialog;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.ToastUtils;

import butterknife.OnClick;

public class BackpackValidationPhoneActivity extends BaseMvpActivity {
    SmsCodeDialog mSmsCodeDialog;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpack_validation_phone;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("验证");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @OnClick(R.id.validation_phone)
    public void onViewClicked() {
        showmCodeDialog();
    }

    /**
     * 弹出验证码对话框
     */
    private void showmCodeDialog() {
        if (mSmsCodeDialog == null) {
            mSmsCodeDialog = new SmsCodeDialog(this);
        }
        if (mSmsCodeDialog.isShowing()) {
            return;
        }
        mSmsCodeDialog.setClickCallbackListener(new SmsCodeDialog.SmsCodeCallback() {
            @Override
            public void SmsCodeResult(String smsCode) {
                if (Consts.DEFAULT_SMS_CODE.equals(smsCode)) {
                    ActivityLauncher.viewBackpackSetPayPwdActivity(BackpackValidationPhoneActivity.this);
                    ToastUtils.showToast(BackpackValidationPhoneActivity.this, "验证成功");
                    finish();
                } else {
                    ToastUtils.showToast(BackpackValidationPhoneActivity.this, "验证失败，请检查手机和验证码是否正确");
                }
            }

            @Override
            public void SmsCodeCancel() {

            }
        });
        mSmsCodeDialog.show();
    }
}
