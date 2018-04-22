package com.smyy.sharetour.buyer.module.order;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.GridAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.view.RatingBar;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;
import com.yongchun.library.view.ImagePreviewActivity;
import com.yongchun.library.view.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.publish
 * @fileName OrderCommentActivity
 * @date on 2018/4/10 0010 16:09
 * @describe 订单评价页面
 */
public class OrderCommentActivity extends BaseMvpActivity {

    private static final int REQUEST_CODE_CAMERA = 101;
    @BindView(R.id.disc_context)
    EditText disc_context;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.good_rating)
    RatingBar goodRating;
    @BindView(R.id.good_quality)
    LinearLayout goodQuality;
    @BindView(R.id.seller_rating)
    RatingBar sellerRating;
    @BindView(R.id.seller_service)
    LinearLayout sellerService;

    private List<String> imagePaths = new ArrayList<>();
    private GridAdapter gridAdapter;
    private boolean is_append = false;
    private TextView toolbarRightTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_comment;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(R.string.publish_comment);
        toolbarRightTv = getToolbarRightTv();
        toolbarRightTv.setText(getString(R.string.publish));
        toolbarRightTv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        is_append = intent.getBooleanExtra("append", false);
        initUI(intent);
    }


    private void initUI(Intent intent) {
        //判断是否为追加评价
        if (is_append) {
            goodQuality.setVisibility(View.GONE);
            sellerService.setVisibility(View.GONE);
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imgs = (String) parent.getItemAtPosition(position);
                if ("paizhao".equals(imgs)) {
                    if (ContextCompat.checkSelfPermission(OrderCommentActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(OrderCommentActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
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
                    ImagePreviewActivity.startPreview(OrderCommentActivity.this, temps, position);
                }
            }
        });

        imagePaths.add("paizhao");
        gridAdapter = new GridAdapter(imagePaths, getApplicationContext(), null);
        gridView.setAdapter(gridAdapter);

        toolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!is_append) {
                    int good_score = goodRating.getStar();
                    int seller_score = sellerRating.getStar();
                }
                CommonDialog.Builder builder = new CommonDialog.Builder(getSupportFragmentManager())
                        .setLayoutRes(R.layout.layout_comment_success)
                        .setGravity(Gravity.CENTER)
                        .addOnClickListener(R.id.view_orders)
                        .setOnViewClickListener(new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                commonDialog.dismiss();
                                finish();
                                ActivityLauncher.viewMyCommentActivity(OrderCommentActivity.this);
                            }
                        });

                builder.create().show();

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
                    loadAdapter(images);
                    break;
               /* // 预览
                case REQUEST_PREVIEW_CODE:
                    //ArrayList<String> ListExtra = data.getStringArrayListExtra(PublishRequireActivity.EXTRA_RESULT);
                    //loadAdapter(ListExtra);
                    break;*/
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

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    private void getPicture() {
        ImageSelectorActivity.start(OrderCommentActivity.this, 10 - imagePaths.size(),
                ImageSelectorActivity.MODE_MULTIPLE, true, true, false, 0);
    }

    private void initPermissions() {
        List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
        permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        permissonItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取外部存储", R.drawable.permission_ic_storage));
        HiPermission.create(OrderCommentActivity.this).permissions(permissonItems).checkMutiPermission(new PermissionCallback() {
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

}
