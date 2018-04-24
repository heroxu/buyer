package com.smyy.sharetour.buyer.BackPacker.Travel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.TravelBean;

import java.util.List;


public class BackPackerTravelDetailItemAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final LayoutInflater inflater;
    private TravelBean travelBean;
    private List<TravelBean.RouteBean> list;

    private static final int TYPE_START = 0;
    private static final int TYPE_MIDDLE = 1;
    private static final int TYPE_END = 2;

    public BackPackerTravelDetailItemAdapter(Context context, TravelBean bean){
        this.context = context;
        this.travelBean = bean;
        this.list = bean.getRouteBeans();
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_START:
                return new NormalViewHolder(inflater.inflate(R.layout.item_backpacker_travel_detail_head, parent, false));
            case TYPE_MIDDLE:
                return new NormalViewHolder(inflater.inflate(R.layout.item_backpacker_travel_detail_middle, parent, false));
            case TYPE_END:
                return new NormalViewHolder(inflater.inflate(R.layout.item_backpacker_travel_detail_end, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((NormalViewHolder)holder).country.setText(list.get(position).getRouteCountry());
        ((NormalViewHolder)holder).time.setText(list.get(position).getRouteTime());
        ((NormalViewHolder)holder).countryImage.setBackground(context.getResources().getDrawable(list.get(position).getImgId()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_START;
        } else if(position==list.size()-1){
            return TYPE_END;
        }
        return TYPE_MIDDLE;
    }


    public class NormalViewHolder extends RecyclerView.ViewHolder{

        private TextView time;
        private TextView country;
        private ImageView countryImage;

        public NormalViewHolder(View itemView) {
            super(itemView);

            time = (TextView) itemView.findViewById(R.id.travel_arrive_time);
            country = (TextView) itemView.findViewById(R.id.travel_arrive_country);
            countryImage = (ImageView) itemView.findViewById(R.id.travel_country_image);

        }

    }


}
