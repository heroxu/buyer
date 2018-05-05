package com.smyy.sharetour.buyer.home.search.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.home.search.SearchBuyVideoBean;

import java.util.List;

/**
 * Created by hasee on 2018/4/19.
 */

public class SearchVideoAdapter extends BaseQuickAdapter<SearchBuyVideoBean,BaseViewHolder> {
    public SearchVideoAdapter(@Nullable List<SearchBuyVideoBean> data) {
        super(R.layout.item_general_video,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBuyVideoBean item) {
        helper.getView(R.id.rl_general_video_title).setVisibility(View.GONE);
        helper.getView(R.id.tv_video_time).setVisibility(View.VISIBLE);
    }
}
