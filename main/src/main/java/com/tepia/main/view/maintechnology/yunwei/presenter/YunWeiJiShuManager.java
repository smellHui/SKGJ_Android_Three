package com.tepia.main.view.maintechnology.yunwei.presenter;

import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.jishu.admin.AdminWorkOrderResponse;
import com.tepia.main.model.jishu.admin.ProblemListByAddvcdResponse;
import com.tepia.main.model.jishu.threepoint.RainConditionResponse;
import com.tepia.main.model.jishu.threepoint.WaterLevelResponse;
import com.tepia.main.model.jishu.yunwei.JiShuRePortDetailResponse;
import com.tepia.main.model.jishu.yunwei.OperationReportListResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderNumResponse;
import com.tepia.main.model.user.UserManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-25
 * Time    :       12:01
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class YunWeiJiShuManager {
    private YunWeiJIShuService mRetrofitService;
    private static final YunWeiJiShuManager ourInstance = new YunWeiJiShuManager();

    public static YunWeiJiShuManager getInstance() {
        return ourInstance;
    }

    private YunWeiJiShuManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL + APPCostant.API_SERVER_TASK_AREA).create(YunWeiJIShuService.class);
    }

    public Observable<WorkOrderListResponse> getNoProcessWorkOrderList(String reservoirId, String operationType, String startDate, String endDate, String currentPage, String pageSize,String executeStatus) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getNoProcessWorkOrderList(token, reservoirId, operationType, startDate, endDate, currentPage, pageSize,executeStatus).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<WorkOrderNumResponse> getWorkOrderNumByJs(String reservoirId, String operationType, String startDate, String endDate) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getWorkOrderNumByJs(token, reservoirId, operationType, startDate, endDate).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OperationReportListResponse> getProblemList(String reservoirId, String workOrderId, String startDate, String endDate, String currentPage, String pageSize, String problemStatus) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getProblemList(token, reservoirId, workOrderId, startDate, endDate, currentPage, pageSize, problemStatus).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<JiShuRePortDetailResponse> getDetailedProblemInfoByProblemId(String problemId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getDetailedProblemInfoByProblemId(token, problemId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<RainConditionResponse> listStPpthRByReservoir(String reservoirId, String startDate, String endDate, String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        YunWeiJIShuService yunWeiJIShuService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL + APPCostant.API_SERVER_MONITOR_AREA).create(YunWeiJIShuService.class);
        return yunWeiJIShuService.listStPpthRByReservoir(token, reservoirId, startDate, endDate, currentPage, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<WaterLevelResponse> listStRsvrRRByReservoir(String reservoirId, String startDate, String endDate, String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        YunWeiJIShuService yunWeiJIShuService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL + APPCostant.API_SERVER_MONITOR_AREA).create(YunWeiJIShuService.class);
        return yunWeiJIShuService.listStRsvrRRByReservoir(token, reservoirId, startDate, endDate, currentPage, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AdminWorkOrderResponse> getAdminWorkOrderList(String reservoirId, String operationType, String queryDate, String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getAdminWorkOrderList(token, reservoirId, operationType, queryDate, currentPage, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AdminWorkOrderResponse> getAdminProblemList(String reservoirId, String queryDate, String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getAdminProblemList(token, reservoirId, queryDate, currentPage, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AdminWorkOrderResponse> getTownWorkOrderList(String operationType, String areaCode, String queryDate, String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getTownWorkOrderList(token, operationType, areaCode, queryDate, currentPage, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AdminWorkOrderResponse> getTownProblemList(String areaCode, String queryDate, String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getTownProblemList(token, areaCode, queryDate, currentPage, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ProblemListByAddvcdResponse> getProblemListByAddvcd(String areaCode, String queryDate) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getProblemListByAddvcd(token, areaCode, queryDate).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AdminWorkOrderResponse> getWorkOrderListByAreaCode(String operationType, String areaCode,String queryDate) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getWorkOrderListByAreaCode(token,operationType,areaCode,queryDate).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
