package com.tepia.main.view.weatherforecast;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.model.weather.DailyBean;
import com.tepia.main.model.weather.HourlyBean;

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
public class AdapterTempDaily extends BaseQuickAdapter<DailyBean,BaseViewHolder> {
    public AdapterTempDaily(int layoutResId, @Nullable List<DailyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyBean item) {

    }
}
