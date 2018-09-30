package com.tepia.main.model.worknotification;

import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.user.UserManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-09-30
 * Time            :       9:33
 * Version         :       1.0
 * 功能描述        :
 **/
public class WorkNotificationManager {
    private static final WorkNotificationManager ourInstance = new WorkNotificationManager();
    private final WorkNotificationHttpService mRetrofitService;

    public static WorkNotificationManager getInstance() {
        return ourInstance;
    }

    private WorkNotificationManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL + APPCostant.API_SERVER_USER_ADMIN).create(WorkNotificationHttpService.class);
    }

    public Observable<WorkNoticeListResponse> getWorkNoticeLeeaderList(String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getWorkNoticeLeeaderList(token, currentPage, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<FeedBackWorkNoticeListResponse> getWorkNoticeWorkerList(String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getWorkNoticeWorkerList(token, currentPage, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<WorkNoticeDetailResponse> getWorkNoticeDetail(String noticeId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getWorkNoticeDetail(token, noticeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<WorkNoticeDetailResponse> getWorkNoticeDetailWroker(String noticeFeedbackId, String noticeId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getWorkNoticeDetailWroker(token,noticeFeedbackId, noticeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
