package com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.leader;

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

public class WorkNotificationDetailPresenter extends BasePresenterImpl<WorkNotificationDetailContract.View> implements WorkNotificationDetailContract.Presenter{



    public void getWorkNoticeDetail( String noticeId) {
        WorkNotificationManager.getInstance().getWorkNoticeDetail(noticeId).safeSubscribe(new LoadingSubject<WorkNoticeDetailResponse>() {
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
}
