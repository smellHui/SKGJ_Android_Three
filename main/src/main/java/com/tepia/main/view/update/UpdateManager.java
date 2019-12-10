package com.tepia.main.view.update;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android Studio
 *
 * @author : Arthur
 * Date :    2019/11/26
 * Time :    9:43
 * Describe :
 */
public class UpdateManager {

    private UpdateHttpService mRetrofitService;

    private static final UpdateManager ourInstance = new UpdateManager();

    public static UpdateManager getInstance() {
        return ourInstance;
    }

    private UpdateManager() {
        mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_APP_UPDATE).create(UpdateHttpService.class);
    }


    /**
     * 查询更新信息
     *
     * @return
     */
    public Observable<VersionInfoResponse> getUpdateInfo(String appId) {
        return mRetrofitService.getUpdateInfo(appId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 下载计数
     */
    public Observable<BaseResponse> uploadDownloadActionCount(String appId, int appVersionIndex){
        return mRetrofitService.uploadDownloadActionCount(appId, appVersionIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
