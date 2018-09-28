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
        /**
         * 上报水位
         * @param reservoirId
         * @param rz
         */
        void uploadingStRsvr(String reservoirId, String rz);

        /**
         * 查询应急情况列表
         * @param reservoirId
         * @param workOrderId
         * @param problemStatus
         * @param startDate
         * @param endDate
         * @param currentPage
         * @param pageSize
         */
        void getProblemList(String reservoirId,
                       String workOrderId,
                       String problemStatus,
                       String startDate,
                       String endDate,
                       String currentPage,
                       String pageSize
        );

        /**
         * 应急上报
         * @param reservoirId
         * @param problemTitle
         * @param problemDescription
         * @param lgtd
         * @param lttd
         * @param files
         */
        void reportProblem(String reservoirId,
                String problemTitle,
                String problemDescription,
                String lgtd,
                String lttd,
                String files

        );
    }
}
