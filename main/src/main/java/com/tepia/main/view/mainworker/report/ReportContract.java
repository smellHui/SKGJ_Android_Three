package com.tepia.main.view.mainworker.report;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-20
 * Time            :       下午6:01
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :
 **/
public class ReportContract {
    public interface View<T> extends BaseView {
        void success(T data);
        void failure(String msg);
    }

    interface  Presenter extends BasePresenter<ReportContract.View> {
    }
}
