package com.smyy.sharetour.buyer.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dtr.zxing.activity.CaptureActivity;
import com.flyco.tablayout.SlidingTabLayout;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.view.SingleFragmentPageAdapter;
import com.smyy.sharetour.buyer.base.BaseFragment;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    private List<BaseFragment> mFragments = new ArrayList<>();
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
        changeTitleBarColor();
        for (String title : mTitles) {
            mFragments.add(FountSubclassFragment.getInstance(title));
        }
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        vpFount.setAdapter(mAdapter);
        stlFount.setViewPager(vpFount, mTitles);
    }

    private void changeTitleBarColor() {
        StatusBarAdapter.changeStatusBarColor(getActivity(), getResources().getColor(R.color.white));
    }


    @OnClick({R.id.ttl_fount_search, R.id.tt_fount_scan, R.id.tt_fount_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ttl_fount_search:
                ActivityLauncher.viewBackpackHomePageActivity(getActivity());
//                ActivityLauncher.viewBackpackSettingActivity(getActivity());
//                ActivityLauncher.viewBackpackerModeActivity(getActivity());
//                ActivityLauncher.viewBackpackCertificationActivity(getActivity());
//                ActivityLauncher.viewSmallBackpackActivity(getActivity());
//                ActivityLauncher.viewReportActivity(getActivity());
                break;
            case R.id.tt_fount_scan:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class),REQUEST_CODE_SCAN);
                break;
            case R.id.tt_fount_message:
                ActivityLauncher.viewGuideLoginActivity(getActivity());
                break;
        }
    }

    private class MyPagerAdapter extends SingleFragmentPageAdapter {
        public MyPagerAdapter(FragmentManager fm) {
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
