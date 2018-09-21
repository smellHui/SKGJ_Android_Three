package com.tepia.main.view.mainworker.homepage;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.user.homepageinfo.HomeGetReservoirInfoBean;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class HomeXunChaContract {
    interface View extends BaseView {

        void getHommeInfoSuccess(HomeGetReservoirInfoBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
