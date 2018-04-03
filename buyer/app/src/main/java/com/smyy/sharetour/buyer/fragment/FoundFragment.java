package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by hasee on 2018/3/15.
 */

public class FoundFragment extends BaseMvpFragment {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @BindView(R.id.stl_fount)
    SlidingTabLayout stlFount;
    @BindView(R.id.vp_fount)
    ViewPager vpFount;
    private MyPagerAdapter mAdapter;
    private final String[] mTitles = {
            "精选", "美容美肤", "潮流时尚"
            , "母婴健康", "文化玩乐", "美容美肤"
    };

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_fount;
    }

    @Override
    protected void initData(Bundle bundle) {
        for (String title : mTitles) {
            mFragments.add(FountSubclassFragment.getInstance(title));
        }
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        vpFount.setAdapter(mAdapter);
        stlFount.setViewPager(vpFount, mTitles);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
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
