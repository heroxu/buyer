package com.smyy.sharetour.buyer;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.smyy.sharetour.buyer.base.BaseApplication;
import com.smyy.sharetour.buyer.db.MySQLiteOpenHelper;
import com.smyy.sharetour.buyer.greendao.DaoMaster;
import com.smyy.sharetour.buyer.greendao.DaoSession;
import com.smyy.sharetour.buyer.my.model.UserInfo;
import com.smyy.sharetour.buyer.network.rx.RxUtils;
import com.smyy.sharetour.buyer.util.PackageUtils;
import com.smyy.sharetour.buyer.util.SharePreferenceUtil;

import org.greenrobot.greendao.database.Database;

public class MyApplication extends BaseApplication {
    private int mAppVersionCode;//app版本号int
    private String mAppVersionName;//app版本号
    private String mProvider;//运营商
    private int mScreenWidth;
    private int mScreenHeight;
    private String mDpi;
    private UserInfo mUserInfo;
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

    public UserInfo getUserInfo() {
        if (mUserInfo == null) {
            mUserInfo = new SharePreferenceUtil(mApplication, Constants.MY_SP)
                    .getBeanValue(Constants.MY_SP_USER_INFO, UserInfo.class);

            if (mUserInfo == null) {//RTRT 测试代码
                mUserInfo = new UserInfo("悠闲的伪牧师", "一只大榴莲，两梳大香蕉。", "",
                        0, 0, 0, 0);
            }
        }
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
        new SharePreferenceUtil(mApplication, Constants.MY_SP)
                .writeBeanValue(Constants.MY_SP_USER_INFO, mUserInfo);
    }

}
