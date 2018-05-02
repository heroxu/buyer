package com.smyy.sharetour.buyer.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smyy.sharetour.buyer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2018/5/2.
 */

public class RegionPop implements View.OnTouchListener{
    private Context mContext;
    private BasePopupWindow mPopupWindow;
    AreaLeftAdapter mAreaLeftAdapter;
    AreaRightAdapter mAreaRightAdapter;

    public RegionPop(Context mContext) {
        this.mContext = mContext;
    }

    /**初始化控件 , 实现控件点击事件*/
    private void init(View v) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.pop_linkage, null);
        RecyclerView recyclerViewLeft = (RecyclerView) convertView.findViewById(R.id.recycler_view_left);
        RecyclerView recyclerViewRight = (RecyclerView) convertView.findViewById(R.id.recycler_view_right);
        recyclerViewLeft.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewRight.setLayoutManager(new LinearLayoutManager(mContext));
        final List<AreaBean> data = new ArrayList<>();
        data.add(new AreaBean("热门"));
        data.add(new AreaBean("亚洲"));
        data.add(new AreaBean("欧洲"));
        data.add(new AreaBean("非洲"));
        data.add(new AreaBean("美洲"));
        data.add(new AreaBean("大洋洲"));
        //初始化
        data.get(0).setCheck(true);
        for (int i=0;i<data.size();i++){
            data.get(i).getmRegions().get(0).setCheck(true);
        }
        mAreaLeftAdapter = new AreaLeftAdapter(data);
        recyclerViewLeft.setAdapter(mAreaLeftAdapter);
        mAreaRightAdapter = new AreaRightAdapter(data.get(0).getmRegions());
        recyclerViewRight.setAdapter(mAreaRightAdapter);
        mAreaLeftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((AreaBean) adapter.getData().get(position)).setCheck(true);
                adapter.notifyDataSetChanged();
                mAreaRightAdapter.setNewData(data.get(position).getmRegions());
                mAreaRightAdapter.notifyDataSetChanged();
            }
        });
        mAreaRightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mAreaRightAdapter.setCheckItem();
                ((AreaBean.Region) adapter.getItem(position)).setCheck(true);
                adapter.notifyDataSetChanged();
            }
        });
        mPopupWindow = new BasePopupWindow(convertView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        convertView.setOnTouchListener(this);//触摸事件 , 在其他区域触摸屏幕 , 取消popupwindow.
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());//保证popupwindow响应返回按钮事件.
        mPopupWindow.showAsDropDown(v);
//        mPopupWindow.showAtLocation(v,0,0, 0);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (null != mPopupWindow && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
        return false;
    }

    public RegionPop showPop(View v) {
        if (null != mPopupWindow) {
            mPopupWindow.dismiss();
        } else {
            init(v);
        }
        return this;
    }

    public boolean disMissPop() {
        boolean isshowing = false;
        if (null != mPopupWindow) {
            isshowing = mPopupWindow.isShowing();
            mPopupWindow.dismiss();
        }
        return isshowing;
    }

}
