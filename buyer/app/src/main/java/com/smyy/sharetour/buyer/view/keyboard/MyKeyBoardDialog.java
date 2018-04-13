package com.smyy.sharetour.buyer.view.keyboard;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.smyy.sharetour.buyer.R;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.view.keyboard
* @fileName MyKeyBoardDialog
* @date on 2018/4/12 0012 14:47
* @describe 自定义输入价格键盘
*/
public class MyKeyBoardDialog extends DialogFragment {

    OnPriceSetCallback mPriceCallback;
    private EditText price;
    private String initialPrice;

    public void setPriceCallback(OnPriceSetCallback mPriceCallback) {
        this.mPriceCallback = mPriceCallback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onResume() {
        super.onResume();
        int height = getResources().getDimensionPixelSize(R.dimen.key_board_height);

        Window window = getDialog().getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, height);//Here!
        window.setGravity(Gravity.BOTTOM);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog_NoTitle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(initView());
        return dialog;
    }

    View initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.keyboard_layout, null);
        price = (EditText) view.findViewById(R.id.tv_price);
        if(!initialPrice.equals("0")){
            price.setText(initialPrice);
            price.setSelection(initialPrice.length());
        }
        final KeyboardUtil keyboardUtil = new KeyboardUtil(view);
        keyboardUtil.attachTo(price);
        keyboardUtil.setOnOkClick(new KeyboardUtil.OnOkClick() {
            @Override
            public void onOkClick() {
                dismiss();
            }
        });

        keyboardUtil.setOnCancelClick(new KeyboardUtil.onCancelClick() {
            @Override
            public void onCancellClick() {
                dismiss();
            }
        });
        return view;
    }

    public interface OnPriceSetCallback{
        void onPriceSet(String str);
    }

    @Override
    public void onDestroy() {
        mPriceCallback.onPriceSet(price.getText().toString());
        super.onDestroy();
    }

    public void setPrice(String str){
        initialPrice = str;
    }
}
