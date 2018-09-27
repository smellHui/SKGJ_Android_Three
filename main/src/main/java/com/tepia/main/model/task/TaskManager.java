package com.tepia.main.model.task;


import com.tepia.base.http.BaseResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.main.APPCostant;
import com.tepia.main.model.task.response.AllItemListResponse;
import com.tepia.main.model.task.response.CandidateResponse;
import com.tepia.main.model.task.response.TaskDetailResponse;
import com.tepia.main.model.task.response.TaskItemDetailResponse;
import com.tepia.main.model.task.response.TaskItemListResponse;
import com.tepia.main.model.task.response.TaskListResponse;
import com.tepia.main.model.task.response.TaskNumResponse;
import com.tepia.main.model.user.UserManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by Joeshould on 2018/5/23.
 */

public class TaskManager {
    private static final TaskManager ourInstance = new TaskManager();
    private TaskHttpService mRetrofitService;

    public static TaskManager getInstance() {
        return ourInstance;
    }

    private TaskManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPCostant.API_SERVER_URL + APPCostant.API_SERVER_TASK_AREA).create(TaskHttpService.class);
    }

    /**
     * 根据条件查询工单列表信息
     *
     * @param workOrderName
     * @param executeStatus 1-待办任务，2-执行中任务，3-已完成任务，4-已审核任务
     * @param operationType
     * @param startTime
     * @param endTime
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Observable<TaskListResponse> listAppWorkOrder(String workOrderName, int executeStatus, String operationType,
                                                         String startTime, String endTime, int currentPage, int pageSize) {
        String token = UserManager.getInstance().getToken();

        if (executeStatus == 0) {
            return mRetrofitService.listAppWorkOrder2(token, workOrderName, operationType, startTime, endTime, currentPage, pageSize)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            return mRetrofitService.listAppWorkOrder(token, workOrderName, executeStatus, operationType, startTime, endTime, currentPage, pageSize)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    /**
     * 获取工单列表
     *
     * @param workOrderName
     * @param executeStatus
     * @param operationType
     * @param startTime
     * @param endTime
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Observable<TaskListResponse> listPageWorks(String workOrderName, int executeStatus, String operationType,
                                                      String startTime, String endTime, int currentPage, int pageSize) {
        String token = UserManager.getInstance().getToken();
        String planPeriod = null;
        if (executeStatus == Integer.MAX_VALUE) {
            return mRetrofitService.listPageWorks2(token, workOrderName, operationType, operationType, planPeriod, startTime, endTime, currentPage, pageSize)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            return mRetrofitService.listPageWorks(token, workOrderName, executeStatus, operationType, operationType, planPeriod, startTime, endTime, currentPage, pageSize)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    /**
     * 根据工单ID查询工单信息
     *
     * @param workOrderId 工单ID
     * @return
     */
    public Observable<TaskDetailResponse> getAppWorkByWorkId(String workOrderId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getAppWorkByWorkId(token, workOrderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 开始执行任务
     *
     * @param workOrderId
     * @param positionStr
     * @return
     */
    public Observable<BaseResponse> startExecute(String workOrderId, String positionStr) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.startExecute(token, workOrderId, positionStr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 完成执行
     *
     * @param workOrderId
     * @param positionStr
     * @return
     */
    public Observable<BaseResponse> endExecute(String workOrderId, String positionStr) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.endExecute(token, workOrderId, positionStr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取 任务项 详细信息
     *
     * @param itemId
     * @return
     */
    public Observable<TaskItemDetailResponse> getAppReservoirWorkOrderItemInfo(String itemId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getAppReservoirWorkOrderItemInfo(token, itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 删除照片
     *
     * @param fileId
     * @return
     */
    public Observable<BaseResponse> delFile(String fileId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.delFile(token, fileId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 提交处理内容
     *
     * @param workOrderId
     * @param itemId
     * @param exResult
     * @param exDesc
     * @param lgtd
     * @param lttd
     * @param files
     * @param endfiles
     * @return
     */
    public Observable<BaseResponse> appReservoirWorkOrderItemCommitOne(String workOrderId,
                                                                       String itemId,
                                                                       String exResult,
                                                                       String exDesc,
                                                                       String lgtd,
                                                                       String lttd,
                                                                       List<String> files,
                                                                       List<String> endfiles) {
        String token = UserManager.getInstance().getToken();
        Map<String, RequestBody> params = new HashMap<>();
        params.put("workOrderId", RetrofitManager.convertToRequestBody(workOrderId));
        params.put("itemId", RetrofitManager.convertToRequestBody(itemId));
        params.put("exResult", RetrofitManager.convertToRequestBody(exResult));
        params.put("exDesc", RetrofitManager.convertToRequestBody(exDesc));
        params.put("lgtd", RetrofitManager.convertToRequestBody(lgtd));
        params.put("lttd", RetrofitManager.convertToRequestBody(lttd));

        List<File> beforefileList = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i));
            beforefileList.add(file);
        }
        List<MultipartBody.Part> beforePathList = RetrofitManager.filesToMultipartBodyParts("files", beforefileList);

        List<File> afterfileList = new ArrayList<>();
        for (int i = 0; i < endfiles.size(); i++) {
            File file = new File(endfiles.get(i));
            afterfileList.add(file);
        }
        List<MultipartBody.Part> afterPathList = RetrofitManager.filesToMultipartBodyParts("endfiles", afterfileList);
        return mRetrofitService.appReservoirWorkOrderItemCommitOne(token, params, beforePathList, afterPathList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 获取任务个数
     *
     * @return
     */
    public Observable<TaskNumResponse> getTaskNum() {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.statisticsEorkByType(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取工单下发候选用户
     *
     * @return
     */
    public Observable<CandidateResponse> candidate(String workOrderId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.candidateTask(token, workOrderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 删除作废工单
     *
     * @param workOrderId
     * @return
     */
    public Observable<BaseResponse> deleteWork(String workOrderId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.deleteWork(token, workOrderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取工单审批流水列表
     *
     * @param workOrderId
     * @return
     */
    public Observable<BaseResponse> getExamineFlowList(String workOrderId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getExamineFlowList(token, workOrderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 下发工单
     *
     * @param workOrderId
     * @param userCode
     * @return
     */
    public Observable<BaseResponse> sendOrder(String workOrderId, String userCode) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.sendOrder(token, workOrderId, userCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 修改工单信息以及工单配置项
     *
     * @param workOrderId
     * @param workOrderName
     * @param planStartTime
     * @param planEndTime
     * @param remarks
     * @param workOrderId
     * @return
     */
    public Observable<BaseResponse> updateWork(String workOrderId, String workOrderName, String planStartTime, String planEndTime, String remarks, String workOrderItemIds) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.updateWork(token, workOrderId, workOrderName, null, null, remarks, workOrderItemIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<AllItemListResponse> getAllItemList(String workOrderId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getAllItemList(token, workOrderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 分页查询巡查工单列表
     *
     * @param operationType
     * @param reservoirId
     * @param startDate
     * @param endDate
     * @param currentPage
     * @param pageSize
     */
    public Observable<TaskListResponse> getPatrolWorkOrderList(String operationType, String reservoirId, String startDate, String endDate, String currentPage, String pageSize) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getPatrolWorkOrderList(token, operationType, reservoirId, startDate, endDate, currentPage, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<TaskDetailResponse> newStartExecute(String reservoirId, String reservoirName, String operationType) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.newStartExecute(token, reservoirId, reservoirName, operationType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<WorkOrderNumResponse> getWorkOrderNumByReservoirId(String reservoirId, String operationType) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getWorkOrderNumByReservoirId(token, reservoirId, operationType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<TaskItemListResponse> getItemListByReservoirId(String reservoirId, String operationType) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getItemListByReservoirId(token, reservoirId, operationType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<UnfinishedNumResponse> getUnfinishedNum(String reservoirId, String operationType) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getUnfinishedNum(token, reservoirId, operationType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
