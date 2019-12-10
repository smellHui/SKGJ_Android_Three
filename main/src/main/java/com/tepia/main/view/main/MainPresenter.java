package com.tepia.main.view.main;

import android.util.Log;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.response.TaskNumResponse;
import com.tepia.main.view.main.work.WorkContract;
import com.tepia.main.view.update.UpdateManager;
import com.tepia.main.view.update.VersionInfoResponse;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {

    public void getVersionInfo(String appId) {
        UpdateManager.getInstance().getUpdateInfo(appId).subscribe(new LoadingSubject<VersionInfoResponse>() {
            @Override
            protected void _onNext(VersionInfoResponse versionInfoResponse) {
                if (versionInfoResponse.getCode() == 200) {
                }
            }

            @Override
            protected void _onError(String message) {
            }
        });
    }

    public void uploadDownloadActionCount(String appId, int appVersionIndex) {
        UpdateManager.getInstance().uploadDownloadActionCount(appId, appVersionIndex).subscribe(new LoadingSubject<BaseResponse>() {
            @Override
            protected void _onNext(BaseResponse baseResponse) {
            }

            @Override
            protected void _onError(String message) {
            }
        });
}

}
