package com.tepia.main.view.main.jihua.presenter;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.view.main.map.presenter.MainMapContract;

/**
 *
 * @author 44822
 */
public class InspectionPlanContract {

    public interface View<T> extends BaseView {
        void success(T data);
        void failure(String msg);
    }

    interface Presenter extends BasePresenter<InspectionPlanContract.View> {
       void  getPlanInfoList(String reservoirName,String planType,String operationType,String isGenerate,String startDate,String endDate,String currentPage,String pageSize,boolean isShowLoading);
       void deleteByIds(String planIds);
       void saveBizPlanInfo(String planType,String operationType,String reservoirId,String planName,String planTimes,String remarks,String problemId);
       void updateBizPlanInfo(String planId,String planType,String operationType,String reservoirId,String planName,String planTimes,String remarks);
       void createWorkOrder(String planIds);
    }
}
