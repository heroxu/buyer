package com.smyy.sharetour.buyer.ui.SmallBackpack;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.util.LogUtil;
import com.smyy.sharetour.buyer.util.ToastUtils;
import com.smyy.sharetour.buyer.view.SwipeItemLayout;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:43
 * E-Mail Address：wuzf2012@sina.com
 */
public class SmallBackpackAdapter extends BaseMultiItemQuickAdapter<SmallBackpackBean, BaseViewHolder> {
    SmallBackpackActivity mActivity;

    public SmallBackpackAdapter(List data, SmallBackpackActivity context) {
        super(data);
        addItemType(SmallBackpackBean.GOODS_TYPE, R.layout.item_small_backpack_goods);
        addItemType(SmallBackpackBean.GOODS_FAILURE_TYPE, R.layout.item_small_backpack_goods_failure);
        mActivity = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SmallBackpackBean item) {
        final int parentPosition = getData().indexOf(item);
        switch (helper.getItemViewType()) {
            case SmallBackpackBean.GOODS_TYPE:
                RecyclerView mRecyclerViewGoods = helper.getView(R.id.recycler_view);
                mRecyclerViewGoods.setLayoutManager(new LinearLayoutManager(mContext));
                final List<SmallBackpackBean.GoodsBean> data = item.getmGoodsBeans();
//                mRecyclerViewGoods.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(mContext));
                final GoodsAdapter mGoodsAdapter = new GoodsAdapter(data);
                mRecyclerViewGoods.setAdapter(mGoodsAdapter);
                final CheckBox cbSelect = helper.getView(R.id.cb_goods_all);
                cbSelect.setChecked(item.getIsSelect() == SmallBackpackActivity.SELECT_TRUE ? true : false);
                mGoodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.ll_btn_itbgc:
                                LogUtil.e("WZF",parentPosition+"-----");
                                mActivity.changeChildSelectStatus(item.getmGoodsBeans(), mGoodsAdapter, cbSelect, parentPosition, position);
                                break;
                            case R.id.ll_edit_itbgc:
                                ToastUtils.showToast("编辑状态");
                                break;
                        }
                    }
                });
                break;
            case SmallBackpackBean.GOODS_FAILURE_TYPE:
//                helper.addOnClickListener(R.id.tv_clean_goods);
                RecyclerView mRecyclerViewGoodsFailure = helper.getView(R.id.recycler_view);
                mRecyclerViewGoodsFailure.setLayoutManager(new LinearLayoutManager(mContext));
                mRecyclerViewGoodsFailure.setAdapter(new GoodsFailureAdapter(item.getmGoodsFailureBeans()));
                helper.getView(R.id.tv_clean_goods).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast("清空啦");
                    }
                });
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
            helper.addOnClickListener(R.id.ll_btn_itbgc).addOnClickListener(R.id.ll_edit_itbgc);
            CheckBox mCheckBox = helper.getView(R.id.cb_goods);
            mCheckBox.setChecked(item.getIsSelect() == SmallBackpackActivity.SELECT_TRUE ? true : false);
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
