package com.smyy.sharetour.buyer.backpacker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.RequireBean;

import java.util.ArrayList;
import java.util.List;


public class BackpackerHomeItemAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<RequireBean> list = new ArrayList<>();
    private OnBackpackerHomeItemOnClickListener mListener;


    public BackpackerHomeItemAdapter(Context context, List<RequireBean> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(inflater.inflate(R.layout.item_backpacke_home_list_layout, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RequireBean item = list.get(position);

        ((NormalViewHolder)holder).disc.setText(item.getRequire_disc());
        ((NormalViewHolder)holder).price.setText(context.getString(R.string.money_unit)+item.getRequire_budget());
        ((NormalViewHolder)holder).time.setText(item.getRequire_time()+context.getString(R.string.before_receive));
        /*if (item.getImages().get(0) == null){
            ((NormalViewHolder)holder).itemImg.setImageResource(R.mipmap.placeholder);
        } else {
            Glide.with(context)
                    .load(item.getImages().get(0))
                    .asBitmap()
                    .placeholder(R.mipmap.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(R.mipmap.placeholder)
                    .centerCrop()
                    .into(((NormalViewHolder)holder).itemImg);
        }
        ((NormalViewHolder)holder).tvLatestNewsTitle.setText(item.getTitle());*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setItemClickListener(OnBackpackerHomeItemOnClickListener listener){
        this.mListener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView disc;
        protected TextView time;
        protected TextView price;
        protected OnBackpackerHomeItemOnClickListener listener;

        public NormalViewHolder(View itemView, final OnBackpackerHomeItemOnClickListener listener) {
            super(itemView);
            disc = (TextView) itemView.findViewById(R.id.backpacker_item_disc);
            price = (TextView) itemView.findViewById(R.id.backpacker_item_price);
            time = (TextView) itemView.findViewById(R.id.backpacker_item_time);
            this.listener = listener;
            itemView.setOnClickListener(this);
            TextView contact = (TextView) itemView.findViewById(R.id.backpacker_item_contact);
            TextView take = (TextView) itemView.findViewById(R.id.backpacker_item_catch);

            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemContactClick(v, getLayoutPosition());
                }
            });

            take.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemTakeClick(v, getLayoutPosition());
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

    public interface OnBackpackerHomeItemOnClickListener{

        void OnItemClick(View v, int position);

        void OnItemContactClick(View v, int position);

        void OnItemTakeClick(View v, int position);
    }

}
