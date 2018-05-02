package com.smyy.sharetour.buyer.dialog;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;

import java.util.List;

/**
 * Created by hasee on 2018/4/19.
 */

public class AreaRightAdapter extends BaseQuickAdapter<AreaBean.Region, BaseViewHolder> {

    public AreaRightAdapter(@Nullable List<AreaBean.Region> data) {
        super(R.layout.item_area_right, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaBean.Region item) {
        helper.setText(R.id.tv_area_right,item.getmRegion());
        if (item.isCheck()){
            ((TextView)helper.getView(R.id.tv_area_right)).setTextColor(mContext.getResources().getColor(R.color.txt_main));
        }else {
            ((TextView)helper.getView(R.id.tv_area_right)).setTextColor(mContext.getResources().getColor(R.color.txt_gray));
        }
    }

    public void setCheckItem() {
        for (int i = 0; i < getData().size(); i++) {
            getData().get(i).setCheck(false);
        }
    }
}
