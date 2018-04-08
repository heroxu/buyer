package com.smyy.sharetour.buyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class SetPwdActivity extends BaseMvpActivity {

    @BindView(R.id.tv_module_name)
    TextView tvModuleName;
    @BindView(R.id.cwet_pwd)
    ClearWriteEditText cwetPwd;
    @BindView(R.id.cwet_again_pwd)
    ClearWriteEditText cwetAgainPwd;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_pwd;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        tvModuleName.setText("重置密码");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        hideToolBarLayout(true);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.iv_close, R.id.reg_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.reg_button:
                ToastUtils.showToast(SetPwdActivity.this,tvModuleName.getText().toString().trim()+"成功");
                finish();
                break;
        }
    }
}
