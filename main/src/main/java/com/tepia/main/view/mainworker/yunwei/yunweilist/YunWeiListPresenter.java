package com.tepia.main.view.mainworker.yunwei.yunweilist;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.response.TaskListResponse;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YunWeiListPresenter extends BasePresenterImpl<YunWeiListContract.View> implements YunWeiListContract.Presenter{

    public void getPatrolWorkOrderList(String reservoirId, String operationType) {
        String startDate= null;
        String endDate= null;
        String currentPage= "1";
        String pageSize= "20";
        boolean isShow= true;
        String msg= null;
        TaskManager.getInstance().getPatrolWorkOrderList(operationType,reservoirId,startDate,endDate,currentPage,pageSize).safeSubscribe(new LoadingSubject<TaskListResponse>(isShow,msg) {
            @Override
            protected void _onNext(TaskListResponse taskListResponse) {
                mView.getPatrolWorkOrderListSuccess(taskListResponse.getData().getList());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
