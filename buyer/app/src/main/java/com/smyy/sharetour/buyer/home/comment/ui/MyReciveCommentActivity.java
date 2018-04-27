package com.smyy.sharetour.buyer.home.comment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.search.activity.SearchDetailActivity;
import com.smyy.sharetour.buyer.home.search.fragment.SearchBuyerFragment;
import com.smyy.sharetour.buyer.home.search.fragment.SearchProductFragment;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyReciveCommentActivity extends BaseMvpActivity {


    @BindView(R.id.stl_my_comment)
    SlidingTabLayout stlMyComment;
    @BindView(R.id.vp_my_comment)
    ViewPager vpMyComment;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"商品", "买手",};
    private MyCommentFragmentAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_recive_comment;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("我的评论");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

        mFragments.add(SearchProductFragment.getInstance(mTitles[0]));
        mFragments.add(SearchBuyerFragment.getInstance(mTitles[1]));

        mAdapter = new MyCommentFragmentAdapter(this.getSupportFragmentManager());
        vpMyComment.setAdapter(mAdapter);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private class MyCommentFragmentAdapter extends FragmentPagerAdapter {
        public MyCommentFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}