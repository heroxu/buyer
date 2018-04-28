package com.smyy.sharetour.buyer.travel;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.TravelBean;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;
import com.yongchun.library.utils.ScreenUtils;

import java.util.List;


public class SellerTravelItemAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<TravelBean> list;
    private OnRecyclerViewOnClickListener mListener;


    public SellerTravelItemAdapter(Context context, List<TravelBean> list){
        this.context = context;
        this.list =list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(inflater.inflate(R.layout.item_travel_list, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position==0){
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(ScreenUtils.dip2px(context, 20), ScreenUtils.dip2px(context, 25),
                    ScreenUtils.dip2px(context, 20), ScreenUtils.dip2px(context, 20));
            ((NormalViewHolder)holder).cardView.setLayoutParams(lp);
        }

        ((NormalViewHolder)holder).start_country.setText(list.get(position).getRouteBeans().get(0).getRouteCountry());
        ((NormalViewHolder)holder).end_country.setText(list.get(position).getRouteBeans().get(list.get(position).getRouteBeans().size()-1).getRouteCountry());
        ((NormalViewHolder)holder).country_bg.setBackground(context.getResources().getDrawable(
                list.get(position).getRouteBeans().get(list.get(position).getCurrentPlace()).getImgId()));
        if(list.get(position).getCurrentPlace()!=0) {
            ((NormalViewHolder) holder).travel_state.setText(list.get(position).getRouteBeans().get(list.get(position).getCurrentPlace()).getRouteCountry());
        } else {
            ((NormalViewHolder) holder).travel_state.setText("准备出发");
            ((NormalViewHolder) holder).travel_state.setCompoundDrawables(null,null,null,null);
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
        private CardView cardView;
        private ImageView country_bg;
        private TextView start_country;
        private TextView end_country;
        private TextView travel_state;

        public NormalViewHolder(final View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            country_bg = (ImageView) itemView.findViewById(R.id.country_img);
            start_country = (TextView) itemView.findViewById(R.id.start_country);
            end_country = (TextView) itemView.findViewById(R.id.end_country);
            travel_state = (TextView) itemView.findViewById(R.id.travel_state);

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
