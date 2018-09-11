package com.tepia.main.view.main;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.task.response.TaskNumResponse;

/**
 * @author Joeshould
 */

public class MainContract {
    public  interface View extends BaseView {

    }

    public interface  Presenter extends BasePresenter<View> {

    }
}
