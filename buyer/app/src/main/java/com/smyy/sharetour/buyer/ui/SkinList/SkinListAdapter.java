package com.smyy.sharetour.buyer.ui.SkinList;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.ui.SkinList.SkinListBean;

import java.util.List;

/**
 * Created by hasee on 2018/4/19.
 */

public class SkinListAdapter extends BaseQuickAdapter<SkinListBean, BaseViewHolder> {
    public SkinListAdapter(@Nullable List<SkinListBean> data) {
        super(R.layout.item_skin_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SkinListBean item) {

    }
}
