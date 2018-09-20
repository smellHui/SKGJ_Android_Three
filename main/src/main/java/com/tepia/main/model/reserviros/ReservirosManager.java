package com.tepia.main.model.reserviros;

import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.detai.DetailManager;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-20
 * Time            :       下午6:49
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       首页水库模块
 **/
public class ReservirosManager {
    private ReserviorsService mRetrofitService;
    private static final ReservirosManager ourInstance = new ReservirosManager();

    public static ReservirosManager getInstance() {
        return ourInstance;
    }

    private  ReservirosManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL+APPCostant.API_SERVER_MONITOR_AREA).create(ReserviorsService.class);
    }
}
