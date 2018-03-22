package com.smyy.sharetour.buyer.ui.test2;

import com.smyy.sharetour.buyer.network.config.HttpConfig;
import com.smyy.sharetour.buyer.network.rx.RetrofitClient;
import com.smyy.sharetour.buyer.network.rx.RxCallBack;
import com.smyy.sharetour.buyer.network.rx.RxUtils;

import java.util.HashMap;

/**
 * Created by 伍振飞 on 2018/3/16 10:20
 * E-Mail Address：wuzf2012@sina.com
 */
public class Test2Model implements ITest2Contract.Model {
    @Override
    public void getData(RxCallBack<Test2JsonData> rxCallBack) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", "a4c7f4f3662a8c08d0a8af54a96c1201");
        params.put("type", "pic");
        RetrofitClient.getInstance()
                .postAsync(Test2JsonData.class, HttpConfig.RequestUrl.getTestUrl(), params)
                .subscribe(RxUtils.getDefaultSubscriber(rxCallBack));
    }
}
