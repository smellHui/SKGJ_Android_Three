package com.tepia.main.utils;

import android.content.Context;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by BLiYing on 2018/6/1.
 */

public class TimePickerDialogUtil {
    private Context mcontext;
    /**
     * 时间选择相关
     *
     * @return
     */
    public TimePickerDialog startDialog;
    public TimePickerDialog.Builder builder;
    private SimpleDateFormat sf;

    public TimePickerDialogUtil(Context context, SimpleDateFormat sf){
        this.mcontext = context;
        this.sf = sf;
    }

    /**
     * 初始化时间选择器
     */
    public void initTimePicker(OnDateSetListener onDateSetListener,Type type) {
        long tenYears = 5L * 365 * 1000 * 60 * 60 * 24L;
        builder = new TimePickerDialog.Builder()
                .setCallBack(onDateSetListener)
                .setCancelStringId(mcontext.getString(R.string.cancel))
                .setSureStringId(mcontext.getString(R.string.sure))
                .setTitleStringId(mcontext.getString(R.string.starttime))
                .setYearText(mcontext.getString(R.string.year))
                .setMonthText(mcontext.getString(R.string.month))
                .setDayText(mcontext.getString(R.string.day))
                .setHourText(mcontext.getString(R.string.hour))
                .setMinuteText(mcontext.getString(R.string.minute))
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - tenYears) // 最小时间
                .setMaxMillseconds(System.currentTimeMillis()) // 最大时间
                .setCurrentMillseconds(System.currentTimeMillis()) // 当前时间
                .setThemeColor(mcontext.getResources().getColor(R.color.colorPrimary))
                .setType(type)
                .setWheelItemTextNormalColor(Utils.getContext().getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(mcontext.getResources().getColor(R.color.black))
                .setWheelItemTextSize(12);
        startDialog = builder.build();

    }

    /**
     * 初始化时间选择器
     * 设置结束时间为10年后
     */
    public void initTimePickerSetStartAndEnd(OnDateSetListener onDateSetListener,Type type,Long start,Long end) {
        long tenYears = 5L * 365 * 1000 * 60 * 60 * 24L;
        builder = new TimePickerDialog.Builder()
                .setCallBack(onDateSetListener)
                .setCancelStringId(mcontext.getString(R.string.cancel))
                .setSureStringId(mcontext.getString(R.string.sure))
                .setTitleStringId(mcontext.getString(R.string.starttime))
                .setYearText(mcontext.getString(R.string.year))
                .setMonthText(mcontext.getString(R.string.month))
                .setDayText(mcontext.getString(R.string.day))
                .setHourText(mcontext.getString(R.string.hour))
                .setMinuteText(mcontext.getString(R.string.minute))
                .setCyclic(false)
                .setMinMillseconds(start) // 最小时间
                .setMaxMillseconds(end) // 最大时间
                .setCurrentMillseconds(System.currentTimeMillis()) // 当前时间
                .setThemeColor(mcontext.getResources().getColor(R.color.colorPrimary))
                .setType(type)
                .setWheelItemTextNormalColor(Utils.getContext().getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(mcontext.getResources().getColor(R.color.black))
                .setWheelItemTextSize(12);
        startDialog = builder.build();

    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
}
