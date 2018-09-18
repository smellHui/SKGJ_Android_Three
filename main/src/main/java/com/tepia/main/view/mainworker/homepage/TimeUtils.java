package com.tepia.main.view.mainworker.homepage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-17
 * Time            :       15:03
 * Version         :       1.0
 * 功能描述        :
 **/
public class TimeUtils {

    public static String dateFormat_day = "HH:mm";
    public static String dateFormat_month = "MM-dd";

    /**
     * 时间转换成字符串,默认为"yyyy-MM-dd HH:mm:ss"
     *
     * @param time 时间
     */
    public static String dateToString(long time) {
        return dateToString(time, "yyyy.MM.dd HH:mm");
    }

    /**
     * 时间转换成字符串,指定格式
     *
     * @param time   时间
     * @param format 时间格式
     */
    public static String dateToString(long time, String format) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}