package com.smyy.sharetour.buyer.module.my.contract;

import android.app.Application;

import com.smyy.sharetour.buyer.module.my.base.MyBasePresenter;
import com.smyy.sharetour.buyer.module.my.base.MyIBaseView;

public interface ISettingsContract {
    interface View extends MyIBaseView {
        void showCacheSize(String cacheSize);
    }

    abstract class Presenter extends MyBasePresenter<ISettingsContract.View, ISettingsContract.Model> {
        public Presenter(ISettingsContract.View view, ISettingsContract.Model model) {
            super(view, model);
        }

        public abstract void getCacheSize(Application application);
        public abstract void clearCache(Application application);
    }

    interface Model {
        String getCacheSize(Application application) throws Exception;
        boolean clearCache(Application application);
    }
}
