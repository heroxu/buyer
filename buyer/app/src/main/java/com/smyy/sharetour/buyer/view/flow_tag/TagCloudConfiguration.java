package com.smyy.sharetour.buyer.view.flow_tag;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.smyy.sharetour.buyer.R;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.view.flow_tag
* @fileName TagCloudConfiguration
* @date on 2018/4/17 0017 17:33
* @describe 标签流容器配置文件
*/
public class TagCloudConfiguration {

    private static final int DEFAULT_LINE_SPACING = 5;
    private static final int DEFAULT_TAG_SPACING = 10;
    private static final int DEFAULT_FIXED_COLUMN_SIZE = 3; //默认列数

    private int lineSpacing;
    private int tagSpacing;
    private int columnSize;
    private boolean isFixed;

   public TagCloudConfiguration(Context context,AttributeSet attrs){
       TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TagCloudLayout);
       try {
           lineSpacing = a.getDimensionPixelSize(R.styleable.TagCloudLayout_lineSpacing, DEFAULT_LINE_SPACING);
           tagSpacing = a.getDimensionPixelSize(R.styleable.TagCloudLayout_tagSpacing, DEFAULT_TAG_SPACING);
           columnSize = a.getInteger(R.styleable.TagCloudLayout_columnSize, DEFAULT_FIXED_COLUMN_SIZE);
           isFixed = a.getBoolean(R.styleable.TagCloudLayout_isFixed,false);
       } finally {
           a.recycle();
       }
   }

    public int getLineSpacing() {
        return lineSpacing;
    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public int getTagSpacing() {
        return tagSpacing;
    }

    public void setTagSpacing(int tagSpacing) {
        this.tagSpacing = tagSpacing;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setIsFixed(boolean isFixed) {
        this.isFixed = isFixed;
    }
}
