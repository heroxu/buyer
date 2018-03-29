package com.smyy.sharetour.buyer.fragment;

import android.Manifest;
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
import java.util.List;

import butterknife.BindView;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * Created by hasee on 2018/3/15.
 */

public class HomeFragment extends BaseMvpFragment {
    public static final int REQUEST_CODE_SCAN = 3301;
    @BindView(R.id.tl_7)
    SlidingTabLayout tabLayout_7;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "全部", "日本", "法国"
            , "马来西亚", "新加坡", "巴西", "阿富汗",
            "美国", "澳大利亚", "墨西哥"
            , "阿根廷", "南非", "俄罗斯", "英国"
    };
    private MyPagerAdapter mAdapter;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_home;
    }

    @Override
    protected void initData(Bundle bundle) {
        changeTitleBarColor();
        initPermissions();
        for (String title : mTitles) {
            mFragments.add(IndexSubclassFragment.getInstance(title));
        }
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        /** indicator矩形圆角 */
        tabLayout_7.setViewPager(vp, mTitles);
//        vp.setCurrentItem(4);
    }

    private void initPermissions() {
        List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
        permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        HiPermission.create(getActivity()).permissions(permissonItems).checkMutiPermission(new PermissionCallback() {
            @Override
            public void onClose() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onDeny(String permission, int position) {

            }

            @Override
            public void onGuarantee(String permission, int position) {

            }
        });
    }

    private void changeTitleBarColor() {
        StatusBarAdapter.changeStatusBarColor(getActivity(), getResources().getColor(R.color.colorAccent));
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
