package com.smyy.sharetour.buyer.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smyy.sharetour.buyer.R;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private List<String> listUrls;
    private LayoutInflater inflater;
    private Context mContext;

    public GridAdapter(List<String> listUrls, Context context) {
        this.listUrls = listUrls;
        if(listUrls.size() == 7){
            listUrls.remove(listUrls.size()-1);
        }
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    public int getCount(){
        return  listUrls.size();
    }
    @Override
    public String getItem(int position) {
        return listUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.publish_grid_item, parent,false);
            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        final String path=listUrls.get(position);
        if (path.equals("paizhao")){
            holder.image.setImageResource(R.mipmap.ic_add_to);
        }else {
            Glide.with(mContext)
                    .load(path)
                    .placeholder(R.mipmap.default_error)
                    .error(R.mipmap.default_error)
                    .centerCrop()
                    .crossFade()
                    .into(holder.image);
        }
        return convertView;
    }
    class ViewHolder {
        ImageView image;
    }
}
