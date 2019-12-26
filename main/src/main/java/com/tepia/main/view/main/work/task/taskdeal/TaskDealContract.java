package com.tepia.main.view.main.work.task.taskdeal;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.task.bean.TaskBean;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TaskDealContract {
    interface View extends BaseView {

        void getTaskDetailSucess(TaskBean data);

        void commitSucess();

        void commitBack();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
