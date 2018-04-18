package com.smyy.sharetour.buyer.module.my.bean;

import java.io.Serializable;


public class ShippingAddressBean implements Serializable {
    private boolean isDefault;
    private String name;
    private String phone;
    private String address;

    public ShippingAddressBean(boolean isDefault, String name, String phone, String address) {
        this.isDefault = isDefault;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ShippingAddressBean{" +
                "isDefault=" + isDefault +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
