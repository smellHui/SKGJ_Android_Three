package com.tepia.main.view.main.map.presenter;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;

/**
  * Created by      Android studio
  * @author :wwj (from Center Of Wuhan)
  * Date    :2018/11/26
  * Version :1.0
  * 功能描述 :
 **/

public class MainMapContract {
    public interface View<T> extends BaseView {
        void success(T data);
        void failure(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void findAllReservoirByRegion(String reservoirName);
        void findAllStRiver(String stnm, String startDate, String endDate);
        void findAllStWqB(String stnm);
        void findAllRainfall(String stnm, String startDate, String endDate);
        void findAllWaterLevel(String stnm, String startTime, String endTime);
        void findAllStPicture(String stnm, String reservoir, String areaCode);
        void findAllVsVideo(String vsnm);
        void getStStbprpBByType(String stnm, String type, String areaCode);
        void findAppAllReservoir(String reservoirName, String areaCode,boolean showloading);
        void getNearbyReservoir(String longitude,String latitude);
    }

}
