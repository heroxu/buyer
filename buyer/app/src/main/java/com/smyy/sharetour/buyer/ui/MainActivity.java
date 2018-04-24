package com.smyy.sharetour.buyer.ui;

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

import com.smyy.sharetour.buyer.MyApplication;
import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.base.BaseFragment;
import com.smyy.sharetour.buyer.fragment.FoundFragment;
import com.smyy.sharetour.buyer.fragment.HomeFragment;
import com.smyy.sharetour.buyer.fragment.LiveFragment;
import com.smyy.sharetour.buyer.fragment.MyFragment;
import com.smyy.sharetour.buyer.util.ActivityLauncher;
import com.smyy.sharetour.buyer.util.FragmentUtil;
import com.smyy.sharetour.buyer.view.RedImageView;
import com.smyy.sharetour.uiframelib.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    //当前显示的fragment
    private BaseFragment mCurrentFragment;
    private View mCurrentViewSelected;
    private HomeFragment homeFragment;
    private FoundFragment fountFragment;
    private LiveFragment liveFragment;
    private MyFragment myFragment;

    @BindView(R.id.main_content)
    FrameLayout mMainContent;
    @BindView(R.id.tab_layout)
    LinearLayout mTabLayout;
    private final int[] fLabelArray = new int[]{R.string.main_tab_index, R.string.main_tab_found, 0, R.string.main_tab_live, R.string.main_tab_my};
    private final int[] fIconResId = new int[]{R.drawable.main_index_selector, R.drawable.main_found_selector, 0, R.drawable.main_live_selector, R.drawable.main_my_selector};

    public static final String KEY_TAB = "key_tab";
    public static final int TAB_INDEX = 0;
    public static final int TAB_SALARY = 1;
    public static final int TAB_LIVE = 3;
    public static final int TAB_ME = 4;


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
        //解决当前界面被系统回收时候fragment重叠问题
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getName());
        if (fragment != null && fragment instanceof HomeFragment) {
            homeFragment = (HomeFragment) fragment;
            initCurrentFragment(homeFragment);
        }
        fragment = getSupportFragmentManager().findFragmentByTag(FoundFragment.class.getName());
        if (fragment != null && fragment instanceof FoundFragment) {
            fountFragment = (FoundFragment) fragment;
            initCurrentFragment(fountFragment);
        }
        fragment = getSupportFragmentManager().findFragmentByTag(LiveFragment.class.getName());
        if (fragment != null && fragment instanceof LiveFragment) {
            liveFragment = (LiveFragment) fragment;
            initCurrentFragment(liveFragment);
        }
        fragment = getSupportFragmentManager().findFragmentByTag(MyFragment.class.getName());
        if (fragment != null && fragment instanceof LiveFragment) {
            myFragment = (MyFragment) fragment;
            initCurrentFragment(myFragment);
        }
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
            isShow = checkFragmentIsAdded(homeFragment, isShow);
            isShow = checkFragmentIsAdded(fountFragment, isShow);
            isShow = checkFragmentIsAdded(liveFragment, isShow);
            isShow = checkFragmentIsAdded(myFragment, isShow);
            if (mCurrentFragment instanceof HomeFragment) {
                changeSelectedView(TAB_INDEX);
            } else if (mCurrentFragment instanceof FoundFragment) {
                changeSelectedView(TAB_SALARY);
            } else if (mCurrentFragment instanceof LiveFragment) {
                changeSelectedView(TAB_LIVE);
            } else if (mCurrentFragment instanceof MyFragment) {
                changeSelectedView(TAB_ME);
            }
        }
        //改变消息状态图片 - 红点
//        changeIncomeReadStatus(XXX);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int tabIndex = intent.getIntExtra(KEY_TAB, 0);
        obtainFragment(tabIndex);
    }

    private void obtainFragment(int index) {
        BaseFragment tagFragment = null;
        switch (index) {
            case TAB_INDEX:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                tagFragment = homeFragment;
                break;
            case TAB_SALARY:
                if (fountFragment == null) {
                    fountFragment = new FoundFragment();
                }
                tagFragment = fountFragment;
                break;
            case TAB_LIVE:
                if (liveFragment == null) {
                    liveFragment = new LiveFragment();
                }
                tagFragment = liveFragment;
                break;
            case TAB_ME:
//                if (!MyApplication.getApplication().isLogin()) {
//                    ActivityLauncher.viewLoginActivity(this);
//                    return;
//                }
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

    @OnClick({R.id.tab_index, R.id.tab_salary, R.id.tab_live, R.id.tab_me, R.id.tab_publish})
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
            case R.id.tab_publish:
                ActivityLauncher.viewPublishRequireActivity(MainActivity.this);
                break;
            default:
                break;
        }
    }

    private void initCurrentFragment(BaseFragment fragment) {
        if (fragment != null && !fragment.isHidden()) {
            mCurrentFragment = fragment;
        }
    }

}
