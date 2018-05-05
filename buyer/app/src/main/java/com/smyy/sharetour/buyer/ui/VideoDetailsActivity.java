package com.smyy.sharetour.buyer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.CommentsAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.CommentsBean;
import com.smyy.sharetour.buyer.view.ExpandTextView;
import com.smyy.sharetour.buyer.view.ObservableScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoDetailsActivity extends BaseMvpActivity implements ObservableScrollView.OnObservableScrollViewScrollChanged {
    @BindView(R.id.etv_video_content)
    ExpandTextView etvVideoContent;
    //评论吸顶相关
    @BindView(R.id.ll_comments)
    LinearLayout llComments;
    @BindView(R.id.ll_fixedView)
    LinearLayout llFixedView;
    @BindView(R.id.ll_ll_comments)
    LinearLayout llLlComments;
    @BindView(R.id.rv_video_comments)
    RecyclerView rvVideoComments;
    @BindView(R.id.sc_video_details)
    ObservableScrollView scVideoDetails;
    CommentsAdapter mCommentsAdapter;
    //用来记录内层固定布局到屏幕顶部的距离
    private int mHeight;
    private boolean isShowText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_details;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        hideToolBarLayout(true);
        String content = "Serializable和Parcelable接口可以完成对象的序列化过程，当我们需要通过Intent和Binder传输数据时就需要使用者两种序列化方式。还有，我们需要对象持久化到存储设备或者通过网络传输给其他客户端，这个使用也需要使用Serializale来完成对象的序列化。在Android应用开发中，这两种方式都很常见，但两者方式并不相同。";
        if (!TextUtils.isEmpty(content)) {
            etvVideoContent.setExpand(isShowText);
            etvVideoContent.setExpandStatusListener(new ExpandTextView.ExpandStatusListener() {
                @Override
                public void statusChange(boolean isExpand) {
                    isShowText = !isExpand;
                }
            });
            etvVideoContent.setText(content);
        }
        etvVideoContent.setVisibility(TextUtils.isEmpty(content) ? View.GONE : View.VISIBLE);
        //评论相关
        scVideoDetails.setOnObservableScrollViewScrollChanged(this);
        rvVideoComments.setHasFixedSize(false);
        rvVideoComments.setNestedScrollingEnabled(false);//解决NestedScrollView+RecyclerView滑动冲突问题
        rvVideoComments.setLayoutManager(new LinearLayoutManager(this));
        Gson gson = new Gson();
        CommentsBean bean = gson.fromJson(Consts.noteDetailsData, CommentsBean.class);
        mCommentsAdapter = new CommentsAdapter(bean.getMainList());
        rvVideoComments.setAdapter(mCommentsAdapter);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            //获取HeaderView的高度，当滑动大于等于这个高度的时候，需要把topView移除当前布局，放入到外层布局
            mHeight = llComments.getTop();
        }
    }

    @Override
    public void onObservableScrollViewScrollChanged(int l, int t, int oldl, int oldt) {
        if (t >= mHeight) {
            if (llLlComments.getParent() != llFixedView) {
                llComments.removeView(llLlComments);
                llFixedView.addView(llLlComments);
            }
        } else {
            if (llLlComments.getParent() != llComments) {
                llFixedView.removeView(llLlComments);
                llComments.addView(llLlComments);
            }
        }
    }


    @OnClick(R.id.iv_close)
    public void onViewClicked() {
        finish();
    }
}
