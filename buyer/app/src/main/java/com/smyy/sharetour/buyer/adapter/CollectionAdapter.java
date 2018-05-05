package com.smyy.sharetour.buyer.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.bean.CollectionBean;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.fragment.CollectionFragment;
import com.smyy.sharetour.buyer.util.PackageUtils;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class CollectionAdapter extends BaseMultiItemQuickAdapter<CollectionBean, BaseViewHolder> {
    private boolean isFirst;
    public CollectionAdapter(List data) {
        super(data);
        addItemType(CollectionBean.GOODS_TYPE, R.layout.item_collection_goods_sl);
        addItemType(CollectionBean.VIDEO_TYPE, R.layout.item_collection_video_sl);
        addItemType(CollectionBean.NOTES_TYPE, R.layout.item_collection_notes_sl);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CollectionBean item) {
        LinearLayout layout = helper.getView(R.id.ll_dynamic);
        if (item.isVisible()) {
            helper.getView(R.id.check_box).setVisibility(View.VISIBLE);
            PackageUtils.setMargins(layout, 0, 0, -60, 0);
            isFirst = true;
        } else {
            helper.getView(R.id.check_box).setVisibility(View.GONE);
            if (isFirst) {
                PackageUtils.setMargins(layout, 0, 0, 0, 0);
            }
        }
        final CheckBox cbSelect = (CheckBox) helper.getView(R.id.check_box);
        cbSelect.setChecked(item.getIsSelect() == CollectionFragment.SELECT_TRUE ? true : false);
        switch (helper.getItemViewType()) {
            case CollectionBean.GOODS_TYPE:
                helper.getView(R.id.line_search_product).setVisibility(View.GONE);
                break;
            case CollectionBean.VIDEO_TYPE:
                helper.getView(R.id.rl_general_video_title).setVisibility(View.GONE);
                break;
            case CollectionBean.NOTES_TYPE:
                helper.getView(R.id.rl_general_notes_title).setVisibility(View.GONE);
                break;
        }
    }
}
