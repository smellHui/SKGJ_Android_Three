package com.tepia.main.view.maincommon.setting.worknotification.worknotificationlist.leader;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.worknotification.WorkNoticeBean;

import java.util.List;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class WorkNotificationListContract {
    interface View extends BaseView {

        void getWorkNoticeListSuccess(List<WorkNoticeBean> list);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
