package com.tepia.main.utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.PowerManager;
import android.view.View;
import android.widget.RemoteViews;

import com.tepia.main.R;

import static android.content.Context.POWER_SERVICE;


/**
 * 消息通知工具类
 * Created by ly on 2018/5/8.
 */
public class NotificationUtil {
    private static NotificationManager manager;

    public static void showNotification(Context context, String contentStr, Class<?> startClass) {
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent inten = new Intent(context, startClass);
        PendingIntent pd = PendingIntent.getActivity(context, 0, inten, PendingIntent.FLAG_UPDATE_CURRENT);

		/*
         * SKD中API Level高于11低于16
		 */
        int resIdIcon;
        int sdk_int = Build.VERSION.SDK_INT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            resIdIcon = R.mipmap.logo;
        } else {
            resIdIcon = R.mipmap.logo;
        }
        if (sdk_int >= Build.VERSION_CODES.HONEYCOMB
                && sdk_int < Build.VERSION_CODES.JELLY_BEAN) {
            Notification.Builder builder1 = new Notification.Builder(context);
            builder1.setContentText(contentStr);
            // 设置图标
            builder1.setSmallIcon(resIdIcon);
            // 发送时间
            builder1.setWhen(System.currentTimeMillis());
            // 设置前台
            builder1.setDefaults(Notification.FLAG_FOREGROUND_SERVICE);
            // 打开程序后图标消失
            builder1.setAutoCancel(true);
            builder1.setContentIntent(pd);
            builder1.setDefaults(Notification.DEFAULT_ALL);
            Notification notification = builder1.getNotification();
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
			manager.notify(R.string.main_name, notification);
        }
		/*
		 * SKD中API Level高于16
		 */
        else if (sdk_int >= Build.VERSION_CODES.JELLY_BEAN && sdk_int < Build.VERSION_CODES.O) {
            // 新建状态栏通知
            Notification.Builder baseNF1 = new Notification.Builder(context);
            baseNF1.setSmallIcon(resIdIcon);
            baseNF1.setAutoCancel(true);
            baseNF1.setContentText(contentStr);
            baseNF1.setWhen(System.currentTimeMillis());
            baseNF1.setContentIntent(pd);
            baseNF1.setDefaults(Notification.DEFAULT_ALL);
            Notification notification = baseNF1.build();
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            manager.notify(R.string.main_name, notification);

        } else if (sdk_int >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("2",
                    "Channel2", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(false);
            channel.setLightColor(Color.GREEN);
            channel.setShowBadge(false);
            channel.setVibrationPattern(null);
            channel.enableVibration(false);
            manager.createNotificationChannel(channel);
            Notification.Builder builderAndroidO = new Notification.Builder(context, "2");
            builderAndroidO
                    .setContentText(contentStr)
                    .setSmallIcon(resIdIcon)
                    .setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setOnlyAlertOnce(true)
                    .setContentIntent(pd);
            builderAndroidO.setDefaults(Notification.DEFAULT_ALL);
            Notification notification = builderAndroidO.build();
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            manager.notify(R.string.main_name, notification);

        }

        //亮屏
        PowerManager mPowerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
        PowerManager.WakeLock mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        mWakeLock.acquire();
    }

    public static void clearNotification() {
        if (manager != null) {
            manager.cancelAll();
        }
    }

    /**
     * 获取应用程序名称
     */
    public static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
