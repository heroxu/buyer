package com.smyy.sharetour.buyer.view.flow_tag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;

import java.util.List;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.view.flow_tag
* @fileName TagBaseAdapter
* @date on 2018/4/17 0017 17:32
* @describe 标签流容器适配器
*/
public class TagBaseAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;

    public TagBaseAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_item_tag, null);
            holder = new ViewHolder();
            holder.tagBtn = (TextView) convertView.findViewById(R.id.tag_btn);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        final String text = getItem(position);
        holder.tagBtn.setText(text);
        return convertView;
    }

    static class ViewHolder {
        TextView tagBtn;
    }
}
