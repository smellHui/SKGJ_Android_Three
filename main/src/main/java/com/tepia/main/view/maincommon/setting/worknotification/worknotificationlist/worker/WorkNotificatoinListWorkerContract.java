package com.tepia.main.view.maincommon.setting.worknotification.worknotificationlist.worker;

import android.content.Context;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.worknotification.FeedBackWorkNoticeBean;
import com.tepia.main.model.worknotification.FeedBackWorkNoticeListResponse;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WorkNotificatoinListWorkerContract {
    interface View extends BaseView {

        void getWorkNoticeWorkerListSuccess(List<FeedBackWorkNoticeBean> list);

        void getWorkNoticeWorkerListMoreSuccess(FeedBackWorkNoticeListResponse.DataBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
