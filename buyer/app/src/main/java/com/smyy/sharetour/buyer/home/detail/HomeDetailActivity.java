package com.smyy.sharetour.buyer.home.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.home.detail.commet.ProductDetailCommentFragment;
import com.smyy.sharetour.buyer.home.detail.product.ProductDetailFragment;
import com.smyy.sharetour.buyer.home.detail.service.ProductDetailServiceFragment;
import com.smyy.sharetour.buyer.tim.ChatActivity;
import com.smyy.sharetour.buyer.view.RedImageView;
import com.tencent.imsdk.TIMConversationType;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by xuxiarong on $DATA$
 */
public class HomeDetailActivity extends BaseMvpActivity implements View.OnClickListener {
    @BindView(R.id.iv_product_detail_back)
    ImageView ivProductDetailBack;
    @BindView(R.id.tv_product_detail_title)
    TextView tvProductDetailTitle;
    @BindView(R.id.tv_product_detail_price)
    TextView tvProductDetailPrice;
    @BindView(R.id.iv_search_detail_share)
    ImageView ivSearchDetailShare;
    @BindView(R.id.iv_product_detail_collect)
    ImageView ivProductDetailCollect;
    @BindView(R.id.stl_product_detail)
    SlidingTabLayout stlProductDetail;
    @BindView(R.id.ll_product_detail_tab_top)
    LinearLayout llProductDetailTabTop;
    @BindView(R.id.tv_product_detail_add_bag)
    TextView tvProductDetailAddBag;
    @BindView(R.id.tv_product_detail_add_cart)
    TextView tvProductDetailAddCart;
    @BindView(R.id.ll_product_detail_bottom_buy)
    LinearLayout llProductDetailBottomBuy;
    @BindView(R.id.tv_product_detail_expand_bag)
    TextView tvProductDetailExpandBag;
    @BindView(R.id.tv_product_detail_close_bag)
    TextView tvProductDetailCloseBag;
    @BindView(R.id.iv_product_detail_bag)
    RedImageView ivProductDetailBag;
    @BindView(R.id.iv_product_detail_comment)
    ImageView ivProductDetailComment;
    @BindView(R.id.ll_product_detail_close_bag)
    LinearLayout llProductDetailCloseBag;
    @BindView(R.id.ll_product_detail_bag_parent)
    LinearLayout llProductDetailBagParent;
    @BindView(R.id.vp_product_detail)
    ViewPager vpProductDetail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.banner_product_detail)
    Banner bannerProductDetail;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"详情", "评价", "服务"};
    private HomeDetailFragmentAdapter mAdapter;
    private boolean isCollected;
    private int productNum;


    private List<String> mHotDatas = new ArrayList<>();

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_detail;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        hideToolBarLayout(true);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mFragments.add(ProductDetailFragment.getInstance(mTitles[0]));
        mFragments.add(ProductDetailCommentFragment.getInstance(mTitles[1]));
        mFragments.add(ProductDetailServiceFragment.getInstance(mTitles[2]));

        mAdapter = new HomeDetailFragmentAdapter(this.getSupportFragmentManager());
        vpProductDetail.setAdapter(mAdapter);
        stlProductDetail.setViewPager(vpProductDetail, mTitles);
        ivProductDetailBack.setOnClickListener(this);
        ivProductDetailCollect.setOnClickListener(this);
        ivSearchDetailShare.setOnClickListener(this);
        tvProductDetailExpandBag.setOnClickListener(this);
        tvProductDetailCloseBag.setOnClickListener(this);
        ivProductDetailBag.setOnClickListener(this);
        ivProductDetailComment.setOnClickListener(this);
        ivProductDetailBag.setRedPointVisible(productNum > 0 ? View.VISIBLE : View.GONE);
        int visibility = getToolbar().getVisibility();
        Log.e("sss", "onCreate: vvv" + visibility);
        if (visibility == View.VISIBLE) {
            getToolbar().setVisibility(View.GONE);
        }
        setBanner();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_product_detail_back:
                finish();
                break;
            case R.id.iv_product_detail_collect:
                ivProductDetailCollect.setImageResource(isCollected ? R.mipmap.ic_collection_ele :
                        R.mipmap.ic_comment_collection);
                isCollected = !isCollected;
                break;
            case R.id.iv_search_detail_share:
                //TODO share to other
                break;
            case R.id.tv_product_detail_expand_bag:
                tvProductDetailExpandBag.setVisibility(View.GONE);
                llProductDetailCloseBag.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_product_detail_close_bag:
                tvProductDetailExpandBag.setVisibility(View.VISIBLE);
                llProductDetailCloseBag.setVisibility(View.GONE);
                break;
            case R.id.iv_product_detail_bag:
                ivProductDetailBag.setText((productNum++) + "");

                break;
            case R.id.iv_product_detail_comment:
                ChatActivity.navToChat(this, "XMYY_XXR", TIMConversationType.C2C);
                break;
        }
    }


    /**
     * 设置轮播图
     */
    private void setBanner() {
        //设置图片加载集合
        List<Integer> imageArray = new ArrayList<>();
        imageArray.add(R.mipmap.carousel_live_01);
        imageArray.add(R.mipmap.carousel_live_01);
        imageArray.add(R.mipmap.carousel_live_01);
        imageArray.add(R.mipmap.carousel_live_01);
        imageArray.add(R.mipmap.carousel_live_01);
        //设置banner样式
        bannerProductDetail.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        bannerProductDetail.setImageLoader(new ImageLoader() {

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //Glide 加载图片简单用法
                Glide.with(context).load(path).into(imageView);
            }
        });
        //设置图片集合
        bannerProductDetail.setImages(imageArray);
        //设置banner动画效果
        bannerProductDetail.setBannerAnimation(Transformer.Default);
        //设置轮播时间
        bannerProductDetail.setDelayTime(5000);
        bannerProductDetail.start();
        bannerProductDetail.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //TODO：跳到对应的直播间
                //ToastUtils.showToast(R.string.feature_developing);
            }
        });

    }


    private class HomeDetailFragmentAdapter extends FragmentPagerAdapter {
        public HomeDetailFragmentAdapter(FragmentManager fm) {
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
