package com.tepia.main.view.main.work.task.taskdeal.taskitemdeal;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.main.model.image.ImageInfoBean;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.model.task.response.TaskItemDetailResponse;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TaskItemDealPresenter extends BasePresenterImpl<TaskItemDealContract.View> implements TaskItemDealContract.Presenter {

    public void getTaskItemDetail(String itemId, boolean isShow, String msg) {
//        if (mView != null) {
//            List<TaskItemBean> templist = DataSupport.where("itemId=?", itemId).find(TaskItemBean.class);
//            if (!CollectionsUtil.isEmpty(templist)) {
//                mView.getTaskItemDetailSucess(templist.get(0));
//            }
//        }
        TaskManager.getInstance().getAppReservoirWorkOrderItemInfo(itemId).subscribe(new LoadingSubject<TaskItemDetailResponse>(isShow, msg) {
            @Override
            protected void _onNext(TaskItemDetailResponse taskDetailResponse) {
                if (taskDetailResponse.getCode() == 0) {
                    if (mView != null) {
                        taskDetailResponse.getData().save();
                        List<TaskItemBean> templist = DataSupport.where("itemId=?", itemId).find(TaskItemBean.class);
                        if (!CollectionsUtil.isEmpty(templist)) {
                            mView.getTaskItemDetailSucess(templist.get(0));
                        } else {
                            mView.getTaskItemDetailSucess(taskDetailResponse.getData());
                        }
                    }
                } else {
                    _onError(taskDetailResponse.getMsg());
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    List<TaskItemBean> templist = DataSupport.where("itemId=?", itemId).find(TaskItemBean.class);
                    if (!CollectionsUtil.isEmpty(templist)) {
//                        mView.getTaskItemDetailSucess(templist.get(0));
                    } else {
//                        ToastUtils.shortToast(message);
                    }

                }
            }
        });
    }

    public void deleteImage(ImageInfoBean imageInfoBean, String isbefore, boolean isShow, String msg) {
        TaskManager.getInstance().delFile(imageInfoBean.getFileId()).subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
            @Override
            protected void _onNext(BaseResponse response) {
                if (response.getCode() == 0) {
                    if (mView != null) {
                        mView.delFileSucess(imageInfoBean, isbefore);
                        imageInfoBean.delete();
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
