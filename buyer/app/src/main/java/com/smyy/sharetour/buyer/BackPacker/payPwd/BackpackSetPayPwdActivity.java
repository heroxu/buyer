package com.smyy.sharetour.buyer.BackPacker.payPwd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.smyy.sharetour.buyer.view.PasswordEditText;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class BackpackSetPayPwdActivity extends BaseMvpActivity {
    @BindView(R.id.pe_password)
    PasswordEditText pePassword;
    private String pwd1;
    private String pwd2;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpack_set_pay_pwd;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("设置支付密码");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        pePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @OnClick(R.id.btn_finish)
    public void onViewClicked() {

    }
}
