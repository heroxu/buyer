package com.smyy.sharetour.buyer.my.presenter;

import android.content.Context;

import com.smyy.sharetour.buyer.my.bean.UserInfoBean;
import com.smyy.sharetour.buyer.my.contract.IUserContract;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter extends IUserContract.Presenter {
    public UserPresenter(IUserContract.View view, IUserContract.Model model) {
        super(view, model);
    }

    @Override
    public void getUserInfo(final Context application) {
        Observable.create(new ObservableOnSubscribe<UserInfoBean>() {
            @Override
            public void subscribe(ObservableEmitter<UserInfoBean> e) throws Exception {
                if (mModel != null) {
                    UserInfoBean userInfo = mModel.getUserInfo();
                    mModel.saveUserInfo(application, userInfo);
                    e.onNext(userInfo);
                    e.onComplete();
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mView != null) {
                            mView.showProgressDialog();
                        }
                    }

                    @Override
                    public void onNext(UserInfoBean userInfo) {
                        mView.showUserInfo(userInfo);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (mView != null) {
                            mView.hideProgressDialog();
                        }
                    }
                });

    }

    @Override
    public void getUserInfoCache(final Context application) {
        Observable.create(new ObservableOnSubscribe<UserInfoBean>() {
            @Override
            public void subscribe(ObservableEmitter<UserInfoBean> e) throws Exception {
                if (mModel != null) {
                    UserInfoBean userInfo = mModel.getUserInfoCache(application);
                    e.onNext(userInfo);
                    e.onComplete();
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(UserInfoBean userInfo) {
                        if (mView != null) {
                            if (userInfo == null) {
                                getUserInfo(application);
                            } else {
                                mView.showUserInfo(userInfo);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void setUserName(final Context application, final String userName) {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
                    boolean result = mModel.setUserName(application, userName);
                    e.onNext(result);
                    e.onComplete();
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mView != null) {
                            mView.showProgressDialog();
                        }
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if (mView != null) {
                            if (result) {
                                mView.finish();
                            } else {
                                mView.showDialog("修改失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (mView != null) {
                            mView.hideProgressDialog();
                        }
                    }
                });
    }

    @Override
    public void setUserIntro(final Context application, final String userIntro) {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                if (mModel != null) {
                    boolean result = mModel.setUserIntro(application, userIntro);
                    e.onNext(result);
                    e.onComplete();
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (mView != null) {
                            mView.showProgressDialog();
                        }
                    }

                    @Override
                    public void onNext(Boolean result) {
                        if (mView != null) {
                            if (result) {
                                mView.finish();
                            } else {
                                mView.showDialog("修改失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (mView != null) {
                            mView.hideProgressDialog();
                        }
                    }
                });

    }
}
