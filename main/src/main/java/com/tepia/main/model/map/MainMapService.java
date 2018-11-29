package com.tepia.main.model.map;

import com.tepia.main.view.main.map.adapter.near.NearReservoirResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
  * Created by      Android studio
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/11/26
  * Version :1.0
  * 功能描述 : 主页地图
 **/
interface MainMapService {

    /**
     * 查找所有水库
     *
     * @param token
     * @return
     */
    @GET("app/appReservoirBase/findAllReservoirByRegion")
    Observable<ReservoirResponse> findAllReservoirByRegion(@Header("Authorization") String token, @Query("reservoirName") String reservoirName);

    /**
     * 查找所有流量站
     *
     * @param token
     * @param startDate
     * @param endDate
     * @return
     */
    @GET("app/appStRiverR/findAllStRiver")
    Observable<StRiverResponse> findAllStRiver(@Header("Authorization") String token,
                                               @Query("stnm") String stnm,
                                               @Query("startDate") String startDate,
                                               @Query("endDate") String endDate);

    /**
     * 查找所有水质站
     *
     * @param token
     * @return
     */
    @GET("app/appStWqR/findAllStWqB")
    Observable<WaterQualityResponse> findAllStWqB(@Header("Authorization") String token,
                                                  @Query("stnm") String stnm);

    /**
     * 查找所有雨量站
     *
     * @param token
     * @param startDate
     * @param endDate
     * @return
     */
    @GET("app/appStPptnR/listPagePpthR")
    Observable<RainfallResponse> findAllRainfall(@Header("Authorization") String token,
                                                 @Query("stnm") String stnm,
                                                 @Query("startDate") String startDate,
                                                 @Query("endDate") String endDate);

    /**
     * 查找所有水位站
     *
     * @param token
     * @param startTime
     * @param endTime
     * @return
     */
    @GET("app/appStRsvrR/listPageStRsvrR")
    Observable<WaterLevelResponse> findAllWaterLevel(@Header("Authorization") String token,
                                                     @Query("stnm") String stnm,
                                                     @Query("startTime") String startTime,
                                                     @Query("endTime") String endTime);

    /**
     * 查找所有图像站
     * 待删除接口
     * @param token
     * @param stnm
     * @param reservoir
     * @param areaCode
     * @return
     */
    @GET("app/appPictureR/findAllStPicture")
    Observable<PictureResponse> findAllStPicture(@Header("Authorization") String token,
                                                 @Query("stnm") String stnm,
                                                 @Query("reservoir") String reservoir,
                                                 @Query("areaCode") String areaCode);

    /**
     * 查找所有视频站
     *
     * @param token
     * @param vsnm
     * @return
     */
    @GET("app/appVsVideo/findAllVsVideo")
    Observable<VideoResponse> findAllVsVideo(@Header("Authorization") String token,
                                             @Query("vsnm") String vsnm);

    /**
     *  查询流量（ZQ）、水质（WQ）、雨量(PP)、水位(RR)、 图像(II)
     * @param token
     * @param stnm
     * @param type
     * @param areaCode
     * @return
     */
    @GET("app/appStStbprpb/getStStbprpBByType")
    Observable<MapCommonResponse> getStStbprpBByType(@Header("Authorization") String token,
                                                     @Query("stnm") String stnm,
                                                     @Query("type") String type,
                                                     @Query("areaCode") String areaCode);

    /**
     * APP 查询水库列表信息
     * @param token
     * @param reservoirName
     * @param areaCode
     * @return
     */
    @GET("app/appReservoirBase/findAppAllReservoir")
    Observable<ReservoirListResponse> findAppAllReservoir(@Header("Authorization") String token,
                                                          @Query("reservoirName") String reservoirName,
                                                          @Query("areaCode") String areaCode);

    /**
     * 附近的水库
     * @param token
     * @param longitude
     * @param latitude
     * @return
     */
    @GET("app/reservoirBase/getNearbyReservoir")
    Observable<NearReservoirResponse> getNearbyReservoir(@Header("Authorization") String token,
                                                         @Query("longitude") String longitude,
                                                         @Query("latitude") String latitude);

}
