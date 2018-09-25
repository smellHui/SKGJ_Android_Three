package com.tepia.main.view.maintechnology.yunwei.presenter;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.main.model.jishu.yunwei.OperationReportListResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;
import com.tepia.main.model.jishu.yunwei.WorkOrderNumResponse;
import com.tepia.main.model.map.MainMapManager;
import com.tepia.main.model.map.ReservoirResponse;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-25
 * Time    :       11:54
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class YunWeiJiShuPresenter extends BasePresenterImpl<YunWeiJiShuContract.View> implements YunWeiJiShuContract.Presenter{
    @Override
    public void getNoProcessWorkOrderList(String reservoirId, String operationType, String startDate, String endDate, String currentPage, String pageSize,boolean isShowLoading) {
        YunWeiJiShuManager.getInstance().getNoProcessWorkOrderList(reservoirId,operationType,startDate,endDate,currentPage,pageSize).subscribe(new LoadingSubject<WorkOrderListResponse>(isShowLoading,"正在加载中...") {
            @Override
            protected void _onNext(WorkOrderListResponse workOrderListResponse) {
                if (workOrderListResponse.getCode()==0){
                    mView.success(workOrderListResponse);
                }else {
                    if (workOrderListResponse.getMsg()!=null && workOrderListResponse.getMsg().length()>0){
                        mView.failure(workOrderListResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                mView.failure(message);
            }
        });
    }

    @Override
    public void getWorkOrderNumByJs(String reservoirId, String operationType, String startDate) {
        YunWeiJiShuManager.getInstance().getWorkOrderNumByJs(reservoirId,operationType,startDate).subscribe(new LoadingSubject<WorkOrderNumResponse>(false,"正在加载中...") {
            @Override
            protected void _onNext(WorkOrderNumResponse workOrderNumResponse) {
                if (workOrderNumResponse.getCode()==0){
                    mView.success(workOrderNumResponse);
                }else {
                    if (workOrderNumResponse.getMsg()!=null && workOrderNumResponse.getMsg().length()>0){
                        mView.failure(workOrderNumResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                mView.failure(message);
            }
        });
    }

    @Override
    public void getProblemList(String reservoirId, String workOrderId, String startDate, String endDate, String currentPage, String pageSize, String problemStatus,boolean isShowLoading) {
        YunWeiJiShuManager.getInstance().getProblemList(reservoirId,workOrderId,startDate,endDate,currentPage,pageSize,problemStatus).subscribe(new LoadingSubject<OperationReportListResponse>(isShowLoading,"正在加载中...") {
            @Override
            protected void _onNext(OperationReportListResponse operationReportListResponse) {
                if (operationReportListResponse.getCode()==0){
                    mView.success(operationReportListResponse);
                }else {
                    if (operationReportListResponse.getMsg()!=null && operationReportListResponse.getMsg().length()>0){
                        mView.failure(operationReportListResponse.getMsg());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                mView.failure(message);
            }
        });
    }
}
