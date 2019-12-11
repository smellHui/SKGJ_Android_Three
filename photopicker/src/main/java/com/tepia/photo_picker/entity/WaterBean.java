package com.tepia.photo_picker.entity;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Author:xch
 * Date:2019/12/11
 * Description:水印信息
 */
public class WaterBean implements Serializable {

    private String reservoirName;
    private String locationCity;
    private String x;
    private String y;

    public WaterBean(String reservoirName, String locationCity, String x, String y) {
        this.reservoirName = reservoirName;
        this.locationCity = locationCity;
        this.x = x;
        this.y = y;
    }

    public String getReservoirName() {
        if (TextUtils.isEmpty(locationCity)) {
            if (TextUtils.isEmpty(x) || TextUtils.isEmpty(y)) {
                return reservoirName;
            }
            return reservoirName + "    " + x + "," + y;
        }
        return locationCity + " · " + reservoirName;
    }

    public void setReservoirName(String reservoirName) {
        this.reservoirName = reservoirName;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "WaterBean{" +
                "reservoirName='" + reservoirName + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }
}
