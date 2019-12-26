package com.tepia.main.view.weatherforecast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.model.weather.WeahterBean;
import com.tepia.main.model.weather.Weather2Response;
import com.tepia.main.model.weather.WeatherHour24Response;
import com.tepia.main.model.weather.WeatherManager;
import com.tepia.main.model.weather.WeatherResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :       天气预报
 **/

public class WeatherForecastPresenter extends BasePresenterImpl<WeatherForecastContract.View> implements WeatherForecastContract.Presenter {

    public void getWeather() {
        String city = "武汉";
        WeahterBean weahterBean = WeatherManager.getInstance().getWeatherBean();
        if (weahterBean != null) {
            mView.getWeatherSuccess(weahterBean);
        } else {
            WeatherManager.getInstance().getWeather(city).safeSubscribe(new Observer<WeatherResponse>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(WeatherResponse weatherResponse) {
                    if ("0".equals(weatherResponse.getStatus())) {
                        mView.getWeatherSuccess(weatherResponse.getResult());
                        SPUtils.getInstance(Utils.getContext()).putString("WEATHERBEAN", new Gson().toJson(weatherResponse.getResult()));
                    }
                }

                @Override
                public void onError(Throwable e) {
                    ToastUtils.shortToast("请求天气出错");
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    public void getWeatherbyArea(String area) {
        WeatherManager.getInstance().getWeatherbyArea(area).safeSubscribe(new Observer<Weather2Response>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Weather2Response weather2Response) {
                if (weather2Response.getShowapi_res_code() == 0 && weather2Response.getShowapi_res_body().getRet_code() == 0) {
                    getWeatherbyHour24(weather2Response.getShowapi_res_body().getCityInfo().getC5());
                    if (mView != null){
                        mView.getWeather2Success(weather2Response.getShowapi_res_body());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null){
                    mView.getWeatherfail();
                }
            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void getWeatherbyHour24(String area) {
        WeatherManager.getInstance().getWeatherbyHour24(area).safeSubscribe(new Observer<WeatherHour24Response>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WeatherHour24Response weatherHour24Response) {
                if (weatherHour24Response.getShowapi_res_code() == 0 && weatherHour24Response.getShowapi_res_body().getRet_code() == 0) {
                    mView.getWeatherHour24Success(weatherHour24Response.getShowapi_res_body());
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.getWeatherfail();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
