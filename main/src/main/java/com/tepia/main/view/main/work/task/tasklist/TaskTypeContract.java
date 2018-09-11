package com.tepia.main.view.main.work.task.tasklist;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.task.bean.TaskBean;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TaskTypeContract {
    public interface View extends BaseView {
        void taskSearchSuccess(List<TaskBean> data);
    }

    public interface Presenter extends BasePresenter<View> {

    }
}
