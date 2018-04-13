package com.smyy.sharetour.buyer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.adapter.MoreReplyAdapter;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.CommentsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MoreReplyActivity extends BaseMvpActivity {
    public static final String BUNDLE_REPLY_DATA = "MoreReplyActivity.REPLY_DATA";
    @BindView(R.id.rv_more_reply)
    RecyclerView rvMoreReply;
    private CommentsBean.MainList mReplyInfo;
    private MoreReplyAdapter mCommentsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more_reply;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("10条回复");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        if (intent != null) {
            mReplyInfo = (CommentsBean.MainList) intent.getSerializableExtra(BUNDLE_REPLY_DATA);
        }
        if (mReplyInfo == null) {
            finish();
            return;
        }
        Gson gson = new Gson();
        CommentsBean bean = gson.fromJson(Consts.noteDetailsData, CommentsBean.class);
        mReplyInfo = bean.getMainList().get(0);
        rvMoreReply.setHasFixedSize(false);
        rvMoreReply.setNestedScrollingEnabled(false);//解决NestedScrollView+RecyclerView滑动冲突问题
        rvMoreReply.setLayoutManager(new LinearLayoutManager(this));
        List<CommentsBean.MainList> list = new ArrayList<>();
        list.add(mReplyInfo);
        mCommentsAdapter = new MoreReplyAdapter(list);
        rvMoreReply.setAdapter(mCommentsAdapter);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

}
