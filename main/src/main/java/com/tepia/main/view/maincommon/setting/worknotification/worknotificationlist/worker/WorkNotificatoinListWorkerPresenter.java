package com.tepia.main.view.maincommon.setting.worknotification.worknotificationlist.worker;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.model.worknotification.FeedBackWorkNoticeListResponse;
import com.tepia.main.model.worknotification.WorkNotificationManager;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class WorkNotificatoinListWorkerPresenter extends BasePresenterImpl<WorkNotificatoinListWorkerContract.View> implements WorkNotificatoinListWorkerContract.Presenter {

    int currentPage = 1;
    String pageSize = "20";

    public void getWorkNoticeWorkerList() {
        WorkNotificationManager.getInstance().getWorkNoticeWorkerList(currentPage+"",pageSize).safeSubscribe(new LoadingSubject<FeedBackWorkNoticeListResponse>() {
            @Override
            protected void _onNext(FeedBackWorkNoticeListResponse feedBackWorkNoticeListResponse) {
                mView.getWorkNoticeWorkerListSuccess(feedBackWorkNoticeListResponse.getData().getList());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
