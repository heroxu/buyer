package com.smyy.sharetour.buyer.home.message;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.ConfirmOrderBean;
import com.smyy.sharetour.buyer.home.model.MessageList;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class MessageListAdapter extends BaseQuickAdapter<MessageList, BaseViewHolder>{

    public MessageListAdapter(@Nullable List<MessageList> data) {
        super(R.layout.item_message_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageList item) {

    }

}
