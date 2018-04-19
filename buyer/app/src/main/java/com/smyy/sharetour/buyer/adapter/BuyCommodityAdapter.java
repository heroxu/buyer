package com.smyy.sharetour.buyer.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.BuyCommodityBean;
import com.smyy.sharetour.buyer.bean.Test1Bean;

import java.util.List;

/**
 * Created by hasee on 2018/4/19.
 */

public class BuyCommodityAdapter extends BaseQuickAdapter<BuyCommodityBean,BaseViewHolder> {
    public BuyCommodityAdapter(@Nullable List<BuyCommodityBean> data) {
        super(R.layout.item_home_child_hot_product,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BuyCommodityBean item) {

    }
}
