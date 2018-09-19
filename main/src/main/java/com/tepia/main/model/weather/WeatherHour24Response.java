package com.tepia.main.model.weather;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-19
 * Time            :       15:49
 * Version         :       1.0
 * 功能描述        :
 **/
public class WeatherHour24Response {

    /**
     * showapi_res_error :
     * showapi_res_id : 98374bb50bd54d95b15079167ac06976
     * showapi_res_code : 0
     * showapi_res_body : {"hourList":[{"time":"201809191500","weather_code":"00","temperature":"33","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809191600","weather_code":"01","temperature":"32","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809191700","weather_code":"01","temperature":"31","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809191800","weather_code":"00","temperature":"29","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809191900","weather_code":"00","temperature":"29","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809192000","weather_code":"00","temperature":"28","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809192100","weather_code":"00","temperature":"27","areaid":"101230506","wind_direction":"东南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809192200","weather_code":"00","temperature":"27","areaid":"101230506","wind_direction":"东南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809192300","weather_code":"00","temperature":"26","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809200000","weather_code":"00","temperature":"26","areaid":"101230506","wind_direction":"南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809200100","weather_code":"00","temperature":"25","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809200200","weather_code":"00","temperature":"25","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809200300","weather_code":"01","temperature":"24","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809200400","weather_code":"01","temperature":"24","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809200500","weather_code":"01","temperature":"24","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809200600","weather_code":"01","temperature":"24","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809200700","weather_code":"01","temperature":"25","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809200800","weather_code":"01","temperature":"26","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809200900","weather_code":"01","temperature":"28","areaid":"101230506","wind_direction":"东南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809201000","weather_code":"01","temperature":"30","areaid":"101230506","wind_direction":"东南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809201100","weather_code":"01","temperature":"32","areaid":"101230506","wind_direction":"东南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809201200","weather_code":"01","temperature":"33","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809201300","weather_code":"01","temperature":"34","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809201400","weather_code":"01","temperature":"34","areaid":"101230506","wind_direction":"东南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"}],"areaid":"101230506","area":"南安","ret_code":0}
     */

    private String showapi_res_error;
    private String showapi_res_id;
    private int showapi_res_code;
    private Hour24Bean showapi_res_body;

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public String getShowapi_res_id() {
        return showapi_res_id;
    }

    public void setShowapi_res_id(String showapi_res_id) {
        this.showapi_res_id = showapi_res_id;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }


    public Hour24Bean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(Hour24Bean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }
}
