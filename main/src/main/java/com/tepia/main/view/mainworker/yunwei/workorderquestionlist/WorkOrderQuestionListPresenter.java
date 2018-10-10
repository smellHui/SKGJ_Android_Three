package com.tepia.main.view.mainworker.yunwei.workorderquestionlist;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.report.EmergenceListBean;
import com.tepia.main.model.report.ShangbaoManager;

/**
 * @author         :      zhang xinhua
 * Version         :       1.0
 * 功能描述        :
 **/

public class WorkOrderQuestionListPresenter extends BasePresenterImpl<WorkOrderQuestionListContract.View> implements WorkOrderQuestionListContract.Presenter{

    public void getProblemList(String workOrderId) {
        ShangbaoManager.getInstance().getProblemList(null, workOrderId, null, null, null, 1+"", Integer.MAX_VALUE+"")
                .subscribe(new LoadingSubject<EmergenceListBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(EmergenceListBean operationPlanBean) {
                        if (operationPlanBean != null) {
                            if (operationPlanBean.getCode() == 0) {
                                mView.getProblemListSuccess(operationPlanBean);
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtils.shortToast(message);

                    }
                });
    }
}
