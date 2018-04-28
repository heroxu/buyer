package com.smyy.sharetour.buyer.travel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;
import com.yongchun.library.utils.ScreenUtils;

import java.util.List;


public class SellerGoodItemAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<String> list;
    private OnRecyclerViewOnClickListener mListener;


    public SellerGoodItemAdapter(Context context, List<String> list){
        this.context = context;
        this.list =list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(inflater.inflate(R.layout.item_seller_good_layout, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position==0){
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ScreenUtils.dip2px(context, 277), LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(ScreenUtils.dip2px(context, 20), 0,
                    ScreenUtils.dip2px(context, 20), 0);
            ((NormalViewHolder)holder).good_view.setLayoutParams(lp);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setItemClickListener(OnRecyclerViewOnClickListener listener){
        this.mListener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        OnRecyclerViewOnClickListener listener;
        RelativeLayout good_view;

        public NormalViewHolder(final View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            good_view = (RelativeLayout) itemView.findViewById(R.id.good_item);
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

}
