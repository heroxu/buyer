package com.smyy.sharetour.buyer.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.bean.CollectionBean;
import com.smyy.sharetour.buyer.R;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class CollectionAdapter extends BaseMultiItemQuickAdapter<CollectionBean, BaseViewHolder> {
    public CollectionAdapter(List data) {
        super(data);
        addItemType(CollectionBean.GOODS_TYPE, R.layout.item_collection_goods_sl);
        addItemType(CollectionBean.VIDEO_TYPE, R.layout.item_collection_video_sl);
        addItemType(CollectionBean.NOTES_TYPE, R.layout.item_collection_notes_sl);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionBean item) {
        switch (helper.getItemViewType()) {
            case CollectionBean.GOODS_TYPE:
                helper.getView(R.id.line_search_product).setVisibility(View.GONE);
                break;
            case CollectionBean.VIDEO_TYPE:
                helper.getView(R.id.rl_general_video_title).setVisibility(View.GONE);
                break;
            case CollectionBean.NOTES_TYPE:
                helper.getView(R.id.rl_general_notes_title).setVisibility(View.GONE);
//                ((RecyclerView) helper.getView(R.id.rv_if_images)).setLayoutManager(new GridLayoutManager(mContext, 3));
//                ((RecyclerView) helper.getView(R.id.rv_if_images)).setAdapter(new FountImageAdapter(item.getFountIamges()));
                break;
        }
    }
}
