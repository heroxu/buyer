package com.smyy.sharetour.buyer.base.bean.response;

import java.io.Serializable;

/**
 * Created by 许夏荣 on 2018/3/29.
 * desc：网络请求的公共基础类
 */

public class BaseResponseBean<T extends BaseResponseBean> implements Serializable{
    protected int code;
    protected String msg;
    protected T data;

    protected T getData(){
        return data;
    }
}
