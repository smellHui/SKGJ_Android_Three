package com.tepia.main;

import android.graphics.Color;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-24
 * Time            :       上午12:48
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       系统配置相关常量
 **/
public class ConfigConsts {
    /**
     * glide参数配置
     */
    public static final RequestOptions options = new RequestOptions()
            .override(800,800)
            .placeholder(R.mipmap.icon_empty)
            .error(R.mipmap.icon_empty)
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

    /**
     * 流量站详情页面查询类型区分
     */
    public static String[] TimeType = {"time","day","month","years"};

    /**
     * 水质站详情页面查询类型区分
     */
    public static int[] TimeTypeInt = {0,1,2,3,4};
    /**
     * 详情页面线性图表颜色
     */
    public static int colortext = Color.rgb(153, 153, 153);
    /**
     * 详情页面默认查询时间间隔
     */
    public static String timeseriod = "-7";
    /**
     * 极光推送注册id保存
     */
    public static String regisid_jiguang = "regisid_jiguang";
    public static String emergence = "emergence";
    public static String TECHNOLOGRROLE = "210";
    public static final String Telphone = "400152230";


}
