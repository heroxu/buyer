package com.smyy.sharetour.buyer.module.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.bean.ShippingAddressBean;

import java.util.List;


public class ManageShippingAddressAdapter extends RecyclerView.Adapter<ManageShippingAddressAdapter.ViewHolder> {

    private Context mContext;
    private List<ShippingAddressBean> mDatas;
    private int mLastCheckedPos;

    public ManageShippingAddressAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_my_manage_shipping_address, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ShippingAddressBean data = mDatas.get(position);
        if (data != null) {
            Log.e("rtrt", data.toString());
            Log.e("rtrt", position + " = position");
            holder.rbDefault.setChecked(data.isDefault());
            if (data.isDefault()) {
                mLastCheckedPos = position;
            }
            holder.tvName.setText(data.getName());
            holder.tvPhone.setText(data.getPhone());
            holder.tvAddress.setText(data.getAddress());

//            holder.rbDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    Log.e("rtrt onCheckedChanged", mDatas.toString());
//                    Log.e("rtrt onCheckedChanged", position + " = position");
//                    Log.e("rtrt onCheckedChanged", mLastCheckedPos + " = mLastCheckedPos");
//                    Log.e("rtrt onCheckedChanged", isChecked + " = isChecked");
//                    if (isChecked && mLastCheckedPos != position) {
//                        if (mLastCheckedPos >= 0 && mLastCheckedPos < mDatas.size()) {
//                            mDatas.get(mLastCheckedPos).setDefault(false);
//                            notifyItemChanged(mLastCheckedPos);
//                        }
//                        mLastCheckedPos = position;
//                        data.setDefault(true);
//                        if (onDefaultChangedListener != null) {
//                            onDefaultChangedListener.onDefaultChanged(holder.rbDefault, position, data);
//                        }
//                    }
//                    Log.e("rtrt onCheckedChanged", mDatas.toString());
//                }
//            });
            holder.rbDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.rbDefault, position, data);
                    }
                }
            });
            holder.tvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.tvEdit, position, data);
                    }
                }
            });
            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onItemViewClick(holder.tvDelete, position, data);
                    }
                }
            });

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

    public List<ShippingAddressBean> getList() {
        return mDatas;
    }

    // 事件回调监听
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, ShippingAddressBean data);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // 事件回调监听
    private OnItemViewClickListener onItemViewClickListener;

    public interface OnItemViewClickListener {
        void onItemViewClick(View view, int position, ShippingAddressBean data);
    }

    public void setOnItemViewClickListener(OnItemViewClickListener listener) {
        this.onItemViewClickListener = listener;
    }

    private OnDefaultChangedListener onDefaultChangedListener;

    public interface OnDefaultChangedListener {
        void onDefaultChanged(View view, int position, ShippingAddressBean data);
    }

    public void setOnDefaultChangedListener(OnDefaultChangedListener listener) {
        this.onDefaultChangedListener = listener;
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
        private TextView tvEdit;
        private TextView tvDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            rbDefault = (RadioButton) itemView.findViewById(R.id.rb_my_shipping_default);
            tvName = (TextView) itemView.findViewById(R.id.tv_my_shipping_name);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_my_shipping_phone);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_my_shipping_address);
            tvEdit = (TextView) itemView.findViewById(R.id.tv_my_shipping_edit);
            tvDelete = (TextView) itemView.findViewById(R.id.tv_my_shipping_delete);
        }
    }
}
