package com.smyy.sharetour.buyer.BackPacker.Travel;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.TravelBean;
import com.smyy.sharetour.buyer.require.OnRecyclerViewOnClickListener;
import com.smyy.sharetour.buyer.require.RecyclerViewDivider;
import com.smyy.sharetour.buyer.require.SimpleSelectActivity;
import com.smyy.sharetour.buyer.view.pickerview.TimePickerDialog;
import com.smyy.sharetour.buyer.view.pickerview.data.Type;
import com.smyy.sharetour.buyer.view.pickerview.listener.OnDateSetListener;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;
import com.yongchun.library.utils.ScreenUtils;
import com.yongchun.library.view.ImageSelectorActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.BackPacker.Travel
 * @fileName BackPackerSendTravelActivity
 * @date on 2018/4/23 0023 16:47
 * @describe 发行程
 */
public class BackPackerSendTravelActivity extends BaseMvpActivity {

    @BindView(R.id.travel_list)
    RecyclerView travelList;
    @BindView(R.id.travel_info_ok)
    TextView travelOk;

    private List<TravelBean.RouteBean> routes = new ArrayList<>();
    private static final int REQUEST_GET_PLACE = 10;
    private int click_position;
    private BackPackerSendTravelItemAdapter adapter;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private final String[] placeArray = {"日本", "法国"
            , "马来西亚", "新加坡", "巴西", "阿富汗",
            "美国", "澳大利亚", "墨西哥"
            , "阿根廷", "南非", "俄罗斯", "英国"};
    private TimePickerDialog mDialogYearMonthDay;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacker_send_travel;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("行程信息");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        travelList.setHasFixedSize(true);
        travelList.setLayoutManager(new LinearLayoutManager(BackPackerSendTravelActivity.this));
        travelList.addItemDecoration(new RecyclerViewDivider(BackPackerSendTravelActivity.this,
                LinearLayoutManager.VERTICAL, 30, R.color.window_background));
        demo();
        adapter = new BackPackerSendTravelItemAdapter(BackPackerSendTravelActivity.this, routes);
        adapter.setItemClickListener(new OnRecyclerViewOnClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                routes.add(position, new TravelBean.RouteBean());
                adapter.notifyItemChanged(position, 0);
                checkIsValid();
            }
        });
        adapter.setOnCountryClickListener(new BackPackerSendTravelItemAdapter.OnCountryClickListener() {
            @Override
            public void onCountryClick(int position) {
                click_position = position;
                selectCountry();
            }
        });
        adapter.setOnTimeClickListener(new BackPackerSendTravelItemAdapter.OnTimeClickListener() {
            @Override
            public void onTimeClick(int position) {
                click_position = position;
                mDialogYearMonthDay.show(getSupportFragmentManager(), "year_month_day");
            }
        });
        travelList.setAdapter(adapter);

        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setThemeColor(Color.WHITE)
                .setBackgroundColor(R.color.txt_gray_transparent)
                .setWheelItemTextSelectorColor(Color.BLACK)
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        String text = getDateToString(millseconds);
                        routes.get(click_position).setRouteTime(text);
                        if (click_position == routes.size() - 1) {
                            adapter.notifyItemChanged(click_position + 1, 0);
                        } else {
                            adapter.notifyItemChanged(click_position, 0);
                        }
                        checkIsValid();
                    }
                })
                .build();
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    private void selectCountry() {
        Intent intent = new Intent(BackPackerSendTravelActivity.this, SimpleSelectActivity.class);
        intent.putExtra("data", placeArray);
        intent.putExtra("title", "购买地");
        startActivityForResult(intent, REQUEST_GET_PLACE);
    }


    private void demo() {
        for (int i = 0; i < 2; i++) {
            TravelBean.RouteBean routeBean = new TravelBean.RouteBean();
            if (i == 1) {
                routeBean.setRouteCountry("中国");
                routeBean.setIsReturn(true);
            } else {
                routeBean.setIsReturn(false);
            }
            routes.add(routeBean);
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_GET_PLACE:
                    routes.get(click_position).setRouteCountry(placeArray[data.getIntExtra("index", 0)]);
                    adapter.notifyItemChanged(click_position, 0);
                    checkIsValid();
                    break;
            }
        }
    }

    private void checkIsValid() {
        for (TravelBean.RouteBean bean : routes) {
            if (bean.getRouteCountry() == null || bean.getRouteTime() == null) {
                travelOk.setEnabled(false);
                travelOk.setTextColor(getResources().getColor(R.color.white));
                return;
            }
        }
        travelOk.setEnabled(true);
        travelOk.setTextColor(getResources().getColor(R.color.txt_main));
    }


    @OnClick(R.id.travel_info_ok)
    public void onViewClicked() {
        BackPackerSendTravelActivity.this.showProgressDialog("发布中");
        travelOk.postDelayed(new Runnable() {
            @Override
            public void run() {
                BackPackerSendTravelActivity.this.hideProgressDialog();
                showPublishSuccessDialog();
            }
        }, 1000);
    }

    private void showPublishSuccessDialog() {
        CommonDialog.Builder builder = new CommonDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.layout_travel_publish_success)
                .setGravity(Gravity.CENTER)
                .setWidth(ScreenUtils.dip2px(getApplicationContext(),295))
                .addOnClickListener(R.id.close_publish_success_dialog, R.id.upload, R.id.know)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                        switch (view.getId()){
                            case R.id.upload:
                                if( ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                                        || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                                    //未获得权限
                                    initPermissions();
                                }else{
                                    //授予权限
                                    getPicture();
                                }
                            case R.id.close_publish_success_dialog:
                            case R.id.know:
                                commonDialog.dismiss();
                                finish();
                        }
                    }
                });

        builder.create().show();
    }

    private void getPicture()
    {
        ImageSelectorActivity.start(BackPackerSendTravelActivity.this, 1,
                ImageSelectorActivity.MODE_SINGLE, true,true,false, 0);
    }

    private void initPermissions() {
        List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
        permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        permissonItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取外部存储", R.drawable.permission_ic_storage));
        HiPermission.create(BackPackerSendTravelActivity.this).permissions(permissonItems).checkMutiPermission(new PermissionCallback() {
            @Override
            public void onClose() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onDeny(String permission, int position) {

            }

            @Override
            public void onGuarantee(String permission, int position) {

            }
        });
    }
}
