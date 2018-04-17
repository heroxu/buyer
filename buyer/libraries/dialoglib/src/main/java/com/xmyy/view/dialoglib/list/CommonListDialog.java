package com.xmyy.view.dialoglib.list;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.xmyy.view.dialoglib.R;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.CommonBaseAdapter;
import com.xmyy.view.dialoglib.base.CommonController;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

/**
 * 列表弹窗  与CommonDialog实现分开处理
 **/
public class CommonListDialog extends CommonDialog {


    @Override
    protected void bindView(View view) {
        super.bindView(view);
        if (commonController.getAdapter() != null) {//有设置列表
            //列表
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
            if (recyclerView == null) {
                throw new IllegalArgumentException("自定义列表xml布局,请设置RecyclerView的控件id为recycler_view");
            }
            commonController.getAdapter().setTDialog(this);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), commonController.getOrientation(),false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(commonController.getAdapter());
            commonController.getAdapter().notifyDataSetChanged();
            if (commonController.getAdapterItemClickListener() != null) {
                commonController.getAdapter().setOnAdapterItemClickListener(commonController.getAdapterItemClickListener());
            }
        }else{
            Log.d("CommonDialog","列表弹窗需要先调用setAdapter()方法!");
        }
    }

    /*********************************************************************
     * 使用Builder模式实现
     *
     */
    public static class Builder {

        CommonController.TParams params;

        public Builder(FragmentManager fragmentManager) {
            params = new CommonController.TParams();
            params.mFragmentManager = fragmentManager;
        }

        //各种setXXX()方法设置数据
        public CommonListDialog.Builder setLayoutRes(@LayoutRes int layoutRes) {
            params.mLayoutRes = layoutRes;
            return this;
        }

        //设置自定义列表布局和方向
        public CommonListDialog.Builder setListLayoutRes(@LayoutRes int layoutRes, int orientation) {
            params.listLayoutRes = layoutRes;
            params.orientation = orientation;
            return this;
        }

        /**
         * 设置弹窗宽度是屏幕宽度的比例 0 -1
         */
        public CommonListDialog.Builder setScreenWidthAspect(Activity activity, float widthAspect) {
            params.mWidth = (int) (getWindowWidth(activity) * widthAspect);
            return this;
        }

        public CommonListDialog.Builder setWidth(int widthPx) {
            params.mWidth = widthPx;
            return this;
        }

        /**
         * 设置屏幕高度比例 0 -1
         */
        public CommonListDialog.Builder setScreenHeightAspect(Activity activity, float heightAspect) {
            params.mHeight = (int) (getWindowHeight(activity) * heightAspect);
            return this;
        }

        public CommonListDialog.Builder setHeight(int heightPx) {
            params.mHeight = heightPx;
            return this;
        }

        public CommonListDialog.Builder setGravity(int gravity) {
            params.mGravity = gravity;
            return this;
        }

        public CommonListDialog.Builder setCancelOutside(boolean cancel) {
            params.mIsCancelableOutside = cancel;
            return this;
        }

        public CommonListDialog.Builder setDimAmount(float dim) {
            params.mDimAmount = dim;
            return this;
        }

        public CommonListDialog.Builder setTag(String tag) {
            params.mTag = tag;
            return this;
        }

        public CommonListDialog.Builder setOnBindViewListener(OnBindViewListener listener) {
            params.bindViewListener = listener;
            return this;
        }

        public CommonListDialog.Builder addOnClickListener(int... ids) {
            params.ids = ids;
            return this;
        }

        public CommonListDialog.Builder setOnViewClickListener(OnViewClickListener listener) {
            params.mOnViewClickListener = listener;
            return this;
        }

        //列表数据,需要传入数据和Adapter,和item点击数据
        public <A extends CommonBaseAdapter> CommonListDialog.Builder setAdapter(A adapter) {
            params.adapter = adapter;
            return this;
        }

        public CommonListDialog.Builder setOnAdapterItemClickListener(CommonBaseAdapter.OnAdapterItemClickListener listener) {
            params.adapterItemClickListener = listener;
            return this;
        }

        public CommonListDialog.Builder setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
            params.mOnDismissListener = dismissListener;
            return this;
        }

        public CommonListDialog create() {
            CommonListDialog dialog = new CommonListDialog();
            //将数据从Buidler的DjParams中传递到DjDialog中
            params.apply(dialog.commonController);
            return dialog;
        }
    }
}
