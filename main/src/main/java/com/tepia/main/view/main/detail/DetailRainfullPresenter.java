package com.tepia.main.view.main.detail;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.detai.DetailManager;
import com.tepia.main.model.detai.OneThreeSixBean;
import com.tepia.main.model.detai.RainfullBean;
import com.tepia.main.view.main.detail.DetailContract.RainfullView;

/**
 * 雨量站详情页面分开写，因为这个页面同时有多个请求
 *
 * @author liying
 * @date 2018/7/24
 */

public class DetailRainfullPresenter extends BasePresenterImpl<DetailContract.RainfullView> implements DetailContract.RainfullPresenter {
    private static final String TAG = DetailRainfullPresenter.class.getName();



    /**
     * 获取雨量站详情
     * @param stcd
     * @param selectType
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public void getStPpthRChartDataInfo(String stcd, String selectType, String startDate, String endDate) {
        DetailManager.getInstance().getStPpthRChartDataInfo(stcd, selectType, startDate, endDate)
                .subscribe(new LoadingSubject<RainfullBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(RainfullBean rainfullBean) {
                        if (rainfullBean != null) {
                            if (rainfullBean.getCode() == 0) {
                                mView.success(rainfullBean);
                            } else {
                                mView.failure(rainfullBean.getMsg());
                            }
                        }

                    }

                    @Override
                    protected void _onError(String message) {
                        mView.failure(message);

                        LogUtil.e(TAG, "_onError" + message);
                    }
                });
    }

    /**
     * 雨量站获取1,3,6小时降雨量和
     * @param stcd
     * @return
     */
    @Override
    public void getOneThreeSixTimePpth(String stcd,boolean isshow) {

        DetailManager.getInstance().getOneThreeSixTimePpth(stcd)
                .subscribe(new LoadingSubject<OneThreeSixBean>(isshow, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(OneThreeSixBean oneThreeSixBean) {
                        if (oneThreeSixBean != null) {
                            if (oneThreeSixBean.getCode() == 0) {
                                mView.successThree(oneThreeSixBean);
                            } else {
                                mView.failureThree(oneThreeSixBean.getMsg());
                            }
                        }

                    }

                    @Override
                    protected void _onError(String message) {
                        mView.failureThree(message);

                        LogUtil.e(TAG, "_onError" + message);
                    }
                });
    }


}
