package com.tepia.main.common;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.tepia.base.utils.ToastUtils;


/**
 * Author:xch
 * Date:2019/4/4
 * Do:
 */
public abstract class ViewBase extends LinearLayout {

    public Context mContext;
    private View root;

    public ViewBase(Context context) {
        super(context);
        initView(context);
    }

    public ViewBase(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ViewBase(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    void initView(Context ctx) {
        this.mContext = ctx;
        root = LayoutInflater.from(getContext()).inflate(getViewId(), this);
        initData();
    }

    public abstract int getViewId();

    public abstract void initData();

    public Activity getActivity() {
        return (Activity) getContext();
    }

    public View getRoot() {
        return root;
    }

    public void toast(String msg) {
        ToastUtils.getInstance().shortToast(msg);
    }
}
