package com.tepia.main.view.maincommon.setting.worknotification.addworknotification;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.worknotification.WorkNotificationManager;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddWorkNotificationPresenter extends BasePresenterImpl<AddWorkNotificationContract.View> implements AddWorkNotificationContract.Presenter {

    public void addWorkNotice(String reservoirIds, String noticeTitle, String noticeContent, ArrayList<String> files, ArrayList<String> images) {
        WorkNotificationManager.getInstance().addWorkNotice(reservoirIds, noticeTitle, noticeContent, files, images)
                .safeSubscribe(new LoadingSubject<BaseResponse>(true, ResUtils.getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.addWorkNoticeSuccess();
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);
                    }
                });
    }
}
