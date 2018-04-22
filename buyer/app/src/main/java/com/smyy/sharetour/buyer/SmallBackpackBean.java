package com.smyy.sharetour.buyer;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by 伍振飞 on 2018/4/18 13:47
 * E-Mail Address：wuzf2012@sina.com
 */
public class SmallBackpackBean implements MultiItemEntity {
    public static final int GOODS_TYPE = 0; //商品
    public static final int GOODS_FAILURE_TYPE = 1;//失效商品
    private int type;
    public static class GoodsBean implements MultiItemEntity {

        @Override
        public int getItemType() {
            return 0;
        }
    }
    public static class GoodsFailureBean implements MultiItemEntity {

        @Override
        public int getItemType() {
            return 0;
        }
    }
    public SmallBackpackBean(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
