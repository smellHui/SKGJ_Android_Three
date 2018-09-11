package com.tepia.main.view;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager.WakeLock;
import android.util.Log;

/**
 * 锁屏通知
 */
public class WakeLockScreenReceiverOfMain extends BroadcastReceiver {
    private static final String TAG = WakeLockScreenReceiverOfMain.class.getName();
    private WakeLock wakeLock = null;
    public static boolean hasScreenOn;


    public WakeLockScreenReceiverOfMain(WakeLock wakeLock) {
        super();
        this.wakeLock = wakeLock;

    }

    @SuppressLint("Wakelock")
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            if (null != wakeLock && !(wakeLock.isHeld())) {
                Log.e(TAG,"锁屏执行wakeLock.acquire()");
                wakeLock.acquire();
                hasScreenOn = true;
                Intent intent1 = new Intent();
                intent1.setAction(VedioComunicationActivity.media_open);
                context.sendBroadcast(intent1);
            }
        } else if (Intent.ACTION_SCREEN_ON.equals(action) || Intent.ACTION_USER_PRESENT.equals(action)) {
            if (null != wakeLock && wakeLock.isHeld()) {
                Log.e(TAG,"开屏执行release");
                wakeLock.release();
                hasScreenOn = false;
            }
        }

    }





}
