package com.tepia.main.view.main.work.task.taskdeal;

import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.loading.LoadingDialog;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.response.TaskDetailResponse;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TaskDealPresenter extends BasePresenterImpl<TaskDealContract.View> implements TaskDealContract.Presenter {

    public void getTaskDetail(String workOrderId, boolean isShow, String msg) {
        TaskManager.getInstance().getAppWorkByWorkId(workOrderId).subscribe(new LoadingSubject<TaskDetailResponse>(isShow, msg) {
            @Override
            protected void _onNext(TaskDetailResponse taskDetailResponse) {
                if (taskDetailResponse.getCode() == 0) {
                    if (mView != null) {
                        mView.getTaskDetailSucess(taskDetailResponse.getData());
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void appReservoirWorkOrderItemCommitOne(String workOrderId,
                                                   String itemId,
                                                   String exResult,
                                                   String exDesc,
                                                   String lgtd,
                                                   String lttd,
                                                   List<String> files,
                                                   List<String> endfiles,
                                                   boolean isShow,
                                                   String msg) {
        if (files.size() == 0 && endfiles.size() == 0) {
            TaskManager.getInstance().appReservoirWorkOrderItemCommitOne(workOrderId, itemId, exResult, exDesc, lgtd, lttd, files, endfiles)
                    .subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
                        @Override
                        protected void _onNext(BaseResponse response) {
                            LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                            if (response.getCode() == 0) {
                                if (mView != null) {
                                    mView.commitSucess();
                                }
                            }
                        }

                        @Override
                        protected void _onError(String message) {
                            LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                            ToastUtils.shortToast(message);
                        }
                    });
        } else {
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            ArrayList<String> filelist = new ArrayList<>();
            filelist.addAll(files);
            filelist.addAll(endfiles);
            Tiny.getInstance().source(filelist.toArray(new String[filelist.size()])).batchAsFile().withOptions(options).batchCompress(new FileBatchCallback() {
                @Override
                public void callback(boolean isSuccess, String[] outfile) {
                    //return the batch compressed file path
                    if (isSuccess) {
                        ArrayList<String> beforelist = new ArrayList<String>();
                        ArrayList<String> afterlist = new ArrayList<String>();
                        ArrayList<String> tempslist = new ArrayList<>();
                        Collections.addAll(tempslist, outfile);
                        beforelist.addAll(tempslist.subList(0, files.size()));
                        afterlist.addAll(tempslist.subList( files.size(),tempslist.size()));
                        TaskManager.getInstance().appReservoirWorkOrderItemCommitOne(workOrderId, itemId, exResult, exDesc, lgtd, lttd, beforelist, afterlist)
                                .subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
                                    @Override
                                    protected void _onNext(BaseResponse response) {
                                        LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                                        if (response.getCode() == 0) {
                                            if (mView != null) {
                                                mView.commitSucess();
                                            }
                                        }
                                    }

                                    @Override
                                    protected void _onError(String message) {
                                        LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                                        ToastUtils.shortToast(message);
                                    }
                                });
                    } else {
                        ToastUtils.shortToast("图片压缩失败");
                    }
                }
            });
        }
    }
}
