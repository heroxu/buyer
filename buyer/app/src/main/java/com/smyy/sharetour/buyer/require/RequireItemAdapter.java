package com.smyy.sharetour.buyer.require;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.RequireBean;

import java.util.ArrayList;
import java.util.List;


public class RequireItemAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<RequireBean> list = new ArrayList<RequireBean>();
    private OnRecyclerViewOnClickListener mListener;

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_OUT_STOCK = 1;

    public RequireItemAdapter(Context context, List<RequireBean> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_NORMAL:
                return new NormalViewHolder(inflater.inflate(R.layout.item_require_list_layout, parent, false), mListener);
            case TYPE_OUT_STOCK:
                return new OutViewHolder(inflater.inflate(R.layout.item_require_list_layout, parent, false),
                        (OnRequireOutOnClickListener) mListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RequireBean item = list.get(position);

        ((NormalViewHolder)holder).disc.setText(item.getRequire_disc());
        ((NormalViewHolder)holder).price.setText(context.getString(R.string.money_unit)+item.getRequire_budget());
        ((NormalViewHolder)holder).time.setText(item.getRequire_time()+context.getString(R.string.before_receive));
        ((NormalViewHolder)holder).state.setText(Consts.REQUIRE_STATE_STRINGS[item.getState()]);
        if(item.getState()==Consts.REQUIRE_STATE_WAIT_POINT){
            ((NormalViewHolder)holder).state.setTextColor(context.getResources().getColor(R.color.txt_price));
        }
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

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getState()==Consts.REQUIRE_STATE_CANCEL||list.get(position).getState()==Consts.REQUIRE_STATE_INVALID) {
            return RequireItemAdapter.TYPE_OUT_STOCK;
        }
        return RequireItemAdapter.TYPE_NORMAL;
    }

    public void setItemClickListener(OnRecyclerViewOnClickListener listener){
        this.mListener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView head;
        protected ImageView image;
        protected TextView disc;
        protected TextView name;
        protected TextView time;
        protected TextView state;
        protected TextView price;
        protected OnRecyclerViewOnClickListener listener;

        public NormalViewHolder(View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            head = (ImageView) itemView.findViewById(R.id.require_item_head);
            name = (TextView) itemView.findViewById(R.id.require_item_name);
            state = (TextView) itemView.findViewById(R.id.require_item_state);
            image = (ImageView) itemView.findViewById(R.id.require_item_image);
            disc = (TextView) itemView.findViewById(R.id.require_item_disc);
            price = (TextView) itemView.findViewById(R.id.require_item_price);
            time = (TextView) itemView.findViewById(R.id.require_item_time);
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

    public class OutViewHolder extends NormalViewHolder {
        protected TextView delete;
        protected TextView retry;

        public OutViewHolder(View itemView, final OnRequireOutOnClickListener listener) {
            super(itemView, listener);
            RelativeLayout layout = (RelativeLayout) itemView.findViewById(R.id.require_out_item_rl);
            layout.setVisibility(View.VISIBLE);
            ImageView time_image = (ImageView) itemView.findViewById(R.id.require_item_time_img);
            time_image.setImageResource(R.mipmap.ic_add_to);
            price.setTextColor(context.getResources().getColor(R.color.txt_gray_dark));
            time.setTextColor(context.getResources().getColor(R.color.txt_gray_dark));
            delete = (TextView) itemView.findViewById(R.id.require_item_delete);
            retry = (TextView) itemView.findViewById(R.id.require_item_retry);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemDeleteClick(v, getLayoutPosition());
                }
            });

            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemRetryClick(v, getLayoutPosition());
                }
            });
        }
    }
}
