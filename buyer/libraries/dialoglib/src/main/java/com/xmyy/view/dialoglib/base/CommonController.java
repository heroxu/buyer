package com.xmyy.view.dialoglib.base;

import android.content.DialogInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;

import com.xmyy.view.dialoglib.R;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

import java.io.Serializable;

/**
 * 数据保存封装的容器类
 **/
public class CommonController<A extends CommonBaseAdapter> implements Parcelable, Serializable {

    private FragmentManager fragmentManager;
    private int layoutRes;
    private int height;
    private int width;
    private float dimAmount;
    private int gravity;
    private String tag;
    private int[] ids;
    private boolean isCancelableOutside;
    private OnViewClickListener onViewClickListener;
    private OnBindViewListener onBindViewListener;
    private A adapter;
    private CommonBaseAdapter.OnAdapterItemClickListener adapterItemClickListener;
    private int orientation;
    private boolean cancelable;//弹窗是否可以取消
    private View dialogView;
    private DialogInterface.OnDismissListener onDismissListener;

    //////////////////////////////////////////Parcelable持久化//////////////////////////////////////////////////////
    public CommonController() {
    }

    protected CommonController(Parcel in) {
        layoutRes = in.readInt();
        height = in.readInt();
        width = in.readInt();
        dimAmount = in.readFloat();
        gravity = in.readInt();
        tag = in.readString();
        ids = in.createIntArray();
        isCancelableOutside = in.readByte() != 0;
        orientation = in.readInt();
        cancelable = in.readByte() != 0;
    }

    public static final Creator<CommonController> CREATOR = new Creator<CommonController>() {
        @Override
        public CommonController createFromParcel(Parcel in) {
            return new CommonController(in);
        }

        @Override
        public CommonController[] newArray(int size) {
            return new CommonController[size];
        }
    };

    //内容描述接口,不用管
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(layoutRes);
        dest.writeInt(height);
        dest.writeInt(width);
        dest.writeFloat(dimAmount);
        dest.writeInt(gravity);
        dest.writeString(tag);
        dest.writeIntArray(ids);
        dest.writeByte((byte) (isCancelableOutside ? 1 : 0));
        dest.writeInt(orientation);
        dest.writeByte((byte) (cancelable ? 1 : 0));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //get
    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int mWidth) {
        this.width = mWidth;
    }

    public float getDimAmount() {
        return dimAmount;
    }

    public int getGravity() {
        return gravity;
    }

    public String getTag() {
        return tag;
    }

    public int[] getIds() {
        return ids;
    }

    public boolean isCancelableOutside() {
        return isCancelableOutside;
    }

    public OnViewClickListener getOnViewClickListener() {
        return onViewClickListener;
    }

    public int getOrientation() {
        return orientation;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public OnBindViewListener getOnBindViewListener() {
        return onBindViewListener;
    }

    public DialogInterface.OnDismissListener getOnDismissListener() {
        return onDismissListener;
    }

    public View getDialogView() {
        return dialogView;
    }

    //列表
    public A getAdapter() {
        return adapter;
    }

    public void setAdapter(A adapter) {
        this.adapter = adapter;
    }

    public CommonBaseAdapter.OnAdapterItemClickListener getAdapterItemClickListener() {
        return adapterItemClickListener;
    }

    public void setAdapterItemClickListener(CommonBaseAdapter.OnAdapterItemClickListener adapterItemClickListener) {
        this.adapterItemClickListener = adapterItemClickListener;
    }

    /**************************************************************************
     */
    public static class TParams<A extends CommonBaseAdapter> {
        public FragmentManager mFragmentManager;
        public int mLayoutRes;
        public int mWidth;
        public int mHeight;
        public float mDimAmount = 0.2f;
        public int mGravity = Gravity.CENTER;
        public String mTag = "CommonDialog";
        public int[] ids;
        public boolean mIsCancelableOutside = true;
        public OnViewClickListener mOnViewClickListener;
        public OnBindViewListener bindViewListener;
        //列表
        public A adapter;
        public CommonBaseAdapter.OnAdapterItemClickListener adapterItemClickListener;
        public int listLayoutRes;
        public int orientation = LinearLayoutManager.VERTICAL;//默认RecyclerView的列表方向为垂直方向
        public boolean mCancelable = true;//弹窗是否可以取消
        public View mDialogView;//直接使用传入进来的View,而不需要通过解析Xml
        public DialogInterface.OnDismissListener mOnDismissListener;

        public void apply(CommonController commonController) {
            commonController.fragmentManager = mFragmentManager;
            if (mLayoutRes > 0) {
                commonController.layoutRes = mLayoutRes;
            }
            if (mDialogView != null) {
                commonController.dialogView = mDialogView;
            }
            if (mWidth > 0) {
                commonController.width = mWidth;
            }
            if (mHeight > 0) {
                commonController.height = mHeight;
            }
            commonController.dimAmount = mDimAmount;
            commonController.gravity = mGravity;
            commonController.tag = mTag;
            if (ids != null) {
                commonController.ids = ids;
            }
            commonController.isCancelableOutside = mIsCancelableOutside;
            commonController.onViewClickListener = mOnViewClickListener;
            commonController.onBindViewListener = bindViewListener;
            commonController.onDismissListener = mOnDismissListener;

            if (adapter != null) {
                commonController.setAdapter(adapter);
                if (listLayoutRes <= 0) {//使用默认的布局
                    commonController.setLayoutRes(R.layout.dialog_recycler);
                } else {
                    commonController.setLayoutRes(listLayoutRes);
                }
                commonController.orientation = orientation;
            } else {
                if (commonController.getLayoutRes() <= 0 && commonController.getDialogView() == null) {
                    throw new IllegalArgumentException("请先调用setLayoutRes()方法设置弹窗所需的xml布局!");
                }
            }
            if (adapterItemClickListener != null) {
                commonController.setAdapterItemClickListener(adapterItemClickListener);
            }
            //弹窗是否可以取消
            commonController.cancelable = mCancelable;
        }
    }
}
