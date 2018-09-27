package com.tepia.main.model.task.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 任务实体
 * Created by Joeshould on 2018/5/31.
 */

public  class TaskBean  implements Serializable{


    /**
     * workOrderId : 01c0b2d2b86144a78bc88638982b181e
     * workOrderCode : XJ201807191459300901035
     * workOrderName : 东风水库201807巡检
     * planStartTime : 2018-07-22 00:00:00
     * planEndTime : 2018-07-22 00:00:00
     * executeStatus : 2
     * bizPlanInfo : {"planType":"2","planName":"2018-07-20东风水库日常巡检计划","operationType":"1"}
     * reservoirName : 东风水库
     * bizReservoirWorkOrderItems : [{"itemId":"8c5fa5f84035460db6245146f7efeb39","workOrderId":"01c0b2d2b86144a78bc88638982b181e","positionId":"089b678b89e046f0a86cd85dd2fecdba","superviseItemCode":"SK-QSB-1-1","reservoirSuperviseSequence":1,"executeId":"a623db0bdb92434ebe6553561aaf2eef","executeResultType":"0","executeResultDescription":"发生的JFK时间d","excuteLongitude":"123","excuteLatitude":"123","excuteDate":"2018-07-19 00:00:00","treeNames":"土石坝/坝体/坝坡","superviseItemName":"消防器材"}]
     */

    private String workOrderId;
    private String workOrderCode;
    private String workOrderName;
    private String planStartTime;
    private String startTime;
    private String executorName;
    private String operationType;
    private String problemNum;
    private String planEndTime;
    private String executeStatus;
    private String remarks;
    private String workOrderRoute;
    private String status;
    private String createBy;
    private String createDate;
    private BizPlanInfoBean bizPlanInfo;
    private String reservoirName;
    private List<TaskItemBean> bizReservoirWorkOrderItems;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getProblemNum() {
        return problemNum;
    }

    public void setProblemNum(String problemNum) {
        this.problemNum = problemNum;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getWorkOrderRoute() {
        return workOrderRoute;
    }

    public void setWorkOrderRoute(String workOrderRoute) {
        this.workOrderRoute = workOrderRoute;
    }

    public String getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(String workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getWorkOrderCode() {
        return workOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
    }

    public String getWorkOrderName() {
        return workOrderName;
    }

    public void setWorkOrderName(String workOrderName) {
        this.workOrderName = workOrderName;
    }

    public String getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(String planStartTime) {
        this.planStartTime = planStartTime;
    }

    public String getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(String planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(String executeStatus) {
        this.executeStatus = executeStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BizPlanInfoBean getBizPlanInfo() {
        return bizPlanInfo;
    }

    public void setBizPlanInfo(BizPlanInfoBean bizPlanInfo) {
        this.bizPlanInfo = bizPlanInfo;
    }

    public String getReservoirName() {
        return reservoirName;
    }

    public void setReservoirName(String reservoirName) {
        this.reservoirName = reservoirName;
    }

    public List<TaskItemBean> getBizReservoirWorkOrderItems() {
        return bizReservoirWorkOrderItems;
    }

    public void setBizReservoirWorkOrderItems(List<TaskItemBean> bizReservoirWorkOrderItems) {
        this.bizReservoirWorkOrderItems = bizReservoirWorkOrderItems;
    }

    public static class BizPlanInfoBean implements Serializable{
        /**
         * planType : 2
         * planName : 2018-07-20东风水库日常巡检计划
         * operationType : 1
         */

        private String planType;
        private String planName;
        private String operationType;

        public String getPlanType() {
            return planType;
        }

        public void setPlanType(String planType) {
            this.planType = planType;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }

        public String getOperationType() {
            return operationType;
        }

        public void setOperationType(String operationType) {
            this.operationType = operationType;
        }
    }

}
