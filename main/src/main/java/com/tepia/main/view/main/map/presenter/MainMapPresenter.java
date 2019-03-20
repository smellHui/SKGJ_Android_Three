package com.tepia.main.view.main.map.presenter;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.main.model.map.MainMapManager;
import com.tepia.main.model.map.MapCommonResponse;
import com.tepia.main.model.map.PictureResponse;
import com.tepia.main.model.map.RainfallResponse;
import com.tepia.main.model.map.ReservoirListResponse;
import com.tepia.main.model.map.ReservoirResponse;
import com.tepia.main.model.map.StRiverResponse;
import com.tepia.main.model.map.VideoResponse;
import com.tepia.main.model.map.WaterLevelResponse;
import com.tepia.main.model.map.WaterQualityResponse;
import com.tepia.main.view.main.map.adapter.near.NearReservoirResponse;

/**
 * 主页地图Presenter
 * @author 44822
 */
public class MainMapPresenter extends BasePresenterImpl<MainMapContract.View> implements MainMapContract.Presenter{

    @Override
    public void findAllReservoirByRegion(String reservoirName) {
        MainMapManager.getInstance().findAllReservoirByRegion(reservoirName).subscribe(new LoadingSubject<ReservoirResponse>(true,"正在加载中...") {
            @Override
            protected void _onNext(ReservoirResponse reservoirResponse) {
                if (reservoirResponse.getCode()==0 && mView != null){
                    mView.success(reservoirResponse);
                }else {
                    if (reservoirResponse.getMsg()!=null && reservoirResponse.getMsg().length()>0){
                        mView.failure(reservoirResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message + "");
                }
            }
        });
    }

    @Override
    public void findAllStRiver(String stnm,String startDate,String endDate) {
        MainMapManager.getInstance().findAllStRiver(stnm,startDate,endDate).subscribe(new LoadingSubject<StRiverResponse>(true,"正在加载中...") {
            @Override
            protected void _onNext(StRiverResponse stRiverResponse) {
                if (stRiverResponse.getCode()==0 && mView != null){
                    mView.success(stRiverResponse);
                }else {
                    if (stRiverResponse.getMsg()!=null && stRiverResponse.getMsg().length()>0){
                        mView.failure(stRiverResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message + "");
                }
            }
        });
    }

    @Override
    public void findAllStWqB(String stnm) {
        MainMapManager.getInstance().findAllStWqB(stnm).subscribe(new LoadingSubject<WaterQualityResponse>(true,"正在加载中...") {
            @Override
            protected void _onNext(WaterQualityResponse waterQualityResponse) {
                if (waterQualityResponse.getCode()==0 && mView != null){
                    mView.success(waterQualityResponse);
                }else {
                    if (waterQualityResponse.getMsg()!=null && waterQualityResponse.getMsg().length()>0){
                        mView.failure(waterQualityResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message + "");
                }
            }
        });
    }

    @Override
    public void findAllRainfall(String stnm,String startDate, String endDate) {
        MainMapManager.getInstance().findAllRainfall(stnm,startDate,endDate).subscribe(new LoadingSubject<RainfallResponse>(true,"正在加载中...") {
            @Override
            protected void _onNext(RainfallResponse rainfallResponse) {
                if (rainfallResponse.getCode()==0 && mView != null){
                    mView.success(rainfallResponse);
                }else {
                    if (rainfallResponse.getMsg()!=null && rainfallResponse.getMsg().length()>0){
                        mView.failure(rainfallResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message + "");
                }
            }
        });
    }

    @Override
    public void findAllWaterLevel(String stnm,String startTime, String endTime) {
        MainMapManager.getInstance().findAllWaterLevel(stnm,startTime,endTime).subscribe(new LoadingSubject<WaterLevelResponse>(true,"正在加载中...") {
            @Override
            protected void _onNext(WaterLevelResponse waterLevelResponse) {
                if (waterLevelResponse.getCode()==0 && mView != null){
                    mView.success(waterLevelResponse);
                }else {
                    if (waterLevelResponse.getMsg()!=null && waterLevelResponse.getMsg().length()>0){
                        mView.failure(waterLevelResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message + "");
                }
            }
        });
    }


    /**
     * 待删除接口
     * @param stnm
     * @param reservoir
     * @param areaCode
     */
    @Override
    public void findAllStPicture(String stnm, String reservoir, String areaCode) {
        MainMapManager.getInstance().findAllStPicture(stnm,reservoir,areaCode).subscribe(new LoadingSubject<PictureResponse>(true,"正在加载中...") {
            @Override
            protected void _onNext(PictureResponse pictureResponse) {
                if (pictureResponse.getCode()==0 && mView != null){
                    mView.success(pictureResponse);
                }else {
                    if (pictureResponse.getMsg()!=null && pictureResponse.getMsg().length()>0){
                        mView.failure(pictureResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message + "");
                }
            }
        });
    }

    @Override
    public void findAllVsVideo(String vsnm) {
        MainMapManager.getInstance().findAllVsVideo(vsnm).subscribe(new LoadingSubject<VideoResponse>(true,"正在加载中...") {
            @Override
            protected void _onNext(VideoResponse videoResponse) {
                if (videoResponse.getCode()==0 && mView != null){
                    mView.success(videoResponse);
                }else {
                    if (videoResponse.getMsg()!=null && videoResponse.getMsg().length()>0){
                        mView.failure(videoResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message + "");
                }
            }
        });
    }

    @Override
    public void getStStbprpBByType(String stnm, String type, String areaCode) {
        MainMapManager.getInstance().getStStbprpBByType(stnm,type,areaCode).subscribe(new LoadingSubject<MapCommonResponse>(true,"正在加载中...") {
            @Override
            protected void _onNext(MapCommonResponse mapCommonResponse) {
                if (mapCommonResponse.getCode()==0 && mView != null){
                    mView.success(mapCommonResponse);
                }else {
                    if (mapCommonResponse.getMsg()!=null && mapCommonResponse.getMsg().length()>0){
                        mView.failure(mapCommonResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message + "");
                }
            }
        });
    }

    @Override
    public void findAppAllReservoir(String reservoirName, String areaCode,boolean showloading) {
        MainMapManager.getInstance().findAppAllReservoir(reservoirName,areaCode).subscribe(new LoadingSubject<ReservoirListResponse>(showloading,"正在加载中...") {
            @Override
            protected void _onNext(ReservoirListResponse reservoirListResponse) {
                if (reservoirListResponse.getCode()==0 && mView != null){
                    mView.success(reservoirListResponse);
                }else {
                    if (reservoirListResponse.getMsg()!=null && reservoirListResponse.getMsg().length()>0){
                        mView.failure(reservoirListResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message + "");
                }
            }
        });
    }

    @Override
    public void getNearbyReservoir(String longitude, String latitude) {
        MainMapManager.getInstance().getNearbyReservoir(longitude,latitude).subscribe(new LoadingSubject<NearReservoirResponse>(true,"正在搜索中...") {
            @Override
            protected void _onNext(NearReservoirResponse nearReservoirResponse) {
                if (nearReservoirResponse.getCode()==0 && mView != null){
                    mView.success(nearReservoirResponse);
                }else {
                    if (nearReservoirResponse.getMsg()!=null && nearReservoirResponse.getMsg().length()>0){
                        mView.failure(nearReservoirResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message + "");
                }
            }
        });
    }
}
