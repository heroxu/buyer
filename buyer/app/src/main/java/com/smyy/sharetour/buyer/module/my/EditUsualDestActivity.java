package com.smyy.sharetour.buyer.module.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.heaven7.android.dragflowlayout.DragAdapter;
import com.heaven7.android.dragflowlayout.DragFlowLayout;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.bean.DragTagBean;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.module.my.contract.IUserContract;
import com.smyy.sharetour.buyer.module.my.model.UserModel;
import com.smyy.sharetour.buyer.module.my.presenter.UserPresenter;
import com.smyy.sharetour.buyer.ui.SelectAreaCodeActivity;
import com.smyy.sharetour.buyer.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EditUsualDestActivity extends MyBaseMvpActivity<UserPresenter> implements IUserContract.View {

    private static final int REQ_CHOOSE_COUNTRY = 1;

    @BindView(R.id.dfl_my_usual_dest)
    DragFlowLayout dflUsualDest;
    @BindView(R.id.dfl_my_country)
    DragFlowLayout dflAddCountry;
    private List<DragTagBean> mUsualDes;

    private int MAX_USUAL_DEST = 6;//最多可选6个常出行地
    private List<String> mUsualDestList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_usual_dest;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("常出行");
        TextView toolbarRightTv = getToolbarRightTv();
        toolbarRightTv.setText("完成");
        toolbarRightTv.setVisibility(View.VISIBLE);
        toolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUsualDest();
                List<String> usualDestList = new ArrayList<>();
                for (DragTagBean tagBean :
                        mUsualDes) {
                    usualDestList.add(tagBean.name);
                }
                mPresenter.setUsualDest(usualDestList);
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        initView();
        mPresenter.getUserInfo();
    }

    private void initView() {
        //常出行
        dflUsualDest.setDragAdapter(new DragAdapter<DragTagBean>() {
            @Override
            public int getItemLayoutId() {
                return R.layout.item_tag_add_minus;
            }

            @Override
            public void onBindData(View itemView, int dragState, final DragTagBean data) {
                itemView.setTag(data);

                TextView tv = (TextView) itemView.findViewById(R.id.tv_name);
                ImageView ivMinus = (ImageView) itemView.findViewById(R.id.iv_add_minus);

                if (data == null || TextUtils.isEmpty(data.name)) {
                    tv.setText("");
                    tv.setBackground(tv.getResources().getDrawable(R.mipmap.ic_add_to_label));

                    ivMinus.setVisibility(View.GONE);

                    tv.setClickable(true);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivityForResult(SelectAreaCodeActivity.class, REQ_CHOOSE_COUNTRY);
                        }
                    });
                } else {
                    tv.setText(data.name);
                    tv.setBackground(tv.getResources().getDrawable(R.drawable.bg_tag_little_yellow));

                    ivMinus.setImageResource(R.mipmap.ic_minus);
                    ivMinus.setVisibility(View.VISIBLE);
                    ivMinus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DragFlowLayout.DragItemManager ususalDestItemManager = dflUsualDest.getDragItemManager();
                            dflUsualDest.getDragItemManager().removeItem(data);
                            dflAddCountry.getDragItemManager().addItem(data);

                            if (mUsualDes != null && mUsualDes.size() == MAX_USUAL_DEST) {
                                ususalDestItemManager.addItem(new DragTagBean(null, false));
                            }
                            saveUsualDest();
                        }
                    });

                    tv.setClickable(false);
                }
            }

            @NonNull
            @Override
            public DragTagBean getData(View itemView) {
                return (DragTagBean) itemView.getTag();
            }
        });

        //设置拖拽状态监听器
        dflUsualDest.setOnDragStateChangeListener(new DragFlowLayout.OnDragStateChangeListener() {
            @Override
            public void onDragStateChange(DragFlowLayout dfl, int dragState) {
                if (dragState == DragFlowLayout.DRAG_STATE_DRAGGABLE) {//停止拖拽
                    saveUsualDest();
                }
            }
        });

        //待选国家
        dflAddCountry.setDraggable(false);
        dflAddCountry.setDragAdapter(new DragAdapter<DragTagBean>() {
            @Override
            public int getItemLayoutId() {
                return R.layout.item_tag_add_minus;
            }

            @Override
            public void onBindData(View itemView, int dragState, final DragTagBean data) {
                itemView.setTag(data);

                TextView tv = (TextView) itemView.findViewById(R.id.tv_name);
                if (data == null || TextUtils.isEmpty(data.name)) {
                    tv.setText("");
                } else {
                    tv.setText(data.name);
                }

                ImageView ivAdd = (ImageView) itemView.findViewById(R.id.iv_add_minus);
                ivAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mUsualDes != null) {
                            if (mUsualDes.size() == MAX_USUAL_DEST) {
                                ToastUtils.showToast("最多可添加六个");
                                return;
                            } else if (mUsualDes.contains(data)) {
                                ToastUtils.showToast("请勿重复添加");
                                return;
                            } else if (mUsualDes.size() == MAX_USUAL_DEST - 1) {
                                dflAddCountry.getDragItemManager().removeItem(data);
                                DragFlowLayout.DragItemManager ususalDestItemManager = dflUsualDest.getDragItemManager();
                                ususalDestItemManager.removeItem(ususalDestItemManager.getItemCount() - 1);
                                ususalDestItemManager.addItem(ususalDestItemManager.getItemCount(), data);
                                saveUsualDest();
                                return;
                            }
                        }
                        dflAddCountry.getDragItemManager().removeItem(data);
                        DragFlowLayout.DragItemManager ususalDestItemManager = dflUsualDest.getDragItemManager();
                        ususalDestItemManager.addItem(ususalDestItemManager.getItemCount() - 1, data);
                        saveUsualDest();
                    }
                });
            }

            @NonNull
            @Override
            public DragTagBean getData(View itemView) {
                return (DragTagBean) itemView.getTag();
            }
        });
    }

    private void saveUsualDest() {
        DragFlowLayout.DragItemManager ususalDestItemManager = dflUsualDest.getDragItemManager();
        List<DragTagBean> items = ususalDestItemManager.getItems();
        DragTagBean lastTagBean = items.get(items.size() - 1);
        if (lastTagBean == null || TextUtils.isEmpty(lastTagBean.name)) {
            items.remove(items.size() - 1);
        }
        mUsualDes = items;
    }

    private void getFakeData() {

        List<String> list2 = new ArrayList<>();
        list2.add("日本");
        list2.add("韩国");
        list2.add("泰国");
        list2.add("新加坡");
        list2.add("马来西亚");
        list2.add("阿拉伯联合酋长国");
        list2.add("中国台湾");
        list2.add("中国澳门");
        list2.add("中国香港");

        List<DragTagBean> dragTagBeans2 = new ArrayList<>();
        for (String name :
                list2) {
            if (mUsualDestList == null || !mUsualDestList.contains(name)) {
                dragTagBeans2.add(new DragTagBean(name));
            }
        }
        dflAddCountry.getDragItemManager().addItems(dragTagBeans2);
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this, new UserModel());
    }

    @Override
    public void showUserInfo(UserInfoBean userInfo) {
        mUsualDestList = userInfo.getUsualDestList();

        if (mUsualDestList != null && mUsualDestList.size() != 0) {
            List<DragTagBean> dragTagBeans1 = new ArrayList<>();
            for (String name :
                    mUsualDestList) {
                dragTagBeans1.add(new DragTagBean(name));
            }
            dragTagBeans1.add(new DragTagBean(null, false));
            dflUsualDest.getDragItemManager().addItems(dragTagBeans1);
        }

        getFakeData();
    }

    @Override
    public void editUserInfoSuccess() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void editUserInfoFail() {
        ToastUtils.showToast("保存失败");
    }
}
