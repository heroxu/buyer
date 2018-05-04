package com.smyy.sharetour.buyer.backpacker.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.backpacker.my.bean.AccountStatementBean;
import com.smyy.sharetour.buyer.util.StringUtil;

import java.util.List;


public class AccountStatementAdapter extends RecyclerView.Adapter<AccountStatementAdapter.ViewHolder> {

    private Context mContext;
    private List<AccountStatementBean> mDatas;

    public AccountStatementAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_my_account_statement, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final AccountStatementBean data = mDatas.get(position);
        if (data != null) {
            holder.tvName.setText(data.getName());
            holder.tvTime.setText(data.getTime());

            int type = data.getType();
            switch (type) {
                case Consts.STATEMENT_TYPE_IN:
                    holder.tvNum.setTextColor(mContext.getResources().getColor(R.color.txt_price));
                    holder.tvNum.setText(StringUtil.connect("+ ", data.getNum()));
                    break;
                case Consts.STATEMENT_TYPE_OUT:
                    holder.tvNum.setTextColor(mContext.getResources().getColor(R.color.txt_green));
                    holder.tvNum.setText(StringUtil.connect("- ", data.getNum()));
                    break;
                default:
                    holder.tvNum.setText("");
                    break;
            }

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
        void onItemClick(View view, int position, AccountStatementBean data);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setData(List<AccountStatementBean> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvTime;
        public TextView tvNum;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_my_statement_name);
            tvTime = (TextView) itemView.findViewById(R.id.tv_my_statement_time);
            tvNum = (TextView) itemView.findViewById(R.id.tv_my_wallet_num);
        }
    }
}
