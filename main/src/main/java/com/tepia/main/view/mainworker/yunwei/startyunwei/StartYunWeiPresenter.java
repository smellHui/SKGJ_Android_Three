package com.tepia.main.view.mainworker.yunwei.startyunwei;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.UnfinishedNumResponse;
import com.tepia.main.model.task.WorkOrderNumResponse;
import com.tepia.main.model.task.response.TaskDetailResponse;
import com.tepia.main.model.task.response.TaskItemListResponse;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        开始运维页面
 **/

public class StartYunWeiPresenter extends BasePresenterImpl<StartYunWeiContract.View> implements StartYunWeiContract.Presenter {

    /**
     * 开始执行工单
     * @param reservoirId
     * @param reservoirName
     * @param operationType
     * @param superviseIds
     */
    public void newStartExecute(String reservoirId, String reservoirName, String operationType, String superviseIds) {
        TaskManager.getInstance().newStartExecute(reservoirId, reservoirName, operationType,superviseIds).safeSubscribe(new LoadingSubject<TaskDetailResponse>() {
            @Override
            protected void _onNext(TaskDetailResponse taskDetailResponse) {
                mView.newStartExecuteSuccess(taskDetailResponse.getData());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void getItemListByReservoirId(String reservoirId, String selectedYunWeiType) {

        TaskManager.getInstance().getItemListByReservoirId(reservoirId, selectedYunWeiType).safeSubscribe(new LoadingSubject<TaskItemListResponse>() {
            @Override
            protected void _onNext(TaskItemListResponse taskItemListResponse) {
                mView.getItemListByReservoirIdSuccess(taskItemListResponse.getData());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void getWorkOrderNumByReservoirId(String reservoirId, String operationType) {

        TaskManager.getInstance().getWorkOrderNumByReservoirId(reservoirId, operationType).safeSubscribe(new LoadingSubject<WorkOrderNumResponse>() {
            @Override
            protected void _onNext(WorkOrderNumResponse workOrderNumResponse) {
                mView.getWorkOrderNumByReservoirIdSuccess(workOrderNumResponse.getData());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void getUnfinishedNum(String reservoirId, String operationType) {
        TaskManager.getInstance().getUnfinishedNum(reservoirId, operationType).safeSubscribe(new LoadingSubject<UnfinishedNumResponse>(true, ResUtils.getString(R.string.data_loading)) {
            @Override
            protected void _onNext(UnfinishedNumResponse workOrderNumResponse) {
                mView.getUnfinishedNumSuccess(workOrderNumResponse.getData());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
