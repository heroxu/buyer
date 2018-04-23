package com.smyy.sharetour.buyer.ui.SmallBackpack;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.view.SwipeItemLayout;

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
                mRecyclerViewGoods.setFocusable(false);
                mRecyclerViewGoods.setLayoutManager(new LinearLayoutManager(mContext));
                final List<SmallBackpackBean.GoodsBean> data = item.getmGoodsBeans();
                mRecyclerViewGoods.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(mContext));
                final GoodsAdapter mGoodsAdapter = new GoodsAdapter(data);
                mRecyclerViewGoods.setAdapter(mGoodsAdapter);
                ((CheckBox) helper.getView(R.id.cb_goods_all)).setChecked(item.isSelect());
                //给CheckBox设置事件监听
                ((CheckBox) helper.getView(R.id.cb_goods_all)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setSelect(isChecked);
                        }
                        mGoodsAdapter.notifyDataSetChanged();
                    }
                });
                break;
            case SmallBackpackBean.GOODS_FAILURE_TYPE:
                RecyclerView mRecyclerViewGoodsFailure = helper.getView(R.id.recycler_view);
                mRecyclerViewGoodsFailure.setFocusable(false);
                mRecyclerViewGoodsFailure.setLayoutManager(new LinearLayoutManager(mContext));
                mRecyclerViewGoodsFailure.setAdapter(new GoodsFailureAdapter(item.getmGoodsFailureBeans()));
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
            super(R.layout.item_small_backpack_goods_child, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SmallBackpackBean.GoodsBean item) {
            CheckBox mCheckBox = helper.getView(R.id.cb_goods);
            mCheckBox.setChecked(item.isSelect());
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
            super(R.layout.item_small_backpack_goods_failure_child, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SmallBackpackBean.GoodsFailureBean item) {

        }
    }
}
