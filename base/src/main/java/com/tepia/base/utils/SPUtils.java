package com.tepia.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.tepia.base.BaseApplication;
import com.tepia.base.mvp.BaseActivity;

import java.util.Map;


/*************************************************************
 * Created by OCN.YAN                                       *
 * 主要功能:sharedPreferences工具类(单例模式)                  *
 * 项目名:贵州水务                                            *
 * 包名:com.elegant.river_system.utils.UIUtils               *
 * 创建时间:2017年08月16日9:35                                *
 * 更新时间:2017年08月16日9:35                                *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class SPUtils {
    private static final String SP_NAME = "common";
    private static SPUtils mSpUtils;
    private Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @SuppressLint("WrongConstant")
    private SPUtils(Context context) {
        this.context = context;
        sp = this.context.getSharedPreferences(SP_NAME, Context.MODE_APPEND);
        editor = sp.edit();
    }

    public static SPUtils getInstance(Context context) {
        if (mSpUtils == null) {
            synchronized (SPUtils.class) {
                if (mSpUtils == null) {
                    mSpUtils = new SPUtils(context);
                    return mSpUtils;
                }
            }
        }

        return mSpUtils;

    }

    public static SPUtils getInstance() {
        if (mSpUtils == null) {
            synchronized (SPUtils.class) {
                if (mSpUtils == null) {
                    mSpUtils = new SPUtils(Utils.getContext());
                    return mSpUtils;
                }
            }
        }

        return mSpUtils;

    }

    public void putBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, Boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public void putString(String key, String value) {
        if (key == null) {
            return;
        }
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    public void remove(String key) {
        sp.edit().remove(key).commit();
    }

}