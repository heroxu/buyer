package com.smyy.sharetour.buyer.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.smyy.sharetour.buyer.R;

/**
 * Created by 伍振飞 on 2018/4/12 15:35
 * E-Mail Address：wuzf2012@sina.com
 */
public class DetailsEditorDialog extends AnimationDialog implements View.OnClickListener {
    private ItemClickCallbackListener mCallbackListener;

    public DetailsEditorDialog(Activity context) {
        super(context);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected View obtainView(Context context) {
        View parent = LayoutInflater.from(context).inflate(R.layout.dialog_details_editor, null);
        parent.findViewById(R.id.tv_share).setOnClickListener(this);
        parent.findViewById(R.id.tv_editor).setOnClickListener(this);
        parent.findViewById(R.id.tv_delete).setOnClickListener(this);
        parent.findViewById(R.id.tv_cancel).setOnClickListener(this);
        return parent;
    }

    public void cancel() {
        dismiss();
    }

    @Override
    public void destroy() {
        mCallbackListener = null;
        super.destroy();
    }

    protected void fromShare() {
        if (mCallbackListener != null) {
            mCallbackListener.fromShare(this);
        }
    }

    protected void fromEditor() {
        if (mCallbackListener != null) {
            mCallbackListener.fromEditor(this);
        }
    }

    protected void fromDelete() {
        if (mCallbackListener != null) {
            mCallbackListener.fromDelete(this);
        }
    }

    protected void fromCancel() {
        if (mCallbackListener != null) {
            mCallbackListener.fromCancel(this);
        }
    }

    public void setClickCallbackListener(ItemClickCallbackListener listener) {
        mCallbackListener = listener;
    }

    public interface ItemClickCallbackListener {
        void fromShare(DetailsEditorDialog dialog);

        void fromEditor(DetailsEditorDialog dialog);

        void fromDelete(DetailsEditorDialog dialog);

        void fromCancel(DetailsEditorDialog dialog);
    }
}
