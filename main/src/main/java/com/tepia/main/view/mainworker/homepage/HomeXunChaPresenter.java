package com.tepia.main.view.mainworker.homepage;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.main.R;
import com.tepia.main.model.user.HomeGetReservoirInfoResponse;
import com.tepia.main.model.user.UserManager;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :        首页涉及的接口
 **/

public class HomeXunChaPresenter extends BasePresenterImpl<HomeXunChaContract.View> implements HomeXunChaContract.Presenter{

    /**
     * 查询APP首页数据
     * @param reservoirId
     */
    public void getAppHomeGetReservoirInfo(String reservoirId) {
        UserManager.getInstance_ADMIN().getAppHomeGetReservoirInfo(reservoirId).safeSubscribe(new LoadingSubject<HomeGetReservoirInfoResponse>(true, ResUtils.getString(R.string.data_loading)) {
            @Override
            protected void _onNext(HomeGetReservoirInfoResponse response) {
                mView.getHommeInfoSuccess(response.getData());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
