package com.tepia.main;

import com.alibaba.android.arouter.launcher.ARouter;
import com.pgyersdk.crash.PgyCrashManager;
import com.tepia.base.BaseApplication;
import com.tepia.base.utils.SPUtils;

/**
 * Created by Joeshould on 2018/5/22.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        appInit();
        PgyCrashManager.register(this);//添加异常收集
    }

    private void appInit() {
        ARouter.openLog();     // 打印日志
//        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init( this ); // 尽可能早，推荐在Application中初始化


    }
}
