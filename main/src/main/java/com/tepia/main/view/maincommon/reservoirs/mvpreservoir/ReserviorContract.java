package com.tepia.main.view.maincommon.reservoirs.mvpreservoir;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.view.maincommon.reservoirs.detail.WaterLevelActivity;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-21
 * Time            :       下午6:01
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :
 **/
public class ReserviorContract {
    public interface View<T> extends BaseView {
        void success(T data);

        void failure(String msg);

    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 水库配套设配
         * @param reservoirId
         */
        void getDeviceByReservoir(String reservoirId);

        /**
         * 水库安全运行报告
         * @param reservoirId
         */
        void getSafetyReportByReservoir(String reservoirId);

        /**
         * 查询防汛物资
         * @param reservoirId
         */
        void getMaterialByReservoir(String reservoirId);

        /**
         * 查询水库安全管理应急预案
         * @param reservoirId
         */
        void getEmergencyByReservoir(String reservoirId);

        /**
         * 查询调度运行方案
         * @param reservoirId
         */
        void getFloodControlByReservoir(String reservoirId);

        /**
         * 查询水库简介
         * @param reservoirId
         */
        void getBaseInfo(String reservoirId);

        /**
         * 查询水库库容曲线
         * @param reservoirId
         */
        void getStorageCurveByReservoir(String reservoirId);

        /**
         * 根据业务主键查询文件
         * @param bizKey
         */
        void getFileByBizKey(String bizKey);

        /**
         * 视频查询
         * @param reservoirId
         */
        void getReservoirVideo(String reservoirId);

        /**
         * 水库月汛限水位-分页查询
         * @param reservoirId
         * @param currentPage
         * @param pageSize
         * @param isshowloading
         */
        void getReservoirFloodSeason(String reservoirId,String currentPage, String pageSize,boolean isshowloading);

        /**
         * 水库月汛限水位-修改
         * @param id
         * @param floodLevel
         */
        void updateFloodSeason(String id, String floodLevel, String reservoirId,WaterLevelActivity waterLevelActivity);

        void addReservoirFloodSeason(String reservoirId,String floodYearMonth,String floodLevel,WaterLevelActivity waterLevelActivity);



    }
}
