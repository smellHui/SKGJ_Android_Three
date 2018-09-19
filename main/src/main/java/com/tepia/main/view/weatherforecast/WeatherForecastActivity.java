package com.tepia.main.view.weatherforecast;


import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.barlibrary.ImmersionBar;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.main.R;
import com.tepia.main.databinding.ActivityWeatherForecastBinding;
import com.tepia.main.model.weather.Hour24Bean;
import com.tepia.main.model.weather.WeahterBean;
import com.tepia.main.model.weather.Weather2Bean;

import java.util.ArrayList;

import static com.tepia.base.utils.Utils.getContext;


/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        天气预报
 **/
@Route(path = AppRoutePath.app_weather_forecast)
public class WeatherForecastActivity extends MVPBaseActivity<WeatherForecastContract.View, WeatherForecastPresenter> implements WeatherForecastContract.View {

    private ActivityWeatherForecastBinding mBinding;
    private AdapterTempHourly adapterTempHourly;
    private AdapterTempDaily adapterTempDaily;

    @Override
    public int getLayoutId() {
        return R.layout.activity_weather_forecast;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
        ImmersionBar.setTitleBar(this, mBinding.loTitle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvTempHourly.setLayoutManager(layoutManager);
        adapterTempHourly = new AdapterTempHourly(R.layout.lv_item_temp_hourly, null);
        mBinding.rvTempHourly.setNestedScrollingEnabled(false);
        mBinding.rvTempHourly.setAdapter(adapterTempHourly);

        mBinding.rvTempDaily.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterTempDaily = new AdapterTempDaily(R.layout.lv_item_temp_daily, null);
        mBinding.rvTempDaily.setNestedScrollingEnabled(false);
        mBinding.rvTempDaily.setAdapter(adapterTempDaily);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        mPresenter.getWeatherbyArea();
    }

    @Override
    public void getWeatherSuccess(WeahterBean weahterBean) {
        refreshView(weahterBean);
    }

    @Override
    public void getWeatherHour24Success(Hour24Bean hour24Bean) {
        adapterTempHourly.setNewData(hour24Bean.getHourList());
    }

    /**
     * @param weather2Bean
     */
    @Override
    public void getWeather2Success(Weather2Bean weather2Bean) {
        refreshView_now(weather2Bean.getNow());
        ArrayList<Weather2Bean.FBean> dailyList = new ArrayList<>();
        dailyList.add(weather2Bean.getF1());
        dailyList.add(weather2Bean.getF2());
        dailyList.add(weather2Bean.getF3());
        dailyList.add(weather2Bean.getF4());
        dailyList.add(weather2Bean.getF5());
        dailyList.add(weather2Bean.getF6());
        dailyList.add(weather2Bean.getF7());
        refreshView_week(dailyList);
        if (weather2Bean.getF1() != null) {
            mBinding.tvTempMax.setText(weather2Bean.getF1().getDay_air_temperature());
            mBinding.tvTempMin.setText(weather2Bean.getF1().getNight_air_temperature());
        }
    }

    private void refreshView_week(ArrayList<Weather2Bean.FBean> dailyList) {
        adapterTempDaily.setNewData(dailyList);
    }

    private void refreshView_now(Weather2Bean.NowBean now) {
        mBinding.tvTempCur.setText(now.getTemperature());
        mBinding.tvTempStatus.setText(now.getWeather());
        mBinding.tvQuality.setText("空气质量："+now.getAqiDetail().getQuality());
    }

    private void refreshView(WeahterBean weahterBean) {
        mBinding.weatherPlace.setText(weahterBean.getCity());
        mBinding.tvTempCur.setText(weahterBean.getTemp());
        mBinding.tvTempStatus.setText(weahterBean.getWeather());
        mBinding.tvTempMax.setText(weahterBean.getTemphigh());
        mBinding.tvTempMin.setText(weahterBean.getTemplow());

//        adapterTempHourly.setNewData(weahterBean.getHourly());
//        adapterTempDaily.setNewData(weahterBean.getDaily());
    }
}
