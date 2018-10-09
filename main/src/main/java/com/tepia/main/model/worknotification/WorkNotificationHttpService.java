package com.tepia.main.model.worknotification;

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
 * @author :      zhang xinhua
 * Date            :       2018-09-30
 * Time            :       9:53
 * Version         :       1.0
 * 功能描述        :
 **/
interface WorkNotificationHttpService {
    /**
     * 查询工作通知列表
     *
     * @param token
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/workNotice/getPageList")
    Observable<WorkNoticeListResponse> getWorkNoticeLeeaderList(@Header("Authorization") String token,
                                                                @Query("currentPage") String currentPage,
                                                                @Query("pageSize") String pageSize);

    /**
     * @param token
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/appWorkNoticeFeedback/findPageWorkNoticeFeedBack")
    Observable<FeedBackWorkNoticeListResponse> getWorkNoticeWorkerList(@Header("Authorization") String token,
                                                                       @Query("currentPage") String currentPage,
                                                                       @Query("pageSize") String pageSize);

    /**
     * @param token
     * @param noticeId
     * @return
     */
    @GET("app/workNotice/getByNoticeId")
    Observable<WorkNoticeDetailResponse> getWorkNoticeDetail(@Header("Authorization") String token,
                                                             @Query("noticeId") String noticeId);

    @GET("app/appWorkNoticeFeedback/findWorkNoticeFeedBackById")
    Observable<WorkNoticeDetailResponse> getWorkNoticeDetailWroker(@Header("Authorization") String token,
                                                                   @Query("noticeFeedbackId") String noticeFeedbackId,
                                                                   @Query("noticeId") String noticeId);

    @FormUrlEncoded
    @POST("app/appWorkNoticeFeedback/feedBackWorkNotice")
    Observable<BaseResponse> feedBackWorkNotice(@Header("Authorization") String token,
                                                @Field("id") String noticeFeedbackId,
                                                @Field("feedBackContent") String feedBackContent);

    @Multipart
    @POST("app/workNotice/addWorkNotice")
    Observable<BaseResponse> addWorkNotice(@Header("Authorization") String token,
                                           @PartMap Map<String, RequestBody> params,
                                           @Part List<MultipartBody.Part> beforePathList,
                                           @Part List<MultipartBody.Part> imagesPathList);

    @GET("app/appWorkNoticeFeedback/findNotFeedBackCount")
    Observable<NotFeedBackCountResponse> findNotFeedBackCount(@Header("Authorization") String token);
}
