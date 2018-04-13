package com.smyy.sharetour.buyer.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.db.HomeSearch;
import com.smyy.sharetour.buyer.db.operate.HomeSearchDaoOpe;

import java.util.List;

/**
 * create by xuxiarong on 2018/4/12
 */
public class SearchHistoryAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mDatas;

    public SearchHistoryAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHistoryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_search_history, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SearchHistoryViewHolder viewHolder = (SearchHistoryViewHolder) holder;
        viewHolder.tv_home_history_content.setText(mDatas.get(position));
        viewHolder.tv_home_history_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeSearch homeSearch = new HomeSearch();
                homeSearch.setSearchContent(mDatas.get(position));
                HomeSearchDaoOpe.insertData(mContext,homeSearch);

                List<HomeSearch> homeSearches = HomeSearchDaoOpe.queryAll(mContext);
                Log.e("sss", "onClick: "+homeSearches.get(0),null);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.isEmpty()?0:mDatas.size();
    }

    public void setData(List<String> datas){
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    class SearchHistoryViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_home_history_content;
        public SearchHistoryViewHolder(View itemView) {
            super(itemView);
            tv_home_history_content = (TextView) itemView.findViewById(R.id.tv_home_history_content);
        }
    }
}
