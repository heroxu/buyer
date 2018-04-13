package com.smyy.sharetour.buyer.util;

import android.text.TextUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberUtils {
    private static DecimalFormat df = null;

    public static String getTwoDecimals(double d) {
        if (d == 0)
            return "0";
        return StringUtil.getTwoDecimals(new DecimalFormat("#0.0000").format(d));
    }

    // 保留两位小数
    public static String getTwoDecimals(String s) {
        try {
            if ("0".equals(s))
                return "0";
            return StringUtil.getTwoDecimals(new DecimalFormat("#0.0000").format(Double.parseDouble(s)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }

    public static String getMoneyNumb(double d) {
        if (d == 0)
            return "0";
        return StringUtil.getTwoDecimals(new DecimalFormat(",###,##0.0000").format(d));
    }

    public static String getMoneyNumb(String s) {
        try {
            if ("0".equals(s))
                return "0";
            return StringUtil.getTwoDecimals(new DecimalFormat(",###,##0.0000").format(Double.parseDouble(s)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }


    /**
     * 小数截取成整数
     */
    public static String getMoneyToInt(String s){
        try{
            if (s.indexOf(".") <= s.length()) {
                return s.substring(0, s.indexOf("."));
            }
            return "0";
        }catch(Exception e){
            e.printStackTrace();
            return "0";
        }
    }




    /**
     * 字符串类型的数字或者金额数字转换为double类型
     */
    public static double getMoneyNumber(String s) {
        try {
            if (!TextUtils.isEmpty(s)) {
                if (s.contains(","))
                    s = s.replace(",", "");
                s = s.trim();
                return Double.valueOf(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0f;
    }

    /**
     * 四舍五入取整
     */
    public static int getIntNumber(double number) {
        try {
            if (number != 0.0f) {
                if (df == null) {
                    df = new DecimalFormat("##0");
                    df.setRoundingMode(RoundingMode.HALF_UP);
                }
                double n = Math.abs(number - 0.49);
                return Integer.parseInt(df.format(n));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getIntNumber(String number) {
        try {
            if (!TextUtils.isEmpty(number)) {
                return getIntNumber(NumberUtils.getMoneyNumber(number));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
