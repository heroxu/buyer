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


public class BackPackerTravelSubItemAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final LayoutInflater inflater;
    private TravelBean travelBean;
    private List<TravelBean.RouteBean> list;

    private static final int TYPE_START = 0;
    private static final int TYPE_MIDDLE = 1;
    private static final int TYPE_END = 2;

    public BackPackerTravelSubItemAdapter(Context context, TravelBean bean){
        this.context = context;
        this.travelBean = bean;
        this.list = bean.getRouteBeans();
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_START:
                return new NormalViewHolder(inflater.inflate(R.layout.item_backpacker_travel_sublist_head, parent, false));
            case TYPE_MIDDLE:
                return new MiddleViewHolder(inflater.inflate(R.layout.item_backpacker_travel_sublist_middle, parent, false));
            case TYPE_END:
                return new NormalViewHolder(inflater.inflate(R.layout.item_backpacker_travel_sublist_end, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position==0 || position==list.size()-1) {
            ((NormalViewHolder)holder).country.setText(list.get(position).getRouteCountry());
            ((NormalViewHolder)holder).time.setText(list.get(position).getRouteTime());
        } else {
            ((MiddleViewHolder)holder).country.setText(list.get(position).getRouteCountry());
            ((MiddleViewHolder)holder).time.setText(list.get(position).getRouteTime());
        }

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

        private View stateLine;
        private ImageView stateImage;
        private TextView time;
        private TextView country;
        private ImageView countryImage;

        public NormalViewHolder(View itemView) {
            super(itemView);

            stateLine = itemView.findViewById(R.id.travel_state_line);
            stateImage = (ImageView) itemView.findViewById(R.id.travel_state_img);
            time = (TextView) itemView.findViewById(R.id.travel_country_time);
            country = (TextView) itemView.findViewById(R.id.travel_country_text);
            countryImage = (ImageView) itemView.findViewById(R.id.travel_country_img);

            if(list.size()==1){
                stateLine.setVisibility(View.GONE);
            }

        }

    }

    public class MiddleViewHolder extends RecyclerView.ViewHolder{

        private View stateUpLine;
        private View stateDownLine;
        private ImageView stateImage;
        private TextView time;
        private TextView country;
        private ImageView countryImage;

        public MiddleViewHolder(View itemView) {
            super(itemView);

            stateUpLine = itemView.findViewById(R.id.travel_state_line_up);
            stateDownLine = itemView.findViewById(R.id.travel_state_line_down);
            stateImage = (ImageView) itemView.findViewById(R.id.travel_state_img);
            time = (TextView) itemView.findViewById(R.id.travel_country_time);
            country = (TextView) itemView.findViewById(R.id.travel_country_text);
            countryImage = (ImageView) itemView.findViewById(R.id.travel_country_img);
        }

    }

}
