package com.tepia.main.model.train;

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
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-27
 * Time    :       15:23
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public interface TrainService {

    /**
     * 查询培训记录列表
     * @param token
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/userTrain/getList")
    Observable<TrainListResponse> getTrainList(@Header("Authorization") String token,
                                               @Query("currentPage") String currentPage,
                                               @Query("pageSize") String pageSize);

    /**
     * 查询培训详情
     * @param token
     * @param id
     * @return
     */
    @GET("app/userTrain/getOneById")
    Observable<TrainDetailResponse> getTrainDetail(@Header("Authorization") String token,
                                                   @Query("id") String id
    );

    /**
     * 新增培训
     * @param token
     * @param parts
     * @param pathList
     * @return
     */
    @Multipart
    @POST("app/userTrain/addUserTrain")
    Observable<BaseResponse> addUserTrain(@Header("Authorization") String token,
                                          @PartMap Map<String, RequestBody> parts,
                                          @Part List<MultipartBody.Part> pathList,
                                          @Part List<MultipartBody.Part> photoList
    );
}
