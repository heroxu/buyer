package com.smyy.sharetour.buyer.home.comment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.comment.ui.fragment.MyReceiveCommentFragment;
import com.smyy.sharetour.buyer.home.comment.ui.fragment.MyReceiveLikeFragment;
import com.smyy.sharetour.buyer.home.comment.ui.fragment.MySendCommentFragment;
import com.smyy.sharetour.buyer.home.comment.ui.fragment.MySendLikeFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyReciveLikeActivity extends BaseMvpActivity {


    @BindView(R.id.stl_my_like)
    SlidingTabLayout stlMyLike;
    @BindView(R.id.vp_my_like)
    ViewPager vpMyLike;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"收到的赞", "发出的赞",};
    private MyLikeFragmentAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_recive_like;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("我的赞");
        hideToolBarDividerLine(true);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

        mFragments.add(MyReceiveLikeFragment.getInstance(mTitles[0]));
        mFragments.add(MySendLikeFragment.getInstance(mTitles[1]));

        mAdapter = new MyLikeFragmentAdapter(this.getSupportFragmentManager());
        vpMyLike.setAdapter(mAdapter);
        stlMyLike.setViewPager(vpMyLike, mTitles);
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

    private class MyLikeFragmentAdapter extends FragmentPagerAdapter {
        public MyLikeFragmentAdapter(FragmentManager fm) {
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