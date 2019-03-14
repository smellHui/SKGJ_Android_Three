package com.tepia.main.view.mainworker.yunwei.yunweilist;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.response.TaskListResponse;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class YunWeiListContract {
    interface View extends BaseView {

        void getPatrolWorkOrderListSuccess(List<TaskBean> list);

        void getPatrolWorkOrderListMoreSuccess(TaskListResponse.DataBean list);

        void getPatrolWorkOrderListMoreFailure();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
