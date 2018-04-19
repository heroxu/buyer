package com.smyy.sharetour.buyer;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

import com.smyy.sharetour.buyer.base.BaseApplication;
import com.smyy.sharetour.buyer.db.MySQLiteOpenHelper;
import com.smyy.sharetour.buyer.event.LoginEvent;
import com.smyy.sharetour.buyer.greendao.DaoMaster;
import com.smyy.sharetour.buyer.greendao.DaoSession;
import com.smyy.sharetour.buyer.module.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.network.rx.RxUtils;
import com.smyy.sharetour.buyer.util.PackageUtils;
import com.smyy.sharetour.buyer.util.SharePreferenceUtil;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.qcloud.sdk.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.database.Database;

public class MyApplication extends BaseApplication {
    private int mAppVersionCode;//app版本号int
    private String mAppVersionName;//app版本号
    private String mProvider;//运营商
    private int mScreenWidth;
    private int mScreenHeight;
    private String mDpi;
    private UserInfoBean mUserInfo;
    public static IStatistic mXqcStatistic;
    private static MyApplication mApplication;
    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = false;

    private static volatile DaoSession daoSession;

    public static MyApplication getApplication() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String packageName = getPackageName();
        String curProcessName = PackageUtils.getCurProcessName(getApplicationContext());
        mApplication = this;
        boolean isUIProcess = packageName.equals(curProcessName);
        if (isUIProcess) {
            initBaseParams();
            initGreenDao();
            TIMSdkConfig config = new TIMSdkConfig(Constant.SDK_APPID);
            config.enableLogPrint(true)
                    .setLogLevel(TIMLogLevel.values()[0]);
            //初始化imsdk
            TIMManager.getInstance().init(this.getApplicationContext(), config);
            //禁止服务器自动代替上报已读
        }
    }

    /**
     * 初始化基础参数
     */
    private void initBaseParams() {
        mAppVersionCode = PackageUtils.getVersionCode(this);
        mAppVersionName = PackageUtils.getVersionName(this);
        initTelephonyParams();

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        mScreenWidth = displayMetrics.widthPixels;
        mScreenHeight = displayMetrics.heightPixels;
        mDpi = mScreenWidth + "x" + mScreenHeight;
    }

    public void initTelephonyParams() {
        TelephonyManager telManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telManager != null) {
            mProvider = telManager.getSimOperatorName();
            //Returns the Service Provider Name (SPN).
//            if (PermissionBaseActivity.permissionCheck_READ_PHONE_STATE(this) == null) {
//                mImei = telManager.getDeviceId();
//            } else {
//                LogUtil.e(TAG, "need request_Check_READ_PHONE_STATE");
//            }
//            LogUtil.d(TAG, "mImei = " + mImei);
        }
    }

    public String getDpi() {
        return mDpi;
    }

    public String getAppVersionName() {
        return mAppVersionName;
    }

    /**
     * greenDao 数据库设置
     */
    public void initGreenDao() {
        RxUtils.asyncRun(new Runnable() {
            @Override
            public void run() {
                getDaoSession();
            }
        });
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            synchronized (MyApplication.class) {
                if (daoSession == null) {
                    MySQLiteOpenHelper helper = new MySQLiteOpenHelper(getContext(), ENCRYPTED ? "xmyy-encrypted" : "xmyy-db", null);
                    Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
                    daoSession = new DaoMaster(db).newSession();
                }
            }
        }
        return daoSession;
    }

    public UserInfoBean getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfoBean mUserInfo) {
        this.mUserInfo = mUserInfo;
    }

    public void saveUserInfo(UserInfoBean mUserInfo) {
        if (mUserInfo != null) {
            EventBus.getDefault().post(new LoginEvent(true));
        } else {
            EventBus.getDefault().post(new LoginEvent(false));
        }
        new SharePreferenceUtil(mApplication, SPConfig.NAME_USER_CACHE).writeBeanValue(SPConfig.KEY_USER_INFO, mUserInfo);
    }
}
