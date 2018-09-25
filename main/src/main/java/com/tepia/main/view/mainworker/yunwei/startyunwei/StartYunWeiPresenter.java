package com.tepia.main.view.mainworker.yunwei.startyunwei;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.WorkOrderNumResponse;
import com.tepia.main.model.task.response.TaskDetailResponse;
import com.tepia.main.model.task.response.TaskItemListResponse;
import com.tepia.main.model.user.UserManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
     */
    public void newStartExecute(String reservoirId, String reservoirName, String operationType) {
        TaskManager.getInstance().newStartExecute(reservoirId, reservoirName, operationType).safeSubscribe(new LoadingSubject<TaskDetailResponse>() {
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
}
