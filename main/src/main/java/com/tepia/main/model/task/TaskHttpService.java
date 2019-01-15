package com.tepia.main.model.task;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.task.response.AllItemListResponse;
import com.tepia.main.model.task.response.CandidateResponse;
import com.tepia.main.model.task.response.TaskDetailResponse;
import com.tepia.main.model.task.response.TaskItemDetailResponse;
import com.tepia.main.model.task.response.TaskItemListResponse;
import com.tepia.main.model.task.response.TaskListResponse;
import com.tepia.main.model.task.response.TaskNumResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Joeshould on 2018/5/23.
 */

interface TaskHttpService {

    /**
     * 根据条件查询工单列表信息
     *
     * @param token
     * @param workOrderName 工单名称
     * @param executeStatus 执行状态( 1-待执行，2-执行中，3-已提交，4-运维已审核，5-已生成报告)
     * @param planType
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param currentPage   当前页
     * @param pageSize      每页数量
     * @return
     */
    @GET("app/appWorkOrder/listAppWorkOrder")
    Observable<TaskListResponse> listAppWorkOrder(@Header("Authorization") String token,
                                                  @Query("workOrderName") String workOrderName,
                                                  @Query("executeStatus") int executeStatus,
                                                  @Query("operationType") String planType,
                                                  @Query("startTime") String startTime,
                                                  @Query("endTime") String endTime,
                                                  @Query("currentPage") int currentPage,
                                                  @Query("pageSize") int pageSize);

    @GET("app/appWorkOrder/listAppWorkOrder")
    Observable<TaskListResponse> listAppWorkOrder2(@Header("Authorization") String token,
                                                   @Query("workOrderName") String workOrderName,
                                                   @Query("operationType") String operationType,
                                                   @Query("startTime") String startTime,
                                                   @Query("endTime") String endTime,
                                                   @Query("currentPage") int currentPage,
                                                   @Query("pageSize") int pageSize);

    @GET("app/appWorkOrderManage/listPageWorks")
    Observable<TaskListResponse> listPageWorks(@Header("Authorization") String token,
                                               @Query("workOrderName") String workOrderName,
                                               @Query("executeStatus") int executeStatus,
                                               @Query("operationType") String operationType,
                                               @Query("planType") String planType,
                                               @Query("planPeriod") String planPeriod,
                                               @Query("startTime") String startTime,
                                               @Query("endTime") String endTime,
                                               @Query("currentPage") int currentPage,
                                               @Query("pageSize") int pageSize);

    @GET("app/appWorkOrderManage/listPageWorks")
    Observable<TaskListResponse> listPageWorks2(@Header("Authorization") String token,
                                                @Query("workOrderName") String workOrderName,
                                                @Query("operationType") String operationType,
                                                @Query("planType") String planType,
                                                @Query("planPeriod") String planPeriod,
                                                @Query("startTime") String startTime,
                                                @Query("endTime") String endTime,
                                                @Query("currentPage") int currentPage,
                                                @Query("pageSize") int pageSize);

    /**
     * 根据工单ID查询工单信息
     *
     * @param token
     * @param workOrderId 工单id
     * @return
     */
    @GET("app/appWorkOrder/getAppWorkByWorkId")
    Observable<TaskDetailResponse> getAppWorkByWorkId(@Header("Authorization") String token,
                                                      @Query("workOrderId") String workOrderId);

    /**
     * 修改工单状态为执行中
     *
     * @param token
     * @param workOrderId 工单id
     * @return
     */
    @FormUrlEncoded
    @POST("app/appWorkOrder/startExecute")
    Observable<BaseResponse> startExecute(@Header("Authorization") String token,
                                          @Field("workOrderId") String workOrderId,
                                          @Field("positionStr") String positionStr);

    /**
     * 查询巡检项详细信息
     *
     * @param token
     * @param itemId 任务项id
     * @return
     */

    @GET("app/appReservoirWorkOrderItem/getAppReservoirWorkOrderItemInfo")
    Observable<TaskItemDetailResponse> getAppReservoirWorkOrderItemInfo(@Header("Authorization") String token,
                                                                        @Query("itemId") String itemId);

    /**
     * APP 提交单个配置运维结果
     *
     * @param params
     * @param beforePathList
     * @param afterPathList
     * @return
     */
    @Multipart
    @POST("app/appReservoirWorkOrderItem/commitOne")
    Observable<BaseResponse> appReservoirWorkOrderItemCommitOne(@Header("Authorization") String token,
                                                                @PartMap Map<String, RequestBody> params,
                                                                @Part List<MultipartBody.Part> beforePathList,
                                                                @Part List<MultipartBody.Part> afterPathList);


    /**
     * APP 巡检人员工单执行完成提交 （App 端使用）
     *
     * @param token
     * @param workOrderId    工单id
     * @param workOrderRoute 流线信息
     * @return
     */
    @FormUrlEncoded
    @POST("app/appWorkOrder/endExecute")
    Observable<BaseResponse> endExecute(@Header("Authorization") String token,
                                        @Field("workOrderId") String workOrderId,
                                        @Field("workOrderRoute") String workOrderRoute);
    @FormUrlEncoded
    @POST("app/workOrderTrp/endExecute")
    Observable<BaseResponse> endExecute2(@Header("Authorization") String token,
                                        @Field("workOrderId") String workOrderId,
                                        @Field("workOrderRoute") String workOrderRoute);


    /**
     * 删除文件
     *
     * @param token
     * @param fileId 文件id
     * @return
     */
    @DELETE("app/appFile/delFile")
    Observable<BaseResponse> delFile(@Header("Authorization") String token,
                                     @Query("fileId") String fileId);


    /**
     * 获取任务个数
     *
     * @param token
     * @return
     */
    @GET("app/appWorkOrder/statisticsWorkByType")
    Observable<TaskNumResponse> statisticsEorkByType(@Header("Authorization") String token);

    /**
     * 获取工单下发候选用户
     *
     * @param token
     * @param workOrderId
     * @return
     */
    @GET("app/appWorkOrderManage/candidate/{workOrderId}")
    Observable<CandidateResponse> candidateTask(@Header("Authorization") String token,
                                                @Path("workOrderId") String workOrderId);

    /**
     * 删除作废工单
     *
     * @param token
     * @param workOrderId
     * @return
     */
    @FormUrlEncoded
    @POST("app/appWorkOrderManage/deleteWork")
    Observable<BaseResponse> deleteWork(@Header("Authorization") String token,
                                        @Field("workOrderId") String workOrderId);

    /**
     * 获取工单审批流水列表
     *
     * @param token
     * @param workOrderId
     * @return
     */
    @GET("app/appWorkOrder/statisticsWorkByType")
    Observable<BaseResponse> getExamineFlowList(@Header("Authorization") String token,
                                                @Query("workOrderId") String workOrderId);

    /**
     * 下发工单
     *
     * @param token
     * @param workOrderId
     * @param userCode
     * @return
     */
    @GET("app/appWorkOrderManage/sendOrder")
    Observable<BaseResponse> sendOrder(@Header("Authorization") String token,
                                       @Query("orderId") String workOrderId,
                                       @Query("userCode") String userCode);

    /**
     * 修改工单信息以及工单配置项
     *
     * @param token
     * @param workOrderId
     * @param workOrderName
     * @param planStartTime
     * @param planEndTime
     * @param remarks
     * @param workOrderItemIds
     * @return
     */
    @FormUrlEncoded
    @POST("app/appWorkOrderManage/updateWork")
    Observable<BaseResponse> updateWork(@Header("Authorization") String token,
                                        @Field("workOrderId") String workOrderId,
                                        @Field("workOrderName") String workOrderName,
                                        @Field("planStartTime") String planStartTime,
                                        @Field("planEndTime") String planEndTime,
                                        @Field("remarks") String remarks,
                                        @Field("workOrderItemIds") String workOrderItemIds);

    @GET("app/appReservoirWorkOrderItem/getAllItemList")
    Observable<AllItemListResponse> getAllItemList(@Header("Authorization") String token,
                                                   @Query("workOrderId") String workOrderId);

    /**
     * 分页查询巡查工单列表
     *
     * @param token
     * @param operationType
     * @param reservoirId
     * @param startDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/workOrderTrp/getPatrolWorkOrderList")
    Observable<TaskListResponse> getPatrolWorkOrderList(@Header("Authorization") String token,
                                                        @Query("operationType") String operationType,
                                                        @Query("reservoirId") String reservoirId,
                                                        @Query("startDate") String startDate,
                                                        @Query("endDate") String endDate,
                                                        @Query("currentPage") String currentPage,
                                                        @Query("pageSize") String pageSize);

    @FormUrlEncoded
    @POST("app/workOrderTrp/startExecute")
    Observable<TaskDetailResponse> newStartExecute(@Header("Authorization") String token,
                                                   @Field("reservoirId") String reservoirId,
                                                   @Field("reservoirName") String reservoirName,
                                                   @Field("operationType") String operationType);


    @GET("app/workOrderTrp/getWorkOrderNumByXc")
    Observable<WorkOrderNumResponse> getWorkOrderNumByReservoirId(@Header("Authorization") String token,
                                                                  @Query("reservoirId") String reservoirId,
                                                                  @Query("operationType") String operationType);

    @GET("app/workOrderTrp/getItemListByReservoirId")
    Observable<TaskItemListResponse> getItemListByReservoirId(@Header("Authorization") String token,
                                                              @Query("reservoirId") String reservoirId,
                                                              @Query("operationType") String operationType);

    @GET("app/workOrderTrp/getUnfinishedNum")
    Observable<UnfinishedNumResponse> getUnfinishedNum(@Header("Authorization") String token,
                                                              @Query("reservoirId") String reservoirId,
                                                              @Query("operationType") String operationType);


}
