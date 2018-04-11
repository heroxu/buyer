package com.smyy.sharetour.buyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.CommentsBean;
import com.smyy.sharetour.buyer.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class NoteDetailsActivity extends BaseMvpActivity {

    @BindView(R.id.iv_dea_photo)
    CircleImageView ivDeaPhoto;
    @BindView(R.id.iv_dea_name)
    TextView ivDeaName;
    @BindView(R.id.rv_note_comments)
    RecyclerView rvNoteComments;
    CommentsAdapter mCommentsAdapter;

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
        rvNoteComments.setHasFixedSize(false);
        rvNoteComments.setNestedScrollingEnabled(false);//解决NestedScrollView+RecyclerView滑动冲突问题
        rvNoteComments.setLayoutManager(new LinearLayoutManager(this));
        Gson gson = new Gson();
        CommentsBean bean = gson.fromJson(Consts.noteDetailsData, CommentsBean.class);
        mCommentsAdapter = new CommentsAdapter(bean.getMainList());
        View headerView = getHeaderView();
        mCommentsAdapter.addHeaderView(headerView);
        rvNoteComments.setAdapter(mCommentsAdapter);

    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    public View getHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.title_comments, (ViewGroup) rvNoteComments.getParent(), false);
        return view;
    }
}
