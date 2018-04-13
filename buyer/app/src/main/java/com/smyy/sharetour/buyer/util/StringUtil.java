package com.smyy.sharetour.buyer.util;

import android.text.TextUtils;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作
 */

public class StringUtil {

    /**
     * 首尾去空格
     */
    public static String trim(TextView textView) {
        if (null != textView) {
            return textView.getText().toString().trim();
        }

        return "";
    }

    public static boolean checkNickname(String name) {
        if (!isEmpty(name) && name.length() <= 12) {
            return true;
        }
        return false;
    }

    public static boolean checkUserIntro(String userIntro) {
        if (!isEmpty(userIntro) && userIntro.length() <= 100) {
            return true;
        }
        return false;
    }


    /**
     * 判断是否为中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /*
     * 判断email格式是否正确
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 手机号验证--只验证是大于1位数字
     */
    public static boolean isPhoneNum(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern p = Pattern.compile("[0-9]+$"); // 验证手机号
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        return b;
    }

    /**
     * 短信验证码验证--6位数字
     */
    public static boolean isSmsCode(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern p = Pattern.compile("[0-9]{6}");
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        return b;
    }

    /**
     * 判断身份证
     */
    public static boolean isIDCard(String str) {
        Pattern p = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        return b;
    }

    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }

    /**
     * 只能是数字,英文字母和中文
     */
    public static boolean isValidTagAndAlias(String s) {
        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_-]{0,}$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * 校验推荐人编码 首字母大写+4位数字
     */
    public static boolean isReferCode(String ss) {
        Pattern p = Pattern.compile("[A-Z]{1}\\d{4}");
        Matcher m = p.matcher(ss);
        return m.matches();
    }


    /***
     * 验证中文名字
     */
    public static boolean isChineseName(String name) {
        Pattern pattern = Pattern.compile("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]){2,5}$");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static String getTwoDecimals(String Decimal) {
        try {
            if (Decimal.indexOf(".") + 3 <= Decimal.length()) {
                return Decimal.substring(0, Decimal.indexOf(".") + 3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }

    //手机号码脱敏
    public static String getPhoneNum(String phoneNum) {
        if (phoneNum != null) {
            int length = phoneNum.length();
            if (length >= 11) {
                return phoneNum.substring(0, 3) + " **** "
                        + phoneNum.substring(length - 4, length);
            } else if (length > 4) {
                return phoneNum.substring(0, 1) + " **** "
                        + phoneNum.substring(length - 1, length);
            } else if (length > 1) {
                return phoneNum.substring(0, 1) + " ****";
            } else {
                return "****";
            }
        }

        return phoneNum;
    }

    /**
     * 加密银行卡号
     */
    public static String getBankNumber(String bankNumber) {
        try {
            if (!TextUtils.isEmpty(bankNumber) && bankNumber.length() > 4) {
                return "**** **** **** " + bankNumber.substring(bankNumber.length() - 4, bankNumber.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 银行卡号后4位
     */
    public static String getBankNumberEnd(String bankNumber) {
        try {
            if (!TextUtils.isEmpty(bankNumber) && bankNumber.length() > 4) {
                return bankNumber.substring(bankNumber.length() - 4, bankNumber.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 全角转半角
     */
    public static final String SBC2DBC(String QJstr) {
        try {
            String outStr = "";
            String Tstr = "";
            byte[] b = null;

            for (int i = 0; i < QJstr.length(); i++) {
                try {
                    Tstr = QJstr.substring(i, i + 1);
                    b = Tstr.getBytes("unicode");
                } catch (java.io.UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (b[3] == -1) {
                    b[2] = (byte) (b[2] + 32);
                    b[3] = 0;
                    try {
                        outStr = outStr + new String(b, "unicode");
                    } catch (java.io.UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else
                    outStr = outStr + Tstr;
            }
            return outStr;
        } catch (Exception e) {
            return QJstr;
        }

    }
}
