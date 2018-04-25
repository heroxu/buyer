package com.smyy.sharetour.buyer.backpacker.bomepage;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class BackpackHomePageAdapter extends BaseQuickAdapter<BackpackHomePageBean,BaseViewHolder> {

    public BackpackHomePageAdapter(@Nullable List<BackpackHomePageBean> data) {
        super(R.layout.item_backpack_comments,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BackpackHomePageBean item) {

    }
}
