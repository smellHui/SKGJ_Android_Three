package com.tepia.main.view.weatherforecast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.model.weather.WeahterBean;
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
                    if (weatherResponse.getStatus().equals("0")) {
                        mView.getWeatherSuccess(weatherResponse.getResult());
                        SPUtils.getInstance(Utils.getContext()).putString("WEATHERBEAN", new Gson().toJson(weatherResponse.getResult()));
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
}
