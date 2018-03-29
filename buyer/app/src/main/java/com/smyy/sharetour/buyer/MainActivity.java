package com.smyy.sharetour.buyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.base.BaseFragment;
import com.smyy.sharetour.buyer.fragment.Fragment2;
import com.smyy.sharetour.buyer.fragment.Fragment4;
import com.smyy.sharetour.buyer.fragment.IndexFragment;
import com.smyy.sharetour.buyer.fragment.MyFragment;
import com.smyy.sharetour.buyer.util.FragmentUtil;
import com.smyy.sharetour.buyer.view.RedImageView;
import com.smyy.sharetour.uiframelib.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    //当前显示的fragment
    private BaseFragment mCurrentFragment;
    private View mCurrentViewSelected;
    private IndexFragment fragment1;
    private Fragment2 fragment2;
    private Fragment4 fragment4;
    private MyFragment myFragment;

    @BindView(R.id.main_content)
    FrameLayout mMainContent;
    @BindView(R.id.tab_layout)
    LinearLayout mTabLayout;
    private final int[] fLabelArray = new int[]{R.string.main_tab_1, R.string.main_tab_2, 0, R.string.main_tab_3, R.string.main_tab_4};
    private final int[] fIconResId = new int[]{R.drawable.main_index_selector, R.drawable.main_salary_selector, 0, R.drawable.main_live_selector, R.drawable.main_me_selector};

    private final int TAB_INDEX = 0;
    private final int TAB_SALARY = 1;
    private final int TAB_LIVE = 3;
    private final int TAB_ME = 4;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        hideToolBarLayout(true);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        int count = mTabLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            if (i != 2) {
                ViewGroup parent = (ViewGroup) mTabLayout.getChildAt(i);
                RedImageView iconView = (RedImageView) parent.findViewById(R.id.tab_icon);
                TextView labelView = (TextView) parent.findViewById(R.id.tab_label);
                labelView.setText(getResources().getString(fLabelArray[i]));

                iconView.setTag(fIconResId[i]);
                iconView.setImageResource(fIconResId[i]);
                parent.setTag(iconView);
            }
        }
        if (mCurrentFragment == null) {
            obtainFragment(TAB_INDEX);
        } else {
            boolean isShow = false;
            isShow = checkFragmentIsAdded(fragment1, isShow);
            isShow = checkFragmentIsAdded(fragment2, isShow);
            isShow = checkFragmentIsAdded(fragment4, isShow);
            isShow = checkFragmentIsAdded(myFragment, isShow);
            if (mCurrentFragment instanceof IndexFragment) {
                changeSelectedView(TAB_INDEX);
            } else if (mCurrentFragment instanceof Fragment2) {
                changeSelectedView(TAB_SALARY);
            } else if (mCurrentFragment instanceof Fragment4) {
                changeSelectedView(TAB_LIVE);
            } else if (mCurrentFragment instanceof MyFragment) {
                changeSelectedView(TAB_ME);
            }
        }
        //改变消息状态图片 - 红点
//        changeIncomeReadStatus(XXX);
    }

    private void obtainFragment(int index) {
        BaseFragment tagFragment = null;
        switch (index) {
            case TAB_INDEX:
                if (fragment1 == null) {
                    fragment1 = new IndexFragment();
                }
                tagFragment = fragment1;
                break;
            case TAB_SALARY:
                if (fragment2 == null) {
                    fragment2 = new Fragment2();
                }
                tagFragment = fragment2;
                break;
            case TAB_LIVE:
                if (fragment4 == null) {
                    fragment4 = new Fragment4();
                }
                tagFragment = fragment4;
                break;
            case TAB_ME:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                }
                tagFragment = myFragment;
                break;
        }
        changeCurrentFragment(tagFragment);
        changeSelectedView(index);
    }

    private void changeCurrentFragment(BaseFragment fragment) {
        if (fragment == null) {
            return;
        }
        if (mCurrentFragment != null) {
            FragmentUtil.hideFragment(getSupportFragmentManager(), mCurrentFragment);
        }
        checkFragmentIsAdded(fragment, false, true);
        mCurrentFragment = fragment;
    }

    private boolean checkFragmentIsAdded(Fragment fragment, boolean isShow) {
        return checkFragmentIsAdded(fragment, isShow, false);
    }

    private boolean checkFragmentIsAdded(Fragment fragment, boolean isShow, boolean changeFragment) {
        if (fragment != null) {
            if (fragment.isAdded()) {
                if (changeFragment) {
                    FragmentUtil.showFragment(getSupportFragmentManager(), fragment);
                } else {
                    isShow = isShow || !fragment.isHidden();
                }
            } else {
                FragmentUtil.addFragment(getSupportFragmentManager(), fragment, R.id.main_content);
            }
        }
        return isShow;
    }

    private void changeSelectedView(int index) {
        View v = getTagView(index);
        if (mCurrentViewSelected != null) {
            mCurrentViewSelected.setSelected(false);
        }
        v.setSelected(true);
        mCurrentViewSelected = v;
    }

    /**
     * 获取正确的tabview
     **/
    private View getTagView(int index) {
        return mTabLayout.getChildAt(index);
    }

    @OnClick({R.id.tab_index, R.id.tab_salary, R.id.tab_live, R.id.tab_me})
    public void onClick(View v) {
        if (mCurrentViewSelected == v) {
            return;
        }
        switch (v.getId()) {
            case R.id.tab_index:
                obtainFragment(TAB_INDEX);
                break;
            case R.id.tab_salary:
                obtainFragment(TAB_SALARY);
                break;
            case R.id.tab_live:
                obtainFragment(TAB_LIVE);
                break;
            case R.id.tab_me:
                obtainFragment(TAB_ME);
                break;
            default:
                break;
        }
    }

}
