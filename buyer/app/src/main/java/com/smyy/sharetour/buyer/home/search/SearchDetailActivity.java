package com.smyy.sharetour.buyer.home.search;

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
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by xuxiarong on 2018/4/13
 */
public class SearchDetailActivity extends BaseMvpActivity implements View.OnClickListener{
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

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"商品", "买手",};
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
        hideToolBarLayout(true);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

        mFragments.add(SearchProductFragment.getInstance(mTitles[0]));
        mFragments.add(SearchBuyerFragment.getInstance(mTitles[1]));

        mAdapter = new HomeSearchFragmentAdapter(this.getSupportFragmentManager());
        vpSearchDetail.setAdapter(mAdapter);
        stlSearchDetail.setViewPager(vpSearchDetail, mTitles);
        ivSearchDetailBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_search_detail_back:
                finish();
                break;
        }
    }

    private class HomeSearchFragmentAdapter extends FragmentPagerAdapter {
        public HomeSearchFragmentAdapter(FragmentManager fm) {
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
