package com.smyy.sharetour.buyer.fragment;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
    @BindView(R.id.home_rv_edit_title)
    RecyclerView home_rv_edit_title;
    @BindView(R.id.home_iv_title_arrow)
    AppCompatImageView home_iv_title_arrow;

    private boolean mArrowIsUp = true;
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
        initListener();
        initPermissions();
        for (String title : mTitles) {
            mFragments.add(IndexSubclassFragment.getInstance(title));
        }
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        tabLayout_7.setViewPager(vp,mTitles);

//        vp.setCurrentItem(4);
        loadData();
    }

    private void initListener() {

        home_iv_title_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ObjectAnimator
                        .ofFloat(home_iv_title_arrow, "rotation", mArrowIsUp?0:180, mArrowIsUp?180:360)//
                        .setDuration(300)//
                        .start();
                mArrowIsUp=!mArrowIsUp;

            }
        });
    }


    private void loadData() {

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
