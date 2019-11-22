package com.tepia.main.view.main.work.task.taskdeal;

import com.google.gson.Gson;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.loading.LoadingDialog;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.main.model.task.TaskManager;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.model.task.response.TaskDetailResponse;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/3/14 9:28
 * @修改人         ：      
 * @修改时间       :       2019/3/14 9:28
 * @功能描述       :
 **/

public class TaskDealPresenter extends BasePresenterImpl<TaskDealContract.View> implements TaskDealContract.Presenter {

    public void getTaskDetail(String workOrderId, boolean isShow, String msg) {
        List<TaskBean> templist = DataSupport.where("workOrderId=?", workOrderId).find(TaskBean.class);
        if (!CollectionsUtil.isEmpty(templist)) {
            mView.getTaskDetailSucess(templist.get(0));
        }
        TaskManager.getInstance().getAppWorkByWorkId(workOrderId).subscribe(new LoadingSubject<TaskDetailResponse>(isShow, msg) {
            @Override
            protected void _onNext(TaskDetailResponse taskDetailResponse) {
                if (taskDetailResponse.getCode() == 0) {
                    if (mView != null) {
                        taskDetailResponse.getData().save();
                        List<TaskBean> templist = DataSupport.where("workOrderId=?", workOrderId).find(TaskBean.class);
                        if (!CollectionsUtil.isEmpty(templist)) {
                            mView.getTaskDetailSucess(templist.get(0));
                        } else {
                            mView.getTaskDetailSucess(taskDetailResponse.getData());
                        }

                    }
                }
            }

            @Override
            protected void _onError(String message) {
                List<TaskBean> templist = DataSupport.where("workOrderId=?", workOrderId).find(TaskBean.class);
                if (!CollectionsUtil.isEmpty(templist)) {
//                    mView.getTaskDetailSucess(templist.get(0));
                } else {
//                    ToastUtils.shortToast(message);
                }

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
        List<TaskItemBean> templist = DataSupport.where("itemId=?", itemId).find(TaskItemBean.class);
        if (!CollectionsUtil.isEmpty(templist)) {
            TaskItemBean bean = templist.get(0);
            bean.setCommitLocal(true);
            bean.setExResult(exResult);
            bean.setExDesc(exDesc);
            bean.setLgtd(lgtd);
            bean.setLttd(lttd);
            bean.setExecuteDate(TimeFormatUtils.getStringDate());
            bean.setBeforelist(new Gson().toJson(files));
            bean.setAfterlist(new Gson().toJson(endfiles));
            bean.update();
            if (mView != null) {
                mView.commitSucess();
            }
        }

        if (files.size() == 0 && endfiles.size() == 0) {
            TaskManager.getInstance().appReservoirWorkOrderItemCommitOne(workOrderId, itemId, exResult, exDesc, lgtd, lttd, files, endfiles)
                    .subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
                        @Override
                        protected void _onNext(BaseResponse response) {
                            LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                            if (response.getCode() == 0) {
                                List<TaskItemBean> templist = DataSupport.where("itemId=?", itemId).find(TaskItemBean.class);
                                if (!CollectionsUtil.isEmpty(templist)) {
                                    TaskItemBean bean = templist.get(0);
                                    bean.setCommitLocal(false);
                                    bean.setExResult("");
                                    bean.setExDesc("");
                                    bean.setAfterlist("");
                                    bean.setBeforelist("");
                                    bean.setCompleteStatus("1");
                                    bean.setExecuteResultType(exResult);
                                    bean.setExecuteResultDescription(exDesc);
                                    bean.update();
                                    getTaskDetail(workOrderId,true,"请稍候...");
                                }

                            }
                        }

                        @Override
                        protected void _onError(String message) {
                            LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                            List<TaskItemBean> templist = DataSupport.where("itemId=?", itemId).find(TaskItemBean.class);
                            if (!CollectionsUtil.isEmpty(templist)) {
                                TaskItemBean bean = templist.get(0);
                                bean.setCommitLocal(true);
                                bean.setExResult(exResult);
                                bean.setExDesc(exDesc);
                                bean.setLgtd(lgtd);
                                bean.setLttd(lttd);
                                bean.setExecuteDate(TimeFormatUtils.getStringDate());
                                bean.setBeforelist(new Gson().toJson(files));
                                bean.setAfterlist(new Gson().toJson(endfiles));
                                bean.update();
                            }
//                            ToastUtils.shortToast("提交数据失败，已存储到本地");
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
                        afterlist.addAll(tempslist.subList(files.size(), tempslist.size()));
                        TaskManager.getInstance().appReservoirWorkOrderItemCommitOne(workOrderId, itemId, exResult, exDesc, lgtd, lttd, beforelist, afterlist)
                                .subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
                                    @Override
                                    protected void _onNext(BaseResponse response) {
                                        LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                                        if (response.getCode() == 0) {
                                            List<TaskItemBean> templist = DataSupport.where("itemId=?", itemId).find(TaskItemBean.class);
                                            if (!CollectionsUtil.isEmpty(templist)) {
                                                TaskItemBean bean = templist.get(0);
                                                bean.setCommitLocal(false);
                                                bean.setExResult("");
                                                bean.setExDesc("");
                                                bean.setAfterlist("");
                                                bean.setBeforelist("");
                                                bean.setCompleteStatus("1");
                                                bean.setExecuteResultType(exResult);
                                                bean.setExecuteResultDescription(exDesc);
                                                bean.update();
                                                getTaskDetail(workOrderId,false,"");
                                            }
//                                            if (mView != null) {
//                                                mView.commitSucess();
//                                            }
                                        }
                                    }

                                    @Override
                                    protected void _onError(String message) {
                                        LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                                        List<TaskItemBean> templist = DataSupport.where("itemId=?", itemId).find(TaskItemBean.class);
                                        if (!CollectionsUtil.isEmpty(templist)) {
                                            TaskItemBean bean = templist.get(0);
                                            bean.setCommitLocal(true);
                                            bean.setExResult(exResult);
                                            bean.setExDesc(exDesc);
                                            bean.setLgtd(lgtd);
                                            bean.setLttd(lttd);
                                            bean.setExecuteDate(TimeFormatUtils.getStringDate());
                                            bean.setBeforelist(new Gson().toJson(files));
                                            bean.setAfterlist(new Gson().toJson(endfiles));
                                            bean.update();
//                                            if (mView != null) {
//                                                mView.commitSucess();
//                                            }
                                        }
//                                        ToastUtils.shortToast("提交数据失败，已存储到本地");
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
