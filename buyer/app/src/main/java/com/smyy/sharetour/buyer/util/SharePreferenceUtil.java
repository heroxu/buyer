package com.smyy.sharetour.buyer.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Created by 伍振飞 on 2018/3/20 14:47
 * E-Mail Address：wuzf2012@sina.com
 */
public class SharePreferenceUtil {
    private SharedPreferences settings;

    public SharePreferenceUtil(Context context, String sharePreFileName) {
        settings = context.getSharedPreferences(sharePreFileName, Context.MODE_PRIVATE);
    }

    public boolean delete(String key) {
        return settings.edit().remove(key).commit();
    }

    /**
     * 清除SharedPreference中的所有内容
     *
     * @return
     */
    public boolean clear() {
        return settings.edit().clear().commit();
    }


    /**
     * 移除key
     *
     * @param key
     */
    public void remove(String key) {
        settings.edit().remove(key).commit();
    }

    /**
     * 是否包含key对应的value
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return settings.contains(key);
    }

    public String getStringValue(String key, String defValue) {
        return settings.getString(key, defValue);
    }

    public String getStringValue(String key) {
        return settings.getString(key, "");
    }

    public boolean getBooleanValue(String key, boolean defValue) {
        return settings.getBoolean(key, defValue);
    }

    public boolean getBooleanValue(String key) {
        return settings.getBoolean(key, false);
    }

    public float getFloatValue(String key, float defValue) {
        return settings.getFloat(key, defValue);
    }

    public float getFloatValue(String key) {
        return settings.getFloat(key, 0f);
    }

    public int getIntValue(String key, int defValue) {
        return settings.getInt(key, defValue);
    }

    public int getIntValue(String key) {
        return settings.getInt(key, -1);
    }

    public long getLongValue(String key, long defValue) {
        return settings.getLong(key, defValue);
    }

    public long getLongValue(String key) {
        return settings.getLong(key, -1L);
    }


    public <T> T getBeanValue(String key, Class<T> classOfT) {
        T bean = null;
        try {
            String beanStr = getStringValue(key);
            if (!TextUtils.isEmpty(beanStr)) {
                Gson gson = new Gson();
                bean = gson.fromJson(beanStr, classOfT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    public <T> List<T> getListValue(String key) {
        List<T> datas = null;
        try {
            String beanStr = getStringValue(key);
            if (!TextUtils.isEmpty(beanStr)) {
                //json转换为list
                Gson gson = new Gson();
                datas = gson.fromJson(beanStr, new TypeToken<List<T>>() {
                }.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datas;
    }

    public <T> boolean writeListValue(String key, List<T> datas) {
        Gson gson = new Gson();
        String beanStr = gson.toJson(datas);
        return writeStringValue(key, beanStr);
    }

    public boolean writeBooleanValue(String key, boolean value) {
        return settings.edit().putBoolean(key, value).commit();
    }

    public boolean writeStringValue(String key, String value) {
        return settings.edit().putString(key, value).commit();
    }

    public boolean writeFloatValue(String key, float value) {
        return settings.edit().putFloat(key, value).commit();
    }

    public boolean writeLongValue(String key, long value) {
        return settings.edit().putLong(key, value).commit();
    }

    public boolean writeIntValue(String key, int value) {
        return settings.edit().putInt(key, value).commit();
    }

    public <T> boolean writeBeanValue(String key, T bean) {
        Gson gson = new Gson();
        String beanStr = gson.toJson(bean);
        return writeStringValue(key, beanStr);
    }

    @SuppressWarnings("rawtypes")
    public Map getAll() {
        return settings.getAll();
    }
}
