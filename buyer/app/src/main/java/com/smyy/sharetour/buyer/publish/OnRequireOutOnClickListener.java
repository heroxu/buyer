package com.smyy.sharetour.buyer.publish;

import android.view.View;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.publish
* @fileName OnRequireOutOnClickListener
* @date on 2018/4/21 0021 20:37
* @describe 下架需求recycleview的点击事件
*/
public interface OnRequireOutOnClickListener extends OnRecyclerViewOnClickListener{

    void OnItemDeleteClick(View v, int position);

    void OnItemRetryClick(View v, int position);

}
