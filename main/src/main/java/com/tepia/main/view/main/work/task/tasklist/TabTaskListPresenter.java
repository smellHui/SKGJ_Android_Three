package com.tepia.main.view.main.work.task.tasklist;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.response.TaskListResponse;
import com.tepia.main.model.task.TaskManager;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TabTaskListPresenter extends BasePresenterImpl<TabTaskListContract.View> implements TabTaskListContract.Presenter {
    int pageSize = 20;
    int pageNumber = 1;
    public boolean isCanLoadMore = true;
    private List<TaskBean> data = new ArrayList<>();
    private int type = Integer.MAX_VALUE;

    /**
     * @param executeStatus
     * @param operationType
     * @param isShow
     * @param msg
     */

    public void getTaskList(int executeStatus, String operationType, boolean isShow, String msg) {
        pageNumber = 1;
        TaskManager.getInstance().listPageWorks(null, executeStatus, operationType, null, null, pageNumber, pageSize)
                .subscribe(new LoadingSubject<TaskListResponse>(isShow, msg) {
                    @Override
                    protected void _onNext(TaskListResponse taskListResponse) {
                        if (taskListResponse.getCode() == 0) {
                            data = taskListResponse.getData().getList();
                            mView.getTaskListSuccess(taskListResponse.getData().getTotal(),taskListResponse.getData().getList());
                            isCanLoadMore = !taskListResponse.getData().isIsLastPage();
                        }else {
                            _onError(taskListResponse.getMsg());
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);
                    }
                });
    }


    /**
     * @param taskStatus
     * @param isShow
     * @param msg
     */

    public boolean getTaskListMore(int taskStatus, String woTypeCode, boolean isShow, String msg) {
        if (!isCanLoadMore) {
            return isCanLoadMore;
        }
        TaskManager.getInstance().listPageWorks(null,taskStatus, woTypeCode,null,null, pageNumber + 1, pageSize).subscribe(new LoadingSubject<TaskListResponse>(isShow, msg) {
            @Override
            protected void _onNext(TaskListResponse taskListResponse) {
//                if (taskListResponse.isSuccess()) {
//                    data.addAll(taskListResponse.getData());
//
//                    mView.getTaskListMoreSuccess(pageNumber * pageSize, taskListResponse.getData());
//                    if (taskListResponse.getTotal() >= pageSize) {
//                        isCanLoadMore = true;
//                        pageNumber = pageNumber + 1;
//                    } else {
//                        isCanLoadMore = false;
//                    }
//                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
                mView.getTaskListMoreFail();
            }
        });
        return isCanLoadMore;
    }

    public void updateTaskList(int taskStatus, String woTypeCode, int count, boolean isShow, String msg) {
        TaskManager.getInstance().listPageWorks(null,taskStatus, woTypeCode,null,null, 1, count).subscribe(new LoadingSubject<TaskListResponse>(isShow, msg) {
            @Override
            protected void _onNext(TaskListResponse taskListResponse) {
//                if (taskListResponse.isSuccess()) {
//                    data = taskListResponse.getData();
//                    mView.updateTaskListSuccess(taskListResponse.getData());
//                    if (taskListResponse.getTotal() >= pageSize) {
//                        isCanLoadMore = true;
//                    } else {
//                        isCanLoadMore = false;
//                    }
//                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }


    public void deleteWork(String workOrderId) {
        TaskManager.getInstance().deleteWork(workOrderId).safeSubscribe(new LoadingSubject<BaseResponse>(true, ResUtils.getString(R.string.data_loading)) {
            @Override
            protected void _onNext(BaseResponse baseResponse) {
                ToastUtils.shortToast("删除成功");
                mView.deleteWorkSuccess(workOrderId);
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
