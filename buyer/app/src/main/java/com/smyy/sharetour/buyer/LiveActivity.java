package com.smyy.sharetour.buyer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.smyy.sharetour.buyer.live.roomutil.dialog.LiveRedBagProductDialog;
import com.smyy.sharetour.buyer.live.roomutil.model.LiveRedBagProduct;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

import java.util.ArrayList;


public class LiveActivity extends FragmentActivity {

    protected TXLivePusher                  mTXLivePusher;
    private ImageView iv_live_red_bag;
//    protected TXLivePushListenerImpl        mTXLivePushListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        //mPlayerView 即 step1 中添加的界面 view
        TXCloudVideoView mView = (TXCloudVideoView) findViewById(R.id.video_view);
        iv_live_red_bag = (ImageView) findViewById(R.id.iv_live_red_bag);
//
////创建 player 对象
//        TXLivePlayer mLivePlayer = new TXLivePlayer(this);
//
////关键 player 对象与界面 view
//        mLivePlayer.setPlayerView(mView);
//
//        String flvUrl = "http://2157.liveplay.myqcloud.com/live/2157_xxxx.flv";
//
//        // 设置填充模式
//        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
//// 设置画面渲染方向
//        mLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);
//        mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_LIVE_FLV); //推荐 FLV

//        TXLivePusher mLivePusher = new TXLivePusher(this);
//        TXLivePushConfig mLivePushConfig = new TXLivePushConfig();
//        mLivePusher.setConfig(mLivePushConfig);
//
////        String rtmpUrl = "rtmp://2157.livepush.myqcloud.com/live/xxxxxx";
////        mLivePusher.startPusher(rtmpUrl);
//        mLivePusher.startScreenCapture();

        initLivePusher();
        if (mTXLivePusher != null) {
            mView.setVisibility(View.VISIBLE);
            mTXLivePusher.startCameraPreview(mView);
        }

        iv_live_red_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LiveRedBagProductDialog liveRedBagProductDialog = new LiveRedBagProductDialog();
                ArrayList<LiveRedBagProduct> liveRedBagProducts = new ArrayList<>();
                liveRedBagProducts.add(new LiveRedBagProduct(LiveRedBagProduct.LIVE_NORMAL));
                liveRedBagProducts.add(new LiveRedBagProduct(LiveRedBagProduct.LIVE_NORMAL));
                liveRedBagProducts.add(new LiveRedBagProduct(LiveRedBagProduct.LIVE_NORMAL));
                liveRedBagProducts.add(new LiveRedBagProduct(LiveRedBagProduct.LIVE_NORMAL));
                liveRedBagProducts.add(new LiveRedBagProduct(LiveRedBagProduct.LIVE_BOTTOM));
                liveRedBagProductDialog.setData(liveRedBagProducts);
                Bundle bundle = new Bundle();
                bundle.putInt(LiveRedBagProductDialog.DATA_SIZE,liveRedBagProducts.size());
                liveRedBagProductDialog.setArguments(bundle);
                liveRedBagProductDialog.show(getSupportFragmentManager(), null);
            }
        });

    }

    protected void initLivePusher() {
        if (mTXLivePusher == null) {
            TXLivePushConfig config = new TXLivePushConfig();
            config.setPauseFlag(TXLiveConstants.PAUSE_FLAG_PAUSE_VIDEO | TXLiveConstants.PAUSE_FLAG_PAUSE_AUDIO);
            mTXLivePusher = new TXLivePusher(this);
            mTXLivePusher.setConfig(config);
            mTXLivePusher.setBeautyFilter(TXLiveConstants.BEAUTY_STYLE_SMOOTH, 5, 3, 2);

//            mTXLivePushListener = new TXLivePushListenerImpl();
//            mTXLivePusher.setPushListener(mTXLivePushListener);
        }
    }

}
