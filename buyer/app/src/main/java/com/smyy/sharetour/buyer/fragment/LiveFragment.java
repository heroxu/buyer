package com.smyy.sharetour.buyer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lifg on 2018/4/10.
 */

public class LiveFragment extends BaseMvpFragment {

    @BindView(R.id.live_vp_fount)
    ViewPager liveVPFount;
    @BindView(R.id.live_stl_fount)
    SlidingTabLayout liveSTLFount;
    @BindView(R.id.live_fount_banner)
    Banner liveBanner;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyViewPagerAdapter mAdapter;
    private final String[] mTitles = {
            "全部", "日本", "韩国"
            , "马来西亚", "新加坡", "巴西","美国","意大利","德国","挪威","西班牙","瑞士","法国"
    };
    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_live;
    }
    @Override
    protected void initData(Bundle bundle) {
        changeTitleBarColor();
        setBanner();
        for (String title : mTitles) {
            mFragments.add(FountSubclassFragment.getInstance(title));
        }
        mAdapter = new MyViewPagerAdapter(getActivity().getSupportFragmentManager());
        liveVPFount.setAdapter(mAdapter);
        liveSTLFount.setViewPager(liveVPFount, mTitles);
    }
    private void changeTitleBarColor() {
        StatusBarAdapter.changeStatusBarColor(getActivity(), getResources().getColor(R.color.white));
    }
    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
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
    /**
     * 设置轮播图
     */
    private void setBanner(){
        //设置图片加载集合
        List<Integer> imageArray = new ArrayList<>();
        imageArray.add(R.mipmap.carousel_live_01);
        imageArray.add(R.mipmap.carousel_live_01);
        imageArray.add(R.mipmap.carousel_live_01);
        imageArray.add(R.mipmap.carousel_live_01);
        imageArray.add(R.mipmap.carousel_live_01);
        //设置banner样式
        liveBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        liveBanner.setImageLoader(new ImageLoader() {

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //Glide 加载图片简单用法
                Glide.with(context).load(path).into(imageView);
            }
        });
        //设置图片集合
        liveBanner.setImages(imageArray);
        //设置banner动画效果
        liveBanner.setBannerAnimation(Transformer.Default);
        //设置轮播时间
        liveBanner.setDelayTime(5000);
        liveBanner.start();
        liveBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
           //TODO：跳到对应的直播间
           //ToastUtils.showToast(R.string.feature_developing);
            }
        });

    }
}
