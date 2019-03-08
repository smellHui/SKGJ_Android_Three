package com.tepia.main.view.maincommon.reservoirs.detail.addoreditflood;

import android.content.Context;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.main.model.image.ImageInfoBean;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/3/6 17:49
 * @修改人         ：      
 * @修改时间       :       2019/3/6 17:49
 * @功能描述       :
 **/

public class AddOREditFloodContract {
    interface View extends BaseView {

        void addReservoirMaterialSuccess();

        void delFileSucess(ImageInfoBean imageInfoBean);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
