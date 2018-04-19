package com.smyy.sharetour.buyer;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.bean.Test1Bean;
import com.smyy.sharetour.buyer.home.model.SearchBuyer;

import java.util.List;

/**
 * Created by hasee on 2018/4/19.
 */

public class RecommendBuyAdapter extends BaseQuickAdapter<SearchBuyer,BaseViewHolder> {
    public RecommendBuyAdapter(@Nullable List<SearchBuyer> data) {
        super(R.layout.item_general_buyer,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBuyer item) {

    }
}
