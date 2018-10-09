package com.tepia.main.view.maincommon.setting.worknotification.worknotificationlist.worker;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.worknotification.FeedBackWorkNoticeListResponse;
import com.tepia.main.model.worknotification.WorkNotificationManager;

/**
 * @author :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class WorkNotificatoinListWorkerPresenter extends BasePresenterImpl<WorkNotificatoinListWorkerContract.View> implements WorkNotificatoinListWorkerContract.Presenter {

    int currentPage = 1;
    String pageSize = "10";
    public boolean isCanLoadMore = true;

    public void getWorkNoticeWorkerList() {
        currentPage = 1;
        WorkNotificationManager.getInstance().getWorkNoticeWorkerList(currentPage + "", pageSize)
                .safeSubscribe(new LoadingSubject<FeedBackWorkNoticeListResponse>(true, ResUtils.getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(FeedBackWorkNoticeListResponse feedBackWorkNoticeListResponse) {
                        if (feedBackWorkNoticeListResponse.getData().getPageNum() >= feedBackWorkNoticeListResponse.getData().getPages()) {
                            isCanLoadMore = false;
                        } else {
                            isCanLoadMore = true;
                        }
                        currentPage = feedBackWorkNoticeListResponse.getData().getPageNum();
                        mView.getWorkNoticeWorkerListSuccess(feedBackWorkNoticeListResponse.getData().getList());

                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);
                    }
                });
    }

    public void getWorkNoticeListMore() {
        WorkNotificationManager.getInstance().getWorkNoticeWorkerList(currentPage + 1 + "", pageSize)
                .safeSubscribe(new LoadingSubject<FeedBackWorkNoticeListResponse>(false, ResUtils.getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(FeedBackWorkNoticeListResponse feedBackWorkNoticeListResponse) {
                        if (feedBackWorkNoticeListResponse.getData().getPageNum() >= feedBackWorkNoticeListResponse.getData().getPages()) {
                            isCanLoadMore = false;
                        } else {
                            isCanLoadMore = true;
                        }
                        currentPage = feedBackWorkNoticeListResponse.getData().getPageNum();
                        mView.getWorkNoticeWorkerListMoreSuccess(feedBackWorkNoticeListResponse.getData());

                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);
                    }
                });
    }
}
