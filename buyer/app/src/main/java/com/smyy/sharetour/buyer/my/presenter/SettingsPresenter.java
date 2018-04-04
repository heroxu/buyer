package com.smyy.sharetour.buyer.my.presenter;

import com.smyy.sharetour.buyer.base.mvp.BasePresenter;
import com.smyy.sharetour.buyer.my.contract.ISettingsContract;
import com.smyy.sharetour.buyer.my.model.SettingsModel;

public class SettingsPresenter extends BasePresenter<ISettingsContract.View> implements ISettingsContract.Presenter {
    private ISettingsContract.Model model;
    public SettingsPresenter(ISettingsContract.View baseView) {
        super(baseView);
        this.model = new SettingsModel();

    }

    @Override
    public void bind() {

    }
}
