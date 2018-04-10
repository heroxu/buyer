package com.smyy.sharetour.buyer.view.pop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.smyy.sharetour.buyer.R;

/**
 * create by xuxiarong on ${DATA}
 */
public class HomeTitlesPopupWindow extends PopupWindow {
    private RecyclerView popRv;
    public HomeTitlesPopupWindow(Context context) {
        super(context);
        View view = View.inflate(context, R.layout.layout_home_table_title, null);
        popRv = (RecyclerView) view.findViewById(R.id.pop_rv);
        setContentView(view);
        setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
        setHeight(FrameLayout.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        setOutsideTouchable(true);

    }
}
