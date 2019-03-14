package com.tepia.main.view.main.work.task.taskdetail;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.task.bean.PeopleBean;
import com.tepia.main.model.task.bean.TaskBean;

import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TaskDetailContract {

    public interface View extends BaseView {

        void getTaskDetailSucess(TaskBean data);

        void startExecuteSucess();

        void endExecuteSucess();

        void getPeopleSucess(List<PeopleBean> data);

        void sendOrderSucess();

        void appReservoirWorkOrderItemCommitOneByOneSuccess();
    }

    public interface  Presenter extends BasePresenter<View> {
        void getTaskDetail(String id, boolean isShow, String msg);
    }
}
