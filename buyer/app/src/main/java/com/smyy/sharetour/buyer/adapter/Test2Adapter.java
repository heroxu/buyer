package com.smyy.sharetour.buyer.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.ui.test2.Test2JsonData;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class Test2Adapter extends BaseQuickAdapter<Test2JsonData.Result,BaseViewHolder> {
    public Test2Adapter(List<Test2JsonData.Result> datas) {
        super(R.layout.item_test2, datas);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Test2JsonData.Result result) {
        baseViewHolder.setText(R.id.tv_test2_title,result.getContent());
        Glide.with(mContext).load(result.getUrl()).crossFade().into((ImageView) baseViewHolder.getView(R.id.iv_comments_first_photo));
    }
}
