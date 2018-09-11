package com.tepia.main.view.main.detail;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.model.detai.DetailManager;
import com.tepia.main.model.detai.ImageBean;
import com.tepia.main.model.detai.OneThreeSixBean;
import com.tepia.main.model.detai.RainfullBean;
import com.tepia.main.model.detai.StRiverRBean;
import com.tepia.main.model.detai.WaterPhBean;
import com.tepia.main.model.detai.WaterlevelBean;
import com.tepia.main.view.main.question.QuestionPresenter;

/**
 * 详情页面
 *
 * @author liying
 * @date 2018/7/24
 */

public class DetailPresenter extends BasePresenterImpl<DetailContract.View> implements DetailContract.Presenter {
    private static final String TAG = DetailPresenter.class.getName();
    public static final String TAG_getStPpthRChartDataInfo = "error_one";
    public static final String TAG_getOneThreeSixTimePpth = "error_two";

    /**
     * 获取流量站详情
     * @param stcd
     * @param selectType
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public void findStRiverRByStcd(String stcd, String selectType, String startDate, String endDate) {
        DetailManager.getInstance().findStRiverRByStcd(stcd, selectType, startDate, endDate)
                .subscribe(new LoadingSubject<StRiverRBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(StRiverRBean reservoirBean) {
                        if (reservoirBean != null) {
                            if (reservoirBean.getCode() == 0) {
                                mView.success(reservoirBean);
                            } else {
                                mView.failure(reservoirBean.getMsg());
                            }
                        }

                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtil.e(TAG, "_onError" + message);
                    }
                });
    }

    /**
     * 查询水质站详情
     * @param stcd
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public void findListStWqRByDate(String stcd, String startDate, String endDate) {
        DetailManager.getInstance().findListStWqRByDate(stcd, startDate, endDate)
                .subscribe(new LoadingSubject<WaterPhBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(WaterPhBean reservoirBean) {
                        if (reservoirBean != null) {
                            LogUtil.e(TAG, reservoirBean.getCode() + "");
                            if (reservoirBean.getCode() == 0) {
                                mView.success(reservoirBean);
                            } else {
                                mView.failure(reservoirBean.getMsg());
                            }
                        }

                    }

                    @Override
                    protected void _onError(String message) {
                        LogUtil.e(TAG, "_onError" + message);
                    }
                });
    }

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
                                mView.failure(rainfullBean.getMsg()+TAG_getStPpthRChartDataInfo);
                            }
                        }

                    }

                    @Override
                    protected void _onError(String message) {
                        mView.failure(message + TAG_getStPpthRChartDataInfo);

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
                                mView.success(oneThreeSixBean);
                            } else {
                                mView.failure(oneThreeSixBean.getMsg()+TAG_getOneThreeSixTimePpth);
                            }
                        }

                    }

                    @Override
                    protected void _onError(String message) {
                        mView.failure(message + TAG_getStPpthRChartDataInfo);

                        LogUtil.e(TAG, "_onError" + message);
                    }
                });
    }

    /**
     * 查询水位站详情
     * @param stcd
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public void listEveryDayStRsvrRByStcd(String stcd, String startDate, String endDate) {
        DetailManager.getInstance().listEveryDayStRsvrRByStcd(stcd,startDate,endDate)
                .subscribe(new LoadingSubject<WaterlevelBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(WaterlevelBean waterlevelBean) {
                        if(waterlevelBean != null){
                            if (waterlevelBean.getCode() == 0) {
                                mView.success(waterlevelBean);
                            }else{
                                mView.failure(waterlevelBean.getMsg());
                            }
                        }

                    }

                    @Override
                    protected void _onError(String message) {
                       LogUtil.e(TAG,"_onError listEveryDayStRsvrRByStcd"+ message);
                       mView.failure(message);
                    }
                });
    }

    /**
     * 查询某个图像站所有分页图片
     * @param stcd
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public void findPictureRByStcd(String stcd,String pageSize, String currentPage, String startTime, String endTime, boolean isrefresh) {
       DetailManager.getInstance().findPictureRByStcd(stcd,pageSize,currentPage,startTime,endTime)
               .subscribe(new LoadingSubject<ImageBean>(isrefresh,Utils.getContext().getString(R.string.data_loading)) {
                   @Override
                   protected void _onNext(ImageBean imageBean) {
                       if (imageBean != null) {
                           if (imageBean.getCode() == 0) {
                               LogUtil.e(TAG,"findPictureRByStcd请求成功 ");
                               mView.success(imageBean);
                           }else{
                               mView.failure(imageBean.getMsg());
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
