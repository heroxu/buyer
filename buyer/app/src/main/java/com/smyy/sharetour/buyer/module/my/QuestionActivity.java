package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;

import butterknife.OnClick;

public class QuestionActivity extends MyBaseMvpActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_question;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("帮助与反馈");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
    }

    @OnClick({R.id.tv_my_service, R.id.tv_my_feedback})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_my_service:
                break;

            case R.id.tv_my_feedback:
                startActivity(FeedbackActivity.class);
                break;

            default:
                break;
        }
    }

    @Override
    protected MyBasePresenter createPresenter() {
        return null;
    }
}
