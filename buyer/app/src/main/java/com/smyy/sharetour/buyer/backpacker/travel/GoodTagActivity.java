package com.smyy.sharetour.buyer.backpacker.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.view.flow_tag.TagBaseAdapter;
import com.smyy.sharetour.buyer.view.flow_tag.TagCloudLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.BackPacker.Travel
* @fileName GoodTagActivity
* @date on 2018/4/23 0023 16:00
* @describe 商品标签选择页面
*/
public class GoodTagActivity extends BaseMvpActivity {

    @BindView(R.id.tag_container)
    TagCloudLayout tagContainer;
    @BindView(R.id.good_tag_ok)
    TextView next;

    private List<String> mList;
    private HashSet<String> results = new HashSet<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_good_tag;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        title.setText("添加商品标签");
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        initUI(intent);
    }


    private void initUI(Intent intent) {
        //判断是否为追加评价
        mList = new ArrayList<>();
        mList.add("护肤美容");
        mList.add("母婴用品");
        mList.add("服饰配件");
        mList.add("数码产品");
        mList.add("珠宝首饰");
        mList.add("钟表眼镜");
        mList.add("古董收藏");
        mList.add("游戏设备");
        mList.add("玩具乐器");
        mList.add("居家日用");
        mList.add("家用电器");
        mList.add("保健护理");
        mList.add("童装");
        mList.add("箱包");
        mList.add("动漫/周边");
        mList.add("宠物/用品");
        TagBaseAdapter mAdapter = new TagBaseAdapter(this, mList ,R.layout.view_item_tag);

        tagContainer.setAdapter(mAdapter);
        tagContainer.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                //Toast.makeText(PersonalTagActivity.this,mList.get(position),Toast.LENGTH_SHORT).show();
                if(results.contains(mList.get(position))) {
                    results.remove(mList.get(position));
                } else {
                    results.add(mList.get(position));
                }

                if(results.size()<3){
                    setOtherTagEnable(true);
                } else {
                    setOtherTagEnable(false);
                }
            }
        });
    }

    private void setOtherTagEnable(boolean enable){
        if(enable){
            next.setEnabled(false);
            next.setTextColor(getResources().getColor(R.color.white));
            tagContainer.setNotCheckItemEnable(true);
        } else {
            next.setEnabled(true);
            next.setTextColor(getResources().getColor(R.color.txt_main));
            tagContainer.setNotCheckItemEnable(false);
        }
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.good_tag_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.good_tag_ok:
                startActivity(new Intent(GoodTagActivity.this, BackPackerSendTravelActivity.class));
                finish();
                break;
        }
    }
}
