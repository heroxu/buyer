package com.smyy.sharetour.buyer.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.backpacker.require.BackPackerRequireSearchListActivity;
import com.smyy.sharetour.buyer.db.HomeSearch;
import com.smyy.sharetour.buyer.db.operate.HomeSearchDaoOpe;
import com.smyy.sharetour.buyer.home.search.activity.SearchActivity;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.uiframelib.BaseActivity;

import java.util.List;

/**
 * create by xuxiarong on 2018/4/12
 */
public class SearchResultAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mDatas;
    private String mSearchType;//判断是搜索什么类型的东西
    public SearchResultAdapter(Context context, List<String> datas, String searchType) {
        this.mContext = context;
        this.mDatas = datas;
        this.mSearchType = searchType;
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
                if(mSearchType.equals(SearchActivity.BUNDLE_REQUIRE)) {
                    Intent intent = new Intent(mContext, BackPackerRequireSearchListActivity.class);
                    intent.putExtra(SearchActivity.BUNDLE_SEARCH_STRING, mDatas.get(position));
                    mContext.startActivity(intent);
                    ((BaseActivity)mContext).finish();
                    ((BaseActivity)mContext).finish();
                } else {
                    HomeSearch homeSearch = new HomeSearch();
                    homeSearch.setSearchContent(mDatas.get(position));
                    HomeSearchDaoOpe.insertData(mContext,homeSearch);
                    ActivityLauncher.viewSearchDetail(mContext, mSearchType);
                }

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
