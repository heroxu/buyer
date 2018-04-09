package com.smyy.sharetour.buyer.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.bean.FImage;
import com.smyy.sharetour.buyer.R;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class FountImageAdapter extends BaseQuickAdapter<FImage> {
    public FountImageAdapter(List<FImage> datas) {
        super(R.layout.item_fount_notes_images, datas);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FImage bean) {
        Glide.with(mContext).load(bean.getImageId()).crossFade().into((ImageView) baseViewHolder.getView(R.id.iv_ifn_image));

    }
}
