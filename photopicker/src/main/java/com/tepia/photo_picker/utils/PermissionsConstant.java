package com.tepia.photo_picker.utils;

import android.Manifest;
import android.os.Build;
import android.support.annotation.RequiresApi;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:TOTO                                             *
 * 项目名:photopicker                                        *
 * 包名:com.tepia.photo_picker utils                         *
 * 创建时间:2016年06月14日14:40                              *
 * 更新人:yanqiuqiu                                          *
 * 邮箱:yanqqkongmi@gmail.com                                *
 * QQ:2361167552                                             *
 * 更新时间:2017年09月14日14:40                              *
 * 版权:个人版权所有                                         *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class PermissionsConstant {

    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_EXTERNAL_READ = 2;
    public static final int REQUEST_EXTERNAL_WRITE = 3;

    protected static final String[] PERMISSIONS_CAMERA = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    protected static final String[] PERMISSIONS_EXTERNAL_WRITE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected static final String[] PERMISSIONS_EXTERNAL_READ = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
}
