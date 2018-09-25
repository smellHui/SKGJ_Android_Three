package com.tepia.main.view.maincommon.reservoirs.mvpreservoir;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.ReservirosManager;
import com.tepia.main.model.reserviros.VisitLogBean;
import com.tepia.main.model.reserviros.VisitLogDetailBean;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-21
 * Time            :       下午6:03
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       到访日志present实现
 **/
public class VisitLogPresenter extends BasePresenterImpl<VisitLogContract.View> implements VisitLogContract.Presenter {

    @Override
    public void getPageList(String reservoirId, String currentPage, String pageSize,boolean isshowloading) {
        ReservirosManager.getInstance().getPageList(reservoirId,currentPage,pageSize)
                .subscribe(new LoadingSubject<VisitLogBean>(isshowloading, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(VisitLogBean visitLogBean) {
                        if (visitLogBean != null) {
                            if (visitLogBean.getCode() == 0) {
                                mView.success(visitLogBean);

                            }else{
                                mView.failure(visitLogBean.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.failure(message);

                    }
                });
    }

    @Override
    public void detail(String id) {
        ReservirosManager.getInstance().detail(id)
                .subscribe(new LoadingSubject<VisitLogDetailBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(VisitLogDetailBean visitLogDetailBean) {
                        if (visitLogDetailBean != null) {
                            if (visitLogDetailBean.getCode() == 0) {
                                mView.success(visitLogDetailBean);

                            }else{
                                mView.failure(visitLogDetailBean.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.failure(message);

                    }
                });
    }
}
