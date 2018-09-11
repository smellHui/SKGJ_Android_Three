package com.tepia.base.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Stack;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:堆栈式管理                                        *
 * 项目名:贵州水务                                           *
 * 包名:com.elegant.river_system.utils                       *
 * 创建时间:2017年08月16日9:37                                *
 * 更新时间:2017年10月12日9:37                                *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class AppManager {
    /**
     *
     * 堆栈列表
     */
    private static Stack<Activity> mActivity = new Stack<>();
    private static AppManager mInstance;
    public static AppManager getInstance() {
        if (mInstance == null) {
            synchronized (AppManager.class) {
                if (mInstance == null) {
                    mInstance = new AppManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        mActivity.add(activity);
    }

    /**
     * 移除Activity到堆栈
     */
    public void removeActivity(Activity activity) {
        mActivity.remove(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity getCurrentActivity() {
        return mActivity.lastElement();
    }

    /**
     * 除了此Activity之外的所有Activity全部关闭
     */
    public void finishExcept(Activity except) {
        ArrayList<Activity> copy;
        synchronized (mActivity) {
            copy = new ArrayList<>(mActivity);
        }
        for (Activity activity : copy) {
            if (activity != except) {
                activity.finish();
            }
        }
    }

    /**
     * 除了此Activity之外的所有Activity全部关闭
     */
    public void finishExcept(Class<? extends Activity> exceptClass) {
        ArrayList<Activity> arrayList;
        synchronized (mActivity) {
            arrayList = new ArrayList<>(mActivity);
        }
        for (Activity activity : arrayList) {
            if (!activity.getClass().equals(exceptClass)) {
                activity.finish();
            }
        }
    }

    /**
     * 关闭所有Activity
     */
    public void finishAll() {
        ArrayList<Activity> copy;
        synchronized (mActivity) {
            copy = new ArrayList<>(mActivity);
        }
        for (Activity activity : copy) {
            activity.finish();
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp() {
        try {
            finishAll();
            System.gc();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
