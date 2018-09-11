package com.tepia.main.model.detai;

import com.tepia.base.http.BaseResponse;
import com.tepia.main.model.map.ReservoirResponse;
import com.tepia.main.model.question.ReservoirBean;

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
 * 详情页面
 * Created by ly on 2018/7/24.
 */
interface DetailService {


    /**
     * 获取流量站详情
     * @param token
     * @param stcd
     * @param selectType
     * @param startDate
     * @param endDate
     * @return
     */
    @GET("app/appStRiverR/findStRiverRByStcd")
    Observable<StRiverRBean> findStRiverRByStcd(@Header("Authorization") String token,
                                                @Query("stcd") String stcd,
                                                @Query("selectType") String selectType,
                                                @Query("startDate") String startDate,
                                                @Query("endDate") String endDate);


    /**
     * 查询水质站详情
     * @param token
     * @param stcd
     * @param startDate
     * @param endDate
     * @return
     */
    @GET("app/appStWqR/findListStWqRByDate")
    Observable<WaterPhBean> findListStWqRByDate(@Header("Authorization") String token,
                                                @Query("stcd") String stcd,
                                                @Query("startDate") String startDate,
                                                @Query("endDate") String endDate);



    /**
     * 获取雨量站详情
     * @param token
     * @param stcd
     * @param selectType
     * @param startDate
     * @param endDate
     * @return
     */
    @GET("app/appStPptnR/getStPpthRChartDataInfo")
    Observable<RainfullBean> getStPpthRChartDataInfo(@Header("Authorization") String token,
                                                @Query("stcd") String stcd,
                                                @Query("selectType") String selectType,
                                                @Query("startDate") String startDate,
                                                @Query("endDate") String endDate);

    /**
     * 雨量站获取1,3,6小时降雨量和
     * @param token
     * @param stcd
     * @return
     */
    @GET("app/appStPptnR/getOneThreeSixTimePpth")
    Observable<OneThreeSixBean> getOneThreeSixTimePpth(@Header("Authorization") String token,
                                                     @Query("stcd") String stcd);


    /**
     * 查询水位站详情
     * @param token
     * @param stcd
     * @param startDate
     * @param endDate
     * @return
     */
    @GET("app/appStRsvrR/listEveryDayStRsvrRByStcd")
    Observable<WaterlevelBean> listEveryDayStRsvrRByStcd(@Header("Authorization") String token,
                                                @Query("stcd") String stcd,
                                                @Query("startTime") String startDate,
                                                @Query("endTime") String endDate);

    /**
     * 查询某个图像站所有分页图片
     * @param token
     * @param stcd
     * @param startTime
     * @param endTime
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GET("app/appPictureR/findPictureRByStcd")
    Observable<ImageBean> findPictureRByStcd(@Header("Authorization") String token,
                                             @Query("stcd") String stcd,
                                             @Query("startTime") String startTime,
                                             @Query("endTime") String endTime,
                                             @Query("currentPage") String currentPage,
                                             @Query("pageSize") String pageSize);

    /**
     * 查询水库详情
     * @param token
     * @param stcd
     * @return
     */
    @GET("app/appReservoirBase/findAppReservoirById")
    Observable<ReservoirDetailBean> findAppReservoirById(@Header("Authorization") String token,
                                                       @Query("reservoirId") String stcd);

    /**
     * 依据stcd查询水质站点详情
     * @param token
     * @param stcd
     * @return
     */
    @GET("app/appStStbprpb/getStStbprpBAndNewWQByType")
    Observable<WaterQualityDetailBean> getStStbprpBAndNewWQByType(@Header("Authorization") String token,
                                                         @Query("stcd") String stcd);


    /**
     * 依据stcd查询水位站点详情
     * @param token
     * @param stcd
     * @return
     */
    @GET("app/appStStbprpb/getStStbprpBAndNewRRByType")
    Observable<WaterlevelBean> getStStbprpBAndNewRRByType(@Header("Authorization") String token,
                                                                  @Query("stcd") String stcd);

    /**
     * 依据stcd查询雨量站点详情
     * @param token
     * @param stcd
     * @return
     */
    @GET("app/appStStbprpb/getStStbprpBAndNewPPByType")
    Observable<RainfullBean> getStStbprpBAndNewPPByType(@Header("Authorization") String token,
                                                          @Query("stcd") String stcd);

    /**
     * 依据stcd查询流量站点详情
     * @param token
     * @param stcd
     * @return
     */
    @GET("app/appStStbprpb/getStStbprpBAndNewZQByType")
    Observable<LiuliangDetailBean> getStStbprpBAndNewZQByType(@Header("Authorization") String token,
                                                        @Query("stcd") String stcd
    );

    @GET("app/appStStbprpb/getStStbprpBAndNewIIByType")
    Observable<ImageDetailBean> getStStbprpBAndNewIIByType(@Header("Authorization") String token,
                                                              @Query("stcd") String stcd
    );

}
