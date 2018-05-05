package com.smyy.sharetour.buyer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCollectionActivity extends BaseMvpActivity {
    @BindView(R.id.ctl_collection)
    CommonTabLayout ctlCollection;
    public TextView toolbarRightTv;
    String titleRight;
    @BindView(R.id.cb_coll_all)
    CheckBox cbCollAll;
    @BindView(R.id.ll_cancel_collection)
    LinearLayout llCancelCollection;
    @BindView(R.id.btn_cancel_collection)
    Button btnCancelCollection;
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
        toolbarRightTv = getToolbarRightTv();
        toolbarRightTv.setText("管理");
        toolbarRightTv.setVisibility(View.VISIBLE);
        toolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleRight = toolbarRightTv.getText().toString().trim();
                if ("管理".equals(titleRight)) {
                    toolbarRightTv.setText("完成");
                    toolbarRightTv.setTextColor(getResources().getColor(R.color.txt_price));
                } else if ("完成".equals(titleRight)) {
                    toolbarRightTv.setText("管理");
                    toolbarRightTv.setTextColor(getResources().getColor(R.color.txt_main));
                }
                changeUITitle();
            }
        });
    }

    private void changeUITitle() {
        titleRight = toolbarRightTv.getText().toString().trim();
        if ("完成".equals(titleRight)) {
            ((CollectionFragment) mFragmentNavigator.getCurrentFragment()).setVisible();
            llCancelCollection.setVisibility(View.VISIBLE);
        } else if ("管理".equals(titleRight)) {
            ((CollectionFragment) mFragmentNavigator.getCurrentFragment()).setGone();
            llCancelCollection.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        mFragmentNavigator = new FragmentNavigator(getSupportFragmentManager(), mAdapter, R.id.fragment_collection);
        mFragmentNavigator.onCreate(savedInstanceState);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        mFragmentNavigator.showFragment(0);
        ctlCollection.setTabData(mTabEntities);
        ctlCollection.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                toolbarRightTv.setText("管理");
                toolbarRightTv.setTextColor(getResources().getColor(R.color.txt_main));
                ((CollectionFragment) mFragmentNavigator.getCurrentFragment()).setCheckAllFalse();
                changeUITitle();
                mFragmentNavigator.showFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        cbCollAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

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

    public void changeUI(boolean type) {
        cbCollAll.setChecked(type);
    }


    @OnClick({R.id.cb_coll_all, R.id.btn_cancel_collection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_coll_all:
                ((CollectionFragment) mFragmentNavigator.getCurrentFragment()).setCheckAll();
                break;
            case R.id.btn_cancel_collection:
                ((CollectionFragment) mFragmentNavigator.getCurrentFragment()).cancelCollection();
                break;
        }
    }
}
