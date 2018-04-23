package com.smyy.sharetour.buyer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.bean.ConfirmOrderBean;
import com.smyy.sharetour.buyer.R;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class ConfirmOrderAdapter extends BaseQuickAdapter<ConfirmOrderBean, BaseViewHolder>{

    public ConfirmOrderAdapter(@Nullable List<ConfirmOrderBean> data) {
        super(R.layout.item_confirm_order,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ConfirmOrderBean item) {

    }
}
