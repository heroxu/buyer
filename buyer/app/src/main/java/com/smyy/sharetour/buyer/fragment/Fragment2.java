package com.smyy.sharetour.buyer.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dtr.zxing.activity.CaptureActivity;
import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.ToastUtils;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpFragment;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.db.Test2;
import com.smyy.sharetour.buyer.util.ActivityLauncher;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by hasee on 2018/3/15.
 */

public class Fragment2 extends BaseMvpFragment {
    public static final int REQUEST_CODE_SCAN = 3301;
    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment2;
    }

    @Override
    protected void initData(Bundle bundle) {
        changeTitleBarColor();
    }

    private void changeTitleBarColor() {
        StatusBarAdapter.changeStatusBarColor(getActivity(), getResources().getColor(R.color.colorAccent));
    }

    List<Test2> mTest2s = new ArrayList<>();

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                ActivityLauncher.viewTest1Activity(getActivity());
                break;
            case R.id.button2:
                ActivityLauncher.viewTest2Activity(getActivity());
                break;
            case R.id.button3:
                for (long i = 0; i < 10; i++) {
                    Test2 mTest2 = new Test2();
                    mTest2.setPhoneNum("18680445592");
                    mTest2.setPhoto("http://blog.csdn.net/zhjianglin/article/details/78773028");
                    mTest2s.add(mTest2);
                }
                MyApplication.getApplication().getDaoSession().getTest2Dao()
                        .insertOrReplaceInTx(mTest2s);
                break;
            case R.id.button4:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class),REQUEST_CODE_SCAN);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_SCAN) {
            if (resultCode == Activity.RESULT_OK) {
                String result = intent.getStringExtra(CaptureActivity.BUNDLE_RESULT);
                ToastUtils.showToast(result);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                ToastUtils.showToast("扫码取消");
            } else {
                ToastUtils.showToast("扫码错误");
            }
        }
    }
}
