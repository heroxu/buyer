package com.smyy.sharetour.buyer.base.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by justin on 17/9/18.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener  {

    private IViewHolderListener mListener;

    public BaseViewHolder(View itemView, IViewHolderListener listener) {
        this(itemView, listener, false);
    }

    public BaseViewHolder(View itemView, IViewHolderListener listener, boolean needClick) {
        super(itemView);
        if (listener != null || needClick) {
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener!= null) {
            mListener.holderOnClickListener(v, this, getLayoutPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mListener!= null) {
            mListener.holderOnLongClickListener(v, this, getLayoutPosition());
            return true;
        }
        return false;
    }


    /**
     * Created by justin on 17/9/18.
     */
    public interface IViewHolderListener<T extends BaseViewHolder> {

        void holderOnClickListener(View v, T holder, int position);

        void holderOnLongClickListener(View v, T holder, int position);

    }

}
