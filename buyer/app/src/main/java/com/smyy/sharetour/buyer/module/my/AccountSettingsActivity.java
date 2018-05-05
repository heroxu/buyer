package com.smyy.sharetour.buyer.module.my;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smyy.sharetour.buyer.Consts;
import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.module.my.base.MyBaseMvpActivity;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.module.my.contract.IUserContract;
import com.smyy.sharetour.buyer.module.my.model.UserModel;
import com.smyy.sharetour.buyer.module.my.presenter.UserPresenter;
import com.smyy.sharetour.buyer.module.order.OrderCommentActivity;
import com.smyy.sharetour.buyer.module.order.OrderHelper;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.StringUtil;
import com.yongchun.library.view.ImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

import static com.smyy.sharetour.buyer.base.BaseApplication.getContext;

public class AccountSettingsActivity extends MyBaseMvpActivity<UserPresenter> implements IUserContract.View {
    @BindView(R.id.iv_my_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_my_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_my_user_intro)
    TextView tvUserIntro;
    @BindView(R.id.lay_my_account_settings_buyer)
    View layBuyer;
    @BindView(R.id.lay_my_account_settings_packer)
    View layPacker;
    @BindView(R.id.tv_my_residence)
    TextView tvResidence;
    @BindView(R.id.tv_my_usual_dest)
    TextView tvUsualDest;

    private int mUserType = Consts.USER_TYPE_BUYER;

    public static final int REQ_EDIT_USER_INFO = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_account_settings;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText(getString(R.string.account_settings));
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        Bundle bundle = getBundle();
        if (bundle != null) {
            mUserType = bundle.getInt(Consts.USER_TYPE, Consts.USER_TYPE_BUYER);
        }

        switch (mUserType) {
            case Consts.USER_TYPE_BACK_PACKER:
                layBuyer.setVisibility(View.GONE);
                layPacker.setVisibility(View.VISIBLE);
                break;
            default:
                layBuyer.setVisibility(View.VISIBLE);
                layPacker.setVisibility(View.GONE);
                break;
        }
        initUserInfo();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    private void initUserInfo() {
        mPresenter.getUserInfo();
    }

    @OnClick({R.id.lay_my_avatar_item, R.id.lay_my_nickname, R.id.lay_my_user_intro,
            R.id.tv_my_shipping_address, R.id.tv_my_security_center,
            R.id.lay_my_residence, R.id.lay_my_usual_dest})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.lay_my_avatar_item:

                if (ContextCompat.checkSelfPermission(AccountSettingsActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(AccountSettingsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //未获得权限
                    initPermissions();
                } else {
                    //授予权限
                    getPicture();
                }
                break;

            case R.id.lay_my_nickname:
                startActivityForResult(EditNicknameActivity.class, REQ_EDIT_USER_INFO);
                break;

            case R.id.lay_my_user_intro:
                startActivityForResult(EditUserIntroActivity.class, REQ_EDIT_USER_INFO);
                break;

            case R.id.tv_my_shipping_address:
                startActivity(ShippingAddressListActivity.class);
                break;

            case R.id.tv_my_security_center:
                startActivity(SecurityCenterActivity.class);
                break;

            case R.id.lay_my_residence:

                break;

            case R.id.lay_my_usual_dest:
                startActivityForResult(EditUsualDestActivity.class, REQ_EDIT_USER_INFO);
                break;

            default:
                break;
        }
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this, new UserModel());
    }

    @Override
    public void showUserInfo(UserInfoBean userInfo) {
        String username = userInfo.getUsername();
        if (!TextUtils.isEmpty(username)) {
            tvNickname.setText(username);
        }
        String userIntro = userInfo.getUserIntro();
        if (!TextUtils.isEmpty(userIntro)) {
            tvUserIntro.setText(userIntro);
        } else {
            tvUserIntro.setText(R.string.please_introduce_yourself);
        }

        String filePath = userInfo.getAvatar();
        if (TextUtils.isEmpty(filePath)) {
            Glide.with(this).load(R.mipmap.user_avatar).into(ivAvatar);
        } else {
            File file = new File(filePath);
            Glide.with(getContext()).load(file).into(ivAvatar);
        }

        UserInfoBean.Residence residence = userInfo.getResidence();
        tvResidence.setText(StringUtil.connect(residence.getCountry(), " ",
                residence.getProvince(), " ", residence.getCity()));
        tvUsualDest.setText(StringUtil.connect(userInfo.getUsualDestList(), "、"));
    }

    @Override
    public void editUserInfoSuccess() {

    }

    @Override
    public void editUserInfoFail() {

    }


    private void getPicture() {
        ImageSelectorActivity.start(AccountSettingsActivity.this, 1,
                ImageSelectorActivity.MODE_SINGLE, true, true, true, 0);
    }

    private void initPermissions() {
        List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
        permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        permissonItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取外部存储", R.drawable.permission_ic_storage));
        HiPermission.create(AccountSettingsActivity.this).permissions(permissonItems).checkMutiPermission(new PermissionCallback() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case ImageSelectorActivity.REQUEST_IMAGE:
                    ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                    String avatarPath = images.get(0);
                    mPresenter.setAvatar(avatarPath);
                    Glide.with(this).load(avatarPath)
                            .crossFade().into(ivAvatar);
                    break;
                case REQ_EDIT_USER_INFO:
                    initUserInfo();
                    break;
            }
        }
    }
}
