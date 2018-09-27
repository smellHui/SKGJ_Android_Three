package com.tepia.main.view.mainworker.yunwei.yunweilist;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.response.TaskListResponse;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class YunWeiListPresenter extends BasePresenterImpl<YunWeiListContract.View> implements YunWeiListContract.Presenter {
    int currentPage = 1;
    String pageSize = "30";
    public boolean isCanLoadMore = true;

    public void getPatrolWorkOrderList(String reservoirId, String operationType) {
        String startDate = null;
        String endDate = null;
        currentPage = 1;
        boolean isShow = true;
        String msg = null;
        TaskManager.getInstance().getPatrolWorkOrderList(operationType, reservoirId, startDate, endDate, currentPage + "", pageSize + "").safeSubscribe(new LoadingSubject<TaskListResponse>(isShow, msg) {
            @Override
            protected void _onNext(TaskListResponse taskListResponse) {
                mView.getPatrolWorkOrderListSuccess(taskListResponse.getData().getList());
                if (taskListResponse.getData().getPageNum() == taskListResponse.getData().getPages()) {
                    isCanLoadMore = false;
                } else {
                    isCanLoadMore = true;
                }
                currentPage = taskListResponse.getData().getPageNum();
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void getPatrolWorkOrderListMore(String reservoirId, String operationType) {
        String startDate = null;
        String endDate = null;

        boolean isShow = true;
        String msg = null;
        if (!isCanLoadMore) {
            return;
        }
        TaskManager.getInstance().getPatrolWorkOrderList(operationType, reservoirId, startDate, endDate, currentPage + 1 + "", pageSize).safeSubscribe(new LoadingSubject<TaskListResponse>(isShow, msg) {
            @Override
            protected void _onNext(TaskListResponse taskListResponse) {
                currentPage = taskListResponse.getData().getPageNum();
                if (taskListResponse.getData().getPageNum() == taskListResponse.getData().getPages()) {
                    isCanLoadMore = false;
                } else {
                    isCanLoadMore = true;
                }
                mView.getPatrolWorkOrderListMoreSuccess(taskListResponse.getData());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
