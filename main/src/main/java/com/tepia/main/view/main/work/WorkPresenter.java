package com.tepia.main.view.main.work;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.response.TaskNumResponse;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WorkPresenter extends BasePresenterImpl<WorkContract.View> implements WorkContract.Presenter{

    public void getTaskNum() {
        TaskManager.getInstance().getTaskNum().subscribe(new LoadingSubject<TaskNumResponse>() {
            @Override
            protected void _onNext(TaskNumResponse taskNumResponse) {
                if (taskNumResponse.getCode() == 0){
                    mView.getTaskNumSuccess(taskNumResponse.getData());
                }else {
                    _onError(taskNumResponse.getMsg());
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
