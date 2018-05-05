package com.smyy.sharetour.buyer.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by 伍振飞 on 2018/4/18 13:47
 * E-Mail Address：wuzf2012@sina.com
 */
public class CollectionBean implements MultiItemEntity {
    public static final int GOODS_TYPE = 0; //商品
    public static final int VIDEO_TYPE = 1;//视频
    public static final int NOTES_TYPE = 2;//笔记
    private boolean isVisible;
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
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
    private int type;

    public CollectionBean(int type) {
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
