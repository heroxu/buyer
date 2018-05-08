package com.smyy.sharetour.buyer.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.bean.SimpleSelectBean;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;
import com.yongchun.library.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class ListSelectAdapter extends RecyclerView.Adapter {

    private List<SimpleSelectBean> list = new ArrayList<>();
    private Context context;
    private OnRecyclerViewOnClickListener listener;

    public ListSelectAdapter(Context context, List<SimpleSelectBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(LayoutInflater.from(context).inflate(R.layout.item_area_right,null,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(list.get(position).isCheck()) {
            ((NormalViewHolder) holder).textView.setTextColor(context.getResources().getColor(R.color.txt_main));
        }
        ((NormalViewHolder) holder).textView.setText(list.get(position).getOption());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public NormalViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_area_right);
            textView.setHeight(ScreenUtils.dip2px(context, 45));
            textView.setWidth(ScreenUtils.getScreenWidth(context));
            if(listener!=null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.OnItemClick(v, getLayoutPosition());
                    }
                });
            }
        }
    }

    public void setItemClickListener(OnRecyclerViewOnClickListener listener) {
        this.listener = listener;
    }
}
