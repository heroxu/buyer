package com.smyy.sharetour.buyer.home.search.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.BaseFragment;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.search.fragment.SearchBuyerFragment;
import com.smyy.sharetour.buyer.home.search.fragment.SearchNotesFragment;
import com.smyy.sharetour.buyer.home.search.fragment.SearchProductFragment;
import com.smyy.sharetour.buyer.home.search.fragment.SearchVideoFragment;
import com.smyy.sharetour.buyer.util.LogUtil;
import com.smyy.sharetour.buyer.view.SingleFragmentPageAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * create by xuxiarong on 2018/4/13
 */
public class SearchDetailActivity extends BaseMvpActivity{
    @BindView(R.id.iv_search_detail_back)
    ImageView ivSearchDetailBack;
    @BindView(R.id.tv_search_detail_cancel)
    TextView tvSearchDetailCancel;
    @BindView(R.id.sv_search_detail)
    SearchView svSearchDetail;
    @BindView(R.id.stl_search_detail)
    SlidingTabLayout stlSearchDetail;
    @BindView(R.id.vp_search_detail)
    ViewPager vpSearchDetail;
    private String searchType;//判断是搜索什么类型的东西
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"商品", "买手",};
    private final String[] mTitlesDynamic = {"笔记", "视频",};
    private HomeSearchFragmentAdapter mAdapter;


    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_detail;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        if(getIntent()!=null){
            searchType = getIntent().getStringExtra(SearchActivity.TYPE);
        }
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        hideToolBarLayout(true);
        if (SearchActivity.BUNDLE_FOUNT.equals(searchType)){
            mFragments.add(SearchNotesFragment.getInstance(mTitlesDynamic[0]));
            mFragments.add(SearchVideoFragment.getInstance(mTitlesDynamic[1]));
            mAdapter = new HomeSearchFragmentAdapter(this.getSupportFragmentManager());
            vpSearchDetail.setAdapter(mAdapter);
            stlSearchDetail.setViewPager(vpSearchDetail, mTitlesDynamic);
        }else {
            mFragments.add(SearchProductFragment.getInstance(mTitles[0]));
            mFragments.add(SearchBuyerFragment.getInstance(mTitles[1]));
            mAdapter = new HomeSearchFragmentAdapter(this.getSupportFragmentManager());
            vpSearchDetail.setAdapter(mAdapter);
            stlSearchDetail.setViewPager(vpSearchDetail, mTitles);
        }
    }

    @OnClick({R.id.iv_search_detail_back})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_search_detail_back:
                finish();
                break;
        }
    }

    private class HomeSearchFragmentAdapter extends SingleFragmentPageAdapter {
        public HomeSearchFragmentAdapter(FragmentManager fm) {
            super(fm,mFragments);
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
