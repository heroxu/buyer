package com.smyy.sharetour.buyer.BackPacker.Travel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.TravelBean;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;


public class BackPackerTravelItemAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<TravelBean> list = new ArrayList<>();
    private OnRecyclerViewOnClickListener mListener;

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_INSERT = 1;
    private static final int TYPE_RETURN = 2;

    public BackPackerTravelItemAdapter(Context context, List<TravelBean> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_NORMAL:
                return new NormalViewHolder(inflater.inflate(R.layout.item_backpacker_travel_normal, parent, false));
            case TYPE_INSERT:
                return new InsertViewHolder(inflater.inflate(R.layout.item_backpacker_travel_insert, parent, false), mListener);
            case TYPE_RETURN:
                return new NormalViewHolder(inflater.inflate(R.layout.item_backpacker_travel_return, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position!=list.size()-1) {
            if(position==list.size()&& list.get(position-1).getTravelCountry()!=null) {
                ((NormalViewHolder) holder).country.setText(list.get(position-1).getTravelCountry());
                ((NormalViewHolder) holder).country.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);
            } else if(position!=list.size()&&list.get(position).getTravelCountry()!=null) {
                ((NormalViewHolder) holder).country.setText(list.get(position).getTravelCountry());
                ((NormalViewHolder) holder).country.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);
            }

            if(position==list.size()&& list.get(position-1).getTravelTime()!=null) {
                ((NormalViewHolder) holder).time.setText(list.get(position-1).getTravelTime());
                ((NormalViewHolder) holder).time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);
            } else if(position!=list.size()&&list.get(position).getTravelTime()!=null) {
                ((NormalViewHolder) holder).time.setText(list.get(position).getTravelTime());
                ((NormalViewHolder) holder).time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==list.size()-1){
            return TYPE_INSERT;
        } else if(position==list.size()){
            return TYPE_RETURN;
        }
        return TYPE_NORMAL;
    }

    public void setItemClickListener(OnRecyclerViewOnClickListener listener){
        this.mListener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder{

        protected TextView country;
        protected TextView time;

        public NormalViewHolder(View itemView) {
            super(itemView);
            country = (TextView) itemView.findViewById(R.id.travel_country);
            country.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getLayoutPosition()==list.size()){
                        onCountryClickListener.onCountryClick(getLayoutPosition()-1);
                    } else {
                        onCountryClickListener.onCountryClick(getLayoutPosition());
                    }
                }
            });
            time = (TextView) itemView.findViewById(R.id.travel_time);
            time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getLayoutPosition()==list.size()) {
                        onTimeClickListener.onTimeClick(getLayoutPosition()-1);
                    } else {
                        onTimeClickListener.onTimeClick(getLayoutPosition());
                    }
                }
            });
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

    private OnCountryClickListener onCountryClickListener;
    private OnTimeClickListener onTimeClickListener;

    public interface OnCountryClickListener {
        void onCountryClick(int position);
    }

    public interface OnTimeClickListener {
        void onTimeClick(int position);
    }

    public void setOnCountryClickListener(OnCountryClickListener onCountryClickListener) {
        this.onCountryClickListener = onCountryClickListener;
    }

    public void setOnTimeClickListener(OnTimeClickListener onTimeClickListener) {
        this.onTimeClickListener = onTimeClickListener;
    }
}
