package com.smyy.sharetour.buyer;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.bean.CollectionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class SmallBackpackAdapter extends BaseMultiItemQuickAdapter<SmallBackpackBean, BaseViewHolder> {
    public SmallBackpackAdapter(List data) {
        super(data);
        addItemType(SmallBackpackBean.GOODS_TYPE, R.layout.item_small_backpack_goods);
        addItemType(SmallBackpackBean.GOODS_FAILURE_TYPE, R.layout.item_small_backpack_goods_failure);
    }

    @Override
    protected void convert(BaseViewHolder helper, SmallBackpackBean item) {
        switch (helper.getItemViewType()) {
            case SmallBackpackBean.GOODS_TYPE:
                RecyclerView mRecyclerViewGoods = helper.getView(R.id.recycler_view);
                mRecyclerViewGoods.setLayoutManager(new LinearLayoutManager(mContext));
                List<SmallBackpackBean.GoodsBean> data1 = new ArrayList<>();
                data1.add(new SmallBackpackBean.GoodsBean());
                data1.add(new SmallBackpackBean.GoodsBean());
                data1.add(new SmallBackpackBean.GoodsBean());
                mRecyclerViewGoods.setAdapter(new GoodsAdapter(data1));
                break;
            case SmallBackpackBean.GOODS_FAILURE_TYPE:
                RecyclerView mRecyclerViewGoodsFailure = helper.getView(R.id.recycler_view);
                mRecyclerViewGoodsFailure.setLayoutManager(new LinearLayoutManager(mContext));
                List<SmallBackpackBean.GoodsFailureBean> data2 = new ArrayList<>();
                data2.add(new SmallBackpackBean.GoodsFailureBean());
                data2.add(new SmallBackpackBean.GoodsFailureBean());
                data2.add(new SmallBackpackBean.GoodsFailureBean());
                data2.add(new SmallBackpackBean.GoodsFailureBean());
                data2.add(new SmallBackpackBean.GoodsFailureBean());
                mRecyclerViewGoodsFailure.setAdapter(new GoodsFailureAdapter(data2));
                break;
        }
    }
    public class GoodsAdapter extends BaseQuickAdapter<SmallBackpackBean.GoodsBean, BaseViewHolder> {

        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data A new list is created out of this one to avoid mutable list
         */
        public GoodsAdapter(List<SmallBackpackBean.GoodsBean> data) {
            super(R.layout.item_small_backpack_goods_child,data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SmallBackpackBean.GoodsBean item) {

        }
    }
    public class GoodsFailureAdapter extends BaseQuickAdapter<SmallBackpackBean.GoodsFailureBean, BaseViewHolder> {

        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data A new list is created out of this one to avoid mutable list
         */
        public GoodsFailureAdapter(List<SmallBackpackBean.GoodsFailureBean> data) {
            super(R.layout.item_small_backpack_goods_failure_child,data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SmallBackpackBean.GoodsFailureBean item) {

        }
    }
}
