package com.tepia.main.model.detai;

import android.text.TextUtils;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.map.ReservoirResponse;
import com.tepia.main.model.question.ReservoirBean;
import com.tepia.main.model.user.UserManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 各类详情页面请求
 *
 * @author ly
 * @date 2018/7/24
 */
public class DetailManager {
    private DetailService mRetrofitService;
    private static final DetailManager ourInstance = new DetailManager();

    public static DetailManager getInstance() {
        return ourInstance;
    }

    private DetailManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL+APPCostant.API_SERVER_MONITOR_AREA).create(DetailService.class);
    }



    /**
     * 获取流量站详情
     * @param stcd
     * @param selectType
     * @param startDate
     * @param endDate
     * @return
     */
    public Observable<StRiverRBean> findStRiverRByStcd(String stcd,String selectType,String startDate,String endDate){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findStRiverRByStcd(token,stcd,selectType,startDate,endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 查询水质站详情
     * @param stcd
     * @param startDate
     * @param endDate
     * @return
     */
    public Observable<WaterPhBean> findListStWqRByDate(String stcd,String startDate,String endDate){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findListStWqRByDate(token,stcd,startDate,endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    /**
     * 获取雨量站详情
     * @param stcd
     * @param selectType
     * @param startDate
     * @param endDate
     * @return
     */
    public Observable<RainfullBean> getStPpthRChartDataInfo(String stcd,String selectType,String startDate,String endDate){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getStPpthRChartDataInfo(token,stcd,selectType,startDate,endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 雨量站获取1,3,6小时降雨量和
     * @param stcd
     * @return
     */
    public Observable<OneThreeSixBean> getOneThreeSixTimePpth(String stcd){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getOneThreeSixTimePpth(token,stcd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 查询水位站详情
     * @param stcd
     * @param startDate
     * @param endDate
     * @return
     */
    public Observable<WaterlevelBean> listEveryDayStRsvrRByStcd(String stcd,String startDate,String endDate){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.listEveryDayStRsvrRByStcd(token,stcd,startDate,endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * 查询某个图像站所有分页图片
     * @param stcd
     * @param startTime
     * @param endTime
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Observable<ImageBean> findPictureRByStcd(String stcd,String pageSize, String currentPage, String startTime, String endTime){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findPictureRByStcd(token,stcd,startTime,endTime,currentPage,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * 查询水库详情
     * @param stcd
     * @return
     */
    public Observable<ReservoirDetailBean> findAppReservoirById(String stcd){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findAppReservoirById(token,stcd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 依据stcd查询水质站点详情
     * @param stcd
     * @return
     */
    public Observable<WaterQualityDetailBean> getStStbprpBAndNewWQByType(String stcd){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getStStbprpBAndNewWQByType(token,stcd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 依据stcd查询水位站点详情
     * @param stcd
     * @return
     */
    public Observable<WaterlevelBean> getStStbprpBAndNewRRByType(String stcd){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getStStbprpBAndNewRRByType(token,stcd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 依据stcd查询雨量站点详情
     * @param stcd
     * @return
     */
    public Observable<RainfullBean> getStStbprpBAndNewPPByType(String stcd){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getStStbprpBAndNewPPByType(token,stcd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //
    /**
     * 依据stcd查询流量站站点详情
     * @param stcd
     * @return
     */
    public Observable<LiuliangDetailBean> getStStbprpBAndNewZQByType(String stcd){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getStStbprpBAndNewZQByType(token,stcd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 依据stcd查询图像站站点详情
     * @param stcd
     * @return
     */
    public Observable<ImageDetailBean> getStStbprpBAndNewIIByType(String stcd){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getStStbprpBAndNewIIByType(token,stcd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



}
