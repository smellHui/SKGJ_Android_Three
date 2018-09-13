package com.tepia.main.view.weatherforecast;

import com.tepia.main.R;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-13
 * Time            :       9:31
 * Version         :       1.0
 * 功能描述        :
 **/
public class WeatherImgManager {
    private static final WeatherImgManager ourInstance = new WeatherImgManager();

    public static WeatherImgManager getInstance() {
        return ourInstance;
    }

    private Integer[] imgsRes;

    private WeatherImgManager() {
        imgsRes = new Integer[]{R.mipmap.refreshing_01,};
    }

    public Integer[] getImgsRes() {
        return imgsRes;
    }

    public Integer getImgRes(int index) {
        if (index < imgsRes.length) {
            return imgsRes[index];
        } else {
            return Integer.MAX_VALUE;
        }
    }

}
