/*
 * The MIT License (MIT)
 *
 * Copyright 2016 by OCN.YAN
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND TORT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.tepia.base.view.dialog.loading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tepia.base.R;
import com.tepia.base.utils.LogUtil;

import java.lang.ref.WeakReference;


/*************************************************************
 * Created by OCN.YAN                                       *
 * 主要功能:自定义加载                                        *
 * 项目名:贵州水务                                            *
 * 包名:com.elegant.river_system.views                       *
 * 创建时间:2017年10月12日11:16                               *
 * 更新时间:2017年10月24日11:16                               *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class LoadingDialog extends Dialog {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private static LoadingDialog dialog;
    private Context context;
    private TextView loadingMessage;
    private ProgressBar progressBar;
    private LinearLayout loadingView;
    private ImageView animationIv;
    private AnimationDrawable animationDrawable;
    private LoadColorDrawable drawable;
    private final WeakReference<Context> reference;


    public LoadingDialog(Context context) {
        super(context, R.style.loading_dialog);
        this.reference = new WeakReference<Context>(context);
        this.context = reference.get();
        drawable = new LoadColorDrawable();
        setContentView(R.layout.loading_dialog);
        loadingMessage = findViewById(R.id.loading_message);
        progressBar = findViewById(R.id.loading_progressbar);
        loadingView = findViewById(R.id.loading_view);

        animationIv = findViewById(R.id.animationIv);
        animationIv.setImageResource(R.drawable.refresh_refreshing);
        animationDrawable =  (AnimationDrawable) animationIv.getDrawable();

        /*loadingMessage.setPadding(15, 0, 0, 0);*/
        drawable.setColor(Color.WHITE);
        setBackground(loadingView, drawable);
        if (context != null && !((Activity)context).isFinishing()) {
            if (animationDrawable != null) {
                animationDrawable.start();
            }
        }
    }

    public static LoadingDialog with(Context context) {
        if (dialog == null) {
            dialog = new LoadingDialog(context);
        }
        return dialog;
    }

    public LoadingDialog setOrientation(int orientation) {
        loadingView.setOrientation(orientation);
        if (orientation == HORIZONTAL) {
            loadingMessage.setPadding(15, 0, 0, 0);
        } else {
            loadingMessage.setPadding(0, 15, 0, 0);
        }
        return dialog;
    }

    public LoadingDialog setBackgroundColor(@ColorInt int color) {
        drawable.setColor(color);
        setBackground(loadingView, drawable);
        return dialog;
    }

    /**
     * @param view
     * @param drawable
     */
    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
        }

    }

    @Override
    public void dismiss() {

        try {
            if (reference != null) {
                context  = reference.get();
            }
            if (context != null && !((Activity)context).isFinishing()) {
                if(animationDrawable != null) {
                    animationDrawable.stop();
                    animationDrawable = null;
                }
                dialog = null;
            }
        } catch (Exception e) {
            LogUtil.e(e.getMessage()+"-");
        }
        super.dismiss();
    }

    public LoadingDialog setCanceled(boolean cancel) {
        setCanceledOnTouchOutside(cancel);
        setCancelable(true);
        return dialog;
    }

    public LoadingDialog setMessage(String message) {
        if (!TextUtils.isEmpty(message)) {
            loadingMessage.setText(message);
        }
        return this;
    }

    public LoadingDialog setMessageColor(@ColorInt int color) {
        loadingMessage.setTextColor(color);
        return this;
    }

    /*@Override
    public void show() {
        if (context != null && !((Activity)context).isFinishing()) {
            if (dialog != null) {
                dialog.show();
            }
        }
        super.show();
    }*/
}
