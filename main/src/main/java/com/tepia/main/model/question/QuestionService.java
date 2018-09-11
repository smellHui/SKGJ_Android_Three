package com.tepia.main.model.question;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.user.UserLoginResponse;

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
 * 问题反馈
 * Created by ly on 2018/5/24.
 */
interface QuestionService {
    /**
     * 提交问题
     * @param token
     * @param parts
     * @param pathList
     * @return
     */
    @Multipart
    @POST("app/appProblemInfo/saveAppProblemInfo")
    Observable<BaseResponse> feedback(@Header("Authorization") String token,
                                      @PartMap Map<String, RequestBody> parts,
                                      @Part List<MultipartBody.Part> pathList
    );

    /**
     * 获取水库列表
     *
     * @return
     */
    @GET("app/appReservoirBase/listReservoirSelect")
    Observable<ReservoirBean> listReservoirInCenter(@Header("Authorization") String token);

    /**
     * 意见反馈
     * @param token
     * @param parts
     * @param pathList
     * @return
     */
    @Multipart
    @POST("app/appFeedBack")
    Observable<BaseResponse> appFeedBack(@Header("Authorization") String token,
                                      @PartMap Map<String, RequestBody> parts,
                                      @Part List<MultipartBody.Part> pathList    );

    /**
     * 事件上报查询当前用户上报的所有事件
     * @param token
     * @param reservoirName
     * @param problemTitle
     * @param problemType
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/appProblemInfo/listAllProblem")
    Observable<AllproblemBean> listAllProblem(@Header("Authorization") String token,
                                              @Query("reservoirName") String reservoirName,
                                              @Query("problemTitle") String problemTitle,
                                              @Query("problemType") String problemType,
                                              @Query("currentPage") String currentPage,
                                              @Query("pageSize") String pageSize

    );

    /**
     * 事件详情
     * @param token
     * @param problemId
     * @return
     */
    @GET("app/appProblemInfo/getDetailedProblemInfoByProblemId")
    Observable<ProblemDetailBean> getDetailedProblemInfoByProblemId(@Header("Authorization") String token,
                                              @Query("problemId") String problemId);


    /**
     * 获取待确认问题列表
     * @param token
     * @param reservoirName
     * @param problemCofirmType 问题状态:"problemCofirmType":[{"name":"严重问题","value":"2"},{"name":"一般问题","value":"1"},{"name":"不是问题","value":"0"}],
     * @param problemStatus "problemStatus":{"0":"待确认","1":"已确认","2":"已审核","3":"业主已审核","4":"待反馈","5":"已完结"},
     * @param problemSource "1": "工单问题","2": "app上报","3": "web上报"
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/appProblemInfo/getProblemCallList")
    Observable<ProblemCallListBean> getProblemCallList(@Header("Authorization") String token,
                                              @Query("reservoirName") String reservoirName,
                                              @Query("problemCofirmType") String problemCofirmType,
                                              @Query("problemStatus") String problemStatus,
                                              @Query("problemSource") String problemSource,
                                              @Query("startDate") String startDate,
                                              @Query("endDate") String endDate,
                                              @Query("currentPage") String currentPage,
                                              @Query("pageSize") String pageSize);

    /**
     * 获取待审核问题列表
     * @param token
     * @param reservoirName
     * @param problemType
     * @param problemTitle
     * @param problemCofirmType 问题状态:"problemCofirmType":[{"name":"严重问题","value":"2"},{"name":"一般问题","value":"1"},{"name":"不是问题","value":"0"}],
     * @param problemStatus "problemStatus":{"0":"待确认","1":"已确认","2":"已审核","3":"业主已审核","4":"待反馈","5":"已完结"},
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/appProblemInfo/getProblemExamineList")
    Observable<ProblemCallListBean> getProblemExamineList(@Header("Authorization") String token,
                                                       @Query("reservoirName") String reservoirName,
                                                       @Query("problemType") String problemType,
                                                       @Query("problemTitle") String problemTitle,
                                                       @Query("problemCofirmType") String problemCofirmType,
                                                       @Query("problemStatus") String problemStatus,
                                                       @Query("startDate") String startDate,
                                                       @Query("endDate") String endDate,
                                                       @Query("currentPage") String currentPage,
                                                       @Query("pageSize") String pageSize);

    /**
     * 待确认问题中意见反馈
     * @param token
     * @param problemId
     * @param excuteDes
     * @return
     */
    @FormUrlEncoded
    @POST("app/appProblemInfo/feedback")
    Observable<BaseResponse> feedback(@Header("Authorization") String token,
                                             @Field("problemId") String problemId,
                                             @Field("excuteDes") String excuteDes);

    /**
     * 提交确认问题操作
     * @param token
     * @param problemId
     * @param planType
     * @param problemConfirmType
     * @return
     */
    @FormUrlEncoded
    @POST("app/appProblemInfo/confirmProblem")
    Observable<BaseResponse> confirmProblem(@Header("Authorization") String token,
                                             @Field("problemId") String problemId,
                                             @Field("planType") String planType,
                                             @Field("problemConfirmType") String problemConfirmType);

    /**
     * 提交审核问题操作
     * @param token
     * @param problemId
     * @param executeType 1通过，0拒绝
     * @param executeDes 审核意见
     * @param isAnimal 是否通知业主 1-否 0-是
     * @return
     */
    @FormUrlEncoded
    @POST("app/appProblemInfo/reviewProblem")
    Observable<BaseResponse> reviewProblem(@Header("Authorization") String token,
                                            @Field("problemId") String problemId,
                                            @Field("executeType") String executeType,
                                            @Field("executeDes") String executeDes,
                                            @Field("isAnimal") String isAnimal);
}
