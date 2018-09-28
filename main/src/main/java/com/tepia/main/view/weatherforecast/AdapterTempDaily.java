package com.tepia.main.view.weatherforecast;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.databinding.LvItemTempDailyBinding;
import com.tepia.main.model.weather.DailyBean;
import com.tepia.main.model.weather.HourlyBean;
import com.tepia.main.model.weather.Weather2Bean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-12
 * Time            :       21:56
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterTempDaily extends BaseQuickAdapter<Weather2Bean.FBean, BaseViewHolder> {
    public AdapterTempDaily(int layoutResId, @Nullable List<Weather2Bean.FBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Weather2Bean.FBean item) {
        LvItemTempDailyBinding mBinding = DataBindingUtil.bind(helper.itemView);
        String week[] = {"星期一","星期二","星期三","星期四","星期五","星期六","星期天",};

        mBinding.tvTime.setText(item.getDay().substring(item.getDay().length() - 4, item.getDay().length() - 2) + "月"
                + item.getDay().substring(item.getDay().length() - 2) + "日  "+week[item.getWeekday()-1]);
        mBinding.tvTemp.setText(item.getDay_air_temperature() + "℃/" + item.getNight_air_temperature() + "℃");
        mBinding.ivTempStatus.setImageResource(WeatherImgManager.getInstance().getImgRes(item.getDay_weather_code()));

    }
}
