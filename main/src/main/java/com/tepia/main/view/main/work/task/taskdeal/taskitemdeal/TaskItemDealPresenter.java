package com.tepia.main.view.main.work.task.taskdeal.taskitemdeal;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.model.image.ImageInfoBean;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.response.TaskItemDetailResponse;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TaskItemDealPresenter extends BasePresenterImpl<TaskItemDealContract.View> implements TaskItemDealContract.Presenter {

    public void getTaskItemDetail(String itemId, boolean isShow, String msg) {
        TaskManager.getInstance().getAppReservoirWorkOrderItemInfo(itemId).subscribe(new LoadingSubject<TaskItemDetailResponse>(isShow, msg) {
            @Override
            protected void _onNext(TaskItemDetailResponse taskDetailResponse) {
                if (taskDetailResponse.getCode() == 0) {
                    if (mView != null) {
                        mView.getTaskItemDetailSucess(taskDetailResponse.getData());
                    }
                } else {
                    _onError(taskDetailResponse.getMsg());
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void deleteImage(ImageInfoBean imageInfoBean, boolean isbefore, boolean isShow, String msg) {
        TaskManager.getInstance().delFile(imageInfoBean.getFileId()).subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
            @Override
            protected void _onNext(BaseResponse response) {
                if (response.getCode() == 0) {
                    if (mView != null) {
                        mView.delFileSucess(imageInfoBean, isbefore);
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });

    }
}
