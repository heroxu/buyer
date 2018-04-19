package com.smyy.sharetour.buyer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.BuyCommodityBean;
import com.smyy.sharetour.buyer.bean.BuyVideoBean;

import java.util.List;

/**
 * Created by hasee on 2018/4/19.
 */

public class BuyVideoAdapter extends BaseQuickAdapter<BuyVideoBean,BaseViewHolder> {
    public BuyVideoAdapter(@Nullable List<BuyVideoBean> data) {
        super(R.layout.item_buy_video,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BuyVideoBean item) {

    }
}
