package com.tepia.main.view.maintechnology.yunwei.presenter;

import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.jishu.yunwei.OperationReportListResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderNumResponse;
import com.tepia.main.model.user.UserManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL+ APPCostant.API_SERVER_TASK_AREA).create(YunWeiJIShuService.class);
    }

    public Observable<WorkOrderListResponse> getNoProcessWorkOrderList(String reservoirId, String operationType, String startDate, String endDate, String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getNoProcessWorkOrderList(token,reservoirId,operationType,startDate,endDate,currentPage,pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<WorkOrderNumResponse> getWorkOrderNumByJs(String reservoirId,String operationType,String startDate){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getWorkOrderNumByJs(token,reservoirId,operationType,startDate).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OperationReportListResponse> getProblemList(String reservoirId, String workOrderId, String startDate, String endDate, String currentPage, String pageSize,String problemStatus){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getProblemList(token,reservoirId,workOrderId,startDate,endDate,currentPage,pageSize,problemStatus).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
