package com.smyy.sharetour.uiframelib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hasee on 2018/3/14.
 */


public class UmengActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //友盟推送要求添加
        //此方法与统计分析sdk中统计日活的方法无关！请务必调用此方法！
        //如果不调用此方法，不仅会导致按照"几天不活跃"条件来推送失效，
        //还将导致广播发送不成功以及设备描述红色等问题发生。
//        PushAgent.getInstance(this).onAppStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //友盟统计页面activity
//        String pageName = getUmPageName();
//        if (!TextUtils.isEmpty(pageName)) {
//            System.out.println(">>>  友盟统计 Activity onPageStart  --> " + pageName);
//            MobclickAgent.onPageStart(pageName);
//        }
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //友盟统计页面
//        String pageName = getUmPageName();
//        if (!TextUtils.isEmpty(pageName)) {
//            System.out.println(">>>  友盟统计 Activity onPageEnd  --> " + pageName);
//            MobclickAgent.onPageEnd(pageName);
//        }
//        MobclickAgent.onPause(this);
    }

    /**
     * 获取页面的名字，默认是类名，若是activity + fragment 的情况，返回null就可以了
     *
     * @return
     */
    protected String getUmPageName() {
        return getClass().getSimpleName();
    }
}
