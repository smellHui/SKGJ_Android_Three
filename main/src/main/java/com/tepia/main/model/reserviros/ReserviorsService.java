package com.tepia.main.model.reserviros;

import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.detai.DetailManager;
import com.tepia.main.model.question.AllproblemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
     * 获取配套设施
     * @param token
     * @param reservoirId
     * @return
     */
    @GET("app/reservoir/getDeviceByReservoir")
    Observable<SupportingBean> getDeviceByReservoir(@Header("Authorization") String token,
                                          @Query("reservoirId") String reservoirId

    );
}
