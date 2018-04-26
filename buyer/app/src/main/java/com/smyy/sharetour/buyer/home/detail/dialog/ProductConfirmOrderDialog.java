package com.smyy.sharetour.buyer.home.detail.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import java.util.ArrayList;

/**
 * create by xuxiarong on 2018/4/26
 */
public class ProductConfirmOrderDialog extends DialogFragment implements View.OnClickListener{

    private TextView tv_confirm_order_cancel;
    private ImageView iv_confirm_order_product_pic;
    private TextView tv_confirm_order_product_title;
    private TextView tv_confirm_order_product_argument;
    private TextView tv_confirm_order_product_num;
    private ImageView iv_confirm_order_product_nation;
    private TextView tv_confirm_order_product_price;
    private TextView tv_confirm_order_product_price_rmb;
    private RecyclerView rv_confirm_order_color;
    private RecyclerView rv_confirm_order_size;
    private TextView tv_confirm_order_sell_num;
    private TextView tv_confirm_order_add_bag;
    private TextView tv_confirm_order_ok;
    private TextView tv_confirm_order_sub;
    private TextView tv_confirm_order_add;
    private EditText et_confirm_order_num;
    private int productNum = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Activity activity = getActivity();
//        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    @Override
    public void onResume() {
        super.onResume();
        int height = (int) (getScreenProperty()[1]*0.7)+
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

    private View initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_product_confirm_order, null);
        tv_confirm_order_cancel = (TextView) view.findViewById(R.id.tv_confirm_order_cancel);
        iv_confirm_order_product_pic = (ImageView) view.findViewById(R.id.iv_confirm_order_product_pic);
        iv_confirm_order_product_nation = (ImageView) view.findViewById(R.id.iv_confirm_order_product_nation);
        tv_confirm_order_product_title = (TextView) view.findViewById(R.id.tv_confirm_order_product_title);
        tv_confirm_order_product_argument = (TextView) view.findViewById(R.id.tv_confirm_order_product_argument);
        tv_confirm_order_product_num = (TextView) view.findViewById(R.id.tv_confirm_order_product_num);
        tv_confirm_order_product_price = (TextView) view.findViewById(R.id.tv_confirm_order_product_price);
        tv_confirm_order_product_price_rmb = (TextView) view.findViewById(R.id.tv_confirm_order_product_price_rmb);
        tv_confirm_order_sell_num = (TextView) view.findViewById(R.id.tv_confirm_order_sell_num);
        tv_confirm_order_add_bag = (TextView) view.findViewById(R.id.tv_confirm_order_add_bag);
        tv_confirm_order_ok = (TextView) view.findViewById(R.id.tv_confirm_order_ok);
        rv_confirm_order_color = (RecyclerView) view.findViewById(R.id.rv_confirm_order_color);
        rv_confirm_order_size = (RecyclerView) view.findViewById(R.id.rv_confirm_order_size);
        tv_confirm_order_sub = (TextView) view.findViewById(R.id.tv_confirm_order_sub);
        tv_confirm_order_add = (TextView) view.findViewById(R.id.tv_confirm_order_add);
        et_confirm_order_num = (EditText) view.findViewById(R.id.et_confirm_order_num);

        tv_confirm_order_add_bag.setOnClickListener(this);
        tv_confirm_order_ok.setOnClickListener(this);
        tv_confirm_order_sub.setOnClickListener(this);
        tv_confirm_order_add.setOnClickListener(this);
        tv_confirm_order_cancel.setOnClickListener(this);
        rv_confirm_order_color.setLayoutManager(new GridLayoutManager(getContext(),4));
        ArrayList<String> colors = new ArrayList<>();
        colors.add("炫酷黑");
        colors.add("迷你军绿色");
        colors.add("迷幻炫彩");
        colors.add("维多利亚紫");
        colors.add("天空蓝");
        colors.add("深灰");
        rv_confirm_order_color.setAdapter(new ProductConfirmOrderAdapter(getContext(),colors));

        rv_confirm_order_size.setLayoutManager(new GridLayoutManager(getContext(),4));
        ArrayList<String> sizes = new ArrayList<>();
        sizes.add("36");
        sizes.add("36.5");
        sizes.add("37");
        sizes.add("37.5");
        sizes.add("38");
        sizes.add("38.5");
        sizes.add("39");
        sizes.add("39.5");
        sizes.add("40");
        sizes.add("40.5");
        sizes.add("41");
        rv_confirm_order_size.setAdapter(new ProductConfirmOrderAdapter(getContext(),sizes));
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_confirm_order_cancel:
                this.dismiss();
                break;
            case R.id.tv_confirm_order_add_bag:
                this.dismiss();
                break;
            case R.id.tv_confirm_order_ok:
                ActivityLauncher.viewConfirmOrderActivity(this.getContext());
                this.dismiss();
                break;
            case R.id.tv_confirm_order_add:
                productNum ++;
                et_confirm_order_num.setText(productNum+"");
                break;
            case R.id.tv_confirm_order_sub:
                if(productNum == 0){
                    return;
                }
                productNum--;
                et_confirm_order_num.setText(productNum+"");
                break;
        }
    }
}
