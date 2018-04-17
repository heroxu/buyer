package com.smyy.sharetour.buyer.publish;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.publish
* @fileName PaymentDialog
* @date on 2018/4/16 0016 10:50
* @describe 付款选择界面
*/
public class PaymentDialog extends DialogFragment {

    private TextView price;
    private String initialPrice;
    private int mChecked_id = R.id.pay_weixin;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onResume() {
        super.onResume();
        int height = getResources().getDimensionPixelSize(R.dimen.pay_select_height);

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
        View view = inflater.inflate(R.layout.layout_payment, null);
        price = (TextView) view.findViewById(R.id.pay_price);
        SpannableString spanText = new SpannableString(getString(R.string.pay_price));

        SpannableString spanText1 = new SpannableString(getString(R.string.money_unit)
                + initialPrice);

        spanText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_gray_dark)),
                0,spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_price)),
                0,spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        price.setText(spanText);
        price.append(spanText1);

        ImageView close = (ImageView) view.findViewById(R.id.pay_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button button = (Button) view.findViewById(R.id.pay_confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        RadioGroup radioGroup = (RadioGroup)view.findViewById(R.id.pay_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mChecked_id = checkedId;
            }
        });

        return view;
    }

    private void startPayment() {
        switch (mChecked_id){
            case R.id.pay_weixin:
                break;
            case R.id.pay_zhifubao:
                break;
            case R.id.pay_bank:
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setPrice(String str){
        initialPrice = str;
    }

}
