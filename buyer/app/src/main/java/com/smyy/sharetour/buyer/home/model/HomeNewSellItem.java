package com.smyy.sharetour.buyer.home.model;

import java.io.Serializable;

/**
 * Created by 许夏荣 on 2018/3/29.
 * desc:
 */

public class HomeNewSellItem  implements Serializable {
    public String sellTime;
    public String reserveNumber;
    public String productPic;
    public String productName;
    public String productPrice;
    public String productAddress;

    public HomeNewSellItem(String sellTime, String reserveNumber, String productPic, String productName, String productPrice, String productAddress) {
        this.sellTime = sellTime;
        this.reserveNumber = reserveNumber;
        this.productPic = productPic;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAddress = productAddress;
    }
}
