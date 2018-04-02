package com.smyy.sharetour.buyer;


import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by 伍振飞 on 2018/3/15 17:44
 * E-Mail Address：wuzf2012@sina.com
 */
public class FountBean extends MultiItemEntity {
    public static final int VIDEO_TYPE = 1;//视频
    public static final int NOTES_TYPE = 2;//笔记
    public static final int BANNER_TYPE = 3; //轮播
    private int type;
    private String photo;
    List<FImage> fountIamges;

    public List<FImage> getFountIamges() {
        return fountIamges;
    }

    public void setFountIamges(List<FImage> fountIamges) {
        this.fountIamges = fountIamges;
    }

    public FountBean(int type, String photo) {
        this.type = type;
        this.photo = photo;
    }

    public FountBean(int type, String photo, List<FImage> fountIamges) {
        this.type = type;
        this.photo = photo;
        this.fountIamges = fountIamges;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
