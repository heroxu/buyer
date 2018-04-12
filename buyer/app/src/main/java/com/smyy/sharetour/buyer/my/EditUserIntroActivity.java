package com.smyy.sharetour.buyer.my;

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

import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.my.contract.IUserContract;
import com.smyy.sharetour.buyer.my.model.UserModel;
import com.smyy.sharetour.buyer.my.presenter.UserPresenter;
import com.smyy.sharetour.buyer.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class EditUserIntroActivity extends MyBaseMvpActivity<UserPresenter> implements IUserContract.View {
    @BindView(R.id.et_my_user_intro)
    EditText etUserIntro;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    private UserInfoBean mUserInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_edit_user_intro;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(getString(R.string.user_intro));
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        mPresenter.getUserInfoCache();

        setListener();
    }

    private void setListener() {
        etUserIntro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(s.toString().trim())) {
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
                mPresenter.setUserIntro(etUserIntro.getText().toString().trim());
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
        String userIntro = userInfo.getUserIntro();
        if (!TextUtils.isEmpty(userIntro)) {
            etUserIntro.setText(userIntro.trim());
        }
    }

    @Override
    public void showDialog(String s) {
        ToastUtils.showToast(s);
    }
}
