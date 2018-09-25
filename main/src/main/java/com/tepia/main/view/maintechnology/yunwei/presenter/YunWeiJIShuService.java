package com.tepia.main.view.maintechnology.yunwei.presenter;

import com.tepia.main.model.jishu.yunwei.OperationReportListResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderNumResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
public interface YunWeiJIShuService {

    /**
     * 分页查询无流程工单列表(技术负责人)
     *
     * @param token
     * @param reservoirId
     * @param operationType
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/workOrderTrp/getNoProcessWorkOrderList")
    Observable<WorkOrderListResponse> getNoProcessWorkOrderList(@Header("Authorization") String token,
                                                                @Query("reservoirId") String reservoirId,
                                                                @Query("operationType") String operationType,
                                                                @Query("startDate") String startDate,
                                                                @Query("endDate") String endDate,
                                                                @Query("currentPage") String currentPage,
                                                                @Query("pageSize") String pageSize
    );

    /**
     * 查询水库工单总数和完成数（技术负责人）
     *
     * @param token
     * @param reservoirId
     * @param operationType
     * @param startDate
     * @return
     */
    @GET("app/workOrderTrp/getWorkOrderNumByJs")
    Observable<WorkOrderNumResponse> getWorkOrderNumByJs(@Header("Authorization") String token,
                                                         @Query("reservoirId") String reservoirId,
                                                         @Query("operationType") String operationType,
                                                         @Query("startDate") String startDate
    );


    @GET("app/workOrderTrp/getProblemList")
    Observable<OperationReportListResponse> getProblemList(@Header("Authorization") String token,
                                                           @Query("reservoirId") String reservoirId,
                                                           @Query("workOrderId") String workOrderId,
                                                           @Query("startDate") String startDate,
                                                           @Query("endDate") String endDate,
                                                           @Query("currentPage") String currentPage,
                                                           @Query("pageSize") String pageSize,
                                                           @Query("problemStatus") String problemStatus
    );
}
