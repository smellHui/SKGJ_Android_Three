package com.tepia.main.view.maincommon.reservoirs.mvpreservoir;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.reserviros.FloodBean;
import com.tepia.main.model.reserviros.IntroduceOfReservoirsBean;
import com.tepia.main.model.reserviros.ReservirosManager;
import com.tepia.main.model.reserviros.SafeRunningBean;
import com.tepia.main.model.reserviros.SupportingBean;
import com.tepia.main.model.reserviros.VisitLogBean;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-25
 * Time            :       下午3:04
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       水库模块对应present实现
 **/
public class ReserviorPresent extends BasePresenterImpl<ReserviorContract.View> implements ReserviorContract.Presenter {

    /**
     * 获取配套设施
     * @param reservoirId
     * @return
     */
    @Override
    public void getDeviceByReservoir(String reservoirId) {
        ReservirosManager.getInstance().getDeviceByReservoir(reservoirId)
                .subscribe(new LoadingSubject<SupportingBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(SupportingBean supportingBean) {
                        if (supportingBean != null) {
                            if (supportingBean.getCode() == 0) {
                                mView.success(supportingBean);

                            }else{
                                mView.failure(supportingBean.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.failure(message);

                    }
                });
    }

    /**
     * 水库安全运行报告
     * @param reservoirId
     * @return
     */
    @Override
    public void getSafetyReportByReservoir(String reservoirId) {
        ReservirosManager.getInstance().getSafetyReportByReservoir(reservoirId)
                .subscribe(new LoadingSubject<SafeRunningBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(SafeRunningBean supportingBean) {
                        if (supportingBean != null) {
                            if (supportingBean.getCode() == 0) {
                                mView.success(supportingBean);

                            }else{
                                mView.failure(supportingBean.getMsg());
                            }
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.failure(message);

                    }
                });

    }

    /**
     * 查询防汛物资
     * @param reservoirId
     */
    @Override
    public void getMaterialByReservoir(String reservoirId) {
        ReservirosManager.getInstance().getMaterialByReservoir(reservoirId)
                .subscribe(new LoadingSubject<FloodBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(FloodBean floodBean) {
                        if (floodBean != null) {
                            if (floodBean.getCode() == 0) {
                                mView.success(floodBean);

                            }else{
                                mView.failure(floodBean.getMsg());
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
    public void getEmergencyByReservoir(String reservoirId) {

    }

    @Override
    public void getFloodControlByReservoir(String reservoirId) {

    }

    /**
     * 查询水库简介
     * @param reservoirId
     * @return
     */
    @Override
    public void getBaseInfo(String reservoirId) {
        ReservirosManager.getInstance().getBaseInfo(reservoirId)
                .subscribe(new LoadingSubject<IntroduceOfReservoirsBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(IntroduceOfReservoirsBean introduceOfReservoirsBean) {
                        if (introduceOfReservoirsBean != null) {
                            if (introduceOfReservoirsBean.getCode() == 0) {
                                mView.success(introduceOfReservoirsBean);

                            }else{
                                mView.failure(introduceOfReservoirsBean.getMsg());
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
    public void getStorageCurveByReservoir(String reservoirId) {

    }
}
