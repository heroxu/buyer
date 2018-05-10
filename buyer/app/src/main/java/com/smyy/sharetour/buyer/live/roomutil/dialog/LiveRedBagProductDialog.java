package com.smyy.sharetour.buyer.live.roomutil.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.VideoView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.FountImageAdapter;
import com.smyy.sharetour.buyer.bean.FountBean;
import com.smyy.sharetour.buyer.home.detail.dialog.ProductConfirmOrderAdapter;
import com.smyy.sharetour.buyer.home.model.MessageList;
import com.smyy.sharetour.buyer.live.roomutil.model.LiveRedBagProduct;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import java.util.ArrayList;
import java.util.List;

/**
 * create by xuxiarong on 2018/4/26
 */
public class LiveRedBagProductDialog extends DialogFragment implements View.OnClickListener{


    private RecyclerView rv_live_red_bag_product;
    private TextView tv_live_total_product;

    private List<LiveRedBagProduct> mDatas = new ArrayList<>();
    private LiveRedBagProductAdapter mAdapter;
    private int mDatsSize;
    public static final String DATA_SIZE = "data_size";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Activity activity = getActivity();
//        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onResume() {
        super.onResume();
        int height;
        Window window = getDialog().getWindow();

        if(mDatsSize>5){
          height = (int) (getScreenProperty()[1]*0.7)+
                    getResources().getDimensionPixelSize(R.dimen.key_board_input_height);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, height);//Here!

        }else {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//Here!
        }

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
        mDatsSize = getArguments().getInt(DATA_SIZE,0);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(initView());
        return dialog;
    }

    private View initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_live_click_red_bag, null);
        rv_live_red_bag_product =(RecyclerView) view.findViewById(R.id.rv_live_red_bag_product);
        tv_live_total_product = (TextView) view.findViewById(R.id.tv_live_total_product);
        rv_live_red_bag_product.setLayoutManager(new LinearLayoutManager(getContext()));
        tv_live_total_product.setText(getString(R.string.live_red_bag_product_num,mDatsSize));
        mAdapter = new LiveRedBagProductAdapter(mDatas);
        rv_live_red_bag_product.setAdapter(mAdapter);
        return view;
    }

    public void setData(List<LiveRedBagProduct> datas){
        this.mDatas = datas;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm_order_cancel:
                this.dismiss();
                break;
        }
    }

    public class LiveRedBagProductAdapter extends BaseMultiItemQuickAdapter<LiveRedBagProduct,BaseViewHolder> {

        public LiveRedBagProductAdapter(@Nullable List<LiveRedBagProduct> data) {
            super(data);
            addItemType(LiveRedBagProduct.LIVE_NORMAL, R.layout.item_live_red_bag_product);
            addItemType(LiveRedBagProduct.LIVE_BOTTOM, R.layout.item_live_red_bag_bottom);
        }

        @Override
        protected void convert(BaseViewHolder helper, LiveRedBagProduct item) {
            switch (helper.getItemViewType()) {
                case LiveRedBagProduct.LIVE_NORMAL:

                    break;
                case LiveRedBagProduct.LIVE_BOTTOM:

                    break;
            }
        }

    }

}
