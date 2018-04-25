package com.smyy.sharetour.buyer.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.EditText;

import com.smyy.sharetour.buyer.R;

/**
 * Created by hasee on 2018/4/24.
 */

public class PasswordEditText extends EditText {
    private Paint mLinePaint;//分割线画笔
    private Paint mPsdPointPaint;//密码画笔
    private int partLineColor;
    private int psdPointColor;
    private int mPasswordTextLength;//输入密码的长度 
    private int mWidth;
    private int mHeight;
    private static final int psdLength = 6;//密码长度
    private static final int psdPointR = 16;//小圆点半径


    public PasswordEditText(Context context) {
        this(context, null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PasswordEditText);
        partLineColor = typedArray.getColor(R.styleable.PasswordEditText_parting_line_color, Color.GRAY);
        psdPointColor = typedArray.getColor(R.styleable.PasswordEditText_point_color, Color.BLACK);
        init();
        typedArray.recycle();
    }

    private void init() {
        //设置长度
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(psdLength)});
        //设置获取焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //移除自带光标
        setCursorVisible(false);

        mLinePaint = new Paint();
        mLinePaint.setStrokeWidth(4);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(partLineColor);

        mPsdPointPaint = new Paint();
        mPsdPointPaint.setStrokeWidth(12);
        mPsdPointPaint.setAntiAlias(true);
        mPsdPointPaint.setColor(psdPointColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        setBackgroundResource(R.drawable.ab_common_btn_shape_97);
        drawPsdLine(canvas);
        drawPsdPoints(canvas);
    }


    /**
     * 画密码分割线
     *
     * @param canvas
     */
    private void drawPsdLine(Canvas canvas) {
        for (int i = 1; i < psdLength; i++) {
            float mX = mWidth * i / psdLength;
            canvas.drawLine(mX, 12, mX, mHeight - 12, mLinePaint);
        }
    }

    /**
     * 绘画密码点
     *
     * @param canvas
     */
    private void drawPsdPoints(Canvas canvas) {
        float cx, cy = mHeight / 2;
        float half = mWidth / psdLength;
        for (int i = 0; i < mPasswordTextLength; i++) {
            cx = half / 2 + half * i;
            canvas.drawCircle(cx, cy, psdPointR, mPsdPointPaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        mPasswordTextLength = text.toString().length();
        if (mPasswordTextLength == psdLength) {//这边可以做接口回调，或者用过eventBus的同仁们进行事件发布
            return;
        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        invalidate();
    }

    @Override
    public void append(CharSequence text, int start, int end) {
        super.append(text, start, end);
    }

    @Override
    public void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setCursorVisible(false);
    }
}
