package com.tepia.main.view.mainworker.yunwei.workorderquestionlist;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.report.EmergenceListBean;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WorkOrderQuestionListContract {
    interface View extends BaseView {

        void getProblemListSuccess(EmergenceListBean operationPlanBean);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
