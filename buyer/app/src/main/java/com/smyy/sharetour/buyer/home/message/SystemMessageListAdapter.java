package com.smyy.sharetour.buyer.home.message;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.CommentsBean;
import com.smyy.sharetour.buyer.home.model.SystemMessageList;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/4/11 11:13
 * E-Mail Address：wuzf2012@sina.com
 */
public class SystemMessageListAdapter extends BaseQuickAdapter<SystemMessageList,BaseViewHolder> {

    public SystemMessageListAdapter(List<SystemMessageList> data) {
        super(R.layout.item_message_system_list,data);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SystemMessageList item) {

    }
}
