package com.tepia.main.model.worknotification;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.user.UserManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
        return mRetrofitService.getWorkNoticeDetailWroker(token, noticeFeedbackId, noticeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseResponse> feedBackWorkNotice(String noticeFeedbackId, String feedBackContent) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.feedBackWorkNotice(token, noticeFeedbackId, feedBackContent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<NotFeedBackCountResponse> findNotFeedBackCount() {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.findNotFeedBackCount(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseResponse> addWorkNotice(String reservoirIds, String noticeTitle, String noticeContent, ArrayList<String> files, ArrayList<String> images) {
        String token = UserManager.getInstance().getToken();

        Map<String, RequestBody> params = new HashMap<>();
        params.put("reservoirIds", RetrofitManager.convertToRequestBody(reservoirIds));
        params.put("noticeTitle", RetrofitManager.convertToRequestBody(noticeTitle));
        params.put("noticeContent", RetrofitManager.convertToRequestBody(noticeContent));

        List<File> fileList = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i));
            fileList.add(file);
        }
        List<MultipartBody.Part> beforePathList = RetrofitManager.filesToMultipartBodyParts("files", fileList);
        List<File> fileList2 = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            File file = new File(images.get(i));
            fileList2.add(file);
        }
        List<MultipartBody.Part> imagesPathList = RetrofitManager.filesToMultipartBodyParts("images", fileList2);
        return mRetrofitService.addWorkNotice(token, params, beforePathList,imagesPathList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
