package com.smyy.sharetour.buyer.dialog;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;

import java.util.ArrayList;
import java.util.List;

public class GridSelectSortPop implements View.OnTouchListener{
    private Context mContext;
    private BasePopupWindow mPopupWindow;
    private IStatusChange mIStatusChange;
    List<SimpleSelectBean> data = new ArrayList<>();

    public GridSelectSortPop(Context mContext, List<SimpleSelectBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    /**初始化控件 , 实现控件点击事件*/
    private void init(View v, final IStatusChange mIStatusChange) {
        this.mIStatusChange = mIStatusChange;
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.pop_simple_list, null);
        RecyclerView recyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                int spanCount = 4;
                int column = position % spanCount;
                int horizontalSpacing = 25;
                int verticalSpacing = 30;
                outRect.left = column * horizontalSpacing / spanCount;
                outRect.right = horizontalSpacing - (column + 1) * horizontalSpacing / spanCount;
                if (position < spanCount) {
                    outRect.top = verticalSpacing;
                }
                outRect.bottom = verticalSpacing;
            }
        });
        GridSelectAdapter adapter = new GridSelectAdapter(mContext, data);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new OnRecyclerViewOnClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                mIStatusChange.selectPosition(mPopupWindow, position);
            }
        });
        mPopupWindow = new BasePopupWindow(convertView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        convertView.setOnTouchListener(this);//触摸事件 , 在其他区域触摸屏幕 , 取消popupwindow.
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());//保证popupwindow响应返回按钮事件.
        mPopupWindow.showAsDropDown(v);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                disMissPop();
            }
        });
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

    public GridSelectSortPop showPop(View v, IStatusChange mIStatusChange) {
        if (null != mPopupWindow) {
            mPopupWindow.dismiss();
        } else {
            init(v,mIStatusChange);
        }
        return this;
    }

    public void disMissPop() {
            mIStatusChange.closePopupWindow();
    }

    public interface IStatusChange{
        void selectPosition(BasePopupWindow popupWindow, int position);
        void closePopupWindow();
    }
}
