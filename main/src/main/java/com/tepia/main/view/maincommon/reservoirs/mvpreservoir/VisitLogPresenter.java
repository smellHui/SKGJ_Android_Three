package com.tepia.main.view.maincommon.reservoirs.mvpreservoir;

import android.text.TextUtils;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.main.R;
import com.tepia.main.model.report.ShangbaoManager;
import com.tepia.main.model.reserviros.ReservirosManager;
import com.tepia.main.model.reserviros.VisitLogBean;
import com.tepia.main.model.reserviros.VisitLogDetailBean;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-21
 * Time            :       下午6:03
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       到访日志present实现
 **/
public class VisitLogPresenter extends BasePresenterImpl<VisitLogContract.View> implements VisitLogContract.Presenter {
    private SimpleLoadDialog simpleLoadDialog;

    @Override
    public void getPageList(String reservoirId, String currentPage, String pageSize,boolean isshowloading) {
        ReservirosManager.getInstance().getPageList(reservoirId,currentPage,pageSize)
                .subscribe(new LoadingSubject<VisitLogBean>(isshowloading, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(VisitLogBean visitLogBean) {
                        if (visitLogBean != null) {
                            if (visitLogBean.getCode() == 0) {
                                mView.success(visitLogBean);

                            }else{
                                mView.failure(visitLogBean.getMsg());
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
    public void detail(String id) {
        ReservirosManager.getInstance().detail(id)
                .subscribe(new LoadingSubject<VisitLogDetailBean>(true, Utils.getContext().getString(R.string.data_loading)) {
                    @Override
                    protected void _onNext(VisitLogDetailBean visitLogDetailBean) {
                        if (visitLogDetailBean != null) {
                            if (visitLogDetailBean.getCode() == 0) {
                                mView.success(visitLogDetailBean);

                            }else{
                                mView.failure(visitLogDetailBean.getMsg());
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
     * 新增到访日志
     * @param reservoirId
     * @param visitCause
     * @param workContent
     * @param remark
     * @param visitTime
     * @param selectPhotos
     * @return
     */
    @Override
    public void insert(String reservoirId, String visitCause, String workContent, String remark, String visitTime, ArrayList<String> selectPhotos) {
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

                        appFeedback(reservoirId, visitCause, workContent,remark,visitTime, tempslist);

                    } else {
                        if (simpleLoadDialog != null) {
                            simpleLoadDialog.dismiss();
                        }
                        ToastUtils.shortToast("图片压缩失败");
                    }
                }
            });
        } else {

            appFeedback(reservoirId, visitCause, workContent,remark,visitTime, selectPhotos);
        }
    }

    private void appFeedback(String reservoirId,
                             String visitCause,
                             String workContent,
                             String remark,
                             String visitTime,
                             ArrayList<String> files) {
        ReservirosManager.getInstance().insert(reservoirId, visitCause, workContent, remark, visitTime, files).subscribe(new LoadingSubject<BaseResponse>() {
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
