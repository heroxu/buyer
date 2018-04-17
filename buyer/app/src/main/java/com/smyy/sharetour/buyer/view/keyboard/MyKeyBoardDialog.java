package com.smyy.sharetour.buyer.view.keyboard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

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
        int height = (int) (getScreenProperty()[1]*0.36)+
                getResources().getDimensionPixelSize(R.dimen.key_board_input_height);

        Window window = getDialog().getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, height);//Here!
        window.setGravity(Gravity.BOTTOM);
    }

    public int[] getScreenProperty() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        /*float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)*/
        return new int[]{width, height};
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
        View view = inflater.inflate(R.layout.layout_keyboard, null);
        LinearLayout input_layout = (LinearLayout) view.findViewById(R.id.ll_price);
        int height = (int) (getScreenProperty()[1]*0.08);
        input_layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                height));
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
