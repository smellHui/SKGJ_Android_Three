package com.tepia.main.model.shangbao;

import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.reserviros.ReserviorsService;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-20
 * Time            :       下午6:49
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       首页上报模块
 **/
public class ShangbaoManager {
    private ShangbaoService mRetrofitService;
    private static final ShangbaoManager ourInstance = new ShangbaoManager();

    public static ShangbaoManager getInstance() {
        return ourInstance;
    }

    private ShangbaoManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL+APPCostant.API_SERVER_MONITOR_AREA).create(ShangbaoService.class);
    }
}
