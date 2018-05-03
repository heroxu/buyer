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

public class BuyTypePop implements View.OnTouchListener{
    private Context mContext;
    private BasePopupWindow mPopupWindow;
    AreaRightAdapter mAdapter;
    public BuyTypePop(Context mContext) {
        this.mContext = mContext;
    }

    /**初始化控件 , 实现控件点击事件*/
    private void init(View v, final IStatusChange mIStatusChange) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.pop_buy_type, null);
        RecyclerView recyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        final List<AreaBean.Region> data = new ArrayList<>();
        data.add(new AreaBean.Region(true,"按买手评分排序"));
        data.add(new AreaBean.Region(false,"按买手交易量排序"));
        mAdapter = new AreaRightAdapter(data);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mAdapter.setCheckItem();
                ((AreaBean.Region) adapter.getItem(position)).setCheck(true);
                adapter.notifyDataSetChanged();
                mIStatusChange.selectPosition(position);
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

    public BuyTypePop showPop(View v,IStatusChange mIStatusChange) {
        if (null != mPopupWindow) {
            mPopupWindow.dismiss();
        } else {
            init(v,mIStatusChange);
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

    public interface IStatusChange{
        void selectPosition(int position);
    }

}
