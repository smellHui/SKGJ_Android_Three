package com.tepia.main.model.jishu.yunwei;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-09-25
 * Time    :       12:06
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class WorkOrderListResponse extends BaseResponse{

    /**
     * data : {"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"total":2,"pages":1,"list":[{"workOrderId":"01bd603f63584e45bec2ada167c0d8f5","planStartTime":"2018-09-19 10:49:56","planEndTime":"2018-09-26 10:49:56","startTime":"2018-09-18 15:06:20","executorId":"474ddcf5df1146d4b683167f482d7a7d","executeStatus":"2","operationType":"1","executorName":"播州管理员"},{"workOrderId":"4fff507488a8421ba4695c2c08b22745","planStartTime":"2018-09-19 10:10:18","planEndTime":"2018-09-28 10:51:10","startTime":"2018-09-19 10:10:18","executorId":"d76b251318494686a4c799f34ccba039","executeStatus":"2","operationType":"1","executorName":"鲁志平"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * pageNum : 1
         * pageSize : 10
         * size : 2
         * startRow : 1
         * endRow : 2
         * total : 2
         * pages : 1
         * list : [{"workOrderId":"01bd603f63584e45bec2ada167c0d8f5","planStartTime":"2018-09-19 10:49:56","planEndTime":"2018-09-26 10:49:56","startTime":"2018-09-18 15:06:20","executorId":"474ddcf5df1146d4b683167f482d7a7d","executeStatus":"2","operationType":"1","executorName":"播州管理员"},{"workOrderId":"4fff507488a8421ba4695c2c08b22745","planStartTime":"2018-09-19 10:10:18","planEndTime":"2018-09-28 10:51:10","startTime":"2018-09-19 10:10:18","executorId":"d76b251318494686a4c799f34ccba039","executeStatus":"2","operationType":"1","executorName":"鲁志平"}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * workOrderId : 01bd603f63584e45bec2ada167c0d8f5
             * planStartTime : 2018-09-19 10:49:56
             * planEndTime : 2018-09-26 10:49:56
             * startTime : 2018-09-18 15:06:20
             * executorId : 474ddcf5df1146d4b683167f482d7a7d
             * executeStatus : 2     2是执行中
             * operationType : 1
             * executorName : 播州管理员
             */

            private String workOrderId;
            private String planStartTime;
            private String planEndTime;
            private String startTime;
            private String executorId;
            private String executeStatus;
            private String operationType;
            private String executorName;

            public String getWorkOrderId() {
                return workOrderId;
            }

            public void setWorkOrderId(String workOrderId) {
                this.workOrderId = workOrderId;
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

            public String getExecutorId() {
                return executorId;
            }

            public void setExecutorId(String executorId) {
                this.executorId = executorId;
            }

            public String getExecuteStatus() {
                return executeStatus;
            }

            public void setExecuteStatus(String executeStatus) {
                this.executeStatus = executeStatus;
            }

            public String getOperationType() {
                return operationType;
            }

            public void setOperationType(String operationType) {
                this.operationType = operationType;
            }

            public String getExecutorName() {
                return executorName;
            }

            public void setExecutorName(String executorName) {
                this.executorName = executorName;
            }
        }
    }
}
