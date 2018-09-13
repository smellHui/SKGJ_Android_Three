package com.tepia.main.model.weather;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-12
 * Time            :       21:41
 * Version         :       1.0
 * 功能描述        :
 **/
public  class HourlyBean {
    /**
     * time : 19:00
     * weather : 多云
     * temp : 25
     * img : 1
     */

    private String time;
    private String weather;
    private String temp;
    private String img;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
