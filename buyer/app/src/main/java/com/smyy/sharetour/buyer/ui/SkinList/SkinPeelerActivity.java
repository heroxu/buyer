package com.smyy.sharetour.buyer.ui.SkinList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class SkinPeelerActivity extends BaseMvpActivity {

    @BindView(R.id.skin_galley)
    Gallery skinGalley;
    Integer[] posterID = {R.mipmap.skin_style_02, R.mipmap.skin_style_01, R.mipmap.skin_style_03};
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_skin_peeler;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        hideToolBarLayout(true);
        MyGalleryAdapter galAdapter = new MyGalleryAdapter();
        skinGalley.setAdapter(galAdapter);
        skinGalley.setSelection(1);

    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void initStatusBar() {
        setStatusBar(getResources().getColor(R.color.txt_hint));
    }

    @OnClick({R.id.ic_close, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_close:
                finish();
                break;
            case R.id.btn_confirm:
                break;
        }
    }

    public class MyGalleryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(SkinPeelerActivity.this).inflate(R.layout.item_skin_galley, parent, false);
            ImageView imageview = (ImageView) view.findViewById(R.id.iv_skin_galley);
            Glide.with(SkinPeelerActivity.this).load(posterID[position]).crossFade().into(imageview);
            return view;

        }
    }
}
