package com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.worker;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.model.worknotification.WorkNoticeDetailResponse;
import com.tepia.main.model.worknotification.WorkNotificationManager;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class WorkNotificationDetailWorkerPresenter extends BasePresenterImpl<WorkNotificationDetailWorkerContract.View> implements WorkNotificationDetailWorkerContract.Presenter{
    public void getWorkNoticeDetail(String noticeFeedbackId, String noticeId) {
        WorkNotificationManager.getInstance().getWorkNoticeDetailWroker(noticeFeedbackId,noticeId).safeSubscribe(new LoadingSubject<WorkNoticeDetailResponse>() {
            @Override
            protected void _onNext(WorkNoticeDetailResponse workNoticeDetailResponse) {
                mView.getWorkNoticeDetailSuccess(workNoticeDetailResponse.getData());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void feedBackWorkNotice(String noticeFeedbackId, String feedBackContent) {
        WorkNotificationManager.getInstance().feedBackWorkNotice(noticeFeedbackId,feedBackContent).safeSubscribe(new LoadingSubject<BaseResponse>() {
            @Override
            protected void _onNext(BaseResponse workNoticeDetailResponse) {
                mView.feedBackWorkNoticeSuccess();
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
