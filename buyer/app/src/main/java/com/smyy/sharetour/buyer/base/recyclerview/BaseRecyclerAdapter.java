package com.smyy.sharetour.buyer.base.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by justin on 17/9/18.
 * base for RecyclerAdapter
 */
public abstract class BaseRecyclerAdapter<VH extends BaseViewHolder, T> extends RecyclerView.Adapter<VH> {

    private List<T> mData;

    public final T getItem(int position) {
        if (mData != null && position > -1 && position < mData.size()) {
            return mData.get(position);
        }
        return null;
    }

    public final void setData(List<T> data) {
        clearData();
        mData = data;
        notifyDataSetChanged();
    }

    public final List<T> getData(){
        return mData;
    }

    public final void addData(List<T> more) {
        addData(false, more);
    }

    public final void addData(boolean isTop, List<T> data) {
        if (data == null) {
            notifyDataSetChanged();
            return;
        }
        if (mData == null) {
            setData(data);
        } else {
            beforeAddData(data);
            if (isTop) {
                mData.addAll(0, data);
            } else {
                mData.addAll(data);
            }
            notifyDataSetChanged();
        }
    }

    protected boolean remove(T obj) {
        if (mData != null) {
            return mData.remove(obj);
        }
        return false;
    }

    protected T remove(int position) {
        if (mData != null) {
            return mData.remove(position);
        }
        return null;
    }

    public final void clearData() {
        if (mData != null) {
            mData.clear();
            mData = null;
        }
    }

    /** 在add前处理数据（如：去重等） **/
    protected void beforeAddData(List<T> data) {
    }

    public int getDataSize() {
        return mData == null ? 0 : mData.size();
    }

    public int getRealDataSize() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public final int getItemCount() {
        return getDataSize();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(getItemLayoutId(viewType), parent, false);
        return obtainViewHolder(v, viewType);
    }

    public abstract int getItemLayoutId(int viewType);
    public abstract VH obtainViewHolder(View v, int viewType);

    protected BaseViewHolder.IViewHolderListener mListener;

    public void setIViewHolderListener(BaseViewHolder.IViewHolderListener listener) {
        mListener = listener;
    }

    /** recyclerview 动画效果删除 **/
    public void deleteItemForRecycler(int position, int realPos) {
        remove(realPos);
        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, getItemCount());
    }
}
