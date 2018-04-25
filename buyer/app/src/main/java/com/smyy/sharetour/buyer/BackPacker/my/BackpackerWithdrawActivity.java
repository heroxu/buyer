package com.smyy.sharetour.buyer.BackPacker.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smyy.sharetour.buyer.BackPacker.my.adapter.BackpackerWithdrawProgressActivity;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.module.my.AddBankcardActivity;
import com.smyy.sharetour.buyer.view.PasswordEditText;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnBindViewListener;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;
import com.yongchun.library.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.BackPacker.my
 * @fileName BackpackerWithdrawActivity
 * @date on 2018/4/25 0025 15:42
 * @describe 提现
 */
public class BackpackerWithdrawActivity extends BaseMvpActivity {

    private static final int ADD_BANK = 1;
    @BindView(R.id.bank_info)
    TextView bankInfo;
    @BindView(R.id.remain_sum)
    TextView remainSum;
    @BindView(R.id.bind_info)
    TextView bindInfo;
    @BindView(R.id.input)
    EditText input;
    @BindView(R.id.all_out)
    TextView allOut;
    @BindView(R.id.confirm)
    Button confirm;

    private int max_sum = 423;

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_backpacker_withdraw;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("提现");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, final Intent intent) {
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length()>0){
                    input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
                } else {
                    input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()!=0&&Integer.parseInt(s.toString().trim())>max_sum){
                    s.replace(0, s.toString().length(), Integer.toString(max_sum));
                }
                checkIsValid();
            }
        });

    }



    @OnClick({R.id.confirm, R.id.select_bank, R.id.all_out})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.confirm:
                showWithdrawDialog(423);
                break;
            case R.id.select_bank:
                startActivityForResult(new Intent(BackpackerWithdrawActivity.this, AddBankcardActivity.class), ADD_BANK);
                break;
            case R.id.all_out:
                input.setText(Integer.toString(max_sum));
                input.setSelection(Integer.toString(max_sum).length());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case ADD_BANK:
                    //ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                    setBankInfo();
                    break;
            }
        }
    }

    private void setBankInfo() {
        bankInfo.setText("华夏银行（尾号3848）");
        remainSum.setVisibility(View.VISIBLE);
        bindInfo.setText("");
        allOut.setVisibility(View.VISIBLE);
        input.setEnabled(true);
        input.setText("");
    }

    private void showWithdrawDialog(final int money) {
        CommonDialog.Builder builder = new CommonDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.layout_withdaw)
                .setWidth(ScreenUtils.getScreenWidth(BackpackerWithdrawActivity.this))
                .setGravity(Gravity.BOTTOM)
                .setHeight(ScreenUtils.dip2px(getApplicationContext(),375))
                .addOnClickListener(R.id.select_bank_ok, R.id.pay_close, R.id.add_new_bank)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                        switch (view.getId()) {
                            case R.id.pay_close:
                                commonDialog.dismiss();
                                break;

                            case R.id.add_new_bank:
                                startActivityForResult(new Intent(BackpackerWithdrawActivity.this, AddBankcardActivity.class), ADD_BANK);
                                commonDialog.dismiss();
                                break;

                            case R.id.select_bank_ok:
                                showInputPswDialog();
                                commonDialog.dismiss();
                                break;
                        }
                    }
                });

        builder.create().show();
    }

    private void showInputPswDialog() {
        CommonDialog.Builder builder = new CommonDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.layout_withdaw_input_pay_psw)
                .setGravity(Gravity.CENTER)
                .setWidth(ScreenUtils.dip2px(getApplicationContext(),335))
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder, final CommonDialog dialog) {
                        PasswordEditText editText = viewHolder.getView(R.id.pe_password);
                        editText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                if(s.toString().trim().length()==6){
                                    dialog.dismiss();
                                    startActivity(new Intent(BackpackerWithdrawActivity.this, BackpackerWithdrawProgressActivity.class));
                                    finish();
                                }
                            }
                        });
                    }
                });

        builder.create().show();
    }

    private void checkIsValid(){
        if(remainSum.getVisibility()==View.VISIBLE&&input.getText().toString().length()>0){
            confirm.setEnabled(true);
            confirm.setTextColor(getResources().getColor(R.color.txt_main));
        } else {
            confirm.setEnabled(false);
            confirm.setTextColor(getResources().getColor(R.color.white));
        }
    }
}
