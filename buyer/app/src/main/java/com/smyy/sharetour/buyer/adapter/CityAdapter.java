package com.smyy.sharetour.buyer.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.bean.CityBean;
import com.smyy.sharetour.buyer.R;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/4/9 13:54
 * E-Mail Address：wuzf2012@sina.com
 */
public class CityAdapter extends BaseMultiItemQuickAdapter<CityBean,BaseViewHolder> {
    public CityAdapter(Context context, List data) {
        super(data);
        addItemType(0, R.layout.item_city);
        addItemType(1, R.layout.item_pinned_header);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityBean city) {
        switch (helper.getItemViewType()) {
            case 0:
                helper.setText(R.id.city_name, city.name);
                break;
            case 1:
                helper.setText(R.id.city_tip, city.pys.substring(0, 1));
                break;
        }
    }

    public int getLetterPosition(String letter) {
        List<CityBean> data = getData();
        for (int i = 0; i < getData().size(); i++) {
            if (data.get(i).type == 1 && data.get(i).pys.equals(letter)) {
                return i;
            }
        }
        return -1;
    }
}
