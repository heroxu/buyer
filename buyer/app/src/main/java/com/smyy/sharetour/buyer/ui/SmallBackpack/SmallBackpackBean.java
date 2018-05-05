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
    private List<GoodsBean> mGoodsBeans = new ArrayList<>();
    private List<GoodsFailureBean> mGoodsFailureBeans = new ArrayList<>();
    private String name;
    private int isSelect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

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

    public class GoodsBean{
        private String name;
        private int isSelect;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIsSelect() {
            return isSelect;
        }

        public void setIsSelect(int isSelect) {
            this.isSelect = isSelect;
        }
    }

    public class GoodsFailureBean{

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
