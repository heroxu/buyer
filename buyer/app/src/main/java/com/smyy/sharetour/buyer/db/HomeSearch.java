package com.smyy.sharetour.buyer.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * create by xuxiarong on 2018/4/11
 */
@Entity
public class HomeSearch {

    @Id
    private Long id;
    private String searchContent;
    @Generated(hash = 440629083)
    public HomeSearch(Long id, String searchContent) {
        this.id = id;
        this.searchContent = searchContent;
    }
    @Generated(hash = 1625948198)
    public HomeSearch() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSearchContent() {
        return this.searchContent;
    }
    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }


}
