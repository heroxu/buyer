package com.smyy.sharetour.buyer.BackPacker.my;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.module.my.QuestionActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MyWalletActivity extends BaseMvpActivity {
    @BindView(R.id.lay_my_remind)
    View layRemind;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        hideToolBarLayout(true);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }

    @OnClick({R.id.iv_close, R.id.iv_my_ques, R.id.iv_my_close_remind, R.id.tv_my_withdraw})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_close:
                finish();
                break;

            case R.id.iv_my_ques:
                startActivity(QuestionActivity.class);
                break;

            case R.id.iv_my_close_remind:
                layRemind.setVisibility(View.GONE);
                break;

            case R.id.tv_my_withdraw:
                startActivity(new Intent(MyWalletActivity.this, BackpackerWithdrawActivity.class));
                break;

            default:
                break;
        }
    }

    @Override
    protected void initStatusBar() {
        setStatusBar(Color.BLACK);
    }
}
