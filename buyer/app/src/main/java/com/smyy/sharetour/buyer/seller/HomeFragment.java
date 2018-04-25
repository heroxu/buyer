package com.smyy.sharetour.buyer.seller;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.smyy.sharetour.buyer.Consts;
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
import butterknife.OnClick;


public class HomeFragment extends BaseMvpFragment {
    @BindView(R.id.tl_home_menu)
    CommonTabLayout tabMenu;
    @BindView(R.id.vp_home_menu)
    ViewPager vpMenu;
    @BindView(R.id.banner_home)
    Banner banner;

    private ArrayList<Fragment> mMenuFrags = new ArrayList<>();

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_seller_home;
    }

    @Override
    protected void initData(Bundle bundle) {
        initMenuVP();
        setBanner();
    }

    @Override
    protected void initStatusBar() {
        setStatusBar(Color.BLACK);
    }

    private void initMenuVP() {
        mMenuFrags.add(new HomeMenuFragment1());
        mMenuFrags.add(new HomeMenuFragment2());

        FragmentAdapter menuAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager());
        vpMenu.setAdapter(menuAdapter);

        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (Fragment frag :
                mMenuFrags) {
            mTabEntities.add(new TabEntity("",
                    R.drawable.shape_menu_indicator_selected, R.drawable.shape_menu_indicator_unselect));
        }

        tabMenu.setTabData(mTabEntities);
        tabMenu.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpMenu.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }

        });

        vpMenu.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabMenu.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 设置轮播图
     */
    private void setBanner() {
        //设置图片加载集合
        List<Integer> imageArray = new ArrayList<>();
        imageArray.add(R.mipmap.fake_banner);

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //Glide 加载图片简单用法
                Glide.with(context).load(path).into(imageView);
            }
        });
        //设置图片集合
        banner.setImages(imageArray);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播时间
        banner.setDelayTime(5000);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //TODO
            }
        });

    }

    private class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mMenuFrags.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

        @Override
        public Fragment getItem(int position) {
            return mMenuFrags.get(position);
        }
    }

    @OnClick({R.id.iv_home_setting, R.id.iv_home_top_arrow, R.id.iv_home_qrcode,
            R.id.lay_home_publish_travel, R.id.lay_home_publish_goods,
            R.id.lay_home_publish_note, R.id.lay_home_publish_live,
            R.id.iv_home_remind_arrow})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt(Consts.USER_TYPE, Consts.USER_TYPE_SELLER);

        switch (view.getId()) {

            case R.id.iv_home_setting:

                break;

            case R.id.iv_home_top_arrow:

                break;

            case R.id.iv_home_qrcode:

                break;

            case R.id.lay_home_publish_travel:

                break;

            case R.id.iv_home_remind_arrow:

                break;


            default:
                break;
        }
    }
}
