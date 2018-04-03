package com.smyy.sharetour.buyer.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.smyy.sharetour.buyer.R;

/**
 * create by xuxiarong on 2018/04/03
 */
public class OnLineLinearLayout extends LinearLayout {

    private View first;
    private View second;
    private View thrid;
    private int count = 0;
    public OnLineLinearLayout(Context context) {
        this(context,null);
    }

    public OnLineLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OnLineLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        View parent = LayoutInflater.from(context).inflate(R.layout.view_on_line_linear_layout, this);
        if(parent == null){
            throw new IllegalArgumentException("load the parent view error");
        }
        first = parent.findViewById(R.id.first);
        second = parent.findViewById(R.id.second);
        thrid = parent.findViewById(R.id.third);
        if(first == null || second== null || thrid== null){
            throw new IllegalArgumentException("load the child view error");
        }
        new CountDownTimer(Integer.MAX_VALUE,500){

            @Override
            public void onTick(long l) {
                count++;
                ViewGroup.LayoutParams firstParams = first.getLayoutParams();
                ViewGroup.LayoutParams secondParams = second.getLayoutParams();
                ViewGroup.LayoutParams thridParams = thrid.getLayoutParams();

                switch (count){
                    case 1:
                        firstParams.height = 10;
                        secondParams.height = 15;
                        thridParams.height = 20;
                        break;
                    case 2:
                        firstParams.height = 15;
                        secondParams.height = 20;
                        thridParams.height = 10;
                        break;
                    case 3:
                        firstParams.height = 20;
                        secondParams.height = 10;
                        thridParams.height = 15;
                        count = 0;
                        break;

                    default:
                        break;
                }
                first.setLayoutParams(firstParams);
                second.setLayoutParams(secondParams);
                thrid.setLayoutParams(thridParams);
            }

            @Override
            public void onFinish() {
                this.cancel();
            }
        }.start();
    }



}
