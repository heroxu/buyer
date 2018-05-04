package com.smyy.sharetour.buyer.module.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;


public class OrderCommentPicItemAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<String> list = new ArrayList<>();
    private OnRecyclerViewOnClickListener mListener;

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_INSERT = 1;

    public OrderCommentPicItemAdapter(Context context, List<String> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_NORMAL:
                return new NormalViewHolder(inflater.inflate(R.layout.item_publish_grid, parent, false), mListener);
            case TYPE_INSERT:
                return new InsertViewHolder(inflater.inflate(R.layout.item_publish_grid_add, parent, false), mListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(list.size()!=0 && (list.size()== Consts.COMMENT_SELECT_PICTURE_NUM||position!=list.size())) {
            Glide.with(context)
                    .load(list.get(position))
                    .placeholder(R.mipmap.default_error)
                    .error(R.mipmap.default_error)
                    .centerCrop()
                    .crossFade()
                    .into(((NormalViewHolder) holder).picture);
        }
    }

    @Override
    public int getItemCount() {
        if(list.size()!= Consts.COMMENT_SELECT_PICTURE_NUM) {
            return list.size() + 1;
        } else
        {
            return list.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(list.size()!= Consts.COMMENT_SELECT_PICTURE_NUM&&position==list.size()){
            return TYPE_INSERT;
        }
        return TYPE_NORMAL;
    }

    public void setItemClickListener(OnRecyclerViewOnClickListener listener){
        this.mListener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView picture;
        protected ImageView del;

        protected OnRecyclerViewOnClickListener listener;
        public NormalViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            this.listener = listener;

            picture = (ImageView) itemView.findViewById(R.id.imageView);
            del = (ImageView) itemView.findViewById(R.id.del_pic);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onDeleteClickListener!=null) {
                        onDeleteClickListener.onDelClick(getLayoutPosition());
                    }
                }
            });
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }

    public class InsertViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected OnRecyclerViewOnClickListener listener;

        public InsertViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }

    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDelClick(int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }
}
