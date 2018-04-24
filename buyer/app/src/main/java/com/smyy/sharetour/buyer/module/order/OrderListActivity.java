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
import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class OrderListActivity extends MyBaseMvpActivity {
    @BindView(R.id.stl_order_list)
    SlidingTabLayout stlOrderList;
    @BindView(R.id.vp_order_list)
    ViewPager vpOrderList;

    private String[] mTitles;
    private HashMap<Integer, Integer> mOrderTypeMap = new HashMap<>();
    private FragmentAdapter mAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("我的订单");
        hideToolBarDividerLine(true);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        Bundle bundle = getBundle();

        if (bundle != null) {
            int userType = bundle.getInt(Consts.USER_TYPE);
            switch (userType) {
                case Consts.USER_TYPE_BUYER:
                    mTitles = new String[]{"全部", "待付款", "待发货", "待收货", "待评价"};
                    mOrderTypeMap.clear();
                    mFragments.clear();
                    mOrderTypeMap.put(Consts.ORDER_TYPE_ALL, 0);
                    mFragments.add(OrderListFragment.getInstance(userType, Consts.ORDER_TYPE_ALL));
                    mOrderTypeMap.put(Consts.ORDER_TYPE_AWAIT_PAY, 1);
                    mFragments.add(OrderListFragment.getInstance(userType, Consts.ORDER_TYPE_AWAIT_PAY));
                    mOrderTypeMap.put(Consts.ORDER_TYPE_AWAIT_SHIPPING, 2);
                    mFragments.add(OrderListFragment.getInstance(userType, Consts.ORDER_TYPE_AWAIT_SHIPPING));
                    mOrderTypeMap.put(Consts.ORDER_TYPE_AWAIT_CONFIRM, 3);
                    mFragments.add(OrderListFragment.getInstance(userType, Consts.ORDER_TYPE_AWAIT_CONFIRM));
                    mOrderTypeMap.put(Consts.ORDER_TYPE_AWAIT_REVIEW, 4);
                    mFragments.add(OrderListFragment.getInstance(userType, Consts.ORDER_TYPE_AWAIT_REVIEW));
                    break;
                case Consts.USER_TYPE_BACK_PACKER:
                    mTitles = new String[]{"全部", "待发货", "已收货"};
                    mOrderTypeMap.clear();
                    mFragments.clear();
                    mOrderTypeMap.put(Consts.ORDER_TYPE_ALL, 0);
                    mFragments.add(OrderListFragment.getInstance(userType, Consts.ORDER_TYPE_ALL));
                    mOrderTypeMap.put(Consts.ORDER_TYPE_AWAIT_SHIPPING, 1);
                    mFragments.add(OrderListFragment.getInstance(userType, Consts.ORDER_TYPE_AWAIT_SHIPPING));
                    mOrderTypeMap.put(Consts.ORDER_TYPE_AWAIT_REVIEW, 2);
                    mFragments.add(OrderListFragment.getInstance(userType, Consts.ORDER_TYPE_AWAIT_REVIEW));
                    break;
                case Consts.USER_TYPE_SELLER:
                    mTitles = new String[]{"全部", "待付款", "待发货", "已发货"};
                    mOrderTypeMap.clear();
                    mFragments.clear();
                    mOrderTypeMap.put(Consts.ORDER_TYPE_ALL, 0);
                    mFragments.add(OrderListFragment.getInstance(userType, Consts.ORDER_TYPE_ALL));
                    mOrderTypeMap.put(Consts.ORDER_TYPE_AWAIT_PAY, 1);
                    mFragments.add(OrderListFragment.getInstance(userType, Consts.ORDER_TYPE_AWAIT_PAY));
                    mOrderTypeMap.put(Consts.ORDER_TYPE_AWAIT_SHIPPING, 2);
                    mFragments.add(OrderListFragment.getInstance(userType, Consts.ORDER_TYPE_AWAIT_SHIPPING));
                    mOrderTypeMap.put(Consts.ORDER_TYPE_AWAIT_CONFIRM, 3);
                    mFragments.add(OrderListFragment.getInstance(userType, Consts.ORDER_TYPE_AWAIT_CONFIRM));
                    break;
                default:
                    break;
            }

            mAdapter = new FragmentAdapter(this.getSupportFragmentManager());
            vpOrderList.setAdapter(mAdapter);
            stlOrderList.setViewPager(vpOrderList, mTitles);


            int orderTypeIndex = mOrderTypeMap.get(bundle.getInt(Consts.ORDER_TYPE));
            if (orderTypeIndex >= 0 && orderTypeIndex < mTitles.length) {
                stlOrderList.setCurrentTab(orderTypeIndex);
            }
        }
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
