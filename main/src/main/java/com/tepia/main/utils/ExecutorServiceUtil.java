package com.tepia.main.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * Created by liying on 2018/8/16.
 */

public class ExecutorServiceUtil {
    public static ExecutorService  poolExecutor = new ThreadPoolExecutor(1, 5,
            1, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(128));

}
