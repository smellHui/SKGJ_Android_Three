package com.tepia.main.model.task.response;

import com.tepia.main.model.task.bean.TaskItemBean;

import java.util.List;

/**
 * Created by Joeshould on 2018/5/23.
 */

public class TaskItemListResponse {

    /**
     * data : [{"id":"27664fe7-3c50-442f-b6db-91f1f15167b3","orderNum":1,"executeSituation":0,"executeName":"","isOver":"hide","positionName":"坝顶","itemName":"有无裂缝、异常变形、积水或杂草丛生等现象","content":"","time":"","routingItemResult":1,"showType":[{"name":"无","value":"1","checked":true},{"name":"有","value":"0","checked":false}],"resultType":"1,2,3","positionNames":[{"title":"清底河水库"},{"title":"坝体"},{"title":"坝顶"}],"resultTypes":[{"title":"选"},{"title":"文"},{"title":"图"}]},{"id":"a6385abb-faed-456d-b615-35052458afb3","orderNum":2,"executeSituation":0,"executeName":"","isOver":"hide","positionName":"背水坡","itemName":"近坝水面有无冒泡、变浑、漩涡等异常现象","content":"","time":"","routingItemResult":1,"showType":[{"name":"无","value":"1","checked":true},{"name":"有","value":"0","checked":false}],"resultType":"1,2,3","positionNames":[{"title":"清底河水库"},{"title":"坝体"},{"title":"坝坡"},{"title":"背水坡"}],"resultTypes":[{"title":"选"},{"title":"文"},{"title":"图"}]},{"id":"2d070ec4-1eaf-43a6-846d-a7b2668bcc48","orderNum":3,"executeSituation":0,"executeName":"","isOver":"hide","positionName":"迎水坡","itemName":"近坝水面有无冒泡、变浑、漩涡等异常现象","content":"","time":"","routingItemResult":1,"showType":[{"name":"无","value":"1","checked":true},{"name":"有","value":"0","checked":false}],"resultType":"1,2,3","positionNames":[{"title":"清底河水库"},{"title":"坝体"},{"title":"坝坡"},{"title":"迎水坡"}],"resultTypes":[{"title":"选"},{"title":"文"},{"title":"图"}]},{"id":"aa3b8d48-d993-430e-b990-34e82abc222d","orderNum":4,"executeSituation":0,"executeName":"","isOver":"hide","positionName":"背水坡","itemName":"混凝土面板有无破损、裂缝、溶蚀破损现象","content":"","time":"","routingItemResult":1,"showType":[{"name":"无","value":"1","checked":true},{"name":"有","value":"0","checked":false}],"resultType":"1,2,3","positionNames":[{"title":"清底河水库"},{"title":"坝体"},{"title":"坝坡"},{"title":"背水坡"}],"resultTypes":[{"title":"选"},{"title":"文"},{"title":"图"}]},{"id":"ec8611f8-81e3-4f54-a224-00a22a2ed20f","orderNum":5,"executeSituation":0,"executeName":"","isOver":"hide","positionName":"迎水坡","itemName":"有无裂缝、剥落、滑动、隆起、塌坑、冲刷或植物滋生等现象","content":"","time":"","routingItemResult":1,"showType":[{"name":"无","value":"1","checked":true},{"name":"有","value":"0","checked":false}],"resultType":"1,2,3","positionNames":[{"title":"清底河水库"},{"title":"坝体"},{"title":"坝坡"},{"title":"迎水坡"}],"resultTypes":[{"title":"选"},{"title":"文"},{"title":"图"}]},{"id":"74fcb659-9515-4951-99ea-287d5a02f408","orderNum":6,"executeSituation":0,"executeName":"","isOver":"hide","positionName":"防浪墙二","itemName":"防浪墙结构有无开裂、松动、架空、变形和倾斜等情况","content":"","time":"","routingItemResult":1,"showType":[{"name":"无","value":"1","checked":true},{"name":"有","value":"0","checked":false}],"resultType":"1,2,3","positionNames":[{"title":"清底河水库"},{"title":"坝体"},{"title":"防浪墙二"}],"resultTypes":[{"title":"选"},{"title":"文"},{"title":"图"}]},{"id":"e4b5cc20-7641-4292-b6e4-be798bd07773","orderNum":7,"executeSituation":0,"executeName":"","isOver":"hide","positionName":"迎水坡","itemName":"砌石护坡有无块石松动、塌陷、垫层流失、架空或风化变质等损坏现象","content":"","time":"","routingItemResult":1,"showType":[{"name":"无","value":"1","checked":true},{"name":"有","value":"0","checked":false}],"resultType":"1,2,3","positionNames":[{"title":"清底河水库"},{"title":"坝体"},{"title":"坝坡"},{"title":"迎水坡"}],"resultTypes":[{"title":"选"},{"title":"文"},{"title":"图"}]},{"id":"ea6b5da4-c0c8-4b2d-881d-27b28e995e17","orderNum":8,"executeSituation":0,"executeName":"","isOver":"hide","positionName":"背水坡","itemName":"有无裂缝、剥落、滑动、隆起、塌坑、冲刷或植物滋生等现象","content":"","time":"","routingItemResult":1,"showType":[{"name":"无","value":"1","checked":true},{"name":"有","value":"0","checked":false}],"resultType":"1,2,3","positionNames":[{"title":"清底河水库"},{"title":"坝体"},{"title":"坝坡"},{"title":"背水坡"}],"resultTypes":[{"title":"选"},{"title":"文"},{"title":"图"}]},{"id":"de0b4d7c-1968-47e5-983d-d48e830165fa","orderNum":9,"executeSituation":0,"executeName":"","isOver":"hide","positionName":"防浪墙一","itemName":"防浪墙结构有无开裂、松动、架空、变形和倾斜等情况","content":"","time":"","routingItemResult":1,"showType":[{"name":"无","value":"1","checked":true},{"name":"有","value":"0","checked":false}],"resultType":"1,2,3","positionNames":[{"title":"清底河水库"},{"title":"坝体"},{"title":"防浪墙一"}],"resultTypes":[{"title":"选"},{"title":"文"},{"title":"图"}]},{"id":"1c9055ba-c9ba-4019-8733-1fe599ecbbb7","orderNum":10,"executeSituation":0,"executeName":"","isOver":"hide","positionName":"背水坡","itemName":"砌石护坡有无块石松动、塌陷、垫层流失、架空或风化变质等损坏现象","content":"","time":"","routingItemResult":1,"showType":[{"name":"无","value":"1","checked":true},{"name":"有","value":"0","checked":false}],"resultType":"1,2,3","positionNames":[{"title":"清底河水库"},{"title":"坝体"},{"title":"坝坡"},{"title":"背水坡"}],"resultTypes":[{"title":"选"},{"title":"文"},{"title":"图"}]}]
     * task : {"taskId":"1519a8a8-f800-4752-9ae3-6c9e06f40400","isBack":0,"woType":"巡检","planId":"075b0af4-adf8-4070-8f6c-953c9c15c4e2","executorId":"9517D806FD5D4CF09AB0E6206281F84D","reservoirId":"6052ccc6-e92a-4874-8448-0339be677eef","executeStatus":1,"workOrderCode":"XJ201803021522659065209","workOrderName":"清底河水库2018-03-12 00:00巡检","planStartTime":"2018-03-12 00:00","planEndTime":"2018-03-12 23:59","startTime":"","endTime":"","isExe":false}
     * isSubmit : false
     * status : true
     * msg : 获取数据成功
     */

    private TaskBean task;
    private boolean isSubmit;
    private boolean status;
    private String msg;
    private List<TaskItemBean> data;

    public TaskBean getTask() {
        return task;
    }

    public void setTask(TaskBean task) {
        this.task = task;
    }

    public boolean isIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(boolean isSubmit) {
        this.isSubmit = isSubmit;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<TaskItemBean> getData() {
        return data;
    }

    public void setData(List<TaskItemBean> data) {
        this.data = data;
    }

    public static class TaskBean {
        /**
         * taskId : 1519a8a8-f800-4752-9ae3-6c9e06f40400
         * isBack : 0
         * woType : 巡检
         * planId : 075b0af4-adf8-4070-8f6c-953c9c15c4e2
         * executorId : 9517D806FD5D4CF09AB0E6206281F84D
         * reservoirId : 6052ccc6-e92a-4874-8448-0339be677eef
         * executeStatus : 1
         * workOrderCode : XJ201803021522659065209
         * workOrderName : 清底河水库2018-03-12 00:00巡检
         * planStartTime : 2018-03-12 00:00
         * planEndTime : 2018-03-12 23:59
         * startTime :
         * endTime :
         * isExe : false
         */

        private String taskId;
        private int isBack;
        private String woType;
        private String planId;
        private String executorId;
        private String reservoirId;
        private int executeStatus;
        private String workOrderCode;
        private String workOrderName;
        private String planStartTime;
        private String planEndTime;
        private String startTime;
        private String endTime;
        private boolean isExe;

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public int getIsBack() {
            return isBack;
        }

        public void setIsBack(int isBack) {
            this.isBack = isBack;
        }

        public String getWoType() {
            return woType;
        }

        public void setWoType(String woType) {
            this.woType = woType;
        }

        public String getPlanId() {
            return planId;
        }

        public void setPlanId(String planId) {
            this.planId = planId;
        }

        public String getExecutorId() {
            return executorId;
        }

        public void setExecutorId(String executorId) {
            this.executorId = executorId;
        }

        public String getReservoirId() {
            return reservoirId;
        }

        public void setReservoirId(String reservoirId) {
            this.reservoirId = reservoirId;
        }

        public int getExecuteStatus() {
            return executeStatus;
        }

        public void setExecuteStatus(int executeStatus) {
            this.executeStatus = executeStatus;
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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public boolean isIsExe() {
            return isExe;
        }

        public void setIsExe(boolean isExe) {
            this.isExe = isExe;
        }
    }


}
