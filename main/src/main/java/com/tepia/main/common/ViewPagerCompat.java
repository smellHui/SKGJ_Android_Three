package com.tepia.main.common;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * ViewPager与百度地图等等的滑动冲突
 * Created by BLiYing on 2018/3/30.
 */
public class ViewPagerCompat extends ViewPager {

    public ViewPagerCompat(Context context) {
        super(context);
    }

    public ViewPagerCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if(v.getClass().getName().equals("com.baidu.mapapi.map.MapView")
                || v.getClass().getName().equals("com.amap.api.maps.MapView")
                || v.getClass().getName().equals("com.baidu.mapapi.map.TextureMapView")
                || v.getClass().getName().equals("com.github.mikephil.charting.charts.LineChart")
                || v.getClass().getName().equals("com.github.mikephil.charting.charts.BarChart")
                || v.getClass().getName().equals("com.esri.android.map.MapView")) {
            return true;
        }
        return super.canScroll(v, checkV, dx, x, y);
    }
}
