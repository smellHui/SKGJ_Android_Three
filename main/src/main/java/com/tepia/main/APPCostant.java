package com.tepia.main;

import android.graphics.Color;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * 用于定义 全局变量 常量
 * Created by Joeshould on 2018/5/28.
 */

public class APPCostant {


    /**
     * host 地址
     * 王志敏IP ：http://192.168.30.151:8765
     * 贵州本地地址 http://192.168.10.99:8765
     * 魏爽-武汉 http://192.168.30.220:8765
     * 武汉本地地址 http://192.168.30.220:8765
     * 外网地址    http://weish.vipgz1.idcfengye.com
     * 鸭溪正式地址 http://218.201.212.243:8765
     * 王志敏ip  http://192.168.30.127:8765
     * 华为服务器（正式地址）  202.98.201.102:1341
     *
     */
    public static String API_SERVER_URL = "http://192.168.30.122:8765/";
    /**
     * 图片服务器地址
     */
    public static final String API_SERVER_IMAGGE_URL = "http://119.1.151.132:3003/";
    /**
     * 天气接口host
     */
    public static final String API_SERVER_URL_WEATHER = "http://0992e2b3fa5e4f1c91215b933a6f8901-cn-beijing.alicloudapi.com/";
    public static final String API_SERVER_URL_WEATHER_APP_CODE = "APPCODE 818a5f6e6f284c3f9ed03876db9e8267";
    /**
     * 用户
     */
    public static final String API_SERVER_USER_AREA = "api/auth/";
    /**
     * admin
     */
    public static final String API_SERVER_USER_ADMIN = "api/admin/";
    /**
     * 运维工单
     */
    public static final String API_SERVER_TASK_AREA = "api/works/";
    /**
     * 综合监控
     */
    public static final String API_SERVER_MONITOR_AREA = "api/compmonitor/";
    /**
     * 太比雅公司官网
     */
    public static final String WEB_COMPANY = "http://www.hydro-soft.cn/";




}
