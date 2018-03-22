package com.smyy.sharetour.buyer.fragment;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * Created by hasee on 2018/3/15.
 */

public class Fragment1 extends BaseMvpFragment {
    public static final int REQUEST_CODE_SCAN = 3301;
    @BindView(R.id.fake_status_bar)
    View fake_status_bar;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment1;
    }

    @Override
    protected void initData(Bundle bundle) {
        //处理状态栏
        StatusBarAdapter.updateStatusHeight(getActivity(), fake_status_bar, null);
        changeTitleBarColor();
        initPermissions();
    }

    private void initPermissions() {
        List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
        permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机",R.drawable.permission_ic_camera));
        HiPermission.create(getActivity()).permissions(permissonItems).checkMutiPermission(new PermissionCallback() {
            @Override
            public void onClose() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onDeny(String permission, int position) {

            }

            @Override
            public void onGuarantee(String permission, int position) {

            }
        });
    }

    private void changeTitleBarColor() {
        StatusBarAdapter.changeStatusBarColor(getActivity(), getResources().getColor(R.color.colorAccent));
    }
}
