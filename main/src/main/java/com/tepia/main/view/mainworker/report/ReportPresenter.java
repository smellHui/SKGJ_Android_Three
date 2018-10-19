package com.tepia.main.view.mainworker.report;

import android.text.TextUtils;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.R;
import com.tepia.main.model.question.QuestionManager;
import com.tepia.main.model.report.EmergenceListBean;
import com.tepia.main.model.report.ShangbaoManager;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import java.util.ArrayList;
import java.util.Collections;

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

    private SimpleLoadDialog simpleLoadDialog;

    /**
     * 上报水位
     * @param reservoirId
     * @param rz
     */
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
    public void getProblemList(String reservoirId, String workOrderId, String problemStatus, String startDate, String endDate, String currentPage, String pageSize,boolean ishowloading) {
        ShangbaoManager.getInstance().getProblemList(reservoirId, workOrderId, problemStatus, startDate, endDate, currentPage, pageSize)
                .subscribe(new LoadingSubject<EmergenceListBean>(ishowloading, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(EmergenceListBean emergenceListBean) {
                        if (emergenceListBean != null) {
                            if (emergenceListBean.getCode() == 0) {
                                mView.success(emergenceListBean);

                            }else{
                                mView.failure(emergenceListBean.getMsg());
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
    public void reportProblem(String reservoirId, String problemTitle, String problemDescription, String lgtd, String lttd, ArrayList<String> selectPhotos,String fileVedioPath) {
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
                        final ArrayList<String> tempslist = new ArrayList<>(10);
                        Collections.addAll(tempslist, outfile);
                        if(!TextUtils.isEmpty(fileVedioPath)){
                            tempslist.add(fileVedioPath);
                        }
                        appFeedback(reservoirId, problemTitle, problemDescription,lgtd,lttd, tempslist);

                    } else {
                        if (simpleLoadDialog != null) {
                            simpleLoadDialog.dismiss();
                        }
                        ToastUtils.shortToast("图片压缩失败");
                    }
                }
            });
        } else {
            if(!TextUtils.isEmpty(fileVedioPath)){
                selectPhotos.add(fileVedioPath);
            }
            appFeedback(reservoirId, problemTitle, problemDescription,lgtd,lttd, selectPhotos);
        }
    }


    private void appFeedback(String reservoirId,
                             String problemTitle,
                             String problemDescription,
                             String lgtd,
                             String lttd, ArrayList<String> files) {
        ShangbaoManager.getInstance().reportProblem(reservoirId, problemTitle, problemDescription,lgtd,lttd,files).subscribe(new LoadingSubject<BaseResponse>() {
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
                mView.failure(message);


            }
        });
    }
}
