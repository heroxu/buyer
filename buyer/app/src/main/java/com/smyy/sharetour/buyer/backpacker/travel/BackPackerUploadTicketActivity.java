package com.smyy.sharetour.buyer.backpacker.travel;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.yongchun.library.view.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.BackPacker.Travel
 * @fileName BackPackerUploadTicketActivity
 * @date on 2018/4/24 0024 22:49
 * @describe 上传登机牌
 */
public class BackPackerUploadTicketActivity extends BaseMvpActivity {


    private static final int SUCCESS = 100;
    private static final int UPDATE = 101;
    @BindView(R.id.mask)
    ImageView mask;
    @BindView(R.id.plane_anim)
    ImageView planeAnim;
    @BindView(R.id.result_img)
    ImageView resultImg;
    @BindView(R.id.progress)
    TextView progress_num;
    @BindView(R.id.progress_text)
    TextView progressText;
    @BindView(R.id.ok)
    TextView ok;

    private Animation rotate;
    private int progress = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacker_upload_ticket;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("上传登机牌");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        initUI(intent);
    }


    private void initUI(Intent intent) {
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        rotate.setInterpolator(new LinearInterpolator());
        startUploadTicket();
    }

    private void startUploadTicket() {
        //模拟图片上传进度
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (progress == 100) {//图片上传完成
                        handler.sendEmptyMessage(SUCCESS);
                        return;
                    }
                    progress+=5;
                    handler.sendEmptyMessage(UPDATE);
                    try {
                        Thread.sleep(200);  //暂停0.2秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    uploadTicketSuccess();
                    break;
                case UPDATE:
                    progress_num.setText(progress + "%");
                    break;
            }
        }
    };

    private void uploadTicketSuccess() {
        progress_num.setVisibility(View.GONE);
        mask.setVisibility(View.GONE);
        progressText.setText("已完成");
        planeAnim.clearAnimation();
        planeAnim.setVisibility(View.GONE);
        resultImg.setVisibility(View.VISIBLE);
        ok.setEnabled(true);
    }

    private void retryUploadTicket() {
        progress = 0;
        progress_num.setText(progress + "%");
        progress_num.setVisibility(View.VISIBLE);
        mask.setVisibility(View.VISIBLE);
        progressText.setText("上传中");
        planeAnim.setAnimation(rotate);
        planeAnim.startAnimation(rotate);
        planeAnim.setVisibility(View.VISIBLE);
        resultImg.setVisibility(View.GONE);
        ok.setEnabled(false);
        startUploadTicket();
    }

    @Override
    protected void onResume() {
        if(progress<100) {
            if (rotate != null) {
                planeAnim.startAnimation(rotate);
            } else {
                planeAnim.setAnimation(rotate);
                planeAnim.startAnimation(rotate);
            }
        }
        super.onResume();
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void onPause() {
        planeAnim.clearAnimation();
        super.onPause();
    }

    @OnClick({R.id.retry_upload, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.retry_upload:
                getPicture();
                break;
            case R.id.ok:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case ImageSelectorActivity.REQUEST_IMAGE:
                    //ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                    retryUploadTicket();
                    break;
            }
        }
    }

    private void getPicture()
    {
        ImageSelectorActivity.start(BackPackerUploadTicketActivity.this, 1,
                ImageSelectorActivity.MODE_SINGLE, true,true,false, 0);
    }

    private void initPermissions() {
        List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
        permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        permissonItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取外部存储", R.drawable.permission_ic_storage));
        HiPermission.create(BackPackerUploadTicketActivity.this).permissions(permissonItems).checkMutiPermission(new PermissionCallback() {
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
