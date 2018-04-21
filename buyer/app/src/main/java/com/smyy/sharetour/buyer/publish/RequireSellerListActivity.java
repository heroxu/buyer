package com.smyy.sharetour.buyer.publish;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.dialog.DialogUtils;
import com.smyy.sharetour.buyer.home.model.SearchBuyer;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.publish
* @fileName RequireSellerListActivity
* @date on 2018/4/21 0021 21:51
* @describe 需求指定买手列表
*/
public class RequireSellerListActivity extends BaseMvpActivity {


    @BindView(R.id.seller_info)
    TextView sellerInfo;
    @BindView(R.id.require_seller_list)
    RecyclerView requireSellerList;
    private List<SearchBuyer> sellers = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_require_seller_list;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("指定买手");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        SpannableString spanText = new SpannableString("已有");
        SpannableString spanText1 = new SpannableString("7");
        SpannableString spanText2 = new SpannableString("位买手抢单，请去指定一位");

        spanText.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_gray_dark)),
                0, spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_price)),
                0, spanText1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.txt_gray_dark)),
                0, spanText2.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        sellerInfo.setText(spanText);
        sellerInfo.append(spanText1);
        sellerInfo.append(spanText2);
        requireSellerList.setHasFixedSize(true);
        requireSellerList.setLayoutManager(new LinearLayoutManager(RequireSellerListActivity.this));
        requireSellerList.addItemDecoration(new RecyclerViewDivider(RequireSellerListActivity.this,
                LinearLayoutManager.VERTICAL, 30, R.color.window_background));

        demo();

        RequireSellerItemAdapter adapter = new RequireSellerItemAdapter(RequireSellerListActivity.this, sellers);
        adapter.setItemClickListener(new OnRequireSellerOnClickListener() {
            @Override
            public void OnItemContactClick(View v, int position) {
            }

            @Override
            public void OnItemPointClick(View v, int position) {
                DialogUtils.showTwoBtnMsgBox(RequireSellerListActivity.this,
                        "请确认您指定的买手",
                        "确认后将形成求购订单，为了保证买卖双方的利益，求购订单将无法取消哦",
                        "确定",
                        new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                commonDialog.dismiss();
                                RequireSellerListActivity.this.showResultDialog(true,"指定买手成功");
                                requireSellerList.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        RequireSellerListActivity.this.hideProgressDialog();
                                    }
                                }, 1000);
                            }
                        },
                        "取消",
                        new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                commonDialog.dismiss();
                            }
                        });
            }

            @Override
            public void OnItemClick(View v, int position) {

            }
        });
        requireSellerList.setAdapter(adapter);
    }


    private void demo() {
        for(int i=0; i<7; i++){
            SearchBuyer buyer = new SearchBuyer("as", "dsf");
            sellers.add(buyer);
        }
    }


    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


}
