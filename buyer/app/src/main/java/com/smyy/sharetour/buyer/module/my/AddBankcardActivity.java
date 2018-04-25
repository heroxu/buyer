package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.dialog.SmsCodeDialog;
import com.smyy.sharetour.buyer.util.StringUtil;
import com.smyy.sharetour.buyer.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class AddBankcardActivity extends BaseMvpActivity {
    @BindView(R.id.et_my_bankcard_name)
    EditText etName;
    @BindView(R.id.et_my_bankcard_num)
    EditText etNum;
    @BindView(R.id.et_my_bankcard_phone)
    EditText etPhone;

    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    SmsCodeDialog mSmsCodeDialog;

    private String mName;
    private String mNum;
    private String mPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_add_bankcard;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("添加银行卡");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

        setListener();
    }

    private void setListener() {
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInfo();
            }
        });
        etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInfo();
            }
        });
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInfo();
            }
        });
    }

    private void checkInfo() {
        mName = StringUtil.trim(etName);
        mNum = StringUtil.trim(etNum);
        mPhone = StringUtil.trim(etPhone);
        if (!TextUtils.isEmpty(mName) && !TextUtils.isEmpty(mNum) && !TextUtils.isEmpty(mPhone)) {
            btnConfirm.setEnabled(true);
        } else {
            btnConfirm.setEnabled(false);
        }
    }


    @OnClick(R.id.btn_confirm)
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_confirm:
                showmCodeDialog();
                break;

            default:
                break;
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
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
                    setResult(RESULT_OK);
                    finish();
                } else {
                    ToastUtils.showToast(AddBankcardActivity.this, "验证码输入错误");
                }
            }

            @Override
            public void SmsCodeCancel() {

            }
        });
        mSmsCodeDialog.show();
    }
}
