package com.smyy.sharetour.buyer.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.GridAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.yongchun.library.view.ImagePreviewActivity;
import com.yongchun.library.view.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class ReportActivity extends BaseMvpActivity {
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.tv_report_other200)
    TextView tvReportOther200;
    @BindView(R.id.et_report_other)
    EditText etReportOther;
    @BindView(R.id.ll_report_other)
    LinearLayout llReportOther;
    @BindView(R.id.r_btn_advertising)
    RadioButton rBtnAdvertising;
    @BindView(R.id.r_btn_bad)
    RadioButton rBtnBad;
    @BindView(R.id.r_btn_garbage)
    RadioButton rBtnGarbage;
    @BindView(R.id.r_btn_violations)
    RadioButton rBtnViolations;
    @BindView(R.id.r_btn_other)
    RadioButton rBtnOther;
    @BindView(R.id.r_group)
    RadioGroup rGroup;
    private GridAdapter gridAdapter;
    private List<String> imagePaths = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_report;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("举报");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.r_btn_other == checkedId){
                    llReportOther.setVisibility(View.VISIBLE);
                }else {
                    llReportOther.setVisibility(View.GONE);
                }
            }
        });
        etReportOther.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                tvReportOther200.setText(etReportOther.length() + "/200");
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imgs = (String) parent.getItemAtPosition(position);
                if ("paizhao".equals(imgs)) {
                    if (ContextCompat.checkSelfPermission(ReportActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(ReportActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        //未获得权限
                        initPermissions();
                    } else {
                        //授予权限
                        getPicture();
                    }
                } else {
                    List<String> temps = new ArrayList<>();
                    temps.addAll(imagePaths);
                    if (imagePaths.contains("paizhao")) {
                        temps.remove(imagePaths.size() - 1);
                    }
                    ImagePreviewActivity.startPreview(ReportActivity.this, temps, position);
                }
            }
        });

        imagePaths.add("paizhao");
        gridAdapter = new GridAdapter(imagePaths, getApplicationContext(), null);
        gridView.setAdapter(gridAdapter);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
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

    private void getPicture() {
        ImageSelectorActivity.start(this, 10 - imagePaths.size(),
                ImageSelectorActivity.MODE_MULTIPLE, true, true, false, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case ImageSelectorActivity.REQUEST_IMAGE:
                    ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                    loadAdapter(images);
                    break;
            }
        }
    }

    private void loadAdapter(ArrayList<String> paths) {
        if (imagePaths != null) {
            imagePaths.remove(imagePaths.size() - 1);
        }
        if (paths.contains("paizhao")) {
            paths.remove("paizhao");
        }
        paths.add("paizhao");
        imagePaths.addAll(paths);
        gridAdapter = new GridAdapter(imagePaths, getApplicationContext(), new GridAdapter.ItemDelClickListener() {
            @Override
            public void itemDelClickListener(View v, int position) {
                if (imagePaths.size() == 9 && !imagePaths.contains("paizhao")) {
                    imagePaths.remove(position);
                    imagePaths.add("paizhao");
                } else {
                    imagePaths.remove(position);
                }
                gridAdapter.notifyDataSetChanged();
            }
        });
        gridView.setAdapter(gridAdapter);
    }

}
