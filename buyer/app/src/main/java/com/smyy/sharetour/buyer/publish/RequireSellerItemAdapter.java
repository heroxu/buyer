package com.smyy.sharetour.buyer.publish;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.home.model.SearchBuyer;

import java.util.ArrayList;
import java.util.List;


public class RequireSellerItemAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<SearchBuyer> list = new ArrayList<>();
    private OnRequireSellerOnClickListener mListener;


    public RequireSellerItemAdapter(Context context, List<SearchBuyer> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(inflater.inflate(R.layout.item_require_seller_list_layout, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setItemClickListener(OnRequireSellerOnClickListener listener){
        this.mListener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected OnRecyclerViewOnClickListener listener;

        public NormalViewHolder(View itemView, final OnRequireSellerOnClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);

            TextView contact = (TextView) itemView.findViewById(R.id.require_item_contact);
            TextView point = (TextView) itemView.findViewById(R.id.require_item_point);

            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemContactClick(v, getLayoutPosition());
                }
            });

            point.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemPointClick(v, getLayoutPosition());
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

}
