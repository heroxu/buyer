package com.smyy.sharetour.buyer.publish;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.adapter.GridAdapter;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.view.pickerview.TimePickerDialog;
import com.smyy.sharetour.buyer.view.pickerview.data.Type;
import com.smyy.sharetour.buyer.view.pickerview.listener.OnDateSetListener;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.publish
 * @fileName PublishRequireActivity
 * @date on 2018/4/10 0010 16:09
 * @describe 发布需求页面
 */
public class PublishRequireActivity extends BaseMvpActivity implements View.OnClickListener, OnDateSetListener {

    @BindView(R.id.et_context)
    EditText etContext;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.require_budget)
    TextView requireBudget;
    @BindView(R.id.require_finish_time)
    TextView requireFinishTime;
    @BindView(R.id.require_type)
    TextView requireType;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.publish)
    Button publish;

    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private List<String> imagePaths = new ArrayList<>();
    private GridAdapter gridAdapter;
    private TimePickerDialog mDialogYearMonthDay;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(R.string.publish_require);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        initUI();
    }

    private void initUI() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String imgs = (String) parent.getItemAtPosition(position);
              /*  if ("paizhao".equals(imgs) ){
                    PhotoPickerIntent intent = new PhotoPickerIntent(MainActivity.this);
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setShowCarema(true); // 是否显示拍照
                    intent.setMaxTotal(6); // 最多选择照片数量，默认为6
                    intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                }else{
                    Toast.makeText(PublishRequireActivity.this,"1"+position,Toast.LENGTH_SHORT).show();
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(MainActivity.this);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(imagePaths);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }*/
            }
        });

        imagePaths.add("paizhao");
        gridAdapter = new GridAdapter(imagePaths, getApplicationContext());
        gridView.setAdapter(gridAdapter);

        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setThemeColor(Color.WHITE)
                .setBackgroundColor(R.color.txt_gray_transparent)
                .setWheelItemTextSelectorColor(Color.BLACK)
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                   // ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    //loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                   // ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                   //loadAdpater(ListExtra);
                    break;
            }
        }
    }

    private void loadAdpater(ArrayList<String> paths){
        if (imagePaths!=null&& imagePaths.size()>0){
            imagePaths.clear();
        }
        if (paths.contains("paizhao")){
            paths.remove("paizhao");
        }
        paths.add("paizhao");
        imagePaths.addAll(paths);
        gridAdapter  = new GridAdapter(imagePaths, getApplicationContext());
        gridView.setAdapter(gridAdapter);
        try{
            JSONArray obj = new JSONArray(imagePaths);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }



    @OnClick({R.id.et_context, R.id.require_budget, R.id.require_finish_time, R.id.require_type, R.id.address, R.id.publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_context:
                break;
            case R.id.gridView:
                break;
            case R.id.require_budget:
                break;
            case R.id.require_finish_time:
                mDialogYearMonthDay.show(getSupportFragmentManager(), "year_month_day");
                break;
            case R.id.require_type:
                break;
            case R.id.address:
                break;
            case R.id.publish:
                break;
        }
    }

    @Override
    public void onClick(View v) {
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        String text = getDateToString(millseconds);
        requireFinishTime.setText(text);
    }
}
