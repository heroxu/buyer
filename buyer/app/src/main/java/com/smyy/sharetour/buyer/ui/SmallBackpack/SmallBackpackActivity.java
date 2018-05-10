package com.smyy.sharetour.buyer.ui.SmallBackpack;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.ToastUtils;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SmallBackpackActivity extends BaseMvpActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_settlement)
    Button btnSettlement;
    @BindView(R.id.cb_sm_all)
    CheckBox cbSmAll;
    private boolean isCheckOll;
    private SmallBackpackAdapter mAdapter;
    public static final int SELECT_TRUE = 1, SELECT_FALSE = 0;
    List<SmallBackpackBean> parentShopings = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_small_backpack;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("小背包");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initParents();
        initChilds();
        initNum();
        mAdapter = new SmallBackpackAdapter(parentShopings, this);
        recyclerView.setAdapter(mAdapter);
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_custom_divider_10));
        recyclerView.addItemDecoration(divider);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                changeSelectStatus(position);
            }
        });
    }

    /**
     * 更改当前Item选中的状态
     *
     * @param position
     */
    protected void changeSelectStatus(int position) {
        if (parentShopings.get(position).getIsSelect() == SELECT_TRUE) {
            parentShopings.get(position).setIsSelect(SELECT_FALSE);
            resetChildSelectAllItemStatusArray(SELECT_FALSE, parentShopings.get(position).getmGoodsBeans());

        } else {
            parentShopings.get(position).setIsSelect(SELECT_TRUE);
            resetChildSelectAllItemStatusArray(SELECT_TRUE, parentShopings.get(position).getmGoodsBeans());
        }
        //父集全选
        if (isSelectAll()) {
            cbSmAll.setChecked(true);
        } else {
            cbSmAll.setChecked(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initNum() {
    }

    private void initChilds() {

    }

    private void initParents() {
        parentShopings.add(new SmallBackpackBean(SmallBackpackBean.GOODS_TYPE));
        parentShopings.add(new SmallBackpackBean(SmallBackpackBean.GOODS_TYPE));
        parentShopings.add(new SmallBackpackBean(SmallBackpackBean.GOODS_FAILURE_TYPE));
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.btn_settlement, R.id.cb_sm_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_settlement:
                ActivityLauncher.viewConfirmOrderActivity(SmallBackpackActivity.this);
//                deleteCodes();
                break;
            case R.id.cb_sm_all:
                if (isSelectAll()) {
                    resetSelectAllItemStatusArray(SELECT_FALSE);
                    cbSmAll.setChecked(false);
                } else {
                    resetSelectAllItemStatusArray(SELECT_TRUE);
                    cbSmAll.setChecked(true);
                }
                break;
        }
    }

    /**
     * 判断是否全选
     *
     * @return
     */
    boolean isSelectAll() {
        for (int i = 0; i < parentShopings.size(); i++) {
            if (parentShopings.get(i).getIsSelect() == SELECT_FALSE) {
                return false;
            }
        }
        return true;
    }

    /**
     * 全部选中logisticCodes的所有值
     * 全选中 arrValue = SELECT_TRUE
     * 全不选 arrValue = SELECT_FALSE
     */
    void resetSelectAllItemStatusArray(int arrValue) {
        for (int i = 0; i < parentShopings.size(); i++) {
            parentShopings.get(i).setIsSelect(arrValue);
            for (int j = 0; j < parentShopings.get(i).getmGoodsBeans().size(); j++) {
                parentShopings.get(i).getmGoodsBeans().get(j).setIsSelect(arrValue);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 更改当前子集Item选中的状态
     *
     * @param
     */
    public void changeChildSelectStatus(List<SmallBackpackBean.GoodsBean> currentBoxings, SmallBackpackAdapter.GoodsAdapter adapter, CheckBox cbSelect, int parentPosition, int position) {
        if (currentBoxings.get(position).getIsSelect() == SELECT_TRUE) {
            currentBoxings.get(position).setIsSelect(SELECT_FALSE);
        } else {
            currentBoxings.get(position).setIsSelect(SELECT_TRUE);
        }
        adapter.notifyDataSetChanged();
        if (childIsSelectAll(currentBoxings)) {
            parentShopings.get(parentPosition).setIsSelect(SELECT_TRUE);
            cbSelect.setChecked(true);
        } else {
            parentShopings.get(parentPosition).setIsSelect(SELECT_FALSE);
            cbSelect.setChecked(false);
        }
        //父集全选
        if (isSelectAll()) {
            cbSmAll.setChecked(true);
        } else {
            cbSmAll.setChecked(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 判断子集是否全选
     *
     * @return
     */
    boolean childIsSelectAll(List<SmallBackpackBean.GoodsBean> currentBoxings) {
        for (int i = 0; i < currentBoxings.size(); i++) {
            if (currentBoxings.get(i).getIsSelect() == SELECT_FALSE) {
                return false;
            }
        }
        return true;
    }

    /**
     * 全部选中logisticCodes的所有值
     * 全选中 arrValue = SELECT_TRUE
     * 全不选 arrValue = SELECT_FALSE
     */
    void resetChildSelectAllItemStatusArray(int arrValue, List<SmallBackpackBean.GoodsBean> boxings) {
        for (int i = 0; i < boxings.size(); i++) {
            boxings.get(i).setIsSelect(arrValue);
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 删除当前选中的所有的码（手动）
     */
    void deleteCodes() {
        if (parentShopings == null || parentShopings.size() == 0) {
            ToastUtils.showToast(this, "没有数据可以删除");
            return;
        }
        List<SmallBackpackBean> parentList = new ArrayList<>();
//        List<List<Shop>> childList = new ArrayList<>();
        for (int i = 0; i < parentShopings.size(); i++) {
            if (parentShopings.get(i).getIsSelect() == SELECT_TRUE) {
                parentList.add(parentShopings.get(i));
//                deleteNum = deleteNum + childShopings.get(i).size();
//                childList.add(childShopings.get(i));
            } else {
                List<SmallBackpackBean.GoodsBean> boxings = parentList.get(i).getmGoodsBeans();
                List<SmallBackpackBean.GoodsBean> list = new ArrayList<>();
                for (int j = 0; j < boxings.size(); j++) {
                    if (boxings.get(j).getIsSelect() == SELECT_TRUE) {
                        list.add(boxings.get(j));
//                        deleteNum = deleteNum + 1;
                    }
                }
                boxings.removeAll(list);
            }
        }
        parentShopings.removeAll(parentList);
//        childShopings.removeAll(childList);
        mAdapter.notifyDataSetChanged();
//        setNum();
    }

}
