package com.smyy.sharetour.buyer.ui.SmallBackpack;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.view.AmountView;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.util.LogUtil;
import com.smyy.sharetour.buyer.util.ToastUtils;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

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
                final GoodsAdapter mGoodsAdapter = new GoodsAdapter(data);
                mRecyclerViewGoods.setAdapter(mGoodsAdapter);
                final CheckBox cbSelect = helper.getView(R.id.cb_goods_all);
                cbSelect.setChecked(item.getIsSelect() == SmallBackpackActivity.SELECT_TRUE ? true : false);
                mGoodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.cb_goods:
                                mActivity.changeChildSelectStatus(item.getmGoodsBeans(), mGoodsAdapter, cbSelect, parentPosition, position);
                                break;
//                            case R.id.ll_edit_itbgc:
//                                helper.getView(R.id.ll_goods_message).setVisibility(View.GONE);
//                                helper.getView(R.id.ll_goods_edit).setVisibility(View.VISIBLE);
//                                break;
//                            case R.id.tv_smb_edit_finish:
//                                helper.getView(R.id.ll_goods_edit).setVisibility(View.GONE);
//                                helper.getView(R.id.ll_goods_message).setVisibility(View.VISIBLE);
//                                break;
                        }
                    }
                });
                mGoodsAdapter.setOnItemChildLongClickListener(new OnItemChildLongClickListener() {
                    @Override
                    public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.ll_btn_itbgc:
                                showCollectionDialog(mActivity, R.layout.item_s_backpack_long, new OnOptionConfirmListener() {
                                    @Override
                                    public void onDelete() {

                                    }

                                    @Override
                                    public void onCollection() {

                                    }
                                });
                                break;
                        }
                        return true;
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
            super(R.layout.item_small_backpack_goods_child_content, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, SmallBackpackBean.GoodsBean item) {
            AmountView mAmountView = helper.getView(R.id.amount_view);
            mAmountView.setGoods_storage(50);
            mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                }
            });
            helper.addOnClickListener(R.id.cb_goods).addOnClickListener(R.id.ll_edit_itbgc).addOnClickListener(R.id.tv_smb_edit_finish);
            helper.getView(R.id.ll_edit_itbgc).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper.getView(R.id.ll_goods_message).setVisibility(View.GONE);
                    helper.getView(R.id.ll_goods_edit).setVisibility(View.VISIBLE);
                }
            });
            helper.getView(R.id.tv_smb_edit_finish).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.e("WZF", helper.getView(R.id.tv_smb_edit_finish).getHeight() + "---");
                    helper.getView(R.id.ll_goods_edit).setVisibility(View.GONE);
                    helper.getView(R.id.ll_goods_message).setVisibility(View.VISIBLE);
                }
            });
            helper.addOnLongClickListener(R.id.ll_btn_itbgc);
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


    public static void showCollectionDialog(FragmentActivity activity, int dialogRes, final OnOptionConfirmListener onOptionConfirmListener) {
        new CommonDialog.Builder(activity.getSupportFragmentManager())
                .setLayoutRes(dialogRes)
                .setGravity(Gravity.CENTER)
                .setDimAmount(0.5f)
                .setScreenWidthAspect(activity, 1)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder, CommonDialog dialog) {
                        viewHolder.setOnViewClickListener(R.id.tv_long_collection, new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                commonDialog.dismiss();
                                if (onOptionConfirmListener != null) {
                                }
                            }
                        });
                        viewHolder.setOnViewClickListener(R.id.tv_long_delete, new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                commonDialog.dismiss();
                                if (onOptionConfirmListener != null) {
                                }
                            }
                        });
                    }
                })
                .create().show();
    }

    public interface OnOptionConfirmListener {
        void onDelete();

        void onCollection();
    }
}
