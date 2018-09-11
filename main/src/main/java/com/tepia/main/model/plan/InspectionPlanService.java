package com.tepia.main.model.plan;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.main.model.map.ReservoirResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * 巡检计划
 * @author 44822
 */
interface InspectionPlanService {

    /**
     * 根据条件获取巡检计划列表
     * @param token
     * @param reservoirName 水库名称
     * @param planType      计划类型:(1：日常；2：定期；3：特别)
     * @param operationType     运维类别1：巡检；2：维修养护；3：保洁
     * @param isGenerate      计划状态  0未生成 1已生成
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param currentPage   当前页
     * @param pageSize  每页条数
     * @return
     */
    @GET("app/appPlanInfo/getPlanInfoList")
    Observable<PlanListResponse> getPlanInfoList(@Header("Authorization") String token,
                                                 @Query("reservoirName") String reservoirName,
                                                 @Query("planType") String planType,
                                                 @Query("operationType") String operationType,
                                                 @Query("isGenerate") String isGenerate,
                                                 @Query("startDate") String startDate,
                                                 @Query("endDate") String endDate,
                                                 @Query("currentPage") String currentPage,
                                                 @Query("pageSize") String pageSize
    );

//     params.put("planType", RetrofitManager.convertToRequestBody(planType));

    /**
     * 新增计划
     * @param token
     * @param planType
     * @param operationType
     * @param reservoirId
     * @param planName
     * @param planTimes
     * @param remarks
     * @param problemId
     * @return
     */
    @FormUrlEncoded
    @POST("app/appPlanInfo/saveBizPlanInfo")
    Observable<BaseResponse> saveBizPlanInfo(@Header("Authorization") String token,
                                             @Field("planType") String planType,
                                             @Field("operationType") String operationType,
                                             @Field("reservoirId") String reservoirId,
                                             @Field("planName") String planName,
                                             @Field("planTimes") String planTimes,
                                             @Field("remarks") String remarks,
                                             @Field("problemId") String problemId
    );

    /**
     * 更新计划
     * @param token
     * @param planId
     * @param planType
     * @param operationType
     * @param reservoirId
     * @param planName
     * @param planTimes
     * @param remarks
     * @return
     */
    @FormUrlEncoded
    @POST("app/appPlanInfo/updateBizPlanInfo")
    Observable<BaseResponse> updateBizPlanInfo(@Header("Authorization") String token,
                                               @Field("planId") String planId,
                                             @Field("planType") String planType,
                                             @Field("operationType") String operationType,
                                             @Field("reservoirId") String reservoirId,
                                             @Field("planName") String planName,
                                             @Field("planTimes") String planTimes,
                                             @Field("remarks") String remarks
    );

    /**
     * 批量删除计划
     * @param token
     * @param planIds
     * @return
     */
    @FormUrlEncoded
    @POST("app/appPlanInfo/deleteByIds")
    Observable<BaseResponse> deleteByIds(@Header("Authorization") String token,
                                         @Field("planIds") String planIds
    );

    /**
     * 批量生成工单
     * @param token
     * @param planIds   58ef924c85e84073abd8b7be54b62986,58ef924c85e84073abd8b7be54b62986
     * @return
     */
    @FormUrlEncoded
    @POST("app/appPlanInfo/createWorkOrder")
    Observable<BaseResponse> createWorkOrder(@Header("Authorization") String token,
                                         @Field("planIds") String planIds
    );
}
