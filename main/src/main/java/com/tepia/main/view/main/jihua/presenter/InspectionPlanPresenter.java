package com.tepia.main.view.main.jihua.presenter;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.LogUtil;
import com.tepia.main.model.map.MainMapManager;
import com.tepia.main.model.map.ReservoirResponse;
import com.tepia.main.model.plan.InspectionPlanManager;
import com.tepia.main.model.plan.PlanListResponse;
import com.tepia.main.view.main.map.presenter.MainMapContract;

/**
 * 巡检计划
 *
 * @author 44822
 */
public class InspectionPlanPresenter extends BasePresenterImpl<InspectionPlanContract.View> implements InspectionPlanContract.Presenter {

    @Override
    public void getPlanInfoList(String reservoirName, String planType, String operationType, String isGenerate, String startDate, String endDate, String currentPage, String pageSize, boolean isShowLoading) {
        InspectionPlanManager.getInstance().getPlanInfoList(reservoirName, planType, operationType, isGenerate, startDate, endDate, currentPage, pageSize).subscribe(new LoadingSubject<PlanListResponse>(isShowLoading, "正在加载中...") {
            @Override
            protected void _onNext(PlanListResponse planListResponse) {
                if (mView != null) {
                    if (planListResponse.getCode() == 0) {
                        mView.success(planListResponse);
                    } else {
                        if (planListResponse.getMsg() != null && planListResponse.getMsg().length() > 0) {
                            mView.failure(planListResponse.getMsg());
                        }
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message);
                }
            }
        });
    }

    @Override
    public void deleteByIds(String planIds) {
        InspectionPlanManager.getInstance().deleteByIds(planIds).subscribe(new LoadingSubject<BaseResponse>(true, "正在删除中...") {
            @Override
            protected void _onNext(BaseResponse baseResponse) {
                if (mView != null) {
                    if (baseResponse.getCode() == 0) {
                        mView.success(baseResponse);
                    } else {
                        if (baseResponse.getMsg() != null && baseResponse.getMsg().length() > 0) {
                            mView.failure(baseResponse.getMsg());
                        }
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message);
                }
            }
        });
    }

    @Override
    public void saveBizPlanInfo(String planType, String operationType, String reservoirId, String planName, String planTimes, String remarks, String problemId) {
        InspectionPlanManager.getInstance().saveBizPlanInfo(planType, operationType, reservoirId, planName, planTimes, remarks, problemId).subscribe(new LoadingSubject<BaseResponse>(true, "正在新建计划...") {
            @Override
            protected void _onNext(BaseResponse baseResponse) {
                if (mView != null) {
                    if (baseResponse.getCode() == 0) {
                        mView.success(baseResponse);
                    } else {
                        if (baseResponse.getMsg() != null && baseResponse.getMsg().length() > 0) {
                            mView.failure(baseResponse.getMsg());
                        }
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message);
                }
            }
        });
    }

    @Override
    public void updateBizPlanInfo(String planId, String planType, String operationType, String reservoirId, String planName, String planTimes, String remarks) {
        InspectionPlanManager.getInstance().updateBizPlanInfo(planId, planType, operationType, reservoirId, planName, planTimes, remarks).subscribe(new LoadingSubject<BaseResponse>(false, "正在修改...") {
            @Override
            protected void _onNext(BaseResponse baseResponse) {
                if (mView != null) {
                    if (baseResponse.getCode() == 0) {
                        mView.success(baseResponse);
                    } else {
                        if (baseResponse.getMsg() != null && baseResponse.getMsg().length() > 0) {
                            mView.failure(baseResponse.getMsg());
                        }
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (mView != null) {
                    mView.failure(message);
                }
            }
        });
    }

    @Override
    public void createWorkOrder(String planIds) {
        InspectionPlanManager.getInstance().createWorkOrder(planIds).subscribe(new LoadingSubject<BaseResponse>(true, "正在生成工单...") {
            @Override
            protected void _onNext(BaseResponse baseResponse) {
//                LogUtil.i("onNext");
                if (mView != null) {
                    if (baseResponse.getCode() == 0) {
                        mView.success(baseResponse);
                    } else {
                        if (baseResponse.getMsg() != null && baseResponse.getMsg().length() > 0) {
                            mView.failure(baseResponse.getMsg());
                        }
                    }
                }
            }

            @Override
            protected void _onError(String message) {
//                LogUtil.i("onError");
                if (mView != null) {
//                    LogUtil.i("错误信息:"+message);
                    if ("计划所属水库运维配置项为空".equals(message)){
                        mView.failure("当前水库配置信息不完善，请联系管理员完善信息");
                    }else {
                        mView.failure(message);
                    }
                }
            }
        });
    }
}
