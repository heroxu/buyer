package com.smyy.sharetour.buyer.ui.buyCommodity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.backpacker.bomepage.BackpackHomePageActivity;
import com.smyy.sharetour.buyer.base.BaseFragment;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.dialog.DialogUtils;
import com.smyy.sharetour.buyer.view.SingleFragmentPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuyHomePageActivity extends BaseMvpActivity {
    @BindView(R.id.stl_buy_home_page)
    SlidingTabLayout stlBuyHomePage;
    @BindView(R.id.vp_buy_home_page)
    ViewPager vpBuyHomePage;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "商品列表", "买手动态", "直播录像"
    };
    private MyPagerAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_home_page;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        hideToolBarLayout(true);
        mFragments.add(BuyCommodityFragment.newInstance("0"));
        mFragments.add(BuyDynamicFragment.newInstance("1"));
        mFragments.add(BuyVideoFragment.newInstance("2"));
        mAdapter = new MyPagerAdapter(this.getSupportFragmentManager());
        vpBuyHomePage.setAdapter(mAdapter);
        stlBuyHomePage.setViewPager(vpBuyHomePage, mTitles);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.ic_close, R.id.iv_my_avatar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_close:
                finish();
                break;
            case R.id.iv_my_avatar:
                DialogUtils.BuyHomePageBuilder.showCollectionDialog(BuyHomePageActivity.this, new DialogUtils.BuyHomePageBuilder.OnOptionConfirmListener() {
                    @Override
                    public void onDelete() {

                    }
                });
                break;
        }
    }


    private class MyPagerAdapter extends SingleFragmentPageAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm, mFragments);
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
