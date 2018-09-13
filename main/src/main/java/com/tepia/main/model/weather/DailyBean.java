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
public  class DailyBean {
    /**
     * date : 2018-09-12
     * week : 星期三
     * sunrise : 06:04
     * sunset : 18:34
     * night : {"weather":"阵雨","templow":"21","img":"3","winddirect":"东北风","windpower":"微风"}
     * day : {"weather":"多云","temphigh":"29","img":"1","winddirect":"东北风","windpower":"微风"}
     */

    private String date;
    private String week;
    private String sunrise;
    private String sunset;
    private NightBean night;
    private DayBean day;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public NightBean getNight() {
        return night;
    }

    public void setNight(NightBean night) {
        this.night = night;
    }

    public DayBean getDay() {
        return day;
    }

    public void setDay(DayBean day) {
        this.day = day;
    }

    public static class NightBean {
        /**
         * weather : 阵雨
         * templow : 21
         * img : 3
         * winddirect : 东北风
         * windpower : 微风
         */

        private String weather;
        private String templow;
        private String img;
        private String winddirect;
        private String windpower;

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemplow() {
            return templow;
        }

        public void setTemplow(String templow) {
            this.templow = templow;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getWinddirect() {
            return winddirect;
        }

        public void setWinddirect(String winddirect) {
            this.winddirect = winddirect;
        }

        public String getWindpower() {
            return windpower;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }
    }

    public static class DayBean {
        /**
         * weather : 多云
         * temphigh : 29
         * img : 1
         * winddirect : 东北风
         * windpower : 微风
         */

        private String weather;
        private String temphigh;
        private String img;
        private String winddirect;
        private String windpower;

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemphigh() {
            return temphigh;
        }

        public void setTemphigh(String temphigh) {
            this.temphigh = temphigh;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getWinddirect() {
            return winddirect;
        }

        public void setWinddirect(String winddirect) {
            this.winddirect = winddirect;
        }

        public String getWindpower() {
            return windpower;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }
    }
}