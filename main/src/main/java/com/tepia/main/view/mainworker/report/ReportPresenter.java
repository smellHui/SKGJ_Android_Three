package com.tepia.main.view.mainworker.report;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.OperationPlanBean;
import com.tepia.main.model.reserviros.ReservirosManager;
import com.tepia.main.model.shangbao.ShangbaoManager;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-20
 * Time            :       下午6:03
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :
 **/
public class ReportPresenter extends BasePresenterImpl<ReportContract.View> implements ReportContract.Presenter {


    @Override
    public void uploadingStRsvr(String reservoirId, String rz) {

        ShangbaoManager.getInstance().uploadingStRsvr(reservoirId,rz)
                .subscribe(new LoadingSubject<BaseResponse>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(BaseResponse operationPlanBean) {
                        if (operationPlanBean != null) {
                            if (operationPlanBean.getCode() == 0) {
                                mView.success(operationPlanBean);

                            }else{
                                mView.failure(operationPlanBean.getMsg());
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
    public void getProblemList(String reservoirId, String workOrderId, String problemStatus, String startDate, String endDate, String currentPage, String pageSize) {

    }

    @Override
    public void reportProblem(String reservoirId, String problemTitle, String problemDescription, String lgtd, String lttd, String files) {

    }
}
