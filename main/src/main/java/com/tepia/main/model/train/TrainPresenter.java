package com.tepia.main.model.train;

import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-27
 * Time    :       15:44
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class TrainPresenter extends BasePresenterImpl<TrainContract.View> implements TrainContract.Presenter{
    @Override
    public void getTrainList(String currentPage, String pageSize) {
        TrainManager.getInstance().getTrainList(currentPage, pageSize).subscribe(new LoadingSubject<TrainListResponse>(false,"正在加载中...") {
            @Override
            protected void _onNext(TrainListResponse trainListResponse) {
                if (trainListResponse.getCode()==0){
                    mView.success(trainListResponse);
                }else {
                    if (trainListResponse.getMsg()!=null && trainListResponse.getMsg().length()>0){
                        mView.failure(trainListResponse.getMsg());
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
    public void getTrainDetail(String id) {
        TrainManager.getInstance().getTrainDetail(id).subscribe(new LoadingSubject<TrainDetailResponse>(false,"正在加载中...") {
            @Override
            protected void _onNext(TrainDetailResponse trainDetailResponse) {
                if (trainDetailResponse.getCode()==0){
                    mView.success(trainDetailResponse);
                }else {
                    if (trainDetailResponse.getMsg()!=null && trainDetailResponse.getMsg().length()>0){
                        mView.failure(trainDetailResponse.getMsg());
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
