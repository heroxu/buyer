package com.xmyy.view.dialoglib.base;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmyy.view.dialoglib.CommonDialog;

import java.util.List;


public abstract class CommonBaseAdapter<T> extends RecyclerView.Adapter<BindViewHolder> {

    private final int layoutRes;
    private List<T> datas;
    private OnAdapterItemClickListener adapterItemClickListener;
    private CommonDialog dialog;

    protected abstract void onBind(BindViewHolder holder, int position, T t);

    public CommonBaseAdapter(@LayoutRes int layoutRes, List<T> datas) {
        this.layoutRes = layoutRes;
        this.datas = datas;
    }

    @Override
    public BindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));
    }

    @Override
    public void onBindViewHolder(final BindViewHolder holder, final int position) {
        onBind(holder, position, datas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterItemClickListener.onItemClick(holder, position, datas.get(position), dialog);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setTDialog(CommonDialog commonDialog) {
        this.dialog = commonDialog;
    }

    public interface OnAdapterItemClickListener<T> {
        void onItemClick(BindViewHolder holder, int position, T t, CommonDialog commonDialog);
    }

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
        this.adapterItemClickListener = listener;
    }
}
