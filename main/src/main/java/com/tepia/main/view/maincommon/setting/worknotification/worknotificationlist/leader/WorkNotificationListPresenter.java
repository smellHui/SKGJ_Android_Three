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
    String pageSize = "10";
    public boolean isCanLoadMore = false;

    public void getWorkNoticeList() {
        currentPage = 1;
        WorkNotificationManager.getInstance().getWorkNoticeLeeaderList(currentPage + "", pageSize)
                .safeSubscribe(new LoadingSubject<WorkNoticeListResponse>(true, ResUtils.getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(WorkNoticeListResponse workNoticeListResponse) {
                        currentPage = workNoticeListResponse.getData().getPageNum();
                        if (workNoticeListResponse.getData().getPages() >= workNoticeListResponse.getData().getPageNum()) {
                            isCanLoadMore = false;
                        } else {
                            isCanLoadMore = true;
                        }
                        mView.getWorkNoticeListSuccess(workNoticeListResponse.getData().getList());
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);
                    }
                });
    }

    public void getWorkNoticeListMore() {
        WorkNotificationManager.getInstance().getWorkNoticeLeeaderList(currentPage + 1 + "", pageSize)
                .safeSubscribe(new LoadingSubject<WorkNoticeListResponse>(false, ResUtils.getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(WorkNoticeListResponse workNoticeListResponse) {
                        currentPage = workNoticeListResponse.getData().getPageNum();
                        if (workNoticeListResponse.getData().getPages() >= workNoticeListResponse.getData().getPageNum()) {
                            isCanLoadMore = false;
                        } else {
                            isCanLoadMore = true;
                        }
                        mView.getWorkNoticeListSuccessMore(workNoticeListResponse.getData());

                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);
                    }
                });
    }
}
