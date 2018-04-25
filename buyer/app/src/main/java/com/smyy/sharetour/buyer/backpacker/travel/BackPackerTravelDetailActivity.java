package com.smyy.sharetour.buyer.backpacker.travel;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.TravelBean;
import com.yongchun.library.view.ImageSelectorActivity;

import java.util.ArrayList;
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
 * @fileName BackPackerTravelDetailActivity
 * @date on 2018/4/24 0024 19:34
 * @describe 行程详情
 */
public class BackPackerTravelDetailActivity extends BaseMvpActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<TravelBean.RouteBean> routes = new ArrayList<>();
    private TravelBean travelBean = new TravelBean();
    private BackPackerTravelDetailItemAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacker_travel_detail;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        hideToolBarLayout(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(BackPackerTravelDetailActivity.this));
        demo();
        adapter = new BackPackerTravelDetailItemAdapter(BackPackerTravelDetailActivity.this, travelBean);
        recyclerView.setAdapter(adapter);
    }


    private void demo() {
        String[] countrys = {"中国", "澳大利亚", "印度尼西亚", "美国", "中国"};
        String[] times = {"04/04", "04/05", "04/06", "04/08", "04/12"};
        int[] imgs = {R.mipmap.img_ill_china, R.mipmap.img_ill_australia, R.mipmap.img_ill_indonesia, R.mipmap.img_ill_america, R.mipmap.img_ill_china};
        for (int i = 0; i < 5; i++) {
            TravelBean.RouteBean routeBean = new TravelBean.RouteBean();
            routeBean.setRouteCountry(countrys[i]);
            routeBean.setRouteTime(times[i]);
            routeBean.setImgId(imgs[i]);
            routes.add(routeBean);
        }
        travelBean.setRouteBeans(routes);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.delete_frame, R.id.edit_frame, R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.delete_frame:
                break;
            case R.id.edit_frame:
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }


    @OnClick(R.id.upload_air_ticket)
    public void onViewClicked() {
        if( ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            //未获得权限
            initPermissions();
        }else{
            //授予权限
            getPicture();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case ImageSelectorActivity.REQUEST_IMAGE:
                    //ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                    startActivity(new Intent(BackPackerTravelDetailActivity.this, BackPackerUploadTicketActivity.class));
                    break;
            }
        }
    }

    private void getPicture()
    {
        ImageSelectorActivity.start(BackPackerTravelDetailActivity.this, 1,
                ImageSelectorActivity.MODE_SINGLE, true,true,false, 0);
    }

    private void initPermissions() {
        List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
        permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        permissonItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取外部存储", R.drawable.permission_ic_storage));
        HiPermission.create(BackPackerTravelDetailActivity.this).permissions(permissonItems).checkMutiPermission(new PermissionCallback() {
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
