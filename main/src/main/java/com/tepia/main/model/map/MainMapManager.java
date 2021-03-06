package com.tepia.main.model.map;


import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.user.UserManager;
import com.tepia.main.view.main.map.adapter.near.NearReservoirResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
  * Created by      Android studio
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/11/26
  * Version :1.0
  * 功能描述 :
 **/
public class MainMapManager {
    private MainMapService mRetrofitService;
    private static final MainMapManager ourInstance = new MainMapManager();

    public static MainMapManager getInstance() {
        return ourInstance;
    }
    public  final String SERVER_URL = "http://weish.vipgz1.idcfengye.com/";
    private MainMapManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL+ APPCostant.API_SERVER_MONITOR_AREA).create(MainMapService.class);
    }

    public Observable<ReservoirResponse> findAllReservoirByRegion(String reservoirName) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findAllReservoirByRegion(token,reservoirName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<StRiverResponse> findAllStRiver(String stnm, String startDate, String endDate){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findAllStRiver(token,stnm,startDate,endDate).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<WaterQualityResponse> findAllStWqB(String stnm){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findAllStWqB(token,stnm).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<RainfallResponse> findAllRainfall(String stnm, String startDate, String endDate){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findAllRainfall(token,stnm,startDate,endDate).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<WaterLevelResponse> findAllWaterLevel(String stnm, String startTime, String endTime){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findAllWaterLevel(token,stnm,startTime,endTime).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<PictureResponse> findAllStPicture(String stnm, String reservoir, String areaCode){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findAllStPicture(token,stnm,reservoir,areaCode).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<VideoResponse> findAllVsVideo(String vsnm){
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findAllVsVideo(token,vsnm).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MapCommonResponse> getStStbprpBByType(String stnm, String type, String areaCode){
        String token = UserManager.getInstance().getToken();
//        MainMapService mainMapService = RetrofitManager.getRetrofit(SERVER_URL + APPCostant.API_SERVER_MONITOR_AREA).create(MainMapService.class);
        return mRetrofitService.getStStbprpBByType(token,stnm,type,areaCode).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ReservoirListResponse> findAppAllReservoir(String reservoirName,String areaCode){
        String token = UserManager.getInstance().getToken();
//        MainMapService mainMapService = RetrofitManager.getRetrofit(SERVER_URL + APPCostant.API_SERVER_MONITOR_AREA).create(MainMapService.class);
        return mRetrofitService.findAppAllReservoir(token,reservoirName,areaCode).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<NearReservoirResponse> getNearbyReservoir(String longitude, String latitude){
        String token = UserManager.getInstance().getToken();
        MainMapService mainMapService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL + APPCostant.API_SERVER_USER_ADMIN).create(MainMapService.class);
//        String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJib3pob3VhZG1pbiIsInVzZXJJZCI6IjQ3NGRkY2Y1ZGYxMTQ2ZDRiNjgzMTY3ZjQ4MmQ3YTdkIiwibmFtZSI6IuaSreW3nueuoeeQhuWRmCIsImxvZ2luU2NvcGUiOiIxIiwiZXhwIjoxNTQ1NDY0NjAyfQ.k7IcjzE2Jdsd-bBmdvJQko7wAVI1c4sqSgUJ7z_p6sDrSk5CT9rJcJ8rgkxM_5z43C4BjzuTgsEvTPVXw4BCkpkSj7jCdWkapHolhxn_Sv5nOghP9-CBZH3xXkc3TTKDVQt6oRtgf_N0Nm_l0TalBrS-zFPVDZ7eSgBY4Zr1VGU";
        return mainMapService.getNearbyReservoir(token,longitude,latitude).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
