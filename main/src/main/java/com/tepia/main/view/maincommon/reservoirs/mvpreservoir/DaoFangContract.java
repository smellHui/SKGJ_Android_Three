package com.tepia.main.view.maincommon.reservoirs.mvpreservoir;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-21
 * Time            :       下午6:01
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :
 **/
public class DaoFangContract {
    public interface View<T> extends BaseView {
        void success(T data);
        void failure(String msg);
    }

    interface  Presenter extends BasePresenter<DaoFangContract.View> {
    }
}
