package com.smyy.sharetour.buyer.seller;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.TabEntity;
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


public class HomeMenuFragment1 extends BaseMvpFragment {

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
    }

    @Override
    protected void initStatusBar() {
        setStatusBar(Color.BLACK);
    }


}
