package com.smyy.sharetour.buyer.BackPacker.Travel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

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
                    progress_num.setBackground(getResources().getDrawable(R.mipmap.ic_xingcheng_send_success));
                    progress_num.setText("");
                    mask.setVisibility(View.GONE);
                    progressText.setText("已完成");
                    planeAnim.clearAnimation();
                    planeAnim.setVisibility(View.GONE);
                    ok.setEnabled(true);
                    break;
                case UPDATE:
                    progress_num.setText(progress + "%");
                    break;
            }
        }
    };

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
                break;
            case R.id.ok:
                break;
        }
    }
}
