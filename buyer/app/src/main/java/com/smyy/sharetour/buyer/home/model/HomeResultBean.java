package com.smyy.sharetour.buyer.home.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.smyy.sharetour.buyer.base.bean.response.SingleModelBean;

/**
 * Created by 许夏荣 on 2018/3/29.
 * desc:
 */

public class HomeResultBean extends SingleModelBean {

    public String mainTitle;
    public String subTitle;
    public int viewType;
    public boolean hasMore;
    public boolean hasChange;

    public HomeResultBean(String mainTitle, String subTitle, int viewType,boolean hasMore, boolean hasChange) {
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.viewType = viewType;
        this.hasMore = hasMore;
        this.hasChange = hasChange;
    }
}
