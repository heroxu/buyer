package com.smyy.sharetour.buyer.ui.test2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.ToastUtils;
import com.smyy.sharetour.buyer.adapter.Test2Adapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.util.LogUtil;

import butterknife.BindView;

public class Test2Activity extends BaseMvpActivity<Test2Presenter> implements ITest2Contract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test2;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("测试");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        showProgressDialog();
        mPresenter.getData();
    }


    @Override
    protected Test2Presenter createPresenter() {
        return new Test2Presenter(this);
    }

    @Override
    public void getDataSuccess(Test2JsonData data) {
        hideProgressDialog();
        if (data != null) {
            LogUtil.d("WZF",data.toString());
            recyclerView.setAdapter(new Test2Adapter(data.getResult()));
        }else {
            finish();
        }

    }

    @Override
    public void getDataFail(String message) {
        hideProgressDialog();
        ToastUtils.showToast(message);
    }
}
