package com.tepia.main.view.main.work.task2.taskedit;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.response.AllItemListResponse;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 * @author Joeshould
 */

public class TaskEditPresenter extends BasePresenterImpl<TaskEditContract.View> implements TaskEditContract.Presenter{

    public void updateWork(String workOrderId, String workOrderName, String planStartTime, String planEndTime, String remarks, String workOrderItemIds) {
        TaskManager.getInstance().updateWork(workOrderId,workOrderName,planStartTime,planEndTime,remarks,workOrderItemIds)
                .safeSubscribe(new LoadingSubject<BaseResponse>(true, ResUtils.getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.updateWorkSuccess();
                        ToastUtils.shortToast("修改成功");
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);
                    }
                });
    }

    public void getAllItemList(String workOrderId) {
        TaskManager.getInstance().getAllItemList(workOrderId)
                .safeSubscribe(new LoadingSubject<AllItemListResponse>(true, ResUtils.getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(AllItemListResponse response) {
                        mView.getAllItemListSuccess(response.getData());
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);
                    }
                });
    }
}
