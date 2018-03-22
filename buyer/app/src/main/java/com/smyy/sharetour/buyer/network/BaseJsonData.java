package com.smyy.sharetour.buyer.network;

/**
 * json 基类
 */
public abstract class BaseJsonData<T> {
    //获取uimodel,不需要转换直接返回this
    public abstract T obtainUIModel();

}
