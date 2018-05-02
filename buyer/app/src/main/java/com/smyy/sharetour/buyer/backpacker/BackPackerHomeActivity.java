package com.smyy.sharetour.buyer.backpacker;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.backpacker.my.MyWalletActivity;
import com.smyy.sharetour.buyer.backpacker.payPwd.BackpackSettingActivity;
import com.smyy.sharetour.buyer.backpacker.require.BackPackerRequireListActivity;
import com.smyy.sharetour.buyer.backpacker.travel.BackPackerTravelListActivity;
import com.smyy.sharetour.buyer.backpacker.travel.GoodTagActivity;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.module.order.OrderHelper;
import com.smyy.sharetour.buyer.module.order.OrderListActivity;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.view.HomeTitlesOpenOrCloseView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.BackPacker
 * @fileName BackPackerHomeActivity
 * @date on 2018/4/22 0022 14:37
 * @describe 背包客首页
 */

public class BackPackerHomeActivity extends BaseMvpActivity {

    @BindView(R.id.tl_7)
    SlidingTabLayout tabLayout_7;
    @BindView(R.id.home_iv_title_arrow)
    AppCompatImageView home_iv_title_arrow;
    @BindView(R.id.hv_home_title)
    HomeTitlesOpenOrCloseView hv_home_title;
    @BindView(R.id.ll_home_search)
    LinearLayout ll_home_search;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.user_head)
    ImageView userHead;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.banner)
    ImageView banner;

    private boolean mArrowIsUp = true;
    private final String[] mTitles = {
            "精选", "美容美肤", "潮流时尚"
            , "母婴健康", "文化玩乐", "美容美肤"
    };
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacker_home;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        hideToolBarLayout(true);
        initListener();
        loadData();

        for (String title : mTitles) {
            mFragments.add(BackPackerHomeFragment.getInstance(title));
        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        tabLayout_7.setViewPager(vp, mTitles);
    }


    private void initListener() {
        hv_home_title.setIStatusChange(new HomeTitlesOpenOrCloseView.IStatusChange() {
            @Override
            public void selectPosition(int position) {
                if (position >= 0) {
                    vp.setCurrentItem(position);
                }
                ObjectAnimator.ofFloat(home_iv_title_arrow, "rotation", 180, 360).setDuration(250).start();
                mArrowIsUp = true;
            }
        }, mTitles);

        home_iv_title_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hv_home_title.isAnimating()) {
                    return;
                }
                ObjectAnimator.ofFloat(home_iv_title_arrow, "rotation", mArrowIsUp ? 0 : 180, mArrowIsUp ? 180 : 360).setDuration(250).start();
                if (mArrowIsUp) {
                    hv_home_title.animateOpen();
                } else {
                    hv_home_title.animateClose();
                }
                mArrowIsUp = !mArrowIsUp;
            }
        });

        ll_home_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityLauncher.viewHomeSearch(BackPackerHomeActivity.this);
            }
        });

    }


    private void loadData() {

    }



    @OnClick({R.id.iv_home_switch, R.id.tt_fount_message, R.id.tt_my_info,
            R.id.send_travel, R.id.nav_order, R.id.nav_require, R.id.nav_travel,
            R.id.nav_wallet, R.id.nav_setting,R.id.ll_btn_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_home_switch:
                ActivityLauncher.viewBuyerHomeActivity(BackPackerHomeActivity.this);
                finish();
                break;
            case R.id.tt_fount_message:
                ActivityLauncher.viewMessageListActivity(BackPackerHomeActivity.this);
                break;

            case R.id.tt_my_info:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;

            case R.id.send_travel:
                startActivity(new Intent(BackPackerHomeActivity.this, GoodTagActivity.class));
                break;

            case R.id.nav_setting:
                drawerLayout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(BackPackerHomeActivity.this, BackpackSettingActivity.class));
                break;

            case R.id.nav_order:
                drawerLayout.closeDrawer(Gravity.LEFT);
                Bundle bundle=  new Bundle();
                bundle.putInt(OrderHelper.USER_TYPE,OrderHelper.USER_TYPE_BACK_PACKER);
                Intent intent = new Intent(BackPackerHomeActivity.this, OrderListActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;
            case R.id.nav_require:
                drawerLayout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(BackPackerHomeActivity.this, BackPackerRequireListActivity.class));
                break;
            case R.id.nav_travel:
                drawerLayout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(BackPackerHomeActivity.this, BackPackerTravelListActivity.class));
                break;
            case R.id.nav_wallet:
                drawerLayout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(BackPackerHomeActivity.this, MyWalletActivity.class));
            case R.id.ll_btn_user:
                drawerLayout.closeDrawer(Gravity.LEFT);
                ActivityLauncher.viewBackpackHomePageActivity(BackPackerHomeActivity.this);
                break;
        }
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

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(Gravity.LEFT)){
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }
}
