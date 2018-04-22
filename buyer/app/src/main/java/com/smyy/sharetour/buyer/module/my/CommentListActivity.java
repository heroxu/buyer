package com.smyy.sharetour.buyer.module.my;

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
import com.smyy.sharetour.buyer.bean.CommentsBean;
import com.smyy.sharetour.buyer.module.my.adapter.CommentItemAdapter;
import com.smyy.sharetour.buyer.module.order.OrderCommentActivity;
import com.smyy.sharetour.buyer.publish.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.module.my
 * @fileName CommentListActivity
 * @date on 2018/4/22 0022 10:27
 * @describe 我的评价
 */
public class CommentListActivity extends BaseMvpActivity {

    private static final int REQUIRE_OUT_REQUEST_CANCEL = 54;
    @BindView(R.id.comment_list)
    RecyclerView commentList;

    private List<CommentsBean> comments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_comment_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("我的评价");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        commentList.setHasFixedSize(true);
        commentList.setLayoutManager(new LinearLayoutManager(CommentListActivity.this));
        commentList.addItemDecoration(new RecyclerViewDivider(CommentListActivity.this,
                LinearLayoutManager.VERTICAL, 5, R.color.window_background));

        demo();
        final CommentItemAdapter adapter = new CommentItemAdapter(CommentListActivity.this, comments);
        adapter.setItemClickListener(new CommentItemAdapter.OnCommentItemOnClickListener() {
            @Override
            public void OnItemClick(View v, int position) {

            }

            @Override
            public void OnItemAppendClick(View v, int position) {
                finish();
                Intent intent = new Intent(CommentListActivity.this, OrderCommentActivity.class);
                intent.putExtra("append", true);
                startActivity(intent);
            }
        });
        commentList.setAdapter(adapter);
    }

    private void demo() {
        for (int i = 0; i < 3; i++) {
            CommentsBean bean = new CommentsBean();
            comments.add(bean);
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


}
