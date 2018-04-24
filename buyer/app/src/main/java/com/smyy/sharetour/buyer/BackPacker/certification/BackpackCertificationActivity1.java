package com.smyy.sharetour.buyer.BackPacker.certification;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.yongchun.library.view.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;


public class BackpackCertificationActivity1 extends BaseMvpActivity {

    @BindView(R.id.line_1)
    ImageView line1;
    @BindView(R.id.line_2)
    ImageView line2;
    @BindView(R.id.c_btn_1)
    CheckBox cBtn1;
    @BindView(R.id.c_btn_2)
    CheckBox cBtn2;
    @BindView(R.id.c_btn_3)
    CheckBox cBtn3;
    @BindView(R.id.tv_certification_1)
    CheckedTextView tvCertification1;
    @BindView(R.id.tv_certification_2)
    CheckedTextView tvCertification2;
    @BindView(R.id.tv_certification_3)
    CheckedTextView tvCertification3;
    @BindView(R.id.tv_positive)
    TextView tvPositive;
    @BindView(R.id.ll_positive)
    LinearLayout llPositive;
    @BindView(R.id.tv_reverse)
    TextView tvReverse;
    @BindView(R.id.ll_reverse)
    LinearLayout llReverse;
    @BindView(R.id.et_bc_name)
    EditText etBcName;
    @BindView(R.id.et_bc_num)
    EditText etBcNum;
    @BindView(R.id.iv_positive)
    ImageView ivPositive;
    @BindView(R.id.iv_reverse)
    ImageView ivReverse;
    private String CAMERA;
    private String REVERSE_TYPE = "reverse";
    private String POSITIVE_TYPE = "positive";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpack_certification_1;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("认证");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        hideToolBarDividerLine(true);
        initView();
    }

    private void initView() {
        line1.setEnabled(false);
        line2.setEnabled(false);
        cBtn1.setEnabled(true);
        tvCertification1.setEnabled(true);

    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.f_btn_positive, R.id.f_btn_reverse, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.f_btn_positive:
                if (ContextCompat.checkSelfPermission(BackpackCertificationActivity1.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(BackpackCertificationActivity1.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //未获得权限
                    initPermissions();
                } else {
                    CAMERA = POSITIVE_TYPE;
                    //授予权限
                    ImageSelectorActivity.start(this, 1, ImageSelectorActivity.MODE_SINGLE, true, true, true, 0);
                }
                break;
            case R.id.f_btn_reverse:
                if (ContextCompat.checkSelfPermission(BackpackCertificationActivity1.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(BackpackCertificationActivity1.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //未获得权限
                    initPermissions();
                } else {
                    CAMERA = REVERSE_TYPE;
                    //授予权限
                    ImageSelectorActivity.start(this, 1, ImageSelectorActivity.MODE_SINGLE, true, true, true, 0);
                }
                break;
            case R.id.btn_next:
                ActivityLauncher.viewBackpackCertificationActivity2(this);
                break;
        }
    }

    private void initPermissions() {
        List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
        permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        permissonItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取外部存储", R.drawable.permission_ic_storage));
        HiPermission.create(this).permissions(permissonItems).checkMutiPermission(new PermissionCallback() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case ImageSelectorActivity.REQUEST_IMAGE:
                    ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                    if (CAMERA.equals(POSITIVE_TYPE)) {
                        Glide.with(BackpackCertificationActivity1.this).load(images.get(0)).crossFade().into(ivPositive);
                        llPositive.setVisibility(View.GONE);
                    } else {
                        Glide.with(BackpackCertificationActivity1.this).load(images.get(0)).crossFade().into(ivReverse);
                        llReverse.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    }
}
