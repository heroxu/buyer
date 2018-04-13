package com.smyy.sharetour.buyer.publish;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
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
import com.yongchun.library.view.ImagePreviewActivity;
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
 * @fileName PublishRequireActivity
 * @date on 2018/4/10 0010 16:09
 * @describe 发布需求页面
 */
public class PublishRequireActivity extends BaseMvpActivity implements OnDateSetListener {

    private static final String TAG = PublishRequireActivity.class.getName();
    @BindView(R.id.disc_context)
    EditText disc_context;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.require_budget)
    TextView requireBudget;
    @BindView(R.id.require_finish_time)
    TextView requireFinishTime;
    @BindView(R.id.require_type)
    TextView requireType;
    @BindView(R.id.buy_place)
    TextView buyPlace;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.publish)
    Button publish;
    @BindView(R.id.identify_send)
    CheckBox identifySend;

    private static final int REQUEST_GET_TYPE = 10;
    private static final int REQUEST_GET_PLACE = 11;
    private static final int REQUEST_GET_ADDRESS = 12;
    private List<String> imagePaths = new ArrayList<>();
    private GridAdapter gridAdapter;
    private TimePickerDialog mDialogYearMonthDay;
    private MyKeyBoardDialog mkeyBoardDialog;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private final String[] typeArray = {"美妆彩妆", "美容护理", "服装鞋包", "数码科技", "精美饰品","母婴用品", "文化娱乐", "生活饮食", "运动户外", "其他商品"};
    private final String[] placeArray = {"日本", "法国"
            , "马来西亚", "新加坡", "巴西", "阿富汗",
            "美国", "澳大利亚", "墨西哥"
            , "阿根廷", "南非", "俄罗斯", "英国"};

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
        initUIObservable();
    }

    /**
    * @method initUIObservable
    * @param
    * @return
    * @description 注册界面控件观察器
    */
    private void initUIObservable() {
        Observable<CharSequence> discObservable = RxTextView.textChanges(disc_context).skip(1);
        Observable<CharSequence> budgetObservable = RxTextView.textChanges(requireBudget).skip(1);
        Observable<CharSequence> timeObservable = RxTextView.textChanges(requireFinishTime).skip(1);
        Observable<CharSequence> typeObservable = RxTextView.textChanges(requireType).skip(1);
        Observable<CharSequence> placeObservable = RxTextView.textChanges(buyPlace).skip(1);
        Observable<CharSequence> addressObservable = RxTextView.textChanges(address).skip(1);

        budgetObservable.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if(!TextUtils.isEmpty(requireBudget.getText())){
                    //如果预算超过一万默认打开
                    if(Integer.parseInt(requireBudget.getText().toString().substring(1))>=10000){
                        identifySend.setChecked(true);
                    } else {
                        identifySend.setChecked(false);
                    }
                }
            }
        });

        Observable.combineLatest(discObservable, budgetObservable,
                timeObservable, typeObservable, placeObservable, addressObservable,
                new Function6<CharSequence, CharSequence, CharSequence, CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence charSequence, CharSequence charSequence2, CharSequence
                            charSequence3, CharSequence charSequence4, CharSequence charSequence5, CharSequence charSequence6) throws Exception {
                        return isValidate();
                    }
                }).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if(aBoolean) {
                            publish.setEnabled(true);
                        } else {
                            publish.setEnabled(false);
                        }
                    }
        });


    }

    private void initUI() {
        requireBudget.setText(getResources().getString(R.string.money_unit)+0);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imgs = (String) parent.getItemAtPosition(position);
                if ("paizhao".equals(imgs) ){
                    ImageSelectorActivity.start(PublishRequireActivity.this, 10-imagePaths.size(),
                            ImageSelectorActivity.MODE_MULTIPLE, true,true,false, 0);
                }else{
                    List<String> temps = new ArrayList<>();
                    temps.addAll(imagePaths);
                    if(imagePaths.contains("paizhao"))
                    {
                        temps.remove(imagePaths.size()-1);
                    }
                    ImagePreviewActivity.startPreview(PublishRequireActivity.this, temps, position);
                }
            }
        });

        imagePaths.add("paizhao");
        gridAdapter = new GridAdapter(imagePaths, getApplicationContext(),null);
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
                case ImageSelectorActivity.REQUEST_IMAGE:
                    ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                    loadAdapter(images);
                    break;
               /* // 预览
                case REQUEST_PREVIEW_CODE:
                    //ArrayList<String> ListExtra = data.getStringArrayListExtra(PublishRequireActivity.EXTRA_RESULT);
                    //loadAdapter(ListExtra);
                    break;*/
                case REQUEST_GET_TYPE:
                    requireType.setText(typeArray[data.getIntExtra("index", 0)]);
                    break;
                case REQUEST_GET_PLACE:
                    buyPlace.setText(placeArray[data.getIntExtra("index", 0)]);
                    break;
                case REQUEST_GET_ADDRESS:
                    address.setText(placeArray[data.getIntExtra("index", 0)]);
                    break;
            }
        }
    }

    private void loadAdapter(ArrayList<String> paths){
        if (imagePaths!=null){
            imagePaths.remove(imagePaths.size()-1);
        }
        if (paths.contains("paizhao")){
            paths.remove("paizhao");
        }
        paths.add("paizhao");
        imagePaths.addAll(paths);
        gridAdapter  = new GridAdapter(imagePaths, getApplicationContext(),new GridAdapter.ItemDelClickListener() {
            @Override
            public void itemDelClickListener(View v, int position) {
                if(imagePaths.size()==9&&!imagePaths.contains("paizhao")){
                    imagePaths.remove(position);
                    imagePaths.add("paizhao");
                } else {
                    imagePaths.remove(position);
                }
                gridAdapter.notifyDataSetChanged();
            }
        });
        gridView.setAdapter(gridAdapter);
        if(isValidate()) publish.setEnabled(true);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }



    @OnClick({R.id.disc_context, R.id.require_budget, R.id.require_finish_time, R.id.require_type, R.id.address, R.id.publish, R.id.buy_place})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.disc_context:
                break;
            case R.id.require_budget:
                MyKeyBoardDialog dialog = new MyKeyBoardDialog();
                dialog.setPriceCallback(new MyKeyBoardDialog.OnPriceSetCallback() {
                    @Override
                    public void onPriceSet(String str) {
                        if(str==null||str.length()==0) str="0";
                        requireBudget.setText(getResources().getString(R.string.money_unit)+str);
                    }
                });
                dialog.setPrice(requireBudget.getText().toString().substring(1));
                dialog.show(getSupportFragmentManager(), null);
                break;
            case R.id.require_finish_time:
                mDialogYearMonthDay.show(getSupportFragmentManager(), "year_month_day");
                break;
            case R.id.require_type:
                intent = new Intent(PublishRequireActivity.this, SimpleSelectActivity.class);
                intent.putExtra("data", typeArray);
                intent.putExtra("title","商品类型");
                startActivityForResult(intent, REQUEST_GET_TYPE);
                break;
            case R.id.buy_place:
                intent = new Intent(PublishRequireActivity.this, SimpleSelectActivity.class);
                intent.putExtra("data", placeArray);
                intent.putExtra("title","购买地");
                startActivityForResult(intent, REQUEST_GET_PLACE);
                break;
            case R.id.address:
                intent = new Intent(PublishRequireActivity.this, SimpleSelectActivity.class);
                intent.putExtra("data", placeArray);
                intent.putExtra("title","收货地址");
                startActivityForResult(intent, REQUEST_GET_ADDRESS);
                break;
            case R.id.publish:
                break;
        }
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

    private boolean isValidate(){
        //判断描述有效性，字符不得少于10个
        boolean isDiscValid = !TextUtils.isEmpty(disc_context.getText())&&disc_context.getText().toString().length()>9;
        boolean isImageValid = imagePaths.size()>1;
        boolean isBudgetValid = !TextUtils.isEmpty(requireBudget.getText())
                &&!requireBudget.getText().toString().equals(getResources().getString(R.string.money_unit)+0);
        boolean isTimeValid = !TextUtils.isEmpty(requireFinishTime.getText());
        boolean isTypeValid = !TextUtils.isEmpty(requireType.getText());
        boolean isPlaceValid = !TextUtils.isEmpty(buyPlace.getText());
        boolean isAddressValid = !TextUtils.isEmpty(address.getText());
        return isDiscValid&&isImageValid&&isBudgetValid&&isTimeValid&&isTypeValid&&isPlaceValid&&isAddressValid;
    }
}
