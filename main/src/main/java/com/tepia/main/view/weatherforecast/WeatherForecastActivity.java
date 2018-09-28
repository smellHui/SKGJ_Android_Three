package com.tepia.main.view.weatherforecast;


import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.amap.api.location.AMapLocation;
import com.example.gaodelibrary.GaodeEntity;
import com.example.gaodelibrary.OnGaodeLibraryListen;
import com.gyf.barlibrary.ImmersionBar;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.AppManager;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
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
    private SimpleLoadDialog simpleLoadDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_weather_forecast;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
        setCenterTitle("武汉");
        showBack();
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
        simpleLoadDialog = new SimpleLoadDialog(AppManager.getInstance().getCurrentActivity(), "请稍等", true);
        if (simpleLoadDialog != null) {
            simpleLoadDialog.show();
        }
        GaodeEntity gaodeEntity = new GaodeEntity(getContext());
        gaodeEntity.initLocation();
        gaodeEntity.startLocation();
        gaodeEntity.setLocationListen(new OnGaodeLibraryListen.LocationListen() {
            @Override
            public void getCurrentGaodeLocation(AMapLocation aMapLocation) {
                if (mPresenter != null) {
                    if (aMapLocation.getCity() != null) {
                        mPresenter.getWeatherbyArea(aMapLocation.getCity());
                        gaodeEntity.closeLocation();
                    }
                }
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void getWeatherSuccess(WeahterBean weahterBean) {
        refreshView(weahterBean);
    }

    @Override
    public void getWeatherHour24Success(Hour24Bean hour24Bean) {
        if (simpleLoadDialog != null) {
            simpleLoadDialog.dismiss();
        }
        adapterTempHourly.setNewData(hour24Bean.getHourList());
    }

    /**
     * @param weather2Bean
     */
    @Override
    public void getWeather2Success(Weather2Bean weather2Bean) {
        if (simpleLoadDialog != null) {
            simpleLoadDialog.dismiss();
        }
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
            mBinding.tvTempMax.setText(weather2Bean.getF1().getDay_air_temperature()+"℃");
            mBinding.tvTempMin.setText(weather2Bean.getF1().getNight_air_temperature()+"℃");
        }
    }

    @Override
    public void getWeatherfail() {
        if (simpleLoadDialog != null) {
            simpleLoadDialog.dismiss();
        }
    }

    private void refreshView_week(ArrayList<Weather2Bean.FBean> dailyList) {
        adapterTempDaily.setNewData(dailyList);
    }

    private void refreshView_now(Weather2Bean.NowBean now) {
        mBinding.tvTempCur.setText(now.getTemperature());
        mBinding.tvTempStatus.setText(now.getWeather());
        mBinding.tvQuality.setText("空气质量：" + now.getAqiDetail().getQuality());
    }

    private void refreshView(WeahterBean weahterBean) {

        setCenterTitle(weahterBean.getCity());
        showBack();
        mBinding.tvTempCur.setText(weahterBean.getTemp());
        mBinding.tvTempStatus.setText(weahterBean.getWeather());
        mBinding.tvTempMax.setText(weahterBean.getTemphigh());
        mBinding.tvTempMin.setText(weahterBean.getTemplow());

//        adapterTempHourly.setNewData(weahterBean.getHourly());
//        adapterTempDaily.setNewData(weahterBean.getDaily());
    }
}
