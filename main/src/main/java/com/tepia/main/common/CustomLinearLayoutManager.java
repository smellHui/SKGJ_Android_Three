package com.tepia.main.common;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-20
 * Time            :       下午9:24
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       禁止滑动
 **/

public class CustomLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
