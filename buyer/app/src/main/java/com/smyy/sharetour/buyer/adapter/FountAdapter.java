package com.smyy.sharetour.buyer.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.bean.FountBean;
import com.smyy.sharetour.buyer.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class FountAdapter extends BaseMultiItemQuickAdapter<FountBean,BaseViewHolder> {
    public FountAdapter(Context context, List data) {
        super(data);
        addItemType(FountBean.VIDEO_TYPE, R.layout.item_fount_video);
        addItemType(FountBean.NOTES_TYPE, R.layout.item_fount_notes);
        addItemType(FountBean.BANNER_TYPE, R.layout.item_fount_banner);
    }

    @Override
    protected void convert(BaseViewHolder helper, FountBean item) {
        switch (helper.getItemViewType()) {
            case FountBean.VIDEO_TYPE:
//                helper.setText(R.id.tv, item.getContent());
                break;
            case FountBean.NOTES_TYPE:
                ((RecyclerView) helper.getView(R.id.rv_if_images)).setLayoutManager(new GridLayoutManager(mContext, 3));
                ((RecyclerView) helper.getView(R.id.rv_if_images)).setAdapter(new FountImageAdapter(item.getFountIamges()));
                break;
            case FountBean.BANNER_TYPE:
                Banner mBanner = helper.getView(R.id.fount_banner);
                //设置图片加载集合
                List<Integer> imageArray = new ArrayList<>();
                imageArray.add(R.mipmap.carousel_01);
                imageArray.add(R.mipmap.carousel_01);
                imageArray.add(R.mipmap.carousel_01);
                imageArray.add(R.mipmap.carousel_01);
                imageArray.add(R.mipmap.carousel_01);
                //设置banner样式
                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                mBanner.setImageLoader(new ImageLoader() {

                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        //Glide 加载图片简单用法
                        Glide.with(context).load(path).into(imageView);
                    }
                });
                //设置图片集合
                mBanner.setImages(imageArray);
                //设置banner动画效果
                mBanner.setBannerAnimation(Transformer.Default);
                //设置轮播时间
                mBanner.setDelayTime(5000);
                mBanner.start();
                mBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
//                    ToastUtils.showToast(R.string.feature_developing);
                    }
                });
                break;
        }
    }
}
