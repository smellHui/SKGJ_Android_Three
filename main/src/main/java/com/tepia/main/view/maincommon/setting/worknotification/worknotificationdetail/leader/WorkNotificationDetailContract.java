package com.tepia.main.view.maincommon.setting.worknotification.worknotificationdetail.leader;

import android.content.Context;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.worknotification.WorkNoticeBean;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class WorkNotificationDetailContract {
    interface View extends BaseView {

        void getWorkNoticeDetailSuccess(WorkNoticeBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
