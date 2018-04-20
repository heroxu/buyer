package com.smyy.sharetour.buyer.module.order;

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
import com.smyy.sharetour.buyer.home.search.fragment.SearchBuyerFragment;
import com.smyy.sharetour.buyer.home.search.fragment.SearchProductFragment;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;

import java.util.ArrayList;

import butterknife.BindView;

public class OrderListActivity extends MyBaseMvpActivity {
    @BindView(R.id.stl_order_list)
    SlidingTabLayout stlSearchDetail;
    @BindView(R.id.vp_order_list)
    ViewPager vpSearchDetail;

    private final String[] mTitles = {"全部", "待付款", "待发货", "待收货", "待评价"};
    private FragmentAdapter mAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("我的订单");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

        mFragments.add(SearchProductFragment.getInstance(mTitles[0]));
        mFragments.add(SearchBuyerFragment.getInstance(mTitles[1]));
        mFragments.add(SearchBuyerFragment.getInstance(mTitles[1]));
        mFragments.add(SearchBuyerFragment.getInstance(mTitles[1]));
        mFragments.add(SearchBuyerFragment.getInstance(mTitles[1]));

        mAdapter = new FragmentAdapter(this.getSupportFragmentManager());
        vpSearchDetail.setAdapter(mAdapter);
        stlSearchDetail.setViewPager(vpSearchDetail, mTitles);
    }

    @Override
    protected MyBasePresenter createPresenter() {
        return null;
    }

    private class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
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
