package com.tepia.main.view.main.work.task.tasklist;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.task.bean.TaskBean;


import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TabTaskListContract {
    public interface View extends BaseView {
        void getTaskListSuccess(int total, List<TaskBean> data);

        void getTaskListMoreSuccess(int count, List<TaskBean> data);

        void getTaskListMoreFail();

        void updateTaskListSuccess(List<TaskBean> data);

        void deleteWorkSuccess(String workOrderId);
    }

    public interface Presenter extends BasePresenter<View> {
    }
}
