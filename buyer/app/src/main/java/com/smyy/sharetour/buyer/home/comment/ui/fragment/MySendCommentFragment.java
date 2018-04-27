package com.smyy.sharetour.buyer.home.comment.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.comment.ui.adapter.MyReceiveCommentAdapter;
import com.smyy.sharetour.buyer.home.comment.ui.adapter.MySendCommentAdapter;
import com.smyy.sharetour.buyer.home.model.MyReceiveComment;
import com.smyy.sharetour.buyer.home.model.MySendComment;
import com.smyy.sharetour.buyer.home.model.SearchProduct;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * create by xuxiarong on 2018/4/13
 */
public class MySendCommentFragment extends BaseMvpFragment {


    @BindView(R.id.rv_my_send_comment)
    RecyclerView rvMySendComment;
    Unbinder unbinder;
    private List<SearchProduct> mSearchProduct = new ArrayList<>();
    private MySendCommentAdapter mAdapter;

    public static MySendCommentFragment getInstance(String title) {
        MySendCommentFragment sf = new MySendCommentFragment();
        return sf;
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_send_comment;
    }

    @Override
    protected void initData(Bundle bundle) {

        rvMySendComment.setLayoutManager(new LinearLayoutManager(getContext()));
        final List<MySendComment> data = new ArrayList<>();
        data.add(new MySendComment());
        data.add(new MySendComment());
        data.add(new MySendComment());
        mAdapter = new MySendCommentAdapter(data);
        rvMySendComment.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this.getActivity());

    }
}
