package com.smyy.sharetour.buyer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
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
 * @package com.smyy.sharetour.buyer.publish
 * @fileName PersonalTagActivity
 * @date on 2018/4/10 0010 16:09
 * @describe 个性标签选择页面
 */
public class PersonalTagActivity extends BaseMvpActivity {

    @BindView(R.id.tag_close)
    ImageView tagClose;
    @BindView(R.id.tag_container)
    TagCloudLayout tagContainer;
    @BindView(R.id.tag_confirm)
    TextView tagConfirm;
    private boolean is_first = false;
    private List<String> mList;
    private HashSet<String> results = new HashSet<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_tag;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        is_first = intent.getBooleanExtra("append", false);
        initUI(intent);
    }


    private void initUI(Intent intent) {
        //判断是否为追加评价
        if (is_first) {
            tagConfirm.setText(R.string.immediate_enjoy);
        }
        mList = new ArrayList<>();
        mList.add("美妆彩妆");
        mList.add("美容护理");
        mList.add("服装鞋包");
        mList.add("数码科技");
        mList.add("精美饰品");
        mList.add("母婴用品");
        mList.add("文化玩乐");
        mList.add("生活饮食");
        mList.add("运动户外");
        TagBaseAdapter mAdapter = new TagBaseAdapter(this,mList);

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
            }
        });
        hideToolBarLayout(true);
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.tag_close, R.id.tag_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tag_close:
                finish();
                break;
            case R.id.tag_confirm:
                break;
        }
    }
}
