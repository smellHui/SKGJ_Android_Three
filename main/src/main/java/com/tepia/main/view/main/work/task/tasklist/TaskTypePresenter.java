package com.tepia.main.view.main.work.task.tasklist;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.response.TaskListResponse;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TaskTypePresenter extends BasePresenterImpl<TaskTypeContract.View> implements TaskTypeContract.Presenter {

    public void taskSearch(String keyWord) {
        TaskManager.getInstance().listPageWorks(keyWord,Integer.MAX_VALUE,null,null,null,1,Integer.MAX_VALUE)
                .subscribe(new LoadingSubject<TaskListResponse>() {
            @Override
            protected void _onNext(TaskListResponse taskListResponse) {

                mView.taskSearchSuccess(taskListResponse.getData().getList());

            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
