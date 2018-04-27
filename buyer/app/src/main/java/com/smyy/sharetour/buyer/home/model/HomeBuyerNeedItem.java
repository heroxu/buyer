package com.smyy.sharetour.buyer.home.model;

import java.io.Serializable;

/**
 * Created by 许夏荣 on 2018/3/29.
 * desc:
 */

public class HomeBuyerNeedItem implements Serializable{
    public String imgUrl;
    public String title;
    public String subTitle;

    public HomeBuyerNeedItem(String imgUrl, String title, String subTitle) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.subTitle = subTitle;
    }
}
