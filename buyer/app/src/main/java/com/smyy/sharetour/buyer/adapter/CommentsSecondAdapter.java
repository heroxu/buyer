package com.smyy.sharetour.buyer.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.CommentsBean;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/4/11 11:13
 * E-Mail Address：wuzf2012@sina.com
 */
public class CommentsSecondAdapter extends BaseMultiItemQuickAdapter<CommentsBean.CsList> {

    public CommentsSecondAdapter(List<CommentsBean.CsList> data) {
        super(data);
        addItemType(CommentsBean.SINGLE_CHAT, R.layout.item_comments_second_layer2);
        addItemType(CommentsBean.MORE_CHAT, R.layout.item_comments_second_layer1);
        addItemType(CommentsBean.MORE_REPLY, R.layout.item_comments_second_layer3);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CommentsBean.CsList csList) {
//        if (getData().)
    }
}
