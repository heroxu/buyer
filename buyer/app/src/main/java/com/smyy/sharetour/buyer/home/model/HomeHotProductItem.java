package com.smyy.sharetour.buyer.home.model;

import java.io.Serializable;

/**
 * Created by 许夏荣 on 2018/3/29.
 * desc:
 */

public class HomeHotProductItem implements Serializable {

    public String hotProductPic;
    public String hotProductName;
    public String hotProductPriceNow;
    public String hotProductPriceBefore;
    public String hotProductSellerAvatar;
    public String hotProductSellerName;

    public HomeHotProductItem(String hotProductPic, String hotProductName, String hotProductPriceNow, String hotProductPriceBefore, String hotProductSellerAvatar, String hotProductSellerName) {
        this.hotProductPic = hotProductPic;
        this.hotProductName = hotProductName;
        this.hotProductPriceNow = hotProductPriceNow;
        this.hotProductPriceBefore = hotProductPriceBefore;
        this.hotProductSellerAvatar = hotProductSellerAvatar;
        this.hotProductSellerName = hotProductSellerName;
    }
}
