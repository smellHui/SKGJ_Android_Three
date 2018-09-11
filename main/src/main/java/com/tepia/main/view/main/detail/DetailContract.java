package com.tepia.main.view.main.detail;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.detai.OneThreeSixBean;
import com.tepia.main.model.detai.RainfullBean;

import java.util.ArrayList;

/**
 * 详情页面
 * by ly on 2018/5
 * @author liying
 */

public class DetailContract{


    public interface View<T> extends BaseView {
         void success(T data);
         void failure(String msg);
    }


    /**
     * 雨量
     */
    public interface RainfullView extends BaseView {
        void success(RainfullBean data);
        void successThree(OneThreeSixBean data);
        void failure(String msg);
        void failureThree(String msg);

    }

    interface  RainfullPresenter extends BasePresenter<RainfullView> {
        void getStPpthRChartDataInfo(String stcd,String selectType,String startDate,String endDate);
        void getOneThreeSixTimePpth(String stcd,boolean isshow);
    }

    /**
     * 流量
     */
    public interface LiuliangfullView extends BaseView {
        void success(RainfullBean data);
        void successThree(OneThreeSixBean data);
        void failure(String msg);
        void failureThree(String msg);

    }

    interface  LiuliangPresenter extends BasePresenter<LiuliangfullView> {
        void getStPpthRChartDataInfo(String stcd,String selectType,String startDate,String endDate);
        void getOneThreeSixTimePpth(String stcd,boolean isshow);
    }


    interface  Presenter extends BasePresenter<View> {
        void findStRiverRByStcd(String stcd,String selectType,String startDate,String endDate);
        void findListStWqRByDate(String stcd,String startDate,String endDate);
        void getStPpthRChartDataInfo(String stcd,String selectType,String startDate,String endDate);
        void getOneThreeSixTimePpth(String stcd,boolean isshow);
        void listEveryDayStRsvrRByStcd(String stcd,String startDate,String endDate);
        void findPictureRByStcd(String stcd,String pageSize,String currentPage,String startTime,String endTime,boolean isrefresh );
    }

}
