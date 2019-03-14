package com.tepia.main.view.main.work.task.taskdetail;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.dialog.loading.LoadingDialog;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.main.R;
import com.tepia.main.model.route.RoutepointDataManager;
import com.tepia.main.model.task.bean.TaskBean;
import com.tepia.main.model.task.bean.TaskItemBean;
import com.tepia.main.model.task.response.CandidateResponse;
import com.tepia.main.model.task.response.TaskDetailResponse;
import com.tepia.main.model.task.TaskManager;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileBatchCallback;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class TaskDetailPresenter extends BasePresenterImpl<TaskDetailContract.View> implements TaskDetailContract.Presenter {

    /**
     * @param workOrderId
     * @param isShow
     * @param msg
     */
    @Override
    public void getTaskDetail(String workOrderId, boolean isShow, String msg) {
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
                    mView.getTaskDetailSucess(templist.get(0));
                } else {
                    ToastUtils.shortToast(message);
                }
            }
        });
    }

    /**
     * 修改工单状态为执行中
     *
     * @param workOrderId
     * @param positionStr
     * @param isShow
     * @param msg
     */
    public void startExecute(String workOrderId, String positionStr, boolean isShow, String msg) {
        TaskManager.getInstance().startExecute(workOrderId, positionStr).subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
            @Override
            protected void _onNext(BaseResponse response) {
                if (mView != null) {
                    mView.startExecuteSucess();
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    /**
     * @param workOrderId
     * @param workOrderRoute
     * @param isShow
     * @param msg
     */
    public void endExecute(String workOrderId, String workOrderRoute, boolean isShow, String msg) {
        TaskManager.getInstance().endExecute(workOrderId, workOrderRoute).subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
            @Override
            protected void _onNext(BaseResponse response) {
                LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                if (mView != null) {
                    mView.endExecuteSucess();
                }
            }

            @Override
            protected void _onError(String message) {
                LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                ToastUtils.shortToast(message);
            }
        });
    }

    public void endExecute2(String workOrderId, String workOrderRoute, boolean isShow, String msg) {
        TaskManager.getInstance().endExecute2(workOrderId, workOrderRoute).subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
            @Override
            protected void _onNext(BaseResponse response) {
                LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                if (mView != null) {
                    mView.endExecuteSucess();
                }
            }

            @Override
            protected void _onError(String message) {
                LoadingDialog.with(AppManager.getInstance().getCurrentActivity()).dismiss();
                ToastUtils.shortToast(message);
            }
        });
    }

    public void getPeople(String workOrderId) {
        TaskManager.getInstance().candidate(workOrderId).subscribe(new LoadingSubject<CandidateResponse>(true, ResUtils.getString(R.string.data_loading)) {
            @Override
            protected void _onNext(CandidateResponse response) {
                if (mView != null) {
                    mView.getPeopleSucess(response.getData());
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void sendOrder(String workOrderId, String userCode) {
        TaskManager.getInstance().sendOrder(workOrderId, userCode).subscribe(new LoadingSubject<BaseResponse>(true, ResUtils.getString(R.string.data_loading)) {
            @Override
            protected void _onNext(BaseResponse response) {
                if (mView != null) {
                    mView.sendOrderSucess();
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void commitTotal(List<TaskItemBean> localData) {
        if (!CollectionsUtil.isEmpty(localData)) {
            TaskItemBean bean = localData.get(0);
            appReservoirWorkOrderItemCommitOneByOne(localData, 0);
        }
    }

    public void appReservoirWorkOrderItemCommitOneByOne(List<TaskItemBean> localData, int count) {
        if (!CollectionsUtil.isEmpty(localData)) {
            if (count < localData.size()) {
                TaskItemBean bean = localData.get(count);
                List<String> files = new Gson().fromJson(bean.getBeforelist(),new TypeToken<List<String>>(){}.getType());
                List<String> endfiles = new Gson().fromJson(bean.getAfterlist(),new TypeToken<List<String>>(){}.getType());
                if (files == null){
                    files = new ArrayList<>();
                }
                if (endfiles == null){
                    endfiles = new ArrayList<>();
                }
                appReservoirWorkOrderItemCommitOne(localData, count, bean.getWorkOrderId(), bean.getItemId(), bean.getExResult(), bean.getExDesc(), bean.getLgtd(), bean.getLttd(), bean.getExecuteDate(), files, endfiles, false, "");
            } else {
                mView.appReservoirWorkOrderItemCommitOneByOneSuccess();
            }
        }
    }

    public void appReservoirWorkOrderItemCommitOne(List<TaskItemBean> localData,
                                                   int count,
                                                   String workOrderId,
                                                   String itemId,
                                                   String exResult,
                                                   String exDesc,
                                                   String lgtd,
                                                   String lttd,
                                                   String executeDate,
                                                   List<String> files,
                                                   List<String> endfiles,
                                                   boolean isShow,
                                                   String msg) {
        if (files.size() == 0 && endfiles.size() == 0) {
            TaskManager.getInstance().appReservoirWorkOrderItemCommitOne(workOrderId, itemId, exResult, exDesc, lgtd, lttd, executeDate, files, endfiles)
                    .subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
                        @Override
                        protected void _onNext(BaseResponse response) {
                            if (response.getCode() == 0) {
                                List<TaskItemBean> templist = DataSupport.where("itemId=?", itemId).find(TaskItemBean.class);
                                if (!CollectionsUtil.isEmpty(templist)) {
                                    TaskItemBean bean = templist.get(0);
                                    bean.setCommitLocal(false);
                                    bean.setExResult("");
                                    bean.setExDesc("");
                                    bean.setAfterlist("");
                                    bean.setBeforelist("");
                                    bean.update();
                                }
                                appReservoirWorkOrderItemCommitOneByOne(localData, count + 1);
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
                        afterlist.addAll(tempslist.subList(files.size(), tempslist.size()));
                        TaskManager.getInstance().appReservoirWorkOrderItemCommitOne(workOrderId, itemId, exResult, exDesc, lgtd, lttd, executeDate, beforelist, afterlist)
                                .subscribe(new LoadingSubject<BaseResponse>(isShow, msg) {
                                    @Override
                                    protected void _onNext(BaseResponse response) {

                                        if (response.getCode() == 0) {
                                            List<TaskItemBean> templist = DataSupport.where("itemId=?", itemId).find(TaskItemBean.class);
                                            if (!CollectionsUtil.isEmpty(templist)) {
                                                TaskItemBean bean = templist.get(0);
                                                bean.setCommitLocal(false);
                                                bean.setExResult("");
                                                bean.setExDesc("");
                                                bean.setAfterlist("");
                                                bean.setBeforelist("");
                                                bean.update();
                                            }
                                            appReservoirWorkOrderItemCommitOneByOne(localData, count + 1);
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
