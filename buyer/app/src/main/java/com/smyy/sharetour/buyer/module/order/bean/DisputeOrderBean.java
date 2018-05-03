package com.smyy.sharetour.buyer.module.order.bean;

import java.io.Serializable;

public class DisputeOrderBean implements Serializable {
    private String orderId;//售后订单ID
    private int orderStatus;
    private String sellerId;
    private String sellerName;
    private String sellerAvatar;
    private String buyerId;
    private String buyerName;
    private String buyerAvatar;
    private String goodsId;
    private String goodsName;
    private String goodsSpec;
    private String receiveDeadline;
    private String goodsPrice;
    private int goodsCount;
    private String goodsPic;
    private int goodsType;

    public DisputeOrderBean(String orderId, int orderStatus, String sellerId, String sellerName, String sellerAvatar, String buyerId, String buyerName, String buyerAvatar, String goodsId, String goodsName, String goodsSpec, String receiveDeadline, String goodsPrice, int goodsCount, String goodsPic, int goodsType) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.sellerAvatar = sellerAvatar;
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.buyerAvatar = buyerAvatar;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsSpec = goodsSpec;
        this.receiveDeadline = receiveDeadline;
        this.goodsPrice = goodsPrice;
        this.goodsCount = goodsCount;
        this.goodsPic = goodsPic;
        this.goodsType = goodsType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAvatar() {
        return sellerAvatar;
    }

    public void setSellerAvatar(String sellerAvatar) {
        this.sellerAvatar = sellerAvatar;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAvatar() {
        return buyerAvatar;
    }

    public void setBuyerAvatar(String buyerAvatar) {
        this.buyerAvatar = buyerAvatar;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }
}
