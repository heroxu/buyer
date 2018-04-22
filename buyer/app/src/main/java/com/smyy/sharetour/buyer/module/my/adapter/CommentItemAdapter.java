package com.smyy.sharetour.buyer.module.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.CommentsBean;

import java.util.ArrayList;
import java.util.List;


public class CommentItemAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<CommentsBean> list = new ArrayList<>();
    private OnCommentItemOnClickListener mListener;

    public CommentItemAdapter(Context context, List<CommentsBean> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(inflater.inflate(R.layout.item_my_comment_list_layout, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setItemClickListener(OnCommentItemOnClickListener listener){
        this.mListener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected OnCommentItemOnClickListener listener;

        public NormalViewHolder(View itemView, final OnCommentItemOnClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            TextView append = (TextView) itemView.findViewById(R.id.append_comment);
            append.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemAppendClick(v, getLayoutPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.OnItemClick(v, getLayoutPosition());
            }
        }
    }

    public interface OnCommentItemOnClickListener{

        void OnItemClick(View v, int position);

        void OnItemAppendClick(View v, int position);

    }

}
