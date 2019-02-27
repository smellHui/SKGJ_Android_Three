package com.tepia.main.view.maintechnology.yunwei.presenter;

import com.tepia.main.model.jishu.admin.AdminWorkOrderResponse;
import com.tepia.main.model.jishu.admin.ProblemListByAddvcdResponse;
import com.tepia.main.model.jishu.threepoint.RainConditionResponse;
import com.tepia.main.model.jishu.threepoint.RainHistoryResponse;
import com.tepia.main.model.jishu.threepoint.WaterHistoryResponse;
import com.tepia.main.model.jishu.threepoint.WaterLevelResponse;
import com.tepia.main.model.jishu.yunwei.JiShuRePortDetailResponse;
import com.tepia.main.model.jishu.yunwei.OperationReportListResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderNumResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by      Intellij IDEA
 * 运维技术责任人
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
     * @param executeStatus 2 未完成 3 已完成
     * @return
     */
    @GET("app/workOrderTrp/getNoProcessWorkOrderList")
    Observable<WorkOrderListResponse> getNoProcessWorkOrderList(@Header("Authorization") String token,
                                                                @Query("reservoirId") String reservoirId,
                                                                @Query("operationType") String operationType,
                                                                @Query("startDate") String startDate,
                                                                @Query("endDate") String endDate,
                                                                @Query("currentPage") String currentPage,
                                                                @Query("pageSize") String pageSize,
                                                                @Query("executeStatus") String executeStatus
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
                                                         @Query("startDate") String startDate,
                                                         @Query("endDate") String endDate
    );


    /**
     * 查询应急情况列表
     *
     * @param token
     * @param reservoirId
     * @param workOrderId
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @param problemStatus 4-未完成  5-已完成
     * @return
     */
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

    /**
     * 查询应急详情
     *
     * @param token
     * @param problemId
     * @return
     */
    @GET("app/workOrderTrp/getProblemInfo")
    Observable<JiShuRePortDetailResponse> getDetailedProblemInfoByProblemId(@Header("Authorization") String token,
                                                                            @Query("problemId") String problemId
    );

    /**
     * 根据水库查询雨情列表
     *
     * @param token
     * @param reservoirId 水库id
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("appThree/StPptnR/listStPptnRByReservoir")
    Observable<RainConditionResponse> listStPpthRByReservoir(@Header("Authorization") String token,
                                                             @Query("reservoirId") String reservoirId,
                                                             @Query("startDate") String startDate,
                                                             @Query("endDate") String endDate,
                                                             @Query("currentPage") String currentPage,
                                                             @Query("pageSize") String pageSize
    );

    /**
     * 根据水库查询水情列表
     * @param token
     * @param reservoirId
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("appThree/StRsvrR/listStRsvrRByReservoir")
    Observable<WaterLevelResponse> listStRsvrRRByReservoir(@Header("Authorization") String token,
                                                          @Query("reservoirId") String reservoirId,
                                                          @Query("startDate") String startDate,
                                                          @Query("endDate") String endDate,
                                                          @Query("currentPage") String currentPage,
                                                          @Query("pageSize") String pageSize
    );

    /**
     * 根据水库查询雨情历史列表
     * @param token
     * @param reservoirId
     * @param startDate
     * @param endDate
     * @param selectType 搜索类型，默认按天  day
     * @return
     */
    @GET("appThree/StPptnR/listReservoirStPptnRBySelectType")
    Observable<RainHistoryResponse> getRainHistoryResponse(@Header("Authorization") String token,
                                                                     @Query("reservoirId") String reservoirId,
                                                                     @Query("startDate") String startDate,
                                                                     @Query("endDate") String endDate,
                                                                     @Query("selectType") String selectType
    );

    /**
     * 根据水库查询水情历史列表
     * @param token
     * @param reservoirId
     * @param startDate
     * @param endDate
     * @return
     */
    @GET("appThree/StRsvrR/listWeekStRsvrRByReservoir")
    Observable<WaterHistoryResponse> getWaterHistoryResponse(@Header("Authorization") String token,
                                                                      @Query("reservoirId") String reservoirId,
                                                                      @Query("startDate") String startDate,
                                                                      @Query("endDate") String endDate
    );

    /**
     * 分页查询运维工单统计列表（行政责任人）
     * @param token
     * @param reservoirId
     * @param queryDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/workOrderTrp/getAdminWorkOrderList")
    Observable<AdminWorkOrderResponse> getAdminWorkOrderList(@Header("Authorization") String token,
                                                             @Query("reservoirId") String reservoirId,
                                                             @Query("operationType") String operationType,
                                                             @Query("queryDate") String queryDate,
                                                             @Query("currentPage") String currentPage,
                                                             @Query("pageSize") String pageSize
    );


    @GET("app/workOrderTrp/getAdminProblemList")
    Observable<AdminWorkOrderResponse> getAdminProblemList(@Header("Authorization") String token,
                                                             @Query("reservoirId") String reservoirId,
                                                             @Query("queryDate") String queryDate,
                                                             @Query("currentPage") String currentPage,
                                                             @Query("pageSize") String pageSize
    );

    /**
     * 根据行政编码统计水库工单总数和完成数
     * @param token
     * @param operationType
     * @param areaCode
     * @param queryDate
     * @return
     */
    @GET("app/workOrderTrp/getWorkOrderListByAreaCode")
    Observable<AdminWorkOrderResponse> getWorkOrderListByAreaCode(@Header("Authorization") String token,
                                                           @Query("operationType") String operationType,
                                                           @Query("areaCode") String areaCode,
                                                           @Query("queryDate") String queryDate
    );

    /**
     * 根据行政编码统计水库问题总数和处理完成数
     * @param token
     * @param areaCode
     * @param queryDate
     * @return
     */
    @GET("app/workOrderTrp/getProblemListByAddvcd")
    Observable<ProblemListByAddvcdResponse> getProblemListByAddvcd(@Header("Authorization") String token,
                                                                   @Query("areaCode") String areaCode,
                                                                   @Query("queryDate") String queryDate
    );

    /**
     * 根据乡镇统计工单总数和完成数
     * @param token
     * @param operationType
     * @param areaCode
     * @param queryDate   必须传具体月份
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/workOrderTrp/getTownWorkOrderList")
    Observable<AdminWorkOrderResponse> getTownWorkOrderList(@Header("Authorization") String token,
                                                              @Query("operationType") String operationType,
                                                              @Query("areaCode") String areaCode,
                                                             @Query("queryDate") String queryDate,
                                                            @Query("currentPage") String currentPage,
                                                            @Query("pageSize") String pageSize
    );

    /**
     *根据乡镇统计问题总数和处理完成数
     * @param token
     * @param areaCode
     * @param queryDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/workOrderTrp/getTownProblemList")
    Observable<AdminWorkOrderResponse> getTownProblemList(@Header("Authorization") String token,
                                                            @Query("areaCode") String areaCode,
                                                            @Query("queryDate") String queryDate,
                                                            @Query("currentPage") String currentPage,
                                                            @Query("pageSize") String pageSize
    );


}
