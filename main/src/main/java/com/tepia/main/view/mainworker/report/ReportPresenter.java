package com.tepia.main.view.mainworker.report;

import com.tepia.base.mvp.BasePresenterImpl;

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
    public void getReservoirVideo(String reservoirId, String rz) {

    }

    @Override
    public void getProblemList(String reservoirId, String workOrderId, String problemStatus, String startDate, String endDate, String currentPage, String pageSize) {

    }

    @Override
    public void reportProblem(String reservoirId, String problemTitle, String problemDescription, String lgtd, String lttd, String files) {

    }
}
