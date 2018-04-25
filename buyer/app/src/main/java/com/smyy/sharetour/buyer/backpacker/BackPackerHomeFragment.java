package com.smyy.sharetour.buyer.backpacker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smyy.sharetour.buyer.backpacker.require.BackPackerRequireDetailsActivity;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.RequireBean;
import com.smyy.sharetour.buyer.dialog.DialogUtils;
import com.smyy.sharetour.buyer.require.RecyclerViewDivider;
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
 * @package com.smyy.sharetour.buyer.BackPacker
 * @fileName BackPackerHomeFragment
 * @date on 2018/4/22 0022 14:36
 * @describe 背包客需求列表
 */
public class BackPackerHomeFragment extends BaseMvpFragment {

    @BindView(R.id.home_all_srl)
    SwipeRefreshLayout home_all_srl;
    @BindView(R.id.require_list)
    RecyclerView requireList;

    private List<RequireBean> requires = new ArrayList<>();

    private BackpackerHomeItemAdapter mAdapter;

    public static BackPackerHomeFragment getInstance(String title) {
        BackPackerHomeFragment sf = new BackPackerHomeFragment();
        return sf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_backpacker_home;
    }

    private void initListener() {

        home_all_srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mAdapter != null) {
//                    mAdapter.setData();
                    mAdapter.notifyDataSetChanged();
                }
                home_all_srl.setRefreshing(false);
            }
        });

    }


    private void demo() {
        for (int i = 0; i < 5; i++) {
            RequireBean bean = new RequireBean();
            bean.setRequire_disc("NIKE JORDAN 8 乔丹8系列 黑白牛皮运动鞋");
            bean.setRequire_time("2018-03-08");
            bean.setRequire_budget("9918.00");
            bean.setState(0);
            requires.add(bean);
        }
    }

    @Override
    protected void initData(Bundle bundle) {
        initListener();
        requireList.setHasFixedSize(true);
        requireList.setLayoutManager(new LinearLayoutManager(getActivity()));
        requireList.addItemDecoration(new RecyclerViewDivider(getActivity(),
                LinearLayoutManager.VERTICAL, 30, R.color.window_background));
        demo();
        mAdapter = new BackpackerHomeItemAdapter(getActivity(), requires);
        mAdapter.setItemClickListener(new BackpackerHomeItemAdapter.OnBackpackerHomeItemOnClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), BackPackerRequireDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(BackPackerRequireDetailsActivity.REQUIRE_KEY, requires.get(position));
                bundle.putBoolean(BackPackerRequireDetailsActivity.REQUIRE_TAKE_KEY, true);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }

            @Override
            public void OnItemContactClick(View v, int position) {

            }

            @Override
            public void OnItemTakeClick(View v, int position) {
                DialogUtils.showTwoBtnMsgBox(getActivity(),
                        null,
                        getString(R.string.send_trip_tip),
                        R.color.txt_hint,
                        getString(R.string.send_trip),
                        new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                            }
                        },
                        getString(R.string.cancel),
                        new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                commonDialog.dismiss();
                            }
                        });
            }
        });
        requireList.setAdapter(mAdapter);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

}