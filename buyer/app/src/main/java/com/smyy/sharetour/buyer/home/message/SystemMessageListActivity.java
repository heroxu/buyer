package com.smyy.sharetour.buyer.home.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.model.SystemMessageList;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemMessageListActivity extends BaseMvpActivity {


    @BindView(R.id.rv_system_message)
    RecyclerView rvSystemMessage;
    private SystemMessageListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_message_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("系统消息");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        rvSystemMessage.setLayoutManager(new LinearLayoutManager(this));
        final List<SystemMessageList> data = new ArrayList<>();
        data.add(new SystemMessageList());
        data.add(new SystemMessageList());
        data.add(new SystemMessageList());
        mAdapter = new SystemMessageListAdapter(data);
        rvSystemMessage.setAdapter(mAdapter);
        //添加自定义分割线
//        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_custom_divider_10));
//        recyclerView.addItemDecoration(divider);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

//    @OnClick({R.id.btn_settlement})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_settlement:
//                ActivityLauncher.viewPayFinishActivity(SystemMessageListActivity.this);
//                break;
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
