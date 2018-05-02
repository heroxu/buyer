package com.smyy.sharetour.buyer.dialog;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;

import java.util.List;

/**
 * Created by hasee on 2018/4/19.
 */

public class AreaLeftAdapter extends BaseQuickAdapter<AreaBean, BaseViewHolder> {

    public AreaLeftAdapter(@Nullable List<AreaBean> data) {
        super(R.layout.item_area_left, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaBean item) {
        helper.setText(R.id.tv_area_left, item.getCountries());
        if (item.isCheck()){
            helper.getView(R.id.tv_area_left).setBackgroundColor(Color.WHITE);
            ((TextView)helper.getView(R.id.tv_area_left)).setTextColor(mContext.getResources().getColor(R.color.txt_main));
        }else {
            helper.getView(R.id.tv_area_left).setBackgroundColor(mContext.getResources().getColor(R.color.tag_bg));
            ((TextView)helper.getView(R.id.tv_area_left)).setTextColor(mContext.getResources().getColor(R.color.txt_gray));
        }
        item.setCheck(false);
    }
}
