package com.tepia.main.view.mainworker.yunwei.startyunwei;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.model.task.bean.WorkOrderNumBean;

import java.util.List;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class StartYunWeiContract {
    interface View extends BaseView {

        void getWorkOrderNumByReservoirIdSuccess(WorkOrderNumBean data);

        void getItemListByReservoirIdSuccess(List<TaskItemBean> data);

        void newStartExecuteSuccess(TaskBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
