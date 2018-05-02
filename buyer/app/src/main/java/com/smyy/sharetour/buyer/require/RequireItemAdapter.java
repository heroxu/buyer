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
    private OnItemRequireClickListener mListener;

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
                return new NormalViewHolder(inflater.inflate(R.layout.item_require_out_list_layout, parent, false), mListener);
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

        if(item.getState()==Consts.REQUIRE_STATE_REQUIRE_DONE){
            ((NormalViewHolder)holder).deleteFrame.setVisibility(View.VISIBLE);
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
        if (list.get(position).getState()==Consts.REQUIRE_STATE_INVALID) {
            return RequireItemAdapter.TYPE_OUT_STOCK;
        }
        return RequireItemAdapter.TYPE_NORMAL;
    }

    public void setItemClickListener(OnItemRequireClickListener listener){
        this.mListener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView image;
        protected TextView disc;
        protected TextView name;
        protected TextView time;
        protected TextView state;
        protected TextView price;
        private TextView delete;
        private RelativeLayout deleteFrame;
        protected OnItemRequireClickListener listener;

        public NormalViewHolder(View itemView, final OnItemRequireClickListener listener) {
            super(itemView);
            state = (TextView) itemView.findViewById(R.id.require_item_state);
            image = (ImageView) itemView.findViewById(R.id.require_item_image);
            disc = (TextView) itemView.findViewById(R.id.require_item_disc);
            price = (TextView) itemView.findViewById(R.id.require_item_price);
            time = (TextView) itemView.findViewById(R.id.require_item_time);
            deleteFrame = (RelativeLayout) itemView.findViewById(R.id.require_out_item_rl);
            this.listener = listener;
            itemView.setOnClickListener(this);
            delete = (TextView) itemView.findViewById(R.id.require_item_delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemDeleteClick(v, getLayoutPosition());
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

    public interface OnItemRequireClickListener extends OnRecyclerViewOnClickListener{
        void OnItemDeleteClick(View v, int position);
    }

}
