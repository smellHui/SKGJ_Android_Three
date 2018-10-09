package com.tepia.main.model.report;

import com.tepia.base.http.BaseResponse;

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
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-20
 * Time            :       下午6:50
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       上报
 **/
public interface ShangbaoService {
    /**
     * 上报水位接口
     * @param token
     * @param reservoirId
     * @param rz 水位
     * @return
     */
    @FormUrlEncoded
    @POST("appThree/StRsvrR/uploadingStRsvr")
    Observable<BaseResponse> uploadingStRsvr(@Header("Authorization") String token,
                                             @Field("reservoirId") String reservoirId,
                                             @Field("rz") String rz

    );

    /**
     * 查询应急情况列表
     * @param token
     * @param reservoirId 水库id
     * @param workOrderId 工单id（巡查列表跳转需传入）
     * @param problemStatus 问题状态（4-未完成  5-已完成）
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/workOrderTrp/getProblemList")
    Observable<EmergenceListBean> getProblemList(@Header("Authorization") String token,
                                           @Query("reservoirId") String reservoirId,
                                           @Query("workOrderId") String workOrderId,
                                           @Query("problemStatus") String problemStatus,
                                           @Query("startDate") String startDate,
                                           @Query("endDate") String endDate,
                                           @Query("currentPage") String currentPage,
                                           @Query("pageSize") String pageSize

    );


    /**
     * 上报应急情况
     * @param token
     * @param parts
     * @param pathList
     * @return
     */
    @Multipart
    @POST("app/workOrderTrp/reportProblem")
    Observable<BaseResponse> reportProblem(@Header("Authorization") String token,
                                           @PartMap Map<String, RequestBody> parts,
                                           @Part List<MultipartBody.Part> pathList

    );

    /**
     * 应急上报反馈
     * @param token
     * @param problemId
     * @param excuteDes
     * @return
     */
    @FormUrlEncoded
    @POST("app/appProblemInfo/feedback")
    Observable<BaseResponse> feedback(@Header("Authorization") String token,
                                             @Field("problemId") String problemId,
                                             @Field("excuteDes") String excuteDes
    );
}
