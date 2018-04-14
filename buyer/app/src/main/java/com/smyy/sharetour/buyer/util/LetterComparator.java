package com.smyy.sharetour.buyer.util;

import com.smyy.sharetour.buyer.bean.CityBean;

import java.util.Comparator;

/**
 * Created by 伍振飞 on 2018/4/9 13:51
 * E-Mail Address：wuzf2012@sina.com
 */
public class LetterComparator implements Comparator<CityBean> {

    @Override
    public int compare(CityBean l, CityBean r) {
        if (l == null || r == null) {
            return 0;
        }

        String lhsSortLetters = l.pys.substring(0, 1).toUpperCase();
        String rhsSortLetters = r.pys.substring(0, 1).toUpperCase();
        if (lhsSortLetters == null || rhsSortLetters == null) {
            return 0;
        }
        return lhsSortLetters.compareTo(rhsSortLetters);
    }
}