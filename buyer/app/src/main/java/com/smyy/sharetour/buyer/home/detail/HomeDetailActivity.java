package com.smyy.sharetour.buyer.home.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.home.detail.commet.ProductDetailCommentFragment;
import com.smyy.sharetour.buyer.home.detail.dialog.ProductConfirmOrderDialog;
import com.smyy.sharetour.buyer.home.detail.product.ProductDetailFragment;
import com.smyy.sharetour.buyer.home.detail.service.ProductDetailServiceFragment;
import com.smyy.sharetour.buyer.tim.ChatActivity;
import com.smyy.sharetour.buyer.ui.MainActivity;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
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
public class HomeDetailActivity extends AppCompatActivity implements View.OnClickListener {


    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"详情", "评价", "服务"};
    private HomeDetailFragmentAdapter mAdapter;
    private boolean isCollected;
    private int productNum;

    private ViewPager vpProductDetail;
    private SlidingTabLayout stlProductDetail;
    private ImageView ivProductDetailBack;
    private ImageView ivProductDetailCollect;
    private ImageView ivSearchDetailShare;
    private TextView tvProductDetailExpandBag;
    private TextView tvProductDetailCloseBag;
    private TextView tvProductDetailAddBag;
    private TextView tvProductDetailAddCart;
    private RedImageView ivProductDetailBag;
    private ImageView ivProductDetailComment;
    private LinearLayout llProductDetailCloseBag;
    private List<String> mHotDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView(R.layout.activity_home_detail);
        mFragments.add(ProductDetailFragment.getInstance(mTitles[0]));
        mFragments.add(ProductDetailCommentFragment.getInstance(mTitles[1]));
        mFragments.add(ProductDetailServiceFragment.getInstance(mTitles[2]));

        mAdapter = new HomeDetailFragmentAdapter(this.getSupportFragmentManager());
        vpProductDetail = (ViewPager) findViewById(R.id.vp_product_detail);
        stlProductDetail = (SlidingTabLayout) findViewById(R.id.stl_product_detail);
        ivProductDetailBack = (ImageView) findViewById(R.id.iv_product_detail_back);
        ivProductDetailCollect = (ImageView) findViewById(R.id.iv_product_detail_collect);
        ivSearchDetailShare = (ImageView) findViewById(R.id.iv_search_detail_share);
        ivProductDetailBag = (RedImageView) findViewById(R.id.iv_product_detail_bag);
        ivProductDetailComment = (ImageView) findViewById(R.id.iv_product_detail_comment);
        tvProductDetailExpandBag = (TextView) findViewById(R.id.tv_product_detail_expand_bag);
        tvProductDetailCloseBag = (TextView) findViewById(R.id.tv_product_detail_close_bag);
        tvProductDetailAddCart = (TextView) findViewById(R.id.tv_product_detail_add_cart);
        tvProductDetailAddBag = (TextView) findViewById(R.id.tv_product_detail_add_bag);
        llProductDetailCloseBag = (LinearLayout) findViewById(R.id.ll_product_detail_close_bag);
        vpProductDetail.setAdapter(mAdapter);
        stlProductDetail.setViewPager(vpProductDetail, mTitles);
        ivProductDetailBack.setOnClickListener(this);
        ivProductDetailCollect.setOnClickListener(this);
        ivSearchDetailShare.setOnClickListener(this);
        tvProductDetailExpandBag.setOnClickListener(this);
        tvProductDetailCloseBag.setOnClickListener(this);
        ivProductDetailBag.setOnClickListener(this);
        ivProductDetailComment.setOnClickListener(this);
        tvProductDetailAddBag.setOnClickListener(this);
        tvProductDetailAddCart.setOnClickListener(this);

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
//                ChatActivity.navToChat(this, "XMYY_XXR", TIMConversationType.C2C);
                ActivityLauncher.viewMessageListActivity(this);
                break;
            case R.id.tv_product_detail_add_cart:
                ProductConfirmOrderDialog productConfirmOrderDialog = new ProductConfirmOrderDialog();
                productConfirmOrderDialog.show(getSupportFragmentManager(), null);
                break;
            case R.id.tv_product_detail_add_bag:

                break;
            case R.id.home:
                break;
        }
    }


    /**
     * 设置轮播图
     */
    private void setBanner() {
        //设置图片加载集合
        List<Integer> imageArray = new ArrayList<>();
        imageArray.add(R.mipmap.home_new_sell);
        imageArray.add(R.mipmap.home_new_sell);
        imageArray.add(R.mipmap.home_new_sell);
        imageArray.add(R.mipmap.home_new_sell);
        imageArray.add(R.mipmap.home_new_sell);
        //设置banner样式
        Banner bannerProductDetail = (Banner) findViewById(R.id.banner_product_detail);
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
