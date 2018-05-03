package com.smyy.sharetour.buyer.module.order.bean;

import java.io.Serializable;

public class OrderGoodsInfo implements Serializable {
    private String goodsId;
    private String goodsName;
    private String goodsSpec;
    private String receiveDeadline;
    private String goodsPrice;
    private int goodsCount;
    private String goodsPic;
    private int goodsType;
    private String disputeOrderId;//对应售后订单ID

    public OrderGoodsInfo(String goodsId, String goodsName, String goodsSpec, String receiveDeadline, String goodsPrice, int goodsCount, String goodsPic, int goodsType, String disputeOrderId) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsSpec = goodsSpec;
        this.receiveDeadline = receiveDeadline;
        this.goodsPrice = goodsPrice;
        this.goodsCount = goodsCount;
        this.goodsPic = goodsPic;
        this.goodsType = goodsType;
        this.disputeOrderId = disputeOrderId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getReceiveDeadline() {
        return receiveDeadline;
    }

    public void setReceiveDeadline(String receiveDeadline) {
        this.receiveDeadline = receiveDeadline;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getDisputeOrderId() {
        return disputeOrderId;
    }

    public void setDisputeOrderId(String disputeOrderId) {
        this.disputeOrderId = disputeOrderId;
    }
}
