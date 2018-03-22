package com.smyy.sharetour.buyer.base;

import android.support.v4.app.Fragment;

/**
 * Created by hasee on 2018/3/14.
 */

public class UmengFragment extends Fragment{
    @Override
    public void onResume() {
        super.onResume();
        pageLifeCycleStatistics(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        pageLifeCycleStatistics(false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        pageLifeCycleStatistics(!hidden);
    }

    /**
     * fragment 页面的生命统计
     * @param onPageStart
     */
    private void pageLifeCycleStatistics(boolean onPageStart) {
        //友盟统计页面activity中的 fragment的这样写
//        String pageName = getUmPageName();
//        if (onPageStart) {
//            MobclickAgent.onPageStart(pageName);
//            System.out.println(">>>  友盟统计 Fragment onPageStart  --> " + pageName);
//        } else {
//            MobclickAgent.onPageEnd(pageName);
//            System.out.println(">>>  友盟统计 Fragment onPageEnd  --> " + pageName);
//        }
    }

    /**
     * 获取Fragment页面的名字，默认是类名
     *
     * @return
     */
    protected String getUmPageName() {
        return getClass().getSimpleName();
    }
}
