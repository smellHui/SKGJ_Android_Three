package com.tepia.main.view.weatherforecast;

import android.content.Context;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.weather.WeahterBean;
import com.tepia.main.model.weather.WeatherResponse;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WeatherForecastContract {
    interface View extends BaseView {

        void getWeatherSuccess(WeahterBean result);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
