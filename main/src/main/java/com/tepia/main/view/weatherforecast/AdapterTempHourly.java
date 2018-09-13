package com.tepia.main.view.weatherforecast;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.main.databinding.LvItemTempHourlyBinding;
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
public class AdapterTempHourly extends BaseQuickAdapter<HourlyBean,BaseViewHolder> {
    public AdapterTempHourly(int layoutResId, @Nullable List<HourlyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HourlyBean item) {
        LvItemTempHourlyBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvHour.setText(item.getTime());
        mBinding.tvTempHour.setText(item.getTemp()+"℃");
    }
}
