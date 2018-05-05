package com.smyy.sharetour.buyer.home.comment.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.FountImageAdapter;
import com.smyy.sharetour.buyer.bean.FountBean;
import com.smyy.sharetour.buyer.home.model.MySendComment;
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
public class MySendLikeAdapter extends BaseMultiItemQuickAdapter<FountBean,BaseViewHolder> {

    public MySendLikeAdapter(@Nullable List<FountBean> data) {
        super(data);
        addItemType(FountBean.VIDEO_TYPE, R.layout.item_general_video);
        addItemType(FountBean.NOTES_TYPE, R.layout.item_general_notes);
    }

    @Override
    protected void convert(BaseViewHolder helper, FountBean item) {
        switch (helper.getItemViewType()) {
            case FountBean.VIDEO_TYPE:
//                helper.setText(R.id.tv, item.getContent());
                helper.setImageResource(R.id.iv_ifv_like, R.mipmap.ic_like_ele);
                break;
            case FountBean.NOTES_TYPE:
                ((RecyclerView) helper.getView(R.id.rv_if_images)).setLayoutManager(new GridLayoutManager(mContext, 3));
                ((RecyclerView) helper.getView(R.id.rv_if_images)).setAdapter(new FountImageAdapter(item.getFountIamges()));
                helper.setImageResource(R.id.iv_ifn_like, R.mipmap.ic_like_ele);
                break;
        }
    }

}
