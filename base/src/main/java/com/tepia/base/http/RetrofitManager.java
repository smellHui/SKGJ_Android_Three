/*
 * The MIT License (MIT)
 *
 * Copyright 2016 by OCN.YAN
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND TORT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.tepia.base.http;


import android.text.TextUtils;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.tepia.base.BaseApplication;
import com.tepia.base.BuildConfig;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.NetUtil;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.Utils;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:TOTO                                              *
 * 项目名:深圳水务                                            *
 * 包名:com.elegant.river_system.common.net                  *
 * 创建时间:2017年10月12日11:16                               *
 * 更新时间:2017年10月24日11:16                               *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class RetrofitManager {


    private volatile static Retrofit mRetrofit;
    private volatile static Retrofit mRetrofitImage;
    /**
     * 缓存机制
     * 在响应请求之后在 data/data/<包名>/cache 下建立一个response 文件夹，保持缓存数据。
     * 这样我们就可以在请求的时候，如果判断到没有网络，自动读取缓存的数据。
     * 同样这也可以实现，在我们没有网络的情况下，重新打开App可以浏览的之前显示过的内容。
     * 也就是：判断网络，有网络，则从网络获取，并保存到缓存中，无网络，则从缓存中获取。
     */
    private static final Interceptor cacheControlInterceptor = chain -> {
        Request request = chain.request();
        if (!NetUtil.isNetworkConnected(Utils.getContext())) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetUtil.isNetworkConnected(Utils.getContext())) {
            // 有网络时 设置缓存为默认值
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .removeHeader("Pragma")
                    .build();
        } else {
            // 无网络时 设置超时为1周
            int maxStale = 60 * 60 * 24 * 7;
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
    };

    public static RequestBody convertToRequestBody(String param) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), param);
        LogUtil.d("参数是" + requestBody);
        return requestBody;
    }

    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> fileList) {
        List<MultipartBody.Part> param = new ArrayList<>(fileList.size());
        for (File file : fileList) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part params = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            param.add(params);
        }
        return param;
    }

    public static List<MultipartBody.Part> filesToMultipartBodyParts(String name,List<File> fileList) {
        List<MultipartBody.Part> param = new ArrayList<>(fileList.size());
        for (File file : fileList) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part params = MultipartBody.Part.createFormData(name, file.getName(), requestBody);
            param.add(params);
        }
        return param;
    }


    //添加请求头的拦截器
    private static class HttpHeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            //  将token统一放到请求头
            String token = SPUtils.getInstance(Utils.getContext()).getString("token", "");
            //  也可以统一配置用户名
            String user_id = "";
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("token", token)
                    .header("user_id", user_id)
                    .build();
        }
    }

    /**
     * @return
     */
    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            synchronized (Retrofit.class) {
                Cache cache = new Cache(new File(Utils.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 50);
                ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(Utils.getContext()));
                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .cookieJar(cookieJar)
                        .addInterceptor(cacheControlInterceptor)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(40, TimeUnit.SECONDS)
                        .writeTimeout(40, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true);

                if (BuildConfig.DEBUG) {
                    // Log信息拦截器
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    //这里可以选择拦截级别
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    //设置 Debug Log 模式
                    builder.addInterceptor(loggingInterceptor);
                }
                mRetrofit = new Retrofit.Builder()
                        .baseUrl(BuildConfig.API_SERVER_URL)
                        .client(builder.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            }
        }
        return mRetrofit;
    }

    /**
     * @return
     */
    public static Retrofit getRetrofit(String URL) {

        synchronized (Retrofit.class) {
            Cache cache = new Cache(new File(Utils.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 50);
            ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(Utils.getContext()));
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .cookieJar(cookieJar)
                    .addInterceptor(cacheControlInterceptor)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(25, TimeUnit.SECONDS)
                    .writeTimeout(25, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true);

            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                //这里可以选择拦截级别
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }
            mRetrofitImage = new Retrofit.Builder()
                    .baseUrl(URL)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return mRetrofitImage;
    }
}
