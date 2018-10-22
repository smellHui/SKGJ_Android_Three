package com.tepia.reservoirthree;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import com.example.gaodelibrary.UtilsContextOfGaode;
import com.pgyersdk.crash.PgyCrashManager;
import com.tepia.base.BaseApplication;
import com.tepia.base.utils.LogUtil;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Joeshould on 2018/5/22.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        appInit();
        //添加异常收集
        PgyCrashManager.register(this);
        // TODO: 2018/10/22 以下代码是和视频会议连接在一起的极光推送。请不要删除
        //极光推送
        /*JPushInterface.setDebugMode(true);
        JPushInterface.init(this);*/



    }

    private void appInit() {
        // TODO: 2018/9/20 正式发布apk时请关闭
        /**
         * 正式发布apk时请关闭，避免某些地方 java.lang.NullPointerException: println needs a message
         * at com.tepia.base.utils.LogUtil.i(LogUtil.java:43)
         */
        LogUtil.isDebug = true;
        UtilsContextOfGaode.init(this);

    }
}
