package com.smyy.sharetour.buyer.BackPacker.Travel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

    private List<TravelBean> travels = new ArrayList<>();
    private static final int REQUEST_GET_PLACE = 10;
    private int click_position;
    private BackPackerTravelItemAdapter adapter;
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
        adapter = new BackPackerTravelItemAdapter(BackPackerSendTravelActivity.this, travels);
        adapter.setItemClickListener(new OnRecyclerViewOnClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                travels.add(position, new TravelBean());
                adapter.notifyItemChanged(position, 0);
                checkIsValid();
            }
        });
        adapter.setOnCountryClickListener(new BackPackerTravelItemAdapter.OnCountryClickListener() {
            @Override
            public void onCountryClick(int position) {
                click_position = position;
                selectCountry();
            }
        });
        adapter.setOnTimeClickListener(new BackPackerTravelItemAdapter.OnTimeClickListener() {
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
                        travels.get(click_position).setTravelTime(text);
                        if (click_position == travels.size() - 1) {
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
            TravelBean bean = new TravelBean();
            if (i == 1) {
                bean.setTravelCountry("中国");
                bean.setIsReturn(true);
            } else {
                bean.setIsReturn(false);
            }
            travels.add(bean);
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
                    travels.get(click_position).setTravelCountry(placeArray[data.getIntExtra("index", 0)]);
                    adapter.notifyItemChanged(click_position, 0);
                    checkIsValid();
                    break;
            }
        }
    }

    private void checkIsValid() {
        for (TravelBean bean : travels) {
            if (bean.getTravelCountry() == null || bean.getTravelTime() == null) {
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
            }
        }, 1000);
    }
}
