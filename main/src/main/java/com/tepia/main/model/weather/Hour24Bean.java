package com.tepia.main.model.weather;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-19
 * Time            :       15:51
 * Version         :       1.0
 * 功能描述        :
 **/
public class Hour24Bean {
    /**
     * hourList : [{"time":"201809191500","weather_code":"00","temperature":"33","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809191600","weather_code":"01","temperature":"32","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809191700","weather_code":"01","temperature":"31","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809191800","weather_code":"00","temperature":"29","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809191900","weather_code":"00","temperature":"29","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809192000","weather_code":"00","temperature":"28","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809192100","weather_code":"00","temperature":"27","areaid":"101230506","wind_direction":"东南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809192200","weather_code":"00","temperature":"27","areaid":"101230506","wind_direction":"东南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809192300","weather_code":"00","temperature":"26","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809200000","weather_code":"00","temperature":"26","areaid":"101230506","wind_direction":"南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809200100","weather_code":"00","temperature":"25","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809200200","weather_code":"00","temperature":"25","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"晴"},{"time":"201809200300","weather_code":"01","temperature":"24","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809200400","weather_code":"01","temperature":"24","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809200500","weather_code":"01","temperature":"24","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809200600","weather_code":"01","temperature":"24","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809200700","weather_code":"01","temperature":"25","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809200800","weather_code":"01","temperature":"26","areaid":"101230506","wind_direction":"西北风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809200900","weather_code":"01","temperature":"28","areaid":"101230506","wind_direction":"东南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809201000","weather_code":"01","temperature":"30","areaid":"101230506","wind_direction":"东南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809201100","weather_code":"01","temperature":"32","areaid":"101230506","wind_direction":"东南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809201200","weather_code":"01","temperature":"33","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809201300","weather_code":"01","temperature":"34","areaid":"101230506","wind_direction":"东风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"},{"time":"201809201400","weather_code":"01","temperature":"34","areaid":"101230506","wind_direction":"东南风","wind_power":"0-3级 微风 <5.4m/s","area":"南安","weather":"多云"}]
     * areaid : 101230506
     * area : 南安
     * ret_code : 0
     */

    private String areaid;
    private String area;
    private int ret_code;
    private List<HourListBean> hourList;

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public List<HourListBean> getHourList() {
        return hourList;
    }

    public void setHourList(List<HourListBean> hourList) {
        this.hourList = hourList;
    }

    public static class HourListBean {
        /**
         * time : 201809191500
         * weather_code : 00
         * temperature : 33
         * areaid : 101230506
         * wind_direction : 东风
         * wind_power : 0-3级 微风 <5.4m/s
         * area : 南安
         * weather : 晴
         */

        private String time;
        private String weather_code;
        private String temperature;
        private String areaid;
        private String wind_direction;
        private String wind_power;
        private String area;
        private String weather;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWeather_code() {
            return weather_code;
        }

        public void setWeather_code(String weather_code) {
            this.weather_code = weather_code;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public String getWind_direction() {
            return wind_direction;
        }

        public void setWind_direction(String wind_direction) {
            this.wind_direction = wind_direction;
        }

        public String getWind_power() {
            return wind_power;
        }

        public void setWind_power(String wind_power) {
            this.wind_power = wind_power;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }
    }
}
