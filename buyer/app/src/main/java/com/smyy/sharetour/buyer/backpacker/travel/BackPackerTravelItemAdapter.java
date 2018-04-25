package com.smyy.sharetour.buyer.backpacker.travel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.TravelBean;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;
import com.smyy.sharetour.buyer.view.InnerRecyclerView;

import java.util.List;


public class BackPackerTravelItemAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final LayoutInflater inflater;
    private List<TravelBean> list;
    private OnRecyclerViewOnClickListener mListener;
    private BackPackerTravelSubItemAdapter adapter;


    public BackPackerTravelItemAdapter(Context context, List<TravelBean> list){
        this.context = context;
        this.list =list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(inflater.inflate(R.layout.item_backpacker_travel_list, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((NormalViewHolder)holder).create_time.setText(list.get(position).getCreateDate());
        adapter = new BackPackerTravelSubItemAdapter(context, list.get(position));
        ((NormalViewHolder)holder).recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setItemClickListener(OnRecyclerViewOnClickListener listener){
        this.mListener = listener;
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView create_time;
        private RecyclerView recyclerView;
        OnRecyclerViewOnClickListener listener;

        public NormalViewHolder(final View itemView, OnRecyclerViewOnClickListener listener) {
            super(itemView);

            create_time = (TextView) itemView.findViewById(R.id.create_time);
            recyclerView = (InnerRecyclerView) itemView.findViewById(R.id.route_list);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            this.listener = listener;
            itemView.setOnClickListener(this);
            recyclerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        itemView.performClick();  //模拟父控件的点击
                    }
                    return false;
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
