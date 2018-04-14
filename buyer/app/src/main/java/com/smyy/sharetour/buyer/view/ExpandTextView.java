package com.smyy.sharetour.buyer.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.util.CircleMovementMethod;

/**
 * Created by 伍振飞 on 2018/4/14 15:01
 * E-Mail Address：wuzf2012@sina.com
 */
public class ExpandTextView extends LinearLayout {
    public static final int DEFAULT_MAX_LINES = 3;
    private TextView contentText;
    private TextView textPlus;
    private LinearLayout llTextShow;

    private int showLines;

    private ExpandStatusListener expandStatusListener;
    private boolean isExpand;
    private ImageView textPlusRight;

    public ExpandTextView(Context context) {
        super(context);
        initView();
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initView();
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_magic_text, this);
        contentText = (TextView) findViewById(R.id.contentText);
        textPlusRight = (ImageView) findViewById(R.id.text_plus_right);
        llTextShow = (LinearLayout) findViewById(R.id.ll_text_show);
        if(showLines > 0){
            contentText.setMaxLines(showLines);
        }

        textPlus = (TextView) findViewById(R.id.textPlus);
        llTextShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textStr = textPlus.getText().toString().trim();
                if("展开".equals(textStr)){
                    contentText.setMaxLines(Integer.MAX_VALUE);
                    textPlus.setText("收起");
                    Glide.with(getContext()).load(R.mipmap.ic_home_arrow_top).into(textPlusRight);
                    setExpand(true);
                }else{
                    contentText.setMaxLines(showLines);
                    textPlus.setText("展开");
                    Glide.with(getContext()).load(R.mipmap.ic_home_arrow_down).into(textPlusRight);
                    setExpand(false);
                }
                //通知外部状态已变更
                if(expandStatusListener != null){
                    expandStatusListener.statusChange(isExpand());
                }
            }
        });
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ExpandTextView, 0, 0);
        try {
            showLines = typedArray.getInt(R.styleable.ExpandTextView_showLines, DEFAULT_MAX_LINES);
        }finally {
            typedArray.recycle();
        }
    }

    public void setText(final CharSequence content){
        contentText.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                // 避免重复监听
                contentText.getViewTreeObserver().removeOnPreDrawListener(this);

                int linCount = contentText.getLineCount();
                if(linCount > showLines){

                    if(isExpand){
                        contentText.setMaxLines(Integer.MAX_VALUE);
                        textPlus.setText("收起");
                        Glide.with(getContext()).load(R.mipmap.ic_home_arrow_top).into(textPlusRight);
                    }else{
                        contentText.setMaxLines(showLines);
                        textPlus.setText("展开");
                        Glide.with(getContext()).load(R.mipmap.ic_home_arrow_down).into(textPlusRight);
                    }
                    textPlus.setVisibility(View.VISIBLE);
                }else{
                    textPlus.setVisibility(View.GONE);
                }

                //Log.d("onPreDraw", "onPreDraw...");
                //Log.d("onPreDraw", linCount + "");
                return true;
            }


        });
        contentText.setText(content);
        contentText.setMovementMethod(new CircleMovementMethod(getResources().getColor(R.color.txt_main)));
    }

    public void setExpand(boolean isExpand){
        this.isExpand = isExpand;
    }

    public boolean isExpand(){
        return this.isExpand;
    }

    public void setExpandStatusListener(ExpandStatusListener listener){
        this.expandStatusListener = listener;
    }

    public static interface ExpandStatusListener{

        void statusChange(boolean isExpand);
    }
}
