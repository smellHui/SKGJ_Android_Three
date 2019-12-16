package com.tepia.photo_picker.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.photo_picker.R;
import com.tepia.photo_picker.entity.WaterBean;

/**
 * Author:xch
 * Date:2019/12/11
 * Description:
 */
public class WaterView extends HorizontalScrollView {

    private TextView tv_time;
    private TextView tv_date;
    private TextView tv_week;
    private TextView tv_reservoir_name;

    public WaterView(Context context) {
        super(context);
        initView();
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_water, this);
        tv_time = findViewById(R.id.tv_time);
        tv_date = findViewById(R.id.tv_date);
        tv_week = findViewById(R.id.tv_week);
        tv_reservoir_name = findViewById(R.id.tv_reservoir_name);
    }

    public void setData(@NonNull WaterBean waterBean) {
        tv_reservoir_name.setText(waterBean.getReservoirName());
        tv_time.setText(TimeFormatUtils.getNowTime());
        tv_date.setText(TimeFormatUtils.getStringDateShort());
        tv_week.setText(TimeFormatUtils.getWeek());
    }
}
