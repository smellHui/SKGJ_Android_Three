package com.tepia.main.view.update;

import com.tepia.base.http.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Android Studio
 *
 * @author : Arthur
 * Date :    2019/11/26
 * Time :    9:48
 * Describe :
 */
public interface UpdateHttpService {

    /**
     * 获取动态菜单
     *
     * @param appId
     * @return
     */
    @GET("app/bizAppInfo/checkNewVersionInfo")
    Observable<VersionInfoResponse> getUpdateInfo(@Header("appId") String appId);

    /**
     * 上传app下载次数
     */
    @FormUrlEncoded
    @POST("app/bizAppVersion/addDownNumber")
    Observable<BaseResponse> uploadDownloadActionCount(@Field("appId") String appId,
                                                       @Field("appVersionIndex") int appVersionIndex);
}
