package com.smyy.sharetour.buyer.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.CollectionAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.CollectionBean;
import com.smyy.sharetour.buyer.ui.MyCollectionActivity;
import com.smyy.sharetour.buyer.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 伍振飞 on 2018/4/18 09:44
 * E-Mail Address：wuzf2012@sina.com
 */
public class CollectionFragment extends BaseMvpFragment {
    private static final String ARGS_DATA = "data.args";
    MyCollectionActivity mContext;
    @BindView(R.id.rv_collection)
    RecyclerView rvCollection;
    CollectionAdapter mCollectionAdapter;
    private int mFragmentTag;
    List<CollectionBean> data = new ArrayList<>();
    public static final int SELECT_TRUE = 1, SELECT_FALSE = 0;
    String[] parents = {"1", "2", "3", "4", "5", "6", "7"};

    public static CollectionFragment newInstance(String data) {
        Bundle args = new Bundle();
        args.putString(ARGS_DATA, data);
        CollectionFragment fragment = new CollectionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collection;
    }

    @Override
    protected void initData(Bundle bundle) {
        mFragmentTag = Integer.parseInt(getArguments().getString(ARGS_DATA));
        mContext = (MyCollectionActivity) getActivity();
        rvCollection.setLayoutManager(new LinearLayoutManager(getActivity()));
        initParents();
        mCollectionAdapter = new CollectionAdapter(data);
        rvCollection.setAdapter(mCollectionAdapter);
        //添加Android自带的分割线
        rvCollection.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mCollectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String titleRight = mContext.toolbarRightTv.getText().toString().trim();
                if ("管理".equals(titleRight)) {
                    ToastUtils.showToast(getActivity(), "item被点击了");
                } else {
                    changeSelectStatus(position);
                }
            }
        });
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    public void setVisible() {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setVisible(true);
        }
        mCollectionAdapter.notifyDataSetChanged();
    }

    public void setCheckAll() {
        if (isSelectAll()) {
            resetSelectAllItemStatusArray(SELECT_FALSE);
            ((MyCollectionActivity) getActivity()).changeUI(false);
        } else {
            resetSelectAllItemStatusArray(SELECT_TRUE);
            ((MyCollectionActivity) getActivity()).changeUI(true);
        }
    }

    public void setCheckAllFalse(){
        resetSelectAllItemStatusArray(SELECT_FALSE);
        ((MyCollectionActivity) getActivity()).changeUI(false);
    }

    public void setGone() {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setVisible(false);
        }
        mCollectionAdapter.notifyDataSetChanged();
    }

    /**
     * 更改当前Item选中的状态
     *
     * @param position
     */
    protected void changeSelectStatus(int position) {
        if (data.get(position).getIsSelect() == SELECT_TRUE) {
            data.get(position).setIsSelect(SELECT_FALSE);

        } else {
            data.get(position).setIsSelect(SELECT_TRUE);
        }
        //父集全选
        if (isSelectAll()) {
            mContext.changeUI(true);
        } else {
            mContext.changeUI(false);
        }
        mCollectionAdapter.notifyDataSetChanged();
    }


    /**
     * 全部选中logisticCodes的所有值
     * 全选中 arrValue = SELECT_TRUE
     * 全不选 arrValue = SELECT_FALSE
     */
    void resetSelectAllItemStatusArray(int arrValue) {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setIsSelect(arrValue);
        }
        mCollectionAdapter.notifyDataSetChanged();
    }

    /**
     * 判断是否全选
     *
     * @return
     */
    boolean isSelectAll() {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getIsSelect() == SELECT_FALSE) {
                return false;
            }
        }
        return true;
    }

    private void initParents() {
        for (int i = 0; i < parents.length; i++) {
            CollectionBean bean = new CollectionBean(mFragmentTag);
            bean.setName(parents[i]);
            data.add(bean);
        }
    }

    public void cancelCollection() {
        if (data == null || data.size() == 0) {
            ToastUtils.showToast(getActivity(), "没有数据可以删除");
            return;
        }
        List<CollectionBean> parentList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getIsSelect() == SELECT_TRUE) {
                parentList.add(data.get(i));
            }
        }
        data.removeAll(parentList);
        mCollectionAdapter.notifyDataSetChanged();
    }
}