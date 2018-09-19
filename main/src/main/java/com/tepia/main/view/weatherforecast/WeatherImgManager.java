package com.tepia.main.view.weatherforecast;

import com.tepia.main.R;

import java.util.HashMap;
import java.util.Map;

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

    private Map<String,Integer> imgsRes;

    private WeatherImgManager() {
        imgsRes = new HashMap<>();
        imgsRes.put("0",R.mipmap.weather_0);
        imgsRes.put("1",R.mipmap.weather_1);
        imgsRes.put("2",R.mipmap.weather_2);
        imgsRes.put("3",R.mipmap.weather_3);
        imgsRes.put("4",R.mipmap.weather_4);
        imgsRes.put("5",R.mipmap.weather_5);
        imgsRes.put("6",R.mipmap.weather_6);
        imgsRes.put("7",R.mipmap.weather_7);
        imgsRes.put("8",R.mipmap.weather_8);
        imgsRes.put("9",R.mipmap.weather_9);
        imgsRes.put("00",R.mipmap.weather_0);
        imgsRes.put("01",R.mipmap.weather_1);
        imgsRes.put("02",R.mipmap.weather_2);
        imgsRes.put("03",R.mipmap.weather_3);
        imgsRes.put("04",R.mipmap.weather_4);
        imgsRes.put("05",R.mipmap.weather_5);
        imgsRes.put("06",R.mipmap.weather_6);
        imgsRes.put("07",R.mipmap.weather_7);
        imgsRes.put("08",R.mipmap.weather_8);
        imgsRes.put("09",R.mipmap.weather_9);
        imgsRes.put("10",R.mipmap.weather_10);
        imgsRes.put("11",R.mipmap.weather_11);
        imgsRes.put("12",R.mipmap.weather_12);
        imgsRes.put("13",R.mipmap.weather_13);
        imgsRes.put("14",R.mipmap.weather_14);
        imgsRes.put("15",R.mipmap.weather_15);
        imgsRes.put("16",R.mipmap.weather_16);
        imgsRes.put("17",R.mipmap.weather_17);
        imgsRes.put("18",R.mipmap.weather_18);
        imgsRes.put("19",R.mipmap.weather_19);
        imgsRes.put("20",R.mipmap.weather_20);
        imgsRes.put("21",R.mipmap.weather_21);
        imgsRes.put("22",R.mipmap.weather_22);
        imgsRes.put("23",R.mipmap.weather_23);
        imgsRes.put("24",R.mipmap.weather_24);
        imgsRes.put("25",R.mipmap.weather_25);
        imgsRes.put("26",R.mipmap.weather_26);
        imgsRes.put("27",R.mipmap.weather_27);
        imgsRes.put("28",R.mipmap.weather_28);
        imgsRes.put("29",R.mipmap.weather_29);
        imgsRes.put("30",R.mipmap.weather_30);
        imgsRes.put("31",R.mipmap.weather_31);
        imgsRes.put("32",R.mipmap.weather_32);
        imgsRes.put("49",R.mipmap.weather_49);
        imgsRes.put("53",R.mipmap.weather_53);
        imgsRes.put("54",R.mipmap.weather_54);
        imgsRes.put("55",R.mipmap.weather_55);
        imgsRes.put("56",R.mipmap.weather_56);
        imgsRes.put("57",R.mipmap.weather_57);
        imgsRes.put("58",R.mipmap.weather_58);
        imgsRes.put("99",R.mipmap.weather_99);
        imgsRes.put("301",R.mipmap.weather_301);
        imgsRes.put("302",R.mipmap.weather_302);


    }

    public Integer getImgRes(String index) {
        return imgsRes.get(index);
    }

}
