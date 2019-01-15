package com.tepia.main.model.reserviros;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.map.VideoResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
 * 功能描述         :
 **/
public interface ReserviorsService {
    /**
     * 水库到访日志列表
     * @param token
     * @param reservoirId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/appReservoirVisit/getPageList")
    Observable<VisitLogBean> getPageList(@Header("Authorization") String token,
                                              @Query("reservoirId") String reservoirId,
                                              @Query("currentPage") String currentPage,
                                              @Query("pageSize") String pageSize

    );

    /**
     * 水库月汛限水位-分页查询
     * @param token
     * @param reservoirId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/bizReservoirFloodSeasonLevel/pageReservoirFloodSeason")
    Observable<FloodSeasonBean> getReservoirFloodSeason(@Header("Authorization") String token,
                                                        @Query("reservoirId") String reservoirId,
                                                        @Query("currentPage") String currentPage,
                                                        @Query("pageSize") String pageSize

    );

    @GET("app/bizReservoirFloodSeasonLevel/findReservoirCurrentFloodSeason")
    Observable<CurrentFloodSeasonBean> findReservoirCurrentFloodSeason(@Header("Authorization") String token,
                                                        @Query("reservoirId") String reservoirId


    );

    /**
     * 水库月汛限水位-修改
     * @param token
     * @param id
     * @param floodLevel
     * @return
     */
    @POST("app/bizReservoirFloodSeasonLevel/updateReservoirFloodSeason")
    Observable<BaseResponse> updateFloodSeason(@Header("Authorization") String token,
                                    @Query("id") String id,
                                    @Query("floodLevel") String floodLevel
                                    );


    /**
     * 水库月汛限水位-新增
     * @param token
     * @param reservoirId
     * @param floodYearMonth
     * @param floodLevel
     * @return
     */
    @POST("app/bizReservoirFloodSeasonLevel/addReservoirFloodSeason")
    Observable<BaseResponse> addReservoirFloodSeason(@Header("Authorization") String token,
                                               @Query("reservoirId") String reservoirId,
                                               @Query("floodYearMonth") String floodYearMonth,
                                                     @Query("floodLevel") String floodLevel
    );

    /**
     * 水库到访日志详情
     * @param token
     * @param id
     * @return
     */
    @GET("app/appReservoirVisit/detail")
    Observable<VisitLogDetailBean> detail(@Header("Authorization") String token,
                                         @Query("id") String id

    );

    /**
     * 新增到访日志
     * @param token
     * @param parts
     * @param pathList
     * @return
     */
    @Multipart
    @POST("app/appReservoirVisit/insert")
    Observable<BaseResponse> insert(@Header("Authorization") String token,
                                           @PartMap Map<String, RequestBody> parts,
                                           @Part List<MultipartBody.Part> pathList
    );

     /**
     * 获取配套设施
     * @param token
     * @param reservoirId
     * @return
     */
    @GET("app/reservoir/getDeviceByReservoir")
    Observable<SupportingBean> getDeviceByReservoir(@Header("Authorization") String token,
                                           @Query("reservoirId") String reservoirId

    );


    /**
     * 水库安全运行报告
     * @param token
     * @param reservoirId
     * @return
     */
    @GET("app/reservoir/getSafetyReportByReservoir")
    Observable<SafeRunningBean> getSafetyReportByReservoir(@Header("Authorization") String token,
                                                    @Query("reservoirId") String reservoirId

    );

    /**
     * 查询防汛物资
     * @param token
     * @param reservoirId
     * @return
     */
    @GET("app/reservoir/getMaterialByReservoir")
    Observable<FloodBean> getMaterialByReservoir(@Header("Authorization") String token,
                                                           @Query("reservoirId") String reservoirId

    );

    /**
     * 根据防汛物资ID查询防汛物资
     * @param token
     * @param materialId
     * @return
     */
    @GET("app/reservoir/getMaterialByMaterialId")
    Observable<FloodBeanDetailBean> getMaterialByMaterialId(@Header("Authorization") String token,
                                                 @Query("materialId") String materialId

    );

    /**
     * 查询水库安全管理应急预案
     * @param token
     * @param reservoirId
     * @return
     */
    @GET("app/reservoir/getEmergencyByReservoir")
    Observable<OperationPlanBean> getEmergencyByReservoir(@Header("Authorization") String token,
                                                 @Query("reservoirId") String reservoirId

    );

    /**
     * 查询调度运行方案
     * @param token
     * @param reservoirId
     * @return
     */
    @GET("app/reservoir/getFloodControlByReservoir")
    Observable<OperationPlanBean> getFloodControlByReservoir(@Header("Authorization") String token,
                                                            @Query("reservoirId") String reservoirId

    );

    /**
     * 查询水库简介
     * @param token
     * @param reservoirId
     * @return
     */
    @GET("app/reservoir/getBaseInfo")
    Observable<IntroduceOfReservoirsBean> getBaseInfo(@Header("Authorization") String token,
                                          @Query("reservoirId") String reservoirId

    );

    /**
     * 查询视频
     * @param token
     * @param reservoirId
     * @return
     */
    @GET("app/reservoir/getReservoirVideo")
    Observable<VideoResponse> getReservoirVideo(@Header("Authorization") String token,
                                                @Query("reservoirId") String reservoirId

    );

    /**
     * 查询水库库容曲线
     * @param token
     * @param reservoirId
     * @return
     */
    @GET("app/reservoir/getStorageCurveByReservoir")
    Observable<CapacityBean> getStorageCurveByReservoir(@Header("Authorization") String token,
                                                       @Query("reservoirId") String reservoirId

    );


    /**
     * 查询文件
     * @param token
     * @param bizKey
     * @return
     */
    @GET("app/fileManage/getFileByBizKey")
    Observable<BizkeyBean> getFileByBizKey(@Header("Authorization") String token,
                                                        @Query("bizKey") String bizKey

    );
}
