package com.smyy.sharetour.buyer.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.Test1Bean;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class Test1Adapter extends BaseQuickAdapter<Test1Bean> {
    public Test1Adapter(List<Test1Bean> datas) {
        super(R.layout.item_test1, datas);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Test1Bean testBean) {
        baseViewHolder.setText(R.id.tv_item_name, testBean.getName())
                .setText(R.id.tv_item_address, testBean.getAddress());
        Glide.with(mContext).load(testBean.getPhoto()).crossFade().into((ImageView) baseViewHolder.getView(R.id.iv_item_photo));

    }
}
