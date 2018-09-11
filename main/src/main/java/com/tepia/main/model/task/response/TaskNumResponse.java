package com.tepia.main.model.task.response;

import com.tepia.base.http.BaseResponse;

/**
 * Created by Joeshould on 2018/7/30.
 */

public class TaskNumResponse extends BaseResponse{

    /**
     * data : {"routingCount":0,"cleaningCount":0,"maintainCount":0,"totalCount":0}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * routingCount : 0
         * cleaningCount : 0
         * maintainCount : 0
         * totalCount : 0
         */

        private int routingCount;
        private int cleaningCount;
        private int maintainCount;
        private int totalCount;

        public int getRoutingCount() {
            return routingCount;
        }

        public void setRoutingCount(int routingCount) {
            this.routingCount = routingCount;
        }

        public int getCleaningCount() {
            return cleaningCount;
        }

        public void setCleaningCount(int cleaningCount) {
            this.cleaningCount = cleaningCount;
        }

        public int getMaintainCount() {
            return maintainCount;
        }

        public void setMaintainCount(int maintainCount) {
            this.maintainCount = maintainCount;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }
}
