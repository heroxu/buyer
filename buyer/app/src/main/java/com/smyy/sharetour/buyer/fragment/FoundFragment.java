package com.smyy.sharetour.buyer.fragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dtr.zxing.activity.CaptureActivity;
import com.flyco.tablayout.SlidingTabLayout;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.BaseFragment;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.search.activity.SearchActivity;
import com.smyy.sharetour.buyer.ui.Test1Activity;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.ToastUtils;
import com.smyy.sharetour.buyer.view.HomeTitlesOpenOrCloseView;
import com.smyy.sharetour.buyer.view.SingleFragmentPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by hasee on 2018/3/15.
 */

public class FoundFragment extends BaseMvpFragment {
    public static final int REQUEST_CODE_SCAN = 3301;
    @BindView(R.id.ttl_fount_search)
    LinearLayout ttlFountSearch;
    @BindView(R.id.tt_fount_scan)
    ImageView ttFountScan;
    @BindView(R.id.tt_fount_message)
    ImageView ttFountMessage;
    @BindView(R.id.hv_fount_title)
    HomeTitlesOpenOrCloseView hvFountTitle;
    @BindView(R.id.fount_iv_title_arrow)
    AppCompatImageView fountIvTitleArrow;
    private List<BaseFragment> mFragments = new ArrayList<>();
    @BindView(R.id.stl_fount)
    SlidingTabLayout stlFount;
    @BindView(R.id.vp_fount)
    ViewPager vpFount;
    private MyPagerAdapter mAdapter;
    private boolean mArrowIsUp = true;
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
        changeTitleBarColor();
        for (String title : mTitles) {
            mFragments.add(FountSubclassFragment.getInstance(title));
        }
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        vpFount.setAdapter(mAdapter);
        stlFount.setViewPager(vpFount, mTitles);
        //头部筛选对话框
        hvFountTitle.setIStatusChange(new HomeTitlesOpenOrCloseView.IStatusChange() {
            @Override
            public void selectPosition(int position) {
                if (position >= 0) {
                    vpFount.setCurrentItem(position);
                }
                ObjectAnimator.ofFloat(fountIvTitleArrow, "rotation", 180, 360).setDuration(250).start();
                mArrowIsUp = true;
            }
        }, mTitles);
    }

    private void changeTitleBarColor() {
        StatusBarAdapter.changeStatusBarColor(getActivity(), getResources().getColor(R.color.white));
    }


    @OnClick({R.id.ttl_fount_search, R.id.tt_fount_scan, R.id.tt_fount_message, R.id.fount_iv_title_arrow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ttl_fount_search:
                ActivityLauncher.viewBackpackCertificationActivity(getActivity());
//                ActivityLauncher.viewSearchActivity(getContext(), SearchActivity.BUNDLE_FOUNT);
//                startActivity(Test1Activity.class);
                break;
            case R.id.tt_fount_scan:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), REQUEST_CODE_SCAN);
                break;
            case R.id.tt_fount_message:
                ActivityLauncher.viewMessageListActivity(getActivity());
                break;
            case R.id.fount_iv_title_arrow:
                if (hvFountTitle.isAnimating()) {
                    return;
                }
                ObjectAnimator.ofFloat(fountIvTitleArrow, "rotation", mArrowIsUp ? 0 : 180, mArrowIsUp ? 180 : 360).setDuration(250).start();
                if (mArrowIsUp) {
                    hvFountTitle.animateOpen();
                } else {
                    hvFountTitle.animateClose();
                }
                mArrowIsUp = !mArrowIsUp;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_SCAN) {
            if (resultCode == Activity.RESULT_OK) {
                String result = intent.getStringExtra(CaptureActivity.BUNDLE_RESULT);
                ToastUtils.showToast(result);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                ToastUtils.showToast("扫码取消");
            } else {
                ToastUtils.showToast("扫码错误");
            }
        }
    }
}
