package com.smyy.sharetour.buyer.home.search.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.home.search.SearchBuyNotesBean;

import java.util.List;

/**
 * Created by hasee on 2018/4/19.
 */

public class SearchNotesAdapter extends BaseQuickAdapter<SearchBuyNotesBean, BaseViewHolder> {
    public SearchNotesAdapter(@Nullable List<SearchBuyNotesBean> data) {
        super(R.layout.item_general_notes, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBuyNotesBean item) {
        helper.getView(R.id.rl_general_notes_title).setVisibility(View.GONE);
        helper.getView(R.id.tv_note_time).setVisibility(View.VISIBLE);
    }
}
