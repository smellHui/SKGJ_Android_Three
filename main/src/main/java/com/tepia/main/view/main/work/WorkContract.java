package com.tepia.main.view.main.work;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.task.response.TaskNumResponse;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WorkContract {
    public  interface View extends BaseView {

        void getTaskNumSuccess(TaskNumResponse.DataBean data);
    }

    public interface  Presenter extends BasePresenter<View> {

    }
}
