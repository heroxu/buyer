package com.smyy.sharetour.buyer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.TabEntity;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.fragment.CollectionFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MyCollectionActivity extends BaseMvpActivity{
    @BindView(R.id.ctl_collection)
    CommonTabLayout ctlCollection;
    private FragmentNavigator mFragmentNavigator;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"商品", "视频", "笔记"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("我的收藏");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        mFragmentNavigator = new FragmentNavigator(getSupportFragmentManager(), mAdapter, R.id.fragment_collection);
        mFragmentNavigator.onCreate(savedInstanceState);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i],0,0));
        }
        mFragmentNavigator.showFragment(0);
        ctlCollection.setTabData(mTabEntities);
        ctlCollection.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mFragmentNavigator.showFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    final FragmentNavigatorAdapter mAdapter = new FragmentNavigatorAdapter() {
        @Override
        public Fragment onCreateFragment(int i) {
            return CollectionFragment.newInstance(getTag(i));
        }

        @Override
        public String getTag(int i) {
            return String.valueOf(i);
        }

        @Override
        public int getCount() {
            return 3;
        }
    };
}
