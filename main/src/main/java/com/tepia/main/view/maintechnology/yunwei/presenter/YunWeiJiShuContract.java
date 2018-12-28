package com.tepia.main.view.maintechnology.yunwei.presenter;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;
import com.tepia.main.view.main.map.presenter.MainMapContract;

import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-25
 * Time    :       11:53
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class YunWeiJiShuContract {
    public interface View<T> extends BaseView {
        void success(T data);

        void failure(String msg);
    }

    interface Presenter extends BasePresenter<YunWeiJiShuContract.View> {
        void getNoProcessWorkOrderList(String reservoirId, String operationType, String startDate, String endDate, String currentPage, String pageSize,String executeStatus, boolean isShowLoading);

        void getWorkOrderNumByJs(String reservoirId, String operationType, String startDate, String endDate);

        void getProblemList(String reservoirId, String workOrderId, String startDate, String endDate, String currentPage, String pageSize, String problemStatus, boolean isShowLoading);

        void getDetailedProblemInfoByProblemId(String problemId);

        void listStPpthRByReservoir(String reservoirId, String startDate, String endDate, String currentPage, String pageSize, boolean isshowloading);

        void getAdminWorkOrderList(String reservoirId, String operationType, String queryDate, String currentPage, String pageSize, boolean isShowLoading);

        void getAdminProblemList(String reservoirId, String queryDate, String currentPage, String pageSize, boolean isShowLoading);

        void listStRsvrRRByReservoir(String reservoirId, String startDate, String endDate, String currentPage, String pageSize, boolean isshowloading);

        void getTownWorkOrderList(String operationType,String areaCode,String queryDate,String currentPage, String pageSize,boolean isshowloading);

        void getTownProblemList(String areaCode, String queryDate, String currentPage, String pageSize,boolean isshowloading);

        void getWorkOrderListByAreaCode(String operationType, String areaCode,String queryDate,boolean isShowLoading);

        void getProblemListByAddvcd(String areaCode, String queryDate,boolean isShowLoading);
    }
}
