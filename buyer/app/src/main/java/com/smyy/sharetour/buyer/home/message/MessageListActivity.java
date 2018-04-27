package com.smyy.sharetour.buyer.home.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.model.MessageList;
import com.smyy.sharetour.buyer.tim.ChatActivity;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.ToastUtils;
import com.tencent.imsdk.TIMConversationType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageListActivity extends BaseMvpActivity {


    @BindView(R.id.ll_message_system_message)
    LinearLayout llMessageSystemMessage;
    @BindView(R.id.ll_message_my_comment)
    LinearLayout llMessageMyComment;
    @BindView(R.id.ll_message_my_like)
    LinearLayout llMessageMyLike;
    @BindView(R.id.rv_message)
    RecyclerView rvMessage;
    private MessageListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("消息");
        getToolbarRightTv().setVisibility(View.VISIBLE);
        getToolbarRightTv().setText(R.string.settings);
        getToolbarRightTv().setTextColor(R.color.txt_main);
        getToolbarRightTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityLauncher.viewAccountSettingsActivity(MessageListActivity.this);
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
        final List<MessageList> data = new ArrayList<>();
        data.add(new MessageList());
        data.add(new MessageList());
        data.add(new MessageList());
        mAdapter = new MessageListAdapter(data);
        rvMessage.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ChatActivity.navToChat(MessageListActivity.this, "刘德华",TIMConversationType.C2C);
            }
        });
        //添加自定义分割线
//        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_custom_divider_10));
//        recyclerView.addItemDecoration(divider);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.ll_message_system_message,R.id.ll_message_my_comment,R.id.ll_message_my_like})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_message_system_message:
                    ActivityLauncher.viewSystemMessageListActivity(MessageListActivity.this);
                break;
            case R.id.ll_message_my_comment:
                ToastUtils.showToast("ll_message_my_comment").show();
                break;
            case R.id.ll_message_my_like:
                ToastUtils.showToast("ll_message_my_like").show();
                break;
        }
    }
}
