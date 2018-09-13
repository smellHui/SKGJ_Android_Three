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
import com.tepia.main.model.weather.WeahterBean;

import static com.tepia.base.utils.Utils.getContext;


/**
 * @author         :      zhang xinhua
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
        adapterTempHourly = new AdapterTempHourly(R.layout.lv_item_temp_hourly,null);
        mBinding.rvTempHourly.setNestedScrollingEnabled(false);
        mBinding.rvTempHourly.setAdapter(adapterTempHourly);

        mBinding.rvTempDaily.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterTempDaily = new AdapterTempDaily(R.layout.lv_item_temp_daily,null);
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
        mPresenter.getWeather();
    }

    @Override
    public void getWeatherSuccess(WeahterBean weahterBean) {
        refreshView(weahterBean);
    }

    private void refreshView(WeahterBean weahterBean) {
        mBinding.weatherPlace.setText(weahterBean.getCity());
        mBinding.tvTempCur.setText(weahterBean.getTemp());
        mBinding.tvTempStatus.setText(weahterBean.getWeather());
        mBinding.tvTempMax.setText(weahterBean.getTemphigh());
        mBinding.tvTempMin.setText(weahterBean.getTemplow());

        adapterTempHourly.setNewData(weahterBean.getHourly());
        adapterTempDaily.setNewData(weahterBean.getDaily());
    }
}
