package com.tepia.main.view.main.work.task.taskdetail;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.loading.LoadingDialog;
import com.tepia.main.R;
import com.tepia.main.model.task.response.CandidateResponse;
import com.tepia.main.model.task.response.TaskDetailResponse;
import com.tepia.main.model.task.TaskManager;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TaskDetailPresenter extends BasePresenterImpl<TaskDetailContract.View> implements TaskDetailContract.Presenter {

    /**
     * @param workOrderId
     * @param isShow
     * @param msg
     */
    @Override
    public void getTaskDetail(String workOrderId, boolean isShow, String msg) {
        TaskManager.getInstance().getAppWorkByWorkId(workOrderId).subscribe(new LoadingSubject<TaskDetailResponse>(isShow, msg) {
            @Override
            protected void _onNext(TaskDetailResponse taskDetailResponse) {
                if (taskDetailResponse.getCode() == 0) {
                    if (mView != null) {
                        mView.getTaskDetailSucess(taskDetailResponse.getData());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    /**
     * 修改工单状态为执行中
     *
     * @param workOrderId
     * @param positionStr
     * @param isShow
     * @param msg
     */
    public void startExecute(String workOrderId, String positionStr, boolean isShow, String msg) {
        TaskManager.getInstance().startExecute(workOrderId, positionStr).subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
            @Override
            protected void _onNext(BaseResponse response) {
                if (mView != null) {
                    mView.startExecuteSucess();
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    /**
     * @param workOrderId
     * @param workOrderRoute
     * @param isShow
     * @param msg
     */
    public void endExecute(String workOrderId, String workOrderRoute, boolean isShow, String msg) {
        TaskManager.getInstance().endExecute(workOrderId, workOrderRoute).subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
            @Override
            protected void _onNext(BaseResponse response) {
                LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                if (mView != null) {
                    mView.endExecuteSucess();
                }
            }

            @Override
            protected void _onError(String message) {
                LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                ToastUtils.shortToast(message);
            }
        });
    }

    public void getPeople(String workOrderId) {
        TaskManager.getInstance().candidate(workOrderId).subscribe(new LoadingSubject<CandidateResponse>(true, ResUtils.getString(R.string.data_loading)) {
            @Override
            protected void _onNext(CandidateResponse response) {
                if (mView != null) {
                    mView.getPeopleSucess(response.getData());
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void sendOrder(String workOrderId, String userCode) {
        TaskManager.getInstance().sendOrder(workOrderId,userCode).subscribe(new LoadingSubject<BaseResponse>(true, ResUtils.getString(R.string.data_loading)) {
            @Override
            protected void _onNext(BaseResponse response) {
                if (mView != null) {
                    mView.sendOrderSucess();
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
