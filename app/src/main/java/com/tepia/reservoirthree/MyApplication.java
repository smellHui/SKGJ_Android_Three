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
        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        // 加载系统默认设置，字体不随用户设置变化
        /*Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            createConfigurationContext(config);
        } else {
            res.updateConfiguration(config, res.getDisplayMetrics());
        }*/

    }

    private void appInit() {
        // TODO: 2018/9/20 正式发布apk时请关闭
        /**
         * 正式发布apk时请关闭，避免某些地方 java.lang.NullPointerException: println needs a message
         * at com.tepia.base.utils.LogUtil.i(LogUtil.java:43)
         */
        LogUtil.isDebug = false;
        UtilsContextOfGaode.init(this);

    }
}
