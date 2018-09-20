package com.tepia.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.pgyersdk.crash.PgyCrashManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.Utils;
import com.zxy.tiny.Tiny;

import org.litepal.LitePalApplication;

/**
 * Created by Joeshould on 2018/5/22.
 */

public class BaseApplication extends LitePalApplication {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appInit();
        LogUtil.isDebug = true;
        //初始化全局context,不允许删除
        Utils.init(this);

    }

    private void appInit() {
        PgyCrashManager.register(this);
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        // 尽可能早，推荐在Application中初始化
        ARouter.init( this );
        Tiny.getInstance().init(this);
    }

    /*public static Context getInstance() {
        return instance;
    }*/  //Utils.getContext()替代;
}
