package com.smyy.sharetour.buyer.backpacker.bomepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BackpackCommentsActivity extends BaseMvpActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpack_comments;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("全部评价");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<BackpackCommentsBean> data = new ArrayList<>();
        data.add(new BackpackCommentsBean());
        data.add(new BackpackCommentsBean());
        data.add(new BackpackCommentsBean());
        data.add(new BackpackCommentsBean());
        recyclerView.setAdapter(new BackpackCommentsAdapter(data));
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

}
