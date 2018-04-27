package com.smyy.sharetour.buyer.home.comment.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.home.model.MyReceiveComment;
import com.smyy.sharetour.buyer.home.model.MySendComment;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class MySendCommentAdapter extends BaseQuickAdapter<MySendComment, BaseViewHolder>{

    public MySendCommentAdapter(@Nullable List<MySendComment> data) {
        super(R.layout.item_my_send_comment,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MySendComment item) {

    }

}
