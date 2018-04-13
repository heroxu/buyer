package com.smyy.sharetour.buyer.module.my.base;

import java.lang.ref.WeakReference;

public abstract class MyBasePresenter<V extends MyIBaseView, M> {

    private WeakReference<V> mWeakReferenceView;
    private WeakReference<M> mWeakReferenceModel;
    public V mView;
    public M mModel;


    public MyBasePresenter(V view, M model) {
        bindVM(view, model);
    }

    private V getView() {
        return mWeakReferenceView != null ? mWeakReferenceView.get() : null;
    }

    private void setView(V view) {
        if (view != null) {
            this.mWeakReferenceView = new WeakReference<>(view);
        }
        mView = getView();
    }

    private M getModel() {
        return mWeakReferenceModel != null ? mWeakReferenceModel.get() : null;
    }

    private void setModel(M model) {
        if (model != null) {
            this.mWeakReferenceModel = new WeakReference<>(model);
        }
        mModel = getModel();
    }

    public void bindVM(V view, M model) {
        setView(view);
        setModel(model);
    }

    public void unBind() {
        if (mWeakReferenceView != null) {
            mWeakReferenceView.clear();
            mWeakReferenceView = null;
        }
        if (mWeakReferenceModel != null) {
            mWeakReferenceModel.clear();
            mWeakReferenceModel = null;
        }
    }
}