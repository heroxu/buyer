package com.smyy.sharetour.buyer.backpacker.require;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smyy.sharetour.buyer.R;
import com.smyy.sharetour.buyer.backpacker.BackpackerHomeItemAdapter;
import com.smyy.sharetour.buyer.backpacker.travel.BackPackerSendTravelActivity;
import com.smyy.sharetour.buyer.base.mvp.BaseMvpActivity;
import com.smyy.sharetour.buyer.base.mvp.IBasePresenter;
import com.smyy.sharetour.buyer.bean.RequireBean;
import com.smyy.sharetour.buyer.dialog.BasePopupWindow;
import com.smyy.sharetour.buyer.dialog.DialogUtils;
import com.smyy.sharetour.buyer.dialog.GridSelectSortPop;
import com.smyy.sharetour.buyer.dialog.SimpleSelectBean;
import com.smyy.sharetour.buyer.dialog.ListSelectSortPop;
import com.smyy.sharetour.buyer.home.search.activity.SearchActivity;
import com.smyy.sharetour.buyer.tim.ChatActivity;
import com.tencent.imsdk.TIMConversationType;
import com.xmyy.view.dialoglib.CommonDialog;
import com.xmyy.view.dialoglib.base.BindViewHolder;
import com.xmyy.view.dialoglib.listener.OnViewClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Liliping
 * @org www.smyy.com
 * @email lilp@stjf.com
 * @package com.smyy.sharetour.buyer.BackPacker.Require
 * @fileName BackPackerRequireListActivity
 * @date on 2018/4/23 0023 11:07
 * @describe 背包客需求搜索列表
 */
public class BackPackerRequireSearchListActivity extends BaseMvpActivity {

    @BindView(R.id.require_search_text)
    TextView requireSearchText;
    @BindView(R.id.require_search_price_arrow)
    ImageView requireSearchPriceArrow;
    @BindView(R.id.require_search_catalog_arrow)
    ImageView requireSearchCatalogArrow;
    @BindView(R.id.require_search_expire_time_arrow)
    ImageView requireSearchExpireTimeArrow;
    @BindView(R.id.require_search_list)
    RecyclerView requireSearchList;
    @BindView(R.id.require_search_all_srl)
    SwipeRefreshLayout requireSearchAllSrl;
    @BindView(R.id.require_search_price)
    LinearLayout requireSearchPrice;
    @BindView(R.id.require_search_catalog)
    LinearLayout requireSearchCatalog;
    @BindView(R.id.require_search_expire_time)
    LinearLayout requireSearchExpireTime;
    @BindView(R.id.require_search)
    ImageView requireSearch;

    private List<RequireBean> requires = new ArrayList<>();

    List<SimpleSelectBean> priceSortData = new ArrayList<>();
    List<SimpleSelectBean> catalogSortData = new ArrayList<>();
    List<SimpleSelectBean> expireSortData = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_require_search;
    }

    @Override
    protected void configToolBar(Toolbar toolbar, TextView title) {
        hideToolBarLayout(true);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState, Intent intent) {
        requireSearchText.setText(intent.getStringExtra(SearchActivity.BUNDLE_SEARCH_STRING));
        requireSearchList.setHasFixedSize(true);
        requireSearchList.setLayoutManager(new LinearLayoutManager(BackPackerRequireSearchListActivity.this));

        demo();

        final BackpackerHomeItemAdapter mAdapter = new BackpackerHomeItemAdapter(BackPackerRequireSearchListActivity.this, requires);
        mAdapter.setItemClickListener(new BackpackerHomeItemAdapter.OnBackpackerHomeItemOnClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Intent intent = new Intent(BackPackerRequireSearchListActivity.this, BackPackerRequireDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(BackPackerRequireDetailsActivity.REQUIRE_KEY, requires.get(position));
                bundle.putBoolean(BackPackerRequireDetailsActivity.REQUIRE_TAKE_KEY, true);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }

            @Override
            public void OnItemContactClick(View v, int position) {
                ChatActivity.navToChat(BackPackerRequireSearchListActivity.this, "我是小桂子", TIMConversationType.C2C);
            }

            @Override
            public void OnItemTakeClick(View v, int position) {
                DialogUtils.showTwoBtnMsgBox(BackPackerRequireSearchListActivity.this,
                        null,
                        getString(R.string.send_trip_tip),
                        R.color.txt_hint,
                        getString(R.string.send_trip),
                        new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                startActivity(BackPackerSendTravelActivity.class);
                                commonDialog.dismiss();
                            }
                        },
                        getString(R.string.cancel),
                        new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, CommonDialog commonDialog) {
                                commonDialog.dismiss();
                            }
                        });
            }
        });
        requireSearchList.setAdapter(mAdapter);

        requireSearchAllSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }
                requireSearchAllSrl.setRefreshing(false);
            }
        });

    }

    private void demo() {
        for (int i = 0; i < 5; i++) {
            RequireBean bean = new RequireBean();
            bean.setRequire_disc("NIKE JORDAN 8 乔丹8系列 黑白牛皮运动鞋");
            bean.setRequire_time("2018-03-08");
            bean.setRequire_budget("9918.00");
            bean.setRequire_buy_place("日本");
            bean.setState(0);
            requires.add(bean);
        }

        priceSortData.add(new SimpleSelectBean("默认", true));
        priceSortData.add(new SimpleSelectBean("价格最高", false));
        priceSortData.add(new SimpleSelectBean("价格最低", false));

        expireSortData.add(new SimpleSelectBean("默认", true));
        expireSortData.add(new SimpleSelectBean("时间最早", false));
        expireSortData.add(new SimpleSelectBean("时间最晚", false));

        catalogSortData.add(new SimpleSelectBean("美容彩妆", true));
        catalogSortData.add(new SimpleSelectBean("美容护理", false));
        catalogSortData.add(new SimpleSelectBean("服装鞋包", false));
        catalogSortData.add(new SimpleSelectBean("精美饰品", false));
        catalogSortData.add(new SimpleSelectBean("数码科技", false));
        catalogSortData.add(new SimpleSelectBean("珠宝首饰", false));
        catalogSortData.add(new SimpleSelectBean("古董收藏", false));
        catalogSortData.add(new SimpleSelectBean("游戏设备", false));
        catalogSortData.add(new SimpleSelectBean("童装", false));
        catalogSortData.add(new SimpleSelectBean("保健护理", false));
        catalogSortData.add(new SimpleSelectBean("家用电器", false));
        catalogSortData.add(new SimpleSelectBean("母婴用品", false));
        catalogSortData.add(new SimpleSelectBean("动漫/周边", false));
        catalogSortData.add(new SimpleSelectBean("宠物/用品", false));
    }

    @Override
    protected IBasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.require_search_text, R.id.require_search_price, R.id.require_search_catalog,
            R.id.require_search_expire_time, R.id.back_btn, R.id.clear_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.require_search_text:
                Intent intent = new Intent(BackPackerRequireSearchListActivity.this, SearchActivity.class);
                intent.putExtra(SearchActivity.TYPE, SearchActivity.BUNDLE_REQUIRE);
                if(!requireSearchText.getText().equals("")) {
                    intent.putExtra(SearchActivity.BUNDLE_SEARCH_STRING, requireSearchText.getText());
                }
                startActivity(intent);
                finish();
                break;
            case R.id.require_search_price:
                ListSelectSortPop mPricePop = new ListSelectSortPop(BackPackerRequireSearchListActivity.this, priceSortData);
                requireSearchPriceArrow.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
                mPricePop.showPop(requireSearchPrice, new ListSelectSortPop.IStatusChange() {
                    @Override
                    public void selectPosition(BasePopupWindow popupWindow, int position) {
                        setSelectOption(priceSortData, position);
                        popupWindow.dismiss();
                    }

                    @Override
                    public void closePopupWindow() {
                        requireSearchPriceArrow.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    }
                });
                break;
            case R.id.require_search_catalog:
                GridSelectSortPop mCatalogPop = new GridSelectSortPop(BackPackerRequireSearchListActivity.this, catalogSortData);
                requireSearchCatalogArrow.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
                mCatalogPop.showPop(requireSearchCatalog, new GridSelectSortPop.IStatusChange() {
                    @Override
                    public void selectPosition(BasePopupWindow popupWindow, int position) {
                        setSelectOption(catalogSortData, position);
                        popupWindow.dismiss();
                    }

                    @Override
                    public void closePopupWindow() {
                        requireSearchCatalogArrow.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    }
                });
                break;
            case R.id.require_search_expire_time:
                ListSelectSortPop mExpireTimePop = new ListSelectSortPop(BackPackerRequireSearchListActivity.this, expireSortData);
                requireSearchExpireTimeArrow.setBackgroundResource(R.drawable.ic_arrow_drop_up_black_24dp);
                mExpireTimePop.showPop(requireSearchExpireTime, new ListSelectSortPop.IStatusChange() {
                    @Override
                    public void selectPosition(BasePopupWindow popupWindow, int position) {
                        setSelectOption(expireSortData, position);
                        popupWindow.dismiss();
                    }

                    @Override
                    public void closePopupWindow() {
                        requireSearchExpireTimeArrow.setBackgroundResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    }
                });
                break;
            case R.id.back_btn:
                finish();
                break;
            case R.id.clear_text:
                requireSearchText.setText("");
                requireSearch.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setSelectOption(List<SimpleSelectBean> data, int select) {
        for (int i=0; i<data.size(); i++) {
            if(i==select) {
                data.get(i).setCheck(true);
            } else {
                data.get(i).setCheck(false);
            }
        }
    }

}
