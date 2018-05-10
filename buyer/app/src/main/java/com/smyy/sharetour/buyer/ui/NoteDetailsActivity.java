package com.smyy.sharetour.buyer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.CommentsAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.CommentsBean;
import com.smyy.sharetour.buyer.dialog.DetailsEditorDialog;
import com.smyy.sharetour.buyer.emotion.EmotionViewUtil;
import com.smyy.sharetour.buyer.util.KeyBoardUtils;
import com.smyy.sharetour.buyer.view.ObservableScrollView;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class NoteDetailsActivity extends BaseMvpActivity implements ObservableScrollView.OnObservableScrollViewScrollChanged {
    @BindView(R.id.iv_dea_photo)
    CircleImageView ivDeaPhoto;
    @BindView(R.id.iv_dea_name)
    TextView ivDeaName;
    @BindView(R.id.rv_note_comments)
    RecyclerView rvNoteComments;
    CommentsAdapter mCommentsAdapter;
    //评论吸顶相关
    @BindView(R.id.ll_comments)
    LinearLayout llComments;
    @BindView(R.id.sc_node_details)
    ObservableScrollView scNodeDetails;
    @BindView(R.id.ll_fixedView)
    LinearLayout llFixedView;
    @BindView(R.id.ll_ll_comments)
    LinearLayout llLlComments;
    @BindView(R.id.ll_nd_bottom)
    LinearLayout llNdBottom;
    @BindView(R.id.ll_comment_input_frame)
    LinearLayout commentInputFrame;
    @BindView(R.id.et_input_comment)
    EditText inputComment;
    @BindView(R.id.chat_face_container)
    LinearLayout chatFaceContainer;

    //用来记录内层固定布局到屏幕顶部的距离
    private int mHeight;
    private DetailsEditorDialog mDetailsEditorDialog;
    private EmotionViewUtil emotionViewUtil;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_note_details;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("笔记详情");
        ImageView mImageView = (ImageView) toolbar.findViewById(R.id.iv_right);
        mImageView.setVisibility(View.VISIBLE);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmDetailsEditorDialog();
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        scNodeDetails.setOnObservableScrollViewScrollChanged(this);
        rvNoteComments.setHasFixedSize(false);
        rvNoteComments.setNestedScrollingEnabled(false);//解决NestedScrollView+RecyclerView滑动冲突问题
        rvNoteComments.setLayoutManager(new LinearLayoutManager(this));
        Gson gson = new Gson();
        CommentsBean bean = gson.fromJson(Consts.noteDetailsData, CommentsBean.class);
        mCommentsAdapter = new CommentsAdapter(bean.getMainList());
        rvNoteComments.setAdapter(mCommentsAdapter);
        mCommentsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_reply_comments:
                        //ToastUtils.showToast("回复被点击了");
                        showCommentInputFrame(true);
                        break;
                }
            }
        });

        emotionViewUtil = new EmotionViewUtil(NoteDetailsActivity.this, inputComment, chatFaceContainer);
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
        showCommentInputFrame(false);
    }

    @Override
    protected void onDestroy() {
        if (mDetailsEditorDialog != null) {
            mDetailsEditorDialog.dismiss();
            mDetailsEditorDialog.destroy();
        }
        super.onDestroy();
    }

    /**
     * 弹出验证码对话框
     */
    private void showmDetailsEditorDialog() {
        if (mDetailsEditorDialog == null) {
            mDetailsEditorDialog = new DetailsEditorDialog(this);
        }
        if (mDetailsEditorDialog.isShowing()) {
            return;
        }
        mDetailsEditorDialog.setClickCallbackListener(new DetailsEditorDialog.ItemClickCallbackListener() {
            @Override
            public void fromShare(DetailsEditorDialog dialog) {

            }

            @Override
            public void fromEditor(DetailsEditorDialog dialog) {

            }

            @Override
            public void fromDelete(DetailsEditorDialog dialog) {

            }

            @Override
            public void fromCancel(DetailsEditorDialog dialog) {

            }
        });
        mDetailsEditorDialog.show();
    }

    private void showCommentInputFrame(boolean isShow) {
        if (isShow) {
            commentInputFrame.setVisibility(View.VISIBLE);
            llNdBottom.setVisibility(View.GONE);
            inputComment.requestFocus();
            KeyBoardUtils.openKeybord(inputComment, NoteDetailsActivity.this);

        } else {
            //关闭软键盘
            KeyBoardUtils.closeKeybord(inputComment, NoteDetailsActivity.this);
            commentInputFrame.setVisibility(View.GONE);
            llNdBottom.setVisibility(View.VISIBLE);
            chatFaceContainer.setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.iv_emotion, R.id.btn_publish_comment, R.id.et_input_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_emotion:
                //关闭软键盘
                KeyBoardUtils.closeKeybord(inputComment, NoteDetailsActivity.this);
                if(chatFaceContainer.getVisibility()==View.GONE){
                    chatFaceContainer.setVisibility(View.VISIBLE);
                }else{
                    chatFaceContainer.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_publish_comment:
                showCommentInputFrame(false);
                inputComment.setText("");
                break;

            case R.id.et_input_comment:
                if(chatFaceContainer.getVisibility()==View.VISIBLE){
                    chatFaceContainer.setVisibility(View.GONE);
                }
                break;
        }
    }
}
