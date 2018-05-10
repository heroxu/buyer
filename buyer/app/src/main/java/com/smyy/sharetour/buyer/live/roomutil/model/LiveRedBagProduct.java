package com.smyy.sharetour.buyer.live.roomutil.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by hasee on 2018/4/23.
 */

public class LiveRedBagProduct implements MultiItemEntity {

    public static final int LIVE_NORMAL= 1;//视频
    public static final int LIVE_BOTTOM = 2;//笔记
    public int type;

    @Override
    public int getItemType() {
        return type;
    }

    public LiveRedBagProduct(int type) {
        this.type = type;
    }
}
