package com.tepia.main.model.report;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.user.UserManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL+APPCostant.API_SERVER_TASK_AREA).create(ShangbaoService.class);
    }

    /**
     * 上报水位
     * @param reservoirId
     * @param rz 水位值
     * @return
     */
    public Observable<BaseResponse> uploadingStRsvr(String reservoirId, String rz) {
        String token = UserManager.getInstance().getToken();
        ShangbaoService mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL+APPCostant.API_SERVER_MONITOR_AREA).create(ShangbaoService.class);
        return mRetrofitService.uploadingStRsvr(token,reservoirId,rz)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 查询应急情况列表
     * @param reservoirId
     * @param workOrderId
     * @param problemStatus
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Observable<EmergenceListBean> getProblemList(String reservoirId,
                                                   String workOrderId,
                                                   String problemStatus,
                                                   String startDate,
                                                   String endDate,
                                                   String currentPage,
                                                   String pageSize
    ) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getProblemList(token,reservoirId,workOrderId,problemStatus,startDate,endDate,currentPage,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
