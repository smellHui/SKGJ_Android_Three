package com.tepia.main.model.weather;

import android.text.TextUtils;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tepia.base.http.RetrofitManager;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.APPCostant;
import com.tepia.main.model.user.UserManager;


import net.lucode.hackware.magicindicator.buildins.UIUtil;

import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 数据字典
 * Created by Joeshould on 2018/7/31.
 */

public class WeatherManager {
    private static final WeatherManager ourInstance = new WeatherManager();
    private WeatherService mRetrofitService;
    private WeahterBean weatherBean;


    public static WeatherManager getInstance() {
        return ourInstance;
    }

    private WeatherManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL_WEATHER).create(WeatherService.class);

    }

    private void getWeatherEntity() {
        getWeather("武汉").safeSubscribe(new Observer<WeatherResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WeatherResponse weatherResponse) {
                if (weatherResponse.getStatus().equals("0")) {
                    weatherBean = weatherResponse.getResult();
                    SPUtils.getInstance(Utils.getContext()).putString("WEATHERBEAN", new Gson().toJson(weatherBean));
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 获取数据字典
     *
     * @param city
     * @return
     */
    public Observable<WeatherResponse> getWeather(String city) {
        String token = UserManager.getInstance().getToken();
        String appcode = APPCostant.API_SERVER_URL_WEATHER_APP_CODE;
        return mRetrofitService.getWeather(appcode, city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public WeahterBean getWeatherBean() {
        weatherBean = null;
        String temp = SPUtils.getInstance(Utils.getContext()).getString("WEATHERBEAN", "");
        if (!TextUtils.isEmpty(temp)) {
            WeahterBean bean = new Gson().fromJson(temp, WeahterBean.class);
            if (bean == null) {
                getWeatherEntity();
            } else {
                Date lastUpdateTime = TimeFormatUtils.strToDateLong(bean.getUpdatetime());
                Date nowDate = new Date(System.currentTimeMillis());

                if (lastUpdateTime.getHours() != nowDate.getTime()) {
//                    getWeatherEntity();
                    weatherBean = bean;
                } else {
                    weatherBean = bean;
                }
            }

        } else {
            getWeatherEntity();
        }
        return weatherBean;
    }
}
