package com.tepia.main.view.maincommon.setting.worknotification.worknotificationlist.leader;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.worknotification.WorkNoticeListResponse;
import com.tepia.main.model.worknotification.WorkNotificationManager;
import com.tepia.main.view.maincommon.setting.worknotification.worknotificationlist.leader.WorkNotificationListContract;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class WorkNotificationListPresenter extends BasePresenterImpl<WorkNotificationListContract.View> implements WorkNotificationListContract.Presenter {

    int currentPage = 1;
    String pageSize = "100";

    public void getWorkNoticeList() {
        WorkNotificationManager.getInstance().getWorkNoticeLeeaderList(currentPage + "", pageSize)
                .safeSubscribe(new LoadingSubject<WorkNoticeListResponse>(true, ResUtils.getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(WorkNoticeListResponse workNoticeListResponse) {
                        mView.getWorkNoticeListSuccess(workNoticeListResponse.getData().getList());
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);
                    }
                });
    }
}
