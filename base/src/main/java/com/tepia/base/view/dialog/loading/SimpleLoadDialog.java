package com.tepia.base.view.dialog.loading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


import com.tepia.base.R;

import java.lang.ref.WeakReference;

/**
 * Describe:自定义网络加载进度条
 * Created by liying on 2018/3/5
 */
public class SimpleLoadDialog extends Handler{

    private  Dialog load = null;

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;
    private final WeakReference<Context> reference;
    private ImageView animationIv;
    private TextView loadingMessageTv;
    private String msg;

    private AnimationDrawable animationDrawable;
    public SimpleLoadDialog(Context context,String msg,
                            boolean cancelable) {
        this.reference = new WeakReference<>(context);
        this.msg = msg;
        this.cancelable = cancelable;
    }

    private void create(){
        if (load == null) {
            context  = reference.get();

            load = new Dialog(context, R.style.loading_dialog);
            View dialogView = LayoutInflater.from(context).inflate(
                    R.layout.loading_dialog, null);
            load.setCanceledOnTouchOutside(false);
            load.setCancelable(cancelable);
            load.setContentView(dialogView);
            load.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if(mProgressCancelListener!=null) {
                        mProgressCancelListener.onCancelProgress();
                    }
                }
            });
            loadingMessageTv = dialogView.findViewById(R.id.loading_message);
            loadingMessageTv.setText(msg);
            animationIv = dialogView.findViewById(R.id.animationIv);
            animationIv.setImageResource(R.drawable.refresh_refreshing);
            animationDrawable =  (AnimationDrawable) animationIv.getDrawable();
            Window dialogWindow = load.getWindow();
            dialogWindow.setGravity(Gravity.CENTER_VERTICAL
                    | Gravity.CENTER_HORIZONTAL);

        }
        if (!load.isShowing() && context != null && !((Activity)context).isFinishing()) {

            animationDrawable.start();
            load.show();
        }
    }

    public void show(){
        create();
    }


    public void dismiss() {
        context  = reference.get();
        if (load != null && load.isShowing() && !((Activity) context).isFinishing()) {
            if(animationDrawable != null) {
                animationDrawable.stop();
                animationDrawable = null;
            }
            load.dismiss();
            load = null;
        }
    }

    /**
     * 设置提示语
     * @param title
     */
    public void setMessage(String title){
        if(loadingMessageTv != null){
            loadingMessageTv.setText(title);
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                create();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismiss();
                break;
        }
    }
}
