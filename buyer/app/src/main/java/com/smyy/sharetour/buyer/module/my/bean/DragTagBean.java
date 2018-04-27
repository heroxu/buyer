package com.smyy.sharetour.buyer.module.my.bean;

import com.heaven7.android.dragflowlayout.IDraggable;

/**
 * 如果想禁止某些Item拖拽请实现 {@link IDraggable} 接口
 */
public class DragTagBean implements IDraggable {
    public String name;
    public boolean draggable = true;

    public DragTagBean(String name) {
        this.name = name;
    }

    public DragTagBean(String name, boolean draggable) {
        this.name = name;
        this.draggable = draggable;
    }

    @Override
    public boolean isDraggable() {
        return draggable;
    }
}
