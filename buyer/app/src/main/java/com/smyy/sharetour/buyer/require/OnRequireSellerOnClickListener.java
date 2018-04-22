package com.smyy.sharetour.buyer.require;

import android.view.View;

/**
* @author Liliping
* @org www.smyy.com
* @email lilp@stjf.com
* @package com.smyy.sharetour.buyer.publish
* @fileName OnRequireSellerOnClickListener
* @date on 2018/4/21 0021 20:36
* @describe 指定买手recycleview的点击事件
*/
public interface OnRequireSellerOnClickListener extends OnRecyclerViewOnClickListener{

    void OnItemContactClick(View v, int position);

    void OnItemPointClick(View v, int position);

}
