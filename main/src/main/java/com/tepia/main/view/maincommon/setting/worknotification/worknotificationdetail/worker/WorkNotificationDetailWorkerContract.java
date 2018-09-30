package com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.worker;

import android.content.Context;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.worknotification.WorkNoticeBean;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WorkNotificationDetailWorkerContract {
    interface View extends BaseView {

        void getWorkNoticeDetailSuccess(WorkNoticeBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
