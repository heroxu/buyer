package com.smyy.sharetour.buyer.publish;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.GridAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.view.keyboard.MyKeyBoardDialog;
import com.smyy.sharetour.buyer.view.pickerview.TimePickerDialog;
import com.smyy.sharetour.buyer.view.pickerview.data.Type;
import com.smyy.sharetour.buyer.view.pickerview.listener.OnDateSetListener;
import com.yongchun.library.view.ImageSelectorActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function6;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.publish
* @fileName SimpleSelectActivity
* @date on 2018/4/13 0013 13:08
* @describe 选择信息界面
*/
public class SimpleSelectActivity extends BaseMvpActivity{

    @BindView(R.id.info_list)
    ListView infoList;

    private String[] datas;
    private TextView mTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_simple_select_layout;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        mTitle = title;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        datas = intent.getStringArrayExtra("data");
        mTitle.setText(intent.getStringExtra("title"));
        initUI();
    }


    private void initUI() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SimpleSelectActivity.this,
                android.R.layout.simple_list_item_1,datas);
        infoList.setAdapter(adapter);
        infoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("index", (int)position);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }




}
