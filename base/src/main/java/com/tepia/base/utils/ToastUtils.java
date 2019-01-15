/*
 * Copyright © 2012-2016 昆明鞋包网科技有限公司 版权所有
 */

package com.tepia.base.utils;

import android.content.Context;
import android.widget.Toast;

import com.tepia.base.BaseApplication;


/*************************************************************
 * Created by OCN.YAN                                       *
 * 主要功能:Toast工具类                                       *
 * 项目名:贵州水务                                            *
 * 包名:com.elegant.river_system.utils.UIUtils               *
 * 创建时间:2017年08月16日9:35                                *
 * 更新时间:2017年08月16日9:35                                *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class ToastUtils {

    private static Context context = Utils.getContext();
    private static ToastUtils mToastUtils;
    private ToastUtils(Context context) {

    }

    public static ToastUtils getInstance() {
        if (mToastUtils == null) {
            synchronized (ToastUtils.class) {
                if (mToastUtils == null) {
                    mToastUtils = new ToastUtils(context);
                    return mToastUtils;
                }
            }
        }
        return mToastUtils;
    }

    /**
     * 短时间显示
     *
     * @param text 提示的内容
     */
    public static void shortToast(String text) {
//        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        com.hjq.toast.ToastUtils.show(text);
    }

    /**
     * 长时间显示
     *
     * @param text 提示的内容
     */
    public static void longToast(String text) {
//        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        com.hjq.toast.ToastUtils.show(text);

    }

    /**
     * 短时间显示
     * @param resId 提示的内容资源id
     */
    public static void shortToast(int resId) {
//        Toast.makeText(context, ResUtils.getString(resId), Toast.LENGTH_SHORT).show();
        com.hjq.toast.ToastUtils.show(ResUtils.getString(resId));

    }

    /**
     * 长时间显示
     * @param resId 提示的内容资源id
     */
    public static void longToast(int resId) {
//        Toast.makeText(context, ResUtils.getString(resId), Toast.LENGTH_LONG).show();
        com.hjq.toast.ToastUtils.show(ResUtils.getString(resId));

    }
}
