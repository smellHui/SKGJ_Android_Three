package com.tepia.main.view.main.work.task.taskdeal.taskitemdeal;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.image.ImageInfoBean;
import com.tepia.main.model.task.bean.TaskItemBean;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TaskItemDealContract {
    interface View extends BaseView {
        void getTaskItemDetailSucess(TaskItemBean data);

        void delFileSucess(ImageInfoBean imageInfoBean, boolean isbefore);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
