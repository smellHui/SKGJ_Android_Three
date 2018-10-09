package com.tepia.main.model.train;

import android.text.TextUtils;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.model.jishu.yunwei.WorkOrderListResponse;
import com.tepia.main.model.report.ShangbaoManager;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import java.util.ArrayList;
import java.util.Collections;

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
    private SimpleLoadDialog simpleLoadDialog;

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

    @Override
    public void addTrain(String trainTitle, String position, String trainContent, String organizeCompany, String trainDate, ArrayList<String> selectPhotos, ArrayList<String> selectFiles) {
        simpleLoadDialog = new SimpleLoadDialog(AppManager.getInstance().getCurrentActivity(), "正在提交...", true);
        if (simpleLoadDialog != null) {
            simpleLoadDialog.show();
        }
        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
        if (selectPhotos != null && selectPhotos.size() > 0) {
            Tiny.getInstance().source(selectPhotos.toArray(new String[selectPhotos.size()])).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
                @Override
                public void callback(boolean isSuccess, String[] outfile) {
                    //return the batch compressed file path
                    if (isSuccess) {
                        final ArrayList<String> tempslist = new ArrayList<>();
//                        Collections.addAll(tempslist, outfile);
                        tempslist.addAll(selectPhotos);
                        appFeedback(trainTitle, position, trainContent, organizeCompany, trainDate, tempslist,selectFiles);
                    } else {
                        if (simpleLoadDialog != null) {
                            simpleLoadDialog.dismiss();
                        }
                        ToastUtils.shortToast("图片压缩失败");
                    }
                }
            });
        } else {
            appFeedback(trainTitle, position, trainContent, organizeCompany, trainDate,selectPhotos,selectFiles);
        }
    }

    private void appFeedback(String trainTitle, String position, String trainContent, String organizeCompany, String trainDate, ArrayList<String> imageFiles,ArrayList<String> files) {
        TrainManager.getInstance().addUserTrain(trainTitle, position, trainContent, organizeCompany, trainDate, imageFiles,files).subscribe(new LoadingSubject<BaseResponse>() {
            @Override
            protected void _onNext(BaseResponse baseResponse) {
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                if (baseResponse != null) {
                    if (baseResponse.getCode() == 0) {
                        mView.success(baseResponse);
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                ToastUtils.shortToast("提交失败");
            }
        });
    }
}
