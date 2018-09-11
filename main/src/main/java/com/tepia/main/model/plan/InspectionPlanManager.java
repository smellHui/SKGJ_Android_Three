package com.tepia.main.model.plan;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.map.MainMapManager;
import com.tepia.main.model.map.ReservoirResponse;
import com.tepia.main.model.user.UserManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * 巡检计划
 * @author 44822
 */
public class InspectionPlanManager {
    private InspectionPlanService mRetrofitService;
    private static final InspectionPlanManager ourInstance = new InspectionPlanManager();

    public static InspectionPlanManager getInstance() {
        return ourInstance;
    }

    private InspectionPlanManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL+ APPCostant.API_SERVER_TASK_AREA).create(InspectionPlanService.class);
    }

    public Observable<PlanListResponse> getPlanInfoList(String reservoirName,String planType,String operationType,String isGenerate,String startDate,String endDate,String currentPage,String pageSize) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getPlanInfoList(token,reservoirName,planType,operationType,isGenerate,startDate,endDate,currentPage,pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseResponse> deleteByIds(String planIds) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.deleteByIds(token,planIds).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseResponse> saveBizPlanInfo(String planType,String operationType,String reservoirId,String planName,String planTimes,String remarks,String problemId){
        String token = UserManager.getInstance().getToken();
//        Map<String, RequestBody> params = new HashMap<>();
//        params.put("planType", RetrofitManager.convertToRequestBody(planType));
        return mRetrofitService.saveBizPlanInfo(token,planType,operationType,reservoirId,planName,planTimes,remarks,problemId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseResponse> updateBizPlanInfo(String planId,String planType,String operationType,String reservoirId,String planName,String planTimes,String remarks){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.updateBizPlanInfo(token,planId,planType,operationType,reservoirId,planName,planTimes,remarks).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseResponse> createWorkOrder(String planIds) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.createWorkOrder(token,planIds).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
