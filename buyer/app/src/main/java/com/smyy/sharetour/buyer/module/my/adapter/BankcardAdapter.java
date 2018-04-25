package com.smyy.sharetour.buyer.module.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.bean.BankcardBean;
import com.smyy.sharetour.buyer.module.my.bean.InterestSellerBean;

import java.util.List;


public class BankcardAdapter extends RecyclerView.Adapter<BankcardAdapter.ViewHolder> {

    private Context mContext;
    private List<BankcardBean> mDatas;

    public BankcardAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_my_bankcard, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BankcardBean data = mDatas.get(position);
        if (data != null) {
            holder.tvName.setText(data.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(holder.itemView, position, data);
                    }
                }
            });
        }
    }

    // 事件回调监听
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, BankcardBean data);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setData(List<BankcardBean> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private View layBg;
        private ImageView ivLogo;
        private TextView tvName;
        private TextView tvType;
        private TextView tvNum;

        public ViewHolder(View itemView) {
            super(itemView);
            layBg = itemView.findViewById(R.id.lay_my_bankcard_bg);
            ivLogo = (ImageView) itemView.findViewById(R.id.iv_my_bankcard_logo);
            tvName = (TextView) itemView.findViewById(R.id.tv_my_bankcard_name);
            tvType = (TextView) itemView.findViewById(R.id.tv_my_bankcard_type);
            tvNum = (TextView) itemView.findViewById(R.id.tv_my_bankcard_num);
        }
    }
}
