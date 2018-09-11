package com.tepia.main.view.main.work.task2.taskedit;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.task.bean.TaskItemBean;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TaskEditContract {
    interface View extends BaseView {

        void updateWorkSuccess();

        void getAllItemListSuccess(List<TaskItemBean> data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
