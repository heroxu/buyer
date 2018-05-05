package com.smyy.sharetour.buyer.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create by xuxiarong on 2018/4/10
 */
public class HomeTitlesOpenOrCloseView extends LinearLayout implements View.OnClickListener {


    private static final String TAG = "HomeTitlesView";
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;
    private RecyclerView rv;
    private View bg;
    boolean isAnimating = false;//是否正在执行动画
    private float mDensity;
    private int mFoldedViewMeasureHeight;
    private IStatusChange mIStatusChange;
    private String[] mTitles;
    private List<String> mTitleList = new ArrayList<>();

    /**
     * 设置监听回调
     *
     * @param iStatusChange
     * @param titles
     */
    public void setIStatusChange(IStatusChange iStatusChange, String[] titles) {
        this.mIStatusChange = iStatusChange;
        mTitles = titles;
        initData();
    }

    /**
     * 设置Rv的朝向和展示方式
     */
    public void setRvLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    private void initData() {
        //默认的3行GridView
        rv.setLayoutManager(mLayoutManager != null ? mLayoutManager : new GridLayoutManager(mContext, 3));
        mTitleList.addAll(Arrays.asList(mTitles));
        rv.setAdapter(new HomeSelectTitleAdapter(mTitleList));
    }

    public HomeTitlesOpenOrCloseView(Context context) {
        this(context, null);
    }

    public HomeTitlesOpenOrCloseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }

    public HomeTitlesOpenOrCloseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_home_table_title, this);
        rv = (RecyclerView) view.findViewById(R.id.pop_rv);

        bg = view.findViewById(R.id.pop_bg);
        bg.setOnClickListener(this);
        mDensity = getResources().getDisplayMetrics().density;
        //获取布局的高度
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        this.measure(w, h);
        int height = this.getMeasuredHeight();
        mFoldedViewMeasureHeight = getResources().getDisplayMetrics().heightPixels;

    }

    /**
     * 展开
     */
    public void animateOpen() {
        this.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(this, 0, mFoldedViewMeasureHeight);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
            }
        });
        animator.start();
        isAnimating = true;
    }

    public void animateClose() {
        int origHeight = this.getHeight();
        Log.e(TAG, "animateClose: " + "isAnimating = " + isAnimating, null);

        ValueAnimator animator = createDropAnimator(this, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                HomeTitlesOpenOrCloseView.this.setVisibility(View.GONE);
                isAnimating = false;
                Log.e(TAG, "onAnimationEnd: " + "isAnimating = " + isAnimating, null);

            }
        });
        animator.start();
        isAnimating = true;
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pop_bg:
                this.animateClose();
                mIStatusChange.selectPosition(-1);
            default:
                this.animateClose();
                mIStatusChange.selectPosition(-1);
                break;
        }
    }

    public interface IStatusChange {
        void selectPosition(int position);
    }

    public class HomeSelectTitleAdapter extends RecyclerView.Adapter {

        private List<String> mDatas;

        public HomeSelectTitleAdapter(List<String> datas) {
            this.mDatas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HomeSelectedViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_title_select, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            HomeSelectedViewHolder viewHolder = (HomeSelectedViewHolder) holder;
            viewHolder.tv_title.setText(mDatas.get(position));
            if (position == 0) {
                viewHolder.tv_title.setSelected(true);
            }
            viewHolder.tv_title.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIStatusChange.selectPosition(position);
                    Log.e(TAG, "item onClick: " + "position = " + position, null);
                    HomeTitlesOpenOrCloseView.this.animateClose();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.isEmpty() ? 0 : mDatas.size();
        }
    }

    public class HomeSelectedViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;

        public HomeSelectedViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_home_title_select);
        }
    }
}
