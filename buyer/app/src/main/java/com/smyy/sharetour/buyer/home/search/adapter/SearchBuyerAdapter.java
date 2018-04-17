package com.smyy.sharetour.buyer.home.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.home.model.SearchBuyer;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * create by xuxiarong on 2018/4/12
 */
public class SearchBuyerAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<SearchBuyer> mDatas;

    public SearchBuyerAdapter(Context context, List<SearchBuyer> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchBuyerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search_buyer, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SearchBuyerViewHolder viewHolder = (SearchBuyerViewHolder) holder;
        SearchBuyer searchBuyer = mDatas.get(position);

        viewHolder.tv_search_buyer_name.setText(searchBuyer.name);
        viewHolder.tv_search_buyer_location.setText(searchBuyer.location);
        viewHolder.tv_search_buyer_communicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty()?0:mDatas.size();
    }

    public void setData(List<SearchBuyer> datas){
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    class SearchBuyerViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView iv_search_buyer_avatar;
        private TextView tv_search_buyer_name;
        private TextView tv_search_buyer_location;
        private TextView tv_search_buyer_communicate;

        public SearchBuyerViewHolder(View itemView) {
            super(itemView);
            iv_search_buyer_avatar = (CircleImageView) itemView.findViewById(R.id.iv_search_buyer_avatar);
            tv_search_buyer_name = (TextView) itemView.findViewById(R.id.tv_search_buyer_name);
            tv_search_buyer_location = (TextView) itemView.findViewById(R.id.tv_search_buyer_location);
            tv_search_buyer_communicate = (TextView) itemView.findViewById(R.id.tv_search_buyer_communicate);

        }
    }
}
