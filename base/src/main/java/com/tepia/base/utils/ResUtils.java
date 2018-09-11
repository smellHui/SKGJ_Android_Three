/*
 *  Copyright 2016 by OCN.YAN
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.tepia.base.utils;

import android.content.Context;
import android.content.res.Resources;

import android.view.View;
import com.tepia.base.BaseApplication;

/*************************************************************
 * Created by OCN.YAN                                       *
 * 主要功能:资源相关                                          *
 * 项目名:贵州水务                                            *
 * 包名:com.elegant.river_system.utils.UIUtils               *
 * 创建时间:2017年08月16日9:35                                *
 * 更新时间:2017年08月16日9:35                                *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class ResUtils {

    public static Context getContext() {
        return Utils.getContext();
    }



    /**
     * 得到Resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到String.xml中的字符串信息
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 得到String.xml中的字符串数组信息
     */
    public static String[] getStrings(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到Color.xml中的颜色信息
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 得到Array.xml中的数组信息
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 加载布局文件
     */
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

}
