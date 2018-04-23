package com.smyy.sharetour.buyer.ui.SmallBackpack;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 伍振飞 on 2018/4/18 13:47
 * E-Mail Address：wuzf2012@sina.com
 */
public class SmallBackpackBean implements MultiItemEntity {
    public static final int GOODS_TYPE = 0; //商品
    public static final int GOODS_FAILURE_TYPE = 1;//失效商品
    private int type;
    private boolean isSelect;
    private List<GoodsBean> mGoodsBeans = new ArrayList<>();
    private List<GoodsFailureBean> mGoodsFailureBeans = new ArrayList<>();

    public List<GoodsBean> getmGoodsBeans() {
        return mGoodsBeans;
    }

    public void setmGoodsBeans(List<GoodsBean> mGoodsBeans) {
        this.mGoodsBeans = mGoodsBeans;
    }

    public List<GoodsFailureBean> getmGoodsFailureBeans() {
        return mGoodsFailureBeans;
    }

    public void setmGoodsFailureBeans(List<GoodsFailureBean> mGoodsFailureBeans) {
        this.mGoodsFailureBeans = mGoodsFailureBeans;
    }

    public SmallBackpackBean(int type) {
        this.type = type;
        mGoodsBeans.add(new GoodsBean());
        mGoodsBeans.add(new GoodsBean());
        mGoodsBeans.add(new GoodsBean());
        mGoodsFailureBeans.add(new GoodsFailureBean());
        mGoodsFailureBeans.add(new GoodsFailureBean());
        mGoodsFailureBeans.add(new GoodsFailureBean());
        mGoodsFailureBeans.add(new GoodsFailureBean());
        mGoodsFailureBeans.add(new GoodsFailureBean());
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public class GoodsBean implements MultiItemEntity {
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        @Override
        public int getItemType() {
            return 0;
        }
    }

    public class GoodsFailureBean implements MultiItemEntity {

        @Override
        public int getItemType() {
            return 0;
        }
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
