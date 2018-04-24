package com.smyy.sharetour.buyer.BackPacker.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;
import com.smyy.sharetour.buyer.util.StringUtil;

import java.util.List;


public class WalletStatementAdapter extends RecyclerView.Adapter<WalletStatementAdapter.ViewHolder> {

    private Context mContext;
    private List<ShippingAddressBean> mDatas;

    public WalletStatementAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_my_shipping_address, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ShippingAddressBean data = mDatas.get(position);
        if (data != null) {
            holder.rbDefault.setVisibility(data.isDefault() ? View.VISIBLE : View.GONE);
            holder.tvName.setText(data.getName());
            holder.tvPhone.setText(data.getPhone());
            holder.tvAddress.setText(StringUtil.connect(data.getDistrict(), data.getStreet(), data.getDetailAddress()));

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
        void onItemClick(View view, int position, ShippingAddressBean data);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setData(List<ShippingAddressBean> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RadioButton rbDefault;
        private TextView tvName;
        private TextView tvPhone;
        private TextView tvAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            rbDefault = (RadioButton) itemView.findViewById(R.id.rb_my_shipping_default);
            tvName = (TextView) itemView.findViewById(R.id.tv_my_shipping_name);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_my_shipping_phone);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_my_shipping_address);
        }
    }
}
