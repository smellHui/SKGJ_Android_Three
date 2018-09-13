package com.tepia.main.view.weatherforecast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BasePresenterImpl;
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
        WeatherManager.getInstance().getWeather(city).safeSubscribe(new Observer<WeatherResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WeatherResponse weatherResponse) {
                if (weatherResponse.getStatus().equals("0")) {
                    mView.getWeatherSuccess(weatherResponse.getResult());
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
}
