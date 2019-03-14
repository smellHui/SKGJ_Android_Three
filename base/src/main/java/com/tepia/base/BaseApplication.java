package com.tepia.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

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
        LogUtil.isDebug = false;
        //初始化全局context,不允许删除
        Utils.init(this);
        //解决部分机型上关闭通知栏权限后吐司无法显示问题
        com.hjq.toast.ToastUtils.init(this);

    }

    private void appInit() {
        //异常收集
        PgyCrashManager.register(this);
        ARouter.openLog();     // 打印日志
        // TODO: 2018/11/26 上线时记得关闭
        // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.openDebug();
        // 尽可能早，推荐在Application中初始化
        ARouter.init( this );
        Tiny.getInstance().init(this);
    }

    /*public static Context getInstance() {
        return instance;
    }*/  //Utils.getContext()替代;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }
}
