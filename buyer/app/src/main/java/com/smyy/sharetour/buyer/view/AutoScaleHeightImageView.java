package com.smyy.sharetour.buyer.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 宽度由父控件确定，高度由图片成比例缩放确定
 */
public class AutoScaleHeightImageView extends AppCompatImageView {

    public AutoScaleHeightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable drawable = getDrawable();    // 获取ImageView的src属性指定的图片
        if (drawable != null) {
            int picutureWidth = drawable.getMinimumWidth();        // 获取图片的宽
            int pictureHeight = drawable.getMinimumHeight();    // 获取图片的高

            // 计算图片高和宽的比例
            float scale = (float) pictureHeight / picutureWidth;

            // 计算width对应比例的高
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width * scale);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
