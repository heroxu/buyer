package com.smyy.sharetour.buyer.home.comment.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.home.model.MyReceiveComment;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class MyReceiveLikeAdapter extends BaseQuickAdapter<MyReceiveComment, BaseViewHolder>{

    public MyReceiveLikeAdapter(@Nullable List<MyReceiveComment> data) {
        super(R.layout.item_my_receive_comment,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyReceiveComment item) {

    }

}
