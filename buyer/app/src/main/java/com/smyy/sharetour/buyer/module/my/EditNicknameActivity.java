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

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.module.my.contract.IUserContract;
import com.smyy.sharetour.buyer.module.my.model.UserModel;
import com.smyy.sharetour.buyer.module.my.presenter.UserPresenter;
import com.smyy.sharetour.buyer.util.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class EditNicknameActivity extends MyBaseMvpActivity<UserPresenter> implements IUserContract.View {
    @BindView(R.id.et_my_nickname)
    EditText etNickname;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private String mNickname;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_edit_nickname;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(getString(R.string.nickname));
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        mPresenter.getUserInfo();

        setListener();
    }

    private void setListener() {
        etNickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNickname = StringUtil.trim(etNickname);
                if (!TextUtils.isEmpty(mNickname) && StringUtil.checkNickname(mNickname)) {
                    btnConfirm.setEnabled(true);
                } else {
                    btnConfirm.setEnabled(false);
                }
            }
        });
    }


    @OnClick(R.id.btn_confirm)
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_confirm:
                mPresenter.setUserName(mNickname);
                break;

            default:
                break;
        }
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this, new UserModel());
    }

    @Override
    public void showUserInfo(UserInfoBean userInfo) {
        String username = userInfo.getUsername();
        if (!TextUtils.isEmpty(username)) {
            etNickname.setText(username.trim());
        }
    }
}
