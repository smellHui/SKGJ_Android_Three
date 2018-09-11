package com.tepia.base.utils;

import android.content.Context;

import com.google.gson.JsonSyntaxException;
import com.tepia.base.R;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * Created by 44822 on 2018/4/9.
 */

public class UnifiedErrorUtil {
    /** 统一错误处理 -> 汉化了提示，以下错误出现的情况 (ps:不一定百分百按我注释的情况，可能其他情况)*/
    public static Throwable unifiedError(Throwable e , Context mContext){
        Throwable throwable;
        if(e instanceof UnknownHostException || e instanceof HttpException) {
            //无网络的情况，或者主机挂掉了。返回，对应消息  Unable to resolve host "m.app.haosou.com": No address associated with hostname
            if (!NetUtil.isNetworkConnected(mContext)) {
                //无网络
                throwable = new Throwable("请先连接网络",e.getCause());
            } else {
                //主机挂了，也就是你服务器关了
                throwable = new Throwable("服务器开小差,请稍后重试！", e.getCause());
            }
        } else if(e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof SocketException){
            //连接超时等
            throwable = new Throwable("网络连接超时,无法请求到数据！", e.getCause());
        } else if(e instanceof NumberFormatException || e instanceof  IllegalArgumentException || e instanceof JsonSyntaxException){
            //也就是后台返回的数据，与你本地定义的Gson类，不一致，导致解析异常 (ps:当然这不能跟客户这么说)
            throwable = new Throwable("无法请求到数据，正在修复!", e.getCause());
        }else{
            //其他 未知
            throwable = new Throwable("未知问题", e.getCause());
        }
        return throwable;
    }
}
