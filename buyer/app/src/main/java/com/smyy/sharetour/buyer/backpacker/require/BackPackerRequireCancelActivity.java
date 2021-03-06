package com.smyy.sharetour.buyer.backpacker.require;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import butterknife.OnClick;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.BackPacker.Require
* @fileName BackPackerRequireCancelActivity
* @date on 2018/4/23 0023 14:23
* @describe 背包客撤销接需求
*/
public class BackPackerRequireCancelActivity extends BaseMvpActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacker_require_cancel;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("取消需求");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {

    }


    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }



    @OnClick({R.id.cancel_no, R.id.cancel_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel_confirm:
                setResult(RESULT_OK);
            case R.id.cancel_no:
                finish();
                break;
        }
    }
}
