package com.smyy.sharetour.buyer;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

public class NoteDetailsActivity extends BaseMvpActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_note_details;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("笔记详情");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }
}
