package com.tepia.reservoirthree;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import com.baidu.mobstat.StatService;
import com.example.gaodelibrary.UtilsContextOfGaode;
import com.pgyersdk.crash.PgyCrashManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.tepia.base.BaseApplication;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;

import org.litepal.LitePal;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Joeshould on 2018/5/22.
 */

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 2018/10/22 以下代码是和视频会议连接在一起的极光推送。请不要删除
        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        //开启crashlog日志上报
        JPushInterface.initCrashHandler(this);
        UtilsContextOfGaode.init(this);

        LitePal.initialize(this);
        CrashReport.initCrashReport(getApplicationContext(), "bc876488f4", true);
        StatService.start(this);
    }

}
